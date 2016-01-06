import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection manager for the database connection
 *
 * @author Jurriaan Den Toonder
 * @version 1.0
 */
public class ConnectionManager {
    private static final String url = "jdbc:mysql://db4free.net:3306/ooppb1";
    private static final String user = "oopp_usr";
    private static final String pass = "oopp_b1_database";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static Connection con;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, pass);
        return con;
    }

    public static void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
