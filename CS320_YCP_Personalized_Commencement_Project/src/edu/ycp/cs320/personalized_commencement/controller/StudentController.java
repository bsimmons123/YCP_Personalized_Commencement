package edu.ycp.cs320.personalized_commencement.controller;

import java.util.ArrayList;

import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.model.User;

/**
 * Acts as the controller for the Student model class.
 */
public class StudentController extends UserController{
	
	ArrayList<Student> students;
	
	/**
	 * Contructor
	 */
	public StudentController() {
		students = new ArrayList<Student>();
	}
	
	/**
	 * Add new Student to ArrayList
	 * @param email		Email for student
	 * @param password	Password for student
	 */
	public void addStudent(String email, String password) {
		Student student = new Student();
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
	public Student getStudent(int index) {
		return students.get(index);
	}
	
	/**
	 * 
	 * @return			Students array
	 */
	public ArrayList<Student> getStudents(){
		return students;
	}
	
	/**
	 * set login for student
	 */
	public void setLogin(Student student) {
		student.setLogin(true);
	}
	
	/**
	 * Set the student model from user input
	 * @param firstName			Firstname from user 
	 * @param middleInitial		MiddleInitial from user
	 * @param lastName			Lastnae from user
	 * @param major				user's major
	 * @param minor				user's minor
	 * @param extraCur			user's extra curr
	 * @param img				image uploaded by user
	 * @param audio				audio file uploaded from user
	 */
	public void setStudentInfo(Student student, String firstName, String middleInitial, String lastName, String major, String minor, String extraCur, String img, String audio) {
		student.setFirstName(firstName);
		student.setMiddleInitial(middleInitial);
		student.setLastName(lastName);
		student.setMajor(major);
		student.setMinor(minor);
		student.setExtraCur(extraCur);
		student.setPicture(img);
		student.setNameSound(audio);
	}

	/**
	 * Checks if the user has entered the correct credentials
	 * @param student	Student
	 * @param user		User from JSP
	 * @return			User is logged in
	 */
	public boolean checkStudentLogin(Student student,User user) {
		if(student.getEmail().equals(user.getEmail()) && student.getPassword().equals(user.getPassword()) && student.getStudent()) {
			return true;
		}
		return false;
	}
}
