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

    /**
     * 'Starts' the server and reads the database
     *
     * @param args (Commandline) parameters
     */
    public static void main(final String[] args) {
        try {
            db = new Database();
        } catch (IllegalStateException e) {
            System.out.println("[ERROR] Could not create database connection\n");
            e.printStackTrace();
        } finally {
            db.close();
        }

        AvailableTimes aTimes = new AvailableTimes();
        aTimes.addTimePeriod(1, new TimePeriod("07:30", "15:00"));
        aTimes.addTimePeriod(1, new TimePeriod("18:30", "21:00"));
        aTimes.addTimePeriod(3, new TimePeriod("10:30", "12:00"));
        aTimes.addTimePeriod(4, new TimePeriod("07:30", "15:00"));
        aTimes.addTimePeriod(5, new TimePeriod("07:30", "15:00"));
        aTimes.addTimePeriod(5, new TimePeriod("16:30", "18:00"));
        aTimes.addTimePeriod(7, new TimePeriod("09:30", "15:00"));
        /*User user = new User(-1, "Passwordz", "Mark", "Johnsson", new Date(1609802), "mark@johnsson.com",
                "06-123456789", new Address("Poeplaan", "69", "2156AB", "Pissing City"), "Technische Plassen",
                "University of Plassen", 3, aTimes, )*/;

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