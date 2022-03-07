package edu.ycp.cs320.personalized_commencement.controller;

import edu.ycp.cs320_personalized_commencement.model.AdvisorModel;
import edu.ycp.cs320_personalized_commencement.model.UserModel;

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
	public boolean checkStudentLogin(AdvisorModel advisor, UserModel user) {
		if(advisor.getEmail().equals(user.getEmail()) && advisor.getPassword().equals(user.getPassword())) {
			return true;
		}
		return false;
	}
}
