class LibraryMember {
    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId,
                         String branchCode,
                         double finesOwed,
                         String displayName) {
        if (membershipId == null ||
            membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid membership ID");
        }

        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class AccessChecker {

    static String classifyAccess(String modifier,
                                 String context) {

        if (modifier.equals("public")) {
            return "ALLOWED";
        }

        if (modifier.equals("private")) {
            return context.equals("SAME_CLASS")
                    ? "ALLOWED" : "DENIED";
        }

        if (modifier.equals("default")) {
            return context.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";
        }

        if (modifier.equals("protected")) {
            return context.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        int pa = 0, pd = 0;
        int da = 0, dd = 0;
        int pra = 0, prd = 0;
        int puba = 0, pubd = 0;

        for (String[] a : attempts) {
            boolean allowed =
                    classifyAccess(a[0], a[1]).equals("ALLOWED");

            switch (a[0]) {
                case "private":
                    if (allowed) pa++; else pd++;
                    break;
                case "default":
                    if (allowed) da++; else dd++;
                    break;
                case "protected":
                    if (allowed) pra++; else prd++;
                    break;
                case "public":
                    if (allowed) puba++; else pubd++;
            }
        }

        return "private: " + pa + " allowed / " + pd +
                " denied | default: " + da + " allowed / " + dd +
                " denied | protected: " + pra + " allowed / " + prd +
                " denied | public: " + puba + " allowed / " + pubd +
                " denied";
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));
    }
}