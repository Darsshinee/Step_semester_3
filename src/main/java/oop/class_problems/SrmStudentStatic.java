class SrmStudentStatic {

    static String university = "SRM";
    static int admissionCount = 0;

    String name;
    String regNo;
    int attendance;

    SrmStudentStatic(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }

    public static void main(String[] args) {
        SrmStudentStatic ravi = new SrmStudentStatic("Ravi", 82);
        SrmStudentStatic meera = new SrmStudentStatic("Meera", 74);

        ravi.printIdCard();
        meera.printIdCard();

        printTotalAdmissions();
    }
}