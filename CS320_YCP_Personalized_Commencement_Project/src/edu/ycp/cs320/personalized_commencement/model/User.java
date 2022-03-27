package edu.ycp.cs320.personalized_commencement.model;

public class User {
	private String email, password;
	
	// approval = 1 if approved
	private int studentId, advisorId;
	
	private boolean login; // student to check if student is an advisor or not or logged in or not
	
	// setters
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAdvisorId(int id) {
		this.advisorId = id;
	}
	public void setStudentId(int id) {
		this.studentId = id;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	
	
	// getters
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public int getAdvisorId() {
		return advisorId;
	}
	public int getStudentId() {
		return studentId;
	}
	public boolean getLogin() {
		return login;
	}
	

}
