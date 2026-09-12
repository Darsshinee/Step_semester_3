class SleeperBusTicketAccount extends BusTicketAccount {
    SleeperBusTicketAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    double calculatePenalty(int minutesLate) {
        return super.calculatePenalty(minutesLate) + 100;
    }
}

public class BusTicketAccount {
    protected String bookingId;
    protected double ticketFare;

    static int processedCount;

    static {
        processedCount = 0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (ticketFare < 0) {
            throw new IllegalArgumentException("Invalid fare");
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0) {
            throw new IllegalArgumentException("Invalid delay");
        }

        if (minutesLate == 0) {
            return 0;
        }

        return ticketFare * 0.01 * minutesLate;
    }

    void processAccount(BusTicketAccount account,
                        double amount, int minutesLate) {
        double penalty = account.calculatePenalty(minutesLate);
        System.out.println(account.bookingId +
                " penalty: Rs " + penalty);
        processedCount++;
    }

    static void processBatch(BusTicketAccount[] accounts,
                              double[] amounts,
                              int[] minutesLateArray) {

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;
        double total = 0;

        int n = Math.min(accounts.length,
                Math.min(amounts.length, minutesLateArray.length));

        for (int i = 0; i < n; i++) {
            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            double penalty =
                    accounts[i].calculatePenalty(minutesLateArray[i]);

            if (accounts[i] instanceof SleeperBusTicketAccount) {
                sleeper++;
            } else {
                regular++;
            }

            total += penalty;
            processed++;
        }

        System.out.println(processed + " processed | " +
                nullSkipped + " null skipped | " +
                sleeper + " sleeper | " +
                regular + " regular | grand total penalties = Rs " +
                total);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new SleeperBusTicketAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] delays = {10, 5, 0};

        processBatch(accounts, amounts, delays);
    }
}