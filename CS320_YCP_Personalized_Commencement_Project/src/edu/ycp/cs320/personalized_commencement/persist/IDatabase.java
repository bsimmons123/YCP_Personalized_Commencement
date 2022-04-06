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

	public ArrayList<Student> getEveryStudent();
	
	/**
	 * Update student associated with email and password
	 */
	public Boolean updateAdvisorComment(String userEmail, String comment);

	public Student findStudentsById(int id);

	/**
	 * Student submissions check
	 * @param major			Check for major
	 * @param minor
	 * @param extraCur
	 * @param img
	 * @param audio
	 * @return				If student content booleans have been successfully updated
	 */
	public Boolean updateStudentContentSubmissions(int student_id, int extraCur, int img, int audio);

	/**
	 * Update student associated with email and password
	 */
	public Boolean updateStudents(String userEmail, String extraCur, String picture, String sound);

	/**
	 * updates student approval state
	 * @param student_id
	 * @param approval
	 * @return
	 */
	public Boolean updateStudentApproval(int student_id, int approval);
}
