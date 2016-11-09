package data_storage.CompetencyQuestionComponents;

import data_storage.CompetencyQuestionComponent;
import data_storage.ID;

/**
 * The Status of a Competency Question. This Component stores the status and has its own ID.
 * @author Luis
 *
 */
public class Status extends CompetencyQuestionComponent {

	private String status;
	private final String accepted = "Accepted";
	private final String proposed = "Proposed";
	private final String incomplete = "Incomplete";
	private final String rejected = "Rejected";
	
	/**
	 * Create a Status Component, providing its status. 
	 * If the status provided is not in the list of possible statuses, the initial status is set to an empty string.
	 * @param status - String the status will be set to. Accepted values are: Accepted, Proposed, Incomlete and Rejected. "" is default.
	 */
	public Status(String status) {
		// TODO Auto-generated constructor stub
		this.id = new ID();
		if(status.equals(accepted) || status.equals(proposed) || status.equals(incomplete) || status.equals(rejected))
			this.status=status;
		else
			clearStatus();
	}
	/**
	 * Create an empty Status Component.
	 */
	public Status (){
		this.id=new ID();
		this.status="";
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return this.status;
	}
	/**
	 * Set the status to: Accepted
	 */
	public void setAccepted(){
		this.status=accepted;
	}
	/**
	 * Set the status to: Proposed
	 */
	public void setProposed(){
		this.status=proposed;
	}
	/**
	 * Set the status to: Incomplete
	 */
	public void setIncomplete(){
		this.status=incomplete;
	}
	/**
	 * Set the status to: Rejected
	 */
	public void setRejected(){
		this.status=rejected;
	}
	/**
	 * Sets the status to an empty String
	 */
	public void clearStatus(){
		this.status="";
	}
	public ID getId(){
		return super.getId();
	}
}
