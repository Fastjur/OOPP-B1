package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * ListenerThread listens for clients and starts connections with them.
 *
 * @author Govert de Gans
 * @version 0.1
 */
public class ListenThread extends Thread {
    private ArrayList<ConnectedClient> clientlist;
    private boolean shouldstop;
    private int port;
    private ServerSocket ss;

    public ListenThread(ArrayList<ConnectedClient> clientlist, int port) {
        super("ListenerThread");
        this.clientlist = clientlist;
    }

    public void run() {
        try {
            ss = new ServerSocket(port);
        } catch (java.io.IOException ex) {
            System.out.println("Err: could not open a server socket on port " + port);
            return;
        }
        shouldstop = false;
        System.out.println("Server listening on port " + port);

        while (!shouldstop) {
            try {
                Socket newsocket = ss.accept();
                clientlist.add(new ConnectedClient(newsocket));
            } catch (SocketException Sex) {
                System.out.println("Server socket closed.");
            } catch (java.io.IOException IOex) {
                System.out.println(IOex.getLocalizedMessage() + "\nErr: An IO exception occurred while waiting for a client, abandon thread");
                shouldstop = true;
            }
        }
        System.out.println("Closing all client connections...");
        closeAllConnections();
    }

    public void end() {
        this.shouldstop = true;
        try {
        ss.close();
        } catch (java.io.IOException ex) {
            System.out.println(ex.getLocalizedMessage() + "\nErr:Could not close the server socket");
            shouldstop = true;
        }
    }

    public void closeAllConnections() {
        try {
            for (int i = clientlist.size() - 1; i >= 0; i--) {
                clientlist.get(i).closeConnection();
                clientlist.remove(i);
            }
        } catch (java.io.IOException ex) {
            System.out.println("Err: could not close all connections\n" + ex.getLocalizedMessage());
        }
    }
}