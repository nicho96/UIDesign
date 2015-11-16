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

import ca.nicho.action.HandlerAction;

public class HistoryPanel extends JPanel implements MouseListener {

	private DefaultListModel<String> doneList;
	private DefaultListModel<String> undoneList;
	private HandlerAction handler;
	private DefaultListCellRenderer doneRenderer;
	private DefaultListCellRenderer undoneRenderer;

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
		
		listDoneDisplay.setCellRenderer(doneRenderer = new DefaultListCellRenderer() {

			// Any cell after this index will be colored differently
			public int stopInd = 1;
			Color grey = Color.decode("0xE0E0E0"); // Hex for the color grey

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (index > stopInd)
					setBackground(grey);
				return c;
			}

		});
		
		listUndoneDisplay.setCellRenderer(undoneRenderer = new DefaultListCellRenderer() {

			// Any cell after this index will be colored differently
			public int stopInd = 1;
			Color grey = Color.decode("0xE0E0E0"); // Hex for the color grey

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (index > stopInd)
					setBackground(grey);
				return c;
			}

		});


	}

	public void update() {
		
		doneList.clear();
		undoneList.clear();
		
		for (int i = handler.getDoneSize() - 1; i >= 0; i--) {
			doneList.addElement(handler.getDoneAction(i).getPreview());
		}
		
		for(int i = handler.getUndoneSize() - 1; i >= 0; i--){
			undoneList.addElement(handler.getUndoneAction(i).getPreview());
		}

	}

	public void setHandler(HandlerAction handler) {
		this.handler = handler;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			for(int i : listDoneDisplay.getSelectedIndices()){
				handler.removeDoneAction(i).undoAction();
			}
		}
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
