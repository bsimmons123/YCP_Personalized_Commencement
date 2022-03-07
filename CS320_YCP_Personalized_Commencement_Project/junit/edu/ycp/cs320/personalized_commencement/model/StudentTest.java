package edu.ycp.cs320.personalized_commencement.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320_personalized_commencement.model.StudentModel;

/**
 * JUnit test class for the Student model class.
 *
 */
public class StudentTest {
	private StudentModel model;
	
	@Before
	public void setup() {
		model = new StudentModel();
		model.setStudentInfo("Info");
		model.setUsername("AndrewLikesGluten43");
		model.setPassword("Brandon'sMom1");
		model.setEmail("EthanLicksWindows@hotmail.yahoo.brown");
	}
	
	@Test
	public void testGetStudentInfo() {
		assertEquals("Info", model.getStudentInfo());
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("AndrewLikesGluten43", model.getUsername());
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
	public void testSetStudentInfo() {
		model.setStudentInfo(4);
		assertEquals(4, model.getStudentInfo());
	}
	
	@Test
	public void testSetUsername() {
		model.setUsername("insertUNameHere");
		assertEquals("insertUNameHere", model.getUsername());
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
}
