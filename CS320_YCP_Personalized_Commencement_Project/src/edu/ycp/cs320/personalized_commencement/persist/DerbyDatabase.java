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
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	private void loadStudent(Student student, ResultSet resultSet, int index) throws SQLException {
		student.setStudentId(resultSet.getInt(index++));
		student.setAdvisorId(resultSet.getInt(index++));
		student.setEmail(resultSet.getString(index++));
		student.setPassword(resultSet.getString(index++));
		student.setFirstName(resultSet.getString(index++));
		student.setLastName(resultSet.getString(index++));
		student.setMajor(resultSet.getString(index++));
		student.setMinor(resultSet.getString(index++));
		student.setExtraCur(resultSet.getString(index++));
		student.setPicture(resultSet.getString(index++));;
		student.setNameSound(resultSet.getString(index++));;
		student.setApproval(resultSet.getInt(index++));
		student.setComment(resultSet.getString(index++));
		student.setCheckMajor(resultSet.getInt(index++));
		student.setCheckMinor(resultSet.getInt(index++));
		student.setCheckExtCur(resultSet.getInt(index++));
		student.setCheckImg(resultSet.getInt(index++));
		student.setCheckAudio(resultSet.getInt(index++));
		
	}
	
	private void loadAdvisor(Advisor advisor, ResultSet resultSet, int index) throws SQLException {
		advisor.setAdvisorId(resultSet.getInt(index++));
		advisor.setEmail(resultSet.getString(index++));
		advisor.setPassword(resultSet.getString(index++));	
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table students (student_id int primary key generated always as identity (start with 1, increment by 1)," +
									"advisor_id int," +
									"email varchar(40)," +
									"password varchar(40)," +
									"firstname varchar(40)," +
									"lastname varchar(40)," +
									"major varchar(40)," +
									"minor varchar(40)," +
									"extcur varchar(40)," +
									"img varchar(40)," +
									"audio varchar(40),"
									+ "approval int,"
									+ "comment varchar(500),"
									+ "checkmajor int,"
									+ "checkminor int,"
									+ "checkextcur int,"
									+ "checkimg int,"
									+ "checkaudio int)"
					);	
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"create table advisors (advisor_id int primary key generated always as identity (start with 1, increment by 1),\r\n" + 
							"email varchar(40),\r\n" + 
							"password varchar(40))"
					);
					stmt2.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
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
							"firstname, lastname, major, minor, extcur, img, audio, approval, comment, checkmajor, checkminor, "
							+ "checkextcur, checkimg, checkaudio)" + 
							"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					for (Student student : studentList) {
						insertStudent.setInt(1, student.getAdvisorId());
						insertStudent.setString(2, student.getEmail());
						insertStudent.setString(3, student.getPassword());
						insertStudent.setString(4, student.getFirst());
						insertStudent.setString(5, student.getLast());
						insertStudent.setString(6, student.getMajor());
						insertStudent.setString(7, student.getMinor());
						insertStudent.setString(8, student.getExtraCur());
						insertStudent.setString(9, student.getPicture());
						insertStudent.setString(10, student.getNameSound());
						insertStudent.setInt(11, student.getApproval());
						insertStudent.setString(12, student.getComment());
						insertStudent.setInt(13, student.getCheckMajor());
						insertStudent.setInt(14, student.getCheckMinor());
						insertStudent.setInt(15, student.getCheckExtCur());
						insertStudent.setInt(16, student.getCheckImg());
						insertStudent.setInt(17, student.getCheckAudio());
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
							"		and students.advisor_id = ?"
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
	public Boolean updateStudents(String userEmail, 
			String major, String minor, String extraCur, 
			String picture, String sound) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				int resultSet = -1;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"update students\r\n" + 
							"set major = ?, minor = ?,\n" + 
							"  extcur = ?, img = ?, audio = ?\n" + 
							"where email = ?"
					);
					stmt.setString(1, major);
					stmt.setString(2, minor);
					stmt.setString(3, extraCur);
					stmt.setString(4, picture);
					stmt.setString(5, sound);
					stmt.setString(6, userEmail);
					
					resultSet = stmt.executeUpdate();
					
					// for testing that a result was returned
					Boolean found = false;
					
					if (resultSet != -1) {
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
	public Boolean updateAdvisorComment(String userEmail, String comment) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				int resultSet = 0;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"update students\n" + 
							"set comment = ? " + 
							"where email = ?"
					);
					stmt.setString(1, comment);
					stmt.setString(2, userEmail);
					
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
						System.out.println("<" + userEmail + "> was not found in the Student table");
					}
					
					return found;
				} finally {
					DBUtil.closeQuietly(stmt);
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
	public Boolean updateStudentContentSubmissions(int student_id, int major, int minor, int extraCur, int img, int audio) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				int resultSet = -1;
				
				try {
					// retreive all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"update students\r\n" + 
							"set checkmajor = ?, checkminor = ?, checkextcur = ?, checkimg = ?, checkaudio = ?" +
							"where student_id = ?"
					);
					stmt.setInt(1, major);
					stmt.setInt(2, minor);
					stmt.setInt(3, extraCur);
					stmt.setInt(4, img);
					stmt.setInt(5, audio);
					stmt.setInt(6, student_id);
					
					resultSet = stmt.executeUpdate();
					
					// for testing that a result was returned
					Boolean found = false;
					
					if (resultSet != -1) {
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
					
					if (resultSet != -1) {
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
}