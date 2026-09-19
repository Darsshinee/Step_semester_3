class EventTicket {
    protected String attendeeId;
    protected double basePrice;
    protected double paidAmount;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid attendee ID");
        }

        if (basePrice <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
    }

    void pay(double amount) {
        if (amount > 0) {
            paidAmount += amount;
        }
    }

    double getBalanceDue() {
        return basePrice - paidAmount;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}

public class TicketRegistration {

    static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        String[] ids = {"STU1", "ST1", "STU2", " ", "STU3"};

        System.out.println(registerBatch(ids, 500));
    }
}