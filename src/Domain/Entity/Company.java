package Domain.Entity;

import java.util.UUID;

import Domain.Service.IGenerateID;
import Domain.ValueObject.Address;

public class Company implements IGenerateID {
    private String companyID;
    private String companyName;
    private Address companyAddress;
    private String companyEmail;

    public Company(String companyID, String companyName, Address companyAddress, String companyEmail) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyEmail = companyEmail;
    }

    public String getCompanyID() {
        return companyID;
    }
    
    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public void generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "CO" + uuid.toString();
        this.companyID = idTemp;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Address getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Address companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

}
