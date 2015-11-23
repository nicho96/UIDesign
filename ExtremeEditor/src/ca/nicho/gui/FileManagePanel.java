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
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ca.nicho.action.HandlerAction;

public class FileManagePanel extends JPanel implements ActionListener{
		
	private HandlerAction handler;
	
	private ActionHistoryFrame actions;
	
	private JButton open, save, delete;
	
	public FileManagePanel(HandlerAction handler, ActionHistoryFrame actions){
		this.setLayout(new GridLayout());
		this.handler = handler;
		this.actions = actions;
		ImageIcon openIcon = new ImageIcon("res/open.png");
		ImageIcon saveIcon = new ImageIcon("res/save.png");
		ImageIcon deleteIcon = new ImageIcon("res/delete.png");
		
		Image img = openIcon.getImage();
		Image openImg = img.getScaledInstance( 28, 28,  java.awt.Image.SCALE_SMOOTH );
		openIcon.setImage(openImg);
		open = new JButton(openIcon);
		open.setIcon(openIcon);
		open.addActionListener(this);
		open.setToolTipText("open");
		open.setBorder(BorderFactory.createEmptyBorder());
		open.setContentAreaFilled(false);
		
		img = saveIcon.getImage();
		Image saveImg = img.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		saveIcon = new ImageIcon(saveImg);
		save = new JButton(saveIcon);
		save.setContentAreaFilled(false);
		save.addActionListener(this);
		save.setBorder(BorderFactory.createEmptyBorder());
		save.setBorder(BorderFactory.createEmptyBorder());
		save.setToolTipText("save") ;

		

		Image deleteImg = deleteIcon.getImage();
		deleteImg = deleteImg.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		deleteIcon.setImage(deleteImg);
		delete = new JButton(deleteIcon);
		delete.setBorder(BorderFactory.createEmptyBorder(-5, -15, 0, -15));
		delete.setContentAreaFilled(false);
		delete.setToolTipText("delete");
		delete.addActionListener(this);

		SpringLayout.Constraints openC = new SpringLayout.Constraints();
		SpringLayout.Constraints saveC = new SpringLayout.Constraints();
		SpringLayout.Constraints deleteC = new SpringLayout.Constraints();
		
		this.setBackground(new Color(166,177,178));
		this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		this.add(open);
		this.add(save);
		this.add(delete);
		this.setPreferredSize(new Dimension(60,40));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	/*	if(e.getSource().equals(open))
			handler.getParent().open();
		else if(e.getSource().equals(save))
			handler.getParent().save();
		else if(e.getSource().equals(delete))
			handler.getParent().delete();
		*/	
		
	}
	
}