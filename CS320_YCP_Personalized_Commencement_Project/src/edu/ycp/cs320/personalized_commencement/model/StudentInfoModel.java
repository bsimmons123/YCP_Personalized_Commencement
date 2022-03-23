package edu.ycp.cs320.personalized_commencement.model;

public class StudentInfoModel{
	
	
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String major;
	private String minor;
	private String extraCur;
	private String picture; // String holding the name of the users file
	private String nameSound; // String of users file
	
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
