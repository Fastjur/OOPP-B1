package server;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class AvailableDateTest {
	//Test: constructor
	@Test
	public void testAvailableDate() {
		AvailableDate available = new AvailableDate(new Date("11-11-2015"));
		assertTrue(available instanceof AvailableDate);
	}
	
	//Test: toString
	@Test
	public void testToString() {
		AvailableDate available = new AvailableDate(new Date("20-11-2015"));
		available.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		String expected = new String("20-11-2015\n10:13 - 14:10");
		assertTrue(expected.equals(available.toString()));
	}
	
	//Test: getDate
	@Test
	public void testGetDate() {
		AvailableDate available = new AvailableDate(new Date("11-11-2015"));
		String expected = new String("11-11-2015");
		assertTrue(expected.equals(available.getDate().toString()));
	}
	
	//Test: setDate
	@Test
	public void testSetDate() {
		AvailableDate available = new AvailableDate(new Date("11-11-2015"));
		available.setDate(new Date("12-11-2015"));
		String expected = new String("12-11-2015");
		assertTrue(expected.equals(available.getDate().toString()));
	}
	
	//Test: setDate2 
	@Test
	public void testSetDate2() {
		AvailableDate available = new AvailableDate(new Date("11-11-2015"));
		available.setDate(new Date("33-11-2015"));
		String expected = new String("11-11-2015");
		assertTrue(expected.equals(available.getDate().toString()));
	}
	
	//Test: getAvailableTimesSize1 
	@Test
	public void testGetAvailableTimesSize1() {
		AvailableDate available = new AvailableDate(new Date("11-11-2015"));
		TimePeriod availablePeriod1 = new TimePeriod(new Timepoint(10, 40), new Timepoint(12,16));
		TimePeriod availablePeriod2 = new TimePeriod(new Timepoint(13, 40), new Timepoint(14,16));
		
		available.addAvailableTimes(availablePeriod1);
		available.addAvailableTimes(availablePeriod2);
		
		assertTrue(available.getAvailableTimesSize() == 2);
	}
	
	//Test: getAvailableTimesSize2 (empty) 
	@Test
	public void testGetAvailableTimesSize2() {
		AvailableDate available = new AvailableDate(new Date("11-11-2015"));
		assertTrue(available.getAvailableTimesSize() == 0);
	}
	
	//Test: containsAvailableTimes1 (true)
	@Test
	public void testContainsAvailableTimes1() {
		ArrayList<TimePeriod> availableTimesList = new ArrayList<TimePeriod>();
		availableTimesList.add(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		assertTrue(availableTimesList.contains(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10))));	
	}
	
	//Test: containsAvailableTimes2 (false)
	@Test
	public void testContainsAvailableTimes2() {
		ArrayList<TimePeriod> availableTimesList = new ArrayList<TimePeriod>();
		availableTimesList.add(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		assertFalse(availableTimesList.contains(new TimePeriod(new Timepoint(11, 13), new Timepoint(14, 10))));	
	}
	
	//Test: addAvailableTimes 
	@Test
	public void testAddAvailableTimes() {
		AvailableDate availabledate = new AvailableDate(new Date("11-22-2015"));
		
		availabledate.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));	
		availabledate.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		availabledate.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(14, 10)));

		assertTrue(availabledate.getAvailableTimesSize() == 2);
	}

	//Test: equals1, compare to itself
	@Test
	public void equals1() {
		AvailableDate available = new AvailableDate(new Date("20-11-2015"));
		assertTrue(available.equals(available));
	}

	//Test: equals2, compare to another AvailableDate with the same value
	@Test
	public void equals2() {
		AvailableDate available1 = new AvailableDate(new Date("20-11-2015"));
		available1.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		available1.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(14, 10)));

		AvailableDate available2 = new AvailableDate(new Date("20-11-2015"));
		available2.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		available2.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(14, 10)));

		assertTrue(available1.equals(available2));
	}
	
	//Test: equals3, compare to another AvailableDate with another date
	@Test
	public void equals3() {
		AvailableDate available1 = new AvailableDate(new Date("20-11-2015"));
		available1.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		available1.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(14, 10)));
		
		AvailableDate available2 = new AvailableDate(new Date("21-11-2015"));
		available2.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		available2.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(14, 10)));
		assertFalse(available1.equals(available2));
	}
	
	//Test: equals4, compare to another AvailableDate with the same date, but another timeperiod
	@Test
	public void equals4() {
		AvailableDate available1 = new AvailableDate(new Date("20-11-2015"));
		available1.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		available1.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(14, 10)));
		
		AvailableDate available2 = new AvailableDate(new Date("20-11-2015"));
		available2.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		available2.addAvailableTimes(new TimePeriod(new Timepoint(11, 13), new Timepoint(14, 10)));
		assertFalse(available1.equals(available2));
	}
	
	//Test: equals5, compare to another AvailableDate with the same date, but it has more timeperiods
	@Test
	public void equals5() {
		AvailableDate available1 = new AvailableDate(new Date("20-11-2015"));
		available1.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		available1.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(14, 10)));
		
		AvailableDate available2 = new AvailableDate(new Date("20-11-2015"));
		available2.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		available2.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(14, 10)));
		available2.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(15, 10)));
		assertFalse(available1.equals(available2));
	}
	
	//Test: equals5, compare AvailableDate to a String
	@Test
	public void equals6() {
		AvailableDate available = new AvailableDate(new Date("20-11-2015"));
		available.addAvailableTimes(new TimePeriod(new Timepoint(10, 13), new Timepoint(14, 10)));
		available.addAvailableTimes(new TimePeriod(new Timepoint(13, 13), new Timepoint(14, 10)));	
		String string = new String("20-11-2015\n10:13 - 14:10\n13:13 - 14:10");
		
		assertFalse(string.equals(available));
	}

}
