package server;

import java.io.IOException;

/**
 * Main Server Class
 * This file will most likely contain the main method for starting up the server
 * @author Jurriaan Den Toonder
 * @version 0.1
 */
public class Server {

    private Server server;
    private static Database db;

    /**
     * 'Starts' the server and reads the database
     * @param args (Commandline) parameters
     */
    public static void main(final String[] args) {
        try {
            db = new Database("src/server/database.json");
            System.out.println(db);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}