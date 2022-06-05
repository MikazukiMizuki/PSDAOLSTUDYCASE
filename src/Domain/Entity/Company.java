package Domain.Entity;

import java.util.UUID;

import Domain.Service.IGenerateID;

public class Company implements IGenerateID {
    private String companyID;
    private String companyName;
    private String companyAddress;
    private String companyEmail;

    public Company(String companyID, String companyName, String companyAddress, String companyEmail) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyEmail = companyEmail;
        generateID();
    }

    public String getCompanyID() {
        return companyID;
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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

}
