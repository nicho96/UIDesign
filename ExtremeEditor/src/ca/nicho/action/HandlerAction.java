package ca.nicho.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import ca.nicho.gui.ActionHistoryFrame;
import ca.nicho.gui.Document;
import ca.nicho.gui.PreviewPanel;

public class HandlerAction {

	public ActionStack done = new ActionStack();
	public ActionStack undone = new ActionStack();
	
	private ActionHistoryFrame frame;
	private Document parent;
	
	public boolean wasModified = false;
	public boolean caretMove = false;
	
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
	
	public void updateDone(int shift){
		Action a = done.peek();
		for(int i = 0; i < done.size(); i++){
			Action tmp = done.getActionAt(i);
			if(a.getPos() < tmp.getPos()){
				tmp.setPos(tmp.getPos() + shift);
			}
		}
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
		if(!done.isEmpty()){
			undoAction(done.peek());
			frame.getHistoryFrame().update();
		}
	}
	
	public void redo(){
		if(!undone.isEmpty()){
			redoAction(undone.peek());
			frame.getHistoryFrame().update();
		}
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
	
	public void bulkUndo(DefaultListModel<Action> doneList, JList<Action> listDoneDisplay){
		for(int i : listDoneDisplay.getSelectedIndices()){
			this.undoAction(doneList.getElementAt(i));
		}
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
	
	public void preview(DefaultListModel<Action> doneList, JList<Action> listDoneDisplay){
		Document tmp = parent;
		ActionStack tmpDone = done.copy();
		ActionStack tmpUndone = undone.copy();
		parent = new PreviewPanel(tmp, this, listDoneDisplay, doneList).getTextArea();
		bulkUndo(doneList, listDoneDisplay);
		done = tmpDone;
		undone = tmpUndone;
		parent = tmp;
		frame.getHistoryFrame().update();
	}
	
	public void saveHistoryToFile(String pathName){
		try {
			File f = new File(pathName + ".hist");
			if(!f.exists())
				f.createNewFile();
			PrintWriter write = new PrintWriter(f);
			
			write.println("DONE");
			
			for(int i = 0; i < done.size(); i++){
				Action a = done.getActionAt(i);
				write.println(a.getClass().getName() + ":" + a.getPos() + ":" + a.getValue());
			}
			
			write.println("UNDONE");
			
			for(int i = 0; i < undone.size(); i++){
				Action a = undone.getActionAt(i);
				write.println(a.getClass().getName() + ":" + a.getPos() + ":" + a.getValue());
			}
			
			write.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
