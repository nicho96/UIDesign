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
	
	public Color idle = Color.BLACK;
	public Color pressed = Color.GRAY;
	
	public CPPanel(HandlerAction handler, ActionHistoryFrame actions){
		this.setLayout(new GridLayout(1,3));
		this.handler = handler;
		this.actions = actions;
		ImageIcon copyIcon = new ImageIcon("icons/ic_content_copy_black_48dp.png");
		ImageIcon pasteIcon = new ImageIcon("icons/ic_assignment_black_48dp.png");
		ImageIcon cutIcon = new ImageIcon("icons/content-cut.png");
		
		Image img = copyIcon.getImage();
		Image copyImg = img.getScaledInstance( 28, 28,  java.awt.Image.SCALE_SMOOTH );
		copyIcon.setImage(copyImg);
		copy = new JButton(copyIcon);
		copy.setIcon(copyIcon);
		copy.addActionListener(this);
		copy.setToolTipText("Copy Ctrl-Z");
		copy.setBorder(BorderFactory.createEtchedBorder());
		copy.setContentAreaFilled(false);
		copy.addMouseListener(new java.awt.event.MouseAdapter() {
			
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        copy.setBackground(idle);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	copy.setContentAreaFilled(true);
		    	copy.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	copy.setContentAreaFilled(false);
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
		paste.setToolTipText("Paste Ctrl-V");
		
		paste.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        paste.setBackground(idle);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	paste.setContentAreaFilled(true);
		    	paste.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	paste.setContentAreaFilled(false);
		    	paste.setBackground(idle);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        paste.setBackground(UIManager.getColor("control"));
		    }
		});

		

		Image cutImg = cutIcon.getImage();
		cutImg = cutImg.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		cutIcon.setImage(cutImg);
		cut = new JButton(cutIcon);
		cut.setBorder(BorderFactory.createEtchedBorder());
		cut.setContentAreaFilled(false);
		cut.setToolTipText("Cut");
		cut.addActionListener(this);
		
		cut.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        cut.setBackground(idle);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	cut.setContentAreaFilled(true);
		    	cut.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	cut.setContentAreaFilled(false);
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
