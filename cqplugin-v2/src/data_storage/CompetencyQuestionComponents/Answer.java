package data_storage.CompetencyQuestionComponents;

import data_storage.CompetencyQuestionComponent;

/**
 * A single Answer Component in a Competency Question. This Component stores an answer.
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
		this.answerText=answerText;
	}
	/**
	 * Create an empty Answer Component for a Competency Question
	 */
	public Answer(){
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
}
