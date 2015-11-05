package ca.nicho.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.nicho.action.HandlerAction;

public class FontPanel extends JPanel{
	
	private ToolPanel toolPanel;
	
	private JTextField size;
	
	public FontPanel(HandlerAction handler){
		
		toolPanel = new ToolPanel(handler);
		this.add(toolPanel);
		
		size = new JTextField(3);
		this.add(size);
		
	}

}
