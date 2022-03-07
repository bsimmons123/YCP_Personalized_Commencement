package edu.ycp.cs320.personalized_commencement.controller;

import edu.ycp.cs320_personalized_commencement.model.Student;
import edu.ycp.cs320_personalized_commencement.model.User;

/**
 * Acts as the controller for the Student model class.
 */
public class StudentController extends UserController{

	/**
	 * 
	 * @param student	Student
	 * @param user		User from JSP
	 * @return			User is logged in
	 */
	public boolean checkStudentLogin(Student student, User user) {
		if(student.getEmail().equals(user.getEmail()) && student.getPassword().equals(user.getPassword())) {
			return true;
		}
		return false;
	}
}
