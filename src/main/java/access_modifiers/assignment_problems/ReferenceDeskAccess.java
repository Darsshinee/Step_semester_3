public class ReferenceDeskAccess {

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
            return context.equals("SAME_CLASS") ||
                   context.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (modifier.equals("protected")) {
            if (context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return "ALLOWED";
            }

            if (context.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
                return "DENIED";
            }

            return context.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";
        }

        return "DENIED";
    }

    static String describeContext(String context) {
        String[] words = context.toLowerCase().split("_");
        String result = "";

        for (String word : words) {
            result += Character.toUpperCase(word.charAt(0))
                    + word.substring(1) + " ";
        }

        return result.trim();
    }

    public static void main(String[] args) {
        System.out.println(
                classifyAccess("protected",
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(
                describeContext(
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
    }
}