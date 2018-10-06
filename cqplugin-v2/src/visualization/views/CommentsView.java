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
import data_storage.CompetencyQuestionComponents.Comment;
import visualization.CQPView;
import visualization.CellRenderers.CommentsCellRenderer;

/**
 * View implemented to show the Vector of Comments attached to the CompetencyQuestion selected in
 * the IDsView.
 * @author Luis
 *
 */
public class CommentsView extends CQPView {

	private static final long serialVersionUID = -2568249980091150110L;
	private JList<Comment> comments;
	private JList<CompetencyQuestion> competencyQuestions;
	private AddCommentAction addComment;
	private RemoveCommentAction remComment;
	private EditCommentAction editComment;
	
	@Override
	public void initialise() throws Exception {
		// TODO Auto-generated method stub
		competencyQuestions = model.getIdsList();
		setLayout(new BorderLayout());
		comments = new JList<Comment>(new DefaultListModel<Comment>());
		comments.setCellRenderer(new CommentsCellRenderer());
		comments.setSelectionModel(new DefaultListSelectionModel());
		comments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneA = new JScrollPane(comments);
		add(scrollPaneA, BorderLayout.CENTER);
		addComment= new AddCommentAction();
		remComment = new RemoveCommentAction();
		editComment = new EditCommentAction();
		addAction(addComment, "A", "A");
		addAction(remComment, "A", "B");
		addAction(editComment, "A", "C");
		
		ListSelectionListener listener = new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(competencyQuestions.getSelectedIndex() > -1 && model.getSize()>0){
					generateList();
				}
				else{
					addComment.setEnabled(false);
					remComment.setEnabled(false);
					editComment.setEnabled(false);
				}
			}
		};
		model.getIdsList().addListSelectionListener(listener);
		

		generateList();		
	}

	@Override
	public void dispose() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void generateList(){
		((DefaultListModel<Comment>)comments.getModel()).removeAllElements();
		
		if(model.getIdsList().getSelectedIndex()>-1){
			Vector<Comment> coms = model.getElementAt(model.getIdsList().getSelectedIndex()).getComments(); 
			int size = coms.size();
			for(int i = 0; i<size; i++){
				((DefaultListModel<Comment>)comments.getModel()).addElement(coms.elementAt(i));
			}
			//WE CAN ACTIVATE  THE BUTTON IN THIS SCENARIO
			addComment.setEnabled(true);
			//set index and the rest of the buttons
			if(size>0){
				((DefaultListSelectionModel)comments.getSelectionModel()).setSelectionInterval(0, 0);
				remComment.setEnabled(true);
				editComment.setEnabled(true);
			}
			else{
				remComment.setEnabled(false);
				editComment.setEnabled(false);
			}
		}
		else{
			//WE MUST DEACTIVATE THE BUTTONS IN THIS SCENARIO
			addComment.setEnabled(false);
			remComment.setEnabled(false);
			editComment.setEnabled(false);
		}
	}
	
	
	class AddCommentAction extends DisposableAction{

		private static final long serialVersionUID = 940072646396933553L;

		protected AddCommentAction() {
			super("Add Comment", OWLIcons.getIcon("individual.add.png"));
			// TODO Auto-generated constructor stub
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JDialog dialog = new JDialog();
			String newComment = JOptionPane.showInputDialog(dialog, "Introduce the new comment", "New Comment Text");
			JDialog dialog2 = new JDialog();
			String author = JOptionPane.showInputDialog(dialog2,"Introduce the name of the author", "Author of the Comment");
			if(newComment != null && author != null && !newComment.equals("") && !author.equals("")){
				model.getElementAt(model.getIdsList().getSelectedIndex()).addComment(newComment, author);
				generateList();
				int size = ((DefaultListModel<Comment>)comments.getModel()).getSize()-1;
				((DefaultListSelectionModel)comments.getSelectionModel()).setSelectionInterval(size, size);
				remComment.setEnabled(true);
				editComment.setEnabled(true);
			}
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class RemoveCommentAction extends DisposableAction{

		private static final long serialVersionUID = 3564454310916159562L;

		protected RemoveCommentAction() {
			super("Delete comment", OWLIcons.getIcon("individual.delete.png"));
			// TODO Auto-generated constructor stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JDialog dialog = new JDialog();
			int confirmed = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to remove this comment?", "Remove comment", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//confirmed == 0 if Yes, == 1 if No, == -1 if Close(UpperLeft X)
			if(confirmed ==0){
				int idindex = ((DefaultListSelectionModel)competencyQuestions.getSelectionModel()).getMinSelectionIndex();
				int comindex = ((DefaultListSelectionModel)comments.getSelectionModel()).getMinSelectionIndex();
				((CQListModel)competencyQuestions.getModel()).getElementAt(idindex).deleteComment(comindex);
				generateList();
				if(((DefaultListModel<Comment>)comments.getModel()).size()<1){
					setEnabled(false);
					editComment.setEnabled(false);
					((DefaultListSelectionModel)comments.getSelectionModel()).setSelectionInterval(-1, -1);
				}
				//else: auto select the "next" entry
				else{
					if(comindex < ((DefaultListModel<Comment>)comments.getModel()).size()){
						((DefaultListSelectionModel)comments.getSelectionModel()).setSelectionInterval(comindex,comindex);
					}
					else{
						((DefaultListSelectionModel)comments.getSelectionModel()).setSelectionInterval(comindex-1, comindex-1);
					}
				}
			}
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class EditCommentAction extends DisposableAction{

		private static final long serialVersionUID = 6712958720232554928L;

		protected EditCommentAction() {
			super("Edit comment", OWLIcons.getIcon("individual.add.png"));
			// TODO Auto-generated constructor stub
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JDialog dialog = new JDialog();
			int comindex = ((DefaultListSelectionModel)comments.getSelectionModel()).getMinSelectionIndex();
			String comment = JOptionPane.showInputDialog(dialog, "Introduce the new comment", ((DefaultListModel<Comment>)comments.getModel()).getElementAt(comindex).getCommentText());
			if(comment != null && !comment.equals("")){
				((DefaultListModel<Comment>)comments.getModel()).getElementAt(comindex).setCommentText(comment);
				generateList();
				((DefaultListSelectionModel)comments.getSelectionModel()).setSelectionInterval(comindex, comindex);
			}
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
