import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A thread that handles the connection with a client
 *
 * @author Govert de Gans
 * @version 0.1
 */
public class ClientConnectionThread extends Thread {
    private Socket socket;
    private ConnectedClient client;
    private boolean shouldStop;
    private InputStream inputStream;
    private OutputStream outputStream;

    /**
     * Constructs a new ClientConnectionThread for communicating with a client
     *
     * @param client the client to communicate with in this thread
     */
    public ClientConnectionThread(ConnectedClient client) {
        this.socket = client.getSocket();
        this.client = client;
    }

    public void run() {
        shouldStop = false;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (java.io.IOException ex) {
            System.out.println("Err: could not open input/output streams for socket");
        }

        while (!shouldStop) {
            try {
                byte[] buf = new byte[4];
                inputStream.read(buf);
                int messagelength = ByteBuffer.wrap(buf).getInt();
                if (messagelength > 0 && messagelength < 65536) {
                    byte[] buffer = new byte[messagelength];
                    inputStream.read(buffer, 0, messagelength);
                    String message = decodeMessage(buffer);
                    handleMessage(message);
                } else {
                    System.out.println("Client sent an invalid request, disconnecting client.");
                    this.client.closeConnection();
                }
            } catch (SocketException SEx) {
                // Socket closed.
            } catch (IOException IOEx) {
                System.out.println("Something went wrong while reading a message from the network.\n" + IOEx.getLocalizedMessage());
            }
        }
    }

    /**
     * Ends the ClientConnectionThread
     */
    public void end() {
        shouldStop = true;
    }

    private String decodeMessage(byte[] buf) {
        return new String(buf, StandardCharsets.UTF_8);
    }

    private byte[] encodeMessage(String input) {
        return input.getBytes(StandardCharsets.UTF_8); // TODO: encryption?
    }

    /**
     * Send a message to the client associated with this thread
     *
     * @param message The message to send
     * @throws java.io.IOException when the socket is being written to while trying to write.
     */
    public synchronized void sendMessage(String message) throws java.io.IOException {
        byte[] msg = encodeMessage(message);
        outputStream.write(ByteBuffer.allocate(4).putInt(msg.length).array());
        outputStream.write(msg);
    }

    private void handleMessage(String message) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode messageObj;
        try {
            messageObj = mapper.readTree(message);
            String action = messageObj.get("action").asText();
            Response response;

            switch (action) {
                case "register":
                    response = new Response("register");
                    User newuser = mapper.treeToValue(messageObj.get("requestData").get("newUser"), User.class);
                    newuser.setUserID(-1);
                    boolean exists = false;
                    try {
                        User u = Server.getDb().getUser(newuser.getMail());
                        exists = u != null;
                    } catch (SQLException e) { }

                    if (exists) {
                        response.errorCode = 2;
                        response.errorMessage = "That user already exists.";
                        break;
                    }

                    try {
                        Server.getDb().addUser(newuser);
                    } catch (SQLException e) {
                        response.errorCode = 1;
                        response.errorMessage = "Generic error.";
                        break;
                    }
                    response.errorCode = 0;
                    response.errorMessage = "Registration successful.";
                    break;

                case "login":
                    response = new Response("login");
                    if (client.userId != -1) {
                        response.errorCode = 2;
                        response.errorMessage = "You are already logged in.";
                    } else {
                        String email = messageObj.get("requestData").get("email").getTextValue();
                        String pass = messageObj.get("requestData").get("pass").getTextValue();
                        try {
                            User user = Server.getDb().getUser(email);
                            if (user != null && user.getPassword().equals(pass)) {
                                this.client.userId = user.getUserID();

                                response.errorCode = 0;
                                response.errorMessage = "Login successful.";
                                break;
                            }
                        } catch(SQLException e) {
                            // user not found
                        }
                        response.errorCode = 3;
                        response.errorMessage = "Invalid email/password.";
                    }
                    break;

                case "logout":
                    response = new Response("logout");
                    if (this.client.userId == -1) {
                        response.errorCode = 2;
                        response.errorMessage = "You are not logged in.";
                    } else {
                        this.client.userId = -1;
                        response.errorCode = 0;
                        response.errorMessage = "Logout successful.";
                    }
                    break;

                case "match":
                    response = new Response("match");
                    try {
                        ArrayList<ArrayList<User>> result = Server.getDb().getMatches(this.client.userId, messageObj);
                        if (result == null) {
                            response.errorMessage = "No matches found!";
                            response.errorCode = 4;
                        } else {
                            response.errorMessage = "Retrieved Matches";
                            response.errorCode = 0;
                            response.putData("canTeach", result.get(0));
                            response.putData("canLearn", result.get(1));
                            response.putData("canBuddyUp", result.get(2));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.errorCode = 3;
                        response.errorMessage = "Could not get match users from database";
                    }
                    break;

                default:
                    response = new Response(action);
                    response.errorMessage = "Unknown command.";
                    response.errorCode = 1;
            }

            client.sendMessage(mapper.writeValueAsString(response));

        } catch (IOException ex) {
            System.out.println("Something went wrong while reading a message from the network.\n" +
                    ex.getLocalizedMessage());
        } //TODO catch all other exceptions and add to logfile
    }
}
