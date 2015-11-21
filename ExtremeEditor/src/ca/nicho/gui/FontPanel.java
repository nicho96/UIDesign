package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import ca.nicho.action.HandlerAction;

public class FontPanel extends JPanel{
	
	private ToolPanel toolPanel;
	private CPPanel copypastePanel;
	private FileManagePanel fileManagePanel;	
	private JTextField size;
	
	public FontPanel(HandlerAction handler, ActionHistoryFrame actions){
		this.setLayout(new GridLayout(1,5));
		fileManagePanel = new FileManagePanel(handler, actions);
		toolPanel = new ToolPanel(handler, actions);
		copypastePanel = new CPPanel(handler,actions);
		this.add(fileManagePanel);
		this.add(toolPanel);
		this.add(copypastePanel);
		this.add(new JSeparator(JSeparator.VERTICAL));
		this.setBorder(BorderFactory.createCompoundBorder());
		this.setBackground(new Color(166,177,178));
		this.setPreferredSize(new Dimension(40,50));
		size = new JTextField(3);
		//this.add(size);
		
	}

}
