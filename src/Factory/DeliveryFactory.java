package Factory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import Domain.Entity.Company;
import Domain.Entity.Delivery;
import Domain.Entity.Order;
import Domain.Service.IGenerateID;

public class DeliveryFactory implements IGenerateID {
    public Delivery createDelivery(Date deliveryDate, ArrayList<Order> deliveryOrder, String deliveryStatus,
            Company deliveryCompany) {
        String deliveryID = generateID();
        return new Delivery(deliveryID, deliveryDate, deliveryOrder, deliveryStatus, deliveryCompany);
    }

    @Override
    public String generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "DE" + uuid.toString();
        return idTemp;
    }
}
