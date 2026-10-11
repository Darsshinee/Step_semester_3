
package abstraction_interface.assignment_problems;

abstract class HomeDevice {
    private static int count = 1000;
    private final String serialNumber;

    HomeDevice() {
        count++;
        serialNumber = "HD-" + count;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public abstract String activate();
}

interface RemoteControllable {
    String connect(String appId);
}

interface EnergyTrackable {
    double getConsumptionWatts();
}

class WashingMachine extends HomeDevice
        implements RemoteControllable, EnergyTrackable {
    private double consumptionWatts;

    WashingMachine(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    public String activate() {
        return "Washing machine " + getSerialNumber()
                + " started a cycle";
    }

    public String connect(String appId) {
        return getSerialNumber() + " connected to " + appId;
    }

    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class Refrigerator extends HomeDevice implements EnergyTrackable {
    private double consumptionWatts;

    Refrigerator(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    public String activate() {
        return "Refrigerator " + getSerialNumber() + " started";
    }

    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class MobileApp implements RemoteControllable {
    private String appName;

    MobileApp(String appName) {
        this.appName = appName;
    }

    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}

public class HomeControlDemo {
    static void connectAll(RemoteControllable[] items, String appId) {
        for (RemoteControllable item : items) {
            System.out.println(item.connect(appId));
        }
    }

    static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            EnergyTrackable e = (EnergyTrackable) d;
            return e.getConsumptionWatts();
        }
        return 0.0;
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(500.0);
        Refrigerator fridge = new Refrigerator(150.0);
        MobileApp app = new MobileApp("HomeConnect App");

        System.out.println(wm.activate());
        System.out.println(wm.connect("HomeConnect"));
        System.out.println(getConsumptionIfTrackable(fridge));
        System.out.println(app.connect("HomeConnect"));

        HomeDevice ref = wm; // Upcasting
        System.out.println(getConsumptionIfTrackable(ref));

        connectAll(
                new RemoteControllable[]{wm, app},
                "HomeConnect"
        );
    }
}
