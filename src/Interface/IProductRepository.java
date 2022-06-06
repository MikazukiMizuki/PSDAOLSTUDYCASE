package Interface;

import java.util.ArrayList;

import Domain.Entity.Product;

public interface IProductRepository {
    public boolean updateProduct(String productID, String productName, int productPrice, int productQuantity);

    public boolean deleteProduct(String productID);

    public void addProduct(String productID, String productName, int productPrice, int productQuantity);

    public ArrayList<Product> getAllProduct();

    public Product getProduct(String productID);
}
