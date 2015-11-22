package server;
import static org.junit.Assert.*;

import org.junit.Test;

public class TimepointTest {
	//Test: constructor
	@Test
	public void testTimePoint() {
		Timepoint timepoint = new Timepoint(10, 40);
		String expected = new String("10:40");
		assertTrue(expected.equals(timepoint.toString()));
	}
	
	//Test: toString1
	@Test
	public void testToString1() {
		Timepoint timepoint = new Timepoint(10, 40);
		String expected = new String("10:40");
		assertTrue(expected.equals(timepoint.toString()));
	}	
	
	//Test: toString2, hour < 10
	@Test
	public void testToString2() {
		Timepoint timepoint = new Timepoint(9, 40);
		String expected = new String("09:40");
		assertTrue(expected.equals(timepoint.toString()));
	}	
	
	//Test: toString3, minute < 10
	@Test
	public void testToString3() {
		Timepoint timepoint = new Timepoint(10, 5);
		String expected = new String("10:05");
		assertTrue(expected.equals(timepoint.toString()));
	}	
	
	//Test: getHour
	@Test
	public void testGetHour() {
		Timepoint timepoint = new Timepoint(10, 40);
		Integer expected = new Integer(10);
		assertTrue(expected.equals(timepoint.getHour()));
	}
	
	//Test: getMinute
	@Test
	public void testGetMinute() {
		Timepoint timepoint = new Timepoint(10, 40);
		Integer expected = new Integer(40);
		assertTrue(expected.equals(timepoint.getMinute()));
	}
	
	//Test: setHour1, valid hour
	@Test
	public void testSetHour1() {
		Timepoint timepoint = new Timepoint(10, 40);
		timepoint.setHour(11);
		String expected = new String("11:40");
		assertTrue(expected.equals(timepoint.toString()));
	}
	
	//Test: setHour2, invalid hour (hour not changed)
	@Test
	public void testSetHour2() {
		Timepoint timepoint = new Timepoint(10, 40);
		timepoint.setHour(24);
		String expected = new String("10:40");
		assertTrue(expected.equals(timepoint.toString()));
	}
	
	//Test: setMinute1, valid minute
	@Test
	public void testSetMinute1() {
		Timepoint timepoint = new Timepoint(10, 40);
		timepoint.setMinute(24);
		String expected = new String("10:24");
		assertTrue(expected.equals(timepoint.toString()));
	}
	
	//Test: setMinute2, invalid minute (minute not changed)
	@Test
	public void testSetMinute2() {
		Timepoint timepoint = new Timepoint(10, 40);
		timepoint.setMinute(60);
		String expected = new String("10:40");
		assertTrue(expected.equals(timepoint.toString()));
	}
	
	//Test: earlierThan1 (true)
	@Test
	public void testEarlierThan1() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(11, 40);
		assertTrue(timepoint1.earlierThan(timepoint2));
	}
	
	//Test: earlierThan2 (true)
	@Test
	public void testEarlierThan2() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(11, 30);
		assertTrue(timepoint1.earlierThan(timepoint2));
	}
	
	//Test: earlierThan3 (true)
	@Test
	public void testEarlierThan3() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(10, 50);
		assertTrue(timepoint1.earlierThan(timepoint2));
	}

	//Test: earlierThan4 (false)
	@Test
	public void testEarlierThan4() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(11, 40);
		assertFalse(timepoint2.earlierThan(timepoint1));
	}
	
	//Test: earlierThan5 (false)
	@Test
	public void testEarlierThan5() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(11, 30);
		assertFalse(timepoint2.earlierThan(timepoint1));
	}
	
	//Test: earlierThan6 (false)
	@Test
	public void testEarlierThan6() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(10, 50);
		assertFalse(timepoint2.earlierThan(timepoint1));
	}
	
	//Test: earlierThan7 , compare equal timepoints
	@Test
	public void testEarlierThan7() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(10, 40);
		assertFalse(timepoint2.earlierThan(timepoint1));
	}
	
	//Test: laterThan1 (true)
	@Test
	public void testLaterThan1() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(11, 40);
		assertTrue(timepoint2.laterThan(timepoint1));
	}
	
	//Test: laterThan2 (true)
	@Test
	public void testLaterThan2() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(11, 30);
		assertTrue(timepoint2.laterThan(timepoint1));
	}
	
	//Test: laterThan3 (true)
	@Test
	public void testLaterThan3() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(10, 50);
		assertTrue(timepoint2.laterThan(timepoint1));
	}
	
	//Test: laterThan4 (false)
	@Test
	public void testLaterThan4() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(11, 40);
		assertFalse(timepoint1.laterThan(timepoint2));
	}
	
	//Test: laterThan5 (false)
	@Test
	public void testLaterThan5() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(11, 30);
		assertFalse(timepoint1.laterThan(timepoint2));
	}
	
	//Test: laterThan6 (false)
	@Test
	public void testLaterThan6() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(10, 50);
		assertFalse(timepoint1.laterThan(timepoint2));
	}
	
	//Test: laterThan7, compare equal timepoints
	@Test
	public void testLaterThan7() {
		Timepoint timepoint1 = new Timepoint(11, 40);
		Timepoint timepoint2 = new Timepoint(11, 40);
		assertFalse(timepoint1.laterThan(timepoint2));
	}
	
	//Test: equals1, the same timepoint
	@Test
	public void testEquals1() {
		Timepoint timepoint = new Timepoint(10, 40);
		assertTrue(timepoint.equals(timepoint));
	}
	
	//Test: equals2, timepoint2 has the same value as timepoint1
	@Test
	public void testEquals2() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(10, 40);
		assertTrue(timepoint1.equals(timepoint2));
	}
	
	//Test: equals3, timepoints differ by hour
	@Test
	public void testEquals3() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(11, 40);
		assertFalse(timepoint1.equals(timepoint2));
	}
	
	//Test: equals4, timepoints differ by minute
	@Test
	public void testEquals4() {
		Timepoint timepoint1 = new Timepoint(10, 40);
		Timepoint timepoint2 = new Timepoint(10, 41);
		assertFalse(timepoint1.equals(timepoint2));
	}
	
	//Test: equals5, compare Timepoint to String
	@Test
	public void testEquals5() {
		Timepoint timepoint = new Timepoint(10, 40);
		String string = new String("10:40");
		assertFalse(string.equals(timepoint));
	}
}
