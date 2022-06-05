package Infrastructure.Interface;

import java.util.ArrayList;

import Domain.Entity.User;
import Domain.ValueObject.Address;

public interface IUserRepository {
    public boolean updateUser(String userID, String userName, String userEmail, String userPassword,
            Address userAddress, String userPhone);

    public boolean deleteUser(String userID);

    public void addUser(String userID, String userName, String userEmail, String userPassword,
            Address userAddress, String userPhone);

    public ArrayList<User> getAllUser();

    public User getUser(String userID);

}
