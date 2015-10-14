import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class WordDictionary {

    private List<String> mWordDictionary;

    /**
     * Creates a word dictionary from the words found in the specified file
     *
     * @param filename the file to try to load
     */
    public WordDictionary(String filename) {
        //instantiating an ArrayList object, and assigning it to mWordDictionary
        mWordDictionary = new ArrayList<>();

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                mWordDictionary.add(inputLine.toLowerCase());
            }
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if the dictionary contains the provided word
     *
     * @param word the word to check dictionary for
     */
    public boolean hasWord(String word) {
        return mWordDictionary.contains(word.toLowerCase());
    }

    public List<String> getAllWords() {
        return mWordDictionary;
    }
}