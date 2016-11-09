package data_storage;

/**
 * Abstract class to represent the various Ontology Requirement Components that make up Ontology Requirements.
 * At this level of abstraction only an ID for the components is needed.
 * @author Luis
 *
 */
public abstract class OntologyRequirementComponent {

	private ID id;
	
	/**
	 * Returns the ID attached to this Ontology Reqruiement Component.
	 * Note that in order to obtain the String form of the ID one must call ID.getId()
	 * @return ID
	 */
	public ID getId(){
		return id;
	}
	
}
