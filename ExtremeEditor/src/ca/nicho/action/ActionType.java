package ca.nicho.action;


public class ActionType extends Action{

	private String value;
	
	public ActionType(int pos, HandlerAction handler){
		super(pos, 0, true, handler, "Insertion");
		value = "";
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
	
	public String toString() {
		String s = name + " (" + value.length() + " inserted): " + value + " " + pos;
		return s;
	}

	
}
