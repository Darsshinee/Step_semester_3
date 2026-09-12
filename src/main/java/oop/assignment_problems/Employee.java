class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class Employee {
    private String empId;
    private String empName;
    private double salary;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }

    public static void main(String[] args) {
        Employee employee = new Employee("E1", "Ravi", 40000);

        ManagerEmployee manager =
                new ManagerEmployee("E2", "Asha", 70000, 8000);

        InternEmployee intern =
                new InternEmployee("E3", "Meera", 12000, 10000);

        System.out.println("Employee salary: Rs " + employee.getSalary());
        System.out.println("Manager effective salary: Rs "
                + manager.effectiveSalary());
        System.out.println("Intern effective salary: Rs "
                + intern.effectiveSalary());
    }
}