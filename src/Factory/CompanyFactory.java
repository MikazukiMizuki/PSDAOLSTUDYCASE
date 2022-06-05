package Factory;

import java.util.UUID;

import Domain.Entity.Company;
import Domain.Service.IGenerateID;
import Domain.ValueObject.Address;

public class CompanyFactory implements IGenerateID {
    public Company createCompany(String companyName, Address companyAddress, String companyEmail) {
        String companyID = generateID();
        return new Company(companyID, companyName, companyAddress, companyEmail);
    }

    @Override
    public String generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "CO" + uuid.toString();
        return idTemp;
    }
}
