package edu.ycp.cs320.personalized_commencement.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Student;

public class InitialData {
	public static List<Student> getStudents() throws IOException {
		List<Student> studentList = new ArrayList<Student>();
		ReadCSV readStudents = new ReadCSV("student.csv");
		try {
			// auto-generated primary key for books table
			Integer studentId = 1;
			while (true) {
				List<String> tuple = readStudents.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();

				Student student = new Student();
				student.setStudentId(studentId++);
				student.setAdvisorId(Integer.parseInt(i.next()));
				student.setEmail(i.next());
				student.setPassword(i.next());
				student.setFirstName(i.next());
				student.setLastName(i.next());
				student.setMajor(i.next());
				student.setMinor(i.next());
				student.setExtraCur(i.next());
				student.setPicture(i.next());
				student.setNameSound(i.next());
				studentList.add(student);
			}
			return studentList;
		} finally {
			readStudents.close();
		}
	}

	public static List<Advisor> getAdvisors() throws IOException {
		List<Advisor> advisorList = new ArrayList<Advisor>();
		ReadCSV readAdvisors = new ReadCSV("advisor.csv");
		try {
			// auto-generated primary key for books table
			Integer advisorId = 1;
			while (true) {
				List<String> tuple = readAdvisors.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Advisor advisor = new Advisor();
				advisor.setAdvisorId(advisorId++);
				advisor.setEmail(i.next());
				advisor.setPassword(i.next());
				advisorList.add(advisor);
			}
			return advisorList;
		} finally {
			readAdvisors.close();
		}
	}
}
