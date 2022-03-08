package edu.ycp.cs320.personalized_commencement.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320_personalized_commencement.model.UserModel;

/**
 * JUnit test class for the User model.
 */
public class UserTest {
private UserModel model;
	
	@Before
	public void setUp() {
		model = new UserModel();
		model.setPassword("Brandon'sMom1");
		model.setEmail("EthanLicksWindows@hotmail.yahoo.brown");
		model.setLogin(true);
		model.setStudent(true);
		model.setAdvisor(false);
	}
	
	
	// getters
	@Test
	public void testGetPassword() {
		assertEquals("Brandon'sMom1", model.getPassword());
	}
	
	@Test
	public void testGetEmail() {
		assertEquals("EthanLicksWindows@hotmail.yahoo.brown", model.getEmail());
	}
	
	@Test
	public void testGetLogin() {
		assertEquals(true, model.getLogin());
	}
	
	@Test
	public void testGetStudent() {
		assertEquals(true, model.getStudent());
	}
	
	@Test
	public void testGetAdvisor() {
		assertEquals(false, model.getAdvisor());
	}
	
	
	// setters
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
	
	@Test
	public void testSetLogin() {
		model.setLogin(false);
		assertEquals(false, model.getLogin());
	}
	
	@Test
	public void testSetStudent() {
		model.setStudent(false);
		assertEquals(false, model.getStudent());
	}
	
	@Test
	public void testSetAdvisor() {
		model.setAdvisor(true);
		assertEquals(true, model.getAdvisor());
	}
}
