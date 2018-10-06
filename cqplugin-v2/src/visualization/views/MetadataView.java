package visualization.views;

import java.awt.BorderLayout;

import javax.swing.JTextPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import data_manipulation.CQModels.CQListModel;
import visualization.CQPView;

/**
 * View implemented to show and edit some metadata on the whole CompetencyQuestion set.
 * @author Luis
 *
 */
public class MetadataView extends CQPView {

	private static final long serialVersionUID = -4862259850993385289L;
	private String sizeLine = "", languageLine = "", ontologyLine = "";
	
	@Override
	public void initialise() throws Exception {
		// TODO Auto-generated method stub
		
		this.model = (CQListModel) model.getIdsList().getModel();
		
		setLayout(new BorderLayout());
		sizeLine = "Number of Competency Questions: " + model.getSize();
		languageLine = "Language: " + model.getLanguage();
		ontologyLine = "Ontology associated to this set: " + model.getOntology();
		final JTextPane metadata = new JTextPane();
		metadata.setText(languageLine + "\n" + ontologyLine + "\n" + sizeLine);
		metadata.setEditable(false);
		add(metadata, BorderLayout.CENTER);
		
		
		ListDataListener listener = new ListDataListener(){

			@Override
			public void contentsChanged(ListDataEvent arg0) {
				// TODO Auto-generated method stub
				sizeLine = "Number of Competency Questions: " + model.getSize();
				metadata.setText(languageLine + "\n" + ontologyLine + "\n" + sizeLine);
			}

			@Override
			public void intervalAdded(ListDataEvent arg0) {
				// TODO Auto-generated method stub
				sizeLine = "Number of Competency Questions: " + model.getSize();
				metadata.setText(languageLine + "\n" + ontologyLine + "\n" + sizeLine);
			}

			@Override
			public void intervalRemoved(ListDataEvent arg0) {
				// TODO Auto-generated method stub
				sizeLine = "Number of Competency Questions: " + model.getSize();
				metadata.setText(languageLine + "\n" + ontologyLine + "\n" + sizeLine);
			}
			
		};
		
		model.addListDataListener(listener);
	}

	@Override
	public void dispose() throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
