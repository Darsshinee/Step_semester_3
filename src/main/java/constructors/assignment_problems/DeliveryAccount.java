class PremiumDeliveryAccount extends DeliveryAccount {
    PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    final double calculateSurgeFee(int delayMinutes) {
        return super.calculateSurgeFee(delayMinutes) + 50;
    }
}

public class DeliveryAccount {
    protected String studentId;
    protected double orderValue;

    static int accountCount;

    static {
        accountCount = 0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("Invalid order value");
        }

        this.studentId = studentId;
        this.orderValue = orderValue;
        accountCount++;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Invalid delay");
        }

        if (delayMinutes == 0) {
            return 0;
        }

        return orderValue * 0.01 * delayMinutes;
    }

    void processAccount(DeliveryAccount account,
                        double amount, int delayMinutes) {
        System.out.println(account.studentId +
                " fee: Rs " +
                account.calculateSurgeFee(delayMinutes));
    }

    static void processBatch(DeliveryAccount[] accounts,
                             double[] amounts,
                             int[] delayMinutesArray) {

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;
        double total = 0;

        int n = Math.min(accounts.length,
                Math.min(amounts.length, delayMinutesArray.length));

        for (int i = 0; i < n; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double fee =
                    accounts[i].calculateSurgeFee(delayMinutesArray[i]);

            if (accounts[i] instanceof PremiumDeliveryAccount) {
                premium++;
            } else {
                regular++;
            }

            total += fee;
            processed++;
        }

        System.out.println(processed + " processed | " +
                nullSkipped + " null skipped | " +
                premium + " premium | " +
                regular + " regular | grand total surge fees = Rs " +
                total);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumDeliveryAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delays = {10, 5, 0};

        processBatch(accounts, amounts, delays);
    }
}