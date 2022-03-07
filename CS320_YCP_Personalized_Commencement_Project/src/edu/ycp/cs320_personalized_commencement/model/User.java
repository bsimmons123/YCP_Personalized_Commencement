package edu.ycp.cs320_personalized_commencement.model;

public class User {
	private String username, email, password;
	private boolean login;
	
	/**
	 * Check for login state
	 */
	public void setLogin(boolean login) {
		this.login = login;
	}
	
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
	 * Returns users login state
	 */
	public boolean getlogin() {
		return login;
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
