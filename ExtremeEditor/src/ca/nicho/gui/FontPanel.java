package ca.nicho.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ca.nicho.action.HandlerAction;

public class FontPanel extends JPanel{
	
	private ToolPanel toolPanel;
	private CPPanel copyPastePanel;
	private FileManagePanel fileManagePanel;	
	
	public FontPanel(HandlerAction handler, ActionHistoryFrame actions){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		fileManagePanel = new FileManagePanel(handler, actions);
		toolPanel = new ToolPanel(handler, actions);
		copyPastePanel = new CPPanel(handler,actions);
		this.add(fileManagePanel);
		this.add(toolPanel);
		this.add(copyPastePanel);
		this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		this.setBackground(new Color(166,177,178));
		this.setPreferredSize(new Dimension(40,50));

	}

	public ToolPanel getToolPanel(){
		return toolPanel;
	}
	
	public CPPanel getCPPanel(){
		return copyPastePanel;
	}
	
	public FileManagePanel getFilePanel(){
		return fileManagePanel;
	}
	
	
	
}
