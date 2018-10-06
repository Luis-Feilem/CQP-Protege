package visualization.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.protege.editor.core.ui.view.DisposableAction;
import org.protege.editor.owl.ui.OWLIcons;

import data_manipulation.CQModels.CQListModel;
import data_storage.CompetencyQuestion;
import data_storage.CompetencyQuestionComponents.Answer;
import visualization.CQPView;
import visualization.CellRenderers.AnswersCellRenderer;

/**
 * View implemented to show and edit the set of Answers attached to the CompetencyQuestion selected in
 * the IDsView.
 * @author Luis
 *
 */
public class AnswersView extends CQPView {


	private static final long serialVersionUID = 2779721715149777893L;
	private JList<Answer> answers;
	private JList<CompetencyQuestion> competencyQuestions;
	private AddAnswerAction addAnswer;
	private RemoveAnswerAction remAnswer;
	private EditAnswerAction editAnswer;
	
	@Override
	public void initialise() throws Exception {
		// TODO Auto-generated method stub
		competencyQuestions = model.getIdsList();
		setLayout(new BorderLayout());
		answers = new JList<Answer>(new DefaultListModel<Answer>());
		answers.setCellRenderer(new AnswersCellRenderer());
		answers.setSelectionModel(new DefaultListSelectionModel());
		answers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneA = new JScrollPane(answers);
		add(scrollPaneA, BorderLayout.CENTER);
		addAnswer = new AddAnswerAction();
		remAnswer = new RemoveAnswerAction();
		editAnswer = new EditAnswerAction();
		addAction(addAnswer, "A", "A");
		addAction(remAnswer, "A", "B");
		addAction(editAnswer, "A", "C");
		
		ListSelectionListener listener = new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(competencyQuestions.getSelectedIndex() > -1 && model.getSize()>0){
					generateList();
				}
				else{
					addAnswer.setEnabled(false);
					remAnswer.setEnabled(false);
					editAnswer.setEnabled(false);
				}
			}
		};
		model.getIdsList().addListSelectionListener(listener);
		

		generateList();
	}

	public void generateList(){
		((DefaultListModel<Answer>)answers.getModel()).removeAllElements();
		
		if(model.getIdsList().getSelectedIndex()>-1){
			Vector<Answer> ans = model.getElementAt(model.getIdsList().getSelectedIndex()).getAnswers(); 
			int size = ans.size();
			for(int i = 0; i<size; i++){
				((DefaultListModel<Answer>)answers.getModel()).addElement(ans.elementAt(i));
			}
			//WE CAN ACTIVATE  THE BUTTON IN THIS SCENARIO
			addAnswer.setEnabled(true);
			//set index and the rest of the buttons
			if(size>0){
				((DefaultListSelectionModel)answers.getSelectionModel()).setSelectionInterval(0, 0);
				remAnswer.setEnabled(true);
				editAnswer.setEnabled(true);
			}
			else{
				remAnswer.setEnabled(false);
				editAnswer.setEnabled(false);
			}
		}
		else{
			//WE MUST DEACTIVATE THE BUTTONS IN THIS SCENARIO
			addAnswer.setEnabled(false);
			remAnswer.setEnabled(false);
			editAnswer.setEnabled(false);
		}
	}
	
	//Additional classes
	class AddAnswerAction extends DisposableAction{

		private static final long serialVersionUID = -2162139955893072130L;

		protected AddAnswerAction() {
			super("Add answer", OWLIcons.getIcon("individual.add.png"));
			// TODO Auto-generated constructor stub
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JDialog dialog = new JDialog();
			String newAnswer = JOptionPane.showInputDialog(dialog, "Introduce the new answer", "New Answer Text");
			if(newAnswer != null && !newAnswer.equals("")){
				model.getElementAt(model.getIdsList().getSelectedIndex()).addAnswer(newAnswer);
				generateList();
				int size = ((DefaultListModel<Answer>)answers.getModel()).getSize()-1;
				((DefaultListSelectionModel)answers.getSelectionModel()).setSelectionInterval(size, size);
				remAnswer.setEnabled(true);
				editAnswer.setEnabled(true);
			}
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}	
	}
	
	class RemoveAnswerAction extends DisposableAction{

		private static final long serialVersionUID = -8052524835503055711L;

		protected RemoveAnswerAction() {
			super("Remove answer", OWLIcons.getIcon("individual.delete.png"));
			// TODO Auto-generated constructor stub
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JDialog dialog = new JDialog();
			int confirmed = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to remove this answer?", "Remove answer", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//confirmed == 0 if Yes, == 1 if No, == -1 if Close(UpperLeft X)
			if(confirmed ==0){
				int idindex = ((DefaultListSelectionModel)competencyQuestions.getSelectionModel()).getMinSelectionIndex();
				int anindex = ((DefaultListSelectionModel)answers.getSelectionModel()).getMinSelectionIndex();
				((CQListModel)competencyQuestions.getModel()).getElementAt(idindex).deleteAnswer(anindex);
				generateList();
				if(((DefaultListModel<Answer>)answers.getModel()).size()<1){
					setEnabled(false);
					editAnswer.setEnabled(false);
					((DefaultListSelectionModel)answers.getSelectionModel()).setSelectionInterval(-1, -1);
				}
				//else: auto select the "next" entry
				else{
					if(anindex < ((DefaultListModel<Answer>)answers.getModel()).size()){
						((DefaultListSelectionModel)answers.getSelectionModel()).setSelectionInterval(anindex,anindex);
					}
					else{
						((DefaultListSelectionModel)answers.getSelectionModel()).setSelectionInterval(anindex-1, anindex-1);
					}
				}
			}
			
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class EditAnswerAction extends DisposableAction{

		private static final long serialVersionUID = -2643685923865712455L;

		protected EditAnswerAction() {
			super("Edit answer", OWLIcons.getIcon("individual.add.png"));
			// TODO Auto-generated constructor stub
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JDialog dialog = new JDialog();
			int anindex = ((DefaultListSelectionModel)answers.getSelectionModel()).getMinSelectionIndex();
			String answer = JOptionPane.showInputDialog(dialog, "Introduce the new answer", ((DefaultListModel<Answer>)answers.getModel()).getElementAt(anindex).get());
			if(answer != null && !answer.equals("")){
				((DefaultListModel<Answer>)answers.getModel()).getElementAt(anindex).setAnswerText(answer);
				generateList();
				((DefaultListSelectionModel)answers.getSelectionModel()).setSelectionInterval(anindex, anindex);
			}
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}	
	}
	
	@Override
	public void dispose() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
