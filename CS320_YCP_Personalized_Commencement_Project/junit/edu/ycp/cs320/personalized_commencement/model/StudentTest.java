package edu.ycp.cs320.personalized_commencement.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the Student model class.
 *
 */
public class StudentTest {
	private Student model;
	private Student model2;
	
	@Before
	public void setup() {
		model = new Student();
//		model.setStudentInfo("Info");
		model.setPassword("Brandon'sMom1");
		model.setEmail("EthanLicksWindows@hotmail.yahoo.brown");
		model.setAdvisorId(0);
		model.setApproval(0);
		model.setCheckAudio(0);
		model.setCheckImg(0);
		model.setCheckExtCur(0);
		model.setCheckMajor(0);
		model.setCheckMinor(0);
		model.setComment("Good job.");
		model.setFirstName("Andrew");
		model.setLastName("MOTT");
	}
	
	// getters
	@Test
	public void testGetStudentInfo() {
//		assertEquals("Info", model.getStudentInfo());
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
	public void testgetAdvisorID() {
		assertEquals(0,model.getAdvisorId());
	}
	@Test
	public void testApproval() {
		assertEquals(0,model.getApproval());
	}
	@Test
	public void testCheckAudio() {
		assertEquals(0,model.getCheckAudio());
	}
	@Test
	public void testCheckImage() {
		assertEquals(0,model.getCheckImg());
	}
	@Test
	public void testCheckExtCur() {
		assertEquals(0,model.getCheckExtCur());
	}
	@Test
	public void testCheckMajor() {
		assertEquals(0,model.getCheckMajor());
	}
	@Test
	public void testCheckMinor() {
		assertEquals(0,model.getCheckMinor());
	}
	@Test
	public void testComment() {
		assertEquals("Good job.", model.getComment());
	}
	@Test
	public void testFirstName() {
		assertEquals("Andrew", model.getFirst());
	}
	@Test
	public void testLastName() {
		assertEquals("MOTT", model.getLast());
	}
	
	// setters
	@Test
	public void testSetStudentInfo() {
//		model.setStudentInfo(4);
//		assertEquals(4, model.getStudentInfo());
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
	@Test
	public void testSetAdvisorID() {
		model.setAdvisorId(1);
		assertEquals(1,model.getAdvisorId());
	}
	@Test
	public void testSetApproval() {
		model.setApproval(1);
		assertEquals(1,model.getApproval());
	}
	@Test
	public void testSetCheckAudio() {
		model.setCheckAudio(1);
		assertEquals(1,model.getCheckAudio());
	}
	@Test
	public void testSetCheckImage() {
		model.setCheckImg(1);
		assertEquals(1,model.getCheckImg());
	}
	@Test
	public void testSetCheckExtCur() {
		model.setCheckExtCur(1);
		assertEquals(1,model.getCheckExtCur());
	}
	@Test
	public void testSetCheckMajor() {
		model.setCheckMajor(1);
		assertEquals(1,model.getCheckMajor());
	}
	@Test
	public void testSetCheckMinor() {
		model.setCheckMinor(1);
		assertEquals(1,model.getCheckMinor());
	}
	@Test
	public void testSetComment () {
		model.setComment("Het there!!");
		assertEquals("Het there!!",model.getComment());
	}
	@Test
	public void testSetFirstName() {
		model.setFirstName("Rob");
		assertEquals("Rob",model.getFirst());
	}
	@Test
	public void testSetLastName() {
		model.setLastName("Woody");
		assertEquals("Woody",model.getLast());
	}
}
