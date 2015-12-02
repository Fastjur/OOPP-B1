package server;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.File;


public class UserTest {
    //Test: constructor
    @Test
    public void testUser() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        assertTrue(user instanceof User);
    }

    //Test: empty constructor(User())
    @Test
    public void testUser2() {
        User user = new User();
        assertTrue(user instanceof User);
        assertTrue(user.equals(new User(-1, "", "", "", new Birthday(-1, -1, -1), "", "", new Address("", "", "", ""), "", "", -1, "", "", "Test User", "", "")));
    }

    //Test: jsonToUser
    @Test
    public void testJsonToUser() {
        User usr = new User();
        usr.jsonToUser(new File("database.json"));
        assertFalse(usr.equals(new User()));
        System.out.println(usr.toString());
    }

    //Test: toString1
    @Test
    public void testToString1() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("4457773\nP@ssW0rd\nLaura Folkerts\n13-01-1997\n"
                + "laura@mail.com\n0612345678\nM. Rutgersweg 1, 2331NT Leiden\nComputer Sciences\n"
                + "TU Delft\n1\navailable: \nCourses teaching: \nCourses learning: \nCourses searching Buddy: "
                + "\nwoman\nDutch\nlanguages: \nHello\nGPS\nIMG");
        assertTrue(expected.equals(user.toString()));
    }

    //Test: toString2
    @Test
    public void testToString2() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");

        String helpOffered = new String("Calculus");
        user.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user.addBuddy(buddy);
        user.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user.addLanguage(language);

        String expected = new String("4457773\nP@ssW0rd\nLaura Folkerts\n13-01-1997\n"
                + "laura@mail.com\n0612345678\nM. Rutgersweg 1, 2331NT Leiden\nComputer Sciences\n"
                + "TU Delft\n1\navailable: Friday\nCourses teaching: Calculus\nCourses learning: OOP\n"
                + "Courses searching Buddy: Web&Database\nwoman\nDutch\nlanguages: Dutch\nHello\nGPS\nIMG");

        assertTrue(expected.equals(user.toString()));
    }

    //Test: toString3
    @Test
    public void testToString3() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");

        String helpOffered1 = new String("Calculus");
        String helpOffered2 = new String("Biology");
        user.addCoursesTeaching(helpOffered1);
        user.addCoursesTeaching(helpOffered2);
        String helpWanted1 = new String("OOP");
        String helpWanted2 = new String("Chemistry");
        user.addCoursesLearning(helpWanted1);
        user.addCoursesLearning(helpWanted2);
        String buddy1 = new String("Web&Database");
        String buddy2 = new String("JavaScript");
        user.addBuddy(buddy1);
        user.addBuddy(buddy2);
        user.addAvailable(new AvailableDate(new Weekdays("Monday")));
        user.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language1 = new String("Dutch");
        String language2 = new String("German");
        user.addLanguage(language1);
        user.addLanguage(language2);

        String expected = new String("4457773\nP@ssW0rd\nLaura Folkerts\n13-01-1997\n"
                + "laura@mail.com\n0612345678\nM. Rutgersweg 1, 2331NT Leiden\nComputer Sciences\n"
                + "TU Delft\n1\navailable: Monday;Friday\nCourses teaching: Calculus;Biology\n"
                + "Courses learning: OOP;Chemistry\nCourses searching Buddy: Web&Database;JavaScript\n"
                + "woman\nDutch\nlanguages: Dutch;German\nHello\nGPS\nIMG");

        assertTrue(expected.equals(user.toString()));
    }

    //Test: getUserID
    @Test
    public void testGetUserID() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        assertTrue(4457773 == user.getUserID());
    }

    //Test: getPassword
    @Test
    public void testGetPassword() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("P@ssW0rd");
        assertTrue(expected.equals(user.getPassword().toString()));
    }

    //Test: getFirstname
    @Test
    public void testGetFirstname() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("Laura");
        assertTrue(expected.equals(user.getFirstname().toString()));
    }

    //Test: getLastname
    @Test
    public void testGetLastname() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("Folkerts");
        assertTrue(expected.equals(user.getLastname().toString()));
    }

    //Test: getBirthday
    @Test
    public void testGetBirthday() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("13-01-1997");
        assertTrue(expected.equals(user.getBirthday().toString()));
    }

    //Test: getMail
    @Test
    public void testGetMail() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("laura@mail.com");
        assertTrue(expected.equals(user.getMail().toString()));
    }

    //Test: getPhonenumber
    @Test
    public void testGetPhonenumber() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("0612345678");
        assertTrue(expected.equals(user.getPhonenumber().toString()));
    }

    //Test: getAddress
    @Test
    public void testGetAddress() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("M. Rutgersweg 1, 2331NT Leiden\n");
        assertTrue(expected.equals(user.getAddress().toString()));
    }

    //Test: getStudy
    @Test
    public void testGetStudy() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("Computer Sciences");
        assertTrue(expected.equals(user.getStudy().toString()));
    }

    //Test: getUniversity
    @Test
    public void testGetUniversity() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("TU Delft");
        assertTrue(expected.equals(user.getUniversity().toString()));
    }

    //Test: getStudyYear
    @Test
    public void testGetStudyYear() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        assertTrue(user.getStudyYear() == 1);
    }

    //Test: getGender
    @Test
    public void testGetGender() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("woman");
        assertTrue(expected.equals(user.getGender().toString()));
    }

    //Test: getUniversity
    @Test
    public void testGetNationality() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("Dutch");
        assertTrue(expected.equals(user.getNationality().toString()));
    }

    //Test: getDescription
    @Test
    public void testGetDescription() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("Hello");
        assertTrue(expected.equals(user.getDescription().toString()));
    }

    //Test: getLocation
    @Test
    public void testGetLocation() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("GPS");
        assertTrue(expected.equals(user.getLocation().toString()));
    }

    //Test: getPicture
    @Test
    public void testGetPicture() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String expected = new String("IMG");
        assertTrue(expected.equals(user.getPicture().toString()));
    }

    //Test: getStudysTeachingSize1 (empty)
    @Test
    public void testgetStudysTeachingSize1() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        assertTrue(user.getStudysTeachingSize() == 0);
    }

    //Test: getStudysTeachingSize2
    @Test
    public void testgetStudysTeachingSize2() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        String helpOffered2 = new String("Calculus");
        user.addCoursesTeaching(helpOffered);
        user.addCoursesTeaching(helpOffered2);
        assertTrue(user.getStudysTeachingSize() == 1);
    }

    //Test: getStudysLearningSize1 (empty)
    @Test
    public void testgetStudysLearningSize1() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        assertTrue(user.getStudysLearningSize() == 0);
    }

    //Test: getStudysLearningSize2
    @Test
    public void testgetStudysLearningSize2() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpWanted = new String("Calculus");
        String helpWanted2 = new String("Calculus");
        user.addCoursesLearning(helpWanted);
        user.addCoursesLearning(helpWanted2);
        assertTrue(user.getStudysLearningSize() == 1);
    }

    //Test: getBuddySize1 (empty)
    @Test
    public void testContainsBuddy1() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        assertTrue(user.getBuddySize() == 0);
    }

    //Test: getBuddySize2
    @Test
    public void testContains2() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.addBuddy("OOP");
        user.addBuddy("OOP");
        assertTrue(user.getBuddySize() == 1);
    }

    //Test: getAvailableSize1 (empty)
    @Test
    public void testGetAvailableSize1() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        assertTrue(user.getAvailableSize() == 0);
    }

    //Test: getAvailableSize2
    @Test
    public void testGetAvailableSize2() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        AvailableDate available = new AvailableDate(new Weekdays("Monday"));
        AvailableDate available2 = new AvailableDate(new Weekdays("Monday"));
        user.addAvailable(available);
        user.addAvailable(available2);
        assertTrue(user.getAvailableSize() == 1);
    }

    //Test: getLanguageSize1 (empty)
    @Test
    public void testGetLanguageSize1() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        assertTrue(user.getLanguageSize() == 0);
    }

    //Test: getLanguageSize2
    @Test
    public void testGetLanguageSize2() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.addLanguage("Dutch");
        user.addLanguage("Dutch");
        assertTrue(user.getLanguageSize() == 1);
    }

    //Test: containsCoursesTeaching
    @Test
    public void testContainsCoursesTeaching() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        String helpOffered2 = new String("OOP");
        user.addCoursesTeaching(helpOffered);
        user.addCoursesTeaching(helpOffered2);
        assertTrue(user.containsCoursesTeaching(helpOffered2));
    }


    //Test: containsCoursesLearning
    @Test
    public void testContainsCoursesLearning() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpWanted = new String("Calculus");
        String helpWanted2 = new String("OOP");
        user.addCoursesLearning(helpWanted);
        user.addCoursesLearning(helpWanted2);
        assertTrue(user.containsCoursesLearning(helpWanted2));
    }

    //Test: containsBuddy
    @Test
    public void testContainsBuddy() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String buddy1 = new String("Calculus");
        String buddy2 = new String("OOP");
        user.addBuddy(buddy1);
        user.addBuddy(buddy2);
        assertTrue(user.containsBuddy(buddy2));
    }

    //Test: containsAvailable
    @Test
    public void testContainsAvailable() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        AvailableDate available = new AvailableDate(new Weekdays("Monday"));
        AvailableDate available2 = new AvailableDate(new Weekdays("Friday"));
        user.addAvailable(available);
        user.addAvailable(available2);
        assertTrue(user.containsAvailable(available2));
    }

    //Test: containsLanguage
    @Test
    public void testContainsLanguage() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String language1 = new String("Dutch");
        String language2 = new String("English");
        user.addLanguage(language1);
        user.addLanguage(language2);
        assertTrue(user.containsLanguage(language2));
    }

    //Test: addCoursesTeaching
    @Test
    public void testAddCoursesTeaching() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.addCoursesTeaching("Calculus");
        user.addCoursesTeaching("Calculus");
        assertTrue(user.getStudysTeachingSize() == 1);
    }

    //Test: addCoursesLearning
    @Test
    public void testAddCoursesLearning() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.addCoursesLearning("Calculus");
        user.addCoursesLearning("Calculus");
        assertTrue(user.getStudysLearningSize() == 1);
    }

    //Test: addBuddy
    @Test
    public void testAddBuddy() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.addBuddy("Calculus");
        user.addBuddy("Calculus");
        assertTrue(user.getBuddySize() == 1);
    }

    //Test: addAvailable
    @Test
    public void testAddAvailable() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.addAvailable(new AvailableDate(new Weekdays("Monday")));
        user.addAvailable(new AvailableDate(new Weekdays("Monday")));
        assertTrue(user.getAvailableSize() == 1);
    }

    //Test: addLanguage
    @Test
    public void testAddLanguage() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.addLanguage("Dutch");
        user.addLanguage("Dutch");
        assertTrue(user.getLanguageSize() == 1);
    }

    //Test: setUserId
    @Test
    public void testSetUserID() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setUserID(123456);
        assertTrue(user.getUserID() == 123456);
    }

    //Test: setPassword
    @Test
    public void testSetPassword() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setPassword("U$er");
        String expected = new String("U$er");
        assertTrue(expected.equals(user.getPassword()));
    }

    //Test: setFirstname
    @Test
    public void testSetFirstname() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setFirstname("Emile");
        String expected = new String("Emile");
        assertTrue(expected.equals(user.getFirstname()));
    }

    //Test: setLastname
    @Test
    public void testLastname() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setLastname("Lobo");
        String expected = new String("Lobo");
        assertTrue(expected.equals(user.getLastname()));
    }

    //Test: setBirthday
    @Test
    public void testSetBirthday() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setBirthday(new Birthday(7, 7, 1999));
        String expected = new String("07-07-1999");
        assertTrue(expected.equals(user.getBirthday().toString()));
    }

    //Test: setMail
    @Test
    public void testSetMail() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setMail("ron@mail.com");
        String expected = new String("ron@mail.com");
        assertTrue(expected.equals(user.getMail()));
    }

    //Test: setPhonenumber
    @Test
    public void testSetPhonenumber() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setPhonenumber("0698765432");
        String expected = new String("0698765432");
        assertTrue(expected.equals(user.getPhonenumber().toString()));
    }

    //Test: setAddress
    @Test
    public void testSetAddress() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setAddress(new Address("Street", "5", "1234AB", "Delft"));
        String expected = new String("Street 5, 1234AB Delft\n");
        assertTrue(expected.equals(user.getAddress().toString()));
    }

    //Test: setStudy
    @Test
    public void testsetStudy() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setStudy("LST");
        String expected = new String("LST");
        assertTrue(expected.equals(user.getStudy().toString()));
    }

    //Test: setUniversity
    @Test
    public void testSetUniversity() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setUniversity("Leiden University");
        String expected = new String("Leiden University");
        assertTrue(expected.equals(user.getUniversity().toString()));
    }

    //Test: setStudyYear
    @Test
    public void testSetStudyYear() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setStudyYear(3);
        assertTrue(user.getStudyYear() == 3);
    }

    //Test: setGender
    @Test
    public void testSetGender() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setGender("man");
        String expected = new String("man");
        assertTrue(expected.equals(user.getGender().toString()));
    }

    //Test: setNationality
    @Test
    public void testSetNationality() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setNationality("Brazilian");
        String expected = new String("Brazilian");
        assertTrue(expected.equals(user.getNationality().toString()));
    }

    //Test: setDescription
    @Test
    public void testSetDescription() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setDescriptionID("Bye");
        String expected = new String("Bye");
        assertTrue(expected.equals(user.getDescription().toString()));
    }

    //Test: setLocation
    @Test
    public void testSetLocation() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setLocation("GPS-location");
        String expected = new String("GPS-location");
        assertTrue(expected.equals(user.getLocation().toString()));
    }

    //Test: setPicture
    @Test
    public void testSetPicture() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user.setPicture("PNG");
        String expected = new String("PNG");
        assertTrue(expected.equals(user.getPicture().toString()));
    }

    //Test: equals1, compare to itself
    @Test
    public void testEquals1() {
        User user = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user.addBuddy(buddy);
        user.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user.addLanguage(language);

        assertTrue(user.equals(user));
    }

    //Test: equals2, compare to another user which has the same values
    @Test
    public void testEquals2() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertTrue(user1.equals(user2));
    }

    //Test: equals3, compare to another user which differs by userID
    @Test
    public void testEquals3() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457772, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals 4, compare to another user which differs by password
    @Test
    public void testEquals4() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "PassW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals5, compare to another user which differs by firstname
    @Test
    public void testEquals5() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Karen", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals6, compare to another user which differs by lastname
    @Test
    public void testEquals6() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Lobo", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);
    }

    //Test: equals7, compare to another user which differs by birthday
    @Test
    public void testEquals7() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(14, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals8, compare to another user which differs by mail
    @Test
    public void testEquals8() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.nl", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals9, compare to another user which differs by phonenumber
    @Test
    public void testEquals9() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345679", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals10, compare to another user which differs by address
    @Test
    public void testEquals10() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "3", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals11, compare to another user which differs by course
    @Test
    public void testEquals11() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "LST", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals12, compare to another user which differs by university
    @Test
    public void testEquals12() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "Leiden University", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals13, compare to another user which differs by studyYear
    @Test
    public void testEquals13() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 2, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals14, compare to another user which differs by gender
    @Test
    public void testEquals14() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "man", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals15, compare to another user which differs by nationality
    @Test
    public void testEquals15() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Brazilian", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals16, compare to another user which differs by description
    @Test
    public void testEquals16() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Bye", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals17, compare to another user which differs by location
    @Test
    public void testEquals17() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS-location", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals18, compare to another user which differs by picture
    @Test
    public void testEquals18() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "PNG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals19, compare to another user which differs by coursesTeaching
    @Test
    public void testEquals19() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered2 = new String("Assembly");
        user2.addCoursesTeaching(helpOffered2);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals20, compare to another user which differs by CoursesLearning
    @Test
    public void testEquals20() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        String helpWanted2 = new String("Assembly");
        user2.addCoursesLearning(helpWanted2);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals21, compare to another user which differs by buddy
    @Test
    public void testEquals21() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        String buddy2 = new String("Assembly");
        user2.addBuddy(buddy2);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals22, compare to another user which differs by availableDate
    @Test
    public void testEquals22() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Monday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals23, compare to another user which differs by language
    @Test
    public void testEquals23() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = new String("Calculus");
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = new String("OOP");
        user1.addCoursesLearning(helpWanted);
        String buddy = new String("Web&Database");
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = new String("Dutch");
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Birthday(13, 1, 1997),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language2 = new String("English");
        user2.addLanguage(language2);

        assertFalse(user1.equals(user2));
    }
}
