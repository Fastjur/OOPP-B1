package server;

import java.sql.*;

/**
 * Database class that reads the database file
 *
 * @author Jurriaan Den Toonder
 * @version 0.1
 */
public class Database {

    private final String JDBCUrl = "jdbc:mysql://db4free.net:3306/ooppb1";
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
        User user = new User();
        try {
            //TODO uncomment statement parts when they have been implemented in User.java
            PreparedStatement stmt = connection.prepareStatement("SELECT id, nationality_id, studies_id," +
                    "universities_id, email as dbemail, passwd, firstname, lastname, sex, birthdate, study, bio," +
                    "studyYear, /*availableDates, */location, phonenumber/*, photo*/ FROM `users` WHERE email = ? " +
                    "LIMIT 1");
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
            PreparedStatement stmt = connection.prepareStatement("SELECT id, nationality_id, studies_id," +
                    "universities_id, email as dbemail, passwd, firstname, lastname, sex, birthdate, study, bio," +
                    "studyYear, /*availableDates, */location, phonenumber/*, photo*/ FROM `users` WHERE email = ? " +
                    "LIMIT 1");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            user = processUser(rs);
        } catch (SQLException e) {
            throw new IllegalStateException("Could not get user by email:\n" + e.getMessage());
        }
        return user;
    }

    private User processUser(ResultSet rs) throws SQLException {
        User usr = new User();
        while(rs.next()) {
            int id = rs.getInt("id"),
                    nationality_id = rs.getInt("nationality_id"),
                    studies_id = rs.getInt("studies_id"),
                    universities_id = rs.getInt("universities_id"),
                    study = rs.getInt("study"),
                    studyYear = rs.getInt("studyYear");
            String dbemail = rs.getString("dbemail"),
                    password = rs.getString("passwd"),
                    firstname = rs.getString("firstname"),
                    lastname = rs.getString("lastname"),
                    sex = rs.getString("sex"),
                    bio = rs.getString("bio"),
                    location = rs.getString("location"),
                    phonenumber = rs.getString("phonenumber");
            Date birthdate = new Date(rs.getString("birthdate"));

            PreparedStatement stmt = connection.prepareStatement("SELECT name FROM `nationalities` " +
                    "WHERE id = ? LIMIT 1");
            stmt.setInt(1, nationality_id);
            ResultSet rs2 = stmt.executeQuery();
            String nationality = "{NATIONALITY}";
            while(rs2.next()) {
                nationality = rs2.getString("name");
            }

            usr = new User(id, firstname + " " + lastname, birthdate, dbemail, phonenumber,
                    new Address("A", "B", "C","D"), "{COURSES}", "{UNIVERSITY}", 0, sex, nationality, bio);
            System.out.println(usr);
        }
        return usr;
    }

}
