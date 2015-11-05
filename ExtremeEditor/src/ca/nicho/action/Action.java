package ca.nicho.action;


public abstract class Action {

	protected int pos, length;
	protected HandlerAction handler;
	private boolean stackChange;

	public Action(int pos, int length, boolean b, HandlerAction handler){
		this.pos = pos;
		this.length = length;
		this.stackChange = b;
		this.handler = handler;
	}
	
	public abstract void undoAction();
	
	public abstract void redoAction();
	
}
