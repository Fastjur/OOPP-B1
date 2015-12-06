package server;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Database class that reads the database file
 *
 * @author Jurriaan Den Toonder
 * @version 0.1
 */
public class Database {

    private static Connection connection;

    /**
     * Constructor of Database object
     * Also contains the host, username and password for the database connection
     */
    public Database() throws IllegalStateException {
        System.out.println("Attempting to connect to database");
        try {
            String JDBCUrl = "jdbc:mysql://db4free.net:3306/ooppb1";
//            String JDBCUrl = "jdbc:mysql://localhost:3306/ooppb1";
            String user = "oopp_usr";
            String password = "oopp_b1_database";
            connection = DriverManager.getConnection(JDBCUrl, user, password);
            System.out.println("Successful connection to database!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect to the database!\n" + e.getMessage());
        }
    }

    public User getUser(String email) throws SQLException, IOException {
        User user;
        try {
            //TODO: Check for null values returned by database and generate appropriate exceptions
            PreparedStatement stmt = connection.prepareStatement("SELECT users.id, nationalities.name as nationality," +
                    "studies.name as study, universities.name as university, email as dbemail, passwd, firstname," +
                    "lastname, sex, birthdate, bio, studyYear, availableDates, location, phonenumber " +
                    "FROM `users` LEFT JOIN nationalities on users.nationality_id = nationalities.id " +
                    "LEFT JOIN studies ON users.study = studies.id " +
                    "LEFT JOIN universities ON users.university_id = universities.id WHERE users.email = ? LIMIT 1");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            user = processGetUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not get user by email:\n" + e.getMessage());
        }
        return user;
    }

    public User getUser(int id) throws SQLException, IOException {
        User user;
        PreparedStatement stmt = connection.prepareStatement("SELECT users.id, nationalities.name as nationality," +
                "studies.name as study, universities.name as university, email as dbemail, passwd, firstname," +
                "lastname, sex, birthdate, bio, studyYear, availableDates, location, phonenumber " +
                "FROM `users` LEFT JOIN nationalities on users.nationality_id = nationalities.id " +
                "LEFT JOIN studies ON users.study = studies.id " +
                "LEFT JOIN universities ON users.university_id = universities.id WHERE users.id = ? LIMIT 1");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        user = processGetUser(rs);
        stmt.close();
        return user;
    }

    private User processGetUser(ResultSet rs) throws SQLException, IOException {
        //fixme: NullPointerExceptions on empty result from Database
        User usr = new User();
        if(rs.next()) {
            int id = rs.getInt("id"),
                    studyYear = rs.getInt("studyYear");
            String dbemail = rs.getString("dbemail"),
                    password = rs.getString("passwd"),
                    firstname = rs.getString("firstname"),
                    lastname = rs.getString("lastname"),
                    nationality = rs.getString("nationality"),
                    university = rs.getString("university"),
                    study = rs.getString("study"),
                    sex = rs.getString("sex"),
                    bio = rs.getString("bio"),
                    location = rs.getString("location"),
                    phonenumber = rs.getString("phonenumber"),
                    availabilityString = rs.getString("availableDates");
            Date birthdate = new Date(rs.getLong("birthdate"));

            AvailableTimes availability = AvailableTimes.fromJson(availabilityString);

            PreparedStatement stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesSearchingBuddy ON courses_id = courses.id " +
                    "WHERE coursesSearchingBuddy.users_id = ?");
            stmt.setInt(1, id);
            ResultSet rs2 = stmt.executeQuery();
            ArrayList<String> coursesSearchingBuddy = new ArrayList<>();
            while(rs2.next()) {
                coursesSearchingBuddy.add(rs2.getString("name"));
            }
            stmt.close();

            stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesTeaching ON courses_id = courses.id " +
                    "WHERE coursesTeaching.users_id = ?");
            stmt.setInt(1, id);
            rs2 = stmt.executeQuery();
            ArrayList<String> coursesTeaching = new ArrayList<>();
            while(rs2.next()) {
                coursesTeaching.add(rs2.getString("name"));
            }
            stmt.close();

            stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesLearning ON courses_id = courses.id " +
                    "WHERE coursesLearning.users_id = ?");
            stmt.setInt(1, id);
            rs2 = stmt.executeQuery();
            ArrayList<String> coursesLearning = new ArrayList<>();
            while(rs2.next()) {
                coursesLearning.add(rs2.getString("name"));
            }
            stmt.close();

            stmt = connection.prepareStatement("SELECT languages.name FROM languages " +
                    "LEFT JOIN users_has_languages ON languages.id = users_has_languages.languages_id")

            usr = new User(id, password, firstname, lastname, birthdate, dbemail, phonenumber,
                    new Address("A", "B", "C","D"), study, university, studyYear, availability, coursesTeaching,
                    coursesLearning, coursesSearchingBuddy, sex, nationality, bio, location);
        }
        return usr;
    }

    public void addUser(User user) throws SQLException, IOException, IllegalArgumentException {
        if(user == null) {
            throw new IllegalArgumentException("User object was null, cannot add to database");
        }
        if(user.getUserID() != -1) {
            throw new IllegalArgumentException("User id was not -1, aborting add to database");
        }
        if(user.getPassword() == null || user.getPassword().equals("")) {
            throw new IllegalArgumentException("User password was null or empty, aborting add to database");
        }
        if(user.getFirstname() == null || user.getFirstname().equals("")) {
            throw new IllegalArgumentException("User firstname was null or empty, aborting add to database");
        }
        if(user.getLastname() == null || user.getLastname().equals("")) {
            throw new IllegalArgumentException("User lastname was null or empty, aborting add to database");
        }
        if(user.getBirthday() == null || user.getBirthday().equals(new Date(0))) {
            throw new IllegalArgumentException("User birthday was null or 0, aborting add to database");
        }
        if(user.getMail() == null || user.getMail().equals("")) {
            throw new IllegalArgumentException("User mail was null or empty, aborting add to database");
        }
        if(user.getPhonenumber() == null || user.getPhonenumber().equals("")) {
            throw new IllegalArgumentException("User phonenumber was null or empty, aborting add to database");
        }
        if(user.getAddress() == null || user.getAddress().contains("")) {
            throw new IllegalStateException("User address was null or one of its properties was empty," +
                    " aborting add to database");
        }
        if(user.getStudy() == null || user.getStudy().equals("")) {
            throw new IllegalArgumentException("User study was null or empty, aborting add to database");
        }
        if(user.getUniversity() == null || user.getUniversity().equals("")) {
            throw new IllegalArgumentException("User university was null or empty, aborting add to database");
        }
        if(user.getStudyYear() == 0) {
            throw new IllegalArgumentException("User studyYear was 0, aborting add to database");
        }
        if(user.getGender() == null || user.getGender().equals("")) {
            throw new IllegalArgumentException("User gender was null or empty, aborting add to database");
        }
        if(user.getNationality() == null || user.getNationality().equals("")) {
            throw new IllegalArgumentException("User nationality was null or empty, aborting add to database");
        }
        if(user.getDescription() == null || user.getDescription().equals("")) {
            throw new IllegalArgumentException("User description was null or empty, aborting add to database");
        }
        if(user.getLocation() == null || user.getLocation().equals("")) {
            throw new IllegalArgumentException("User location was null or empty, aborting add to database");
        }

        PreparedStatement stmt;
        ResultSet rs;

        /**
         * Get the nationality id, throw IllegalArgumentException on fail
         */
        int nationality_id;
        stmt = connection.prepareStatement("SELECT id FROM `nationalities` WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getNationality());
        rs = stmt.executeQuery();
        if(rs.next()) {
            nationality_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("Couldn't get nationality id, aborting add to database\n" +
                    "    Arguments: " + user.getNationality());
        }
        stmt.close();

        /**
         * Get the university id, throw IllegalArgumentException on fail
         */
        int university_id;
        stmt = connection.prepareStatement("SELECT id FROM `universities` WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getUniversity());
        rs = stmt.executeQuery();
        if(rs.next()) {
            university_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("Couldn't get university id, aborting add to database");
        }
        stmt.close();

        /**
         * Get the study id, throw IllegalArgumentException on fail
         */
        int study_id;
        stmt = connection.prepareStatement("SELECT id FROM `studies` WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getStudy());
        rs = stmt.executeQuery();
        if(rs.next()) {
            study_id = rs.getInt("id");
        }else{
            throw new IllegalArgumentException("Couldn't get study id, aborting add to database");
        }
        stmt.close();

        /**
         * Insert user into database
         */
        stmt = connection.prepareStatement("INSERT INTO `users`(id, nationality_id, university_id, email, passwd," +
                "firstname, lastname, sex, birthdate, study, bio, studyYear, availableDates, location, phonenumber) " +
                "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
        stmt.setString(12, user.getAvailability().toJson());
        stmt.setString(13, user.getLocation());
        stmt.setString(14, user.getPhonenumber());
        stmt.close();
    }

    public void updateUser(User user) throws IllegalArgumentException, SQLException, IOException {
        if(user == null) {
            throw new IllegalArgumentException("User object was null, cannot add to database");
        }
        if(user.getPassword() == null || user.getPassword().equals("")) {
            throw new IllegalArgumentException("User password was null or empty, aborting add to database");
        }
        if(user.getFirstname() == null || user.getFirstname().equals("")) {
            throw new IllegalArgumentException("User firstname was null or empty, aborting add to database");
        }
        if(user.getLastname() == null || user.getLastname().equals("")) {
            throw new IllegalArgumentException("User lastname was null or empty, aborting add to database");
        }
        if(user.getBirthday() == null || user.getBirthday().equals(new Date(0))) {
            throw new IllegalArgumentException("User birthday was null or 0, aborting add to database");
        }
        if(user.getMail() == null || user.getMail().equals("")) {
            throw new IllegalArgumentException("User mail was null or empty, aborting add to database");
        }
        if(user.getPhonenumber() == null || user.getPhonenumber().equals("")) {
            throw new IllegalArgumentException("User phonenumber was null or empty, aborting add to database");
        }
        if(user.getAddress() == null || user.getAddress().contains("")) {
            throw new IllegalStateException("User address was null or one of its properties was empty," +
                    " aborting add to database");
        }
        if(user.getStudy() == null || user.getStudy().equals("")) {
            throw new IllegalArgumentException("User study was null or empty, aborting add to database");
        }
        if(user.getUniversity() == null || user.getUniversity().equals("")) {
            throw new IllegalArgumentException("User university was null or empty, aborting add to database");
        }
        if(user.getStudyYear() == 0) {
            throw new IllegalArgumentException("User studyYear was 0, aborting add to database");
        }
        if(user.getGender() == null || user.getGender().equals("")) {
            throw new IllegalArgumentException("User gender was null or empty, aborting add to database");
        }
        if(user.getNationality() == null || user.getNationality().equals("")) {
            throw new IllegalArgumentException("User nationality was null or empty, aborting add to database");
        }
        if(user.getDescription() == null || user.getDescription().equals("")) {
            throw new IllegalArgumentException("User description was null or empty, aborting add to database");
        }
        if(user.getLocation() == null || user.getLocation().equals("")) {
            throw new IllegalArgumentException("User location was null or empty, aborting add to database");
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
            throw new IllegalArgumentException("Could not get nationality id, aborting update to database\n    " +
                    "Argument: (name:\"" + user.getNationality() + "\")");
        }

        /**
         * Get university id
         */
        stmt = connection.prepareStatement("SELECT id FROM universities WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getUniversity());
        rs = stmt.executeQuery();
        int university_id = 0;
        if (rs.next()) {
            university_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("Could not get university id, aborting update to database\n    " +
                    "Argument: (name:\"" + user.getUniversity() + "\")");
        }

        /**
         * Get study id
         */
        stmt = connection.prepareStatement("SELECT id FROM studies WHERE name = ? LIMIT 1");
        stmt.setString(1, user.getStudy());
        rs = stmt.executeQuery();
        int study_id = 0;
        if (rs.next()) {
            study_id = rs.getInt("id");
        } else {
            throw new IllegalArgumentException("Could not get study id, aborting update to database\n    " +
                    "Argument: (name:\"" + user.getStudy() + "\")");
        }

        stmt = connection.prepareStatement("UPDATE `users` SET nationality_id=?, university_id=?, " +
                "email=?, passwd=?, firstname=?, lastname=?, sex=?, birthdate=?, study=?, bio=?, studyYear=?, " +
                "availableDates=?, location=?, phonenumber=? " +
                "WHERE id = ?");
        stmt.setInt(1, nationality_id);
        stmt.setInt(2, university_id);
        stmt.setString(3, user.getMail());
        stmt.setString(4, user.getPassword());
        stmt.setString(5, user.getFirstname());
        stmt.setString(6, user.getLastname());
        stmt.setString(7, user.getGender());
        stmt.setLong(8, user.getBirthday().getTime());
        stmt.setString(9, user.getDescription());
        stmt.setInt(10, user.getStudyYear());
        stmt.setString(11, user.getAvailability().toJson());
        stmt.setString(12, user.getLocation());
        stmt.setString(13, user.getPhonenumber());

        stmt.execute();
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
