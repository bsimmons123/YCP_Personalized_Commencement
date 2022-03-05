package edu.ycp.cs320_personalized_commencement.model;

public class User {
	private String username, email, password;
	
	/**
	 * Sets the user's username.
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * Returns the user's username.
	 */
	public String getUsername() {
		return username;
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
