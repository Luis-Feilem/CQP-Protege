package data_storage;

import java.util.Collection;
import java.util.Vector;

/**
 * An extension of the Vector class restricted to have CompetencyQuestion as its delegate. It also includes attributes for 'ontology', 'language'.
 * 
 * @see java.util.Vector
 * @author Luis
 *
 */
public class CompetencyQuestionsSet extends Vector<CompetencyQuestion> {
	
	private static final long serialVersionUID = 4186871254338848598L;
	private String ontology = "";
	private String language = "";
	
	public CompetencyQuestionsSet() {
		// TODO Auto-generated constructor stub
		super();
	}

	public CompetencyQuestionsSet(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
	}

	public CompetencyQuestionsSet(Collection<? extends CompetencyQuestion> vector) {
		super(vector);
		// TODO Auto-generated constructor stub
	}

	public CompetencyQuestionsSet(int initialCapacity, int capacityIncrement) {
		super(initialCapacity, capacityIncrement);
		// TODO Auto-generated constructor stub
	}

	public String getOntology() {
		return ontology;
	}

	public void setOntology(String ontology) {
		this.ontology = ontology;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}


}
