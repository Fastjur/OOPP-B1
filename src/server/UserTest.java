package server;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Govert de Gans
 * @version  2015-12-09
 */
public class UserTest {
    User testuser1;
    User testuser2;

    @Before
    public void createUserObject() {
        testuser1 = new User(1, "Pepernoten01", "Sinter", "Klaas", new Date(1),
                "sinterklaas@sintmail.nl", "+316123456789", new Address("A", "B", "C", "D"),
                "study1", "university", 3, new AvailableTimes(), new ArrayList<String>(),
                new ArrayList<String>(), new ArrayList<String>(), "male", "NLD",
                new ArrayList<String>(), "It's-a-me", "Spanje");

        testuser2 = new User(1, "Pepernoten01", "Sinter", "Klaas", new Date(1),
                "sinterklaas@sintmail.nl", "+316123456789", new Address("A", "B", "C", "D"),
                "study1", "university", 3, new AvailableTimes(), new ArrayList<String>(),
                new ArrayList<String>(), new ArrayList<String>(), "male", "NLD",
                new ArrayList<String>(), "It's-a-me", "Spanje");
    }

    @Test
    public void testFromJsonEquals() throws Exception {
        User fromjson = User.fromJson("{\"password\":\"Pepernoten01\",\"firstname\":\"Sinter\",\"lastname\":\"Klaas\"" +
                ",\"mail\":\"sinterklaas@sintmail.nl\",\"phonenumber\":\"+316123456789\"," +
                "\"study\":\"study1\",\"university\":\"university\",\"gender\":\"male\"," +
                "\"nationality\":\"NLD\",\"description\":\"It's-a-me\",\"location\":\"Spanje\"," +
                "\"birthday\":1,\"userID\":1,\"studyYear\":3,\"address\":{\"street\":\"A\"," +
                "\"housenumber\":\"B\",\"zipcode\":\"C\",\"city\":\"D\"},\"coursesTeachingList\":[]," +
                "\"coursesLearningList\":[],\"buddyList\":[],\"languageList\":[]," +
                "\"availability\":{\"monday\":[],\"tuesday\":[],\"wednesday\":[],\"thursday\":[]," +
                "\"friday\":[],\"saturday\":[],\"sunday\":[]}}");

        assertEquals(testuser1, fromjson);
    }

    @Test
    public void testToJson() throws Exception {
        assertEquals("{\"password\":\"Pepernoten01\",\"firstname\":\"Sinter\",\"lastname\":\"Klaas\"" +
                ",\"mail\":\"sinterklaas@sintmail.nl\",\"phonenumber\":\"+316123456789\"," +
                "\"study\":\"study1\",\"university\":\"university\",\"gender\":\"male\"," +
                "\"nationality\":\"NLD\",\"description\":\"It's-a-me\",\"location\":\"Spanje\"," +
                "\"birthday\":1,\"userID\":1,\"studyYear\":3,\"address\":{\"street\":\"A\"," +
                "\"housenumber\":\"B\",\"zipcode\":\"C\",\"city\":\"D\"},\"coursesTeachingList\":[]," +
                "\"coursesLearningList\":[],\"buddyList\":[],\"languageList\":[]," +
                "\"availability\":{\"monday\":[],\"tuesday\":[],\"wednesday\":[],\"thursday\":[]," +
                "\"friday\":[],\"saturday\":[],\"sunday\":[]}}", testuser1.toJson());
    }

    @Test
    public void testToString() {
        assertEquals("1\n" +
                "Pepernoten01\n" +
                "Sinter Klaas\n" +
                "1970-01-01\n" +
                "sinterklaas@sintmail.nl\n" +
                "+316123456789\n" +
                "A B, C D\n" +
                "study1\n" +
                "university\n" +
                "3\n" +
                "available: Maandag() Dinsdag() Woensdag() Donderdag() Vrijdag() Zaterdag() Zondag()\n" +
                "Courses teaching: []\n" +
                "Courses learning: []\n" +
                "Courses searching Buddy: []\n" +
                "male\n" +
                "NLD\n" +
                "languages: []\n" +
                "It's-a-me\n" +
                "Spanje", testuser1.toString());
    }

    @Test
    public void testNotEqualsObject() throws Exception {
        assertFalse(testuser1.equals('p'));
    }

    @Test
    public void testNotEqualsDifferID() {
        testuser2.setUserID(5);
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferPassword() {
        testuser2.setPassword("lolno");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferFirstName() {
        testuser2.setFirstname("Henk");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferLastName() {
        testuser2.setLastname("de Vries");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferBirthday() {
        testuser2.setBirthday(new Date());
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferMail() {
        testuser2.setMail("my@mail.com");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferPhonenumber() {
        testuser2.setPhonenumber("no");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferAddress() {
        testuser2.setAddress(new Address("9","9","9","9"));
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferStudy() {
        testuser2.setStudy("38");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferUniversity() {
        testuser2.setUniversity("Paypal");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferStudyYear() {
        testuser2.setStudyYear(50);
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferGender() {
        testuser2.setGender("Apache attack helicopter");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferNationality() {
        testuser2.setNationality("N/A");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferDescription() {
        testuser2.setDescription("new Description()");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferLocation() {
        testuser2.setLocation("[1, -3]");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferCoursesLearning() {
        testuser2.getCoursesLearningList().add("Maymays");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferCoursesTeaching() {
        testuser2.getCoursesTeachingList().add("Yu-Gi-Oh");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferBuddies() {
        testuser2.getBuddyList().add("azaidman");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferLanguages() {
        testuser2.getLanguageList().add("Estonian");
        assertNotEquals(testuser1, testuser2);
    }

    @Test
    public void testNotEqualsDifferAvailability() {
        testuser2.getAvailability().addTimePeriod(3, new TimePeriod(12, 13));
        assertNotEquals(testuser1, testuser2);
    }
}