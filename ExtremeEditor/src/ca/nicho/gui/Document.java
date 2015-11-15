package ca.nicho.gui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

import ca.nicho.action.Action;
import ca.nicho.action.HandlerAction;
import ca.nicho.keyboard.Keystrokes;

public class Document extends JTextPane{

	private HandlerAction handler;
	
	public Document(){		
		KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK);
		this.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
		this.setCaretPosition(this.getDocument().getLength());
	}
	
	public void printText(){
		System.out.println(this.getText());
	}
	
	public void remove(int pos, int length){
		try {
			this.getDocument().remove(pos, length);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void insertString(String text){
		insertString(this.getCaretPosition(), text);
	}
	
	public void insertString(int pos, String text){
		try {
			this.getDocument().insertString(pos, text, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void undo(){
		
		if(handler.getDoneSize() == 0)
			return;
		Action a = handler.popDone();
		a.undoAction();
		handler.addUndoneAction(a);
	}
	
	public void redo(){
		if(handler.getUndoneSize() == 0)
			return;
		Action a = handler.popUndone();
		a.redoAction();
		handler.addDoneAction(a);
	}
	
	public void setHandler(HandlerAction handler){
		this.handler = handler;
		this.addKeyListener(new Keystrokes(handler));
	}
	
}
