package communication;

import shared.AvailableTimes;
import shared.Response;
import shared.TimePeriod;
import shared.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * Created by Govert on 14-12-2015.
 */
public class Backend {
    private static Socket socket;
    private static boolean connected = false;
    public static int serverPort;
    public static String serverAddress;
    private static ListenThread listenThread;
    private static ArrayList<IMessageListener> messageListeners = new ArrayList<>();
    private static ArrayList<IDisconnectListener> disconnectListeners = new ArrayList<>();
    private static User selfUserObj;

    public static void addMessageListener(IMessageListener listener) {
        messageListeners.add(listener);
    }

    public static void addDisconnectListener(IDisconnectListener listener) {
        disconnectListeners.add(listener);
    }

    public static void removeAllListeners() {
        messageListeners.clear();
        disconnectListeners.clear();
    }

    public static void onResponse(Response response) {
        for (IMessageListener listener : messageListeners) {
            listener.onIncomingResponse(response);
        }
    }

    public static void onDisconnect(boolean erroneous) {
        for (IDisconnectListener listener : disconnectListeners) {
            listener.onDisconnect(erroneous);
        }
    }

    public static boolean connectToServer() {
        if (isConnected())
            return true;
        if (serverPort < 1 || serverAddress.equals(null))
            return false;
        try {
            socket = new Socket(serverAddress, serverPort);
            listenThread = new ListenThread(socket);
            listenThread.start();
            connected = true;
            System.out.println("Connected to server!");
            return true;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void closeConnection() throws IOException {
        if (isConnected()) {
            logout();
            listenThread.end();
            socket.close();
            connected = false;
        }
    }

    public static void login(String email, String password) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot login: Not connected!");
            return;
        }
        try {

            Request request = new Request("login");
            request.putData("email", email);
            request.putData("pass", password);

            listenThread.sendMessage(request.toSendableJSON());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void logout() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot logout: Not connected!");
            return;
        }

        try {
            Request request = new Request("logout");

            listenThread.sendMessage(request.toSendableJSON());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void register(String email, String password) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot register: Not connected!");
            return;
        }

        try {
            Request request = new Request("register");

            request.putData("email", email);
            request.putData("password", password);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void findStudyBuddy(String course) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot find buddy matches: Not connected!");
            return;
        }

        try {
            Request request = new Request("findMatch");

            request.putData("type", "buddy");
            request.putData("course", course);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findTutorMatch(String course) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot find tutor matches: Not connected!");
            return;
        }

        try {
            Request request = new Request("findMatch");

            request.putData("type", "learning");
            request.putData("course", course);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findEmergency(String course) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot find tutor matches: Not connected!");
            return;
        }

        try {
            Request request = new Request("findMatch");

            request.putData("type", "emergency");
            request.putData("course", course);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findBecomeTutorMatch(String course) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot find become tutor matches: Not connected!");
            return;
        }

        try {
            Request request = new Request("findMatch");

            request.putData("type", "teaching");
            request.putData("course", course);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getBuddies() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot getBuddies: Not connected!");
            return;
        }

        try {
            Request request = new Request("getBuddies");

            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getStudents() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot get Students: Not connected!");
            return;
        }

        try {
            Request request = new Request("getStudents");

            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getTutors() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot get Tutors: Not connected!");
            return;
        }

        try {
            Request request = new Request("getTutors");

            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void acceptMatch(int matchedUserId, String matchType, String matchCourse) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot accept match: Not connected!");
            return;
        }

        try {
            Request request = new Request("acceptMatch");

            request.putData("matchUser", matchedUserId);
            request.putData("matchType", matchType);
            request.putData("matchCourse", matchCourse);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Note, this get's a user from the database by ID
     * THIS IS NOT THE GETTER FOR THE USER OBJECT IN BACKEND
     */
    public static void getSelf() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot retrieve your information: Not connected!");
            return;
        }

        try {
            Request request = new Request("getSelf");
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getNationalities() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot get nationalities: Not connected!");
            return;
        }

        try {
            Request request = new Request("getNationalities");
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getLanguages() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot get languages: Not connected!");
            return;
        }

        try {
            Request request = new Request("getLanguages");
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getStudies() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot get studies: Not connected!");
            return;
        }

        try {
            Request request = new Request("getStudies");
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getUniversities() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot get nationalities: Not connected!");
            return;
        }

        try {
            Request request = new Request("getUniversities");
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getCourses() {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot get nationalities: Not connected!");
            return;
        }

        try {
            Request request = new Request("getCourses");
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateNationality(int id) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update nationality: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateNationality");
            request.putData("nationality", id);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateName(String text) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update name: Not connected!");
            return;
        }

        String[] name = text.split(" ", 2);

        try {
            Request request = new Request("updateName");
            request.putData("firstname", name[0]);
            request.putData("lastname", name[1]);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateSex(String sex) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update sex: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateSex");
            request.putData("sex", sex);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateDateOfBirth(LocalDate date) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update sex: Not connected!");
            return;
        }

        ZoneId zoneId = ZoneId.systemDefault();
        long epoch = date.atStartOfDay(zoneId).toEpochSecond()*1000;

        try {
            Request request = new Request("updateDateOfBirth");
            request.putData("date", epoch);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateLanguages(ArrayList<Integer> languages) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update languages: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateLanguages");
            request.putData("languages", languages);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateEmail(String email) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update email: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateEmail");
            request.putData("email", email);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateTelephoneNumber(String phoneNumber) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update phoneNumber: Not connected!");
            return;
        }

        try {
            Request request = new Request("updatePhoneNumber");
            request.putData("phoneNumber", phoneNumber);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateLocation(String location) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update location: Not connected!");
            return;
        }

        String arr[] = location.split(",");
        double longitude = Double.parseDouble(arr[0]),
                latitude = Double.parseDouble(arr[1]);

        if (longitude != selfUserObj.getLongitude() || latitude != selfUserObj.getLatitude()) {
            try {
                Request request = new Request("updateLocation");
                request.putData("longitude", longitude);
                request.putData("latitude", latitude);
                listenThread.sendMessage(request.toSendableJSON());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateUniversity(int id) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update university: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateUniversity");
            request.putData("university", id);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudy(int id) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update study: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateStudy");
            request.putData("study", id);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudyYear(int id) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update studyYear: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateStudyYear");
            request.putData("studyYear", id);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateLearning(ArrayList<Integer> learning) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update learning: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateLearning");
            request.putData("learning", learning);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateTeaching(ArrayList<Integer> teaching) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update teaching: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateTeaching");
            request.putData("teaching", teaching);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateBuddies(ArrayList<Integer> buddies) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update buddies: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateBuddies");
            request.putData("buddies", buddies);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAvailability(AvailableTimes aTimes) {
        if (!isConnected()) {
            System.out.println("[ERROR] Cannot update buddies: Not connected!");
            return;
        }

        try {
            Request request = new Request("updateAvailability");
            request.putData("availability", aTimes);
            listenThread.sendMessage(request.toSendableJSON());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the User object of Backend, representing the currently logged in user
     * @param self User object
     */
    public static void setSelfObject(User self) {
        selfUserObj = self;
    }

    /**
     * Getter for the User object representing the currently logged in user
     * @return User object
     */
    public static User getSelfObject() {
        return selfUserObj;
    }

    public static boolean isConnected() {
        return connected;
    }

    public static InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
}
