package data_storage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

	private ID id;
	private Question question;
	private Status status;
	private List<Comment> comments;
	private List<Answer> answers;
	
	/**
	 * Create a Competency Question. Values are empty by default (except for its ID)
	 */
	public CompetencyQuestion() {
		// TODO Auto-generated constructor stub
		this.id = new ID();
		this.question = new Question();
		this.status = new Status();
		this.comments = new LinkedList<Comment>();
		this.answers = new ArrayList<Answer>();
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
	 * @return The list of Answer attached to this CompetencyQuestion
	 */
	public List<Answer> getAnswers(){
		return answers;
	}
	//Comments
	/**
	 * Retrieve the comments
	 * @return The list of Comment attached to this CompetencyQuestion
	 */
	public List<Comment> getComments(){
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
	//Answers
	/**
	 * Adds an empty Answer to the list of answers.
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
	 * Edits the Answer in a given position of the list.
	 * @param pos - position of the answer within the list of Answer.
	 * @param answer - new String form of the answer
	 */
	public void editAnswer(int pos, String answer){
		if(pos<answers.size())
			answers.get(pos).setAnswerText(answer);
	}
	/**
	 * Deletes an Answer in the list.
	 * @param pos - position of the answer within the list of Answer.
	 */
	public void deleteAnswer(int pos){
		if(pos<answers.size())
			answers.remove(pos);
	}
	//Comments
	/**
	 * Adds an empty Comment to the list of comments.
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
	 * Edits the Comment in a given position of the list.
	 * @param pos - position of the Comment within the list of Comment
	 * @param comment - new String form of the comment.
	 */
	public void editComment(int pos, String comment){
		if(pos<comments.size())
			comments.get(pos).setCommentText(comment);
	}
	/**
	 * Deletes an Comment in the list.
	 * @param pos - position of the comment within the list of Comment.
	 */
	public void deleteComment(int pos){
		if(pos<comments.size())
			comments.remove(pos);
	}
}
