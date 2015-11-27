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

import ca.nicho.action.Action;
import ca.nicho.action.HandlerAction;

public class PreviewPanel extends JDialog implements ActionListener{

	
	private JPanel mainPanel;
	private Document textArea;
	private JPanel bottomPanel;
	private JButton apply;
	private JButton discard;
	
	private HandlerAction handler;
	
	private JList<Action> list;
	private DefaultListModel<Action> model;
	
	public PreviewPanel(Document doc, HandlerAction handler, JList<Action> list, DefaultListModel<Action> model){
		super(doc.getTopFrame(), "", ModalityType.APPLICATION_MODAL);
		doc.setHandler(handler);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("PREVIEW");
		
		this.handler = handler;
		this.list = list;
		this.model = model;

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		textArea = new Document(doc.getTopFrame());
		textArea.setEditable(false);
		textArea.setText(doc.getText());
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
	
	public Document getTextArea(){
		return textArea;
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
