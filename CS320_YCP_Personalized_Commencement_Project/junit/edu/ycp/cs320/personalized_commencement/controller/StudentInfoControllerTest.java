package edu.ycp.cs320.personalized_commencement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320_personalized_commencement.model.StudentInfoModel;

public class StudentInfoControllerTest {
	private StudentInfoModel model;
	private StudentInfoController controller;
	
	@Before
	public void setUp() {
		model = new StudentInfoModel();
		model.setFirstName("Greg");
		model.setMiddleInitial("E.");
		model.setLastName("Heffley");
		model.setMajor("Compoober Signs");
		model.setMinor("Sign Language for Blind People");
		model.setExtraCur("Guinea Pig Jousting Club");
		model.setPicture("File");
		model.setNameSound("Audio");
		controller = new StudentInfoController();
		controller.setStudentInfo(model);
	}
	
	
	// test getters
	@Test
	public void getStuInfo() {
		assertEquals(controller.getStudentInfo(), model);
	}
	@Test
	public void getFName() {
		assertEquals("Greg", controller.getStudentInfo().getFirst());
	}
	@Test
	public void getMiddle() {
		assertEquals("E.", controller.getStudentInfo().getMiddle());
	}
	@Test
	public void getLName() {
		assertEquals("Heffley", controller.getStudentInfo().getLast());
	}
	@Test
	public void getMajors() {
		assertEquals("Compoober Signs", controller.getStudentInfo().getMajor());
	}
	@Test
	public void getMinors() {
		assertEquals("Sign Language for Blind People", controller.getStudentInfo().getMinor());
	}
	@Test
	public void getExtraCur() {
		assertEquals("Guinea Pig Jousting Club", controller.getStudentInfo().getExtraCur());
	}
	@Test
	public void getDisplayFile() {
		assertEquals("File", controller.getStudentInfo().getPicture());
	}
	@Test
	public void getAudioFile() {
		assertEquals("Audio", controller.getStudentInfo().getNameSound());
	}
	
	
	// test setters
	@Test
	public void setStuInfo() {
		model.setFirstName("R");
		model.setMiddleInitial("O.");
		model.setLastName("B");
		model.setMajor("Maj");
		model.setMinor("Min");
		model.setExtraCur("CURR");
		model.setPicture("DisplayFile");
		model.setNameSound("CustomAudio");
		controller.setStudentInfo(model);
		assertEquals(controller.getStudentInfo(), model);
	}
	@Test
	public void setFName() {
		controller.getStudentInfo().setFirstName("ROB");
		assertEquals("ROB", controller.getStudentInfo().getFirst());
	}
	@Test
	public void setMiddle() {
		controller.getStudentInfo().setMiddleInitial("L");
		assertEquals("L", controller.getStudentInfo().getMiddle());
	}
	@Test
	public void setLName() {
		controller.getStudentInfo().setLastName("Wood");
		assertEquals("Wood", controller.getStudentInfo().getLast());
	}
	@Test
	public void setMajors() {
		controller.getStudentInfo().setMajor("brown");
		assertEquals("brown", controller.getStudentInfo().getMajor());
	}
	@Test
	public void setMinors() {
		controller.getStudentInfo().setMinor("Grapes");
		assertEquals("Grapes", controller.getStudentInfo().getMinor());
	}
	@Test
	public void setExtraCur() {
		controller.getStudentInfo().setExtraCur("BaksetBowl");
		assertEquals("BaksetBowl", controller.getStudentInfo().getExtraCur());
	}
	@Test
	public void setDisplayFile() {
		controller.getStudentInfo().setPicture("DispFile");
		assertEquals("DispFile", controller.getStudentInfo().getPicture());
	}
	@Test
	public void setAudioFile() {
		controller.getStudentInfo().setNameSound("Sounds 'N Stuff");
		assertEquals("Sounds 'N Stuff", controller.getStudentInfo().getNameSound());
	}
}
