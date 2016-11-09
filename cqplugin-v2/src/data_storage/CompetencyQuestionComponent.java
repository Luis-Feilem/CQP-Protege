package data_storage;

/**
 * Abstract class to represent the various Competency Question Components that make up a Competency Question.
 * At this level of abstraction, we can only assure an ID as a common component in each Component, and that
 * for each Component, there will be some information we can retrieve in a similar way.
 * @author Luis
 *
 */
public abstract class CompetencyQuestionComponent extends OntologyRequirementComponent {

	protected ID id;
	
	/**
	 * Retrieves the particular information that the Component stores.
	 * @return String with the valuable information of the Component towards a Competency Question.
	 */
	public abstract String get();
	
	
	public ID getId(){
		return super.getId();
	}
}
