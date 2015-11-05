package ca.nicho.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.BadLocationException;

import ca.nicho.action.Action;
import ca.nicho.action.ActionDelete;
import ca.nicho.action.ActionType;
import ca.nicho.action.HandlerAction;
import ca.nicho.gui.Document;
import ca.nicho.gui.ExtremeEditorGUI;

public class Keystrokes implements KeyListener{

	private HandlerAction handler;
	
	private String lastString = "";
	private ActionType insert;
	private ActionDelete delete;
	
	private boolean backspace;
	
	public Keystrokes(HandlerAction handler){
		this.handler = handler;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		int pos = handler.getParent().getCaretPosition();
		
		if(backspace && (int)e.getKeyChar() != 8){
			backspace = false;
			delete = null;
		}
		
		//Create delete action
		if((int)e.getKeyChar() == 8){
			insert = null;
			Document doc = handler.getParent();
			
			String text = "";
			try{
				
				if(doc.getSelectedText() != null){
					text = doc.getSelectedText();
					doc.getDocument().remove(Math.min(doc.getSelectionStart(), doc.getSelectionEnd()), text.length());
					handler.done.add(new ActionDelete(pos, handler, text));
				}else{
					if(pos > 0){
						pos -= 1;
						text = doc.getDocument().getText(pos, 1);
						doc.getDocument().remove(pos, 1);
						if(!backspace){
							backspace = true;
							delete = new ActionDelete(pos, handler, text);
							handler.done.add(delete);
						}else{
							delete.addText(text);
							delete.setPos(pos);
						}
					}
				}
				
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		else{
			
			if(insert == null){
				if(e.getKeyChar() == '\n')
					insert = new ActionType(pos - 1, handler);
				else
					insert = new ActionType(pos, handler);
				handler.done.add(insert);
				lastString = "";
			}
			
			lastString += e.getKeyChar();
			insert.setString(lastString);
	
			if(e.getKeyChar() == ' ' || e.getKeyChar() == '\n'){
				insert = null;
			}
		}		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}