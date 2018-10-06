package visualization.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.protege.editor.core.ui.view.DisposableAction;
import org.protege.editor.owl.ui.OWLIcons;

import data_storage.CompetencyQuestion;
import visualization.CQPView;

/**
 * View implemented to show and edit the Question attached to the CompetencyQuestion selected in the
 * IDs view.
 * @author Luis
 *
 */
public class QuestionView extends CQPView {


	private static final long serialVersionUID = -7558476111613490793L;
	private JList<CompetencyQuestion> competencyQuestions;
	private JTextArea stored;
	private SaveCQAction button;
	
	@Override
	public void initialise() throws Exception {
		
		competencyQuestions = model.getIdsList();
		setLayout(new BorderLayout());
		
		JTextArea hint = new JTextArea("Click the button above to edit this Competency Question.\nBelow you can see the one currently stored.");
		hint.setEditable(false);
		stored = new JTextArea();
		stored.setBackground(new Color(255,255,150));
		stored.setEditable(false);
		
		add(hint, BorderLayout.NORTH);
		add(stored, BorderLayout.CENTER);
		button = new SaveCQAction();
		addAction(button, "A", "A");
		
		
		ListSelectionListener listener = new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(competencyQuestions.getSelectedIndex() > -1 && model.getSize()>0){
					stored.setText(model.getElementAt(competencyQuestions.getSelectedIndex()).getQuestion().get());
					button.setEnabled(true);
				}
				else{
					stored.setText("");
					button.setEnabled(false);
				}
			}
		};
		competencyQuestions.addListSelectionListener(listener);
		
		
	}

	@Override
	public void dispose() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	public void setStoredQuestion(String question){
		stored.setText(question);
		model.getElementAt(competencyQuestions.getSelectedIndex()).updateQuestion(question);
	}
	
	public void setJTextsOnSelection(){
		stored.setText(model.getElementAt(competencyQuestions.getSelectedIndex()).getQuestion().get());
	}
	
	class SaveCQAction extends DisposableAction {

		private static final long serialVersionUID = -593162679761710629L;

		
		public SaveCQAction() {
			//OWLIcons.getIcon("individual.add.png")
			super("Save CQ", OWLIcons.getIcon("individual.add.png"));
			super.setEnabled(model.getSize()>0);
		}

		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			JDialog dialog = new JDialog();
			int index = model.getIdsList().getSelectedIndex();
			String newQuestion = JOptionPane.showInputDialog(dialog, "Introduce the new Question", model.getElementAt(index).getQuestion().get());
			if(newQuestion != null && !newQuestion.equals("")){
				model.getElementAt(index).updateQuestion(newQuestion);
				setStoredQuestion(model.getElementAt(index).getQuestion().get());
			}
		}
		@Override
		public void dispose() {
			// TODO Auto-generated method stub
		}

	}
}
