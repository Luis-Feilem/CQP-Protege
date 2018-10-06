package visualization.CellRenderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;

import data_storage.CompetencyQuestionComponents.Answer;
import visualization.CQPListCellRenderer;

/**
 * ListCellRenderer for the AnswersView
 * @author Luis
 *
 */
public class AnswersCellRenderer extends CQPListCellRenderer<Answer> {

	private static final long serialVersionUID = 5880678392668631495L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Answer> list, Answer value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		String theLabel = ((Answer)value).get();
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
