package data_storage.CompetencyQuestionComponents;

import java.sql.Timestamp;

import data_storage.CompetencyQuestionComponent;
import data_storage.ID;

/**
 * A single Comment Component in a Competency Question. This Component stores a comment, its author,
 * a timestamp and has its own ID.
 * @author Luis
 *
 */
public class Comment extends CompetencyQuestionComponent {

	private String commentText;
	private String author;
	private Timestamp timestamp;
	
	/**
	 * Creates an empty Comment Component.
	 */
	public Comment(){
		this.id=new ID();
		this.commentText="";
		this.author="";
		this.timestamp=null;
	}
	/**
	 * Creates a complete Comment Component, providing the comment and its author.
	 * @param commentText - String form of the comment.
	 * @param author - String form of the author.
	 */
	public Comment(String commentText, String author) {
		// TODO Auto-generated constructor stub
		this.id= new ID();
		this.commentText=commentText;
		this.author=author;
		updateTimestamp();
	}

	/**
	 * Updates the timestamp. Should be called everytime the Comment is updated.
	 */
	public void updateTimestamp(){
		this.timestamp= new Timestamp(new java.util.Date().getTime());
	}
	@Override
	public String get() {
		// TODO Auto-generated method stub
		return toString();
	}

	/**
	 * Updates the comment stored (and timestamp).
	 * @param commentText - String form of the comment
	 */
	public void setCommentText(String commentText){
		this.commentText=commentText;
		updateTimestamp();
	}
	
	public ID getId(){
		return super.getId();
	}
	/**
	 * Updates the author of the comment (and timestamp).
	 * @param author - String form of the author
	 */
	public void setAuthor(String author){
		this.author=author;
		updateTimestamp();
	}
	/**
	 * Updates a Comment completely: comment, author and timestamp.
	 * @param commentText - String form of the comment.
	 * @param author - String form of the author.
	 */
	public void setCommentAndAuthor(String commentText, String author){
		this.commentText=commentText;
		this.author=author;
		updateTimestamp();
	}
	/**
	 * Returns the date of the last update.
	 * Formats a timestamp in JDBC timestamp escape format.
	 * @return  yyyy-mm-dd hh:mm:ss.fffffffff, where ffffffffff indicates nanoseconds
	 */
	public String getDate(){
		return this.timestamp.toString();
	}
	
	/**
	 * Prints the comment according to the format:
	 * timestamp \t author: comment.
	 */
	public String toString(){
		return getDate() + "\t" + author + ": " + commentText;
	}
}
