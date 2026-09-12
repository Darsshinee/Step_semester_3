public class LibraryMember {

    static String libraryName = "SRM Library";
    static int memberCount = 0;

    String name;
    String memberId;
    int booksIssued;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {
        LibraryMember aditi = new LibraryMember("Aditi", 2);
        LibraryMember rohan = new LibraryMember("Rohan", 3);

        aditi.printMemberCard();
        rohan.printMemberCard();

        printTotalMembers();
    }
}