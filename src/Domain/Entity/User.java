package Domain.Entity;

import java.util.UUID;

import Domain.Service.IGenerateID;
import Domain.ValueObject.Address;

public class User implements IGenerateID {
    private String userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private Address userAddress;
    private String userPhone;

    public User(String userID, String userName, String userEmail, String userPassword, Address userAddress,
            String userPhone) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void generateID() {
        if (userID != null) {
            return;
        }
        UUID uuid = UUID.randomUUID();
        String idTemp = "US" + uuid.toString();
        this.userID = idTemp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

}