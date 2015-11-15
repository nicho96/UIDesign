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
	
	public abstract void undoAction();
	
	public abstract void redoAction();
	
	public abstract String getPreview();
	
}
