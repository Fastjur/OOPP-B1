package server;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTest {
	//Test: constructor, valid date
	@Test
	public void testDate() {
		Date date = new Date("13-01-1997");
		String expected = new String ("13-01-1997");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: toString
	@Test
	public void testToString() {
		Date date = new Date("13-01-1997");
		String expected = new String ("13-01-1997");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: checkDate1, invalid date too early
	@Test 
	public void testCheckDate1() {
		Date date = new Date("01-01-1876");
		String expected = new String ("00-00-0000");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: checkDate2, invalid date too far in the future
	@Test 
	public void testCheckDate2() {
		Date date = new Date("01-01-2200");
		String expected = new String ("00-00-0000");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: checkDate3, invalid day due to leap year
	@Test 
	public void testCheckDate3() {
		Date date = new Date("29-02-1999");
		String expected = new String ("00-00-0000");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: checkDate4, invalid day due to leap year
	@Test 
	public void testCheckDate4() {
		Date date = new Date("29-02-1900");
		String expected = new String ("00-00-0000");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: checkDate5, valid day due to leap year
	@Test 
	public void testCheckDate5() {
		Date date = new Date("29-02-2000");
		String expected = new String ("29-02-2000");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: checkDate6, valid
	@Test
	public void testCheckDate6() {
		Date date = new Date("22-11-2015");
		String expected = new String ("22-11-2015");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: checkDate7, invalid due to day
	@Test
	public void testCheckDate7() {
		Date date = new Date("32-02-2000");
		String expected = new String ("00-00-0000");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: checkDate8, invalid due to month
	@Test
	public void testCheckDate8() {
		Date date = new Date("29-13-2000");
		String expected = new String ("00-00-0000");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: checkDate9, invalid due to wrong day in month
	@Test
	public void testCheckDate9() {
		Date date = new Date("31-11-2013");
		String expected = new String ("00-00-0000");
		assertTrue(expected.equals(date.toString()));
	}
	
	//Test: getDate1, invalid date
	@Test
	public void testGetDate1() {
		Date date = new Date("31-02-2000");
		String expected = new String ("00-00-0000");
		assertTrue(expected.equals(date.getDate()));
	}
	
	//Test: getDate2, valid date
	@Test
	public void testGetDate2() {
		Date date = new Date("01-02-2000");
		String expected = new String ("01-02-2000");
		assertTrue(expected.equals(date.getDate()));
	}
	
	//Test: setDate1, invalid date (no changes)
	@Test
	public void testSetDate1() {
		Date date = new Date("13-01-1997");
		date.setDate("29-02-1900");
		String expected = new String ("13-01-1997");
		assertTrue(expected.equals(date.getDate()));
	}
	
	//Test: setDate2, valid date
	@Test
	public void testSetDate2() {
		Date date = new Date("13-01-1997");
		date.setDate("12-02-2000");
		String expected = new String ("12-02-2000");
		assertTrue(expected.equals(date.getDate()));
	}
	
	//Test: equals1, compares date to the same date
	@Test
	public void testEquals1() {
		Date date = new Date("13-01-1997");
		assertTrue(date.equals(date));
	}
	
	//Test: equals2, compares date to other date with the same value
	@Test
	public void testEquals2() {
		Date date1 = new Date("13-01-1997");
		Date date2 = new Date("13-01-1997");
		assertTrue(date1.equals(date2));
	}
	
	//Test: equals3, compares date to other date which differs by day
	@Test
	public void testEquals3() {
		Date date1 = new Date("13-01-1997");
		Date date2 = new Date("14-01-1997");
		assertFalse(date1.equals(date2));
	}
	
	//Test equals4, compares to other date which differs by month
	@Test
	public void testEquals4() {
		Date date1 = new Date("13-01-1997");
		Date date2 = new Date("13-02-1997");
		assertFalse(date1.equals(date2));
	}
	
	//Test equals5, compares to other date which differs by year
	@Test
	public void testEquals5() {
		Date date1 = new Date("13-01-1997");
		Date date2 = new Date("13-01-2000");
		assertFalse(date1.equals(date2));
	}
	
	//Test equals6, compares date to string
	@Test
	public void testEquals6() {
		Date date = new Date("13-01-1997");
		String string = new String("13-01-1997");
		assertFalse(string.equals(date));
	}
}
