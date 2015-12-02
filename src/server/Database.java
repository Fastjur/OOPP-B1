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
            user = processUser(rs);
        } catch (SQLException e) {
            throw new IllegalStateException("Could not get user by email:\n" + e.getMessage());
        }
        return user;
    }

    public User getUser(int id) {
        User user;
        try {
            //TODO uncomment statement parts when they have been implemented in User.java
            PreparedStatement stmt = connection.prepareStatement("SELECT users.id, nationalities.name as nationality," +
                    "studies.name as study, universities.name as university, email as dbemail, passwd, firstname," +
                    "lastname, sex, birthdate, bio, studyYear, availableDates, location, phonenumber, photo " +
                    "FROM `users` LEFT JOIN nationalities on users.nationality_id = nationalities.id " +
                    "LEFT JOIN studies ON users.study = studies.id " +
                    "LEFT JOIN universities ON users.universities_id = universities.id WHERE users.id = ? LIMIT 1");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            user = processUser(rs);
            stmt.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Could not get user by email:\n" + e.getMessage());
        }
        return user;
    }

    private User processUser(ResultSet rs) throws SQLException {
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
                    "LEFT JOIN coursessearchingbuddy ON courses_id = courses.id " +
                    "WHERE coursessearchingbuddy.users_id = ?");
            stmt.setInt(1, id);
            ResultSet rs2 = stmt.executeQuery();
            ArrayList<String> coursesSearchingBuddy = new ArrayList<String>();
            while(rs2.next()) {
                coursesSearchingBuddy.add(rs2.getString("name"));
            }
            stmt.close();

            stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN coursesteaching ON courses_id = courses.id " +
                    "WHERE coursesteaching.users_id = ?");
            stmt.setInt(1, id);
            rs2 = stmt.executeQuery();
            ArrayList<String> coursesTeaching = new ArrayList<String>();
            while(rs2.next()) {
                coursesTeaching.add(rs2.getString("name"));
            }
            stmt.close();

            stmt = connection.prepareStatement("SELECT courses.name FROM courses " +
                    "LEFT JOIN courseslearning ON courses_id = courses.id " +
                    "WHERE courseslearning.users_id = ?");
            stmt.setInt(1, id);
            rs2 = stmt.executeQuery();
            ArrayList<String> coursesLearning = new ArrayList<String>();
            while(rs2.next()) {
                coursesLearning.add(rs2.getString("name"));
            }
            stmt.close();

            usr = new User(id, password, firstname, lastname, birthdate, dbemail, phonenumber,
                    new Address("A", "B", "C","D"), study, university, studyYear, new ArrayList(), coursesTeaching,
                    coursesLearning, coursesSearchingBuddy, sex, nationality, bio, location);
            System.out.println(usr);
        }
        return usr;
    }

}
