package edu.ycp.cs320.personalized_commencement.database;

import edu.ycp.cs320.personalized_commencement.persist.DatabaseProvider;
import edu.ycp.cs320.personalized_commencement.persist.DerbyDatabase;
import edu.ycp.cs320.personalized_commencement.persist.IDatabase;
import static org.junit.Assert.*;
import org.junit.Test;

public class DatabaseTest {
	
	@Test
	public void testGetters() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase GetterDB = DatabaseProvider.getInstance();
		assertEquals(3.91, GetterDB.getStudent("bsimmons1@ycp.edu", "test").getGPA(), 0);
		assertEquals(3.89, GetterDB.findStudentsById(4).getGPA(), 0);
		assertEquals(1, GetterDB.getAdvisor("jmoscola@ycp.edu", "test").getAdvisorId());
		assertEquals(5, GetterDB.findStudentsByAdvisor("jmoscola@ycp.edu").get(4).getStudentId());
		assertEquals(5, GetterDB.getEveryStudent().get(4).getStudentId());
	}
	
	@Test
	public void testUpdateStudentDB() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase USDB = DatabaseProvider.getInstance();
		USDB.updateStudents("mhensel@ycp.edu", "Rowing", "picture.jpg", "sound.mp4");
		assertEquals("Rowing", USDB.getStudent("mhensel@ycp.edu", "test").getExtraCur());
		assertEquals("picture.jpg", USDB.getStudent("mhensel@ycp.edu", "test").getPicture());
		assertEquals("sound.mp4", USDB.getStudent("mhensel@ycp.edu", "test").getNameSound());
	}
	
	@Test
	public void testUpdateStudentContentDB() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase USConDB = DatabaseProvider.getInstance();
		USConDB.updateStudentContentSubmissions(18, 1, 0, 1);
		assertEquals(1, USConDB.getStudent("kkapoor@ycp.edu", "test").getCheckExtCur());
		assertEquals(0, USConDB.getStudent("kkapoor@ycp.edu", "test").getCheckImg());
		assertEquals(1, USConDB.getStudent("kkapoor@ycp.edu", "test").getCheckAudio());
	}
	
	@Test
	public void testUpdateStudentApprovalDB() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase USADB = DatabaseProvider.getInstance();
		USADB.updateStudentApproval(16, 1);
		USADB.updateStudentApproval(17, 0);
		assertEquals(1, USADB.getStudent("mpalmer@ycp.edu", "test").getApproval());
		assertEquals(0, USADB.getStudent("abernard@ycp.edu", "test").getApproval());
	}
	
	@Test
	public void testUpdateStudentCommentDB() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase USComDB = DatabaseProvider.getInstance();
		USComDB.updateAdvisorComment("dschrute@ycp.edu", "Absolute Dweeb");
		assertEquals("Absolute Dweeb", USComDB.getStudent("dschrute@ycp.edu", "test").getComment());
		assertNotNull(USComDB.getStudent("amott@ycp.edu", "test").getComment());
	}
}
