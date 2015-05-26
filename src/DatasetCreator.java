import java.io.*;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * <h1>The Data Set Creator</h1>
 * This program reads in text files and adds them either to a data set file.
 * The user can add in english or afrikaans texts. This will affect the trailing integer(1 if english, 0 if afrikaans).
 *
 * @author Trevor Austin
 * @version 1.0
 * @since 26/05/2015
 */
public class DatasetCreator {

    /**
     * This variable is used to store the document directory.
     */
    private String doc;

    /**
     * This is the default constructor it will create the file within the current directory.
     */
    public DatasetCreator() {
        try {
            String dir = "doc.txt";
            PrintWriter file = new PrintWriter(dir, "UTF-8");
            file.close();
            doc = dir;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This constructor will take in a file specified by the user and use it to store the data set information.
     *
     * @param doc This sets the current data set document.
     */
    public DatasetCreator(String doc) {
        this.doc = doc;
    }

    /**
     * This will modify the int array so that the final value in the array is a 0.
     * This allows for the data set to portray that the text was afrikaans.
     * It then calls the addDocument function.
     *
     * @param directory This is the directory of the file being processed
     */
    public void addAfrikaansDocument(String directory) {
        int[] letterCount = new int[27];
        for (int i = 0; i < letterCount.length; i++)
            letterCount[i] = 0;
        addDocument(directory, letterCount);
    }

    /**
     * This will modify the int array so that the final value in the array is a 1.
     * This allows for the data set to portray that the text was english.
     * It then calls the addDocument function.
     *
     * @param directory This is the directory of the file being processed
     */
    public void addEnglishDocument(String directory) {
        int[] letterCount = new int[27];
        for (int i = 0; i < letterCount.length - 1; i++)
            letterCount[i] = 0;
        letterCount[26] = 1;
        addDocument(directory, letterCount);
    }

    /**
     * This will read through a file and count the occurrences of a specific letter.
     * The specific letter has position in the integer array and every time a letter is detected this position is increased by one.
     * Once the text file has been parsed the array will be output to either a training file or testing file;
     *
     * @param directory This is the directory of the file being processed
     * @param letters   This is the integer array used to store the count of each character.
     */
    private void addDocument(String directory, int[] letters) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(directory));
            String s = "";
            String temp;
            while ((temp = br.readLine()) != null) {
                s += temp;
            }
            System.out.println("Before deaccent");
            System.out.println(s);
            s = deAccent(s);
            System.out.println("After deaccent");
            System.out.println(s);
            s = s.toUpperCase();
            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetter(s.charAt(i))) {
                    int num = (int) s.charAt(i) - 65;
                    letters[num]++;
                }
            }
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(doc, true)));
            for (int i = 0; i < letters.length-1; i++) {
                out.print(letters[i] + " ");
            }
            out.println(letters[letters.length - 1]);
            br.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This will get rid of all diacritical marks within a string.
     *
     * @param str The string to be processed.
     * @return It will return a new string without diacritical marks.
     */
    private String deAccent(String str) {
        String s = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern p = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return p.matcher(s).replaceAll("");
    }
}
