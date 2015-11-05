package ca.nicho.action;

import javax.swing.text.BadLocationException;

import ca.nicho.gui.ExtremeEditorGUI;

public class ActionType extends Action{

	private String value;
	
	public ActionType(int pos, HandlerAction handler){
		super(pos, 0, true, handler);
	}

	public void setString(String txt){
		this.value = txt;
		this.length = value.length();
	}
	
	@Override
	public void undoAction() {
		handler.getParent().remove(pos, length);
	}
	
	@Override
	public void redoAction() {
		handler.getParent().insertString(pos, value);
	}
	
}
