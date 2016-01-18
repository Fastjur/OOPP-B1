import shared.AvailableTimes;
import shared.User;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
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
            throw new IllegalArgumentException("[ERROR] Couldn't retrieve newly added users ID.\n" +
                    "    Presume database invalid");
        }
        stmt.close();
        ConnectionManager.close();
    }

    /**
     * Update a user in the database. According to the ID in the given user object
     *
     * @param user shared.User object
     * @throws IllegalArgumentException
     * @throws SQLException
     * @throws IOException
     */
    public void updateUser(User user) throws IllegalArgumentException, SQLException, IOException, ClassNotFoundException {
        if (user == null) {
            throw new IllegalArgumentException("[ERROR] User object was null, cannot add to database");
        }

        PreparedStatement stmt;
        ResultSet rs;

        /**
         * Get nationality id
         */
        connection = ConnectionManager.getConnection();
        stmt = connection.prepareStatement("SELECT id FROM nationalities WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getNationality());
        rs = stmt.executeQuery();
        int nationality_id;
        if (rs.next()) {
            nationality_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("[ERROR] Could not get nationality id, aborting update to database\n    " +
                    "Argument: (name:\"" + user.getNationality() + "\")");
        }
        stmt.close();

        /**
         * Get university id
         */
        stmt = connection.prepareStatement("SELECT id FROM universities WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getUniversity());
        rs = stmt.executeQuery();
        int university_id;
        if (rs.next()) {
            university_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("[ERROR] Could not get university id, aborting update to database\n    " +
                    "Argument: (name:\"" + user.getUniversity() + "\")");
        }
        stmt.close();

        /**
         * Get study id
         */
        stmt = connection.prepareStatement("SELECT id FROM studies WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getStudy());
        rs = stmt.executeQuery();
        int study_id;
        if (rs.next()) {
            study_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("[ERROR] Could not get study id, aborting update to database\n    " +
                    "Argument: (name:\"" + user.getStudy() + "\")");
        }
        stmt.close();

        /**
         * Finally fucking update the user
         */
        stmt = connection.prepareStatement("UPDATE `users` SET nationality_id=?, university_id=?, " +
                "email=?, passwd=?, firstname=?, lastname=?, sex=?, birthdate=?, study=?, bio=?, studyYear=?, " +
                "availableDates=?, phonenumber=?, latitude=?, longitude=? " +
                "WHERE id = ?");
        stmt.setInt(1, nationality_id);
        stmt.setInt(2, university_id);
        stmt.setString(3, user.getMail());
        stmt.setString(4, user.getPassword());
        stmt.setString(5, user.getFirstname());
        stmt.setString(6, user.getLastname());
        stmt.setString(7, user.getGender());
        stmt.setLong(8, user.getBirthday().getTime());
        stmt.setInt(9, study_id);
        stmt.setString(10, user.getDescription());
        stmt.setInt(11, user.getStudyYear());
        stmt.setString(12, user.getAvailableDates().toJson());
        stmt.setString(13, user.getPhonenumber());
        stmt.setDouble(14, user.getLatitude());
        stmt.setDouble(15, user.getLongitude());
        stmt.setInt(16, user.getUserID());

        stmt.executeUpdate();
        stmt.close();
        ConnectionManager.close();

        /**
         * Update languages and courses
         */
        updateDbLanguages(user);
        updateDbCourses(user);

    }

    private void updateDbLanguages(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt;
        ResultSet rs;

        connection = ConnectionManager.getConnection();
        stmt = connection.prepareStatement("DELETE FROM `users_has_languages` WHERE users_id = ?");
        stmt.setInt(1, user.getUserID());
        stmt.executeUpdate();
        stmt.close();

        if (user.getLanguageList().size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < user.getLanguageList().size(); i++) {
                builder.append("name = ? OR ");
            }
            builder.setLength(builder.length() - 4);
            String query = "SELECT languages.id FROM languages WHERE " + builder.toString();
            stmt = connection.prepareStatement(query);
            int index = 1;
            for (String lang : user.getLanguageList()) {
                stmt.setString(index, lang);
                index++;
            }
            rs = stmt.executeQuery();
            ArrayList<Integer> languageIds = new ArrayList<>();
            while (rs.next()) {
                languageIds.add(rs.getInt("id"));
            }
            stmt.close();

            stmt = connection.prepareStatement("INSERT INTO `users_has_languages`(users_id, languages_id) " +
                    "VALUES(?,?)");
            stmt.setInt(1, user.getUserID());
            for (int lang : languageIds) {
                stmt.setInt(2, lang);
                stmt.executeUpdate();
            }
            stmt.close();
        }
        ConnectionManager.close();
    }

    private void updateDbCourses(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt;
        ResultSet rs;
        StringBuilder builder;
        String query;

        /**
         * Delete all residual course entries
         */
        connection = ConnectionManager.getConnection();
        stmt = connection.prepareStatement("DELETE FROM `coursesLearning` WHERE users_id = ?;");
        stmt.setInt(1, user.getUserID());
        stmt.executeUpdate();
        stmt.close();
        stmt = connection.prepareStatement("DELETE FROM `coursesTeaching` WHERE users_id = ?;");
        stmt.setInt(1, user.getUserID());
        stmt.executeUpdate();
        stmt.close();
        stmt = connection.prepareStatement("DELETE FROM `coursesSearchingBuddy` WHERE users_id = ?;");
        stmt.setInt(1, user.getUserID());
        stmt.executeUpdate();
        stmt.close();

        /**
         * Get the course id's the user is learning and add them to the database
         */
        builder = new StringBuilder();
        for (int i = 0; i < user.getCoursesLearningList().size(); i++) {
            builder.append("name = ? OR ");
        }
        builder.setLength(builder.length() - 4);
        query = "SELECT id FROM courses WHERE " + builder.toString();
        stmt = connection.prepareStatement(query);
        int index = 1;
        for (String learning : user.getCoursesLearningList()) {
            stmt.setString(index, learning);
            index++;
        }
        rs = stmt.executeQuery();
        ArrayList<Integer> learningIds = new ArrayList<>();
        while (rs.next()) {
            learningIds.add(rs.getInt("id"));
        }
        stmt.close();
        stmt = connection.prepareStatement("INSERT INTO `coursesLearning`(users_id, courses_id) VALUES(?,?)");
        stmt.setInt(1, user.getUserID());
        for (int id : learningIds) {
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        stmt.close();

        /**
         * Get the course id's the user is teaching and add them to the database
         */
        builder = new StringBuilder();
        for (int i = 0; i < user.getCoursesTeachingList().size(); i++) {
            builder.append("name = ? OR ");
        }
        builder.setLength(builder.length() - 4);
        query = "SELECT id FROM courses WHERE " + builder.toString();
        stmt = connection.prepareStatement(query);
        index = 1;
        for (String teaching : user.getCoursesTeachingList()) {
            stmt.setString(index, teaching);
            index++;
        }
        rs = stmt.executeQuery();
        ArrayList<Integer> teachingIds = new ArrayList<>();
        while (rs.next()) {
            teachingIds.add(rs.getInt("id"));
        }
        stmt.close();
        stmt = connection.prepareStatement("INSERT INTO `coursesTeaching`(users_id, courses_id) VALUES(?,?)");
        stmt.setInt(1, user.getUserID());
        for (int id : teachingIds) {
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
        stmt.close();

        /**
         * Get the course id's the user is searching a buddy for and add them to the database
         */
        builder = new StringBuilder();
        for (int i = 0; i < user.getBuddyList().size(); i++) {
            builder.append("name = ? OR ");
        }
        builder.setLength(builder.length() - 4);
        query = "SELECT id FROM courses WHERE " + builder.toString();
        stmt = connection.prepareStatement(query);
        index = 1;
        for (String buddy : user.getBuddyList()) {
            stmt.setString(index, buddy);
            index++;
        }
        rs = stmt.executeQuery();
        ArrayList<Integer> buddyIds = new ArrayList<>();
        while (rs.next()) {
            buddyIds.add(rs.getInt("id"));
        }
        stmt.close();
        stmt = connection.prepareStatement("INSERT INTO `coursesSearchingBuddy`(users_id, courses_id) VALUES(?,?)");
        stmt.setInt(1, user.getUserID());
        for (int id : buddyIds) {
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
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

    /**
     * Used to get matching users using given parameters
     *
     * @param node JSON node containing the search parameters
     * @return ArrayList containing 3 ArrayLists, the first is an ArrayList containing users the requesting user can
     * teach, the second who he can learn from, the third who he can buddy up with.
     * @throws SQLException
     */
    public ArrayList<ArrayList<User>> getMatches(int self_id, JsonNode node) throws SQLException, IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode self = mapper.readTree(node.get("requestData").asText()).get("self");
        System.out.println(self);
        double maxDist = self.get("maxDistance").getDoubleValue(),
                latitude = self.get("latitude").getDoubleValue(),
                longitude = self.get("longitude").getDoubleValue();
        AvailableTimes aTimes = mapper.readValue(self.get("availableDates"), AvailableTimes.class);
        ArrayList learning = mapper.readValue(self.get("coursesLearningList"), ArrayList.class),
                teaching = mapper.readValue(self.get("coursesTeachingList"), ArrayList.class),
                buddys = mapper.readValue(self.get("buddyList"), ArrayList.class),
                languages = mapper.readValue(self.get("languageList"), ArrayList.class);
        String where = "WHERE users.id <> ?",
                dist = "(((acos(sin((? * pi()/180)) * sin((users.latitude * pi()/180))+cos((? * pi()/180)) * cos((users.latitude * pi()/180)) * cos(((?-users.longitude) * pi()/180)))) * 180/pi()) * 60 * 1.1515 ) as distance",
                query = "SELECT users.id, nationalities.name AS nationality, universities.name AS university, email, passwd, firstname, lastname, sex, birthdate, studies.name AS study, bio, studyYear, availableDates, phonenumber, latitude, longitude, " +
                        dist + " FROM `users` " +
                        "LEFT JOIN nationalities ON users.nationality_id = nationalities.id " +
                        "LEFT JOIN universities ON users.university_id = universities.id " +
                        "LEFT JOIN studies ON users.study = studies.id " + where + " " +
                        "HAVING distance <= ?";
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setDouble(1, latitude);
        stmt.setDouble(2, latitude);
        stmt.setDouble(3, longitude);
        stmt.setInt(4, self_id);
        stmt.setDouble(5, maxDist);
        ResultSet rs = stmt.executeQuery();
        ArrayList<User> matches = new ArrayList<>(),
                canTeach = new ArrayList<>(),
                canLearn = new ArrayList<>(),
                canBuddyUp = new ArrayList<>();
        while (rs.next()) {
            User user = getUser(rs.getInt("id"));
            user.setPassword("");
            matches.add(user);
        }
        stmt.close();
        ConnectionManager.close();
        ListIterator<User> it = matches.listIterator();
        int index = 0;
        while (it.hasNext()) {
            User user = it.next();
            if (listIntersection(user.getLanguageList(), languages).size() == 0 || aTimes.intersect(user.getAvailableDates()).size() == 0) {
                matches.remove(index);
            }
            index++;
        }
        if (matches.size() == 0) {
            return null;
        }
        canTeach.addAll(matches.stream().filter(user -> listIntersection(user.getCoursesLearningList(), teaching).size() > 0).collect(Collectors.toList()));
        canLearn.addAll(matches.stream().filter(user -> listIntersection(user.getCoursesTeachingList(), learning).size() > 0).collect(Collectors.toList()));
        canBuddyUp.addAll(matches.stream().filter(user -> listIntersection(user.getBuddyList(), buddys).size() > 0).collect(Collectors.toList()));

        ArrayList<ArrayList<User>> total = new ArrayList<>();
        total.add(canTeach);
        total.add(canLearn);
        total.add(canBuddyUp);

        System.out.println(total);

        return total;
    }

    /**
     * Adds a match to the database when a user accepts it
     * @param self ID of the user itself
     * @param matchedUserId ID of the user to whom 'self' matched
     * @param type Type of match, should be 'learning', 'teaching' or 'buddy'
     * @throws SQLException
     */
    public void acceptMatch(int self, int matchedUserId, String type, String course) throws SQLException, ClassNotFoundException {
        if (course.equals("")) {
            course = "NONE";
        }
        int courseId = getCourseIdByName(course);
        if (courseId == -1)
            throw new IllegalArgumentException("[ERROR] Couldn't find course ID by name!");
        connection = ConnectionManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO `matches`(id, matched_user_id, match_type, " +
                "courses_id) VALUES(NULL,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ResultSet rs;
        stmt.setInt(1, matchedUserId);
        stmt.setString(2, type);
        if (courseId == 0) {
            stmt.setNull(3, Types.NULL);
        } else {
            stmt.setInt(3, courseId);
        }
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
        stmt.setInt(1, self);
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
}
