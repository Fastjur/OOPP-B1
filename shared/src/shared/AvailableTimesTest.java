package shared;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Fastjur on 10-12-2015.
 */
public class AvailableTimesTest {

    private AvailableTimes aTimes;

    @Before
    public void setUp() throws Exception {
        aTimes = new AvailableTimes();
        aTimes.addTimePeriod(1, new TimePeriod(150, 250));
        aTimes.addTimePeriod(1, new TimePeriod(250, 350));
        aTimes.addTimePeriod(2, new TimePeriod(1, 2));
        aTimes.addTimePeriod(3, new TimePeriod(2, 3));
        aTimes.addTimePeriod(4, new TimePeriod(3, 4));
        aTimes.addTimePeriod(5, new TimePeriod(4, 5));
        aTimes.addTimePeriod(6, new TimePeriod(5, 6));
        aTimes.addTimePeriod(7, new TimePeriod(6, 7));
    }

    @Test
    public void testToJson() throws Exception {
        System.out.println(aTimes.toJson());
        assertEquals("{\"monday\":[{\"start\":150,\"end\":250},{\"start\":250,\"end\":350}],\"tuesday\":[{\"start\":1,\"end\":2}],\"wednesday\":[{\"start\":2,\"end\":3}],\"thursday\":[{\"start\":3,\"end\":4}],\"friday\":[{\"start\":4,\"end\":5}],\"saturday\":[{\"start\":5,\"end\":6}],\"sunday\":[{\"start\":6,\"end\":7}]}",
                aTimes.toJson());
    }

    @Test
    public void testFromJson() throws Exception {
        AvailableTimes temp = AvailableTimes.fromJson(aTimes.toJson());
        assertEquals(aTimes, temp);
        assertNull(AvailableTimes.fromJson(null));
    }

    @Test
    public void testAddTimePeriod() throws Exception {
        AvailableTimes a = new AvailableTimes();
        assertEquals(a, new AvailableTimes());
        a.addTimePeriod(1, new TimePeriod(1, 2));
        assertEquals(a.getMonday().get(0), new TimePeriod(1, 2));
    }

    @Test
    public void testGetTimesOfDay() throws Exception {
        ArrayList<TimePeriod> monday = new ArrayList<>();
        ArrayList<TimePeriod> tuesday = new ArrayList<>();
        ArrayList<TimePeriod> wednesday = new ArrayList<>();
        ArrayList<TimePeriod> thursday = new ArrayList<>();
        ArrayList<TimePeriod> friday = new ArrayList<>();
        ArrayList<TimePeriod> saturday = new ArrayList<>();
        ArrayList<TimePeriod> sunday = new ArrayList<>();
        monday.add(new TimePeriod(150, 250));
        monday.add(new TimePeriod(250, 350));
        tuesday.add(new TimePeriod(1, 2));
        wednesday.add(new TimePeriod(2, 3));
        thursday.add(new TimePeriod(3, 4));
        friday.add(new TimePeriod(4, 5));
        saturday.add(new TimePeriod(5, 6));
        sunday.add(new TimePeriod(6, 7));

        assertEquals(monday, aTimes.getMonday());
        assertEquals(tuesday, aTimes.getTuesday());
        assertEquals(wednesday, aTimes.getWednesday());
        assertEquals(thursday, aTimes.getThursday());
        assertEquals(friday, aTimes.getFriday());
        assertEquals(saturday, aTimes.getSaturday());
        assertEquals(sunday, aTimes.getSunday());
    }

    @Test
    public void testGetPeriod() throws Exception {
        TimePeriod a = new TimePeriod(150, 250),
                b = new TimePeriod(1, 2),
                c = new TimePeriod(2, 3),
                d = new TimePeriod(3, 4),
                e = new TimePeriod(4, 5),
                f = new TimePeriod(5, 6),
                g = new TimePeriod(6, 7);
        assertEquals(a, aTimes.getPeriod(1, 0));
        assertEquals(b, aTimes.getPeriod(2, 0));
        assertEquals(c, aTimes.getPeriod(3, 0));
        assertEquals(d, aTimes.getPeriod(4, 0));
        assertEquals(e, aTimes.getPeriod(5, 0));
        assertEquals(f, aTimes.getPeriod(6, 0));
        assertEquals(g, aTimes.getPeriod(7, 0));
        assertNull(aTimes.getPeriod(99, 99));
    }

    @Test
    public void testRemoveTime() throws Exception {
        AvailableTimes bTimes = new AvailableTimes();
        AvailableTimes cTimes = new AvailableTimes();
        bTimes.addTimePeriod(1, new TimePeriod(250, 350));
        bTimes.addTimePeriod(2, new TimePeriod(1, 2));
        cTimes.addTimePeriod(2, new TimePeriod(1, 2));

        bTimes.removeTime(1, 0);
        assertEquals(bTimes, cTimes);
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Maandag(<TimePeriod(150-250)><TimePeriod(250-350)>) Dinsdag(<TimePeriod(1-2)>) Woensdag(<TimePeriod(2-3)>) Donderdag(<TimePeriod(3-4)>) Vrijdag(<TimePeriod(4-5)>) Zaterdag(<TimePeriod(5-6)>) Zondag(<TimePeriod(6-7)>)",
                aTimes.toString());
    }

    @Test
    public void testIntersect() throws Exception {
        AvailableTimes bTimes = new AvailableTimes(),
                intersect = new AvailableTimes();
        TimePeriod p1 = new TimePeriod(150, 250);
        TimePeriod p2 = new TimePeriod(2, 3);
        bTimes.addTimePeriod(1, p1);
        bTimes.addTimePeriod(1, new TimePeriod(500, 600));
        bTimes.addTimePeriod(3, p2);
        intersect.addTimePeriod(1, p1);
        intersect.addTimePeriod(3, p2);

        assertEquals(intersect, aTimes.intersect(bTimes));
        assertEquals(aTimes, aTimes.intersect(aTimes));
        assertNotEquals(aTimes, new AvailableTimes());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(8, aTimes.size());
        assertEquals(0, new AvailableTimes().size());
    }

    @Test
    public void testEquals() throws Exception {
        AvailableTimes bTimes = new AvailableTimes();
        AvailableTimes cTimes = new AvailableTimes();
        bTimes.addTimePeriod(1, new TimePeriod(1, 2));
        cTimes.addTimePeriod(1, new TimePeriod(1, 2));
        assertEquals(bTimes, cTimes);
        cTimes.addTimePeriod(2, new TimePeriod(2, 3));
        assertNotEquals(bTimes, cTimes);
        assertFalse(aTimes.equals("STRING"));
    }

    @Test
    public void testGetMonday() throws Exception {
        ArrayList<TimePeriod> expected = new ArrayList<>();
        expected.add(new TimePeriod(150, 250));
        expected.add(new TimePeriod(250, 350));
        assertEquals(expected, aTimes.getMonday());
    }

    @Test
    public void testSetMonday() throws Exception {
        aTimes.setMonday(new ArrayList<>());
        assertEquals(new ArrayList<TimePeriod>(), aTimes.getMonday());
    }

    @Test
    public void testGetTuesday() throws Exception {
        ArrayList<TimePeriod> expected = new ArrayList<>();
        expected.add(new TimePeriod(1, 2));
        assertEquals(expected, aTimes.getTuesday());
    }

    @Test
    public void testSetTuesday() throws Exception {
        aTimes.setTuesday(new ArrayList<>());
        assertEquals(new ArrayList<TimePeriod>(), aTimes.getTuesday());
    }

    @Test
    public void testGetWednesday() throws Exception {
        ArrayList<TimePeriod> expected = new ArrayList<>();
        expected.add(new TimePeriod(2, 3));
        assertEquals(expected, aTimes.getWednesday());
    }

    @Test
    public void testSetWednesday() throws Exception {
        aTimes.setWednesday(new ArrayList<>());
        assertEquals(new ArrayList<TimePeriod>(), aTimes.getWednesday());
    }

    @Test
    public void testGetThursday() throws Exception {
        ArrayList<TimePeriod> expected = new ArrayList<>();
        expected.add(new TimePeriod(3, 4));
        assertEquals(expected, aTimes.getThursday());
    }

    @Test
    public void testSetThursday() throws Exception {
        aTimes.setThursday(new ArrayList<>());
        assertEquals(new ArrayList<TimePeriod>(), aTimes.getThursday());
    }

    @Test
    public void testGetFriday() throws Exception {
        ArrayList<TimePeriod> expected = new ArrayList<>();
        expected.add(new TimePeriod(4, 5));
        assertEquals(expected, aTimes.getFriday());
    }

    @Test
    public void testSetFriday() throws Exception {
        aTimes.setFriday(new ArrayList<>());
        assertEquals(new ArrayList<TimePeriod>(), aTimes.getFriday());
    }

    @Test
    public void testGetSaturday() throws Exception {
        ArrayList<TimePeriod> expected = new ArrayList<>();
        expected.add(new TimePeriod(5, 6));
        assertEquals(expected, aTimes.getSaturday());
    }

    @Test
    public void testSetSaturday() throws Exception {
        aTimes.setSaturday(new ArrayList<>());
        assertEquals(new ArrayList<TimePeriod>(), aTimes.getSaturday());
    }

    @Test
    public void testGetSunday() throws Exception {
        ArrayList<TimePeriod> expected = new ArrayList<>();
        expected.add(new TimePeriod(6, 7));
        assertEquals(expected, aTimes.getSunday());
    }

    @Test
    public void testSetSunday() throws Exception {
        aTimes.setSunday(new ArrayList<>());
        assertEquals(new ArrayList<TimePeriod>(), aTimes.getSunday());
    }

    @Test
    public void testGetMondayReadable() throws Exception {
        assertEquals("02:30-04:10,04:10-05:50", aTimes.getReadable(1));
        assertEquals("00:01-00:02", aTimes.getReadable(2));
        aTimes.setMonday(new ArrayList<>());
        assertEquals("", aTimes.getReadable(1));
    }
}