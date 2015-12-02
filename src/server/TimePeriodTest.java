package server;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimePeriodTest {
    //Test: constructor1
    @Test
    public void testTimePeriod1() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        String string = new String("10:40 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: constructor2
    @Test
    public void testTimePeriod2() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(9, 50));
        String string = new String("10:40 - 10:40");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: toString
    @Test
    public void testToString() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        String string = new String("10:40 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: getBeginTime
    @Test
    public void testGetBeginTime() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        String string = new String("10:40");
        assertTrue(string.equals(timeperiod.getBeginTime().toString()));
    }

    //Test: getEndTime
    @Test
    public void testGetEndTime() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        String string = new String("11:50");
        assertTrue(string.equals(timeperiod.getEndTime().toString()));
    }

    //Test: setBeginTime1, invalid beginTime1
    @Test
    public void testSetBeginTime1() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setBeginTime(new Timepoint(12, 00));
        String string = new String("10:40 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: setBeginTime2, invalid beginTime2
    @Test
    public void testSetBeginTime2() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setBeginTime(new Timepoint(11, 51));
        String string = new String("10:40 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: setBeginTime3, valid beginTime1
    @Test
    public void testSetBeginTime3() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setBeginTime(new Timepoint(9, 50));
        String string = new String("09:50 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: setBeginTime4, valid beginTime2
    @Test
    public void testSetBeginTime4() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setBeginTime(new Timepoint(11, 40));
        String string = new String("11:40 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: setBeginTime5, beginTime=endTime
    @Test
    public void testSetBeginTime5() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setBeginTime(new Timepoint(11, 50));
        String string = new String("10:40 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: setEndTime1, invalid endTime1
    @Test
    public void testSetEndTime1() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setEndTime(new Timepoint(9, 40));
        String string = new String("10:40 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: setEndTime2, invalid endTime2
    @Test
    public void testSetEndTime2() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setEndTime(new Timepoint(10, 30));
        String string = new String("10:40 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: setEndTime3, valid endTime1
    @Test
    public void testEndTime3() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setEndTime(new Timepoint(12, 30));
        String string = new String("10:40 - 12:30");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: setEndTime4, valid endTime2
    @Test
    public void testEndTime4() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setEndTime(new Timepoint(11, 51));
        String string = new String("10:40 - 11:51");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: setEndTime5, beginTime=endTime
    @Test
    public void testEndTime5() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        timeperiod.setEndTime(new Timepoint(10, 40));
        String string = new String("10:40 - 11:50");
        assertTrue(string.equals(timeperiod.toString()));
    }

    //Test: overlap1
    @Test
    public void testOverlap1() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(10, 50), new Timepoint(11, 10));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(0, 0), new Timepoint(0, 0));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap2
    @Test
    public void testOverlap2() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(10, 50), new Timepoint(11, 20));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(0, 0), new Timepoint(0, 0));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap3
    @Test
    public void testOverlap3() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(10, 50), new Timepoint(11, 40));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(11, 20), new Timepoint(11, 40));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap4
    @Test
    public void testOverlap4() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(10, 50), new Timepoint(12, 30));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap5
    @Test
    public void testOverlap5() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(10, 50), new Timepoint(12, 50));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap6
    @Test
    public void testOverlap6() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(11, 20), new Timepoint(11, 20));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(0, 0), new Timepoint(0, 0));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap7
    @Test
    public void testOverlap7() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(11, 20), new Timepoint(11, 40));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(11, 20), new Timepoint(11, 40));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap8
    @Test
    public void testOverlap8() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap9
    @Test
    public void testOverlap9() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 50));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap10
    @Test
    public void testOverlap10() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(11, 40), new Timepoint(11, 50));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(11, 40), new Timepoint(11, 50));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap11
    @Test
    public void testOverlap11() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(11, 40), new Timepoint(12, 30));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(11, 40), new Timepoint(12, 30));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap12
    @Test
    public void testOverlap12() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(11, 40), new Timepoint(12, 50));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(11, 40), new Timepoint(12, 30));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap13
    @Test
    public void testOverlap13() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(12, 30), new Timepoint(12, 30));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(0, 0), new Timepoint(0, 0));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap14
    @Test
    public void testOverlap14() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(12, 30), new Timepoint(12, 50));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(0, 0), new Timepoint(0, 0));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: overlap15
    @Test
    public void testOverlap15() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(12, 50), new Timepoint(13, 00));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 20), new Timepoint(12, 30));
        TimePeriod overlapPeriod = new TimePeriod(new Timepoint(0, 0), new Timepoint(0, 0));
        assertTrue(overlapPeriod.equals(timeperiod1.overlap(timeperiod2)));
    }

    //Test: isEmpty1 (false)
    @Test
    public void testIsEmpty1() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        assertFalse(timeperiod.isEmpty());
    }

    //Test: isEmpty2 (true)
    @Test
    public void testIsEmpty2() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(10, 40));
        assertTrue(timeperiod.isEmpty());
    }

    //Test: equals1, compare timeperiod to itself
    @Test
    public void testEquals1() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        assertTrue(timeperiod.equals(timeperiod));
    }

    //Test: equals2, compare timeperiod1 to another timeperiod with the same value
    @Test
    public void testEquals2() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        assertTrue(timeperiod1.equals(timeperiod2));
    }

    //Test: equals3, timeperiod1 differs from timeperiod2 by beginTime
    @Test
    public void testEquals3() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(11, 40), new Timepoint(11, 50));
        assertFalse(timeperiod1.equals(timeperiod2));
    }

    //Test: equals4, timeperiod1 differs from timeperiod2 by endTime
    @Test
    public void testEquals4() {
        TimePeriod timeperiod1 = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        TimePeriod timeperiod2 = new TimePeriod(new Timepoint(10, 40), new Timepoint(12, 00));
        assertFalse(timeperiod1.equals(timeperiod2));
    }

    //Test: equals5, compare timeperiod to string
    @Test
    public void testEquals5() {
        TimePeriod timeperiod = new TimePeriod(new Timepoint(10, 40), new Timepoint(11, 50));
        String string = new String("10:40 - 11:50");
        assertFalse(string.equals(timeperiod));
    }
}
