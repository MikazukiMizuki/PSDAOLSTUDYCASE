package Infrastructure.Database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.Entity.Company;
import Domain.Entity.Delivery;
import Domain.Entity.Order;
import Domain.ValueObject.Address;
import Infrastructure.Connector.Connect;
import Infrastructure.Interface.IDeliveryRepository;
import Infrastructure.Service.IExist;

public class DeliveryDatabase implements IDeliveryRepository, IExist {
    private final Connect db = Connect.getInstance();
    private String query = null;
    private CompanyDatabase cp = new CompanyDatabase();
    private OrderDatabase od = new OrderDatabase();

    @Override
    public boolean isNotExist(String id) {
        query = String.format(
                "SELECT * FROM delivery " +
                        "WHERE deliveryID = %s",
                id);
        try (ResultSet result = db.executeQuery(query)) {
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteDelivery(String deliveryID) {
        if (isNotExist(deliveryID)) {
            return false;
        }
        query = String.format("DELETE FROM delivery " + "WHERE deliveryID = %s", deliveryID);
        return true;
    }

    @Override
    public void addDelivery(String deliveryID, Date deliveryDate, ArrayList<Order> deliveryOrder, String deliveryStatus,
            Company deliveryCompany) {
        query = String.format(
                "INSERT INTO order(deliveryID, deliveryDate, deliveryStatus) "
                        + "VALUES(%s, %s, %s, %s)",
                deliveryID, deliveryDate, deliveryStatus);
        db.executeUpdate(query);

        for (Order order : deliveryOrder) {
            query = String.format("INSERT INTO orderDetail(deliveryID, companyID, orderID)" + "VALUES(%s, %s, %s)",
                    deliveryID, deliveryCompany.getCompanyID(), order.getOrderID());
            db.executeUpdate(query);
        }
    }

    private ArrayList<Delivery> getDeliveryWithQuery(String query) {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        ResultSet rs = db.executeQuery(query);

        try {
            while (rs.next()) {
                String deliveryID = rs.getString("deliveryID");
                Date deliveryDate = rs.getDate("deliveryDate");
                String deliveryStatus = rs.getString("deliveryStatus");
                deliveries.add(new Delivery(deliveryID, deliveryDate, null, deliveryStatus, null));
            }

            for (Delivery delivery : deliveries) {
                String companyQuery = String.format(
                        "SELECT TOP(1) companyName FROM orderDetail JOIN order ON orderDetail.orderID = order.orderID");
                ResultSet companyResult = db.executeQuery(companyQuery);
                String companyID = companyResult.getString("companyID");
                String companyName = companyResult.getString("companyName");
                String companyEmail = companyResult.getString("companyEmail");

                String addressQuery = String.format("SELECT street, name, zipcode FROM address " + "WHERE ID = %s",
                        companyID);
                ResultSet addressResult = db.executeQuery(addressQuery);
                String street = addressResult.getString("street");
                String city = addressResult.getString("city");
                String zipCode = addressResult.getString("zipcode");
                Address address = new Address(street, city, zipCode);
                Company company = new Company(companyID, companyName, address, companyEmail);
                delivery.setDeliveryCompany(company);

                ArrayList<Order> orders = new ArrayList<>();
                for (Order order : delivery.getDeliveryOrder()) {
                    order = od.getOrder(order.getOrderID());
                    orders.add(order);
                }
                delivery.setDeliveryOrder(orders);
            }
            return deliveries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Delivery> getAllDelivery() {
        String query = String.format("SELECT * FROM order");
        return getDeliveryWithQuery(query);
    }

    @Override
    public Delivery getDelivery(String deliveryID) {
        String query = String.format("SELECT * FROM order " + "WHERE orderID = %s", deliveryID);
        ArrayList<Delivery> deliveries = getDeliveryWithQuery(query);
        return deliveries.get(0);
    }
}
