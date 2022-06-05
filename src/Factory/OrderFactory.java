package Factory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import Domain.Entity.Order;
import Domain.Entity.Product;
import Domain.Entity.User;
import Domain.Service.IGenerateID;
import Domain.ValueObject.Address;
import Domain.ValueObject.Payment;

public class OrderFactory implements IGenerateID {
    public Order createOrder(Date orderDate, int orderPrice, ArrayList<Product> orderProduct, Address orderAddress,
            String orderStatus, Payment orderPayment, User orderUser) {
        String orderID = generateID();
        return new Order(orderID, orderDate, orderPrice, orderProduct, orderAddress, orderStatus, orderPayment,
                orderUser);
    }

    @Override
    public String generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "OR" + uuid.toString();
        return idTemp;
    }
}