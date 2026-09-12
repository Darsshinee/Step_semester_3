import java.util.Scanner;

public class ShortestLongestWord {

    static String[] findShortestAndLongestWord(String text) {
        String[] words = text.split("\\s+");

        String shortest = words[0];
        String longest = words[0];

        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "");

            if (word.length() < shortest.length()) {
                shortest = word;
            }

            if (word.length() > longest.length()) {
                longest = word;
            }
        }

        return new String[]{shortest, longest};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sentence: ");
        String text = sc.nextLine();

        String[] result = findShortestAndLongestWord(text);

        System.out.println("Shortest: \"" + result[0] + "\" (" + result[0].length() + ")");
        System.out.println("Longest: \"" + result[1] + "\" (" + result[1].length() + ")");

        sc.close();
    }
}