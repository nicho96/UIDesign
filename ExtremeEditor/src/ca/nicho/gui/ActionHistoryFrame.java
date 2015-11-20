package ca.nicho.gui;

import javax.swing.JDialog;

public class ActionHistoryFrame extends JDialog{

	private HistoryPanel history;
	
	public ActionHistoryFrame(ExtremeEditorGUI frame, HistoryPanel history){
		super(frame, false);
		this.setBounds(frame.getWidth() - 300, 150, 300, frame.getHeight() - 150);
				
		this.history = history;		
		this.add(history);
		this.setTitle("Undo/Redo History");
		
	}
	
	public HistoryPanel getHistoryFrame(){
		return history;
	}
	
	public void toggle(){
		this.setVisible(!this.isVisible());
	}
}
