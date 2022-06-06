package Factory;

import java.util.UUID;

import Domain.Entity.Management;
import Domain.Entity.Product;
import Domain.Entity.User;
import Domain.Service.IGenerateID;

public class ManagementFactory implements IGenerateID {
    UserFactory userFactory = new UserFactory();
    ProductFactory productFactory = new ProductFactory();

    public Management createManagement(String managementID, String managementDesc,
            String userID, String userName, String userEmail, String userPassword, String street,
            String city,
            String zipCode,
            String userPhone,
            String productID, String productName, int productPrice, int productQuantity,
            String managementStatus) {
        if (managementID == null) {
            managementID = generateID();
        }
        User managementUser = userFactory.createUser(userID, userName, userEmail, userPassword, street, city, zipCode,
                userPhone);

        Product managementProduct = productFactory.createProduct(productID, productName, productPrice,
                productQuantity);

        return new Management(managementID, managementDesc, managementUser, managementProduct, managementStatus);
    }

    @Override
    public String generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "MG" + uuid.toString();
        return idTemp;
    }
}
