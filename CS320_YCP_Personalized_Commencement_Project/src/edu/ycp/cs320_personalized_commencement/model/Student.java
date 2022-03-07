package edu.ycp.cs320_personalized_commencement.model;

/**
 * Stores the student's information in the model.
 */
public class Student extends User{
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
