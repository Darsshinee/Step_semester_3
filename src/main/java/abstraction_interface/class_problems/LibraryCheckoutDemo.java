
package abstraction_interface.class_problems;

abstract class LibraryItem {
    private static int count = 1000;
    private final String itemId;
    protected String title;

    LibraryItem(String title) {
        this.title = title;
        count++;
        itemId = "LIB-" + count;
    }

    public String getItemId() {
        return itemId;
    }

    public abstract int getLoanPeriodDays();
}

interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

class Textbook extends LibraryItem implements Renewable, Reservable {
    Textbook(String title) {
        super(title);
    }

    public int getLoanPeriodDays() {
        return 14;
    }

    public String renew() {
        return title + " renewed";
    }

    public String reserve() {
        return title + " reserved";
    }
}

class Magazine extends LibraryItem implements Renewable {
    Magazine(String title) {
        super(title);
    }

    public int getLoanPeriodDays() {
        return 7;
    }

    public String renew() {
        return title + " renewed";
    }
}

class DigitalPass implements Renewable {
    private String resourceName;

    DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    public String renew() {
        return resourceName + " renewed";
    }
}

public class LibraryCheckoutDemo {
    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items) {
            System.out.println(item.title + ": "
                    + item.getLoanPeriodDays() + " days");
        }
    }

    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) {
            Reservable r = (Reservable) o;
            return r.reserve();
        }
        return "Reservation not supported";
    }

    public static void main(String[] args) {
        Textbook t = new Textbook("Java Fundamentals");
        Magazine m = new Magazine("Tech Monthly");
        DigitalPass d = new DigitalPass("E-Journal Access");

        processCheckouts(new LibraryItem[]{t, m});

        System.out.println(t.renew());
        System.out.println(t.reserve());
        System.out.println(reserveIfSupported(m));
        System.out.println(reserveIfSupported(d));
        System.out.println(reserveIfSupported(t));
    }
}
