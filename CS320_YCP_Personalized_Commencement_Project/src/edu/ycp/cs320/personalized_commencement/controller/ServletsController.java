package edu.ycp.cs320.personalized_commencement.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Student;
import edu.ycp.cs320.personalized_commencement.persist.DatabaseProvider;
import edu.ycp.cs320.personalized_commencement.persist.DerbyDatabase;
import edu.ycp.cs320.personalized_commencement.persist.IDatabase;

public class ServletsController {

	// pls work
	/**
	 * Gets the student's account.
	 *
	 * @param email		student's email
	 *
	 * @return			student's account
	 */
	public Student getStudent(String email, String password) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());

		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Student student = db.getStudent(email, password);

		// check if anything was returned and output the list
		if (student != null) {
				return student;
			}
		return null;
	}

	/**
	 * @returns the student as a Student object based off of their student ID.
	 */
	public Student getStudentById(int id) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());

		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Student student = db.findStudentsById(id);

		// check if anything was returned and output the list
		if (student == null) {
			System.out.println("\tNo students found for ID <" + id + ">");
			return null;
		} else {
			return student;
		}
	}

	/**
	 * Shows if the student exists in the database.
	 *
	 * @param email		email to check records with
	 *
	 * @returns true if the student exists, false otherwise.
	 */
	public boolean checkStudentLogin(String email, String password) {
		// create student object
		Student student = new Student();
		student = getStudent(email, password);

		// check if anything was returned and output the list
		if (student != null) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the advisor's account.
	 *
	 * @param email		advisor's email
	 *
	 * @return			advisor's account
	 */
	public Advisor getAdvisor(String email, String password) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());

		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Advisor advisor = db.getAdvisor(email, password);

		// check if anything was returned and output the list
		if (advisor != null) {
				return advisor;
			}
		return null;
	}

	/**
	 * Shows if the advisor exists in the database or not.
	 *
	 * @param email		email to check records with
	 *
	 * @returns true if the advisor exists in the database, false otherwise.
	 */
	public boolean checkAdvisorLogin(String email, String password) {
		Advisor advisor = new Advisor();

		advisor = getAdvisor(email, password);

		// check if anything was returned and output the list
		if (advisor != null) {
				return true;
			}
		return false;
	}

	/**
	 * @returns the list of students that belong to a specific advisor.
	 */
	public ArrayList<Student> getAdvisorsStudents(String AdvisorEmail) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());

		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Student> studentAdvisorList = db.findStudentsByAdvisor(AdvisorEmail);

		// check if anything was returned and output the list
		if (studentAdvisorList.isEmpty()) {
			System.out.println("\tNo students found for <" + AdvisorEmail + ">");
			return null;
		} else {
			return studentAdvisorList;
		}
	}
	
	/**
	 * @returns the list of students that belong to a specific advisor.
	 */
	public ArrayList<Student> getAllStudents() {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());

		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Student> studentsList = db.getEveryStudent();

		// check if anything was returned and output the list
		if (studentsList.isEmpty()) {
			System.out.println("\tNo students in this list");
			return null;
		} else {
			return studentsList;
		}
	}
	
	/**
	 * @returns student requested from first and last name
	 */
	public Student getStudentByFirstAndLast(String first, String last) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());

		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Student> studentsList = db.getEveryStudent();
		
		Student student = null;
		for (Student stud : studentsList) {
			if (stud.getFirst().equalsIgnoreCase(first) && stud.getLast().equalsIgnoreCase(last)) {
				student = stud;
			}
		}
		return student;
	}

	/**
	 * Gets an integer from the posted form data, for the given attribute name.
	 *
	 * @returns an integer
	 */
	public int getInteger(HttpServletRequest req, String name) {
		return Integer.parseInt(req.getParameter(name));
	}

	/**
	 * Converts the string passed as a parameter to an integer of either 1 or 0 and returns it.
	 * Used for Radial values
	 *
	 * @returns an integer
	 */
	public int toInt(String param) {
		if(param == null) {
			return 0;
		}
		else {
			return 1;
		}
	}

	/**
	 * Updates the attributes of the student in the database.
	 *
	 * @param userEmail
	 * @param extraCur
	 * @param picture
	 * @param sound
	 *
	 * @returns true or false depending on if the users attributes are updated or not
	 */
	public Boolean updateStudent(String userEmail, String extraCur, String picture, String sound) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());

		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Boolean update = db.updateStudents(userEmail, extraCur, picture, sound);

		return update;
	}

	/**
	 * Updates the approval status of the students attributes in the database.
	 *
	 * @param id
	 * @param extcur
	 * @param img
	 * @param audio
	 *
	 * @returns the student's updated approval status or returns false.
	 */
	 public Boolean updateStudentContent(int id, int extcur, int img, int audio) {
 		// Create the default IDatabase instance
 		DatabaseProvider.setInstance(new DerbyDatabase());

 		// get the DB instance and execute transaction
 		IDatabase db = DatabaseProvider.getInstance();
 		Boolean student = db.updateStudentContentSubmissions(id, extcur, img, audio);
 		if(extcur == 1 && img == 1 && audio == 1) {
 			return db.updateStudentApproval(id, 1);
 		}else {
 			db.updateStudentApproval(id, 0);
 		}
 		// check if anything was returned and output the list
 		if (student == null) {
 			System.out.println("\tNo students found for ID <" + id + ">");
 		}
 		return false;
 	}

	 public Boolean updateStudentApproval(int id, int approval) {
			// Create the default IDatabase instance
			DatabaseProvider.setInstance(new DerbyDatabase());

			// get the DB instance and execute transaction
			IDatabase db = DatabaseProvider.getInstance();
			Boolean student = db.updateStudentApproval(id, approval);
			// check if anything was returned and output the list
			if (student == null) {
				System.out.println("\tNo students found for ID <" + id + ">");
				return false;
			}
			return true;
		}
	 
	 public Boolean updateStudentShowGPA(int id, int GPA) {
			// Create the default IDatabase instance
			DatabaseProvider.setInstance(new DerbyDatabase());

			// get the DB instance and execute transaction
			IDatabase db = DatabaseProvider.getInstance();
			Boolean student = db.updateStudentShowGPA(id, GPA);
			// check if anything was returned and output the list
			if (student == null) {
				System.out.println("\tNo students found for ID <" + id + ">");
				return false;
			}
			return true;
		}

	/**
	 * Updates the comment written by an advisor inside of the database.
	 *
	 * @param email
	 * @param comment
	 *
	 * @returns the updated student object
	 */
	public boolean updateStudentComment(String email, String comment) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());

		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Boolean student = db.updateAdvisorComment(email, comment);

		// check if anything was returned and output the list
		if (student == null) {
			System.out.println("\tNo students found for email <" + email + ">");
			return false;
		}
		else {
			return student;
		}
	}

	public Student getStudentByEmail(String email) {
		// Create the default IDatabase instance
		DatabaseProvider.setInstance(new DerbyDatabase());

		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
		Student student = db.getStudentByEmail(email);

		// check if anything was returned and output the list
		if (student != null) {
				return student;
			}
		return null;
	}
}
