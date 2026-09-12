public class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || dishName == null ||
            studentName.trim().isEmpty() ||
            dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid order");
        }

        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
    }

    void markDelivered() {
        if (!delivered) {
            delivered = true;
            System.out.println("Order delivered");
        } else {
            System.out.println("Order already delivered");
        }
    }

    static void processBatch(String[][] orders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : orders) {
            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(orders);
    }
}