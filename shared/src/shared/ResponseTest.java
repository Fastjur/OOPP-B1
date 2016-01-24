package shared;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResponseTest {
	@Test
	public void testResponse(){
		Response res = new Response("resTo");
		assertTrue(res instanceof Response);
	}
	
	@Test
	public void testResponseEmpty() {
		Response res = new Response();
		assertTrue(res instanceof Response);
	}

	@Test
	public void testToString() {
		Response res = new Response("resTo");
		assertEquals("{ \"responseTo\" : \"resTo\" \"errorCode\" : \"0\" \"errorMessage\" : \"\" \"responseData\" : {} }", res.toString());
	}
	
	@Test
	public void testEquals1() {
		Response res1 = new Response("resTo");
		Response res2 = new Response("resTo");
		assertEquals(res1, res2);
	}
	
	@Test
	public void testEquals2() {
		Response res1 = new Response("resTo");
		String res2 = "resTo";
		assertNotEquals(res1, res2);
	}
}
