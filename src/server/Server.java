package server;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Server Class
 * This file will most likely contain the main method for starting up the server
 *
 * @author Jurriaan Den Toonder
 * @author Govert de Gans
 * @version 1.0
 */
public class Server {

    private static Database db;
    private static ArrayList<ConnectedClient> clients = new ArrayList<>();
    private static ListenThread listenthread;
    private static ArrayList<Session> sessions = new ArrayList<>();

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
        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] Could not find database driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("[ERROR] SQLException occurred");
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

        while (!input.equals("quit")) {
            input = scanner.nextLine();
            System.out.println("You typed: " + input);
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

    public void logIn(String email, String password){
        User user;
        try {
            user = db.getUser(email);
            if(user.getMail().equals(email) && user.getPassword().equals(password)){
                Session session = new Session(user.getUserID());
                sessions.add(session);
            }
            else{
                System.out.println("Authentication failed.");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}