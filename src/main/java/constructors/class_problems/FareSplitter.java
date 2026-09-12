public class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0) {
            throw new IllegalArgumentException("Invalid fare or passenger count");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0, 2);
    }

    double[] fareBreakdown() {
        double[] result = new double[passengerCount];

        if (passengerCount == 0) {
            return result;
        }

        double share = Math.floor((totalFare / passengerCount) * 100) / 100;
        double used = 0;

        for (int i = 0; i < passengerCount - 1; i++) {
            result[i] = share;
            used += share;
        }

        result[passengerCount - 1] =
                Math.round((totalFare - used) * 100) / 100.0;

        return result;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        FareSplitter f = new FareSplitter("TRIP001", 100000, 3);

        for (double amount : f.fareBreakdown()) {
            System.out.println(amount);
        }
    }
}