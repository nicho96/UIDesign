package ca.nicho.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
		if(i < size() && i >= 0)
			return stack.get(i);
		return null;
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
	
	public boolean isEmpty(){
		return stack.isEmpty();
	}
	
	public ActionStack copy(){
		ActionStack copy = new ActionStack();
		
		for(int i = 0; i < stack.size(); i++)
			copy.addAction(stack.get(i).copy());
		
		return copy;
	}
	
}
