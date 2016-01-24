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
                                try {
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
                                } catch (SQLException | ClassNotFoundException e) {
                                    response.errorCode = 1;
                                    response.errorMessage = "Couldn't find buddys generic error";
                                    e.printStackTrace();
                                    break;
                                }
                            }
                            case "learning": {
                                response = new Response("findBuddy");
                                String course = requestData.get("course").getTextValue();
                                try {
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
                                } catch (SQLException | ClassNotFoundException e) {
                                    response.errorCode = 1;
                                    response.errorMessage = "Couldn't find tutor: generic error";
                                    e.printStackTrace();
                                    break;
                                }
                            }
                            case "teaching": {
                                response = new Response("findBuddy");
                                String course = requestData.get("course").getTextValue();
                                try {
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
                                } catch (SQLException | ClassNotFoundException e) {
                                    response.errorCode = 1;
                                    response.errorMessage = "Couldn't find student: generic error";
                                    e.printStackTrace();
                                    break;
                                }
                            }
                            case "emergency": {
                                response = new Response("findBuddy");
                                String course = requestData.get("course").getTextValue();
                                try {
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
                                } catch (SQLException | ClassNotFoundException e) {
                                    response.errorCode = 1;
                                    response.errorMessage = "Couldn't find emergency tutor: generic error";
                                    e.printStackTrace();
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
                            try {
                                Server.getDb().acceptMatch(client.userId, matchUserId, matchType, course);
                                response.errorMessage = "Added match to database!";
                                response.errorCode = 0;
                                break;
                            } catch (ClassNotFoundException | SQLException e) {
                                e.printStackTrace();
                                response.errorCode = 5;
                                response.errorMessage = "Couldn't add match to database!";
                                break;
                            }
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
                        try {
                            Server.getDb().removeMatch(self, matchId);
                            response.errorCode = 0;
                            response.errorMessage = "Removed match from database!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            response.errorCode = 6;
                            response.errorMessage = "Couldn't remove match from database";
                            break;
                        }
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
                        try {
                            User dbSelf = Server.getDb().getUser(self);
                            response.putData("self", dbSelf);
                            response.errorMessage = "Retrieved your information!";
                            response.errorCode = 0;
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            response.errorCode = 7;
                            response.errorMessage = "Couldn't retrieve your information!";
                            break;
                        }
                    }

                case "getNationalities":
                    System.out.println("Received getNationalities from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        try {
                            HashMap<Integer, String> dbNationalities = Server.getDb().getNationalities();
                            response.putData("nationalities", dbNationalities);
                            response.errorCode = 0;
                            response.errorMessage = "Retreived database nationalities!";
                            break;
                        } catch (SQLException | ClassNotFoundException e) {
                            response.errorCode = 8;
                            response.errorMessage = "Couldn't get database nationalities!";
                            e.printStackTrace();
                            break;
                        }
                    }

                case "getLanguages":
                    System.out.println("Received getLanguages from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        try {
                            HashMap<Integer, String> dbLanguages = Server.getDb().getLanguages();
                            response.putData("dbLanguages", dbLanguages);
                            response.errorCode = 0;
                            response.errorMessage = "Retreived database languages!";
                            break;
                        } catch (SQLException | ClassNotFoundException e) {
                            response.errorCode = 8;
                            response.errorMessage = "Couldn't get database languages!";
                            e.printStackTrace();
                            break;
                        }
                    }

                case "getStudies":
                    System.out.println("Received getStudies from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        try {
                            HashMap<Integer, String> dbStudies = Server.getDb().getStudies();
                            response.putData("dbStudies", dbStudies);
                            response.errorCode = 0;
                            response.errorMessage = "Retreived database studies!";
                            break;
                        } catch (SQLException | ClassNotFoundException e) {
                            response.errorCode = 8;
                            response.errorMessage = "Couldn't get database studies!";
                            e.printStackTrace();
                            break;
                        }
                    }

                case "getUniversities":
                    System.out.println("Received getUniversities from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        try {
                            HashMap<Integer, String> dbUniversities = Server.getDb().getUniversities();
                            response.putData("dbUniversities", dbUniversities);
                            response.errorCode = 0;
                            response.errorMessage = "Retreived database universities!";
                            break;
                        } catch (SQLException | ClassNotFoundException e) {
                            response.errorCode = 8;
                            response.errorMessage = "Couldn't get database universities!";
                            e.printStackTrace();
                            break;
                        }
                    }

                case "getCourses":
                    System.out.println("Received getCourses from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        try {
                            HashMap<Integer, String> dbCourses = Server.getDb().getCourses();
                            response.putData("dbCourses", dbCourses);
                            response.errorCode = 0;
                            response.errorMessage = "Retreived database courses!";
                            break;
                        } catch (SQLException | ClassNotFoundException e) {
                            response.errorCode = 8;
                            response.errorMessage = "Couldn't get database courses!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateNationality(client.userId, nationalityId);
                            response.errorCode = 0;
                            response.errorMessage = "Updated nationality!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update nationality!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateName(client.userId, firstname, lastname);
                            response.errorCode = 0;
                            response.errorMessage = "Updated name!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update name!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateDateOfBirth(client.userId, date);
                            response.errorCode = 0;
                            response.errorMessage = "Updated dateOfBirth!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update dateOfBirth!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateLanguages(client.userId, languages);
                            response.errorCode = 0;
                            response.errorMessage = "Updated languages!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update languages!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateSex(client.userId, sex);
                            response.errorCode = 0;
                            response.errorMessage = "Updated sex!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update sex!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateEmail(client.userId, email);
                            response.errorCode = 0;
                            response.errorMessage = "Updated email!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update email!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updatePhoneNumber(client.userId, phoneNumber);
                            response.errorCode = 0;
                            response.errorMessage = "Updated phoneNumber!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update phoneNumber!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateLocation(client.userId, longitude, latitude);
                            response.errorCode = 0;
                            response.errorMessage = "Updated location!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update location!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateUniversity(client.userId, universityId);
                            response.errorCode = 0;
                            response.errorMessage = "Updated university!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update university!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateStudy(client.userId, studyId);
                            response.errorCode = 0;
                            response.errorMessage = "Updated study!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update study!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateStudyYear(client.userId, studyYear);
                            response.errorCode = 0;
                            response.errorMessage = "Updated studyYear!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update studyYear!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateLearning(client.userId, learning);
                            response.errorCode = 0;
                            response.errorMessage = "Updated learning!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update learning!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateTeaching(client.userId, teaching);
                            response.errorCode = 0;
                            response.errorMessage = "Updated teaching!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update teaching!";
                            e.printStackTrace();
                            break;
                        }
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
                        try {
                            Server.getDb().updateBuddies(client.userId, buddies);
                            response.errorCode = 0;
                            response.errorMessage = "Updated buddies!";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update buddies!";
                            e.printStackTrace();
                            break;
                        }
                    }

                case "updateAvailability":
                    System.out.println("Received updateAvailability from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        TypeReference<ArrayList<Integer>> typeRef = new TypeReference<ArrayList<Integer>>() {
                        };
                        AvailableTimes aTimes = mapper.readValue(requestData.get("availability"), AvailableTimes
                                .class);
                        try {
                            Server.getDb().updateAvailability(client.userId, aTimes);
                            response.errorCode = 0;
                            response.errorMessage = "Updated availability!";
                            break;
                        } catch (ClassNotFoundException | SQLException | IOException e) {
                            response.errorCode = 9;
                            response.errorMessage = "Couldn't update availability!";
                            e.printStackTrace();
                            break;
                        }
                    }

                case "getBuddies":
                    System.out.println("Received getBuddies from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        try {
                            ArrayList<User> buddies = Server.getDb().getBuddies(client.userId);
                            response.putData("buddies", buddies);
                            response.errorCode = 0;
                            response.errorMessage = "Retrieved your buddies.";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            response.errorCode = 1;
                            response.errorMessage = "Couldn't retrieve your buddies!";
                        }
                    }

                case "getStudents":
                    System.out.println("Received getStudents from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        try {
                            ArrayList<User> students = Server.getDb().getStudents(client.userId);
                            response.putData("students", students);
                            response.errorCode = 0;
                            response.errorMessage = "Retrieved your students.";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            response.errorCode = 1;
                            response.errorMessage = "Couldn't retrieve your students!";
                        }
                    }

                case "getTutors":
                    System.out.println("Received getTutors from userid: " + client.userId);
                    response = new Response(action);
                    if (client.userId == -1) {
                        response.errorMessage = "You are not logged in!";
                        response.errorCode = 2;
                        break;
                    } else {
                        try {
                            ArrayList<User> tutors = Server.getDb().getTutors(client.userId);
                            response.putData("tutors", tutors);
                            response.errorCode = 0;
                            response.errorMessage = "Retrieved your tutors.";
                            break;
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            response.errorCode = 1;
                            response.errorMessage = "Couldn't retrieve your tutors!";
                        }
                    }

                default:
                    response = new Response(action);
                    response.errorMessage = "Unknown command.";
                    response.errorCode = 1;
                    break;
            }

            client.sendMessage(mapper.writeValueAsString(response));

        } catch (JsonParseException e) {
            System.out.println("A client sent an invalid request (Could not parse JSON).");
            System.out.println(e.getLocalizedMessage());
        } catch (NullPointerException e) {
            System.out.println("A client sent a malformed request.");
            e.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Something went wrong while reading a message from the network.");
            ex.printStackTrace();
        } //TODO catch all other exceptions and add to logfile
    }
}
