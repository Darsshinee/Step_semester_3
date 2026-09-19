public class LibraryMemberBean {

    private String membershipId;
    private String memberName;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMemberBean() {
        this("", false);
    }

    public LibraryMemberBean(String memberName) {
        this("", memberName);
    }

    public LibraryMemberBean(String membershipId, String memberName) {
        this.membershipId = membershipId;
        this.memberName = memberName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        if (this.membershipId == null || this.membershipId.isEmpty()) {
            this.membershipId = membershipId;
        }
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public static void main(String[] args) {

        LibraryMemberBean member = new LibraryMemberBean();

        member.setMembershipId("LIB1001");
        member.setMembershipId("LIB2002");

        member.setMemberName("Priya");
        member.setPremiumMember(true);
        member.setSecurityAnswer("blue");

        System.out.println(member.getMembershipId());
        System.out.println(member.getMemberName());
        System.out.println(member.isPremiumMember());
    }
}