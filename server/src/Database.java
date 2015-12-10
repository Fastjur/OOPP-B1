import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

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
    public Database() throws IllegalStateException, SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
    }

    protected Database(boolean IFUCKINGHATEJAVA) {}

    /**
     * Gets user from database by id
     *
     * @param id int, id from user
     * @return User object if found, otherwise null
     * @throws SQLException
     * @throws IOException
     */
    public User getUser(int id) throws SQLException, IOException {
        //TODO: Check for null values returned by database and generate appropriate exceptions
        User user;
        PreparedStatement stmt = connection.prepareStatement("SELECT users.id, nationalities.name AS nationality," +
                "studies.name AS study, universities.name AS university, email AS dbemail, passwd, firstname," +
                "lastname, sex, birthdate, bio, studyYear, availableDates, phonenumber, latitude, longitude " +
                "FROM `users` LEFT JOIN nationalities ON users.nationality_id = nationalities.id " +
                "LEFT JOIN studies ON users.study = studies.id " +
                "LEFT JOIN universities ON users.university_id = universities.id WHERE users.id = ? LIMIT 1");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        user = processGetUser(rs);
        stmt.close();
        return user;
    }

    /**
     * Gets user from database by email
     *
     * @param email String, email from user
     * @return User object if found, otherwise null
     * @throws SQLException
     * @throws IOException
     */
    public User getUser(String email) throws SQLException, IOException {
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
        return getUser(id);
    }

    private User processGetUser(ResultSet rs) throws SQLException, IOException {
        //fixme: NullPointerExceptions on empty result from Database
        User usr = new User();
        if (rs.next()) {
            int id = rs.getInt("id"),
                    studyYear = rs.getInt("studyYear");
            double latitude = rs.getDouble("latitude"),
                    longitude = rs.getDouble("longitude");
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
            AvailableTimes availability = AvailableTimes.fromJson(availabilityString);

            /**
             * Get buddy courses names
             */
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

            usr = new User(id, password, firstname, lastname, birthdate, dbemail, phonenumber, study, university,
                    studyYear, availability, coursesTeaching, coursesLearning, coursesSearchingBuddy, sex, nationality,
                    languages, bio, latitude, longitude);
        } else {
            return null;
        }
        return usr;
    }

    /**
     * Adds a user to the database
     * This method checks the incoming user object and returns an IllegalArgumentException if one of its attributes is out of range or null
     *
     * @param user User object
     * @throws SQLException
     * @throws IOException
     * @throws IllegalArgumentException
     * @see User
     */
    public void addUser(User user) throws SQLException, IOException, IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("[ERROR] User object was null, cannot add to database");
        }
        if (user.getUserID() != -1) {
            throw new IllegalArgumentException("[ERROR] User id was not -1, aborting add to database");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new IllegalArgumentException("[ERROR] User password was null or empty, aborting add to database");
        }
        if (user.getFirstname() == null || user.getFirstname().equals("")) {
            throw new IllegalArgumentException("[ERROR] User firstname was null or empty, aborting add to database");
        }
        if (user.getLastname() == null || user.getLastname().equals("")) {
            throw new IllegalArgumentException("[ERROR] User lastname was null or empty, aborting add to database");
        }
        if (user.getBirthday() == null || user.getBirthday().equals(new Date(0))) {
            throw new IllegalArgumentException("[ERROR] User birthday was null or 0, aborting add to database");
        }
        if (user.getMail() == null || user.getMail().equals("")) {
            throw new IllegalArgumentException("[ERROR] User mail was null or empty, aborting add to database");
        }
        if (user.getPhonenumber() == null || user.getPhonenumber().equals("")) {
            throw new IllegalArgumentException("[ERROR] User phonenumber was null or empty, aborting add to database");
        }
        if (user.getStudy() == null || user.getStudy().equals("")) {
            throw new IllegalArgumentException("[ERROR] User study was null or empty, aborting add to database");
        }
        if (user.getUniversity() == null || user.getUniversity().equals("")) {
            throw new IllegalArgumentException("[ERROR] User university was null or empty, aborting add to database");
        }
        if (user.getStudyYear() == 0) {
            throw new IllegalArgumentException("[ERROR] User studyYear was 0, aborting add to database");
        }
        if (user.getGender() == null || user.getGender().equals("")) {
            throw new IllegalArgumentException("[ERROR] User gender was null or empty, aborting add to database");
        }
        if (user.getNationality() == null || user.getNationality().equals("")) {
            throw new IllegalArgumentException("[ERROR] User nationality was null or empty, aborting add to database");
        }
        if (user.getDescription() == null || user.getDescription().equals("")) {
            throw new IllegalArgumentException("[ERROR] User description was null or empty, aborting add to database");
        }
        if (user.getLatitude() == 0) {
            throw new IllegalArgumentException("[ERROR] User latitude was 0, aborting add to database");
        }
        if (user.getLongitude() == 0) {
            throw new IllegalArgumentException("[ERROR] User longitude was 0, aborting add to database");
        }

        PreparedStatement stmt;
        ResultSet rs;
        /**
         * Get the nationality id, throw IllegalArgumentException on fail
         */
        stmt = connection.prepareStatement("SELECT id FROM `nationalities` WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getNationality());
        rs = stmt.executeQuery();
        int nationality_id = -1;
        if (rs.next()) {
            nationality_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("[ERROR] Couldn't get or find nationality id, aborting add to database\n" +
                    "    Arguments: " + user.getNationality());
        }
        stmt.close();

        /**
         * Get the university id, throw IllegalArgumentException on fail
         */
        stmt = connection.prepareStatement("SELECT id FROM `universities` WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getUniversity());
        rs = stmt.executeQuery();
        int university_id;
        if (rs.next()) {
            university_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("[ERROR] Couldn't get or find university id, aborting add to database\n" +
                    "    Argument: " + user.getUniversity());
        }
        stmt.close();


        /**
         * Get the study id, throw IllegalArgumentException on fail
         */
        stmt = connection.prepareStatement("SELECT id FROM `studies` WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getStudy());
        rs = stmt.executeQuery();
        int study_id;
        if (rs.next()) {
            study_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("[ERROR] Couldn't get or find study id, aborting add to database");
        }
        stmt.close();

        /**
         * Insert user into database, finally
         */
        stmt = connection.prepareStatement("INSERT INTO `users`(id, nationality_id, university_id, email, passwd," +
                "firstname, lastname, sex, birthdate, study, bio, studyYear, availableDates, phonenumber, latitude, " +
                "longitude) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
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

        stmt.executeUpdate();
        rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            user.setUserID(rs.getInt(1));
        } else {
            throw new IllegalArgumentException("[ERROR] Couldn't retrieve newly added users ID.\n" +
                    "    Presume database invalid");
        }
        stmt.close();

        /**
         * Insert languages and courses
         */
        updateDbLanguages(user);
        updateDbCourses(user);
    }

    /**
     * Update a user in the database. According to the ID in the given user object
     *
     * @param user User object
     * @throws IllegalArgumentException
     * @throws SQLException
     * @throws IOException
     */
    public void updateUser(User user) throws IllegalArgumentException, SQLException, IOException {
        if (user == null) {
            throw new IllegalArgumentException("[ERROR] User object was null, cannot add to database");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new IllegalArgumentException("[ERROR] User password was null or empty, aborting add to database");
        }
        if (user.getFirstname() == null || user.getFirstname().equals("")) {
            throw new IllegalArgumentException("[ERROR] User firstname was null or empty, aborting add to database");
        }
        if (user.getLastname() == null || user.getLastname().equals("")) {
            throw new IllegalArgumentException("[ERROR] User lastname was null or empty, aborting add to database");
        }
        if (user.getBirthday() == null || user.getBirthday().equals(new Date(0))) {
            throw new IllegalArgumentException("[ERROR] User birthday was null or 0, aborting add to database");
        }
        if (user.getMail() == null || user.getMail().equals("")) {
            throw new IllegalArgumentException("[ERROR] User mail was null or empty, aborting add to database");
        }
        if (user.getPhonenumber() == null || user.getPhonenumber().equals("")) {
            throw new IllegalArgumentException("[ERROR] User phonenumber was null or empty, aborting add to database");
        }
        if (user.getStudy() == null || user.getStudy().equals("")) {
            throw new IllegalArgumentException("[ERROR] User study was null or empty, aborting add to database");
        }
        if (user.getUniversity() == null || user.getUniversity().equals("")) {
            throw new IllegalArgumentException("[ERROR] User university was null or empty, aborting add to database");
        }
        if (user.getStudyYear() == 0) {
            throw new IllegalArgumentException("[ERROR] User studyYear was 0, aborting add to database");
        }
        if (user.getGender() == null || user.getGender().equals("")) {
            throw new IllegalArgumentException("[ERROR] User gender was null or empty, aborting add to database");
        }
        if (user.getNationality() == null || user.getNationality().equals("")) {
            throw new IllegalArgumentException("[ERROR] User nationality was null or empty, aborting add to database");
        }
        if (user.getDescription() == null || user.getDescription().equals("")) {
            throw new IllegalArgumentException("[ERROR] User description was null or empty, aborting add to database");
        }
        if (user.getLatitude() == 0) {
            throw new IllegalArgumentException("[ERROR] User latitude was 0, aborting add to database");
        }
        if (user.getLongitude() == 0) {
            throw new IllegalArgumentException("[ERROR] User longitude was 0, aborting add to database");
        }

        PreparedStatement stmt;
        ResultSet rs;

        /**
         * Get nationality id
         */
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

        /**
         * Update languages and courses
         */
        updateDbLanguages(user);
        updateDbCourses(user);

    }

    private void updateDbLanguages(User user) throws SQLException {
        PreparedStatement stmt;
        ResultSet rs;

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
    }

    private void updateDbCourses(User user) throws SQLException {
        PreparedStatement stmt;
        ResultSet rs;
        StringBuilder builder;
        String query;

        /**
         * Delete all residual course entries
         */
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
    }

    /**
     * Remove a user from the database using given id
     *
     * @param id int, remove user with this id
     * @throws SQLException
     */
    public void removeUser(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM `users` WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * Remove a user from the database using the given User object id
     *
     * @param user remove user that has the id in this given object
     * @throws SQLException
     */
    public void removeUser(User user) throws SQLException {
        if (user == null) {
            throw new IllegalArgumentException("User object was null, cannot delete from database");
        }
        int id = user.getUserID();
        removeUser(id);
    }

    /**
     * Used to get matching users using given parameters
     * @param node JSON node containing the search parameters
     * @return
     * @throws SQLException
     */
    public ArrayList<User> getMatches(int self_id, JsonNode node) throws SQLException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        double maxDist = node.get("data").get("maxdist").getDoubleValue(),
                            latitude = node.get("data").get("latitude").getDoubleValue(),
                            longitude = node.get("data").get("longitude").getDoubleValue();
                    AvailableTimes aTimes = mapper.readValue(node.get("data").get("availability").getTextValue(),
                            AvailableTimes.class);
                    ArrayList learning = mapper.readValue(node.get("data").get("learning").getTextValue(),
                                    ArrayList.class),
                            teaching = mapper.readValue(node.get("data").get("teaching").getTextValue(),
                                    ArrayList.class),
                            buddys = mapper.readValue(node.get("data").get("buddys").getTextValue(),
                                    ArrayList.class),
                            languages = mapper.readValue(node.get("data").get("languages").getTextValue(),
                                    ArrayList.class);
        String where = "WHERE users.id <> ?",
                dist = "(((acos(sin((? * pi()/180)) * sin((users.latitude * pi()/180))+cos((? * pi()/180)) * cos((users.latitude * pi()/180)) * cos(((?-users.longitude) * pi()/180)))) * 180/pi()) * 60 * 1.1515 ) as distance",
                query = "SELECT users.id, nationalities.name AS nationality, universities.name AS university, email, passwd, firstname, lastname, sex, birthdate, studies.name AS study, bio, studyYear, availableDates, phonenumber, latitude, longitude, " +
                dist + " FROM `users` " +
                "LEFT JOIN nationalities ON users.nationality_id = nationalities.id " +
                "LEFT JOIN universities ON users.university_id = universities.id " +
                "LEFT JOIN studies ON users.study = studies.id " + where + " " +
                "HAVING distance <= ?";
        System.out.println(query);
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
        while(rs.next()) {
            matches.add(getUser(rs.getInt("id")));
        }
        stmt.close();
        int index = 0;
        for (User user : matches) {
            if (listIntersection(user.getLanguageList(), languages).size() == 0) {
                matches.remove(index);
            }
            index++;
        }
        if (matches.size() == 0) {
            return matches; //returns empty result
        }
        /*
        fixme: This code is not correct semantically due to return type.
        User self = getUser(self_id);
        for (User user : matches) {
            if (listIntersection(user.getCoursesLearningList(), teaching).size() > 0) {
                canTeach.add(user);
            }
        }
        for (User user : matches) {
            if (listIntersection(user.getCoursesTeachingList(), learning).size() > 0) {
                canLearn.add(user);
            }
        }
        for (User user : matches) {
            if (listIntersection(user.getBuddyList(), buddys).size() > 0) {
                canBuddyUp.add(user);
            }
        }*/

        return null;
    }

    /**
     * Returns the intersection between two ArrayLists, thus only elements that are in both lists
     * @param list1 ArrayList 1
     * @param list2 ArrayList 2
     * @param <T> Type specifier
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
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
