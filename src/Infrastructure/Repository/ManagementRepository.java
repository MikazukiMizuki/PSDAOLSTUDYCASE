package Infrastructure.Repository;

import java.util.ArrayList;

import Domain.Entity.Management;
import Domain.Entity.Product;
import Domain.Entity.User;
import Infrastructure.Interface.IManagementRepository;

public class ManagementRepository implements IManagementRepository {
    private IManagementRepository shopediaDB;
    private ArrayList<Management> managements = null;
    private String query = null;
    private Management management = null;

    private void resetQuery() {
        managements = null;
        query = null;
        management = null;
    }

    @Override
    public boolean updateManagement(String managementID, String managementDesc, String managementStatus) {
        resetQuery();
        return shopediaDB.updateManagement(managementID, managementDesc,
                managementStatus);
    }

    @Override
    public boolean deleteManagement(String managementID) {
        resetQuery();
        return shopediaDB.deleteManagement(managementID);
    }

    @Override
    public void addManagement(String managementID, String managementDesc, User managementUser,
            Product managementProduct, String managementStatus) {
        resetQuery();
        shopediaDB.addManagement(managementID, managementDesc, managementUser, managementProduct, managementStatus);

    }

    @Override
    public ArrayList<Management> getAllManagement() {
        if (managements == null || query != null) {
            resetQuery();
            managements = shopediaDB.getAllManagement();
            if (managements == null) {
                return new ArrayList<>();
            }
        }
        return managements;
    }

    @Override
    public Management getManagement(String managementID) {
        if (management == null || query != null) {
            resetQuery();
            management = shopediaDB.getManagement(managementID);
            if (management == null) {
                return null;
            }
        }
        return management;
    }
}
