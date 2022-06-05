package Infrastructure.Repository;

import java.util.ArrayList;

import Domain.Entity.User;
import Domain.ValueObject.Address;
import Infrastructure.Interface.IUserRepository;

public class UserRepository implements IUserRepository {
    private IUserRepository shopediaDB;
    private ArrayList<User> users = null;
    private String query = null;
    private User user = null;

    private void resetQuery() {
        users = null;
        query = null;
        user = null;
    }

    @Override
    public boolean updateUser(String userID, String userName, String userEmail, String userPassword,
            Address userAddress, String userPhone) {
        resetQuery();
        return shopediaDB.updateUser(userID, userName, userEmail, userPassword, userAddress, userPhone);
    }

    @Override
    public boolean deleteUser(String userID) {
        resetQuery();
        return shopediaDB.deleteUser(userID);
    }

    @Override
    public void addUser(String userID, String userName, String userEmail, String userPassword, Address userAddress,
            String userPhone) {
        resetQuery();
        shopediaDB.addUser(userID, userName, userEmail, userPassword, userAddress, userPhone);
    }

    @Override
    public ArrayList<User> getAllUser() {
        if (users == null || query != null) {
            resetQuery();
            users = shopediaDB.getAllUser();
            if (users == null) {
                return new ArrayList<>();
            }
        }
        return users;
    }

    @Override
    public User getUser(String userID) {
        if (user == null || query != null) {
            resetQuery();
            user = shopediaDB.getUser(userID);
            if (user == null) {
                return null;
            }
        }
        return user;
    }

}
