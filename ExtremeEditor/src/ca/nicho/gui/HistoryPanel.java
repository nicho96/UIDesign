package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import ca.nicho.action.Action;
import ca.nicho.action.HandlerAction;
import ca.nicho.gui.components.CellRenderer;

public class HistoryPanel extends JPanel implements MouseListener, ActionListener{

	private DefaultListModel<Action> doneList;
	private DefaultListModel<Action> undoneList;
	private HandlerAction handler;
	private CellRenderer doneRenderer;
	private CellRenderer undoneRenderer;

	private JTabbedPane tab;
	private JPanel undoPanel;
	private JPanel redoPanel;
	private JList<Action> listDoneDisplay;
	private JList<Action> listUndoneDisplay;
	
	private JPanel buttonPanel;
	private JButton preview;
	private JButton apply;
	
	public HistoryPanel() {
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		
		tab = new JTabbedPane();
		
		doneList = new DefaultListModel<Action>();
		undoneList = new DefaultListModel<Action>();

		listDoneDisplay = new JList<Action>(doneList);
		listDoneDisplay.addMouseListener(this);
		
		listUndoneDisplay = new JList<Action>(undoneList);
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
		
		buttonPanel = new JPanel();
		preview = new JButton("Preview");
		preview.addActionListener(this);
		apply = new JButton("Perform Changes");
		apply.addActionListener(this);
		buttonPanel.add(preview);
		buttonPanel.add(apply);
		this.add(buttonPanel, BorderLayout.PAGE_END);
		
		listDoneDisplay.setCellRenderer(doneRenderer = new CellRenderer());
		
		listUndoneDisplay.setCellRenderer(undoneRenderer = new CellRenderer());


	}

	public void update() {
		
		/**
		System.out.print("Done: ");
		handler.done.debug();
		System.out.print("Undone: ");
		handler.undone.debug();
		*/
		
		doneList.clear();
		undoneList.clear();
		
		int doneLimit = -1;
		
		for (int i = handler.getDoneSize() - 1; i >= 0; i--) {
			doneList.addElement(handler.getDoneAction(i));
			if(!handler.getDoneAction(i).canChangeBelow() && doneLimit < 0)
				doneLimit = i;
		}
				
		for(int i = handler.getUndoneSize() - 1; i >= 0; i--){
			Action a = handler.getUndoneAction(i);
			undoneList.addElement(a);
		}
		
		doneRenderer.stopInd = doneList.size() - doneLimit - 1;
		undoneRenderer.stopInd = 0;
		
	}

	public void setHandler(HandlerAction handler) {
		this.handler = handler;
	}
	
	public int[] getDoneIndices(){
		return listDoneDisplay.getSelectedIndices();
	}
	
	public int[] getUnoneIndices(){
		return listUndoneDisplay.getSelectedIndices();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			if(e.getSource().equals(listDoneDisplay)){
				if(listDoneDisplay.getSelectedIndex() <= doneRenderer.stopInd){
					handler.undoAction(doneList.get(listDoneDisplay.getSelectedIndex()));
					update();
				}
			}else if(e.getSource().equals(listUndoneDisplay)){
				if(listUndoneDisplay.getSelectedIndex() <= undoneRenderer.stopInd){
					handler.redoAction(undoneList.get(listUndoneDisplay.getSelectedIndex()));
					update();
				}
			}
		}
	}
	
	public HandlerAction getHandler(){
		return handler;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src.equals(apply) || src.equals(preview)){
			if(undoPanel.isShowing()){
				if(listDoneDisplay.getSelectedIndices().length != 0){
					if(handler.canMultipleUndo(listDoneDisplay.getSelectedIndices())){
						if(src.equals(preview)){
							handler.previewDone(doneList, listDoneDisplay.getSelectedIndices());
						}else
							handler.bulkUndo(doneList, listDoneDisplay.getSelectedIndices());
					}else{
						JOptionPane.showMessageDialog(this, "Error: Deletions above any other modifications must be selected to perform this action.");
					}
				}
			}else{
				if(listUndoneDisplay.getSelectedIndices().length != 0){
					if(handler.canMultipleRedo(listUndoneDisplay.getSelectedIndices())){
						if(src.equals(preview)){
							handler.previewUndone(undoneList, listUndoneDisplay.getSelectedIndices());
						}else
							handler.bulkRedo(undoneList, listUndoneDisplay.getSelectedIndices());
					}else{
						JOptionPane.showMessageDialog(this, "Error: Undo actions must be done in order as they are listed.");
					}
				}
			}
		}
		update();
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
