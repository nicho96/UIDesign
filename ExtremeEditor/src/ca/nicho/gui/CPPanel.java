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
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ca.nicho.action.HandlerAction;

public class CPPanel extends JPanel implements ActionListener{
		
	private HandlerAction handler;
	
	private ActionHistoryFrame actions;
	
	private JButton copy, paste, cut;
	
	public Color idle = Color.RED;
	public Color pressed = Color.GREEN;
	
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
		copy.setBorder(BorderFactory.createEtchedBorder());
		copy.setContentAreaFilled(false);
		
		copy.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        copy.setBackground(idle);
		    }

		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	copy.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	copy.setBackground(idle);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        copy.setBackground(UIManager.getColor("control"));
		    }
		});
		
		img = pasteIcon.getImage();
		Image pasteImg = img.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		pasteIcon = new ImageIcon(pasteImg);
		paste = new JButton(pasteIcon);
		paste.setContentAreaFilled(false);
		paste.addActionListener(this);
		paste.setBorder(BorderFactory.createEtchedBorder());
		paste.setToolTipText("paste (HotKey tbd)");
		
		paste.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        paste.setBackground(idle);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	paste.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	paste.setBackground(idle);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        paste.setBackground(UIManager.getColor("control"));
		    }
		});

		

		Image cutImg = cutIcon.getImage();
		cutImg = cutImg.getScaledInstance(46, 46, java.awt.Image.SCALE_SMOOTH);
		cutIcon.setImage(cutImg);
		cut = new JButton(cutIcon);
		cut.setBorder(BorderFactory.createEtchedBorder());
		cut.setContentAreaFilled(false);
		cut.setToolTipText("cut");
		cut.addActionListener(this);
		
		cut.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        cut.setBackground(idle);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	cut.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	cut.setBackground(idle);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        cut.setBackground(UIManager.getColor("control"));
		    }
		});

		
		
		this.setBackground(new Color(203,203,203));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.add(cut);
		this.add(copy);
		this.add(paste);
		this.setPreferredSize(new Dimension(105,40));
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
