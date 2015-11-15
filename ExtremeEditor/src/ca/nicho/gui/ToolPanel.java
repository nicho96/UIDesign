package ca.nicho.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import ca.nicho.action.HandlerAction;

public class ToolPanel extends JPanel implements ActionListener{
		
	private HandlerAction handler;
	
	private ActionHistoryFrame actions;
	
	private JButton undo, redo, history;
	
	public ToolPanel(HandlerAction handler, ActionHistoryFrame actions){
		
		this.handler = handler;
		this.actions = actions;
		ImageIcon undoIcon = new ImageIcon("icon.xpm");
		undo = new JButton(undoIcon);
		undo.addActionListener(this);
		undo.setToolTipText("Undo (Ctrl + Z)");

		redo = new JButton("->");
		redo.addActionListener(this);
		
		history = new JButton("VV");
		history.addActionListener(this);
		redo.setToolTipText("Redo (HotKey tbd)") ;
			
		this.add(history);
		this.add(undo);
		this.add(redo);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(undo))
			handler.getParent().undo();
		else if(e.getSource().equals(redo))
			handler.getParent().redo();
		else if(e.getSource().equals(history))
			actions.toggle();
		
	}
	
}
