package Domain.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import Domain.Service.IGenerateID;

public class Delivery implements IGenerateID {
    private String deliveryID;
    private Date deliveryDate;
    private ArrayList<Order> deliveryOrder;
    private String deliveryStatus;
    private Company deliveryCompany;

    public Delivery(String deliveryID, Date deliveryDate, ArrayList<Order> deliveryOrder, String deliveryStatus,
            Company deliveryCompany) {
        this.deliveryID = deliveryID;
        this.deliveryDate = deliveryDate;
        this.deliveryOrder = deliveryOrder;
        this.deliveryStatus = deliveryStatus;
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public void generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "DE" + uuid.toString();
        this.deliveryID = idTemp;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ArrayList<Order> getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(ArrayList<Order> deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Company getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(Company deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

}
