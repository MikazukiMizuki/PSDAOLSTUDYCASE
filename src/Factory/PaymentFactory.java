package Factory;

import Domain.ValueObject.Payment;

public class PaymentFactory {
    public Payment createPayment(String paymentName, String paymentType, int paymentPrice) {
        return new Payment(paymentName, paymentType, paymentPrice);
    }
}
