package visualization.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data_storage.CompetencyQuestion;
import data_storage.CompetencyQuestionComponents.Status;
import visualization.CQPView;

/**
 * View implemeted to show the Status attached to the CompetencyQuestion selected in the IDsView
 * @author Luis
 *
 */
public class StatusView extends CQPView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4171158746667734385L;
	private JList<CompetencyQuestion> competencyQuestions;
	private JRadioButton incomplete, accepted, rejected, proposed;
//	private SaveStatusAction button;
	
	@Override
	public void initialise() throws Exception {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		
		competencyQuestions = model.getIdsList();
		
		JTextArea hint = new JTextArea("Select the status of the requirement.");
		hint.setEditable(false);
		
		incomplete = new JRadioButton("Incomplete", true);
		incomplete.setActionCommand(Status.incomplete);
		
		proposed = new JRadioButton("Proposed", false);
		proposed.setActionCommand(Status.proposed);
		
		accepted = new JRadioButton("Accepted", false);
		accepted.setActionCommand(Status.accepted);
		
		rejected = new JRadioButton("Rejected", false);
		rejected.setActionCommand(Status.rejected);
				
		//gather all the buttons into a single group so mutual exclusion is assured
		final ButtonGroup buttons = new ButtonGroup();
		
		buttons.add(incomplete);
		buttons.add(proposed);
		buttons.add(accepted);
		buttons.add(rejected);
		
		//action listener to the radio buttons
		ActionListener actionListener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				model.getElementAt(model.getIdsList().getSelectedIndex()).setStatus(e.getActionCommand());
			}
		};
		
		incomplete.addActionListener(actionListener);
		proposed.addActionListener(actionListener);
		accepted.addActionListener(actionListener);
		rejected.addActionListener(actionListener);
		
		//buttons is a logical unit, not a graphical one
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(incomplete);
		buttonsPanel.add(proposed);
		buttonsPanel.add(accepted);
		buttonsPanel.add(rejected);
		

		add(hint, BorderLayout.NORTH);
		add(buttonsPanel, BorderLayout.CENTER);
		
		
		//listSelectionListener to manage changes in the selection of a CompetencyQuestion
		ListSelectionListener listener = new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(competencyQuestions.getSelectedIndex() > -1 && model.getSize()>0){
					Enumeration<AbstractButton> enumeration = buttons.getElements();
					while (enumeration.hasMoreElements()) {
					    enumeration.nextElement().setEnabled(true);
					}
					String status = model.getElementAt(model.getIdsList().getSelectedIndex()).getStatus().get();
					switch (status){
					case Status.incomplete:
						incomplete.setSelected(true);
						break;
					case Status.accepted:
						accepted.setSelected(true);
						break;
					case Status.proposed:
						proposed.setSelected(true);
						break;
					case Status.rejected:
						rejected.setSelected(true);
						break;
					default:
						incomplete.setSelected(true);
						model.getElementAt(model.getIdsList().getSelectedIndex()).setStatus(Status.incomplete);
					}
				}
				else{
					Enumeration<AbstractButton> enumeration = buttons.getElements();
					while (enumeration.hasMoreElements()) {
					    enumeration.nextElement().setEnabled(false);
					}
				}
			}
		};
		model.getIdsList().addListSelectionListener(listener);
		
		Enumeration<AbstractButton> enumeration = buttons.getElements();
		while (enumeration.hasMoreElements()) {
		    enumeration.nextElement().setEnabled(false);
		}
	}

	@Override
	public void dispose() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
