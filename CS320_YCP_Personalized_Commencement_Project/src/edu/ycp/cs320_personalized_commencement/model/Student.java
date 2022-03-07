package edu.ycp.cs320_personalized_commencement.model;

/**
 * Stores the student's information in the model.
 */
public class Student extends User{
	// field Object for the student's info
	private Object studentInfo; 
	
	private User user; // used to set Hardcoded credentials
	
	/**
	 * Hardcode test account
	 */
	public void createTestStudent() {
		user.setEmail("teststudent@ycp.edu");
		user.setUsername("test");
		user.setPassword("test");
	}
	
	
	/**
	 * Sets the studentInfo object with the info they submitted.
	 */
	public void setStudentInfo(Object studentInfo) {
		this.studentInfo = studentInfo;
	}
	
	/**
	 * Returns the student's info.
	 */
	public Object getStudentInfo() {
		return studentInfo;
	}
}
