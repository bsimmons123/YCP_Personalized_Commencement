package edu.ycp.cs320.personalized_commencement.model;


//import org.eclipse.jetty.server.Authentication.User;

/**
 * Stores the student's information in the model.
 */
public class Student extends User{
	// field Object for the student's info
	private StudentInfoModel studentInfo; 
	
	/**
	 * Sets studentInfo with the info student submitted.
	 */
	public void setStudentInfo(StudentInfoModel studentInfo) {
		this.studentInfo = studentInfo;
	}
	
	/**
	 * Returns student's info.
	 */
	public StudentInfoModel getStudentInfo() {
		return studentInfo;
	}
}
