package edu.ycp.cs320.personalized_commencement.controller;

import java.util.ArrayList;

import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.User;

/**
 * Acts as a controller for the Advisor model class.
 */
public class AdvisorController extends UserController{
	
	// Create an arraylist of advisor users
	ArrayList<Advisor> advisors = new ArrayList<Advisor>();
	
	/**
	 * Contructor
	 */
	public AdvisorController() {
		advisors = new ArrayList<Advisor>();
	}
	
	/**
	 * 
	 * @param index		Index of advisor in arraylist
	 * @return			Advisor
	 */
	public Advisor getAdvisor(int index) {
		return advisors.get(index);
	}
	
	/**
	 * 
	 * @return			Arraylist of advisors
	 */
	public ArrayList<Advisor> getAdvisors(){
		return advisors;
	}
	
	/**
	 * set login for advisor
	 */
	public void setLogin(Advisor advisor) {
		advisor.setLogin(true);
	}
	
	/**
	 * 	Checks if the user has entered the correct credentials
	 * @param advisor 	Advisor
	 * @param user	 	User from JSP
	 * @return if		User is logged in
	 */
	public boolean checkAdvisorLogin(Advisor advisor, User user) {
		if(advisor.getEmail().equals(user.getEmail()) && advisor.getPassword().equals(user.getPassword()) && !advisor.getStudent()) {
			return true;
		}
		return false;
	}

	/**
	 * Add advisor to advsior arraylist
	 * @param email		Email for new advisor
	 * @param password	Password for Advisor
	 */
	public void addAdvisor(String email, String password) {
		Advisor advisor = new Advisor();
		advisor.setEmail(email);
		advisor.setPassword(password);
		advisor.setStudent(false);;
		advisors.add(advisor);
	}
}
