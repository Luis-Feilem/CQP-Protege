package data_storage;

/**
 * This class represents the idea of an ID. The reason behind creating a whole class just for this purpose is 
 * so that IDs are thought of as separate entities rather than "just some String".
 * @author Luis
 *
 */
public class ID {

	private String id;
	private static int next = 1;
	
	/**
	 * Creates a new instance of an ID
	 */
	public ID(){
		this.id=Integer.toString(next);
		next++;
	}
	
	/**
	 * Returns the string component of this ID
	 * @return id - the String form of the ID
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * Resets the ID list to start over again from 1.
	 * WARNING: THE USE OF THIS METHOD IS HIGHLY RECOMMENDED AGAINST. The only purpose of implementing it is
	 * in the case of, after a really long time of usage, the scope of the 'int' datatype is reached and 
	 * a reset must be performed.
	 */
	public void resetIdCount(){
		next=1;
	}
}
