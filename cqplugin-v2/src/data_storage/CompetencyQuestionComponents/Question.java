package data_storage.CompetencyQuestionComponents;

import data_storage.CompetencyQuestionComponent;
import data_storage.ID;

/**
 * The Question Component in a Competency Question. This Component stores the Question and has its own ID.
 * @author Luis
 *
 */
public class Question extends CompetencyQuestionComponent {

	private String cqtext;
	
	/** 
	 * Create a Question Component, providing its question.
	 * @param cqtext - String form of the question.
	 */
	public Question(String cqtext) {
		this.id = new ID();
		this.cqtext=cqtext;
	}
	/**
	 * Create an empty Question Component for a Competency Question.
	 */
	public Question(){
		this.id= new ID();
		this.cqtext="";
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return cqtext;
	}

	/**
	 * Updates the question.
	 * @param cqtext - String form of the question.
	 */
	public void setCqtext(String cqtext){
		this.cqtext=cqtext;
	}
	public ID getId(){
		return super.getId();
	}
}
