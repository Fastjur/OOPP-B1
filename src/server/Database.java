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
            PreparedStatement stmt = connection.prepareStatement("SELECT users.id, nationalities.name as nationality, studies.name as study, universities.name as university,   email as dbemail, passwd, firstname, lastname, sex, birthdate, bio, studyYear, availableDates, location, phonenumber, photo FROM `users` LEFT JOIN nationalities on users.nationality_id = nationalities.id LEFT JOIN studies ON users.study = studies.id LEFT JOIN universities ON users.universities_id = universities.id WHERE email = ? LIMIT 1");
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
                    "studyYear, /*availableDates, */location, phonenumber, photo FROM `users` WHERE id = ? " +
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

            usr = new User(id, password, firstname, lastname, birthdate, dbemail, phonenumber,
                    new Address("A", "B", "C","D"), study, university, studyYear, sex, nationality, bio, location, photo);
            System.out.println(usr);
        }
        return usr;
    }

}
