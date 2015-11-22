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
import javax.swing.UIManager;
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
		open.setBorder(BorderFactory.createEtchedBorder());
		open.setContentAreaFilled(false);
		
		open.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        open.setBackground(Color.RED);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	open.setBackground(Color.BLACK);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	open.setBackground(Color.RED);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        open.setBackground(UIManager.getColor("control"));
		    }
		});
		
		
		img = saveIcon.getImage();
		Image saveImg = img.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		saveIcon = new ImageIcon(saveImg);
		save = new JButton(saveIcon);
		save.setContentAreaFilled(false);
		save.addActionListener(this);
		save.setBorder(BorderFactory.createEtchedBorder());
		save.setToolTipText("save") ;
		
		save.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        save.setBackground(Color.RED);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	save.setBackground(Color.BLACK);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	save.setBackground(Color.RED);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        save.setBackground(UIManager.getColor("control"));
		    }
		});

		Image deleteImg = deleteIcon.getImage();
		deleteImg = deleteImg.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		deleteIcon.setImage(deleteImg);
		delete = new JButton(deleteIcon);
		delete.setBorder(BorderFactory.createEtchedBorder());
		delete.setContentAreaFilled(false);
		delete.setToolTipText("delete");
		delete.addActionListener(this);
		
		delete.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        delete.setBackground(Color.RED);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	delete.setBackground(Color.BLACK);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	delete.setBackground(Color.RED);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        delete.setBackground(UIManager.getColor("control"));
		    }
		});
		
		this.setBackground(new Color(203,203,203));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.add(open);
		this.add(save);
		this.add(delete);
		this.setPreferredSize(new Dimension(105,40));
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
