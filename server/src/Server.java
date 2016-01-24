import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Server Class
 * This file will most likely contain the shared method for starting up the server
 *
 * @author Jurriaan Den Toonder
 * @author Govert de Gans
 * @version 1.0
 */
public class Server {

    private static Database db;
    private static ArrayList<ConnectedClient> clients = new ArrayList<>();
    private static ListenThread listenthread;

    public static void SetupDudDatabaseForTesting() {
        db = new DudDatabase();
    }

    /**
     * 'Starts' the server and reads the database
     *
     * @param args (Commandline) parameters
     */
    public static void main(final String[] args) {
        try {
            db = new Database();
            start();
        } catch (IllegalStateException e) {
            System.out.println("[ERROR] Could not create database connection");
            e.printStackTrace();
        }

    }

    private static void start() {
        listenthread = new ListenThread(clients, 8372);
        listenthread.start();
        //todo: some sort of interactive console here?
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("Type 'quit' to exit the server.");

        try {
            while (!input.equals("quit")) {
                input = scanner.nextLine();
                System.out.println("You typed: " + input);
            }
        } catch (Exception e) {
            // Catch every unhandled and unchecked exception here, allowing the server to continue
            // TODO Adds an entry to the log file for investigation
        }

        //Will only continue to here once the user types quit
        close();
    }

    private static void close() {
        System.out.println("Server exiting...");
        listenthread.end();
        db.close();
    }

    public static Database getDb() {
        return db;
    }

}