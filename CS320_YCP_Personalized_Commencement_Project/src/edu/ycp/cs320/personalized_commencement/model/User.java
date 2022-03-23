package edu.ycp.cs320.personalized_commencement.model;

public class User {
	private String email, password;
	
	private boolean login, student, advisor; // student to check if student is an advisor or not or logged in or not
	
	// setters
	/**
	 * Sets the user's password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Sets the user's email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Check to see if user is a student
	 */
	public void setStudent(boolean student) {
		this.student = student;
	}
	
	/**
	 * Check to see if the user is an advisor
	 */
	public void setAdvisor(boolean advisor) {
		this.advisor = advisor;
	}
	
	/**
	 * Check for login state
	 */
	public void setLogin(boolean login) {
		this.login = login;
	}
	
	
	// getters
	/**
	 * Returns the user's password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Returns the user's email.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Returns if user is student
	 */
	public boolean getStudent() {
		return student;
	}
	
	/**
	 * Returns true if the user is an advisor
	 */
	public boolean getAdvisor() {
		return advisor;
	}
	
	/**
	 * Returns users login state
	 */
	public boolean getLogin() {
		return login;
	}
}
