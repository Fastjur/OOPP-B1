package server;

import java.io.IOException;
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

    private Server server;

    public static void main(final String[] args) {
        try {
            Database db = new Database("src/server/database.txt");
            System.out.println(db);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}