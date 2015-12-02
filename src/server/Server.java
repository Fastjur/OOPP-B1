package server;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
            db = new Database();
            db.getUser("sinterklaas@sintmail.nl");
            db.getUser("john@doe.com");
            db.getUser("ayykek");//Should return empty
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        listenthread = new ListenThread(clients, 8372);
        listenthread.start();
        // some sort of interactive console here?
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("Type 'quit' to exit the server.");

        while(!input.equals("quit"))
        {
            input = scanner.nextLine();
            System.out.println("You typed: " + input);
        }

        System.out.println("Server exiting...");
        listenthread.end();
    }

}