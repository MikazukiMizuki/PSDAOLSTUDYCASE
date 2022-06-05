package Infrastructure.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.Entity.User;
import Domain.ValueObject.Address;
import Infrastructure.Connector.Connect;
import Infrastructure.Interface.IUserRepository;

public class UserDatabase implements IUserRepository {
    private final Connect db = Connect.getInstance();
    private String query = null;
    private AddressDatabase ad = new AddressDatabase();

    private boolean isNotExist(String id) {
        String query = String.format(
                "SELECT * FROM user " +
                        "WHERE userID = %s",
                id);
        try (ResultSet result = db.executeQuery(query)) {
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateUser(String userID, String userName, String userEmail, String userPassword,
            Address userAddress, String userPhone) {
        if (isNotExist(userID)) {
            return false;
        }
        query = String.format(
                "UPDATE user "
                        + "SET userName = %s, userEmail = %s, userPassword = %s, userPhone = %s WHERE userID = %s",
                userName, userEmail, userPassword, userPhone, userID);
        db.executeUpdate(query);
        ad.editAddress(userID, userAddress.getStreet(), userAddress.getCity(), userAddress.getZipCode());
        return true;
    }

    @Override
    public boolean deleteUser(String userID) {
        if (isNotExist(userID)) {
            return false;
        }
        query = String.format("DELETE FROM user " + "WHERE userID = %s", userID);
        db.executeUpdate(query);
        ad.deleteAddress(userID);
        return true;
    }

    @Override
    public void addUser(String userID, String userName, String userEmail, String userPassword, Address userAddress,
            String userPhone) {
        query = String.format(
                "INSERT INTO user(userID, userName, userEmail, userPassword, userAddress, userPhone) "
                        + "VALUES(%s, %s, %s, %s, %s, %s, %s)",
                userID, userName, userEmail, userPassword, userAddress, userPhone);
        db.executeUpdate(query);
        ad.addAddress(userID, userAddress.getStreet(), userAddress.getCity(), userAddress.getZipCode());
    }

    private ArrayList<User> getUserWithQuery(String query) {
        ArrayList<User> users = new ArrayList<>();
        ResultSet rs = db.executeQuery(query);

        try {
            while (rs.next()) {
                String userID = rs.getString("userID");
                String userName = rs.getString("userName");
                String userEmail = rs.getString("userEmail");
                String userPassword = rs.getString("userPassword");
                String userPhone = rs.getString("userPhone");
                users.add(new User(userID, userName, userEmail, userPassword, null, userPhone));
            }
            for (User user : users) {
                String addressQuery = String.format("SELECT street, name, zipcode FROM address " + "WHERE ID = %s",
                        user.getUserID());
                ResultSet addressResult = db.executeQuery(addressQuery);
                String street = addressResult.getString("street");
                String city = addressResult.getString("city");
                String zipCode = addressResult.getString("zipcode");
                Address address = new Address(street, city, zipCode);
                user.setUserAddress(address);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUser() {
        String query = String.format("SELECT * FROM user");
        return getUserWithQuery(query);
    }

    @Override
    public User getUser(String userID) {
        String query = String.format("SELECT * FROM user " + "WHERE userID = %s", userID);
        ArrayList<User> users = getUserWithQuery(query);
        return users.get(0);
    }

}