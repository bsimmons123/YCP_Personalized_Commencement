package edu.ycp.cs320.personalized_commencement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320_personalized_commencement.model.UserModel;

public class AdvisorControllerTest {
private UserModel user;
private AdvisorController controller;
	
	@Before
	public void setup() {
		
		//set up user
		user = new UserModel();
		
		// set up controller
		controller = new AdvisorController();

		
		controller.addAdvisor("EthanLicksWindows@hotmail.yahoo.brown", "Brandon'sMom1");
		//controller.setModel(model);
		
	}
	@Test
	public void testCheckAdvisorLogin(){
		user.setEmail("EthanLicksWindows@hotmail.yahoo.brown");
		user.setPassword("Brandon'sMom1");
		assertEquals(true, controller.checkAdvisorLogin(controller.getAdvisor(0), user));
	}
	@Test
	public void testCheckAdvisorLogin2(){
		controller.addAdvisor("Zombie@hotmail.yahoo", "Hello");
		
		user.setEmail("Zombie@hotmail.yahoo");
		user.setPassword("Hello");
		assertEquals(true, controller.checkAdvisorLogin(controller.getAdvisor(1), user));
	}
	@Test
	public void testCheckAdvisorLoginFalse(){
		user.setEmail("AndrewLicksLinix@hotmail.com");
		user.setPassword("Rob'sMom2");
		assertEquals(false, controller.checkAdvisorLogin(controller.getAdvisor(0),user));
	}
	@Test
	public void testCheckAdvisorLoginFalse2(){
		user.setEmail("Scammer@hotmail.com");
		user.setPassword("IscAmER2");
		assertEquals(false, controller.checkAdvisorLogin(controller.getAdvisor(0),user));
	}
	
}


