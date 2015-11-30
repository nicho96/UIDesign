package ca.nicho.gui.components;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class CellRenderer extends DefaultListCellRenderer{

	// Any cell after this index will be colored differently
	public int stopInd = -1;
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (index > stopInd && stopInd >= 0){
			c.setEnabled(false);
		}
		return c;
	}
	
}
