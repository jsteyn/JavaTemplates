import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton to load properties
 */
public class ReadProperties {
    static Properties prop = new Properties();
    static ReadProperties readProperties = null;

    private ReadProperties() {
        try  {
            InputStream input = new FileInputStream("data/sqlite.properties");
            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static public ReadProperties getInstance() {
        if (readProperties == null) {
            readProperties = new ReadProperties();
        }
        return readProperties;
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

}
