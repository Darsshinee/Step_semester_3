class FeeHostelStudent {
    String name;
    String regNo;
    FeeAccountBase feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    FeeHostelStudent(String name, String regNo, FeeAccountBase feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        totalStudents++;
    }

    String fullStatus() {
        String roomNumber = room == null ? "unallotted" : room.roomNo;

        return name + " | Due: Rs " + feeAccount.getDue()
                + " | Room: " + roomNumber;
    }
}

public class FeeHostelSystem {

    public static void main(String[] args) {
        HostelRoom room1 = new HostelRoom("C-214", 1, 0);
        HostelRoom room2 = new HostelRoom("C-507", 1, 0);

        FeeAccountBase fee1 = new FeeAccountBase("R1", 200000);
        FeeAccountBase fee2 = new FeeAccountBase("R2", 200000);
        FeeAccountBase fee3 = new FeeAccountBase("R3", 200000);

        fee1.pay(60000);
        fee2.pay(20000);
        fee3.pay(-5000);

        FeeHostelStudent ravi =
                new FeeHostelStudent("Ravi", "R1", fee1);

        FeeHostelStudent anitha =
                new FeeHostelStudent("Anitha", "R2", fee2);

        FeeHostelStudent karthik =
                new FeeHostelStudent("Karthik", "R3", fee3);

        room1.allot("Ravi");
        ravi.room = room1;

        room2.allot("Anitha");
        anitha.room = room2;

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());
        System.out.println("Total students: " + FeeHostelStudent.totalStudents);
    }
}