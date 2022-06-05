package Factory;

import java.util.UUID;

import Domain.Entity.User;
import Domain.Service.IGenerateID;
import Domain.ValueObject.Address;

public class UserFactory implements IGenerateID {
    AddressFactory addressFactory = new AddressFactory();

    public User createUser(String userName, String userEmail, String userPassword, String street, String city,
            String zipCode,
            String userPhone) {
        String userID = generateID();
        Address userAddress = addressFactory.createAddress(street, city, zipCode);
        return new User(userID, userName, userEmail, userPassword, userAddress, userPhone);
    }

    @Override
    public String generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "US" + uuid.toString();
        return idTemp;
    }
}
