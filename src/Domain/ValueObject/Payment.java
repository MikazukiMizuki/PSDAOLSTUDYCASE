package Domain.ValueObject;

public class Payment {
    private final String paymentName;
    private final String paymentType;
    private final int paymentPrice;

    public Payment(String paymentName, String paymentType, int paymentPrice) {
        this.paymentName = paymentName;
        this.paymentType = paymentType;
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public int getPaymentPrice() {
        return paymentPrice;
    }

}
