package Factory;

import java.util.UUID;

import Domain.Entity.Company;
import Domain.Service.IGenerateID;
import Domain.ValueObject.Address;

public class CompanyFactory implements IGenerateID {
    AddressFactory addressFactory = new AddressFactory();

    public Company createCompany(String companyID, String companyName, String street, String city, String zipCode,
            String companyEmail) {
        if (companyID == null) {
            companyID = generateID();
        }

        Address companyAddress = addressFactory.createAddress(street, city, zipCode);
        return new Company(companyID, companyName, companyAddress, companyEmail);
    }

    @Override
    public String generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "CO" + uuid.toString();
        return idTemp;
    }
}
