package Infrastructure.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Application.Interface.IDeliveryRepository;
import Domain.Entity.Company;
import Domain.Entity.Delivery;
import Domain.Entity.Order;
import Infrastructure.Connector.Connect;
import Infrastructure.Service.IExist;

public class DeliveryRepository implements IDeliveryRepository, IExist {
    private final Connect db = Connect.getInstance();
    private String query = null;
    private OrderRepository od = new OrderRepository();
    private CompanyRepository cp = new CompanyRepository();

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
                        + "VALUES(%s, %s, %s)",
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
                        "SELECT companyID FROM orderDetail WHERE orderID = %s", delivery.getDeliveryID());
                ResultSet companyResult = db.executeQuery(companyQuery);
                String companyID = companyResult.getString("companyID");
                Company deliveryCompany = cp.getCompany(companyID);
                delivery.setDeliveryCompany(deliveryCompany);

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
