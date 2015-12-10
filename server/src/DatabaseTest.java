package server;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Connection;
import java.sql.SQLException;
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
        aTimes.addTimePeriod(1, new TimePeriod(1110, 1260));
        aTimes.addTimePeriod(3, new TimePeriod(630, 720));
        aTimes.addTimePeriod(4, new TimePeriod(450, 900));
        aTimes.addTimePeriod(5, new TimePeriod(450, 900));
        aTimes.addTimePeriod(5, new TimePeriod(990, 1080));
        aTimes.addTimePeriod(7, new TimePeriod(570, 900));
        bTimes.addTimePeriod(1, new TimePeriod(1, 2));

        teaching.add("wiskunde");
        learning.add("wiskunde");
        buddy.add("poepen");
        buddy.add("plassen");
        buddy.add("wiskunde");

        languages.add("Nederlands");
        languages.add("Duits");

        user = new User(1, "Pepernoten01", "Sinter", "Klaas", new Date(-35659808), "sinterklaas@sintmail.nl",
                "+316123456789", "Technische Natuurkunde", "Universiteit of Pepernoten bakken", 3, aTimes, teaching,
                learning, buddy, "male", "Nederlands", languages, "SINTERKAAL\r\nWIE KENT HEM NIET\r\n\r\n-Kut",
                51.2065, 26.5098);

        addUser = new User(-1, "a", "b", "c", new Date(1234), "d", "e", "Technische Poepen", "Universiteit of Plassen",
                1, bTimes, teaching, learning, buddy, "l", "Frans", languages, "o", 1.5, 2.5);

    }

    @Test
    public void testBooleanConstructor() throws Exception {
        Database random = new Database(true);
        //Why have this one anyway?
        //I am drunk now, but I made test anyway :)
    }

    @Test
    public void testGetUser() throws Exception {
        assertEquals(user, db.getUser(1));
        assertNull(db.getUser(-1));
    }

    @Test
    public void testGetUser1() throws Exception {
        assertEquals(user, db.getUser("sinterklaas@sintmail.nl"));
        assertNull(db.getUser("DKLFJSDKLFJSDKLFJSDKLFJSDKLFJSDFKL"));
    }

    @Test
    public void testAddUser() throws Exception {
        db.addUser(addUser);
        assertEquals(addUser, db.getUser("d"));
        User temp = db.getUser("d");
        db.removeUser(temp.getUserID());
    }

    @Test
    public void testUpdateUser() throws Exception {
        db.addUser(addUser);
        User temp = db.getUser("d");
        temp.setLastname("blargh");
        db.updateUser(temp);
        assertEquals(temp, db.getUser("d"));
        db.removeUser(temp);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUser() throws Exception {
        db.addUser(addUser);
        User temp = db.getUser("d");
        db.removeUser(temp);
        db.removeUser(null);//Throws the IllegalArgumentException
    }

    @Test
    public void testRemoveUser1() throws Exception {
        db.addUser(addUser);
        User temp = db.getUser("d");
        db.removeUser(temp.getUserID());
    }

    @Test
    public void testClose() throws Exception {
        db.close();
    }
}