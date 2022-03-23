package edu.ycp.cs320.personalized_commencement.model;

public class User {
	private String email, password;
	
	private boolean login, student, advisor; // student to check if student is an advisor or not or logged in or not
	
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String major;
	private String minor;
	private String extraCur;
	private String picture; // String holding the name of the users file
	private String nameSound; // String of users file
	
	
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
	//setters
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public void setMiddleInitial(String middleInitial) { // why middle initial?
		this.middleInitial = middleInitial;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public void setExtraCur(String extraCur) {
		this.extraCur = extraCur;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public void setNameSound(String nameSound) {
		this.nameSound = nameSound;
	}
	
	//getters
	public String getFirst(){
		return firstName;
	}
	public String getMiddle() {
		return middleInitial;
	}
	public String getLast() {
		return lastName;
	}
	public String getMajor() {
		return major;
	}
	public String getMinor() {
		return minor;
	}
	public String getExtraCur() {
		return extraCur;
	}
	public String getPicture() {
		return picture;
	}
	public String getNameSound() {
		return nameSound;
	}
}
