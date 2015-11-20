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
	
	public void undoAction(int index){
		Action a = removeDoneAction(index);
		a.undoAction();
		for(int i = index; i < done.size(); i++){
			Action tmp = getDoneAction(i);
			tmp.setPos(tmp.getPos() - a.getLength());
		}
	}
	
	public Action popUndone(){
		return undone.pop();
	}
}
