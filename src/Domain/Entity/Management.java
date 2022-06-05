package Domain.Entity;

public class Management {
    private String managementID;
    private String managementDesc;
    private User managementUser;
    private Product managementProduct;
    private String managementStatus;

    public Management(String managementID, String managementDesc,
            User managementUser,
            Product managementProduct,
            String managementStatus) {
        this.managementID = managementID;
        this.managementDesc = managementDesc;
        this.managementUser = managementUser;
        this.managementProduct = managementProduct;
        this.managementStatus = managementStatus;
    }

    public String getManagementID() {
        return managementID;
    }

    public void setManagementID(String managementID) {
        this.managementID = managementID;
    }

    public String getManagementDesc() {
        return managementDesc;
    }

    public void setManagementDesc(String managementDesc) {
        this.managementDesc = managementDesc;
    }

    public User getManagementUser() {
        return managementUser;
    }

    public void setManagementUser(User managementUser) {
        this.managementUser = managementUser;
    }

    public Product getManagementProduct() {
        return managementProduct;
    }

    public void setManagementProduct(Product managementProduct) {
        this.managementProduct = managementProduct;
    }

    public String isManagementStatus() {
        return managementStatus;
    }

    public void setManagementStatus(String managementStatus) {
        this.managementStatus = managementStatus;
    }

}
