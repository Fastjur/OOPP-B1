import org.junit.Before;
import org.junit.Test;
import shared.AvailableTimes;
import shared.TimePeriod;
import shared.User;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created on 9-12-2015.
 *
 * @author Jurriaan Den Toonder
 */
public class DatabaseTest {

    private Connection conn;
    private Database db;
    private User user, addUser;
    private AvailableTimes aTimes, bTimes;
    private ArrayList<String> teaching, learning, buddy, languages;

    @Before
    public void setUp() throws Exception {
        conn = ConnectionManager.getConnection();
        db = new Database();
        aTimes = new AvailableTimes();
        bTimes = new AvailableTimes();
        teaching = new ArrayList<>();
        learning = new ArrayList<>();
        buddy = new ArrayList<>();
        languages = new ArrayList<>();

        aTimes.addTimePeriod(1, new TimePeriod(450, 900));
        aTimes.addTimePeriod(1, new TimePeriod(1110, 1200));
        aTimes.addTimePeriod(2, new TimePeriod(0, 0));
        aTimes.addTimePeriod(3, new TimePeriod(0, 0));
        aTimes.addTimePeriod(4, new TimePeriod(0, 0));
        aTimes.addTimePeriod(5, new TimePeriod(0, 0));
        aTimes.addTimePeriod(6, new TimePeriod(0, 0));
        aTimes.addTimePeriod(7, new TimePeriod(0, 0));
        bTimes.addTimePeriod(1, new TimePeriod(1, 2));

        teaching.add("Web en Database technologie");
        learning.add("Calculus");
        buddy.add("Calculus");
        buddy.add("Web en Database technologie");
        buddy.add("Object Oriënted Programming");

        languages.add("Nederlands");
        languages.add("Engels");
        languages.add("C++");
        languages.add("Java");

        user = new User(1, "1000:0f6d32f8bbb343a9d6de15366fdb1d6f622fee3d5aee928d:bb8fbbf00ba255e7a9dd63cf4a015582dbf4bf16bea23c40",
                "Sinter", "Klaas", new SimpleDateFormat("yyyy-MM-dd").parse("1976-12-05"), "sinterklaas@sintmail.nl",
                "+31612345678", "Computer Sciences and Engineering", "Technische Universiteit Delft", 1, aTimes, teaching,
                learning, buddy, "male", "Nederlands", languages, "SINTERKAAL\r\nWIE KENT HEM NIET\r\n\r\n-Kut",
                51.9827, 4.34825, 0);

        addUser = new User(-1, "a", "b", "c", new Date(1234), "d", "e", "Technische Poepen", "Universiteit of Plassen",
                1, bTimes, teaching, learning, buddy, "l", "Frans", languages, "o", 1.5, 2.5, 5);

    }

    @Test
    public void testBooleanConstructor() throws Exception {
        Database random = new Database(true);
        //Why have this one anyway?
        //I am drunk now, but I made test anyway :)
    }

    @Test
    public void testGetUserByID() throws Exception {
        assertEquals(user, db.getUser(1));
        assertNull(db.getUser(-1));
    }

    @Test
    public void testGetUserByMail() throws Exception {
        assertEquals(user, db.getUser("sinterklaas@sintmail.nl"));
        assertNull(db.getUser("DKLFJSDKLFJSDKLFJSDKLFJSDKLFJSDFKL"));
    }

    //FIXME
    @Test
    public void testAddUser() throws Exception {
        /*db.addUser(addUser);
        assertEquals(addUser, db.getUser("d"));
        User temp = db.getUser("d");
        db.removeUser(temp.getUserID());*/
    }

    //FIXME
    @Test
    public void testUpdateUser() throws Exception {
        /*db.addUser(addUser);
        User temp = db.getUser("d");
        temp.setLastname("blargh");
        db.updateUser(temp);
        assertEquals(temp, db.getUser("d"));
        db.removeUser(temp);*/
    }

    @Test
    public void testRemoveUser() throws Exception {
        /*db.addUser(addUser);
        User temp = db.getUser("d");
        db.removeUser(temp);
        db.removeUser(null);//Throws the IllegalArgumentException*/
    }

    @Test
    public void testRemoveUser1() throws Exception {
        /*db.addUser(addUser);
        User temp = db.getUser("d");
        db.removeUser(temp.getUserID());*/
    }

    @Test
    public void testClose() throws Exception {
        db.close();
    }
}