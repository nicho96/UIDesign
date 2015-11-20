package ca.nicho.action;

import java.util.ArrayList;
import java.util.Stack;

public class ActionStack {

	private Stack<Action> stack;
	
	public ActionStack(){
		stack = new Stack<Action>();
	}
	
	public void addAction(Action a){
		stack.add(a);
	}
	
	public Action peek(){
		return stack.peek();
	}
	
	public Action pop(){
		return stack.pop();
	}
	
	public int size(){
		return stack.size();
	}
	
	public Action getActionAt(int i){
		return stack.get(i);
	}
	
	public Action removeAction(int i){
		return stack.remove(i);
	}
	
	public int removeAction(Action a){
		int i = stack.indexOf(a);
		stack.remove(a);
		return i;
	}
	
	public int getIndex(Action a){
		return stack.indexOf(a);
	}

	public ArrayList<Integer> getNodeActions(int index){
		ArrayList<Integer> vals = new ArrayList<Integer>();
		for(int i = index; i >= 0; i--){
			if(!getActionAt(i).canChangeBelow())
				vals.add(i);
		}
		return vals;
	}
	
}
