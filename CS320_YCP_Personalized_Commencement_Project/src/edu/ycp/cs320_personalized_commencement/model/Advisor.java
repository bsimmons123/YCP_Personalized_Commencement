package edu.ycp.cs320_personalized_commencement.model;

/**
 * Stores the advisors information.
 */
public class Advisor extends User{

	// variable to be set as true if content is accepted or false if it is rejected.
	boolean decision;
	
	private User user; // used to set Hardcoded credentials
	
	/**
	 * Hardcode test account
	 */
	public void createTestAdvisor() {
		user.setEmail("testadvisor@ycp.edu");
		user.setUsername("test");
		user.setPassword("test");
	}
	
	/**
	 * Sets the advisor's decision of content
	 */
	public void setDecision(boolean decision) { 
		this.decision = decision;
	}
	
	/**
	 * Returns the advisor's decision.
	 */
	public boolean getDecision() {
		return decision;
	}
}
