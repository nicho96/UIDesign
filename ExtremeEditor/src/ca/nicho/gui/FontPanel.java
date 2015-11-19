package ca.nicho.gui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.nicho.action.HandlerAction;

public class FontPanel extends JPanel{
	
	private ToolPanel toolPanel;
	
	private JTextField size;
	
	public FontPanel(HandlerAction handler, ActionHistoryFrame actions){
		
		toolPanel = new ToolPanel(handler, actions);
		this.add(toolPanel);
		this.setBackground(new Color(166,177,178));
		size = new JTextField(3);
		this.add(size);
		
	}

}
