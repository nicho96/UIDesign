package ca.nicho.action;

import javax.swing.text.BadLocationException;

import ca.nicho.gui.ExtremeEditorGUI;

public class ActionDelete extends Action{

	private String value;
	
	public ActionDelete(int pos, HandlerAction handler, String value){
		super(pos, value.length(), false, handler);
		this.value = value;
	}

	public void addText(String text){
		this.value = text + value;
		this.length += text.length();
	}
	
	public void setPos(int pos){
		this.pos = pos;
	}
	
	@Override
	public void undoAction() {
		handler.getParent().insertString(pos, value);
	}

	@Override
	public void redoAction() {
		handler.getParent().remove(pos, length);
	}

	
	
}
