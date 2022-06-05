package Infrastructure.Interface;

import java.sql.Date;
import java.util.ArrayList;

import Domain.Entity.Order;
import Domain.Entity.Product;
import Domain.ValueObject.Address;
import Domain.ValueObject.Payment;

public interface IOrderRepository {
    public boolean deleteOrder(String orderID);

    public void addOrder(String orderID, Date orderDate, int orderPrice, ArrayList<Product> orderProduct,
            Address orderAddress,
            String orderStatus, Payment orderPayment);

    public ArrayList<Order> getAllOrder();

    public Order getOrder(String orderID);
}
