package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
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
	
	public FontPanel(HandlerAction handler, ActionHistoryFrame actions){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		fileManagePanel = new FileManagePanel(handler, actions);
		toolPanel = new ToolPanel(handler, actions);
		copypastePanel = new CPPanel(handler,actions);
		this.add(fileManagePanel);
		this.add(toolPanel);
		this.add(copypastePanel);
		this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.setBackground(new Color(166,177,178));
		this.setPreferredSize(new Dimension(40,50));

	}

}
