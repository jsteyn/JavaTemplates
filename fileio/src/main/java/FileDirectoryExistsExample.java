import java.io.File;

/**
 * <ol>
 * <li>How to check whether a file or a directory exists or not</li>
 * <li>How to check whether something is a file or a directory</li>
 * </ol>
 */
public class FileDirectoryExistsExample {
    static File directory = new File("data/");
    static File file = new File("data/sqlite.properties");

    public static void main(String[] args) {
        // test "/var/tmp" directory

        // check to see if given exists
        boolean exists = directory.exists();
        if (exists) {
            System.out.println(directory.getAbsolutePath() + " exists");
        } else {
            System.out.println(directory.getAbsolutePath() + " does not exist");
        }
        if (file.getAbsoluteFile().exists()) {
            System.out.println(file.getAbsoluteFile() + " exists");
        } else {
            System.out.println(file.getAbsolutePath() + " does not exists");
        }

        // test to see if given is a directory
        if (directory.isDirectory()) System.out.println(directory.getAbsolutePath() + " is a directory");

        // test to see if a file exists
        exists = file.exists();
        if (file.exists() && file.isFile()) {
            System.out.println(file.getAbsolutePath() + " exists, and it is a file");
        }
    }
}