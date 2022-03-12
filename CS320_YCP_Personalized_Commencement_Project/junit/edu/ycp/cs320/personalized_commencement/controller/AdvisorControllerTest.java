package edu.ycp.cs320.personalized_commencement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320_personalized_commencement.model.AdvisorModel;
import edu.ycp.cs320_personalized_commencement.model.UserModel;

public class AdvisorControllerTest {
private AdvisorModel model;
private UserModel user;
private AdvisorController controller;
	
	@Before
	public void setup() {
		//set up model
		model = new AdvisorModel();
		model.setDecision(true);
		model.setPassword("Brandon'sMom1");
		model.setEmail("EthanLicksWindows@hotmail.yahoo.brown");
		
		//set up user
		user = new UserModel();
		
		// set up controller
		controller = new AdvisorController();

		
		controller.addAdvisor(model);
		//controller.setModel(model);
		
	}
	@Test
	public void testCheckAdvisorLogin(){
		user.setEmail("EthanLicksWindows@hotmail.yahoo.brown");
		user.setPassword("Brandon'sMom1");
		assertEquals(true, controller.checkAdvisorLogin(controller.getAdvisor(0), user));
		
		user.setEmail("AndrewLicksLinix@hotmail.com");
		user.setPassword("Rob'sMom2");
		assertEquals(false, controller.checkAdvisorLogin(controller.getAdvisor(0),user));
	}
	
	
}


