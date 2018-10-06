package visualization.CellRenderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;

import data_storage.CompetencyQuestion;
import visualization.CQPListCellRenderer;

/**
 * ListCellRenderer for the IDsView
 * @author Luis
 *
 */
public class IDCellRenderer extends CQPListCellRenderer<CompetencyQuestion> {
	
	private static final long serialVersionUID = 1675094509555146319L;

	@Override
	public Component getListCellRendererComponent(JList<? extends CompetencyQuestion> list, CompetencyQuestion value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		String theLabel = ((CompetencyQuestion)value).getId().getId();
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
