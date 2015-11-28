package server;

import static org.junit.Assert.*;

import org.junit.Test;

public class WeekdaysTest {

	//Test: constructor
	@Test
	public void testWeekdays1() {
		Weekdays day = new Weekdays("Monday");
		assertTrue(day instanceof Weekdays);
	}
	
	//Test: constructor (invalid)
	@Test
	public void testWeekdays2() {
		Weekdays day = new Weekdays("Mo");
		String expected = new String("");
		assertTrue(expected.equals(day.toString()));
	}
	
	//Test: toString
	@Test
	public void testToString() {
		Weekdays day = new Weekdays("Monday");
		String expected = new String("Monday");
		assertTrue(expected.equals(day.toString()));
	}
	
	//Test: checkDay (false)
	@Test
	public void testCheckWeekDay1() {
		String day = new String("Hello");
		assertFalse(Weekdays.checkWeekday(day));
	}
	
	//Test: checkDay (true, Monday)
	@Test
	public void testCheckWeekDay2() {
		String day = new String("Monday");
		assertTrue(Weekdays.checkWeekday(day));
	}
	
	//Test: checkDay (true, Tuesday)
	@Test
	public void testCheckWeekDay3() {
		String day = new String("Tuesday");
		assertTrue(Weekdays.checkWeekday(day));
	}	
	
	//Test: checkDay (true, Wednesday)
	@Test
	public void testCheckWeekDay4() {
		String day = new String("Wednesday");
		assertTrue(Weekdays.checkWeekday(day));
	}
	
	//Test: checkDay (true, Thursday)
	@Test
	public void testCheckWeekDay5() {
		String day = new String("Thursday");
		assertTrue(Weekdays.checkWeekday(day));
	}
	
	//Test: checkDay (true, Friday)
	@Test
	public void testCheckWeekDay6() {
		String day = new String("Friday");
		assertTrue(Weekdays.checkWeekday(day));
	}
	
	//Test: checkDay (true, Saturday)
	@Test
	public void testCheckWeekDay7() {
		String day = new String("Saturday");
		assertTrue(Weekdays.checkWeekday(day));
	}
	
	//Test: checkDay (true, Sunday)
	@Test
	public void testCheckWeekDay8() {
		String day = new String("Sunday");
		assertTrue(Weekdays.checkWeekday(day));
	}
	
	//Test: getDay
	@Test
	public void testGetDay() {
		Weekdays day = new Weekdays("Monday");
		String expected = new String("Monday");
		assertTrue(expected.equals(day.getDay().toString()));
	}
	
	//Test: setDay
	@Test
	public void testSetDay() {
		Weekdays day = new Weekdays("Monday");
		day.setDay("Wednesday");
		String expected = new String("Wednesday");
		assertTrue(expected.equals(day.getDay().toString()));
	}
	
	//Test: equals1, compares the same Weekday
	@Test
	public void testEquals1() {
		Weekdays day = new Weekdays("Monday");
		assertTrue(day.equals(day));
	}
	
	//Test: equals2, compares a Weekday to another Weekday with the same value
	@Test
	public void testEquals2() {
		Weekdays day1 = new Weekdays("Monday");
		Weekdays day2 = new Weekdays("Monday");
		assertTrue(day1.equals(day2));
	}
	
	//Test: equals3, compares a Weekday to a different Weekday
	@Test
	public void testEquals3() {
		Weekdays day1 = new Weekdays("Monday");
		Weekdays day2 = new Weekdays("Thursday");
		assertFalse(day1.equals(day2));
	}
	
	//Test: equals4, compares a Weekday to an invalid Weekday
	@Test
	public void testEquals4() {
		Weekdays day1 = new Weekdays("Adios");
		Weekdays day2 = new Weekdays("Thursday");
		assertFalse(day1.equals(day2));
	}
	
	//Test: equals5, compares a Weekday to a String
	@Test
	public void testEquals5() {
		Weekdays day = new Weekdays("Monday");
		String string = new String("Monday");
		assertFalse(string.equals(day));
	}
}
