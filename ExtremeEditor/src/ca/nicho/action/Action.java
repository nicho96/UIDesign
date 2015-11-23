package ca.nicho.action;


public abstract class Action {

	protected int pos, length;
	protected HandlerAction handler;
	private boolean stackChange;
	protected String value;
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
	
	public void setCanChange(boolean b){
		this.stackChange = b;
	}
	
	public String getValue(){
		return value;
	}
	
	public abstract Action copy();
	
	public abstract void undoAction();
	
	public abstract void redoAction();
		
}
