package ca.nicho.action;

import java.util.Stack;

import ca.nicho.gui.Document;

public class HandlerAction {


	public Stack<Action> done = new Stack<Action>();
	public Stack<Action> undone = new Stack<Action>();
	
	private Document parent;
	
	public HandlerAction(Document parent){
		this.parent = parent;
	}
	
	public Document getParent(){
		return parent;
	}
	
}
