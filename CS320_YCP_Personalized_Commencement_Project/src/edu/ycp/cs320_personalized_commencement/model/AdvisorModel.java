package edu.ycp.cs320_personalized_commencement.model;

/**
 * Stores the advisors information.
 */
public class AdvisorModel extends UserModel{

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
