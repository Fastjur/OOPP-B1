import shared.Response;
import shared.User;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
                System.out.println("Remote socket closed, closing connection.");
                try {
                    this.client.closeConnection();
                } catch (IOException e) {
                    System.out.println("Could not close the connection");
                    e.printStackTrace();
                }
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
        JsonNode messageObj, requestData;
        try {
            messageObj = mapper.readTree(message);
            String action = messageObj.get("action").asText();
            requestData = mapper.readTree(messageObj.get("requestData").asText());
            Response response;

            switch (action) {
                case "register":
                    System.out.println("Received register from userid: " + client.userId);
                    response = new Response("register");
                    String regemail = requestData.get("email").asText(),
                            regpassword = requestData.get("password").asText();
                    boolean exists = false;
                    try {
                        User u = Server.getDb().getUser(regemail);
                        exists = u != null;
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    if (exists) {
                        response.errorCode = 2;
                        response.errorMessage = "That user already exists.";
                        break;
                    }

                    try {
                        Server.getDb().addUser(regemail, regpassword);
                    } catch (ClassNotFoundException | SQLException e) {
                        response.errorCode = 1;
                        response.errorMessage = "Generic error.";
                        break;
                    }
                    response.errorCode = 0;
                    response.errorMessage = "Registration successful.";
                    break;

                case "login":
                    System.out.print("Received login from: ");
                    response = new Response("login");
                    if (client.userId != -1) {
                        response.errorCode = 2;
                        response.errorMessage = "You are already logged in.";
                    } else {
                        String email = requestData.get("email").getTextValue();
                        String pass = requestData.get("pass").getTextValue();
                        System.out.println(email + " " + pass);//Gets appended to the first 'Received login'
                        try {
                            User user = Server.getDb().getUser(email);
                            if (user != null) {
                                if (PasswordHash.validatePassword(pass, user.getPassword())) {
                                    client.userId = user.getUserID();
                                    response.errorCode = 0;
                                    response.errorMessage = "Login successful!";
                                    break;
                                }
                            } else {
                                response.errorCode = 3;
                                response.errorMessage = "Couldn't find user!";
                                break;
                            }
                        } catch (ClassNotFoundException | SQLException | InvalidKeySpecException | NoSuchAlgorithmException e) {
                            response.errorCode = 1;
                            response.errorMessage = "Generic error";
                            e.printStackTrace();
                            break;
                        }
                        response.errorCode = 3;
                        response.errorMessage = "Invalid email/password.";
                        break;
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

                case "getMatches":
                    System.out.println("Received getMatches from userid: " + client.userId);
                    response = new Response("match");
                    if (client.userId == -1) {
                        response.errorCode = 2;
                        response.errorMessage = "You are not logged in.";
                    } else {
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
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            response.errorCode = 3;
                            response.errorMessage = "Could not get match users from database";
                        }
                    }
                    break;

                case "acceptMatch":
                    System.out.println("Received acceptMatch from userid: " + client.userId);
                    response = new Response("acceptMatch");
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in";
                        response.errorCode = 2;
                    } else {
                        int matchUserId = requestData.get("matchUser").getIntValue();
                        String matchType = requestData.get("matchType").getTextValue(), course = "";
                        if (matchType.equals("learning") || matchType.equals("teaching")) {
                            course = requestData.get("matchCourse").getTextValue();
                        }
                        if (!(matchType.equals("learning")) && !(matchType.equals("teaching")) && !(matchType.equals
                                ("buddy"))) {
                            response.errorMessage = "Wrong match type received!";
                            response.errorCode = 5;
                        } else if (matchUserId == 0) {
                            response.errorMessage = "Didn't receive matchUserId";
                            response.errorCode = 5;
                        } else {
                            try {
                                Server.getDb().acceptMatch(client.userId, matchUserId, matchType, course);
                                response.errorMessage = "Added match to database!";
                                response.errorCode = 0;
                            } catch (ClassNotFoundException | SQLException e) {
                                e.printStackTrace();
                                response.errorCode = 5;
                                response.errorMessage = "Couldn't add match to database!";
                            }
                        }
                    }
                    break;

                case "removeMatch":
                    System.out.println("Received removeMatch from userid: " + client.userId);
                    response = new Response("removeMatch");
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                    } else {
                        int matchId = requestData.get("matchId").getIntValue(),
                            self = client.userId;
                        try {
                            Server.getDb().removeMatch(self, matchId);
                            response.errorCode = 0;
                            response.errorMessage = "Removed match from database!";
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            response.errorCode = 6;
                            response.errorMessage = "Couldn't remove match from database";
                        }
                    }

                    break;

                case "getSelf":
                    System.out.println("Received getSelf from userid: " + client.userId);
                    response = new Response("getSelf");
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                    } else {
                        int self = client.userId;
                        try {
                            User dbSelf = Server.getDb().getUser(self);
                            response.putData("self", dbSelf);
                            response.errorMessage = "Retrieved your information!";
                            response.errorCode = 0;
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            response.errorCode = 7;
                            response.errorMessage = "Couldn't retrieve your information!";
                        }
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
            ex.printStackTrace();
        } //TODO catch all other exceptions and add to logfile
    }
}
