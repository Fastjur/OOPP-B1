package test;

import static org.junit.Assert.*;
import org.junit.Test;
import communication.Request;


public class RequestTest {
	@Test
	public void testRequest1() {
		Request req = new Request("hallo");
		assertTrue(req instanceof Request);
	}
	
	@Test
	public void testReques2t() {
		Request req = new Request();
		assertTrue(req instanceof Request);
	}
	
	@Test
	public void testGetAction1() {
		Request req = new Request();
		assertNull(req.getAction());
	}
	
	@Test
	public void testGetAction2() {
		Request req = new Request("hallo");
		assertEquals("{ \"action\" : \"hallo\", \"requestData\" : \"{}\" }", req.toString());
	}
	
	@Test
	public void testGetRequestData() {
		Request req = new Request("hallo");
		req.putData("key", null);
		assertEquals("{key=null}", req.getRequestData().toString());
	}
}
