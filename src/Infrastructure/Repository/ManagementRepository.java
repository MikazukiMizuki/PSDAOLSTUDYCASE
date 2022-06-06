package Infrastructure.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Application.Interface.IManagementRepository;
import Domain.Entity.Management;
import Domain.Entity.Product;
import Domain.Entity.User;
import Infrastructure.Connector.Connect;
import Infrastructure.Service.IExist;

public class ManagementRepository implements IManagementRepository, IExist {
    private final Connect db = Connect.getInstance();
    private String query = null;
    private ProductRepository pd = new ProductRepository();
    private UserRepository us = new UserRepository();

    @Override
    public boolean isNotExist(String id) {
        String query = String.format(
                "SELECT * FROM management " +
                        "WHERE managementID = %s",
                id);
        try (ResultSet result = db.executeQuery(query)) {
            return !result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean updateManagement(String managementID, String managementDesc, String managementStatus) {
        if (isNotExist(managementID)) {
            return false;
        }
        query = String.format(
                "UPDATE management "
                        + "SET managementDesc = %s, managementStatus = %s WHERE productID = %s",
                managementDesc, managementStatus, managementID);
        db.executeUpdate(query);
        return true;
    }

    @Override
    public boolean deleteManagement(String managementID) {
        if (isNotExist(managementID)) {
            return false;
        }
        query = String.format("DELETE FROM management" + "WHERE managementID = %s", managementID);
        db.executeUpdate(query);
        return true;
    }

    @Override
    public void addManagement(String managementID, String managementDesc, User managementUser,
            Product managementProduct, String managementStatus) {
        query = String.format(
                "INSERT INTO management(managementID, managementDesc, managementStatus "
                        + "VALUES(%s, %s, %s)",
                managementID, managementDesc, managementStatus);
        db.executeUpdate(query);

        query = String.format("INSERT INTO managementDetail(managementID, userID, productID) " + "VALUES(%s, %s, %s)",
                managementID, managementUser.getUserID(), managementProduct.getProductID());

    }

    private ArrayList<Management> getManagementWithQuery(String query) {
        ArrayList<Management> managements = new ArrayList<>();
        ResultSet rs = db.executeQuery(query);
        try {
            while (rs.next()) {
                String managementID = rs.getString("managementID");
                String managementDesc = rs.getString("managementDesc");
                String managementStatus = rs.getString("managementStatus");
                managements.add(new Management(managementID, managementDesc, null, null, managementStatus));
            }

            for (Management management : managements) {
                String userProductQuery = String.format(
                        "SELECT userID, productID FROM managementDetail WHERE managementID = %s",
                        management.getManagementID());
                ResultSet userProductResult = db.executeQuery(userProductQuery);
                String userID = userProductResult.getString("userID");
                String productID = userProductResult.getString("productID");
                User managementUser = us.getUser(userID);
                management.setManagementUser(managementUser);
                Product managementProduct = pd.getProduct(productID);
                management.setManagementProduct(managementProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Management> getAllManagement() {
        String query = String.format("SELECT * FROM management");
        return getManagementWithQuery(query);
    }

    @Override
    public Management getManagement(String managementID) {
        String query = String.format("SELECT * FROM management " + "WHERE managementID = %s", managementID);
        ArrayList<Management> managements = getManagementWithQuery(query);
        return managements.get(0);
    }

}