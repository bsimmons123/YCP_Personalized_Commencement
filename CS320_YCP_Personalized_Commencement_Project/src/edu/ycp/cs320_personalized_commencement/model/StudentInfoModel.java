package edu.ycp.cs320_personalized_commencement.model;

public class StudentInfoModel {
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String major;
	private String minor;
	private String extraCur;
	private Object picture;
	private Object nameSound;
	
	//setters
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public void setMiddleInitial(String middleInitial) {
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
	public void setPicture(Object picture) {
		this.picture = picture;
	}
	public void setNameSound(Object nameSound) {
		this.nameSound = nameSound;
	}
	
	//getters
	public String getFirst(){
		return firstName;
	}
	public String getMiddleInital() {
		return middleInitial;
	}
	public String getLastName() {
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
	public Object getPicture() {
		return picture;
	}
	public Object getNameSound() {
		return nameSound;
	}
}
