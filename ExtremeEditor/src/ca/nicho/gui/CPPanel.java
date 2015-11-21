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

public class CPPanel extends JPanel implements ActionListener{
		
	private HandlerAction handler;
	
	private ActionHistoryFrame actions;
	
	private JButton copy, paste, cut;
	
	public CPPanel(HandlerAction handler, ActionHistoryFrame actions){
		this.setLayout(new GridLayout(1,3));
		this.handler = handler;
		this.actions = actions;
		ImageIcon copyIcon = new ImageIcon("res/copy.png");
		ImageIcon pasteIcon = new ImageIcon("res/paste.png");
		ImageIcon cutIcon = new ImageIcon("res/scissors2.png");
		
		Image img = copyIcon.getImage();
		Image copyImg = img.getScaledInstance( 28, 28,  java.awt.Image.SCALE_SMOOTH );
		copyIcon.setImage(copyImg);
		copy = new JButton(copyIcon);
		copy.setIcon(copyIcon);
		copy.addActionListener(this);
		copy.setToolTipText("copy (Ctrl + Z)");
		copy.setBorder(BorderFactory.createEmptyBorder());
		copy.setContentAreaFilled(false);
		
		img = pasteIcon.getImage();
		Image pasteImg = img.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		pasteIcon = new ImageIcon(pasteImg);
		paste = new JButton(pasteIcon);
		paste.setContentAreaFilled(false);
		paste.addActionListener(this);
		paste.setBorder(BorderFactory.createEmptyBorder());
		paste.setBorder(BorderFactory.createEmptyBorder());
		paste.setToolTipText("paste (HotKey tbd)");

		

		Image cutImg = cutIcon.getImage();
		cutImg = cutImg.getScaledInstance(46, 46, java.awt.Image.SCALE_SMOOTH);
		cutIcon.setImage(cutImg);
		cut = new JButton(cutIcon);
		cut.setBorder(BorderFactory.createEmptyBorder(-5, -15, 0, -15));
		cut.setContentAreaFilled(false);
		cut.setToolTipText("cut");
		cut.addActionListener(this);

		
		
		this.setBackground(new Color(166,177,178));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.add(cut);
		this.add(copy);
		this.add(paste);
		this.setPreferredSize(new Dimension(100,50));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(copy))
			handler.getParent().copy();
		else if(e.getSource().equals(paste))
			handler.getParent().paste();
		else if(e.getSource().equals(cut))
			handler.getParent().equals(cut);
		
	}
	
}
