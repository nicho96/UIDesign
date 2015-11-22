package ca.nicho.action;

import java.util.ArrayList;

import ca.nicho.gui.ActionHistoryFrame;
import ca.nicho.gui.Document;

public class HandlerAction {

	private ActionStack done = new ActionStack();
	private ActionStack undone = new ActionStack();
	
	private ActionHistoryFrame frame;
	private Document parent;
	
	public boolean wasModified = false;
	
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
	
	public void undo(){
		undoAction(done.peek());
		frame.getHistoryFrame().update();
		
	}
	
	public void redo(){
		redoAction(undone.peek());
		frame.getHistoryFrame().update();
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
	
	public void redoAction(Action a){
		int index = undone.removeAction(a);
		wasModified = true;
		a.redoAction();
		for(int i = 0; i < undone.size(); i++){
			Action tmp = undone.getActionAt(i);
			if(a.getPos() < tmp.getPos())
				tmp.setPos(tmp.getPos() - a.getLength());
		}
		for(int i = 0; i < done.size(); i++){
			Action tmp = done.getActionAt(i);
			if(a.getPos() <= tmp.getPos())
				tmp.setPos(tmp.getPos() + a.getLength());
		}
		
		done.addAction(a);
	}
	
	public void undoAction(Action a){
		int index = done.removeAction(a);
		wasModified = true;
		a.undoAction();
		for(int i = 0; i < done.size(); i++){
			Action tmp = done.getActionAt(i);
			if(a.getPos() < tmp.getPos())
				tmp.setPos(tmp.getPos() - a.getLength());
		}
		for(int i = 0; i < undone.size(); i++){
			Action tmp = undone.getActionAt(i);
			if(a.getPos() <= tmp.getPos())
				tmp.setPos(tmp.getPos() + a.getLength());
		}
		undone.addAction(a);
	}
	
	//Seriously fuck this method
	public boolean canMultipleUndo(int[] indices){
		for(int start= done.size() - indices[0] - 1; start < done.size(); start++){
			Action a = this.getDoneAction(start);
			if(!a.canChangeBelow()){
				boolean b = false;
				for(int i : indices){
					if(i == done.size() - done.getIndex(a) - 1)
						b = true;
				}
				if(!b)
					return false;
			}
		}

		return true;
	}
	
	public boolean canMultipleRedo(int[] indices){
		for(int i = 0; i < indices.length; i++){
			if(indices[i] != i)
				return false;
		}
		return true;
	}
	
	public Action popUndone(){
		return undone.pop();
	}
}
