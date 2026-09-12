public class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        totalRecords++;
    }

    String fullProfile() {
        String slotNumber =
                slot == null ? "no parking assigned" : slot.slotNo;

        double pay;

        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        return name + " | Pay: Rs " + pay
                + " | Slot: " + slotNumber;
    }

    public static void main(String[] args) {

        Employee manager =
                new ManagerEmployee("E1", "Divya", 70000, 8000);

        Employee normal =
                new Employee("E2", "Karan", 40000);

        Employee intern =
                new InternEmployee("E3", "Meera", 12000, 10000);

        CompanyEmployeeRecord divya =
                new CompanyEmployeeRecord("Divya", "E1", manager);

        CompanyEmployeeRecord karan =
                new CompanyEmployeeRecord("Karan", "E2", normal);

        CompanyEmployeeRecord meera =
                new CompanyEmployeeRecord("Meera", "E3", intern);

        ParkingSlot slot1 = new ParkingSlot("A1", 1, 0);
        ParkingSlot slot2 = new ParkingSlot("A2", 1, 0);

        slot1.allot("E1");
        divya.slot = slot1;

        slot2.allot("E2");
        karan.slot = slot2;

        System.out.println(divya.fullProfile());
        System.out.println(karan.fullProfile());
        System.out.println(meera.fullProfile());

        System.out.println("Total records: " + totalRecords);
    }
}