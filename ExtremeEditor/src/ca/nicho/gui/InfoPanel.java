package ca.nicho.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{

	private JLabel messageLabel;
	
	public InfoPanel(){
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		messageLabel = new JLabel("(C) 2015 Apache License 2.0 - Nicholas Mercier, Paul Tonon, Alex McGraw and Diego M. Carbajal ");
		this.add(messageLabel);
		this.setBorder(BorderFactory.createEtchedBorder());
		
	}
	
	public void setMessage(String s){
		this.messageLabel.setText(s);
	}
	
}
