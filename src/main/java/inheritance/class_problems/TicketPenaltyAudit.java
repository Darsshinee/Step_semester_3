import java.util.Arrays;

class EventTicket {
    protected double basePrice;
    protected double paidAmount;

    private double[] lateFeeHistory = new double[10];
    private int feeCount = 0;

    public EventTicket(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }

        this.basePrice = basePrice;
    }

    void pay(double amount) {
        if (amount > 0) {
            paidAmount += amount;
        }
    }

    protected void applyLateFee(double amount) {
        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }
    }

    double getBalanceDue() {
        return basePrice - paidAmount + getTotalLateFee();
    }

    private double getTotalLateFee() {
        double total = 0;

        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
    }

    double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class TicketPenaltyAudit {

    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);

        w.pay(1200);
        w.applyLateFee(100);

        System.out.println(w.getBalanceDue());

        double[] history = w.getLateFeeHistory();

        System.out.println(Arrays.toString(history));

        history[0] = 999;

        System.out.println(Arrays.toString(w.getLateFeeHistory()));
    }
}