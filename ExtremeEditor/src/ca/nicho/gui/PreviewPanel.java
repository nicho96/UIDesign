package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import ca.nicho.action.Action;
import ca.nicho.action.HandlerAction;

public class PreviewPanel extends JDialog implements ActionListener{

	
	private JPanel mainPanel;
	private JTextPane textArea;
	private JPanel bottomPanel;
	private JButton apply;
	private JButton discard;
	
	private HandlerAction handler;
	
	private JList<Action> list;
	private DefaultListModel<Action> model;
	
	public PreviewPanel(String content, HandlerAction handler, JList<Action> list, DefaultListModel<Action> model){

		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("PREVIEW");
		
		this.handler = handler;
		this.list = list;
		this.model = model;

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		this.textArea = new JTextPane();
		textArea.setText(content);
		textArea.setEditable(false);
		mainPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		bottomPanel = new JPanel();
		bottomPanel.add(apply = new JButton("Apply"));
		bottomPanel.add(discard = new JButton("Discard"));
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		add(mainPanel);
		
		apply.addActionListener(this);
		discard.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(apply)){
			handler.bulkUndo(model, list);
			this.dispose();
		}else{
			this.dispose();
		}
	}
	
}
