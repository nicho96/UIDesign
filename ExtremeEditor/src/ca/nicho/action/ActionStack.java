package ca.nicho.action;

public class ActionStack {

	private ActionNode head;
	private int size;
	
	public ActionStack(){
		head = new ActionNode(new ActionType(1, null));
		size = 0;
	}
	
	public void addAction(Action a){
		//Do checks if it is a retro-reversible action
		head.addAction(a);
		size++;
	}
	
	public Action peek(){
		return head.getActionStack().peek();
	}
	
	public Action pop(){
		size --;
		return head.popAction();
	}
	
	public int size(){
		return size;
	}
	
	public Action getActionAt(int i){
		if(i >= size)
			return null;
		
		int current = i;
		ActionNode tmp = head;
		boolean loop = true;
		while(loop){
			if(tmp == null)
				return null;
			if(current - tmp.getActionStack().size() <= 0){
				loop = false;
			}else{
				current -= tmp.getActionStack().size();
				tmp = tmp.getLink();
			}
		}
		
		return tmp.getActionStack().get(current);
		
	}
	
	public Action removeAction(int i){
		if(i >= size)
			return null;
		
		int current = i;
		ActionNode tmp = head;
		boolean loop = true;
		while(loop){
			if(tmp == null)
				return null;
			if(current - tmp.getActionStack().size() <= 0){
				loop = false;
			}else{
				current -= tmp.getActionStack().size();
				tmp = tmp.getLink();
			}
		}
		size --;
		return tmp.getActionStack().remove(current);
	}
	
}
