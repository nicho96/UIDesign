package ca.nicho.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class PreviewPanel extends JFrame{

	private JTextPane textArea;
	private JPanel bottomPanel;
	private JButton apply;
	private JButton discard;
	
	public PreviewPanel(Document doc){
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("PREVIEW");
		
		textArea = new JTextPane();
		textArea.setEditable(false);
		textArea.setText(doc.getText());
		this.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		bottomPanel = new JPanel();
		bottomPanel.add(apply = new JButton("Apply"));
		bottomPanel.add(discard = new JButton("Discard"));
		add(bottomPanel, BorderLayout.PAGE_END);
	}
	
}
