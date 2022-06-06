package Factory;

import java.util.UUID;

import Domain.Entity.Product;
import Domain.Service.IGenerateID;

public class ProductFactory implements IGenerateID {
    public Product createProduct(String productID, String productName, int productPrice, int productQuantity) {
        if (productID == null) {
            productID = generateID();
        }

        return new Product(productID, productName, productPrice, productQuantity);
    }

    @Override
    public String generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "PR" + uuid.toString();
        return idTemp;
    }
}
