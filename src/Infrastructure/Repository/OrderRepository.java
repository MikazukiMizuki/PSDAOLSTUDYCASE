package Infrastructure.Repository;

import java.sql.Date;
import java.util.ArrayList;

import Domain.Entity.Order;
import Domain.Entity.Product;
import Domain.ValueObject.Address;
import Domain.ValueObject.Payment;
import Infrastructure.Interface.IOrderRepository;

public class OrderRepository implements IOrderRepository {
    private IOrderRepository shopediaDB;
    private ArrayList<Order> orders = null;
    private String query = null;
    private Order order = null;

    private void resetQuery() {
        orders = null;
        query = null;
        order = null;
    }

    @Override
    public boolean deleteOrder(String orderID) {
        resetQuery();
        return shopediaDB.deleteOrder(orderID);
    }

    @Override
    public void addOrder(String orderID, Date orderDate, int orderPrice, ArrayList<Product> orderProduct,
            Address orderAddress, String orderStatus, Payment orderPayment) {
        resetQuery();
        shopediaDB.addOrder(orderID, orderDate, orderPrice, orderProduct, orderAddress, orderStatus, orderPayment);
    }

    @Override
    public ArrayList<Order> getAllOrder() {
        if (orders == null || query != null) {
            resetQuery();
            orders = shopediaDB.getAllOrder();
            if (orders == null) {
                return new ArrayList<>();
            }
        }
        return orders;
    }

    @Override
    public Order getOrder(String orderID) {
        if (order == null || query != null) {
            resetQuery();
            order = shopediaDB.getOrder(orderID);
            if (order == null) {
                return null;
            }
        }
        return order;
    }

}
