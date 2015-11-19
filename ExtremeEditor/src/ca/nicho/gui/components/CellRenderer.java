package ca.nicho.gui.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class CellRenderer extends DefaultListCellRenderer{

	// Any cell after this index will be colored differently
	public int stopInd = -1;
	Color grey = Color.decode("0xE0E0E0"); // Hex for the color grey

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (index > stopInd && stopInd >= 0)
			setBackground(grey);
		return c;
	}
	
}
