package edu.ycp.cs320.personalized_commencement.model;

// does this work question mark

/**
 * Stores the advisors information.
 */
public class Advisor extends User {

	// variable to be set as true if content is accepted or false if it is rejected.
	boolean decision;
	
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
