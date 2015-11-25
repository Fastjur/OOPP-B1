package server;
import static org.junit.Assert.*;
import org.junit.Test;


public class UserTest {
	//Test: constructor
	@Test
	public void testUser() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
				"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
				"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		assertTrue(user instanceof User);
	}
	
	//Test: toString
	@Test
	public void testToString() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
				"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
				"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("4457773\nLaura\n13-01-1997\nlaura@mail.com\n0612345678\n"
				+ "M. Rutgersweg 1, 2331NT Leiden\nTechnische Informatica\nTU Delft\n1\n"
				+ "available:\nhelpOffered:\nhelpWanted:\nwoman\nDutch\nHello"
				+ "\nHobbies");
		assertTrue(expected.equals(user.toString()));
		}
	
	//Test: getUserID
	@Test
	public void testGetUserID() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		assertTrue(4457773 == user.getUserID());
	}
	
	//Test: getName
	@Test
	public void testGetName() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");	
		String expected = new String("Laura");
		assertTrue(expected.equals(user.getName().toString()));
	}
	
	//Test: getBirthday
	@Test
	public void testGetBirthday() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("13-01-1997");
		assertTrue(expected.equals(user.getBirthday().toString()));
	}
	
	//Test: getMail
	@Test
	public void testGetMail() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("laura@mail.com");
		assertTrue(expected.equals(user.getMail().toString()));
	}
	
	//Test: getPhonenumber
	@Test
	public void testGetPhonenumber() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("0612345678");
		assertTrue(expected.equals(user.getPhonenumber().toString()));
	}
	
	//Test: getAddress
	@Test
	public void testGetAddress() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("M. Rutgersweg 1, 2331NT Leiden\n");
		assertTrue(expected.equals(user.getAddress().toString()));
	}
	
	//Test: getCourse
	@Test
	public void testGetCourse() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("Technische Informatica");
		assertTrue(expected.equals(user.getCourse().toString()));
	}
	
	//Test: getUniversity
	@Test
	public void testGetUniversity() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("TU Delft");
		assertTrue(expected.equals(user.getUniversity().toString()));
	}
	
	//Test: getExperienceInYears
	@Test
	public void testGetExperienceInYears() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		assertTrue(user.getExperienceInYears() == 1);
	}
	
	//Test: getGender
	@Test
	public void testGetGender() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("woman");
		assertTrue(expected.equals(user.getGender().toString()));
	}
	
	//Test: getUniversity
	@Test
	public void testGetNationality() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("Dutch");
		assertTrue(expected.equals(user.getNationality().toString()));
	}
	
	//Test: getDescription
	@Test
	public void testGetDescription() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String expected = new String("Hello");
		assertTrue(expected.equals(user.getDescription().toString()));
	}
	
	//Test: getHelpOfferedSize1 (empty)
	@Test
	public void testGetHelpOfferedSize1() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		assertTrue(user.getHelpOfferedSize() == 0);
	}
	
	//Test: getHelpOfferedSize2 
	@Test
	public void testGetHelpOfferedSize2() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		String helpOffered2 = new String("Calculus");
		user.addHelpOffered(helpOffered);
		user.addHelpOffered(helpOffered2);
		assertTrue(user.getHelpOfferedSize() == 1);
	}
	
	//Test: getHelpWantedSize1 (empty)
	@Test
	public void testGetHelpWantedSize1() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");		
		assertTrue(user.getHelpWantedSize() == 0);
	}
	
	//Test: getHelpWantedSize2
	@Test
	public void testGetHelpWantedSize2() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpWanted = new String("Calculus");
		String helpWanted2 = new String("Calculus");
		user.addHelpWanted(helpWanted);
		user.addHelpWanted(helpWanted2);
		assertTrue(user.getHelpWantedSize() == 1);
	}
	
	//Test: getAvailableSize1 (empty)
	@Test
	public void testGetAvailableSize1() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		assertTrue(user.getAvailableSize() == 0);
	}
	
	//Test: getAvailableSize2
	@Test
	public void testGetAvailable2() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		AvailableDate available = new  AvailableDate(new Date("11-11-2015"));
		AvailableDate available2 = new  AvailableDate(new Date("11-11-2015"));
		user.addAvailable(available);
		user.addAvailable(available2);
		assertTrue(user.getAvailableSize() == 1);
	}
	
	//Test: containsHelpOffered
	@Test
	public void testContainsHelpOffered() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		String helpOffered2 = new String("OOP");
		user.addHelpOffered(helpOffered);
		user.addHelpOffered(helpOffered2);
		assertTrue(user.containsHelpOffered(helpOffered2));
	}
	
	
	//Test: containsHelpWanted
	@Test
	public void testContainsHelpWanted() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpWanted = new String("Calculus");
		String helpWanted2 = new String("OOP");
		user.addHelpWanted(helpWanted);
		user.addHelpWanted(helpWanted2);
		assertTrue(user.containsHelpWanted(helpWanted2));		
	}
	
	//Test: containsAvailable
	@Test
	public void testContainsAvailable() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		AvailableDate available = new AvailableDate(new Date("12-12-2015"));
		AvailableDate available2 = new AvailableDate(new Date("13-11-2014"));
		user.addAvailable(available);
		user.addAvailable(available2);
		assertTrue(user.containsAvailable(available2));		
	}
	
	//Test: AddHelpOffered
	@Test
	public void testAddHelpOffered() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.addHelpOffered("Calculus");
		user.addHelpOffered("Calculus");
		assertTrue(user.getHelpOfferedSize() == 1);
	}
	
	//Test: AddHelpWanted
	@Test
	public void testAddHelpWanted() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.addHelpWanted("Calculus");
		user.addHelpWanted("Calculus");
		assertTrue(user.getHelpWantedSize() == 1);
	}
	
	//Test: AddAvailable
	@Test
	public void testAddAvailable() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");	
		user.addAvailable(new AvailableDate(new Date("13-01-2017")));
		user.addAvailable(new AvailableDate(new Date("13-01-2017")));
		assertTrue(user.getAvailableSize() == 1);
	}
	
	//Test: setUserId
	@Test
	public void testSetUserID() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");	
		user.setUserID(123456);
		assertTrue(user.getUserID() == 123456);
	}
	
	//Test: setName
	@Test
	public void testSetName() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");	
		user.setName("Emile");
		String expected = new String("Emile");
		assertTrue(expected.equals(user.getName()));
	}
	
	//Test: setBirthday
	@Test
	public void testSetBirthday() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setBirthday(new Date("07-07-1999"));
		String expected = new String("07-07-1999");
		assertTrue(expected.equals(user.getBirthday().toString()));
	}
	
	//Test: setMail
	@Test
	public void testSetMail() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setMail("ron@mail.com");
		String expected = new String("ron@mail.com");
		assertTrue(expected.equals(user.getMail()));
	}
	
	//Test: setPhonenumber
	@Test
	public void testSetPhonenumber() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setPhonenumber("0698765432");
		String expected = new String("0698765432");
		assertTrue(expected.equals(user.getPhonenumber().toString()));
	}
	
	//Test: setAddress
	@Test
	public void testSetAddress() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setAddress(new Address("Street", "5", "1234AB", "Delft"));
		String expected = new String("Street 5, 1234AB Delft\n");
		assertTrue(expected.equals(user.getAddress().toString()));
	}
	
	//Test: setCourse
	@Test
	public void testSetCourse() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setCourse("LST");
		String expected = new String("LST");
		assertTrue(expected.equals(user.getCourse().toString()));
	}
	
	//Test: setUniversity
	@Test
	public void testSetUniversity() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setUniversity("Leiden University");
		String expected = new String("Leiden University");
		assertTrue(expected.equals(user.getUniversity().toString()));
	}
	
	//Test: setExperienceInYears
	@Test
	public void testSetExperienceInYears() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setExperienceInYears(3);
		assertTrue(user.getExperienceInYears() == 3);
	}
	
	//Test: setGender
	@Test
	public void testSetGender() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setGender("man");
		String expected = new String("man");
		assertTrue(expected.equals(user.getGender().toString()));
	}
	
	//Test: setNationality
	@Test
	public void testSetNationality() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setNationality("Brazilian");
		String expected = new String("Brazilian");
		assertTrue(expected.equals(user.getNationality().toString()));
	}
	
	//Test: setDescription
	@Test
	public void testSetDescription() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user.setDescriptionID("Bye");
		String expected = new String("Bye");
		assertTrue(expected.equals(user.getDescription().toString()));
	}
	
	//Test: equals1, compare to itself
	@Test
	public void testEquals1() {
		User user = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user.addHelpWanted(helpWanted);
		user.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertTrue(user.equals(user));
	}
	
	//Test: equals2, compare to another user which has the same values
	@Test
	public void testEquals2() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertTrue(user1.equals(user2));
	}
	
	//Test: equals3, compare to another user which differs by userID
	@Test
	public void testEquals3() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457774, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals4, compare to another user which differs by name
	@Test
	public void testEquals4() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Karen", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals5, compare to another user which differs by birthday
	@Test
	public void testEquals5() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("14-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals6, compare to another user which differs by mail
	@Test
	public void testEquals6() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.nl", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals7, compare to another user which differs by phonenumber
	@Test
	public void testEquals7() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345679", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals8, compare to another user which differs by address
	@Test
	public void testEquals8() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "3", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals9, compare to another user which differs by course
	@Test
	public void testEquals9() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"LST", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals10, compare to another user which differs by university
	@Test
	public void testEquals10() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "Leiden University", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals11, compare to another user which differs by experienceInYears
	@Test
	public void testEquals11() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 3, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals12, compare to another user which differs by gender
	@Test
	public void testEquals12() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "man", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals13, compare to another user which differs by nationality
	@Test
	public void testEquals13() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Brazilian", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals14, compare to another user which differs by description
	@Test
	public void testEquals14() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Bye");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals15, compare to another user which differs by hobbies
	@Test
	public void testEquals15() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals16, compare to another user which differs by helpOffered
	@Test
	public void testEquals16() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals17, compare to another user which differs by helpWanted
	@Test
	public void testEquals17() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		assertFalse(user1.equals(user2));
	}
	
	//Test: equals18, compare to another user which differs by availableDate
	@Test
	public void testEquals18() {
		User user1 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		String helpOffered = new String("Calculus");
		user1.addHelpOffered(helpOffered);
		String helpWanted = new String("OOP");
		user1.addHelpWanted(helpWanted);
		user1.addAvailable(new AvailableDate(new Date("13-01-2017")));
		
		User user2 = new User(4457773, "Laura", new Date("13-01-1997"), 
			"laura@mail.com", "0612345678", new Address("M. Rutgersweg", "1", "2331NT", "Leiden"), 
			"Technische Informatica", "TU Delft", 1, "woman", "Dutch", "Hello");
		user2.addHelpOffered(helpOffered);
		user2.addHelpWanted(helpWanted);
		user2.addAvailable(new AvailableDate(new Date("13-01-2017")));
		user2.addAvailable(new AvailableDate(new Date("12-12-2015")));
		
		assertFalse(user1.equals(user2));
	}
	
}
