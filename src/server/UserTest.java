package server;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;


public class UserTest {
    //Test: constructor

    User getterSetterTest = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
            "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
            "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(),
            new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
    @Test
    public void testUser() {
        assertTrue(getterSetterTest instanceof User);
    }

    //Test: toString1
    @Test
    public void testToString1() {
        String expected = "4457773\nP@ssW0rd\nLaura Folkerts\n13-01-1997\n"
                + "laura@mail.com\n0612345678\nM. Rutgersweg 1, 2331NT Leiden\nComputer Sciences\n"
                + "TU Delft\n1\navailable: \nCourses teaching: \nCourses learning: \nCourses searching Buddy: "
                + "\nwoman\nDutch\nlanguages: \nHello\nGPS\nIMG";
        assertTrue(expected.equals(getterSetterTest.toString()));
    }

    //Test: toString2
    @Test
    public void testToString2() {
        String helpOffered = "Calculus";
        getterSetterTest.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        getterSetterTest.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        getterSetterTest.addBuddy(buddy);
        getterSetterTest.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        getterSetterTest.addLanguage(language);

        String expected = "4457773\nP@ssW0rd\nLaura Folkerts\n13-01-1997\n"
                + "laura@mail.com\n0612345678\nM. Rutgersweg 1, 2331NT Leiden\nComputer Sciences\n"
                + "TU Delft\n1\navailable: Friday\nCourses teaching: Calculus\nCourses learning: OOP\n"
                + "Courses searching Buddy: Web&Database\nwoman\nDutch\nlanguages: Dutch\nHello\nGPS\nIMG";

        assertTrue(expected.equals(getterSetterTest.toString()));
    }

    //Test: toString3
    @Test
    public void testToString3() {

        String helpOffered1 = "Calculus";
        String helpOffered2 = "Biology";
        getterSetterTest.addCoursesTeaching(helpOffered1);
        getterSetterTest.addCoursesTeaching(helpOffered2);
        String helpWanted1 = "OOP";
        String helpWanted2 = "Chemistry";
        getterSetterTest.addCoursesLearning(helpWanted1);
        getterSetterTest.addCoursesLearning(helpWanted2);
        String buddy1 = "Web&Database";
        String buddy2 = "JavaScript";
        getterSetterTest.addBuddy(buddy1);
        getterSetterTest.addBuddy(buddy2);
        getterSetterTest.addAvailable(new AvailableDate(new Weekdays("Monday")));
        getterSetterTest.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language1 = "Dutch";
        String language2 = "German";
        getterSetterTest.addLanguage(language1);
        getterSetterTest.addLanguage(language2);

        String expected = "4457773\nP@ssW0rd\nLaura Folkerts\n13-01-1997\n"
                + "laura@mail.com\n0612345678\nM. Rutgersweg 1, 2331NT Leiden\nComputer Sciences\n"
                + "TU Delft\n1\navailable: Monday;Friday\nCourses teaching: Calculus;Biology\n"
                + "Courses learning: OOP;Chemistry\nCourses searching Buddy: Web&Database;JavaScript\n"
                + "woman\nDutch\nlanguages: Dutch;German\nHello\nGPS\nIMG";

        assertTrue(expected.equals(getterSetterTest.toString()));
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

        String expected = "13-01-1997";
        assertTrue(expected.equals(getterSetterTest.getBirthday().toString()));
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

    //Test: getPicture
    @Test
    public void testGetPicture() {

        String expected = "IMG";
        assertTrue(expected.equals(getterSetterTest.getPicture().toString()));
    }

    //Test: getStudysTeachingSize1 (empty)
    @Test
    public void testgetStudysTeachingSize1() {

        assertTrue(getterSetterTest.getStudysTeachingSize() == 0);
    }

    //Test: getStudysTeachingSize2
    @Test
    public void testgetStudysTeachingSize2() {

        String helpOffered = "Calculus";
        String helpOffered2 = "Calculus";
        getterSetterTest.addCoursesTeaching(helpOffered);
        getterSetterTest.addCoursesTeaching(helpOffered2);
        assertTrue(getterSetterTest.getStudysTeachingSize() == 1);
    }

    //Test: getStudysLearningSize1 (empty)
    @Test
    public void testgetStudysLearningSize1() {

        assertTrue(getterSetterTest.getStudysLearningSize() == 0);
    }

    //Test: getStudysLearningSize2
    @Test
    public void testgetStudysLearningSize2() {

        String helpWanted = "Calculus";
        String helpWanted2 = "Calculus";
        getterSetterTest.addCoursesLearning(helpWanted);
        getterSetterTest.addCoursesLearning(helpWanted2);
        assertTrue(getterSetterTest.getStudysLearningSize() == 1);
    }

    //Test: getBuddySize1 (empty)
    @Test
    public void testContainsBuddy1() {

        assertTrue(getterSetterTest.getBuddySize() == 0);
    }

    //Test: getBuddySize2
    @Test
    public void testContains2() {

        getterSetterTest.addBuddy("OOP");
        getterSetterTest.addBuddy("OOP");
        assertTrue(getterSetterTest.getBuddySize() == 1);
    }

    //Test: getAvailableSize1 (empty)
    @Test
    public void testGetAvailableSize1() {

        assertTrue(getterSetterTest.getAvailableSize() == 0);
    }

    //Test: getAvailableSize2
    @Test
    public void testGetAvailableSize2() {

        AvailableDate available = new  AvailableDate(new Weekdays("Monday"));
        AvailableDate available2 = new  AvailableDate(new Weekdays("Monday"));
        getterSetterTest.addAvailable(available);
        getterSetterTest.addAvailable(available2);
        assertTrue(getterSetterTest.getAvailableSize() == 1);
    }

    //Test: getLanguageSize1 (empty)
    @Test
    public void testGetLanguageSize1() {

        assertTrue(getterSetterTest.getLanguageSize() == 0);
    }

    //Test: getLanguageSize2
    @Test
    public void testGetLanguageSize2() {

        getterSetterTest.addLanguage("Dutch");
        getterSetterTest.addLanguage("Dutch");
        assertTrue(getterSetterTest.getLanguageSize() == 1);
    }

    //Test: containsCoursesTeaching
    @Test
    public void testContainsCoursesTeaching() {

        String helpOffered = "Calculus";
        String helpOffered2 = "OOP";
        getterSetterTest.addCoursesTeaching(helpOffered);
        getterSetterTest.addCoursesTeaching(helpOffered2);
        assertTrue(getterSetterTest.containsCoursesTeaching(helpOffered2));
    }


    //Test: containsCoursesLearning
    @Test
    public void testContainsCoursesLearning() {

        String helpWanted = "Calculus";
        String helpWanted2 = "OOP";
        getterSetterTest.addCoursesLearning(helpWanted);
        getterSetterTest.addCoursesLearning(helpWanted2);
        assertTrue(getterSetterTest.containsCoursesLearning(helpWanted2));
    }

    //Test: containsBuddy
    @Test
    public void testContainsBuddy() {

        String buddy1 = "Calculus";
        String buddy2 = "OOP";
        getterSetterTest.addBuddy(buddy1);
        getterSetterTest.addBuddy(buddy2);
        assertTrue(getterSetterTest.containsBuddy(buddy2));
    }

    //Test: containsAvailable
    @Test
    public void testContainsAvailable() {

        AvailableDate available = new AvailableDate(new Weekdays("Monday"));
        AvailableDate available2 = new AvailableDate(new Weekdays("Friday"));
        getterSetterTest.addAvailable(available);
        getterSetterTest.addAvailable(available2);
        assertTrue(getterSetterTest.containsAvailable(available2));
    }

    //Test: containsLanguage
    @Test
    public void testContainsLanguage() {

        String language1 = "Dutch";
        String language2 = "English";
        getterSetterTest.addLanguage(language1);
        getterSetterTest.addLanguage(language2);
        assertTrue(getterSetterTest.containsLanguage(language2));
    }

    //Test: addCoursesTeaching
    @Test
    public void testAddCoursesTeaching() {

        getterSetterTest.addCoursesTeaching("Calculus");
        getterSetterTest.addCoursesTeaching("Calculus");
        assertTrue(getterSetterTest.getStudysTeachingSize() == 1);
    }

    //Test: addCoursesLearning
    @Test
    public void testAddCoursesLearning() {

        getterSetterTest.addCoursesLearning("Calculus");
        getterSetterTest.addCoursesLearning("Calculus");
        assertTrue(getterSetterTest.getStudysLearningSize() == 1);
    }

    //Test: addBuddy
    @Test
    public void testAddBuddy() {

        getterSetterTest.addBuddy("Calculus");
        getterSetterTest.addBuddy("Calculus");
        assertTrue(getterSetterTest.getBuddySize() == 1);
    }

    //Test: addAvailable
    @Test
    public void testAddAvailable() {

        getterSetterTest.addAvailable(new AvailableDate(new Weekdays("Monday")));
        getterSetterTest.addAvailable(new AvailableDate(new Weekdays("Monday")));
        assertTrue(getterSetterTest.getAvailableSize() == 1);
    }

    //Test: addLanguage
    @Test
    public void testAddLanguage() {

        getterSetterTest.addLanguage("Dutch");
        getterSetterTest.addLanguage("Dutch");
        assertTrue(getterSetterTest.getLanguageSize() == 1);
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

        getterSetterTest.setBirthday(new Date(931305600));
        String expected = "07-07-1999";
        assertTrue(expected.equals(getterSetterTest.getBirthday().toString()));
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

    //Test: setPicture
    @Test
    public void testSetPicture() {

        getterSetterTest.setPicture("PNG");
        String expected = "PNG";
        assertTrue(expected.equals(getterSetterTest.getPicture().toString()));
    }

    //Test: equals1, compare to itself
    @Test
    public void testEquals1() {

        String helpOffered = "Calculus";
        getterSetterTest.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        getterSetterTest.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        getterSetterTest.addBuddy(buddy);
        getterSetterTest.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        getterSetterTest.addLanguage(language);

        assertTrue(getterSetterTest.equals(getterSetterTest));
    }

    //Test: equals2, compare to another user which has the same values
    @Test
    public void testEquals2() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457772, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "PassW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Karen", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Lobo", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);
    }

    //Test: equals7, compare to another user which differs by birthday
    @Test
    public void testEquals7() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853200000),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.nl", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345679", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "3", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "LST", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "Leiden University", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 2, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "man", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Brazilian", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Bye", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS-location", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "PNG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered2 = "Assembly";
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        String helpWanted2 = "Assembly";
        user2.addCoursesLearning(helpWanted2);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals21, compare to another user which differs by buddy
    @Test
    public void testEquals21() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        String buddy2 = "Assembly";
        user2.addBuddy(buddy2);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        user2.addLanguage(language);

        assertFalse(user1.equals(user2));
    }

    //Test: equals22, compare to another user which differs by availableDate
    @Test
    public void testEquals22() {
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
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
        User user1 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        String helpOffered = "Calculus";
        user1.addCoursesTeaching(helpOffered);
        String helpWanted = "OOP";
        user1.addCoursesLearning(helpWanted);
        String buddy = "Web&Database";
        user1.addBuddy(buddy);
        user1.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language = "Dutch";
        user1.addLanguage(language);

        User user2 = new User(4457773, "P@ssW0rd", "Laura", "Folkerts", new Date(853113600),
                "laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"),
                "Computer Sciences", "TU Delft", 1, new ArrayList<AvailableDate>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), "woman", "Dutch", "Hello", "GPS", "IMG");
        user2.addCoursesTeaching(helpOffered);
        user2.addCoursesLearning(helpWanted);
        user2.addBuddy(buddy);
        user2.addAvailable(new AvailableDate(new Weekdays("Friday")));
        String language2 = "English";
        user2.addLanguage(language2);

        assertFalse(user1.equals(user2));
    }
}
