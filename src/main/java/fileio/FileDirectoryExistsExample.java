package fileio;

import java.io.File;

public class FileDirectoryExistsExample {
    static File directory = new File("sqlite/");
    static File file = new File("sqlite/sqlite.properties");

    public static void main(String[] args) {
        // test "/var/tmp" directory
        boolean exists = directory.exists();
        if (exists) {
            System.out.println(directory.getAbsolutePath() + " exists");
        } else {
            System.out.println(directory.getAbsolutePath() + " does not exist");
        }

        if (directory.isDirectory()) System.out.println(directory.getAbsolutePath() + " is a directory");

        // test to see if a file exists
        exists = file.exists();
        if (file.exists() && file.isFile()) {
            System.out.println(file.getAbsolutePath() + " exists, and it is a file");
        }
    }
}