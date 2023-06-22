import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Jannetta S. Steyn
 * @version 1
 */
public class ReadingAFileWithScanner {

    /**
     * Name of file
     */
    static String filename = "data/testfile.txt";
    /**
     * Counter to count number of lines in the file
     */
    static int counter = 0;

    static public void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                counter++;
                System.out.println("Line " + counter + ": " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist.");
            e.printStackTrace();
        }
    }
}
