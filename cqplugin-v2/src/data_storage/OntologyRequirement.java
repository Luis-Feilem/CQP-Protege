package data_storage;

import java.util.List;

/**
 * Abstract class to represent the various Ontology Requirements of an ontology. 
 * At this level of abstraction, we can assume that various Ontology Requirement Components can make up an
 * Ontology Requirement, and that an ID will be attached to the Ontology Requirement.
 * @author Luis
 *
 */
public abstract class OntologyRequirement {

	@SuppressWarnings("rawtypes")
	protected List requirements;
	protected ID id;
	
	/**
	 * Returns the ID attached to the OntologyRequirement.
	 * Note that in order to obtain the String form of the ID one must call ID.getId()
	 * @return ID
	 */
	public ID getId(){
		return id;
	}
}
