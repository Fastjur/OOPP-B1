package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main Server Class
 * This file will most likely contain the main method for starting up the server
 * @author Jurriaan Den Toonder
 * @version 0.1
 */
public class Server {

    //SQL Database connection variables
    private static Connection dbConn;
    private static final String jdbcDriver = "com.mysql.jdbc.Driver";
    private static final String dbHost = "jdbc:mysql://localhost/oopp";
    private static final String dbUser = "oopp_usr";
    private static final String dbPass = "TD5hxzMjp3RBVKy5";



    public static void main(final String[] args) {
        try {
            dbConn = getConnection();
            System.out.println("Successful database connection!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the database driver!");
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException, ClassNotFoundException{
        //Registers the JDBC driver
        Class.forName(jdbcDriver);

        System.out.println("Attempting to connect to database...");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPass);

        return conn;
    }
}