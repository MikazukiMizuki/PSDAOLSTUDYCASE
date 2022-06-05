package Infrastructure.Database;

import Infrastructure.Connector.Connect;

public class PaymentDatabase {
    private final Connect db = Connect.getInstance();
    private String query = null;

    public void addPayment(String ID, String paymentName, String paymentType, int paymentPrice) {
        query = String.format("INSERT INTO payment(ID, street, city, zipcode) " + "VALUES(%s, %s, %s %d)", ID,
                paymentName, paymentType, paymentPrice);
        db.executeUpdate(query);
    }

    public void deletePayment(String ID) {
        query = String.format("DELETE from payment " + "WHERE ID = %s", ID);
        db.executeQuery(query);
    }

    public void editPayment(String ID, String paymentName, String paymentType, int paymentPrice) {
        query = String.format(
                "UPDATE payment " + "SET paymentName = %s, paymentType = %s, paymentPrice = %d WHERE ID = %s",
                paymentName,
                paymentType, paymentPrice, ID);
        db.executeUpdate(query);
    }
}
