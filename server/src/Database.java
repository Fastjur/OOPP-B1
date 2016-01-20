import shared.AvailableTimes;
import shared.User;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * Main database class that defines the database functions
 * This class attempts to connect to the database and supplies functions to add, edit and delete users
 *
 * @author Jurriaan Den Toonder
 * @version 1.0
 */
public class Database {

    private static Connection connection;

    /**
     * Constructor of Database object
     * Gets the database connection from ConnectionManager
     *
     * @see ConnectionManager
     */
    public Database() {
    }

    protected Database(boolean IFUCKINGHATEJAVA) {
    }

    /**
     * Gets user from database by id
     *
     * @param id int, id from user
     * @return shared.User object if found, otherwise null
     * @throws SQLException
     * @throws IOException
     */
    public User getUser(int id) throws SQLException, IOException, ClassNotFoundException {
        User user;
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT users.id, nationalities.name AS nationality," +
                "studies.name AS study, universities.name AS university, email AS dbemail, passwd, firstname," +
                "lastname, sex, birthdate, bio, studyYear, availableDates, phonenumber, latitude, longitude, maxdist " +
                "FROM `users` LEFT JOIN nationalities ON users.nationality_id = nationalities.id " +
                "LEFT JOIN studies ON users.study = studies.id " +
                "LEFT JOIN universities ON users.university_id = universities.id WHERE users.id = ? LIMIT 1");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        user = processGetUser(rs);
        stmt.close();
        ConnectionManager.close();
        return user;
    }

    /**
     * Gets user from database by email
     *
     * @param email String, email from user
     * @return shared.User object if found, otherwise null
     * @throws SQLException
     * @throws IOException
     */
    public User getUser(String email) throws SQLException, IOException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT users.id FROM `users` " +
                "WHERE users.email = ? LIMIT 1");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        int id;
        if (rs.next()) {
            id = rs.getInt("id");
        } else {
            return null;
        }
        stmt.close();
        ConnectionManager.close();
        return getUser(id);
    }

    private User processGetUser(ResultSet rs) throws SQLException, IOException, ClassNotFoundException {
        //fixme: NullPointerExceptions on empty result from Database
        User usr;
        if (rs.next()) {
            int id = rs.getInt("id"),
                    studyYear = rs.getInt("studyYear");
            double latitude = rs.getDouble("latitude"),
                    longitude = rs.getDouble("longitude"),
                    maxdist = rs.getDouble("maxdist");
            String dbemail = rs.getString("dbemail"),
                    password = rs.getString("passwd"),
                    firstname = rs.getString("firstname"),
                    lastname = rs.getString("lastname"),
                    nationality = rs.getString("nationality"),
                    university = rs.getString("university"),
                    study = rs.getString("study"),
                    sex = rs.getString("sex"),
                    bio = rs.getString("bio"),
                    phonenumber = rs.getString("phonenumber"),
                    availabilityString = rs.getString("availableDates");
            Date birthdate = new Date(rs.getLong("birthdate"));

            /**
             * Start of NULL object checking block
             */
            if (firstname == null)
                firstname = "";

            if (lastname == null)
                lastname = "";

            if (nationality == null)
                nationality = "";

            if (university == null)
                university = "";

            if (study == null)
                study = "";

            if (sex == null)
                sex = "";

            if (bio == null)
                bio = "";

            if (phonenumber == null)
                phonenumber = "";

            AvailableTimes availability;
            if (availabilityString == null) {
                availability = new AvailableTimes();
            } else {
                availability = AvailableTimes.fromJson(availabilityString);
            }

            /**
             * Get buddy courses names
             */
            connection = ConnectionManager.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesSearchingBuddy ON courses_id = courses.id " +
                    "WHERE coursesSearchingBuddy.users_id = ?");
            stmt.setInt(1, id);
            ResultSet rs2 = stmt.executeQuery();
            ArrayList<String> coursesSearchingBuddy = new ArrayList<>();
            while (rs2.next()) {
                coursesSearchingBuddy.add(rs2.getString("name"));
            }
            stmt.close();

            /**
             * Get teaching courses names
             */
            stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesTeaching ON courses_id = courses.id " +
                    "WHERE coursesTeaching.users_id = ?");
            stmt.setInt(1, id);
            rs2 = stmt.executeQuery();
            ArrayList<String> coursesTeaching = new ArrayList<>();
            while (rs2.next()) {
                coursesTeaching.add(rs2.getString("name"));
            }
            stmt.close();

            /**
             * Get learning courses names
             */
            stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesLearning ON courses_id = courses.id " +
                    "WHERE coursesLearning.users_id = ?");
            stmt.setInt(1, id);
            rs2 = stmt.executeQuery();
            ArrayList<String> coursesLearning = new ArrayList<>();
            while (rs2.next()) {
                coursesLearning.add(rs2.getString("name"));
            }
            stmt.close();

            /**
             * Get spoken languages names
             */
            stmt = connection.prepareStatement("SELECT languages.name FROM languages " +
                    "LEFT JOIN users_has_languages ON languages.id = users_has_languages.languages_id " +
                    "WHERE users_has_languages.users_id = ?");
            stmt.setInt(1, id);
            rs2 = stmt.executeQuery();
            ArrayList<String> languages = new ArrayList<>();
            while (rs2.next()) {
                languages.add(rs2.getString("name"));
            }
            stmt.close();
            ConnectionManager.close();

            usr = new User(id, password, firstname, lastname, birthdate, dbemail, phonenumber, study, university,
                    studyYear, availability, coursesTeaching, coursesLearning, coursesSearchingBuddy, sex, nationality,
                    languages, bio, latitude, longitude, maxdist);
        } else {
            return null;
        }
        return usr;
    }

    /**
     * Adds a user to the database
     * This method checks the incoming user object and returns an IllegalArgumentException if one of its attributes is out of range or null
     *
     * @param email Users registration email
     * @param password Users registration password
     * @throws SQLException
     * @throws IllegalArgumentException
     * @see User
     */
    public void addUser(String email, String password) throws SQLException, IllegalArgumentException, ClassNotFoundException {
        if (email == null || password == null || email.equals("") || password.equals("")) {
            throw new IllegalArgumentException("Email or Password not given, aborting registration");
        }

        String hash;
        try {
            hash = PasswordHash.createHash(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Could not hash password!");
        }

        PreparedStatement stmt;
        ResultSet rs;
        connection = ConnectionManager.getConnection();

        /**
         * Insert user into database
         */
        stmt = connection.prepareStatement("INSERT INTO `users`(id, email, passwd) VALUES (NULL, ?, ?);", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, email);
        stmt.setString(2, hash);

        stmt.executeUpdate();
        rs = stmt.getGeneratedKeys();
        if (!rs.next()) {
            stmt.close();
            ConnectionManager.close();
            throw new IllegalArgumentException("[ERROR] Couldn't retrieve newly added users ID.\n" +
                    "    Presume database invalid");
        }
        stmt.close();
        ConnectionManager.close();
    }

    public void updateNationality(int userid, int nationalityId) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET nationality_id = ? WHERE id = ?");
        stmt.setInt(1, nationalityId);
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updateName(int userid, String firstname, String lastname) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET firstname = ?, lastname = ? WHERE id = ?");
        stmt.setString(1, firstname);
        stmt.setString(2, lastname);
        stmt.setInt(3, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updateSex(int userid, String sex) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET sex = ? WHERE id = ?");
        stmt.setString(1, sex);
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updateDateOfBirth(int userid, Long date) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET birthdate = ? WHERE id = ?");
        stmt.setLong(1, date);
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updateLanguages(int userid, ArrayList<Integer> languages) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM users_has_languages WHERE users_id = ?");
        stmt.setInt(1, userid);
        stmt.executeUpdate();

        stmt = connection.prepareStatement("INSERT INTO users_has_languages (users_id, languages_id) VALUES (?,?)");
        stmt.setInt(1, userid);
        for (Integer language : languages) {
            stmt.setInt(2, language);
            stmt.executeUpdate();
        }
        stmt.close();
        ConnectionManager.close();
    }

    public void updateEmail(int userid, String email) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET email = ? WHERE id = ?");
        stmt.setString(1, email);
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updatePhoneNumber(int userid, String phoneNumber) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET phonenumber = ? WHERE id = ?");
        stmt.setString(1, phoneNumber);
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updateLocation(int userid, double longitude, double latitude) throws SQLException,
            ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET longitude = ?, latitude = ? WHERE id = ?");
        stmt.setDouble(1, longitude);
        stmt.setDouble(2, latitude);
        stmt.setInt(3, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updateUniversity(int userid, int universityId) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET university_id = ? WHERE id = ?");
        stmt.setInt(1, universityId);
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updateStudy(int userid, int studyId) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET study = ? WHERE id = ?");
        stmt.setInt(1, studyId);
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updateStudyYear(int userid, int studyYear) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET studyYear = ? WHERE id = ?");
        stmt.setInt(1, studyYear);
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void updateLearning(int userid, ArrayList<Integer> learning) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM coursesLearning WHERE users_id = ?");
        stmt.setInt(1, userid);
        stmt.executeUpdate();

        if (learning.size() > 0) {
            stmt = connection.prepareStatement("INSERT INTO coursesLearning(users_id, courses_id) VALUES(?,?)");
            stmt.setInt(1, userid);
            for (int id : learning) {
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
        }
        stmt.close();
        ConnectionManager.close();
    }

    public void updateTeaching(int userid, ArrayList<Integer> teaching) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM coursesTeaching WHERE users_id = ?");
        stmt.setInt(1, userid);
        stmt.executeUpdate();

        if (teaching.size() > 0) {
            stmt = connection.prepareStatement("INSERT INTO coursesTeaching(users_id, courses_id) VALUES(?,?)");
            stmt.setInt(1, userid);
            for (int id : teaching) {
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
        }
        stmt.close();
        ConnectionManager.close();
    }

    public void updateBuddies(int userid, ArrayList<Integer> buddies) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM coursesSearchingBuddy WHERE users_id = ?");
        stmt.setInt(1, userid);
        stmt.executeUpdate();

        if (buddies.size() > 0) {
            stmt = connection.prepareStatement("INSERT INTO coursesSearchingBuddy(users_id, courses_id) VALUES(?,?)");
            stmt.setInt(1, userid);
            for (int id : buddies) {
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
        }
        stmt.close();
        ConnectionManager.close();
    }

    public void updateAvailability(int userid, AvailableTimes aTimes) throws SQLException, ClassNotFoundException, IOException {
        String aTimesJSON = aTimes.toJson();
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET availableDates = ? WHERE id = ?");
        stmt.setString(1, aTimesJSON);
        stmt.setInt(2, userid);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }


    public int getCourseIdByName(String name) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id FROM courses WHERE name=?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        int id;
        if(rs.next()) {
            id = rs.getInt("id");
        } else {
            id = -1;
        }
        stmt.close();
        return id;
    }

    public HashMap<Integer, String> getNationalities() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM nationalities");
        ResultSet rs = stmt.executeQuery();
        return rsToHashMap(rs);
    }

    public HashMap<Integer, String> getLanguages() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM languages");
        ResultSet rs = stmt.executeQuery();
        return rsToHashMap(rs);
    }

    public HashMap<Integer, String> getStudies() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM studies");
        ResultSet rs = stmt.executeQuery();
        return rsToHashMap(rs);
    }

    public HashMap<Integer, String> getUniversities() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM universities");
        ResultSet rs = stmt.executeQuery();
        return rsToHashMap(rs);
    }

    public HashMap<Integer, String> getCourses() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT id, name FROM courses");
        ResultSet rs = stmt.executeQuery();
        return rsToHashMap(rs);
    }

    private HashMap<Integer, String> rsToHashMap(ResultSet rs) throws SQLException {
        HashMap<Integer, String> res = new HashMap<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            res.put(id, name);
        }
        return res;
    }

    /**
     * Remove a user from the database using given id
     *
     * @param id int, remove user with this id
     * @throws SQLException
     */
    public void removeUser(int id) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM `users` WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    /**
     * Remove a user from the database using the given shared.User object id
     *
     * @param user remove user that has the id in this given object
     * @throws SQLException
     */
    public void removeUser(User user) throws SQLException, ClassNotFoundException {
        if (user == null) {
            throw new IllegalArgumentException("User object was null, cannot delete from database");
        }
        int id = user.getUserID();
        removeUser(id);
    }

    public ArrayList<User> findStudyBuddy(int self_id, String course) throws SQLException, ClassNotFoundException,
            IOException {
        int course_id = getCourseIdByName(course);
        User self = getUser(self_id);
        /*double maxDist = 999999999; //TODO, integrate
        double latitude = self.getLatitude(),
                longitude = self.getLongitude();
        String dist = "(((acos(sin((? * pi()/180)) * sin((users.latitude * pi()/180))+cos((? * pi()/180)) * " +
                "cos((users.latitude * pi()/180)) * cos(((?-users.longitude) * pi()/180)))) * 180/pi()) * 60 * " +
                "1.1515 ) AS distance",*/
        String query = "SELECT `users`.id FROM `users` " +
                       "  JOIN `coursesSearchingBuddy` AS buddy ON `users`.id = buddy.users_id " +
                       "  JOIN `users_has_matches` AS hasmatches ON `users`.id = hasmatches.users_id " +
                       "  JOIN `matches` ON matches.id = hasmatches.matches_id " +
                       "WHERE `users`.id <> ?" +
                       "  AND buddy.courses_id = ? " +
                       "  AND matches.match_type = ?" +
                       "  AND `users`.id <> matches.matched_user_id ";
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        /*stmt.setDouble(1, latitude);
        stmt.setDouble(2, latitude); //No, that is not a typo
        stmt.setDouble(3, longitude);*/
        stmt.setInt(1, self_id);
        stmt.setInt(2, course_id);
        stmt.setString(3, "buddy");
        ResultSet rs = stmt.executeQuery();

        ArrayList<User> res = processMatches(rs, self);
        stmt.close();
        ConnectionManager.close();

        return res;
    }

    public ArrayList<User> findTutor(int self_id, String course) throws SQLException, ClassNotFoundException,
            IOException {
        int course_id = getCourseIdByName(course);
        User self = getUser(self_id);
        String query = "SELECT `users`.id FROM `users` " +
                       "  JOIN `coursesTeaching` AS buddy ON `users`.id = buddy.users_id " +
                       "  JOIN `users_has_matches` AS hasmatches ON `users`.id = hasmatches.users_id " +
                       "  JOIN `matches` ON matches.id = hasmatches.matches_id " +
                       "WHERE `users`.id <> ?" +
                       "  AND buddy.courses_id = ? " +
                       "  AND matches.match_type = ?" +
                       "  AND `users`.id <> matches.matched_user_id";
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, self_id);
        stmt.setInt(2, course_id);
        stmt.setString(3, "teaching");
        ResultSet rs = stmt.executeQuery();

        ArrayList<User> res = processMatches(rs, self);
        stmt.close();
        ConnectionManager.close();

        return res;
    }

    public ArrayList<User> findStudent(int self_id, String course) throws SQLException, ClassNotFoundException,
            IOException {
        int course_id = getCourseIdByName(course);
        User self = getUser(self_id);
        String query = "SELECT `users`.id FROM `users` " +
                       "  JOIN `coursesLearning` AS buddy ON `users`.id = buddy.users_id " +
                       "  JOIN `users_has_matches` AS hasmatches ON `users`.id = hasmatches.users_id " +
                       "  JOIN `matches` ON matches.id = hasmatches.matches_id " +
                       "WHERE `users`.id <> ?" +
                       "  AND buddy.courses_id = ? " +
                       "  AND matches.match_type = ?" +
                       "  AND `users`.id <> matches.matched_user_id";
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, self_id);
        stmt.setInt(2, course_id);
        stmt.setString(3, "learning");
        ResultSet rs = stmt.executeQuery();

        ArrayList<User> res = processMatches(rs, self);
        stmt.close();
        ConnectionManager.close();

        return res;
    }

    private ArrayList<User> processMatches(ResultSet rs, User self) throws SQLException, IOException,
            ClassNotFoundException {
        ArrayList<Integer> user_ids = new ArrayList<>();
        ArrayList<User> res = new ArrayList<>();
        while (rs.next()) {
            user_ids.add(rs.getInt("id"));
        }
        if (user_ids.size() == 0) {
            return res;
        }
        for (int id : user_ids) {
            res.add(getUser(id));
        }
        ListIterator<User> it = res.listIterator();
        while (it.hasNext()) {
            User user = it.next();
            if (listIntersection(self.getLanguageList(), user.getLanguageList()).size() == 0) {
                it.remove();
            }
        }
        it = res.listIterator();
        while (it.hasNext()) {
            User user = it.next();
            if (self.getAvailableDates().intersect(user.getAvailableDates()).size() == 0) {
                it.remove();
            }
        }
        return res;
    }

    /**
     * Adds a match to the database when a user accepts it
     * @param self_id ID of the user itself
     * @param matchedUserId ID of the user to whom 'self_id' matched
     * @param type Type of match, should be 'learning', 'teaching' or 'buddy'
     * @throws SQLException
     */
    public void acceptMatch(int self_id, int matchedUserId, String type, String course) throws SQLException, ClassNotFoundException {
        int courseId = getCourseIdByName(course);
        if (courseId == -1)
            throw new IllegalArgumentException("[ERROR] Couldn't find course ID by name!");
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO `matches`(id, matched_user_id, match_type, " +
                "courses_id) VALUES(NULL,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ResultSet rs;
        stmt.setInt(1, matchedUserId);
        stmt.setString(2, type);
        stmt.setInt(3, courseId);
        stmt.executeUpdate();
        rs = stmt.getGeneratedKeys();
        int matchId;
        if (rs.next()) {
            matchId = rs.getInt(1);
        } else {
            throw new IllegalArgumentException("[ERROR] Couldn't retrieve newly added match ID.\n" +
                    "    Presume database invalid");
        }
        stmt.close();

        stmt = connection.prepareStatement("INSERT INTO `users_has_matches`(users_id, matches_id) VALUES(?,?)");
        stmt.setInt(1, self_id);
        stmt.setInt(2, matchId);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    /**
     * Removes a match from the database, cascades automatically in the other tables.
     * @param self The id of the user itself, to prevent someone deleting another users matches
     * @param matchId The ID of the match to be deleted
     * @throws SQLException
     */
    public void removeMatch(int self, int matchId) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE matches FROM matches INNER JOIN users_has_matches " +
                "ON matches_id = matches.id WHERE users_id = ? AND matches.id = ?");
        stmt.setInt(1, self);
        stmt.setInt(2, matchId);
        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();
    }

    public void getCompleteMatches(int self) throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT\n" +
                "  matches.matched_user_id AS matched_user,\n" +
                "  matches.courses_id      AS matched_course\n" +
                "FROM users u2\n" +
                "  JOIN users_has_matches ON u2.id = users_has_matches.users_id\n" +
                "  JOIN matches ON users_has_matches.matches_id = matches.id\n" +
                "WHERE u2.id = ?\n" +
                "      AND matches.match_type = 'buddy'");
        stmt.setInt(1, self);

        ResultSet rs = stmt.executeQuery();
        ArrayList<ArrayList<Integer>> preMatches = new ArrayList<>();
        while (rs.next()) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(rs.getInt("matched_user"));
            temp.add(rs.getInt("matched_course"));
            preMatches.add(temp);
        }
        //todo finish
    }

    /**
     * Returns the intersection between two ArrayLists, thus only elements that are in both lists
     *
     * @param list1 ArrayList 1
     * @param list2 ArrayList 2
     * @param <T>   Type specifier
     * @return ArrayList with intersections between lists
     */
    private <T> ArrayList<T> listIntersection(ArrayList<T> list1, ArrayList<T> list2) {
        ArrayList<T> list = new ArrayList<>();

        for (T t : list1) {
            if (list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }

    /**
     * Attempt to close the database connection
     */
    public void close() {
        ConnectionManager.close();
    }

    public ArrayList<User> getBuddies(int userId) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<User> res;
        connection = ConnectionManager.getConnection();
        String query = "SELECT u.id FROM `users` AS u" +
                       "  JOIN users_has_matches AS hm ON hm.users_id = u.id" +
                       "  JOIN matches AS m ON m.id = hm.matches_id " +
                       "WHERE u.id = ?" +
                       "  AND m.match_type = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setString(2, "buddy");
        ResultSet rs = stmt.executeQuery();
        res = processGetMatches(rs);
        System.out.println(res);
        return res;
    }

    public ArrayList<User> getStudents(int userId) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<User> res;
        connection = ConnectionManager.getConnection();
        String query = "SELECT u.id FROM `users` AS u" +
                "  JOIN users_has_matches AS hm ON hm.users_id = u.id" +
                "  JOIN matches AS m ON m.id = hm.matches_id " +
                "WHERE u.id = ?" +
                "  AND m.match_type = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setString(2, "teaching");
        ResultSet rs = stmt.executeQuery();
        res = processGetMatches(rs);
        System.out.println(res);
        return res;
    }

    public ArrayList<User> getTutors(int userId) throws SQLException, ClassNotFoundException, IOException {
        ArrayList<User> res;
        connection = ConnectionManager.getConnection();
        String query = "SELECT u.id FROM `users` AS u" +
                "  JOIN users_has_matches AS hm ON hm.users_id = u.id" +
                "  JOIN matches AS m ON m.id = hm.matches_id " +
                "WHERE u.id = ?" +
                "  AND m.match_type = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, userId);
        stmt.setString(2, "learning");
        ResultSet rs = stmt.executeQuery();
        res = processGetMatches(rs);
        System.out.println(res);
        return res;
    }

    private ArrayList<User> processGetMatches(ResultSet rs) throws SQLException, IOException, ClassNotFoundException {
        ArrayList<User> res = new ArrayList<>();
        while (rs.next()) {
            User temp = this.getUser(rs.getInt("id"));
            res.add(temp);
        }
        return res;
    }
}
