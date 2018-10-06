package visualization.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.protege.editor.core.ui.view.DisposableAction;
import org.protege.editor.owl.ui.OWLIcons;

import data_manipulation.CQModels.CQListModel;
import data_storage.CompetencyQuestion;
import visualization.CQPView;
import visualization.CellRenderers.IDCellRenderer;

/**
 * View implemented to show and edit the amount and identifiers of CompetencyQuestions in the set.
 * It is also used to select one CompetencyQuestion and further edit it using the other views.
 * @author Luis
 *
 */
public class IDView extends CQPView {


	private static final long serialVersionUID = -6196896147951569573L;
	private JList<CompetencyQuestion> competencyQuestions;
	private AddEntryAction addEntry;
	private RemoveEntryAction remEntry;
	
	
	@Override
	public void initialise() throws Exception {
		// TODO Auto-generated method stub
//		competencyQuestions = new JList<CompetencyQuestion>(questions.getQuestions());
//	//	competencyQuestions  =  new JList<CompetencyQuestion>(new DefaultListModel<CompetencyQuestion>());
//	//	int size = questions.getQuestions().size();
//	//	for(int i = 0; i<size; i++){
//	//		((DefaultListModel<CompetencyQuestion>)competencyQuestions.getModel()).addElement(questions.getQuestions().elementAt(i));
//	//	}
		competencyQuestions = new JList<CompetencyQuestion>(model);
		model.setIdsList(competencyQuestions);
		setLayout(new BorderLayout());
		
		competencyQuestions.setCellRenderer(new IDCellRenderer());
		competencyQuestions.setSelectionModel(new DefaultListSelectionModel());
		competencyQuestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JScrollPane scrollPanelA = new JScrollPane(competencyQuestions);
		add(scrollPanelA, BorderLayout.CENTER);
		addEntry = new AddEntryAction();
		addAction(addEntry, "A", "A");
		remEntry = new RemoveEntryAction();
		addAction(remEntry, "B", "B");
		
	}

	@Override
	public void dispose() throws Exception {
		// TODO Auto-generated method stub

	}
	
//	public static JList<CompetencyQuestion> getJList(){
//		return competencyQuestions;
//	}
	
	
	
	//Additional classes
	class AddEntryAction extends DisposableAction {
		
		private static final long serialVersionUID = -2175548488615695777L;
		public AddEntryAction() {
			super("Add Entry", OWLIcons.getIcon("individual.add.png"));
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			((CQListModel)competencyQuestions.getModel()).addElement(new CompetencyQuestion());;
			int size = ((CQListModel)competencyQuestions.getModel()).getSize()-1;
			((DefaultListSelectionModel)competencyQuestions.getSelectionModel()).setSelectionInterval(size, size);
			remEntry.setEnabled(true);
		}
		@Override
		public void dispose() {
			// TODO Auto-generated method stub
		}

	}
	
	class RemoveEntryAction extends DisposableAction {

		private static final long serialVersionUID = -5116555215062965270L;

		public RemoveEntryAction() {
			super("Remove Entry", OWLIcons.getIcon("individual.delete.png"));
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int index = ((DefaultListSelectionModel)competencyQuestions.getSelectionModel()).getMinSelectionIndex();
			((CQListModel)competencyQuestions.getModel()).removeElementAt(index);
			//if there are no entries left, deactivate the button and empty the selection
			if(((CQListModel)competencyQuestions.getModel()).size()<1){
				setEnabled(false);
				((DefaultListSelectionModel)competencyQuestions.getSelectionModel()).setSelectionInterval(-1, -1);
			}
			//else: auto select the "next" entry
			else{
				if(index < ((CQListModel)competencyQuestions.getModel()).size()){
					((DefaultListSelectionModel)competencyQuestions.getSelectionModel()).setSelectionInterval(index, index);
				}
				else{
					((DefaultListSelectionModel)competencyQuestions.getSelectionModel()).setSelectionInterval(index-1, index-1);
				}
			}
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub

		}

	}
	
}
