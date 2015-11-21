package ca.nicho.keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.BadLocationException;

import ca.nicho.action.ActionDelete;
import ca.nicho.action.ActionType;
import ca.nicho.action.HandlerAction;
import ca.nicho.gui.Document;

public class Keystrokes implements KeyListener{

	private HandlerAction handler;
	
	private boolean isCtrlPressed = false;
	private boolean isAltPressed = false;
	
	private String lastString = "";
	private ActionType insert;
	private ActionDelete delete;
	
	private boolean backspace;
	
	public Keystrokes(HandlerAction handler){
		this.handler = handler;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		if(isCtrlPressed || isAltPressed)
			return;
		
		if(handler.getDoneSize() == 0 || handler.wasModified){
			insert = null;
			handler.wasModified = false;
		}
		
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
					handler.addDoneAction(new ActionDelete(pos, handler, text));
				}else{
					if(pos > 0){
						pos -= 1;
						text = doc.getDocument().getText(pos, 1);
						doc.getDocument().remove(pos, 1);
						if(!backspace){
							backspace = true;
							delete = new ActionDelete(pos, handler, text);
							handler.addDoneAction(delete);
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
				handler.addDoneAction(insert);
				lastString = "";
			}
			
			lastString += e.getKeyChar();
			insert.setString(lastString);
	
			if(e.getKeyChar() == '\n' || e.getKeyChar() == ',' || e.getKeyChar() == '.'){
				insert = null;
			}
		}		
		handler.getFrame().getHistoryFrame().update();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL)
			isCtrlPressed = true;
		if(e.getKeyCode() == KeyEvent.VK_ALT)
			isAltPressed = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_CONTROL)
			isCtrlPressed = false;
		if(e.getKeyCode() == KeyEvent.VK_ALT)
			isAltPressed = false;
	}
	
}