class HostelFeeAccount extends FeeAccount {
    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
        pay(amount);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() - (getDue() * scholarshipPercent / 100);
    }
}

public class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("R1", 150000);
        HostelFeeAccount hostel = new HostelFeeAccount("R2", 200000);
        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("R3", 180000, 20);

        plain.pay(150000);
        hostel.payInTwoInstallments(30000);

        System.out.println("Plain account due: Rs " + plain.getDue());
        System.out.println("Hostel account due: Rs " + hostel.getDue());
        System.out.println("Scholarship effective due: Rs "
                + scholarship.effectiveDue());
    }
}