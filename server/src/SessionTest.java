import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by Zoë van Steijn on 25-11-2015.
 */
public class SessionTest {

    @Test
    public void testSession1() {
        Session session = new Session(12345);
        assertEquals(12345, session.getUserId());
    }

    @Test
    public void testSession2() {
        Session session = new Session(-600);
        assertEquals(-600, session.getUserId());
    }

    //Test: SessionId length is 32.
    @Test
    public void testSession3() {
        Session session = new Session(1234);
        assertEquals(32, session.getSessionId().length());
    }

    @Test
    public void testGetUserId() {
        Session session = new Session(0);
        session.setUserId(4200);
        assertEquals(4200, session.getUserId());
    }

    @Test
    public void testGetSessionId() {
        Session session1 = new Session(100000);
        Session session2 = new Session(100000);
        assertFalse(session1.getSessionId().equals(session2.getSessionId()));
    }

    //Test: SessionId's are different (random).
    @Test
    public void testEqualsNegative() {
        Session session1 = new Session(1234);
        Session session2 = new Session(1234);
        assertFalse(session1.equals(session2));
    }

}
