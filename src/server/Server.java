package server;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main Server Class
 * This file will most likely contain the main method for starting up the server
 * @author Jurriaan Den Toonder
 * @version 0.1
 */
public class Server {

    private static Database db;
    private static ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>();
    private static ListenThread listenthread;

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

        listenthread = new ListenThread(clients, 8372);
        listenthread.start();

        // some sort of interactive console here?
    }

}