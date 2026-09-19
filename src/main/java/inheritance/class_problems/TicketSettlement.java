class EventTicket {
    private static int ticketsIssued = 0;

    private final String ticketId;
    private double basePrice;
    private double paidAmount;

    public EventTicket(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }

        ticketsIssued++;
        ticketId = "TCK-" + (1000 + ticketsIssued);

        this.basePrice = basePrice;
    }

    void pay(double amount) {
        if (amount > 0) {
            paidAmount += amount;
        }
    }

    void pay(double amount, String mode) {
        System.out.println("Payment Mode: " + mode);
        pay(amount);
    }

    double getBalanceDue() {
        return basePrice - paidAmount;
    }

    static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        return code.charAt(0) == 'F'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isDigit(code.charAt(3))
                && Character.isUpperCase(code.charAt(4));
    }

    static int getTicketsIssued() {
        return ticketsIssued;
    }

    String getTicketId() {
        return ticketId;
    }
}

class GroupTicket extends EventTicket {
    private int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }
}

public class TicketSettlement {

    static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (EventTicket ticket : tickets) {
            if (ticket == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }

    public static void main(String[] args) {
        EventTicket t1 = new EventTicket(500);

        System.out.println(t1.getTicketId());
        System.out.println(EventTicket.getTicketsIssued());

        System.out.println(EventTicket.isValidPromoCode("F123A"));
        System.out.println(EventTicket.isValidPromoCode("F12A"));
        System.out.println(EventTicket.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(t1.getBalanceDue());

        EventTicket[] tickets = {
            new GroupTicket(2000, 5),
            null,
            new EventTicket(500)
        };

        System.out.println(TicketSettlement.processNightlySettlement(tickets));
    }
}