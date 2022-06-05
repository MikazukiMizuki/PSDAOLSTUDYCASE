package Factory;

import java.util.UUID;

import Domain.Entity.Management;
import Domain.Entity.Product;
import Domain.Entity.User;
import Domain.Service.IGenerateID;

public class ManagementFactory implements IGenerateID {
    public Management createManagement(String managementDesc,
            User managementUser,
            Product managementProduct,
            String managementStatus) {
        String managementID = generateID();
        return new Management(managementID, managementDesc, managementUser, managementProduct, managementStatus);
    }

    @Override
    public String generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "MG" + uuid.toString();
        return idTemp;
    }
}
