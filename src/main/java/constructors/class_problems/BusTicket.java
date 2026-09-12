public class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        if (passengerName == null || destination == null ||
            passengerName.trim().isEmpty() || destination.trim().isEmpty() ||
            !passengerName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Invalid booking");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
    }

    public void markCheckedIn() {
        if (!checkedIn) {
            checkedIn = true;
            System.out.println("Checked in");
        } else {
            System.out.println("Already checked in");
        }
    }

    static void processBatch(String[][] rawBookings) {
        String[] accepted = new String[rawBookings.length];
        int valid = 0;
        int rejected = 0;
        int duplicate = 0;

        for (String[] booking : rawBookings) {
            try {
                BusTicket ticket = new BusTicket(booking[0], booking[1]);
                String key = ticket.passengerName + "|" + ticket.destination;

                boolean found = false;

                for (int i = 0; i < valid; i++) {
                    if (accepted[i].equals(key)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    duplicate++;
                } else {
                    accepted[valid++] = key;
                }
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicate);
    }

    public static void main(String[] args) {
        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        processBatch(bookings);
    }
}