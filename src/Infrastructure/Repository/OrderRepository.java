package Infrastructure.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Application.Interfaces.IOrderRepository;
import Domain.Entity.Order;
import Domain.Entity.Product;
import Domain.Entity.User;
import Domain.ValueObject.Address;
import Domain.ValueObject.Payment;
import Infrastructure.Connector.Connect;
import Infrastructure.Service.IExist;

public class OrderRepository implements IOrderRepository, IExist {
    private final Connect db = Connect.getInstance();
    private String query = null;
    private AddressRepository ad = new AddressRepository();
    private PaymentRepository pm = new PaymentRepository();
    private ProductRepository pd = new ProductRepository();
    private UserRepository us = new UserRepository();

    @Override
    public boolean isNotExist(String id) {
        query = String.format(
                "SELECT * FROM order " +
                        "WHERE orderID = %s",
                id);
        try (ResultSet result = db.executeQuery(query)) {
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteOrder(String orderID) {
        if (isNotExist(orderID)) {
            return false;
        }
        query = String.format("DELETE FROM order " + "WHERE orderID = %s", orderID);
        db.executeUpdate(query);
        ad.deleteAddress(orderID);
        pm.deletePayment(orderID);
        return true;
    }

    @Override
    public void addOrder(String orderID, Date orderDate, int orderPrice, ArrayList<Product> orderProduct,
            Address orderAddress, String orderStatus, Payment orderPayment, User orderUser) {
        query = String.format(
                "INSERT INTO order(orderID, orderDate, orderPrice, orderStatus) "
                        + "VALUES(%s, %s, %d, %s)",
                orderID, orderDate, orderPrice, orderStatus);
        db.executeUpdate(query);
        ad.addAddress(orderID, orderAddress.getStreet(), orderAddress.getCity(), orderAddress.getZipCode());
        for (Product product : orderProduct) {
            pd.reduceQuantity(product.getProductID(), product.getProductQuantity());
        }
        pm.addPayment(orderID, orderPayment.getPaymentName(), orderPayment.getPaymentType(),
                orderPayment.getPaymentPrice());
        for (Product product : orderProduct) {
            query = String.format("INSERT INTO orderDetail(orderID, userID, productID)" + "VALUES(%s, %s, %s)", orderID,
                    orderUser.getUserID(),
                    product.getProductID());
        }

    }

    private ArrayList<Order> getOrderWithQuery(String query) {
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet rs = db.executeQuery(query);

        try {
            while (rs.next()) {
                String orderID = rs.getString("orderID");
                Date orderDate = rs.getDate("orderDate");
                int orderPrice = rs.getInt("orderPrice");
                String orderStatus = rs.getString("orderStatus");
                orders.add(new Order(orderID, orderDate, orderPrice, null, null, orderStatus, null, null));
            }
            for (Order order : orders) {
                String addressQuery = String.format("SELECT street, name, zipcode FROM address " + "WHERE ID = %s",
                        order.getOrderID());
                ResultSet addressResult = db.executeQuery(addressQuery);
                String street = addressResult.getString("street");
                String city = addressResult.getString("city");
                String zipCode = addressResult.getString("zipcode");
                order.setOrderAddress(new Address(street, city, zipCode));

                String paymentQuery = String.format(
                        "SELECT paymentName, paymentType, paymentPrice FROM payment WHERE ID = %s",
                        order.getOrderID());
                ResultSet paymentResult = db.executeQuery(paymentQuery);
                String paymentName = paymentResult.getString("paymentName");
                String paymentType = paymentResult.getString("paymentType");
                int paymentPrice = paymentResult.getInt("paymentPrice");
                order.setOrderPayment(new Payment(paymentName, paymentType, paymentPrice));

                String userQuery = String.format("SELECT userID FROM orderDetail WHERE orderID = %s",
                        order.getOrderID());
                ResultSet userResult = db.executeQuery(userQuery);
                String userID = userResult.getString("userID");
                User orderUser = us.getUser(userID);
                order.setOrderUser(orderUser);

                ArrayList<Product> products = new ArrayList<>();
                for (Product product : order.getOrderProduct()) {
                    product = pd.getProduct(product.getProductID());
                    products.add(product);
                }
                order.setOrderProduct(products);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAllOrder() {
        String query = String.format("SELECT * FROM order");
        return getOrderWithQuery(query);
    }

    @Override
    public Order getOrder(String orderID) {
        String query = String.format("SELECT * FROM order " + "WHERE orderID = %s", orderID);
        ArrayList<Order> orders = getOrderWithQuery(query);
        return orders.get(0);
    }
}
