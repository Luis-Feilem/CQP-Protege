package data_storage.CompetencyQuestionComponents;

import data_storage.CompetencyQuestionComponent;
import data_storage.ID;

/**
 * A single Answer Component in a Competency Question. This Component stores an answer and has its own ID.
 * @author Luis
 *
 */
public class Answer extends CompetencyQuestionComponent {

	private String answerText;
	
	/**
	 * Create an Answer Component, providing its answer.
	 * @param answerText - String form of the answer
	 */
	public Answer(String answerText) {
		// TODO Auto-generated constructor stub
		this.id= new ID();
		this.answerText=answerText;
	}
	/**
	 * Create an empty Answer Component for a Competency Question
	 */
	public Answer(){
		this.id = new ID();
		this.answerText="";
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return answerText;
	}
	/**
	 * Updates the answer.
	 * @param answerText - String form of the answer
	 */
	public void setAnswerText(String answerText){
		this.answerText=answerText;
	}
	public ID getId(){
		return super.getId();
	}
}
