
package abstraction_interface.assignment_problems;

interface Exportable {
    String exportData();
}

class ExportCounter {
    private static int totalExports = 0;

    static void increase() {
        totalExports++;
    }

    static int getTotalExports() {
        return totalExports;
    }
}

class ReportGenerator implements Exportable {
    private String reportName;

    ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    public String exportData() {
        ExportCounter.increase();
        return "Exported report: " + reportName;
    }
}

class UserProfile implements Exportable {
    private String username;

    UserProfile(String username) {
        this.username = username;
    }

    public String exportData() {
        ExportCounter.increase();
        return "Exported profile: " + username;
    }
}

public class DataExportDemo {
    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    static int getTotalExports() {
        return ExportCounter.getTotalExports();
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        System.out.println(r.exportData());
        System.out.println(u.exportData());

        Exportable ref = r; // Upcasting
        exportAll(new Exportable[]{ref, u});

        System.out.println("Total exports: " + getTotalExports());
    }
}
