
package abstraction_interface.class_problems;

abstract class PaymentMethod {
    private static int count = 1000;
    private final String transactionId;

    PaymentMethod() {
        count++;
        transactionId = "TXN-" + count;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public abstract String processPayment(double amount);

    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }
}

class CreditCardPayment extends PaymentMethod {
    private String cardNumberLastFour;

    CreditCardPayment(String cardNumberLastFour) {
        this.cardNumberLastFour = cardNumberLastFour;
    }

    public String processPayment(double amount) {
        return "Charged $" + amount + " to card ending "
                + cardNumberLastFour + " - Txn " + getTransactionId();
    }
}

class CashPayment extends PaymentMethod {
    public String processPayment(double amount) {
        return "Received $" + amount + " in cash - Txn "
                + getTransactionId();
    }
}

public class PaymentMethodDemo {
    static void printConfirmation(PaymentMethod payment, double amount) {
        System.out.println(payment.processPayment(amount));
    }

    public static void main(String[] args) {
        CreditCardPayment card = new CreditCardPayment("4471");
        CashPayment cash = new CashPayment();

        System.out.println(card.processPayment(250.0));
        System.out.println(card.processPayment(250.0, "Birthday gift"));
        printConfirmation(cash, 40.0);

        PaymentMethod ref = card; // Upcasting
        printConfirmation(ref, 250.0);
    }
}
