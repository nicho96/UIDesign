package ca.nicho.action;

import java.util.Stack;

public class ActionNode {

	private ActionNode link;
	private Stack<Action> actions;
	
	public ActionNode(Action superElement){
		actions = new Stack<Action>();
	}
	
	public void setLink(ActionNode node){
		link = node;
	}
	
	public ActionNode getLink(){
		return link;
	}
	
	public Stack<Action> getActionStack(){
		return actions;
	}
	
	public void addAction(Action a){
		actions.add(a);
	}
	
	public Action popAction(){
		return actions.pop();
	}
	
}
