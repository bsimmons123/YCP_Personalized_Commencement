package edu.ycp.cs320.personalized_commencement.controller;

import java.util.ArrayList;

import edu.ycp.cs320_personalized_commencement.model.AdvisorModel;
import edu.ycp.cs320_personalized_commencement.model.UserModel;

/**
 * Acts as a controller for the Advisor model class.
 */
public class AdvisorController extends UserController{
	
	// Create an arraylist of advisor users
	ArrayList<AdvisorModel> advisors = new ArrayList<AdvisorModel>();
	
	/**
	 * 
	 * @param index		Index of advisor in arraylist
	 * @return			Advisor
	 */
	public AdvisorModel getAdvisor(int index) {
		return advisors.get(index);
	}
	
	/**
	 * 
	 * @return			Arraylist of advisors
	 */
	public ArrayList<AdvisorModel> getAdvisors(){
		return advisors;
	}
	
	/**
	 * 
	 * @param advisors	sets an arraylist of advisors
	 */
	public void setAdvisors(ArrayList<AdvisorModel> advisors) {
		this.advisors = advisors;
	}
	
	/**
	 * set login for advisor
	 */
	public void setLogin(AdvisorModel advisor) {
		advisor.setLogin(true);
	}
	
	/**
	 * 	Checks if the user has entered the correct credentials
	 * @param advisor 	Advisor
	 * @param user	 	User from JSP
	 * @return if		User is logged in
	 */
	public boolean checkAdvisorLogin(AdvisorModel advisor, UserModel user) {
		if(advisor.getEmail().equals(user.getEmail()) && advisor.getPassword().equals(user.getPassword()) && !advisor.getStudent()) {
			return true;
		}
		return false;
	}

	/**
	 * Initialize an advisor account
	 * @param advisor	advisor
	 * @param email		email of JSP user
	 * @param password	password of JSP user
	 */
	public void createTestAdvisor(AdvisorModel advisor, String email, String password) {
		advisor.setEmail(email);
		advisor.setPassword(password);
		advisor.setStudent(false);
	}
}
