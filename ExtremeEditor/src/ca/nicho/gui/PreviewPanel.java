package ca.nicho.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import ca.nicho.action.HandlerAction;

public class PreviewPanel extends JFrame{

	private Document textArea;
	private JPanel bottomPanel;
	private JButton apply;
	private JButton discard;
	
	private HandlerAction handler;
	
	public PreviewPanel(Document doc, HandlerAction handler){
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("PREVIEW");
		
		textArea = new Document();
		textArea.setEditable(false);
		textArea.setText(doc.getText());
		this.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		bottomPanel = new JPanel();
		bottomPanel.add(apply = new JButton("Apply"));
		bottomPanel.add(discard = new JButton("Discard"));
		add(bottomPanel, BorderLayout.PAGE_END);
		this.handler = handler;
	}
	
	public Document getTextArea(){
		return textArea;
	}
	
}
