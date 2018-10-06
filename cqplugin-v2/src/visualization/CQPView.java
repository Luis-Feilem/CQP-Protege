package visualization;


import org.protege.editor.core.ui.view.ViewComponent;

import data_manipulation.CQModels.CQListModel;

/**
 * Abstract superclass from which the more specific views of this plugin extend.
 * Includes a CQListModel as its attributes.
 * @author Luis
 *
 */
public abstract class CQPView extends ViewComponent {
	
	private static final long serialVersionUID = -2382121722518416519L;
	protected CQListModel model = new CQListModel();
	
	
	/**
	 * Return the CQListModel.
	 * @return model - CQListModel
	 */
	public CQListModel getModel(){
		return model;
	}
}
