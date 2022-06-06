package Application.Interfaces;

import java.util.ArrayList;

import Domain.Entity.Management;
import Domain.Entity.Product;
import Domain.Entity.User;

public interface IManagementRepository {
        public boolean updateManagement(String managementID, String managementDesc, String managementStatus);

        public boolean deleteManagement(String managementID);

        public void addManagement(String managementID, String managementDesc,
                        User managementUser,
                        Product managementProduct,
                        String managementStatus);

        public ArrayList<Management> getAllManagement();

        public Management getManagement(String managementID);
}
