package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;


public class ActionHistoryFrame extends JDialog implements ActionListener{

	
	private JButton close;
	private HistoryPanel history;
	
	public ActionHistoryFrame(JFrame frame, HistoryPanel history){
		super(frame, false);
		this.setBounds(frame.getWidth() - 300, 150, 300, frame.getHeight() - 150);
		
		this.history = history;		
		this.add(history);
		close = new JButton("Hide");
		close.addActionListener(this);
		this.add(close, BorderLayout.PAGE_END);
		this.setTitle("Undo/Redo History");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		history.update();
		Object src = e.getSource();
		
		if(src.equals(close))
			new PreviewPanel(history.getHandler().getParent());
			//this.setVisible(false);
	}
	
	public HistoryPanel getHistoryFrame(){
		return history;
	}
	
	public void toggle(){
		this.setVisible(!this.isVisible());
	}
	
}
