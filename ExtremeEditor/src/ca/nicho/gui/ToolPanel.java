package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ca.nicho.action.HandlerAction;

public class ToolPanel extends JPanel implements ActionListener{
		
	private HandlerAction handler;
	
	private ActionHistoryFrame actions;
	
	private JButton undo, redo, history;
	
	public ToolPanel(HandlerAction handler, ActionHistoryFrame actions){
		this.setLayout(new GridLayout(1,3));
		this.handler = handler;
		this.actions = actions;
		ImageIcon undoIcon = new ImageIcon("res/undo2.png");
		ImageIcon redoIcon = new ImageIcon("res/redo2.png");
		ImageIcon historyIcon = new ImageIcon("res/history2.png");

		
		Image img = undoIcon.getImage();
		Image undoImg = img.getScaledInstance( 28, 28,  java.awt.Image.SCALE_SMOOTH );
		undoIcon.setImage(undoImg);
		undo = new JButton(undoIcon);
		undo.setIcon(undoIcon);
		undo.addActionListener(this);
		undo.setToolTipText("Undo (Ctrl + Z)");
		undo.setBorder(BorderFactory.createEmptyBorder());
		undo.setContentAreaFilled(false);
		
		img = redoIcon.getImage();
		Image redoImg = img.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		redoIcon = new ImageIcon(redoImg);
		redo = new JButton(redoIcon);
		redo.setContentAreaFilled(false);
		redo.addActionListener(this);
		redo.setBorder(BorderFactory.createEmptyBorder());
		redo.setBorder(BorderFactory.createEmptyBorder());
		redo.setToolTipText("Redo (HotKey tbd)") ;

		
		Image histImg = historyIcon.getImage();
		histImg = histImg.getScaledInstance(36, 36, java.awt.Image.SCALE_SMOOTH);
		historyIcon.setImage(histImg);
		history = new JButton(historyIcon);
		history.setBorder(BorderFactory.createEmptyBorder(3, -15, 0, -15));
		history.setContentAreaFilled(false);
		history.setToolTipText("Undo/Redo History");
		history.addActionListener(this);

		
		
		this.setBackground(new Color(166,177,178));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.add(history);
		this.add(undo);
		this.add(redo);
		this.setPreferredSize(new Dimension(100,50));
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
