package Factory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import Domain.Entity.Company;
import Domain.Entity.Delivery;
import Domain.Entity.Order;
import Domain.Service.IGenerateID;

public class DeliveryFactory implements IGenerateID {
    CompanyFactory companyFactory = new CompanyFactory();

    public Delivery createDelivery(String deliveryID, Date deliveryDate, ArrayList<Order> deliveryOrder,
            String deliveryStatus,
            String companyID, String companyName, String street, String city, String zipCode,
            String companyEmail) {
        if (deliveryID == null) {
            deliveryID = generateID();
        }

        Company deliveryCompany = companyFactory.createCompany(companyID, companyName, street, city, zipCode,
                companyEmail);
        return new Delivery(deliveryID, deliveryDate, deliveryOrder, deliveryStatus, deliveryCompany);
    }

    @Override
    public String generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "DE" + uuid.toString();
        return idTemp;
    }
}
