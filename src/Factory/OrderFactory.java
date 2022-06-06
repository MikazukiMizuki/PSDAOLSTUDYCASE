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
    AddressFactory addressFactory = new AddressFactory();
    PaymentFactory paymentFactory = new PaymentFactory();

    public Order createOrder(String orderID, Date orderDate, int orderPrice, ArrayList<Product> orderProduct,
            String street,
            String city,
            String zipCode,
            String orderStatus, String paymentName, String paymentType, int paymentPrice, User orderUser) {
        if (orderID == null) {
            orderID = generateID();
        }

        Address orderAddress = new Address(street, city, zipCode);
        Payment orderPayment = new Payment(paymentName, paymentType, paymentPrice);
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