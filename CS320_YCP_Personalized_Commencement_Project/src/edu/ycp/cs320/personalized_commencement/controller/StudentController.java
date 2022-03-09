package edu.ycp.cs320.personalized_commencement.controller;

import edu.ycp.cs320_personalized_commencement.model.StudentModel;
import edu.ycp.cs320_personalized_commencement.model.UserModel;

/**
 * Acts as the controller for the Student model class.
 */
public class StudentController extends UserController{
	
	StudentModel model;
	
	/**
	 * 
	 * @param model 	For interactions with controller
	 */
	public void setModel(StudentModel model) {
		this.model = model;
	}
	
	/**
	 * set login for student
	 */
	public void setLogin() {
		model.setLogin(true);
	}
	
	/**
	 * Hardcode account details
	 */
	public void createTestStudent(String email, String password) {
		model.setEmail(email);
		model.setPassword(password);
		model.setStudent(true);
	}

	/**
	 * 
	 * @param student	Student
	 * @param user		User from JSP
	 * @return			User is logged in
	 */
	public boolean checkStudentLogin(UserModel user) {
		if(model.getEmail().equals(user.getEmail()) && model.getPassword().equals(user.getPassword()) && model.getStudent()) {
			return true;
		}
		return false;
	}
}
