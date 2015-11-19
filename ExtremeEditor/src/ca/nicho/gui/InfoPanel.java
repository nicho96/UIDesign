package ca.nicho.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel{

	private JLabel countLabel;
	private JLabel messageLabel;
	
	public InfoPanel(){
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		countLabel = new JLabel("10");
		messageLabel = new JLabel("Welcome!");
		this.add(messageLabel);
		this.add(countLabel);
		this.setBackground(new Color(166,177,178));
		
	}
	
	public void setMessage(String s){
		this.messageLabel.setText(s);
	}
	
}
