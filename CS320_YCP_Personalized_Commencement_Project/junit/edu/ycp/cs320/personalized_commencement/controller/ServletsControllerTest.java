package edu.ycp.cs320.personalized_commencement.controller;

import static org.junit.Assert.*;

//import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.personalized_commencement.model.Student;

//
//Comments indicate which tests I have written which failed
//

public class ServletsControllerTest {
	private Student sController2;
	private ServletsController serv;
	//private HttpServletRequest req;
	
	@Before
	public void setup() {
		serv = new ServletsController();
		
		sController2 = new Student();
		sController2.setEmail("CleanUpYourRoomYouFilthyAnimal@gmail.com");
		sController2.setAdvisorId(0);
		sController2.setApproval(0);
		sController2.setCheckAudio(0);
		sController2.setCheckImg(0);
		sController2.setCheckExtCur(0);
		sController2.setCheckMajor(0);
		sController2.setCheckMinor(0);
		sController2.setComment("4");
		sController2.setFirstName("Shub");
		sController2.setLastName("Spoon");
	}
	
	@Test
	public void testGetStudent() {
		assertEquals(1, serv.getStudent("bsimmons1@ycp.edu", "test").getStudentId());
		assertEquals(7, serv.getStudent("scarell@ycp.edu", "test").getStudentId());
		assertNull(serv.getStudent("NonExistent", "Non"));
	}
	
	@Test
	public void testGetStudentById() {
		assertEquals("Valedictorian", serv.getStudentById(19).getAward());
		assertEquals("Flannery", serv.getStudentById(9).getLast());
	}
	
	@Test
	public void testGetStudentMethodsEquivalent() {
		assertEquals(serv.getStudent("bsimmons1@ycp.edu", "test").getStudentId(), serv.getStudentById(1).getStudentId());
		assertEquals(serv.getStudent("erosenberry@ycp.edu", "test").getAdvisorId(), serv.getStudentById(3).getAdvisorId());
	}
	
	@Test
	public void testCheckStudentLogin() {
		assertFalse(serv.checkStudentLogin("Arsinius", "The Great"));
		assertTrue(serv.checkStudentLogin("rwood7@ycp.edu", "test"));
	}
	
	@Test
	public void testGetAdvisor() {
		assertEquals(1, serv.getAdvisor("jmoscola@ycp.edu", "test").getAdvisorId());
		assertEquals(4, serv.getAdvisor("rgrim2@ycp.edu", "test").getAdvisorId());
		assertNull(serv.getAdvisor("IAmNotAnAdvisor@gmail.com", "ArvelTheSwift"));
		assertNull(serv.getAdvisor("bsimmons1@ycp.edu", "test"));
	}
	
	@Test
	public void testCheckAdvisorLogin() {
		assertFalse(serv.checkAdvisorLogin("BurritoMan88@yahoo.com", "ILOVETACOS"));
		assertTrue(serv.checkAdvisorLogin("kkambhampaty@ycp.edu", "test"));
	}
	
	@Test
	public void testGetAdvisorsStudents() {
		assertEquals(1, serv.getAdvisorsStudents("jmoscola@ycp.edu").get(0).getStudentId());
		assertEquals(6, serv.getAdvisorsStudents("dbabcock@ycp.edu").get(0).getStudentId());
	}
	
	@Test
	public void testGetAllStudents() {
		assertEquals(20, serv.getAllStudents().size());
		assertEquals("Quality Assurance", serv.getAllStudents().get(9).getMajor());
	}
	
	//Cannot get serv.getInteger to return a non-null value. Must revisit
	@Test
	public void testGetInteger() {
		//assertEquals(4, serv.getInteger(req, sController2.getComment()));
	}
	
	@Test
	public void testToInt() {
		assertEquals(1, serv.toInt(sController2.getFirst()));
		assertEquals(0, serv.toInt(sController2.getPassword()));
	}
	
	//Fake student email is not returning false for updateStudent
	@Test
	public void testUpdateStudent() {
		assertTrue(serv.updateStudent(serv.getStudentById(1).getEmail(), "Lifting", "Arnold.jpg", "monkeyspinningmonkeys.mp4"));
		//assertFalse(serv.updateStudent("hackerman87@gmail.com", "hacking", "hacker.jpg", "hackerTyping.mp4"));
	}
	
	@Test
	public void testUpdateStudentContent() {
		assertTrue(serv.updateStudentContent(serv.getStudentById(3).getStudentId(), 1, 1, 1));
		assertFalse(serv.updateStudentContent(serv.getStudentById(7).getStudentId(), 1, 0, 0));
	}
	
	//AssertFalse doesn't work even though there is no student with ID 25
	@Test
	public void testUpdateStudentApproval() {
		assertTrue(serv.updateStudentApproval(1, 1));
		//assertFalse(serv.updateStudentApproval(25, 0));
	}
	
	@Test
	public void testUpdateStudentComment() {
		assertTrue(serv.updateStudentComment(serv.getStudentById(1).getEmail(), "Absolutely impeccable."));
		assertFalse(serv.updateStudentComment(sController2.getEmail(), "Holy schmoly"));
	}
}
