package edu.ycp.cs320.personalized_commencement.model;


//import org.eclipse.jetty.server.Authentication.User;

/**
 * Stores the student's information in the model.
 */
public class Student extends User{
	// all methods extend user
	private int checkMajor, checkMinor, checkExtCur, checkImg, checkAudio, checkApproval;
	
	private String firstName;
	private String lastName;
	private String major;
	private String minor;
	private String extraCur;
	private String picture; // String holding the name of the users file
	private String nameSound; // String of users file
	private String comment; // Advisors comment on student info
	
	// setters
	public void setCheckMajor(int checkMajor) {
		this.checkMajor = checkMajor;
	}
	public void setCheckMinor(int checkMinor) {
		this.checkMinor = checkMinor;
	}
	public void setCheckExtCur(int checkExtCur) {
		this.checkExtCur = checkExtCur;
	}
	public void setCheckImg(int checkImg) {
		this.checkImg = checkImg;
	}
	public void setCheckAudio(int checkAudio) {
		this.checkAudio = checkAudio;
	}
	//setters for student info
	public void setFirstName(String firstName){
		this.firstName = firstName;
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
	public void setApproval(int checkApproval) {
		this.checkApproval = checkApproval;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	// Getters 
	public int getCheckMajor() {
		return checkMajor;
	}
	public int getCheckMinor() {
		return checkMinor;
	}
	public int getCheckExtCur() {
		return checkExtCur;
	}
	public int getCheckImg() {
		return checkImg;
	}
	public int getCheckAudio() {
		return checkAudio;
	}
	public String getFirst(){
		return firstName;
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
	public int getApproval() {
		return checkApproval;
	}
	public String getComment() {
		return comment;
	}
	
}
