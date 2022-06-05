package Domain.Entity;

import java.sql.Date;
import java.util.ArrayList;

import Domain.ValueObject.Address;
import Domain.ValueObject.Payment;

public class Order{
    private String orderID;
    private Date orderDate;
    private int orderPrice;
    private ArrayList<Product> orderProduct;
    private Address orderAddress;
    private String orderStatus;
    private Payment orderPayment;

    public Order(String orderID, Date orderDate, int orderPrice, ArrayList<Product> orderProduct, Address orderAddress,
            String orderStatus, Payment orderPayment) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.orderProduct = orderProduct;
        this.orderAddress = orderAddress;
        this.orderStatus = orderStatus;
        this.orderPayment = orderPayment;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public ArrayList<Product> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(ArrayList<Product> orderProduct) {
        this.orderProduct = orderProduct;
    }

    public Address getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(Address orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Payment getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(Payment orderPayment) {
        this.orderPayment = orderPayment;
    }

}