package Infrastructure.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Domain.Entity.Product;
import Infrastructure.Connector.Connect;
import Infrastructure.Interface.IProductRepository;
import Infrastructure.Service.IExist;

public class ProductDatabase implements IProductRepository, IExist {
    private final Connect db = Connect.getInstance();
    private String query = null;

    public boolean isNotExist(String id) {
        query = String.format(
                "SELECT * FROM product " +
                        "WHERE productID = %s",
                id);
        try (ResultSet result = db.executeQuery(query)) {
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateProduct(String productID, String productName, int productPrice, int productQuantity) {
        if (isNotExist(productID)) {
            return false;
        }
        query = String.format(
                "UPDATE product "
                        + "SET productName = %s, productPrice = %d, productQuantity = %d WHERE productID = %s",
                productName, productPrice, productQuantity, productID);
        db.executeUpdate(query);
        return true;
    }

    public boolean reduceQuantity(String productID, int productQuantity) {
        if (isNotExist(productID)) {
            return false;
        }
        Product product = getProduct(productID);

        query = String.format(
                "UPDATE product "
                        + "SET productQuantity = %d WHERE productID = %s",
                product.getProductQuantity() - productQuantity, productID);
        db.executeUpdate(query);
        return true;
    }

    @Override
    public boolean deleteProduct(String productID) {
        if (isNotExist(productID)) {
            return false;
        }
        query = String.format("DELETE FROM product " + "WHERE productID = %s", productID);
        db.executeUpdate(query);
        return true;
    }

    @Override
    public void addProduct(String productID, String productName, int productPrice, int productQuantity) {
        query = String.format(
                "INSERT INTO product(productID, productName, productPrice, productQuantity) "
                        + "VALUES(%s, %d, %d, %d)",
                productID, productName, productPrice, productQuantity);
        db.executeUpdate(query);
    }

    private ArrayList<Product> getProductWithQuery(String query) {
        ArrayList<Product> products = new ArrayList<>();
        ResultSet rs = db.executeQuery(query);
        try {
            while (rs.next()) {
                String productID = rs.getString("productID");
                String productName = rs.getString("productName");
                int productPrice = rs.getInt("productPrice");
                int productQuantity = rs.getInt("productQuantity");
                products.add(new Product(productID, productName, productPrice, productQuantity));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAllProduct() {
        String query = String.format("SELECT * FROM product");
        return getProductWithQuery(query);
    }

    @Override
    public Product getProduct(String productID) {
        String query = String.format("SELECT * FROM product " + "WHERE productID = %s", productID);
        ArrayList<Product> products = getProductWithQuery(query);
        return products.get(0);
    }
}
