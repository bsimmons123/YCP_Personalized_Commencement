package edu.ycp.cs320_personalized_commencement.model;

public class UserModel {
	private String email, password;
	
	private boolean login, student; // student to check if student is an advisor or not or logged in or not
	
	/**
	 * Check to see if user is student
	 */
	public void setStudent(boolean student) {
		this.student = student;
	}
	
	/**
	 * Check for login state
	 */
	public void setLogin(boolean login) {
		this.login = login;
	}
	
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
	 * Returns if user is student
	 */
	public boolean getStudent() {
		return student;
	}
	
	/**
	 * Returns users login state
	 */
	public boolean getlogin() {
		return login;
	}
	
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
}
