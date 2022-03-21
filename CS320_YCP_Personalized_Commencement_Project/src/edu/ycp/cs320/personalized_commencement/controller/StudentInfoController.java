package edu.ycp.cs320.personalized_commencement.controller;

import java.util.ArrayList;

import edu.ycp.cs320_personalized_commencement.model.StudentInfoModel;
import edu.ycp.cs320_personalized_commencement.model.StudentModel;

/**
 * Acts as the controller for the Student model class.
 */
public class StudentInfoController extends UserController{
	
	// Initialize the arrays for student
	StudentInfoModel stuInfo;
	StudentModel students;
	ArrayList<StudentInfoModel> stuInfos;
	
	/**
	 * Constructor for Controller
	 */
	public StudentInfoController() {
		
		stuInfos = new ArrayList<StudentInfoModel>();
	}
	
//	/**
//	 * Sets student info model
//	 * @param stuInfo		Student info model
//	 */
//	public void setStudentInfo(StudentInfoModel stuInfo) {
//		this.stuInfo = stuInfo;
//	}
	
	public StudentInfoModel getStudentInfo(int index) {
		return stuInfos.get(index);
	}
	
	/**
	 * Set student model
	 * @param students		Students array
	 */
	public void setStudentModel(StudentModel students) {
		this.students = students;
	}
	
	/**
	 * Set the student model from user input
	 * @param firstName			Firstname from user 
	 * @param middleInitial		MiddleInitial from user
	 * @param lastName			Lastnae from user
	 * @param major				user's major
	 * @param minor				user's minor
	 * @param extraCur			user's extra curr
	 * @param img				image uploaded by user
	 * @param audio				audio file uploaded from user
	 */
	public void setStudentInfo(String firstName, String middleInitial, String lastName, String major, String minor, String extraCur, String img, String audio) {
		stuInfo = new StudentInfoModel();
		stuInfo.setFirstName(firstName);
		stuInfo.setMiddleInitial(middleInitial);
		stuInfo.setLastName(lastName);
		stuInfo.setMajor(major);
		stuInfo.setMinor(minor);
		stuInfo.setExtraCur(extraCur);
		stuInfo.setPicture(img);
		stuInfo.setNameSound(audio);
		stuInfos.add(stuInfo);
	}
	
	/**
	 * Return Student info model
	 * @return	Student info model
	 */
	public StudentInfoModel getStudentInfo() {
		return stuInfo;
	}
}
