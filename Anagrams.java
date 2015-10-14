import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collection;

public class Anagrams {

    private static Set<String> mCombinations;

    public static void main(String[] args) {
        System.out.println("Enter a word or <Enter> to exit:");

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();

        WordDictionary dictionary = new WordDictionary("wordsList.txt");
        if (str.length() > 0) {
            if (dictionary.hasWord(str)) {
                mCombinations = new TreeSet<>();
                permutation(str);

                filterRealWords(dictionary);
                removeCaseInsensitive(mCombinations, str);
                printSet(mCombinations, str, true);

            }
            else {
                System.out.println(String.format("The word [%s] is not in the dictionary", str));
            }

        }
    }

    /**
     * Forces targetString to match the case of String sourceCase. If the lengths
     * do not match, targetString is returned as-is.
     *
     * Ex,
     *
     *  matchCase("pooL", "frog");
     *
     * Should return "froG"
     *
     * @return returns targetString in the case matching sourceCase.
     */
    private static String matchCase(String sourceCase, String targetString) {
        String retString = targetString;
        if (sourceCase.length() == targetString.length()) {
            StringBuilder stringBuilder = new StringBuilder();

            //write a loop, check each character in sourceCase
            //append targetString letter in the same case
            for (int i = 0; i < sourceCase.length(); i++) {
                if (Character.isUpperCase(sourceCase.charAt(i))) {
                    stringBuilder.append(Character.toUpperCase(targetString.charAt(i)));
                }
                else {
                    stringBuilder.append(Character.toLowerCase(targetString.charAt(i)));
                }
            }

            retString = stringBuilder.toString();
        }
        return retString;
    }

    private static void printSet(Set<String> list) {
        printSet(list, "", false);
    }

    private static void printSet(Set<String> list, String inputWord, boolean matchCase) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        String prefix = "";
        for (String word : list) {
            stringBuilder.append(prefix);
            if (matchCase) {
                stringBuilder.append(matchCase(inputWord, word));
            }
            else {
                stringBuilder.append(word);
            }
            prefix = ",";
        }

        stringBuilder.append("]");

        System.out.println(stringBuilder.toString());
        
    }

    /**
     * Records all permutations of the string {@code str} into
     * the set {@code mCombinations}.
     *
     * Taken from StackOverflow.
     */
    public static void permutation(String str) {
        permutation("", str.toLowerCase());
    }

    /**
     * Recursive method for getting permutations of a string.
     * Modified from a StackOverflow answer
     */
    private static void permutation (String prefix, String str) {
        int n = str.length();

        if (n == 0) {
            mCombinations.add(prefix);
        }
        else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i),
                    str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }

    /** Generate a list of anagrams.
     *  Modified from a StackOverflow answer
     */

    public static void filterRealWords(WordDictionary dictionary) {
        mCombinations.retainAll(dictionary.getAllWords());
    }

    /**
     * Removes a string from a collection of strings, ignoring case
     */
    public static boolean removeCaseInsensitive(Collection<String> collection, String string) {
        boolean retValue = false;
        for (String currentString : collection) {
            if (currentString.toLowerCase().equals(string.toLowerCase())) {
                collection.remove(currentString);
                retValue = true;
            }
        }

        return retValue;
    }
    
}



