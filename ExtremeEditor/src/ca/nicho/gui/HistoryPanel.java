package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import ca.nicho.action.Action;
import ca.nicho.action.HandlerAction;
import ca.nicho.gui.components.CellRenderer;

public class HistoryPanel extends JPanel implements MouseListener {

	private DefaultListModel<String> doneList;
	private DefaultListModel<String> undoneList;
	private HandlerAction handler;
	private CellRenderer doneRenderer;
	private CellRenderer undoneRenderer;

	private JTabbedPane tab;
	private JPanel undoPanel;
	private JPanel redoPanel;
	private JList<String> listDoneDisplay;
	private JList<String> listUndoneDisplay;
	
	public HistoryPanel() {
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		tab = new JTabbedPane();
		
		doneList = new DefaultListModel<String>();
		undoneList = new DefaultListModel<String>();

		listDoneDisplay = new JList<String>(doneList);
		listDoneDisplay.addMouseListener(this);
		
		listUndoneDisplay = new JList<String>(undoneList);
		listUndoneDisplay.addMouseListener(this);
		
		undoPanel = new JPanel();
		undoPanel.setLayout(new BorderLayout());
		undoPanel.add(new JScrollPane(listDoneDisplay), BorderLayout.CENTER);
		
		redoPanel = new JPanel();
		redoPanel.setLayout(new BorderLayout());
		redoPanel.add(new JScrollPane(listUndoneDisplay), BorderLayout.CENTER);
		
		tab.addTab("Undo", undoPanel);
		tab.addTab("Redo", redoPanel);
		
		this.add(tab);
		
		listDoneDisplay.setCellRenderer(doneRenderer = new CellRenderer());
		
		listUndoneDisplay.setCellRenderer(undoneRenderer = new CellRenderer());


	}

	public void update() {
		
		doneList.clear();
		undoneList.clear();
		
		int doneLimit = -1;
		
		for (int i = handler.getDoneSize() - 1; i >= 0; i--) {
			doneList.addElement(handler.getDoneAction(i).getPreview());
			if(!handler.getDoneAction(i).canChangeBelow() && doneLimit < 0)
				doneLimit = i;
		}
		
		int undoneLimit = -1;
		
		for(int i = handler.getUndoneSize() - 1; i >= 0; i--){
			undoneList.addElement(handler.getUndoneAction(i).getPreview());
			if(!handler.getDoneAction(i).canChangeBelow() && doneLimit < 0)
				undoneLimit = i;
		}
		
		doneRenderer.stopInd = handler.getDoneSize() - doneLimit - 1;
		undoneRenderer.stopInd = handler.getUndoneSize() - undoneLimit - 1;
		
	}

	public void setHandler(HandlerAction handler) {
		this.handler = handler;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			for(int i : listDoneDisplay.getSelectedIndices()){
				Action a = handler.removeDoneAction(handler.getDoneSize() - i - 1);
				a.undoAction();
				handler.updateDoneActions(a);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public HandlerAction getHandler(){
		return handler;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
