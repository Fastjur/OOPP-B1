package server;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

@Deprecated
public class DEPRECATED_UserTest {
    //Test: constructor

    User getterSetterTest;
    @Before
    public void before() {
        AvailableTimes aTimes = new AvailableTimes();
        aTimes.addTimePeriod(1, new TimePeriod("17:00", "21:00"));
         getterSetterTest = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, aTimes, new ArrayList<String>(),
                new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
    }

    @Test
    public void testNotEqualsObject() {
        Object o = new Object();
        assertNotEquals(getterSetterTest, o);
    }

    //Test: toString1
    @Test
    public void testToString1() {
        String expected = "4457773\nP@ssW0rd\nLaura Folkerts\n1970-01-10\n"
                + "laura@mail.com\n0612345678\nM. Rutgersweg 1, 2331NT Leiden\nComputer Sciences\n"
                + "TU Delft\n1\navailable: \nCourses teaching: \nCourses learning: \nCourses searching Buddy: "
                + "\nwoman\nDutch\nlanguages: \nHello\nGPS";
        assertEquals(expected, getterSetterTest.toString());
    }

    @Test
    public void testjsonToUser() throws Exception {
        String json = "{\"password\":\"P@ssW0rd\",\"firstname\":\"Laura\",\"lastname\":"
                + "\"Folkerts\",\"mail\":\"laura@mail.com\",\"phonenumber\":\"0612345678\","
                + "\"study\":\"Computer Sciences\",\"university\":\"TU Delft\",\"gender\":"
                + "\"woman\",\"nationality\":\"Dutch\",\"description\":\"Hello\",\"location\":"
                + "\"GPS\",\"birthday\":853113600,\"userID\":4457773,\"studyYear\":1,\"address\":"
                + "{\"street\":\"M. Rutgersweg\",\"housenumber\":\"1\",\"zipcode\":\"2331NT\","
                + "\"city\":\"Leiden\"},\"coursesTeachingList\":[],\"coursesLearningList\":[],"
                + "\"buddyList\":[],\"languageList\":[],\"availableList\":[]}";
        User usr = User.jsonToUser(json);
        assertEquals(usr, getterSetterTest);
    }

    //Test: toString2
    @Test
    public void testToString2() {
        String helpOffered = "Calculus";
        getterSetterTest.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        getterSetterTest.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        getterSetterTest.getBuddyList().add(buddy);
        getterSetterTest.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        getterSetterTest.getLanguageList().add(language);

        String expected = "4457773\nP@ssW0rd\nLaura Folkerts\n1970-01-10\n"
                + "laura@mail.com\n0612345678\nM. Rutgersweg 1, 2331NT Leiden\nComputer Sciences\n"
                + "TU Delft\n1\navailable: Friday\nCourses teaching: Calculus\nCourses learning: OOP\n"
                + "Courses searching Buddy: Web&Database\nwoman\nDutch\nlanguages: Dutch\nHello\nGPS";

        assertEquals(expected, getterSetterTest.toString());
    }

    //Test: toString3
    @Test
    public void testToString3() {

        String helpOffered1 = "Calculus";
        String helpOffered2 = "Biology";
        getterSetterTest.getCoursesTeachingList().add(helpOffered1);
        getterSetterTest.getCoursesTeachingList().add(helpOffered2);
        String helpWanted1 = "OOP";
        String helpWanted2 = "Chemistry";
        getterSetterTest.getCoursesLearningList().add(helpWanted1);
        getterSetterTest.getCoursesLearningList().add(helpWanted2);
        String buddy1 = "Web&Database";
        String buddy2 = "JavaScript";
        getterSetterTest.getBuddyList().add(buddy1);
        getterSetterTest.getBuddyList().add(buddy2);
        getterSetterTest.getAvailability().add(new AvailableDate(new Weekdays("Monday")));
        getterSetterTest.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language1 = "Dutch";
        String language2 = "German";
        getterSetterTest.getLanguageList().add(language1);
        getterSetterTest.getLanguageList().add(language2);

        String expected = "4457773\nP@ssW0rd\nLaura Folkerts\n1970-01-10\n"
                + "laura@mail.com\n0612345678\nM. Rutgersweg 1, 2331NT Leiden\nComputer Sciences\n"
                + "TU Delft\n1\navailable: Monday;Friday\nCourses teaching: Calculus;Biology\n"
                + "Courses learning: OOP;Chemistry\nCourses searching Buddy: Web&Database;JavaScript\n"
                + "woman\nDutch\nlanguages: Dutch;German\nHello\nGPS";

        assertEquals(expected, getterSetterTest.toString());
    }

    //Test: getUserID
    @Test
    public void testGetUserID() {

        assertTrue(4457773 == getterSetterTest.getUserID());
    }

    //Test: getPassword
    @Test
    public void testGetPassword() {

        String expected = "P@ssW0rd";
        assertTrue(expected.equals(getterSetterTest.getPassword().toString()));
    }

    //Test: getFirstname
    @Test
    public void testGetFirstname() {

        String expected = "Laura";
        assertTrue(expected.equals(getterSetterTest.getFirstname().toString()));
    }

    //Test: getLastname
    @Test
    public void testGetLastname() {

        String expected = "Folkerts";
        assertTrue(expected.equals(getterSetterTest.getLastname().toString()));
    }

    //Test: getBirthday
    @Test
    public void testGetBirthday() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String expected = "1970-01-10";
        assertEquals(expected, df.format(getterSetterTest.getBirthday()));
    }

    //Test: getMail
    @Test
    public void testGetMail() {

        String expected = "laura@mail.com";
        assertTrue(expected.equals(getterSetterTest.getMail().toString()));
    }

    //Test: getPhonenumber
    @Test
    public void testGetPhonenumber() {

        String expected = "0612345678";
        assertTrue(expected.equals(getterSetterTest.getPhonenumber().toString()));
    }

    //Test: getAddress
    @Test
    public void testGetAddress() {

        String expected = "M. Rutgersweg 1, 2331NT Leiden\n";
        assertTrue(expected.equals(getterSetterTest.getAddress().toString()));
    }

    //Test: getStudy
    @Test
    public void testGetStudy() {

        String expected = "Computer Sciences";
        assertTrue(expected.equals(getterSetterTest.getStudy().toString()));
    }

    //Test: getUniversity
    @Test
    public void testGetUniversity() {

        String expected = "TU Delft";
        assertTrue(expected.equals(getterSetterTest.getUniversity().toString()));
    }

    //Test: getStudyYear
    @Test
    public void testGetStudyYear() {

        assertTrue(getterSetterTest.getStudyYear() == 1);
    }

    //Test: getGender
    @Test
    public void testGetGender() {

        String expected = "woman";
        assertTrue(expected.equals(getterSetterTest.getGender().toString()));
    }

    //Test: getUniversity
    @Test
    public void testGetNationality() {

        String expected = "Dutch";
        assertTrue(expected.equals(getterSetterTest.getNationality().toString()));
    }

    //Test: getDescription
    @Test
    public void testGetDescription() {

        String expected = "Hello";
        assertTrue(expected.equals(getterSetterTest.getDescription().toString()));
    }

    //Test: getLocation
    @Test
    public void testGetLocation() {

        String expected = "GPS";
        assertTrue(expected.equals(getterSetterTest.getLocation().toString()));
    }

    @Test
    public void testGetCoursesLearningListInitial() {
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(expected, getterSetterTest.getCoursesLearningList());
    }

    @Test
    public void testGetCoursesLearningListEditedEqual() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Koken");
        getterSetterTest.getCoursesLearningList().add("Koken");
        assertEquals(expected, getterSetterTest.getCoursesLearningList());
    }

    @Test
    public void testGetCoursesLearningListEditedNotEqual() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Fietsen");
        getterSetterTest.getCoursesLearningList().add("Koken");
        assertNotEquals(expected, getterSetterTest.getCoursesLearningList());
    }

    @Test
    public void testGetCoursesTeachingListInitial() {
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(expected, getterSetterTest.getCoursesTeachingList());
    }

    @Test
    public void testGetCoursesTeachingListEditedEqual() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Koken");
        getterSetterTest.getCoursesTeachingList().add("Koken");
        assertEquals(expected, getterSetterTest.getCoursesTeachingList());
    }

    @Test
    public void testGetCoursesTeachingListEditedNotEqual() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Fietsen");
        getterSetterTest.getCoursesTeachingList().add("Koken");
        assertNotEquals(expected, getterSetterTest.getCoursesTeachingList());
    }

    @Test
    public void testGetBuddyListInitial() {
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(expected, getterSetterTest.getBuddyList());
    }

    @Test
    public void testGetBuddyListEditedEqual() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Koken");
        getterSetterTest.getBuddyList().add("Koken");
        assertEquals(expected, getterSetterTest.getBuddyList());
    }

    @Test
    public void testGetBuddyListEditedNotEqual() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Fietsen");
        getterSetterTest.getBuddyList().add("Koken");
        assertNotEquals(expected, getterSetterTest.getBuddyList());
    }

    @Test
    public void testGetLanguageListInitial() {
        ArrayList<String> expected = new ArrayList<String>();
        assertEquals(expected, getterSetterTest.getLanguageList());
    }

    @Test
    public void testGetLanguageListEditedEqual() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Polish");
        getterSetterTest.getLanguageList().add("Polish");
        assertEquals(expected, getterSetterTest.getLanguageList());
    }

    @Test
    public void testGetLanguageEditedNotEqual() {
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("Russian");
        getterSetterTest.getLanguageList().add("Polish");
        assertNotEquals(expected, getterSetterTest.getLanguageList());
    }

    @Test
    public void testGetAvailableListInitial() {
        fail("Need to rewrite availability");
    }

    @Test
    public void testGetAvailableListEdited() {
        fail("Need to rewrite availability");
    }

    //Test: setUserId
    @Test
    public void testSetUserID() {

        getterSetterTest.setUserID(123456);
        assertTrue(getterSetterTest.getUserID() == 123456);
    }

    //Test: setPassword
    @Test
    public void testSetPassword() {

        getterSetterTest.setPassword("U$er");
        String expected = "U$er";
        assertTrue(expected.equals(getterSetterTest.getPassword()));
    }

    //Test: setFirstname
    @Test
    public void testSetFirstname() {

        getterSetterTest.setFirstname("Emile");
        String expected = "Emile";
        assertTrue(expected.equals(getterSetterTest.getFirstname()));
    }

    //Test: setLastname
    @Test
    public void testLastname() {

        getterSetterTest.setLastname("Lobo");
        String expected = "Lobo";
        assertTrue(expected.equals(getterSetterTest.getLastname()));
    }

    //Test: setBirthday
    @Test
    public void testSetBirthday() {
        Date expected = new Date(931305600);
        getterSetterTest.setBirthday(expected);
        assertEquals(expected, getterSetterTest.getBirthday());
    }

    //Test: setMail
    @Test
    public void testSetMail() {

        getterSetterTest.setMail("ron@mail.com");
        String expected = "ron@mail.com";
        assertTrue(expected.equals(getterSetterTest.getMail()));
    }

    //Test: setPhonenumber
    @Test
    public void testSetPhonenumber() {

        getterSetterTest.setPhonenumber("0698765432");
        String expected = "0698765432";
        assertTrue(expected.equals(getterSetterTest.getPhonenumber().toString()));
    }

    //Test: setAddress
    @Test
    public void testSetAddress() {

        getterSetterTest.setAddress(new Address("Street", "5", "1234AB", "Delft"));
        String expected = "Street 5, 1234AB Delft\n";
        assertTrue(expected.equals(getterSetterTest.getAddress().toString()));
    }

    //Test: setStudy
    @Test
    public void testsetStudy() {

        getterSetterTest.setStudy("LST");
        String expected = "LST";
        assertTrue(expected.equals(getterSetterTest.getStudy().toString()));
    }

    //Test: setUniversity
    @Test
    public void testSetUniversity() {

        getterSetterTest.setUniversity("Leiden University");
        String expected = "Leiden University";
        assertTrue(expected.equals(getterSetterTest.getUniversity().toString()));
    }

    //Test: setStudyYear
    @Test
    public void testSetStudyYear() {

        getterSetterTest.setStudyYear(3);
        assertTrue(getterSetterTest.getStudyYear() == 3);
    }

    //Test: setGender
    @Test
    public void testSetGender() {

        getterSetterTest.setGender("man");
        String expected = "man";
        assertTrue(expected.equals(getterSetterTest.getGender().toString()));
    }

    //Test: setNationality
    @Test
    public void testSetNationality() {

        getterSetterTest.setNationality("Brazilian");
        String expected = "Brazilian";
        assertTrue(expected.equals(getterSetterTest.getNationality().toString()));
    }

    //Test: setDescription
    @Test
    public void testSetDescription() {

        getterSetterTest.setDescriptionID("Bye");
        String expected = "Bye";
        assertTrue(expected.equals(getterSetterTest.getDescription().toString()));
    }

    //Test: setLocation
    @Test
    public void testSetLocation() {

        getterSetterTest.setLocation("GPS-location");
        String expected = "GPS-location";
        assertTrue(expected.equals(getterSetterTest.getLocation().toString()));
    }

    //Test: equals1, compare to itself
    @Test
    public void testEquals1() {

        String helpOffered = "Calculus";
        getterSetterTest.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        getterSetterTest.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        getterSetterTest.getBuddyList().add(buddy);
        getterSetterTest.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        getterSetterTest.getLanguageList().add(language);

        assertEquals(getterSetterTest, getterSetterTest);
    }

    //Test: equals2, compare to another user which has the same values
    @Test
    public void testEquals2() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertTrue(user1.equals(user2));
    }

    //Test: equals3, compare to another user which differs by userID
    @Test
    public void testEquals3() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457772, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals 4, compare to another user which differs by password
    @Test
    public void testEquals4() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "PassW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals5, compare to another user which differs by firstname
    @Test
    public void testEquals5() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Karen", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals6, compare to another user which differs by lastname
    @Test
    public void testEquals6() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Lobo", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);
    }

    //Test: equals7, compare to another user which differs by birthday
    @Test
    public void testEquals7() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853200000),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals8, compare to another user which differs by mail
    @Test
    public void testEquals8() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.nl", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals9, compare to another user which differs by phonenumber
    @Test
    public void testEquals9() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345679", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals10, compare to another user which differs by address
    @Test
    public void testEquals10() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "3", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals11, compare to another user which differs by course
    @Test
    public void testEquals11() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "LST", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals12, compare to another user which differs by university
    @Test
    public void testEquals12() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "Leiden University", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals13, compare to another user which differs by studyYear
    @Test
    public void testEquals13() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 2, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals14, compare to another user which differs by gender
    @Test
    public void testEquals14() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "man", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals15, compare to another user which differs by nationality
    @Test
    public void testEquals15() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Brazilian", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals16, compare to another user which differs by description
    @Test
    public void testEquals16() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Bye", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals17, compare to another user which differs by location
    @Test
    public void testEquals17() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS-location");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals19, compare to another user which differs by coursesTeaching
    @Test
    public void testEquals19() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered2 = "Assembly";
        user2.getCoursesTeachingList().add(helpOffered2);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals20, compare to another user which differs by CoursesLearning
    @Test
    public void testEquals20() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        String helpWanted2 = "Assembly";
        user2.getCoursesLearningList().add(helpWanted2);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals21, compare to another user which differs by buddy
    @Test
    public void testEquals21() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        String buddy2 = "Assembly";
        user2.getBuddyList().add(buddy2);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals22, compare to another user which differs by availableDate
    @Test
    public void testEquals22() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        String helpOffered = "Calculus";
        user1.getCoursesTeachingList().add(helpOffered);
        String helpWanted = "OOP";
        user1.getCoursesLearningList().add(helpWanted);
        String buddy = "Web&Database";
        user1.getBuddyList().add(buddy);
        user1.getAvailability().add(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.getLanguageList().add(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getCoursesTeachingList().add(helpOffered);
        user2.getCoursesLearningList().add(helpWanted);
        user2.getBuddyList().add(buddy);
        user2.getAvailability().add(new AvailableDate(new Weekdays("Monday")));
        user2.getLanguageList().add(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals23, compare to another user which differs by language
    @Test
    public void testEquals23() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user1.getLanguageList().add("Dutch");

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS");
        user2.getLanguageList().add("English");

        assertNotEquals(user1, user2);
    }
}
