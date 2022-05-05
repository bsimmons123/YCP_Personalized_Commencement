package edu.ycp.cs320.personalized_commencement.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.personalized_commencement.model.Advisor;
import edu.ycp.cs320.personalized_commencement.model.Student;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:York.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	// loads the students information from
	private void loadStudent(Student student, ResultSet resultSet, int index) throws SQLException {
		student.setStudentId(resultSet.getInt(index++));
		student.setAdvisorId(resultSet.getInt(index++));
		student.setEmail(resultSet.getString(index++));
		student.setPassword(resultSet.getString(index++));
		student.setFirstName(resultSet.getString(index++));
		student.setLastName(resultSet.getString(index++));
		student.setMajor(resultSet.getString(index++));
		student.setMinor(resultSet.getString(index++));
		student.setGPA(resultSet.getDouble(index++));
		student.setAward(resultSet.getString(index++));
		student.setExtraCur(resultSet.getString(index++));
		student.setPicture(resultSet.getString(index++));;
		student.setNameSound(resultSet.getString(index++));;
		student.setApproval(resultSet.getInt(index++));
		student.setCheckMajor(resultSet.getInt(index++));
		student.setCheckMinor(resultSet.getInt(index++));
		student.setCheckExtCur(resultSet.getInt(index++));
		student.setCheckImg(resultSet.getInt(index++));
		student.setCheckAudio(resultSet.getInt(index++));
		student.setShowGPA(resultSet.getInt(index++));
		
	}
	
	private void loadAdvisor(Advisor advisor, ResultSet resultSet, int index) throws SQLException {
		advisor.setAdvisorId(resultSet.getInt(index++));
		advisor.setEmail(resultSet.getString(index++));
		advisor.setPassword(resultSet.getString(index++));	
	}
	// creates the SQL table for students and advisors, assigning types to each variable
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement createStudentTable = null;
				PreparedStatement createAdvisorTable = null;
				PreparedStatement createCommentTable = null;
				
				try {
					// creates student's table
					createStudentTable = conn.prepareStatement(
							"create table students (student_id int primary key generated always as identity (start with 1, increment by 1)," +
									"advisor_id int," +
									"email varchar(40)," +
									"password varchar(40)," +
									"firstname varchar(40)," +
									"lastname varchar(40)," +
									"major varchar(40)," +
									"minor varchar(40)," +
									"gpa double,"
									+ "awards varchar(100),"
									+ "extcur varchar(500)," +
									"img varchar(500)," +
									"audio varchar(500),"
									+ "approval int,"
									+ "checkmajor int,"
									+ "checkminor int,"
									+ "checkextcur int,"
									+ "checkimg int,"
									+ "checkaudio int,"
									+ "showgpa int)"
					);	
					createStudentTable.executeUpdate();
					
					// creates advisor's table
					createAdvisorTable = conn.prepareStatement(
							"create table advisors (advisor_id int primary key generated always as identity (start with 1, increment by 1),\r\n" + 
							"email varchar(40),\r\n" + 
							"password varchar(40))"
					);
					createAdvisorTable.executeUpdate();
					
					// creates comment's table
					createCommentTable = conn.prepareStatement(
							"create table comments (student_id integer constraint student_id references students,\n" + 
							"comment varchar(500))" 
					);
					createCommentTable.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(createStudentTable);
					DBUtil.closeQuietly(createAdvisorTable);
					DBUtil.closeQuietly(createCommentTable);
				}
			}
		});
	}
	
	// load the data from CSV files into the database tables, both student and advisor tables
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Student> studentList;
				List<Advisor> advisorList;
				
				try {
					studentList = InitialData.getStudents();
					advisorList = InitialData.getAdvisors();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertStudent = null;
				PreparedStatement insertAdvisor   = null;

				try {
					// populate authors table (do authors first, since author_id is foreign key in books table)
					insertStudent = conn.prepareStatement("INSERT INTO students (advisor_id, email, password, " + 
							"firstname, lastname, major, minor, gpa, awards, extcur, img, audio, approval, checkmajor, checkminor, "
							+ "checkextcur, checkimg, checkaudio, showgpa)" + 
							"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					for (Student student : studentList) {
						insertStudent.setInt(1, student.getAdvisorId());
						insertStudent.setString(2, student.getEmail());
						insertStudent.setString(3, student.getPassword());
						insertStudent.setString(4, student.getFirst());
						insertStudent.setString(5, student.getLast());
						insertStudent.setString(6, student.getMajor());
						insertStudent.setString(7, student.getMinor());
						insertStudent.setDouble(8, student.getGPA());
						insertStudent.setString(9, student.getAward());
						insertStudent.setString(10, student.getExtraCur());
						insertStudent.setString(11, student.getPicture());
						insertStudent.setString(12, student.getNameSound());
						insertStudent.setInt(13, student.getApproval());
						insertStudent.setInt(14, student.getCheckMajor());
						insertStudent.setInt(15, student.getCheckMinor());
						insertStudent.setInt(16, student.getCheckExtCur());
						insertStudent.setInt(17, student.getCheckImg());
						insertStudent.setInt(18, student.getCheckAudio());
						insertStudent.setInt(19, student.getShowGPA());
						insertStudent.addBatch();
					}
					insertStudent.executeBatch();
					
					// populate books table (do this after authors table,
					// since author_id must exist in authors table before inserting book)
					insertAdvisor = conn.prepareStatement("INSERT INTO advisors(\n" + 
							"	email,\n" + 
							"	password)\n" + 
							"	VALUES(\n" + 
							"	?,\n" + 
							"	?)");
					for (Advisor advisor : advisorList) {
						insertAdvisor.setString(1, advisor.getEmail());
						insertAdvisor.setString(2, advisor.getPassword());
						insertAdvisor.addBatch();
					}
					insertAdvisor.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertAdvisor);
					DBUtil.closeQuietly(insertStudent);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}
	
	// returns all of the students that correspond with a specific advisor
	@Override
	public ArrayList<Student> findStudentsByAdvisor(String email) {
		return executeTransaction(new Transaction<ArrayList<Student>>() {
			@Override
			public ArrayList<Student> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				ResultSet resultSet = null;
				ResultSet resultSet1 = null;
				int advisorId = 0;
				
				try {
					stmt1 = conn.prepareStatement("select advisor_id\n" + 
							"	from advisors\n" + 
							"	where email = ?");
					
					stmt1.setString(1, email);
					
					resultSet1 = stmt1.executeQuery();
					if(resultSet1.next()) {
						Object obj = resultSet1.getObject(1);
						
						advisorId = Integer.parseInt(obj.toString());
					}
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"select students.*, advisors.*" + 
							"	from students, advisors\n" + 
							"	where advisors.advisor_id = students.advisor_id\n" + 
							"		and students.advisor_id = ?\n"
							+ " order by lastname"
					);
					
					stmt.setLong(1, advisorId);
					
					ArrayList<Student> result = new ArrayList<Student>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						// create new Author object
						// retrieve attributes from resultSet starting with index 1
						Student student = new Student();
						loadStudent(student, resultSet, 1);
						
						result.add(student);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<" + email + "> was not found in the authors table");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public Advisor getAdvisor(String email, String password) {
		return executeTransaction(new Transaction<Advisor>() {
			@Override
			public Advisor execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Advisor advisor = null;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"select * from advisors where email = ? and password = ?"
					);
					
					stmt.setString(1, email);
					stmt.setString(2, password);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						advisor = new Advisor();
						loadAdvisor(advisor, resultSet, 1);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("\t<" + email + "> was not found in the Advisors table");
					}
					
					return advisor;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	/**
	 * Get student associated with email and password
	 */
	@Override
	public Student getStudent(String email, String password) {
		return executeTransaction(new Transaction<Student>() {
			@Override
			public Student execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Student student = null;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"select * from students where email = ? and password = ?"
					);
					
					stmt.setString(1, email);
					stmt.setString(2, password);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						student = new Student();
						loadStudent(student, resultSet, 1);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("\t<" + email + "> was not found in the Students table");
					}
					
					return student;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	/**
	 * Update student associated with email and password
	 */
	@Override
	public Boolean updateStudents(String userEmail, String extraCur, String picture, String sound) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				int resultSet = -1;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"update students\r\n" + 
							"set extcur = ?, img = ?, audio = ?\n" + 
							"where email = ?"
					);
					stmt.setString(1, extraCur);
					stmt.setString(2, picture);
					stmt.setString(3, sound);
					stmt.setString(4, userEmail);
					
					resultSet = stmt.executeUpdate();
					
					// for testing that a result was returned
					Boolean found = false;
					
					if (resultSet != 0) {
						found = true;
						return found;
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<" + userEmail + "> was not found in the Student table");
					}
					
					return found;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	/**
	 * Update student associated with email and password
	 */
	@Override
	public Boolean updateStudentComment(int student_id, String comment) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement insertComment = null;
				int resultSet = 0;
				
				try {
					insertComment = conn.prepareStatement("INSERT INTO comments(\n" + 
							"	student_id,\n" + 
							"	comment)\n" + 
							"	VALUES(\n" + 
							"	?,\n" + 
							"	?)");
					insertComment.setInt(1, student_id);
					insertComment.setString(2, comment);
					
					resultSet = insertComment.executeUpdate();
					
					// for testing that a result was returned
					Boolean found = false;
					
					if (resultSet != 0) {
						found = true;
						return found;
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<" + student_id + "> was not found in the Student table");
					}
					
					return found;
				} finally {
					DBUtil.closeQuietly(insertComment);
				}
			}
		});
	}
	
	/**
	 * Get students comments
	 */
	@Override
	public ArrayList<String> getStudentComments(int student_id) {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement insertComment = null;
				ResultSet resultSet = null;
				
				try {
					insertComment = conn.prepareStatement("select comment from comments where student_id = ?");
					insertComment.setInt(1, student_id);
					
					resultSet = insertComment.executeQuery();
					
					// for testing that a result was returned
					
					ArrayList<String> comments = new ArrayList<String>();
					
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						comments.add(resultSet.getString(1));
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("\t<" + student_id + "> was not found in the Students table");
						return null;
					}
					
					return comments;
				} finally {
					DBUtil.closeQuietly(insertComment);
				}
			}
		});
	}
	
	

	@Override
	public Student findStudentsById(int id) {
		return executeTransaction(new Transaction<Student>() {
			@Override
			public Student execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Student student = null;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"select * from students where student_id = ?"
					);
					
					stmt.setInt(1, id);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						student = new Student();
						loadStudent(student, resultSet, 1);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("\t<" + id + "> was not found in the Students table");
					}
					
					return student;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public Boolean updateStudentContentSubmissions(int student_id, int extraCur, int img, int audio) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				int resultSet = -1;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"update students\r\n" + 
							"set checkextcur = ?, checkimg = ?, checkaudio = ?" +
							"where student_id = ?"
					);
					stmt.setInt(1, extraCur);
					stmt.setInt(2, img);
					stmt.setInt(3, audio);
					stmt.setInt(4, student_id);
					
					resultSet = stmt.executeUpdate();
					
					// for testing that a result was returned
					Boolean found = false;
					
					if (resultSet != 0) {
						found = true;
						return found;
					}
					
					// check if the id was found
					if (!found) {
						System.out.println("< Student_id: " + student_id + "> was not found in the Student table");
					}
					
					return found;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public Boolean updateStudentApproval(int student_id, int approval) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				int resultSet = -1;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"update students\r\n" + 
							"set approval = ? " +
							"where student_id = ?"
					);
					stmt.setInt(1, approval);
					stmt.setInt(2, student_id);
					
					resultSet = stmt.executeUpdate();
					
					// for testing that a result was returned
					Boolean found = false;
					
					if (resultSet != 0) {
						found = true;
						return found;
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("< Student_id: " + student_id + "> was not found in the Student table");
					}
					
					return found;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public Boolean updateStudentShowGPA(int student_id, int GPA) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				int resultSet = -1;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"update students\r\n" + 
							"set showgpa = ? " +
							"where student_id = ?"
					);
					stmt.setInt(1, GPA);
					stmt.setInt(2, student_id);
					
					resultSet = stmt.executeUpdate();
					System.out.println(resultSet);
					
					// for testing that a result was returned
					Boolean found = false;
					
					if (resultSet != 0) {
						found = true;
						return found;
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("< Student_id: " + student_id + "> was not found in the Student table");
					}
					
					return found;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public ArrayList<Student> getEveryStudent() {
		return executeTransaction(new Transaction<ArrayList<Student>>() {
			@Override
			public ArrayList<Student> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement("select students.*\n from students\n order by lastname");
					
					ArrayList<Student> result = new ArrayList<Student>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						// create new Student object
						// retrieve attributes from resultSet starting with index 1
						Student student = new Student();
						loadStudent(student, resultSet, 1);
						
						result.add(student);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("Student was not found in the students table");
					}

					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Student getStudentByEmail(String email) {
		return executeTransaction(new Transaction<Student>() {
			@Override
			public Student execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				Student student = null;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"select * from students where email = ?"
					);
					
					stmt.setString(1, email);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						student = new Student();
						loadStudent(student, resultSet, 1);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("\t<" + email + "> was not found in the Students table");
					}
					
					return student;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
}