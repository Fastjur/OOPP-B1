import org.junit.Before;
import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.*;

/**
 * Created by Fastjur on 10-12-2015.
 */
public class TimePeriodTest {

    private TimePeriod p, p2;

    @Before
    public void setUp() throws Exception {
        p = new TimePeriod(1, 2);
        p2 = new TimePeriod("00:01", "00:02");
    }

    @Test
    public void testToJson() throws Exception {
        String expected = "{\"start\":1,\"end\":2}";
        assertEquals(expected, p.toJson());
    }

    @Test
    public void testFromJson() throws Exception {
        TimePeriod a = new TimePeriod(1, 2);
        assertEquals(a, TimePeriod.fromJson(p.toJson()));
    }

    @Test
    public void testToString() throws Exception {
        String expected = "<TimePeriod(1-2)>";
        assertEquals(expected, p.toString());
    }

    @Test
    public void testEquals() throws Exception {
        TimePeriod a = new TimePeriod(1, 2);
        assertEquals(a, p);
        assertEquals(p, p2);
        assertEquals(p, p);
        assertFalse(p.equals("ayy"));
    }

    @Test
    public void testGetStart() throws Exception {
        assertEquals(1, p.getStart());
    }

    @Test
    public void testSetStart() throws Exception {
        p.setStart(50);
        assertEquals(50, p.getStart());
    }

    @Test
    public void testGetEnd() throws Exception {
        assertEquals(2, p.getEnd());
    }

    @Test
    public void testSetEnd() throws Exception {
        p.setEnd(55);
        assertEquals(55, p.getEnd());
    }
}