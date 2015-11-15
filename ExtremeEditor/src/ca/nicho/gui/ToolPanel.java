package ca.nicho.gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
		ImageIcon undoIcon = new ImageIcon("res/undo.png");
		ImageIcon redoIcon = new ImageIcon("res/redo.png");
		
		Image img = undoIcon.getImage();
		Image undoImg = img.getScaledInstance( 32, 32,  java.awt.Image.SCALE_SMOOTH );
		img = redoIcon.getImage();
		Image redoImg = img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);

		undoIcon.setImage(undoImg);
		redoIcon = new ImageIcon(redoImg);
		undo = new JButton(undoIcon);
		undo.setIcon(undoIcon);
		undo.addActionListener(this);
		undo.setToolTipText("Undo (Ctrl + Z)");
		undo.setBorder(BorderFactory.createEmptyBorder());
		undo.setContentAreaFilled(false);

		redo = new JButton(redoIcon);
		redo.setBorder(BorderFactory.createEmptyBorder());
		redo.setContentAreaFilled(false);
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
