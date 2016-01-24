import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.type.TypeReference;
import shared.AvailableTimes;
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
import java.util.HashMap;

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
                if (shouldStop) {
                    System.out.println("Closing connection.");
                } else {
                    System.out.println("Remote socket closed, closing connection.");
                    try {
                        this.client.closeConnection();
                    } catch (IOException e) {
                        System.out.println("Could not close the connection");
                        e.printStackTrace();
                    }
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
            requestData = messageObj.get("requestData");
            Response response;

            label:
            switch (action) {
                case "register":
                    System.out.println("Received register from userid: " + client.userId);
                    response = new Response(action);
                    String regemail = requestData.get("email").asText(),
                            regpassword = requestData.get("password").asText();
                    User u = Server.getDb().getUser(regemail);
                    boolean exists = u != null;

                    if (exists) {
                        response.errorCode = 2;
                        response.errorMessage = "That user already exists.";
                        break;
                    }

                    Server.getDb().addUser(regemail, regpassword);

                    response.errorCode = 0;
                    response.errorMessage = "Registration successful.";
                    break;

                case "login":
                    System.out.print("Received login from: ");
                    response = new Response(action);
                    if (client.userId != -1) {
                        response.errorCode = 2;
                        response.errorMessage = "You are already logged in.";
                        break;
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

                case "logout":
                    System.out.println("Received logout from userid: " + client.userId);
                    response = new Response(action);
                    if (this.client.userId == -1) {
                        response.errorCode = 2;
                        response.errorMessage = "You are not logged in.";
                        break;
                    } else {
                        this.client.userId = -1;
                        response.errorCode = 0;
                        response.errorMessage = "Logout successful.";
                        break;
                    }

                case "findMatch":
                    System.out.println("Received findMatch from userid " + client.userId);
                    response = new Response(action);
                    if (this.client.userId == -1) {
                        response.errorCode = 2;
                        response.errorMessage = "You are not logged in.";
                        break;
                    } else {
                        String type = requestData.get("type").getTextValue();
                        switch (type) {
                            case "buddy": {
                                response = new Response("findBuddy");
                                String course = requestData.get("course").getTextValue();
                                ArrayList<User> findBuddyRes = Server.getDb().findStudyBuddy(client.userId, course);
                                if (findBuddyRes.size() > 0) {
                                    response.putData("findBuddyRes", findBuddyRes);
                                    response.errorCode = 0;
                                    response.errorMessage = "Matched buddies!";
                                    break;
                                } else {
                                    response.errorCode = 9;
                                    response.errorMessage = "Couldn't match any buddies!";
                                    break;
                                }
                            }
                            case "learning": {
                                response = new Response("findBuddy");
                                String course = requestData.get("course").getTextValue();
                                ArrayList<User> findBuddyRes = Server.getDb().findTutor(client.userId, course);
                                if (findBuddyRes.size() > 0) {
                                    response.putData("findBuddyRes", findBuddyRes);
                                    response.errorCode = 0;
                                    response.errorMessage = "Matched tutor!";
                                    break;
                                } else {
                                    response.errorCode = 9;
                                    response.errorMessage = "Couldn't match any tutors!";
                                    break;
                                }
                            }
                            case "teaching": {
                                response = new Response("findBuddy");
                                String course = requestData.get("course").getTextValue();
                                ArrayList<User> findBuddyRes = Server.getDb().findStudent(client.userId, course);
                                if (findBuddyRes.size() > 0) {
                                    response.putData("findBuddyRes", findBuddyRes);
                                    response.errorCode = 0;
                                    response.errorMessage = "Matched student!";
                                    break;
                                } else {
                                    response.errorCode = 9;
                                    response.errorMessage = "Couldn't match any students!";
                                    break;
                                }
                            }
                            case "emergency": {
                                response = new Response("findBuddy");
                                String course = requestData.get("course").getTextValue();
                                ArrayList<User> findBuddyRes = Server.getDb().findEmergency(client.userId, course);
                                if (findBuddyRes.size() > 0) {
                                    response.putData("findBuddyRes", findBuddyRes);
                                    response.errorCode = 0;
                                    response.errorMessage = "Matched emergency tutor!";
                                    break;
                                } else {
                                    response.errorCode = 9;
                                    response.errorMessage = "Couldn't match any emergency tutors!";
                                    break;
                                }
                            }
                        }
                    }
                    break;

                case "acceptMatch":
                    System.out.println("Received acceptMatch from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in";
                        response.errorCode = 2;
                        break;
                    } else {
                        int matchUserId = requestData.get("matchUser").getIntValue();
                        String matchType = requestData.get("matchType").getTextValue(),
                                course = requestData.get("matchCourse").getTextValue();
                        if (!(matchType.equals("learning")) && !(matchType.equals("teaching")) && !(matchType.equals
                                ("buddy"))) {
                            response.errorMessage = "Wrong match type received!";
                            response.errorCode = 5;
                            break;
                        } else if (matchUserId == 0) {
                            response.errorMessage = "Didn't receive matchUserId";
                            response.errorCode = 5;
                            break;
                        } else {
                            Server.getDb().acceptMatch(client.userId, matchUserId, matchType, course);
                            response.errorMessage = "Added match to database!";
                            response.errorCode = 0;
                            break;
                        }
                    }

                case "removeMatch":
                    System.out.println("Received removeMatch from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        int matchId = requestData.get("matchId").getIntValue(),
                                self = client.userId;
                        Server.getDb().removeMatch(self, matchId);
                        response.errorCode = 0;
                        response.errorMessage = "Removed match from database!";
                        break;
                    }

                case "getSelf":
                    System.out.println("Received getSelf from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        int self = client.userId;
                        User dbSelf = Server.getDb().getUser(self);
                        response.putData("self", dbSelf);
                        response.errorMessage = "Retrieved your information!";
                        response.errorCode = 0;
                        break;
                    }

                case "getNationalities":
                    System.out.println("Received getNationalities from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        HashMap<Integer, String> dbNationalities = Server.getDb().getNationalities();
                        response.putData("nationalities", dbNationalities);
                        response.errorCode = 0;
                        response.errorMessage = "Retreived database nationalities!";
                        break;
                    }

                case "getLanguages":
                    System.out.println("Received getLanguages from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        HashMap<Integer, String> dbLanguages = Server.getDb().getLanguages();
                        response.putData("dbLanguages", dbLanguages);
                        response.errorCode = 0;
                        response.errorMessage = "Retreived database languages!";
                        break;
                    }

                case "getStudies":
                    System.out.println("Received getStudies from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        HashMap<Integer, String> dbStudies = Server.getDb().getStudies();
                        response.putData("dbStudies", dbStudies);
                        response.errorCode = 0;
                        response.errorMessage = "Retreived database studies!";
                        break;
                    }

                case "getUniversities":
                    System.out.println("Received getUniversities from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        HashMap<Integer, String> dbUniversities = Server.getDb().getUniversities();
                        response.putData("dbUniversities", dbUniversities);
                        response.errorCode = 0;
                        response.errorMessage = "Retreived database universities!";
                        break;
                    }

                case "getCourses":
                    System.out.println("Received getCourses from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        HashMap<Integer, String> dbCourses = Server.getDb().getCourses();
                        response.putData("dbCourses", dbCourses);
                        response.errorCode = 0;
                        response.errorMessage = "Retreived database courses!";
                        break;
                    }

                case "updateNationality":
                    System.out.println("Received updateNationality from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        int nationalityId = requestData.get("nationality").getIntValue();
                        Server.getDb().updateNationality(client.userId, nationalityId);
                        response.errorCode = 0;
                        response.errorMessage = "Updated nationality!";
                        break;
                    }

                case "updateName":
                    System.out.println("Received updateName from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        String firstname = requestData.get("firstname").getTextValue(),
                                lastname = requestData.get("lastname").getTextValue();
                        Server.getDb().updateName(client.userId, firstname, lastname);
                        response.errorCode = 0;
                        response.errorMessage = "Updated name!";
                        break;
                    }

                case "updateDateOfBirth":
                    System.out.println("Received updateDateOfBirth from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        Long date = requestData.get("date").getLongValue();
                        Server.getDb().updateDateOfBirth(client.userId, date);
                        response.errorCode = 0;
                        response.errorMessage = "Updated dateOfBirth!";
                        break;
                    }

                case "updateLanguages":
                    System.out.println("Received updateLanguages from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        TypeReference<ArrayList<Integer>> typeRef = new TypeReference<ArrayList<Integer>>() {
                        };
                        ArrayList<Integer> languages = mapper.readValue(requestData.get("languages"), typeRef);
                        Server.getDb().updateLanguages(client.userId, languages);
                        response.errorCode = 0;
                        response.errorMessage = "Updated languages!";
                        break;
                    }

                case "updateSex":
                    System.out.println("Received updateSex from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        String sex = requestData.get("sex").getTextValue();
                        Server.getDb().updateSex(client.userId, sex);
                        response.errorCode = 0;
                        response.errorMessage = "Updated sex!";
                        break;
                    }

                case "updateEmail":
                    System.out.println("Received updateEmail from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        String email = requestData.get("email").getTextValue();
                        Server.getDb().updateEmail(client.userId, email);
                        response.errorCode = 0;
                        response.errorMessage = "Updated email!";
                        break;
                    }

                case "updatePhoneNumber":
                    System.out.println("Received updatePhoneNumber from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        String phoneNumber = requestData.get("phoneNumber").getTextValue();
                        Server.getDb().updatePhoneNumber(client.userId, phoneNumber);
                        response.errorCode = 0;
                        response.errorMessage = "Updated phoneNumber!";
                        break;
                    }

                case "updateLocation":
                    System.out.println("Received updateLocation from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        double longitude = requestData.get("longitude").getDoubleValue(),
                                latitude = requestData.get("latitude").getDoubleValue();
                        Server.getDb().updateLocation(client.userId, longitude, latitude);
                        response.errorCode = 0;
                        response.errorMessage = "Updated location!";
                        break;
                    }

                case "updateUniversity":
                    System.out.println("Received updateUniversity from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        int universityId = requestData.get("university").getIntValue();
                        Server.getDb().updateUniversity(client.userId, universityId);
                        response.errorCode = 0;
                        response.errorMessage = "Updated university!";
                        break;
                    }

                case "updateStudy":
                    System.out.println("Received updateStudy from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        int studyId = requestData.get("study").getIntValue();
                        Server.getDb().updateStudy(client.userId, studyId);
                        response.errorCode = 0;
                        response.errorMessage = "Updated study!";
                        break;
                    }

                case "updateStudyYear":
                    System.out.println("Received updateStudyYear from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        int studyYear = requestData.get("studyYear").getIntValue();
                        Server.getDb().updateStudyYear(client.userId, studyYear);
                        response.errorCode = 0;
                        response.errorMessage = "Updated studyYear!";
                        break;
                    }

                case "updateLearning":
                    System.out.println("Received updateLearning from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        TypeReference<ArrayList<Integer>> typeRef = new TypeReference<ArrayList<Integer>>() {
                        };
                        ArrayList<Integer> learning = mapper.readValue(requestData.get("learning"), typeRef);
                        Server.getDb().updateLearning(client.userId, learning);
                        response.errorCode = 0;
                        response.errorMessage = "Updated learning!";
                        break;
                    }

                case "updateTeaching":
                    System.out.println("Received updateTeaching from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        TypeReference<ArrayList<Integer>> typeRef = new TypeReference<ArrayList<Integer>>() {
                        };
                        ArrayList<Integer> teaching = mapper.readValue(requestData.get("teaching"), typeRef);
                        Server.getDb().updateTeaching(client.userId, teaching);
                        response.errorCode = 0;
                        response.errorMessage = "Updated teaching!";
                        break;
                    }

                case "updateBuddies":
                    System.out.println("Received updateBuddies from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        TypeReference<ArrayList<Integer>> typeRef = new TypeReference<ArrayList<Integer>>() {
                        };
                        ArrayList<Integer> buddies = mapper.readValue(requestData.get("buddies"), typeRef);
                        Server.getDb().updateBuddies(client.userId, buddies);
                        response.errorCode = 0;
                        response.errorMessage = "Updated buddies!";
                        break;
                    }

                case "updateAvailability":
                    System.out.println("Received updateAvailability from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        AvailableTimes aTimes = mapper.readValue(requestData.get("availability"), AvailableTimes
                                .class);
                        Server.getDb().updateAvailability(client.userId, aTimes);
                        response.errorCode = 0;
                        response.errorMessage = "Updated availability!";
                        break;
                    }

                case "getBuddies":
                    System.out.println("Received getBuddies from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        ArrayList<User> buddies = Server.getDb().getBuddies(client.userId);
                        response.putData("buddies", buddies);
                        response.errorCode = 0;
                        response.errorMessage = "Retrieved your buddies.";
                        break;
                    }

                case "getStudents":
                    System.out.println("Received getStudents from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        ArrayList<User> students = Server.getDb().getStudents(client.userId);
                        response.putData("students", students);
                        response.errorCode = 0;
                        response.errorMessage = "Retrieved your students.";
                        break;
                    }

                case "getTutors":
                    System.out.println("Received getTutors from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        ArrayList<User> tutors = Server.getDb().getTutors(client.userId);
                        response.putData("tutors", tutors);
                        response.errorCode = 0;
                        response.errorMessage = "Retrieved your tutors.";
                        break;
                    }

                case "getChatMessage":
                    System.out.println("Received sendChatMessage from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else{
                        String chatmessage = mapper.readValue(requestData.get("chatMessage"), String.class);
                        int receiverId = mapper.readValue(requestData.get("receiverId"), Integer.class);
                        response.errorCode = 0;
                        response.errorMessage = "Retrieved your message.";
                        response.putData("chatMessage", chatmessage);
                        response.putData("senderId", client.userId);
                        client.sendChatMessage(mapper.writeValueAsString(response), receiverId);

                        break;
                    }

                default:
                    response = new Response(action);
                    response.errorMessage = "Unknown command.";
                    response.errorCode = 1;
                    break;
            }

            if(!action.equals("sendChatMessage")) {
                client.sendMessage(mapper.writeValueAsString(response));
            }

        } catch (JsonParseException e) {
            System.out.println("A client sent an invalid request (Could not parse JSON).");
            System.out.println(e.getLocalizedMessage());
        } catch (NullPointerException e) {
            System.out.println("A client sent a malformed request.");
            e.printStackTrace();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Something went wrong with a database operation.");
            e.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Something went wrong while reading a message from the network.");
            ex.printStackTrace();
        } //TODO catch all other exceptions and add to logfile
    }
}
