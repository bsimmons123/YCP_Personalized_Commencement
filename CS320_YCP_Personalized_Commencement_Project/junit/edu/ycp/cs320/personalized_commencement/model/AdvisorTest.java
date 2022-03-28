package edu.ycp.cs320.personalized_commencement.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the Advisor model class.
 *
 */
public class AdvisorTest {
private Advisor model;
private Advisor model2;
	
	@Before
	public void setup() {
		model = new Advisor();
		model.setDecision(true);
		model.setPassword("Brandon'sMom1");
		model.setEmail("EthanLicksWindows@hotmail.yahoo.brown");
		
		model2 = new Advisor();
		model2.setDecision(true);
		model2.setPassword("Brandon'sDad1");
		model2.setEmail("EthanLicksLinux@yahoo.com");
		
	}
	
	// getters
	@Test
	public void testGetDecision() {
		assertEquals(true, model.getDecision());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("Brandon'sMom1", model.getPassword());
	}
	
	@Test
	public void testGetEmail() {
		assertEquals("EthanLicksWindows@hotmail.yahoo.brown", model.getEmail());
	}
	
	@Test
	public void testGetDecision2() {
		assertEquals(true, model2.getDecision());
	}
	// getters for second model
	@Test
	public void testGetPassword2() {
		assertEquals("Brandon'sDad1", model2.getPassword());
	}
	
	@Test
	public void testGetEmail2() {
		assertEquals("EthanLicksLinux@yahoo.com", model2.getEmail());
	}
	
	// setters
	@Test
	public void testSetDecision() {
		model.setDecision(false);
		assertEquals(false, model.getDecision());
	}
	
	@Test
	public void testSetPassword() {
		model.setPassword("insertPasswordHere");
		assertEquals("insertPasswordHere", model.getPassword());
	}
	
	@Test
	public void testSetEmail() {
		model.setEmail("MyEmail@stuff.gov");
		assertEquals("MyEmail@stuff.gov", model.getEmail());
	}
	
	//setters for model 2
	@Test
	public void testSetDecision2() {
		model.setDecision(true);
		assertEquals(true, model2.getDecision());
	}
	
	@Test
	public void testSetPassword2() {
		model2.setPassword("emoaddict");
		assertEquals("emoaddict", model2.getPassword());
	}
	
	@Test
	public void testSetEmail2() {
		model2.setEmail("email@gov.gov");
		assertEquals("email@gov.gov", model2.getEmail());
	}
	
}
