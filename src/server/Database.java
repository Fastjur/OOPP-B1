package server;

import java.sql.*;
import java.util.ArrayList;

/**
 * Database class that reads the database file
 *
 * @author Jurriaan Den Toonder
 * @version 0.1
 */
public class Database {

    private final String JDBCUrl = "jdbc:mysql://db4free.net:3306/ooppb1";
//    private final String JDBCUrl = "jdbc:mysql://localhost:3306/ooppb1";
    private final String user = "oopp_usr";
    private final String password = "oopp_b1_database";

    private static Connection connection;

    /**
     * Constructor of Database object
     */
    public Database() throws IllegalStateException {
        System.out.println("Attempting to connect to database");
        try {
            connection = DriverManager.getConnection(JDBCUrl, user, password);
            System.out.println("Successful connection to database!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect to the database!\n" + e.getMessage());
        }
    }

    public User getUser(String email) {
        User user;
        try {
            //TODO uncomment statement parts when they have been implemented in User.java
            PreparedStatement stmt = connection.prepareStatement("SELECT users.id, nationalities.name as nationality," +
                    "studies.name as study, universities.name as university, email as dbemail, passwd, firstname," +
                    "lastname, sex, birthdate, bio, studyYear, availableDates, location, phonenumber, photo " +
                    "FROM `users` LEFT JOIN nationalities on users.nationality_id = nationalities.id " +
                    "LEFT JOIN studies ON users.study = studies.id " +
                    "LEFT JOIN universities ON users.universities_id = universities.id WHERE users.email = ? LIMIT 1");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            user = processCourses(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not get user by email:\n" + e.getMessage());
        }
        return user;
    }

    public User getUser(int id) throws SQLException {
        User user;
        PreparedStatement stmt = connection.prepareStatement("SELECT users.id, nationalities.name as nationality," +
                "studies.name as study, universities.name as university, email as dbemail, passwd, firstname," +
                "lastname, sex, birthdate, bio, studyYear, availableDates, location, phonenumber, photo " +
                "FROM `users` LEFT JOIN nationalities on users.nationality_id = nationalities.id " +
                "LEFT JOIN studies ON users.study = studies.id " +
                "LEFT JOIN universities ON users.universities_id = universities.id WHERE users.id = ? LIMIT 1");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        user = processCourses(rs);
        stmt.close();
        return user;
    }

    public void addUser(User user) {
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
        if(user.getAddress() == null || user.getAddress().Contains("")) {
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
    }

    private User processCourses(ResultSet rs) throws SQLException {
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
                    photo = rs.getString("photo");
            Date birthdate = new Date(rs.getLong("birthdate"));

            PreparedStatement stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesSearchingBuddy ON courses_id = courses.id " +
                    "WHERE coursesSearchingBuddy.users_id = ?");
            stmt.setInt(1, id);
            ResultSet rs2 = stmt.executeQuery();
            ArrayList<String> coursesSearchingBuddy = new ArrayList<String>();
            while(rs2.next()) {
                coursesSearchingBuddy.add(rs2.getString("name"));
            }
            stmt.close();

            stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesTeaching ON courses_id = courses.id " +
                    "WHERE coursesTeaching.users_id = ?");
            stmt.setInt(1, id);
            rs2 = stmt.executeQuery();
            ArrayList<String> coursesTeaching = new ArrayList<String>();
            while(rs2.next()) {
                coursesTeaching.add(rs2.getString("name"));
            }
            stmt.close();

            stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesLearning ON courses_id = courses.id " +
                    "WHERE coursesLearning.users_id = ?");
            stmt.setInt(1, id);
            rs2 = stmt.executeQuery();
            ArrayList<String> coursesLearning = new ArrayList<String>();
            while(rs2.next()) {
                coursesLearning.add(rs2.getString("name"));
            }
            stmt.close();

            usr = new User(id, password, firstname, lastname, birthdate, dbemail, phonenumber,
                    new Address("A", "B", "C","D"), study, university, studyYear, new ArrayList(), coursesTeaching,
                    coursesLearning, coursesSearchingBuddy, sex, nationality, bio, location, photo);
            System.out.println(usr);
        }
        return usr;
    }

}
