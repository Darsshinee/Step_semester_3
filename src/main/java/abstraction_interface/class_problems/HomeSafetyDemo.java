
package abstraction_interface.class_problems;

interface Alertable {
    String sendAlert(String message);
}

class SecuritySensor {
    protected String zoneName;

    SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }
}

class MotionSensor extends SecuritySensor implements Alertable {
    MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {
        return "[" + zoneName + "] " + message;
    }
}

class DualZoneMotionSensor extends MotionSensor {
    private String secondZoneName;

    DualZoneMotionSensor(String zoneName, String secondZoneName) {
        super(zoneName);
        this.secondZoneName = secondZoneName;
    }

    @Override
    public String sendAlert(String message) {
        return super.sendAlert(message)
                + " [also covering " + secondZoneName + "]";
    }
}

class SmokeDetector implements Alertable {
    private String deviceId;

    SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    public String sendAlert(String message) {
        return "[" + deviceId + "] " + message;
    }
}

public class HomeSafetyDemo {
    static void broadcastAll(Alertable[] devices, String message) {
        for (Alertable device : devices) {
            System.out.println(device.sendAlert(message));
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor) {
            MotionSensor m = (MotionSensor) a;
            return m.getZoneName();
        }
        return "Not a motion sensor";
    }

    public static void main(String[] args) {
        MotionSensor m = new MotionSensor("Living Room");
        DualZoneMotionSensor d =
                new DualZoneMotionSensor("Hallway", "Stairwell");
        SmokeDetector s = new SmokeDetector("SD-01");

        broadcastAll(new Alertable[]{m, d, s}, "Motion detected");
        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));
    }
}
