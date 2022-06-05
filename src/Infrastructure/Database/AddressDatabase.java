package Infrastructure.Database;

import Infrastructure.Connector.Connect;

public class AddressDatabase {
    private final Connect db = Connect.getInstance();
    private String query = null;

    public void addAddress(String ID, String street, String city, String zipCode) {
        query = String.format("INSERT INTO address(ID, street, city, zipcode) " + "VALUES(%s, %s, %s %s)", ID,
                street, city, zipCode);
        db.executeUpdate(query);
    }

    public void deleteAddress(String ID) {
        query = String.format("DELETE from address " + "WHERE ID = %s", ID);
        db.executeQuery(query);
    }

    public void editAddress(String ID, String street, String city, String zipCode) {
        query = String.format("UPDATE address " + "SET street = %s, city = %s, zipcode = %s WHERE ID = %s", street,
                city, zipCode, ID);
        db.executeUpdate(query);
    }
}
