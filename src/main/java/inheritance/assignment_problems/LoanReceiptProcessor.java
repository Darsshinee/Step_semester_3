import java.util.Arrays;

final class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static {
        System.out.println("Loan Receipt System Ready");
    }

    public LoanReceipt(String memberId, String[] bookIds) {

        if (memberId == null || bookIds == null) {
            throw new IllegalArgumentException("Invalid data");
        }

        for (String id : bookIds) {
            if (!isValidBookId(id)) {
                throw new IllegalArgumentException("Invalid book ID");
            }
        }

        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    private static boolean isValidBookId(String id) {

        if (id == null || id.length() != 6) {
            return false;
        }

        return id.charAt(0) == 'B'
                && id.charAt(1) == 'K'
                && id.charAt(2) == '-'
                && Character.isDigit(id.charAt(3))
                && Character.isDigit(id.charAt(4))
                && Character.isDigit(id.charAt(5));
    }

    String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    LoanReceipt withCorrectedBookId(int index, String newId) {

        if (!isValidBookId(newId)) {
            throw new IllegalArgumentException("Invalid book ID");
        }

        String[] newBooks = Arrays.copyOf(bookIds, bookIds.length);
        newBooks[index] = newId;

        return new LoanReceipt(memberId, newBooks);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId,
                                    String[] bookIds,
                                    String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

public class LoanReceiptProcessor {

    static String processNightlyCirculation(LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt(
                    "LIB-001",
                    new String[]{"BK-200"},
                    "Reading Room 3"),

            null,

            new LoanReceipt(
                    "LIB-002",
                    new String[]{"BK-201"})
        };

        System.out.println(processNightlyCirculation(receipts));
    }
}