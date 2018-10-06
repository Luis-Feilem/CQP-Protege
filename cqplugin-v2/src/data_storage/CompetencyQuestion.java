package data_storage;

import java.util.ArrayList;
import java.util.Vector;

import data_storage.CompetencyQuestionComponents.Answer;
import data_storage.CompetencyQuestionComponents.Comment;
import data_storage.CompetencyQuestionComponents.Question;
import data_storage.CompetencyQuestionComponents.Status;

/**
 * A Competency Question is an Ontology Requirement composesd of a Question, some Answers, a Status and some Comments.
 * For the answers, we use an ArrayList of Answer, while for the comments we use a LinkedList, ordering its elements 
 * by timestamp of the comment.
 * @author Luis
 *
 */
public class CompetencyQuestion extends OntologyRequirement {

	private Question question;
	private Status status;
//	private List<Comment> comments; JList are better used with arrays, Vectors and adding elements one at a time
	private Vector<Comment> comments;
//	private List<Answer> answers;
	private Vector<Answer> answers;
	/**
	 * Create a Competency Question. Values are empty by default (except for its ID)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CompetencyQuestion() {

		question = new Question();
		status = new Status();
		comments = new Vector<Comment>();
		answers = new Vector<Answer>();
		
		requirements = new ArrayList(4);
		requirements.add(question);
		requirements.add(status);
		requirements.add(comments);
		requirements.add(answers);
		
		id = new ID();
	}

	//GETTERS
	public ID getId(){
		return super.getId();
	}
	
	//Question
	/**
	 * Retrieve the question
	 * @return The Question attached to this CompetencyQuestion
	 */
	public Question getQuestion(){
		return question;
	}
	//Status
	/**
	 * Retrieve the status
	 * @return The Status attached to this CompetencyQuestion
	 */
	public Status getStatus(){
		return status;
	}
	//Answers
	/**
	 * Retrieve the answers
	 * @return The Vector of Answer attached to this CompetencyQuestion
	 */
	public Vector<Answer> getAnswers(){
		return answers;
	}
	//Comments
	/**
	 * Retrieve the comments
	 * @return The Vector of Comment attached to this CompetencyQuestion
	 */
	public Vector<Comment> getComments(){
		return comments;
	}
	
	//SETTERS
	
	//Question
	/**
	 * Updates the question.
	 * @param cqtext - String form of the question
	 */
	public void updateQuestion(String question){
		this.question.setCqtext(question);
	}
	
	//Status
	/**
	 * Set the status to: Accepted
	 */
	public void setStatusAccepted(){
		this.status.setAccepted();
	}
	/**
	 * Set the status to: Proposed
	 */
	public void setStatusProposed(){
		this.status.setProposed();
	}
	/**
	 * Set the status to: Incomplete
	 */
	public void setStatusIncomplete(){
		this.status.setIncomplete();
	}
	/**
	 * Set the status to: Rejected
	 */
	public void setStatusRejected(){
		this.status.setRejected();
	}
	/**
	 * Sets the status to an empty String
	 */
	public void clearStatus(){
		this.status.clearStatus();
	}
	/**
	 * @see data_storage.CompetencyQuestionComponents.Status#setStatus(String)
	 */
	public boolean setStatus(String status){
		return this.status.setStatus(status);
	}
	//Answers
	/**
	 * Adds an empty Answer to the vector of answers.
	 */
	public void addAnswer(){
		answers.add(new Answer());
	}
	/**
	 * Adds an Answer, providing said answer.
	 * @param answer - String form of the answer
	 */
	public void addAnswer(String answer){
		answers.add(new Answer(answer));
	}
	/**
	 * Edits the Answer in a given position of the vector.
	 * @param pos - position of the answer within the vector of Answer.
	 * @param answer - new String form of the answer
	 */
	public void editAnswer(int pos, String answer){
		if(pos > -1 && pos<answers.size())
			answers.get(pos).setAnswerText(answer);
	}
	/**
	 * Deletes an Answer in the vector.
	 * @param pos - position of the answer within the vector of Answer.
	 */
	public void deleteAnswer(int pos){
		if(pos > -1 && pos<answers.size())
			answers.remove(pos);
	}
	//Comments
	/**
	 * Adds an empty Comment to the vector of comments.
	 */
	public void addComment(){
		comments.add(new Comment());
	}
	/**
	 * Adds an Comment, providing said comment and its author.
	 * @param comment - String form of the comment.
	 * @param author - String form of the author.
	 */
	public void addComment(String comment, String author){
		comments.add(new Comment(comment, author));
	}
	/**
	 * Edits the Comment in a given position of the vector.
	 * @param pos - position of the Comment within the vector of Comment
	 * @param comment - new String form of the comment.
	 */
	public void editComment(int pos, String comment){
		if(pos > -1 && pos<comments.size())
			comments.get(pos).setCommentText(comment);
	}
	/**
	 * Deletes an Comment in the vector.
	 * @param pos - position of the comment within the vector of Comment.
	 */
	public void deleteComment(int pos){
		if(pos > -1 && pos<comments.size())
			comments.remove(pos);
	}
}
