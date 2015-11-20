package ca.nicho.action;

public class ActionDelete extends Action{

	private String value;
	
	public ActionDelete(int pos, HandlerAction handler, String value){
		super(pos, value.length(), false, handler, "Deletion");
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
	public int getLength(){
		return -length;
	}
	
	@Override
	public void undoAction() {
		handler.getParent().insertString(pos, value);
	}

	@Override
	public void redoAction() {
		handler.getParent().remove(pos, length);
	}

	public String toString() {
		String s = name + " (" + value.length() + " deleted): " + value + " " + pos;
		return s;
	}

	
	
}
