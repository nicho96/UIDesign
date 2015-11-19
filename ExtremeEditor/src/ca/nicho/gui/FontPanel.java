package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import ca.nicho.action.HandlerAction;

public class FontPanel extends JPanel{
	
	private ToolPanel toolPanel;
	
	private JTextField size;
	
	public FontPanel(HandlerAction handler, ActionHistoryFrame actions){
		this.setLayout(new BorderLayout());
		toolPanel = new ToolPanel(handler, actions);
		this.add(toolPanel, BorderLayout.WEST);
		//this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(255,255,255), new Color(166,177,178)));
		this.setBackground(new Color(166,177,178));
		size = new JTextField(3);
		//this.add(size);
		
	}

}
