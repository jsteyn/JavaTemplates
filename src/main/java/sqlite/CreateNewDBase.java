package sqlite;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateNewDBase {

    static ReadProperties properties = ReadProperties.getInstance();
    /**
     * Connect to a sample database
     *
     */
    public static void createNewDatabase() {

        String url = "jdbc:sqlite:" + properties.getProperty("sqlitedb");

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewDatabase();
    }
}
