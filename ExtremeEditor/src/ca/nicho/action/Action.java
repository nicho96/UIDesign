package ca.nicho.action;


public abstract class Action {

	protected int pos, length;
	protected HandlerAction handler;
	private boolean stackChange;
	protected String name;

	public Action(int pos, int length, boolean b, HandlerAction handler, String name){
		this.pos = pos;
		this.length = length;
		this.stackChange = b;
		this.handler = handler;
		this.name = name;
	}
	
	public int getPos(){
		return pos;
	}
	
	public void setPos(int pos){
		this.pos = pos;
	}
	
	public int getLength(){
		return length;
	}
	
	public boolean canChangeBelow(){
		return stackChange;
	}
	
	public abstract void undoAction();
	
	public abstract void redoAction();
	
	public abstract String getPreview();
	
}
