package ca.nicho.gui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import ca.nicho.action.Action;
import ca.nicho.action.HandlerAction;
import ca.nicho.keyboard.Keystrokes;

public class Document extends JTextPane{

	public HandlerAction handler;
	
	
	public Document(){		
		KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK);
		this.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
		this.handler = new HandlerAction(this);
		this.addKeyListener(new Keystrokes(handler));
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

		//TODO remove, just for debugging
		System.out.print("[");

		for(int i = 0; i < handler.done.size(); i++){
			System.out.print(handler.done.get(i).getClass().getName() + ", ");
		}
		System.out.println();
		
		
		if(handler.done.isEmpty())
			return;
		Action a = handler.done.pop();
		a.undoAction();
		handler.undone.add(a);
	}
	
	public void redo(){
		if(handler.undone.isEmpty())
			return;
		Action a = handler.undone.pop();
		a.redoAction();
		handler.done.add(a);
	}
	
}
