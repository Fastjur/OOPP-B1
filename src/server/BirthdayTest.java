package server;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class BirthdayTest {

	//Test: constructor
	@Test
	public void testBirthday() {
		Birthday bday = new Birthday(13, 1, 1997);
		assertTrue(bday instanceof Birthday);
	}
	
	//Test: toString1
	@Test
	public void testToString1() {
		Birthday bday = new Birthday(13, 1, 1997);
		String expected = new String("13-01-1997");
		assertTrue(expected.equals(bday.toString()));
	}
	
	//Test: toString2
	@Test
	public void testToString2() {
		Birthday bday = new Birthday(1, 12, 1997);
		String expected = new String("01-12-1997");
		assertTrue(expected.equals(bday.toString()));
	}	
	
	//Test: toString3
	@Test
	public void testToString3() {
		Birthday bday = new Birthday(31, 12, 1997);
		String expected = new String("31-12-1997");
		assertTrue(expected.equals(bday.toString()));
	}
	
	//Test: getCalendar
	@Test
	public void testGetCalendar() {
		Birthday bday = new Birthday(13, 11, 1997);
		assertTrue(bday.getCalendar() instanceof Calendar);
	}
	
	//Test: getDay1
	@Test
	public void testGetDay1() {
		Birthday bday = new Birthday(13, 11, 1997);
		Integer expected = new Integer("13");
		assertTrue(expected.equals(bday.getDay()));
	}
	
	//Test: getDay2
	@Test
	public void testGetDa2y() {
		Birthday bday = new Birthday(1, 11, 1997);
		Integer expected = new Integer("1");
		assertTrue(expected.equals(bday.getDay()));
	}
	
	//Test: getMonth1
	@Test
	public void testGetMonth1() {
		Birthday bday = new Birthday(13, 12, 1997);
		Integer expected = new Integer("12");
		assertTrue(expected.equals(bday.getMonth()));		
	}
	
	//Test: getMonth2
	@Test
	public void testGetMonth2() {
		Birthday bday = new Birthday(13, 1, 1997);
		Integer expected = new Integer("1");
		assertTrue(expected.equals(bday.getMonth()));		
	}
	
	//Test: getYear
	@Test
	public void testGetYear() {
		Birthday bday = new Birthday(13, 1, 1997);
		Integer expected = new Integer("1997");
		assertTrue(expected.equals(bday.getYear()));		
	}
	
	//Test: setCalendar
	@Test
	public void testSetCalendar() {
		Birthday bday = new Birthday(13, 1, 1997);
		Calendar cal =new Calendar.Builder().setCalendarType("iso8601").setDate(2015, 11, 5).build();
		bday.setCalendar(cal);
		String expected = new String("05-12-2015");
		assertTrue(expected.equals(bday.toString()));
	}
	
	//Test: setDay
	@Test
	public void testSetDay() {
		Birthday bday = new Birthday(13, 1, 1997);
		Birthday bday2 = new Birthday(14, 1, 1997);
		bday.setDay(14);
		assertTrue(bday2.equals(bday));
	}
	
	//Test: setMonth
	@Test
	public void testSetMonth() {
		Birthday bday = new Birthday(13, 1, 1997);
		Birthday bday2 = new Birthday(13, 11, 1997);
		bday.setMonth(11);
		assertTrue(bday2.equals(bday));		
	}
	
	//Test: setYear
	@Test
	public void setYear() {
		Birthday bday = new Birthday(13, 1, 1997);
		Birthday bday2 = new Birthday(13, 1, 2015);
		bday.setYear(2015);
		assertTrue(bday2.equals(bday));		
	}
	
	//Test: equals1, compares the same Birthday
	@Test
	public void equals1() {
		Birthday bday = new Birthday(13, 1, 1997);
		assertTrue(bday.equals(bday));
	}
	
	//Test: equals2, compares bday with another Birthday with the same value
	@Test
	public void equals2() {
		Birthday bday1 = new Birthday(13, 1, 1997);
		Birthday bday2 = new Birthday(13, 1, 1997);
		assertTrue(bday1.equals(bday2));	
	}
	
	//Test: equals3, compares bday with another Birthday which differs by day
	@Test
	public void equals3() {
		Birthday bday1 = new Birthday(13, 1, 1997);
		Birthday bday2 = new Birthday(14, 1, 1997);
		assertFalse(bday1.equals(bday2));		
	}
	
	//Test: equals4, compares bday with another Birthday which differs by month
	@Test
	public void equals4() {
		Birthday bday1 = new Birthday(13, 1, 1997);
		Birthday bday2 = new Birthday(13, 2, 1997);
		assertFalse(bday1.equals(bday2));		
	}
	
	//Test: equals5, compares bday with another Birthday which differs by year
	@Test
	public void equals5() {
		Birthday bday1 = new Birthday(13, 1, 1997);
		Birthday bday2 = new Birthday(13, 1, 1998);
		assertFalse(bday1.equals(bday2));		
	}
	
	//Test: equals6, compares bday with a string
	@Test
	public void equals6() {
		Birthday bday = new Birthday(13, 1, 1997);
		String string = new String("13-01-1997");
		assertFalse(bday.equals(string));
	}
}
