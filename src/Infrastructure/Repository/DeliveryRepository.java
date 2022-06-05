package Infrastructure.Repository;

import java.sql.Date;
import java.util.ArrayList;

import Domain.Entity.Company;
import Domain.Entity.Delivery;
import Domain.Entity.Order;
import Infrastructure.Interface.IDeliveryRepository;

public class DeliveryRepository implements IDeliveryRepository {
    private IDeliveryRepository shopediaDB;
    private ArrayList<Delivery> deliveries = null;
    private String query = null;
    private Delivery delivery = null;

    private void resetQuery() {
        deliveries = null;
        query = null;
        delivery = null;
    }

    @Override
    public boolean deleteDelivery(String deliveryID) {
        resetQuery();
        return shopediaDB.deleteDelivery(deliveryID);
    }

    @Override
    public void addDelivery(String deliveryID, Date deliveryDate, ArrayList<Order> deliveryOrder,
            String deliveryStatus,
            Company deliveryCompany) {
        resetQuery();
        shopediaDB.addDelivery(deliveryID, deliveryDate, deliveryOrder, deliveryStatus, deliveryCompany);
    }

    @Override
    public ArrayList<Delivery> getAllDelivery() {
        if (deliveries == null || query != null) {
            resetQuery();
            deliveries = shopediaDB.getAllDelivery();
            if (deliveries == null) {
                return new ArrayList<>();
            }
        }
        return deliveries;
    }

    @Override
    public Delivery getDelivery(String deliveryID) {
        if (delivery == null || query != null) {
            resetQuery();
            delivery = shopediaDB.getDelivery(deliveryID);
            if (delivery == null) {
                return null;
            }
        }
        return delivery;
    }

}
