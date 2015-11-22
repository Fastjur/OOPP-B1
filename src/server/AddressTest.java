package server;
import static org.junit.Assert.*;

import org.junit.Test;

public class AddressTest {
	//Test: constructor
	@Test
	public void testAddress() {
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		String expected = new String("M. Rutgersweg 1, 2331NT Leiden\n"); 
		assertTrue(expected.equals(address.toString()));
	}
	
	//Test: toString
	@Test
	public void testToString() {
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		String expected = new String("M. Rutgersweg 1, 2331NT Leiden\n");
		assertTrue(expected.equals(address.toString()));
	}
	
	//Test: getStreet
	@Test
	public void testGetStreet() {
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		String expected = new String("M. Rutgersweg");
		assertTrue(expected.equals(address.getStreet()));
	}
	
	//Test: getHousenumber
	@Test
	public void testGetHousenumber() {			
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		String expected = new String("1");
		assertTrue(expected.equals(address.getHousenumber()));
	}

	//Test: getZipcode
	@Test
	public void testGetZipcode() {			
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		String expected = new String("2331NT");
		assertTrue(expected.equals(address.getZipcode()));
	}
	
	//Test: getCity
	@Test
	public void testGetCity() {			
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		String expected = new String("Leiden");
		assertTrue(expected.equals(address.getCity()));
	}
	
	//Test: setStreet
	@Test
	public void testSetStreet() {
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		address.setStreet("Street");
		String expected = new String("Street 1, 2331NT Leiden\n");
		assertTrue(expected.equals(address.toString()));
	}
	
	//Test: setHousenumber
	@Test
	public void testSetHousenumber() {
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		address.setHousenumber("3");
		String expected = new String("M. Rutgersweg 3, 2331NT Leiden\n");
		assertTrue(expected.equals(address.toString()));
	}
	
	//Test: setZipcode
	@Test
	public void testSetZipcode() {
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		address.setZipcode("0000AB");
		String expected = new String("M. Rutgersweg 1, 0000AB Leiden\n");
		assertTrue(expected.equals(address.toString()));
	}
	
	//Test: setCity
	@Test
	public void testSetCity() {
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		address.setCity("Delft");
		String expected = new String("M. Rutgersweg 1, 2331NT Delft\n");
		assertTrue(expected.equals(address.toString()));
	}
	
	//Test: equals1, compare Address to String
	@Test
	public void testEquals1() {
		String string = new String("M. Rutgersweg 1, 2331NT Leiden\n");
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		assertFalse(string.equals(address));
	}
	
	//Test: equals2, compare similar addresses
	@Test
	public void testEquals2() {
		Address address1 = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		Address address2 = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		assertTrue(address1.equals(address2));
	}
	
	//Test: equals3, compare addresses which differ by street
	@Test
	public void testEquals3() {
		Address address1 = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		Address address2 = new Address("M. Rutgerweg", "1", "2331NT", "Leiden");
		assertFalse(address1.equals(address2));
	}
	
	//Test: equals4, compare addresses which differ by housenumber
	@Test
	public void testEquals4() {
		Address address1 = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		Address address2 = new Address("M. Rutgersweg", "4", "2331NT", "Leiden");
		assertFalse(address1.equals(address2));
	}
	
	//Test: equals5, compare addresses which differ by zipcode
	@Test
	public void testEquals5() {
		Address address1 = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		Address address2 = new Address("M. Rutgersweg", "1", "2331NZ", "Leiden");
		assertFalse(address1.equals(address2));
	}
	
	//Test: equals6, compare addresses which differ by city
	@Test
	public void testEquals6() {
		Address address1 = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		Address address2 = new Address("M. Rutgersweg", "1", "2331NT", "Delft");
		assertFalse(address1.equals(address2));
	}
	
	//Test: equals7, compare address to itself
	@Test
	public void testEquals7() {
		Address address = new Address("M. Rutgersweg", "1", "2331NT", "Leiden");
		assertTrue(address.equals(address));
	}
}
