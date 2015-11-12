package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import ca.nicho.action.HandlerAction;


public class ActionHistoryFrame extends JDialog implements ActionListener{
	
	private DefaultListModel<String> list;
	private HandlerAction handler;
	
	private JList<String> listDisplay;
	
	private JButton close;
	
	public ActionHistoryFrame(JFrame frame, HandlerAction handler){
		super(frame, false);
		this.setSize(200, 200);
		
		this.handler = handler;
	
		list = new DefaultListModel<String>();
		
		listDisplay = new JList<String>(list);
		this.add(new JScrollPane(listDisplay), BorderLayout.CENTER);
		
		close = new JButton("Hide");
		close.addActionListener(this);
		this.add(close, BorderLayout.PAGE_END);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src.equals(close))
			this.setVisible(false);
	}
	
	public void toggle(){
		this.setVisible(!this.isVisible());
	}
	
	public void update(){
		for(int i = 0; i < handler.done.size(); i++){
			list.addElement(handler.done.elementAt(i).toString());
		}
	}
	
}
