package edu.ycp.cs320.personalized_commencement.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320_personalized_commencement.model.StudentInfoModel;

/**
 * Junit test class for the student info model.
 */
public class StudentInfoModelTest {
	private StudentInfoModel model;
	
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
	}
	
	// test getters
	@Test
	public void getFName() {
		assertEquals("Greg", model.getFirst());
	}
	
	@Test
	public void getMiddle() {
		assertEquals("E.", model.getMiddle());
	}
	@Test
	public void getLName() {
		assertEquals("Heffley", model.getLast());
	}
	@Test
	public void getMajors() {
		assertEquals("Compoober Signs", model.getMajor());
	}
	@Test
	public void getMinors() {
		assertEquals("Sign Language for Blind People", model.getMinor());
	}
	@Test
	public void getExtraCur() {
		assertEquals("Guinea Pig Jousting Club", model.getExtraCur());
	}
	@Test
	public void getFile() {
		assertEquals("File", model.getPicture());
	}
	@Test
	public void getAudio() {
		assertEquals("Audio", model.getNameSound());
	}
	
	// test setters
	@Test
	public void setFName() {
		model.setFirstName("Iron");
		assertEquals("Iron", model.getFirst());
	}
	
	@Test
	public void setMiddle() {
		model.setMiddleInitial("M.");
		assertEquals("M.", model.getMiddle());
	}
	@Test
	public void setLName() {
		model.setLastName("Man");
		assertEquals("Man", model.getLast());
	}
	@Test
	public void setMajors() {
		model.setMajor("Crying");
		assertEquals("Crying", model.getMajor());
	}
	@Test
	public void setMinors() {
		model.setMinor("Not a minor, over 18");
		assertEquals("Not a minor, over 18", model.getMinor());
	}
	@Test
	public void setExtraCur() {
		model.setExtraCur("Swimming in a pool of childrens tears");
		assertEquals("Swimming in a pool of childrens tears", model.getExtraCur());
	}
	@Test
	public void setFile() {
		model.setPicture("This is a picture");
		assertEquals("This is a picture", model.getPicture());
	}
	@Test
	public void setAudio() {
		model.setNameSound("I am a sound AHHHHHH");
		assertEquals("I am a sound AHHHHHH", model.getNameSound());
	}
	
}
