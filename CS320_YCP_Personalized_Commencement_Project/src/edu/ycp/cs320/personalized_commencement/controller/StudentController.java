package edu.ycp.cs320.personalized_commencement.controller;

import java.util.ArrayList;

import edu.ycp.cs320_personalized_commencement.model.StudentModel;
import edu.ycp.cs320_personalized_commencement.model.UserModel;

/**
 * Acts as the controller for the Student model class.
 */
public class StudentController extends UserController{
	
	ArrayList<StudentModel> students;
	
	/**
	 * Contructor
	 */
	public StudentController() {
		students = new ArrayList<StudentModel>();
	}
	
	/**
	 * Add new Student to ArrayList
	 * @param email		Email for student
	 * @param password	Password for student
	 */
	public void addStudent(String email, String password) {
		StudentModel student = new StudentModel();
		student.setEmail(email);
		student.setPassword(password);
		student.setStudent(true);
		students.add(student);
	}
	
	/**
	 * 
	 * @param index		Index of a particular student
	 * @return			Student
	 */
	public StudentModel getStudent(int index) {
		return students.get(index);
	}
	
	/**
	 * 
	 * @return			Students array
	 */
	public ArrayList<StudentModel> getStudents(){
		return students;
	}
	
	/**
	 * set login for student
	 */
	public void setLogin(StudentModel student) {
		student.setLogin(true);
	}

	/**
	 * Checks if the user has entered the correct credentials
	 * @param student	Student
	 * @param user		User from JSP
	 * @return			User is logged in
	 */
	public boolean checkStudentLogin(StudentModel student,UserModel user) {
		if(student.getEmail().equals(user.getEmail()) && student.getPassword().equals(user.getPassword()) && student.getStudent()) {
			return true;
		}
		return false;
	}
}
