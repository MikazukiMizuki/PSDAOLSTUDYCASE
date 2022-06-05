package Infrastructure.Repository;

import java.util.ArrayList;

import Domain.Entity.Product;
import Infrastructure.Interface.IProductRepository;

public class ProductRepository implements IProductRepository {
    private IProductRepository shopediaDB;
    private ArrayList<Product> products = null;
    private String query = null;
    private Product product = null;

    private void resetQuery() {
        products = null;
        query = null;
        product = null;
    }

    @Override
    public boolean updateProduct(String productID, String productName, int productPrice, int productQuantity) {
        resetQuery();
        return shopediaDB.updateProduct(productID, productName, productPrice, productQuantity);
    }

    @Override
    public boolean deleteProduct(String productID) {
        resetQuery();
        return shopediaDB.deleteProduct(productID);
    }

    @Override
    public void addProduct(String productID, String productName, int productPrice, int productQuantity) {
        resetQuery();
        shopediaDB.addProduct(productID, productName, productPrice, productQuantity);
    }

    @Override
    public ArrayList<Product> getAllProduct() {
        if (products == null || query != null) {
            resetQuery();
            products = shopediaDB.getAllProduct();
            if (products == null) {
                return new ArrayList<>();
            }
        }
        return products;
    }

    @Override
    public Product getProduct(String productID) {
        if (product == null || query != null) {
            resetQuery();
            product = shopediaDB.getProduct(productID);
            if (product == null) {
                return null;
            }
        }
        return product;
    }

}
