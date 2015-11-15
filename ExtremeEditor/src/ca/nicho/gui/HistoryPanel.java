package ca.nicho.gui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ca.nicho.action.HandlerAction;

public class HistoryPanel extends JPanel{

	private DefaultListModel<String> list;
	private HandlerAction handler;
	
	private JList<String> listDisplay;
	
	public HistoryPanel(HandlerAction handler){
		
		this.handler = handler;
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		list = new DefaultListModel<String>();
		
		listDisplay = new JList<String>(list);
		this.add(new JScrollPane(listDisplay), BorderLayout.CENTER);
		
	}
	
	public void update(){
		for(int i = 0; i < handler.done.size(); i++){
			list.addElement(handler.done.elementAt(i).toString());
		}
	}

}
