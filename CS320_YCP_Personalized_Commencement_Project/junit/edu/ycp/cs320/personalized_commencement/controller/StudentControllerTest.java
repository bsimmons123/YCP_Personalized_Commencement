package edu.ycp.cs320.personalized_commencement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320_personalized_commencement.model.StudentModel;
import edu.ycp.cs320_personalized_commencement.model.UserModel;

public class StudentControllerTest {
	private StudentModel model;
	private UserModel user;
	private StudentController controller;
		
		@Before
		public void setup() {
			//set up model
			model = new StudentModel();
			model.setEmail("EthanLicksWindows@hotmail.yahoo.brown");
			model.setPassword("Brandon'sMom1");
			model.setStudent(true);
			
			
			//set up user
			user = new UserModel();
			
			// set up controller
			controller = new StudentController();
			
		}
		
		@Test
		public void testCheckStudentLoginTrue(){
			user.setEmail("EthanLicksWindows@hotmail.yahoo.brown");
			user.setPassword("Brandon'sMom1");
			user.setStudent(true);
			model.setStudent(true);
			assertEquals(true, controller.checkStudentLogin(model, user));	
		}
		@Test
		public void testCheckStudentLoginTrue2(){
			model.setEmail("yolo@hotmail.brown");
			model.setPassword("UrMom");
			user.setEmail("yolo@hotmail.brown");
			user.setPassword("UrMom");
			user.setStudent(true);
			model.setStudent(true);
			assertEquals(true, controller.checkStudentLogin(model, user));
			
		}
		@Test
		public void testCheckStudentLoginFalse() {
			user.setEmail("AndrewLicksLinix@hotmail.com");
			user.setPassword("Rob'sMom2");
			user.setStudent(false);
			model.setStudent(false);
			assertEquals(false, controller.checkStudentLogin(model, user));	
		}
		@Test
		public void testCheckStudentLoginFalse2() {
			user.setEmail("ryan@hotmail.com");
			user.setPassword("Ryanmom111");
			user.setStudent(false);
			model.setStudent(false);
			assertEquals(false, controller.checkStudentLogin(model, user));	
		}
}
