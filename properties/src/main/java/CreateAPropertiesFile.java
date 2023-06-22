import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;


public class CreateAPropertiesFile {


    public static void main(String[] args) {
        File propfile = new File("data/config.properties");
        try  {
            OutputStream output = new FileOutputStream(propfile);
            System.out.println(propfile.getAbsolutePath());
            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("db.url", "localhost");
            prop.setProperty("db.user", "user");
            prop.setProperty("db.password", "password");

            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
