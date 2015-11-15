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

import ca.nicho.action.HandlerAction;

public class HistoryPanel extends JPanel implements MouseListener{

	private DefaultListModel<String> list;
	private HandlerAction handler;
	private DefaultListCellRenderer renderer;
	
	private JList<String> listDisplay;
	
	public HistoryPanel(){
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		list = new DefaultListModel<String>();
		
		listDisplay = new JList<String>(list);
		listDisplay.addMouseListener(this);
		this.add(new JScrollPane(listDisplay), BorderLayout.CENTER);
				
	}
	
	public void update(){
		list.clear();
		for(int i = handler.getDoneSize() - 1; i >= 0; i--){
			list.addElement(handler.getDoneAction(i).getPreview());
		}
		
		listDisplay.setCellRenderer(renderer = new DefaultListCellRenderer(){
		
			//Any cell after this index will be colored differently
			public int stopInd = 1;			
			
			  @Override
              public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                   Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                   if (value instanceof String) {
                        if(index > stopInd)
                        	setBackground(Color.decode("0xE0E0E0"));
                   } else {
                        setText("whodat?");
                   }
                   return c;
              }
			
		});
	}
	
	public void setHandler(HandlerAction handler){
		this.handler = handler;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2)
			System.out.println(true);
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
