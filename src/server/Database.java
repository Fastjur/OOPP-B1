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

    public static User getUser(String email) {
        User user = new User();
        try {
            //TODO uncomment statement parts when they have been implemented in User.java
            PreparedStatement stmt = connection.prepareStatement("SELECT id, nationality_id, studies_id," +
                    "universities_id, email as dbemail, passwd, firstname, lastname, sex, birthdate, study, bio," +
                    "studyYear, /*availableDates, */location, phonenumber/*, photo*/ FROM `users` WHERE email = ?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
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
                java.util.Date birthdate = new java.util.Date(rs.getLong("birthdate"));

                //TODO create user using above fields.
                System.out.println("================================================\nData received from database:");
                System.out.println(id);
                System.out.println(nationality_id);
                System.out.println(studies_id);
                System.out.println(universities_id);
                System.out.println(study);
                System.out.println(studyYear);
                System.out.println(dbemail);
                System.out.println(password);
                System.out.println(firstname);
                System.out.println(lastname);
                System.out.println(sex);
                System.out.println(bio);
                System.out.println(location);
                System.out.println(phonenumber);
                System.out.println(birthdate);
                System.out.println("==================================================");

            }
        } catch (SQLException e) {
            throw new IllegalStateException("Could not get user by email:\n" + e.getMessage());
        }
        return user;
    }

}
