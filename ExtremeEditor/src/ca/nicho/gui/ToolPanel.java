package ca.nicho.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import ca.nicho.action.HandlerAction;

public class ToolPanel extends JPanel implements ActionListener{
		
	private HandlerAction handler;
	
	private JButton undo, redo;
	
	public ToolPanel(HandlerAction handler){
		
		this.handler = handler;
		
		undo = new JButton("<-");
		undo.addActionListener(this);
		undo.setToolTipText("Undo the previous action");

		redo = new JButton("->");
		redo.addActionListener(this);
			
		this.add(undo);
		this.add(redo);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(undo))
			handler.getParent().undo();
		else if(e.getSource().equals(redo))
			handler.getParent().redo();
		
	}
	
}
