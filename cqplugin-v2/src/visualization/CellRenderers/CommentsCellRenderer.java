package visualization.CellRenderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;

import data_storage.CompetencyQuestionComponents.Comment;
import visualization.CQPListCellRenderer;

/**
 * ListCellRenderer for the CommentsView
 * @author Luis
 *
 */
public class CommentsCellRenderer extends CQPListCellRenderer<Comment> {

	
	private static final long serialVersionUID = 8470117135801383882L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Comment> list, Comment value, int index, 
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		String theLabel = ((Comment)value).get();
		setText(theLabel);
		if(isSelected){
			setOpaque(true);
			setBackground(Color.blue);
			setForeground(Color.white);
		}
		else
		{
			setOpaque(false);
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		return this;
	}

}
