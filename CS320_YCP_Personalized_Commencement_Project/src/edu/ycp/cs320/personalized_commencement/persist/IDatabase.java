package edu.ycp.cs320.personalized_commencement.persist;

import java.util.ArrayList;

import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Student;

public interface IDatabase {
	public ArrayList<Student> findStudentsByAdvisor(String advisor);

	public Advisor getAdvisor(String email, String password);

	/**
	 * return student 
	 * @param email			student email
	 * @param password		student password
	 * @return
	 */
	public Student getStudent(String email, String password);

	/**
	 * Update student associated with email and password
	 */
	Boolean updateStudents(String userEmail, String first, String last, String major, String minor, String extraCur,
			String picture, String sound);

	/**
	 * Update student associated with email and password
	 */
	Boolean updateAdvisorComment(String userEmail, String comment);
}
