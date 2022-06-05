package Domain.Entity;

import java.util.UUID;

import Domain.Service.IGenerateID;

public class Product implements IGenerateID {
    private String productID;
    private String productName;
    private int productPrice;
    private int productQuantity;

    public Product(String productID, String productName, int productPrice, int productQuantity) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        generateID();
    }

    public String getProductID() {
        return productID;
    }

    public void generateID() {
        UUID uuid = UUID.randomUUID();
        String idTemp = "PR" + uuid.toString();
        this.productID = idTemp;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

}