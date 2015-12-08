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
 * @version 0.2
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
            db.getUser("john@doe.com");
            User temp = db.getUser(1);
            temp.setUserID(-1);
            db.addUser(temp);
        } catch (SQLException e) {
            System.out.println("[ERROR] SQLException:\n    " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[ERROR] IOException:\n    " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] MySQL query failed:\n    " + e.getMessage());
            e.printStackTrace();
        }

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

        System.out.println("Server exiting...");
        listenthread.end();
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