package edu.ycp.cs320_personalized_commencement.model;


import org.eclipse.jetty.server.Authentication.User;

/**
 * Stores the student's information in the model.
 */
@SuppressWarnings("unused")
public class StudentModel extends UserModel{
	// field Object for the student's info
	private Object studentInfo; 
	
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
