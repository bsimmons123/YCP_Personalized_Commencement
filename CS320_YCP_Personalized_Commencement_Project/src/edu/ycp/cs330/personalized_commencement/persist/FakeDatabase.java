package edu.ycp.cs330.personalized_commencement.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Pair;
import edu.ycp.cs320.personalized_commencement.model.Student;

public class FakeDatabase implements IDatabase {
	
	private List<Student> studentList;
	private List<Advisor> advisorList;
	
	public FakeDatabase() {
		studentList = new ArrayList<Student>();
		advisorList = new ArrayList<Advisor>();
		
		// Add initial data
		readInitialData();
		
		System.out.println(studentList.size() + " - Students");
		System.out.println(advisorList.size() + " - Advisors");
	}

	public void readInitialData() {
		try {
			studentList.addAll(InitialData.getStudents());
			advisorList.addAll(InitialData.getAdvisors());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}

	
	private Advisor findAdvisorByEmail(String Email) {
		for(Advisor advisor : advisorList) {
			if (advisor.getEmail().equals(Email)) {
				return advisor;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Student> findStudentsByAdvisor(String email) {
		ArrayList<Student> result = new ArrayList<Student>();
		Advisor advisor = findAdvisorByEmail(email);
		if (advisor != null)
		for (Student student : studentList) {
			if (student.getAdvisorId() == advisor.getAdvisorId()) {
				result.add(student);
			}
		}
		return result;
	}

	@Override
	public Advisor getAdvisor(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudent(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateStudent(String userEmail, int advisorId, String email, String password, String first,
			String last, String major, String minor, String extraCur, String picture, String sound) {
		// TODO Auto-generated method stub
		return null;
	}
}
