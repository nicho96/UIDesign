package ca.nicho.action;

import ca.nicho.gui.ActionHistoryFrame;
import ca.nicho.gui.Document;

public class HandlerAction {

	private ActionStack done = new ActionStack();
	private ActionStack undone = new ActionStack();
	
	private ActionHistoryFrame frame;
	private Document parent;
	
	public HandlerAction(Document parent, ActionHistoryFrame frame){
		this.frame = frame;
		this.parent = parent;
	}
	
	public Document getParent(){
		return parent;
	}
	
	public void addDoneAction(Action a){
		done.addAction(a);
		frame.getHistoryFrame().update();
	}
	
	public void addUndoneAction(Action a){
		undone.addAction(a);
		frame.getHistoryFrame().update();
	}
	
	public Action removeDoneAction(int i){
		Action a = done.removeAction(i);
		frame.getHistoryFrame().update();
		return a;
	}
	
	public Action getDoneAction(int i){
		return done.getActionAt(i);
	}
	
	public Action getUndoneAction(int i){
		return undone.getActionAt(i);
	}
	
	public int getDoneSize(){
		return done.size();
	}
	
	public int getUndoneSize(){
		return undone.size();
	}
	
	public Action popDone(){
		return done.pop();
	}
	
	public ActionHistoryFrame getFrame(){
		return frame;
	}
	
	public void updateDoneActions(Action a){
	
		int index = 0;
		Action nextAction = done.getActionAt(index);

		while(nextAction != null){
			if(nextAction.getPos() >= a.getPos())
				nextAction.setPos(nextAction.getPos() - a.getLength());
			nextAction = done.getActionAt(++index);
		}
		
	}
	
	public Action popUndone(){
		return undone.pop();
	}
}
