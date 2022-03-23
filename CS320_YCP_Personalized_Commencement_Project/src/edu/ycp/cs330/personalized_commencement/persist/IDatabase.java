package edu.ycp.cs330.personalized_commencement.persist;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Pair;
import edu.ycp.cs320.personalized_commencement.model.Student;

public interface IDatabase {
	public ArrayList<Student> findStudentsByAdvisor(String advisor);

	public Advisor getAdvisor(String email);

	public Student getStudent(String email);
}
