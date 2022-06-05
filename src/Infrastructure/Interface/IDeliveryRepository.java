package Infrastructure.Interface;

import java.sql.Date;
import java.util.ArrayList;

import Domain.Entity.Company;
import Domain.Entity.Delivery;
import Domain.Entity.Order;

public interface IDeliveryRepository {
    public boolean deleteDelivery(String deliveryID);

    public void addDelivery(String deliveryID, Date deliveryDate, ArrayList<Order> deliveryOrder, String deliveryStatus,
            Company deliveryCompany);

    public ArrayList<Delivery> getAllDelivery();

    public Delivery getDelivery(String deliveryID);
}
