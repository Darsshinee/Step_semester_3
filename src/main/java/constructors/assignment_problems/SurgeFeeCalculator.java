public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue,
                                          int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (delayMinutes == 0) {
            return 0;
        }

        int first = Math.min(delayMinutes, 5);
        int second = Math.min(Math.max(delayMinutes - 5, 0), 10);
        int third = Math.max(delayMinutes - 15, 0);

        double fee =
                orderValue * 0.005 * first +
                orderValue * 0.01 * second +
                orderValue * 0.02 * third;

        double floor =
                orderValue * minimumSurgePercent / 100;

        return Math.max(fee, floor);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator s =
                new SurgeFeeCalculator(1);

        System.out.println(s.calculateSurgeFee(500, 0));
        System.out.println(s.calculateSurgeFee(500, 1));
        System.out.println(s.calculateSurgeFee(500, 16));
    }
}