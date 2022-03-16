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
	ArrayList<StudentModel> students;
	
	/**
	 * Constructor for Controller
	 */
	public StudentInfoController() {
		
	}
	
	public void setStudentInfo(StudentInfoModel stuInfo) {
		this.stuInfo = stuInfo;
	}
	
	/**
	 * Set the student array
	 * @param students		Students array
	 */
	public void setStudentArray(ArrayList<StudentModel> students) {
		this.students = students;
	}
	
	public void setStudentInfo(String firstName, String middleInitial, String lastName, String major, String minor, String extraCur, String img, String audio) {
		stuInfo.setFirstName(firstName);
		stuInfo.setMiddleInitial(middleInitial);
		stuInfo.setLastName(lastName);
		stuInfo.setMajor(major);
		stuInfo.setMinor(minor);
		stuInfo.setExtraCur(extraCur);
		stuInfo.setPicture(img);
		stuInfo.setNameSound(audio);
	}
	
}
