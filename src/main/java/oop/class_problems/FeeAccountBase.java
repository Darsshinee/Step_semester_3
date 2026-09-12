class FeeAccountBase {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccountBase(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccountBase {

    HostelFeeAccount(String regNo, double totalFee) {
        super(regNo, totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
        pay(amount);
    }
}

class ScholarshipFeeAccount extends FeeAccountBase {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee, double scholarshipPercent) {
        super(regNo, totalFee);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() - getDue() * scholarshipPercent / 100;
    }
}

public class FeeAccount {

    public static void main(String[] args) {
        FeeAccountBase plain = new FeeAccountBase("R1", 150000);
        plain.pay(150000);

        HostelFeeAccount hostel = new HostelFeeAccount("R2", 200000);
        hostel.payInTwoInstallments(30000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("R3", 180000, 20);

        System.out.println("Plain account due: Rs " + plain.getDue());
        System.out.println("Hostel account due: Rs " + hostel.getDue());
        System.out.println("Scholarship account effective due: Rs "
                + scholarship.effectiveDue());
    }
}