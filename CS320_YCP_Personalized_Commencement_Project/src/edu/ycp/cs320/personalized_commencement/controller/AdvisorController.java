package edu.ycp.cs320.personalized_commencement.controller;

import edu.ycp.cs320_personalized_commencement.model.Advisor;
import edu.ycp.cs320_personalized_commencement.model.User;

/**
 * Acts as a controller for the Advisor model class.
 */
public class AdvisorController extends UserController{

	/**
	 * 
	 * @param advisor 	Advisor
	 * @param user	 	User from JSP
	 * @return if		User is logged in
	 */
	public boolean checkStudentLogin(Advisor advisor, User user) {
		if(advisor.getEmail().equals(user.getEmail()) && advisor.getPassword().equals(user.getPassword())) {
			return true;
		}
		return false;
	}
}
