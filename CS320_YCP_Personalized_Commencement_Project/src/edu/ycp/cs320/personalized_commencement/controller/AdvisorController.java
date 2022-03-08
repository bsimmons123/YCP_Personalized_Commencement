package edu.ycp.cs320.personalized_commencement.controller;

import edu.ycp.cs320_personalized_commencement.model.AdvisorModel;
import edu.ycp.cs320_personalized_commencement.model.UserModel;

/**
 * Acts as a controller for the Advisor model class.
 */
public class AdvisorController extends UserController{
	
	AdvisorModel model; 
	
	/**
	 * 
	 * @param model 	For interactions with controller
	 */
	public void setModel(AdvisorModel model) {
		this.model = model;
	}
	
	/**
	 * set login for advisor
	 */
	public void setLogin() {
		model.setLogin(true);
	}

	/**
	 * 
	 * @param advisor 	Advisor
	 * @param user	 	User from JSP
	 * @return if		User is logged in
	 */
	public boolean checkAdvisorLogin(UserModel user) {
		if(model.getEmail().equals(user.getEmail()) && model.getPassword().equals(user.getPassword()) && !model.getStudent()) {
			return true;
		}
		return false;
	}

	/**
	 * Hardcode test account
	 */
	public void createTestAdvisor() {
		model.setStudent(false);
		model.setEmail("testadvisor@ycp.edu");
		model.setPassword("test");
	}
}
