package ca.nicho.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
		Image undoImg = img.getScaledInstance( 28, 28,  java.awt.Image.SCALE_SMOOTH );
		img = redoIcon.getImage();
		Image redoImg = img.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);

		undoIcon.setImage(undoImg);
		redoIcon = new ImageIcon(redoImg);
		undo = new JButton(undoIcon);
		undo.setIcon(undoIcon);
		undo.addActionListener(this);
		undo.setToolTipText("Undo (Ctrl + Z)");
		undo.setBorder(BorderFactory.createEmptyBorder(0,0,3,0));
		undo.setContentAreaFilled(false);
		this.setBackground(new Color(166,177,178));
		

		redo = new JButton(redoIcon);
		redo.setBorder(BorderFactory.createEmptyBorder());
		redo.setContentAreaFilled(false);
		redo.addActionListener(this);
		redo.setBorder(BorderFactory.createEmptyBorder(0,0,3,0));
		redo.setToolTipText("Redo (HotKey tbd)") ;
		
		ImageIcon historyIcon = new ImageIcon("res/history.png");
		Image histImg = historyIcon.getImage();
		histImg = histImg.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
		historyIcon.setImage(histImg);
		history = new JButton(historyIcon);
		//TODO: Incomment this once history image is commited
		//history.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));
		history.setContentAreaFilled(false);
		history.addActionListener(this);
		this.add(history);
		this.add(undo);
		this.add(redo);
		this.setPreferredSize(new Dimension(250,40));
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
