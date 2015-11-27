package ca.nicho.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ca.nicho.gui.FontPanel;

public class FileMenu extends JMenu implements ActionListener{

	private JMenuItem open, save;
	
	private FontPanel fontPanel;
	
	public FileMenu(FontPanel fontPanel){
		this.setText("File");
		open = new JMenuItem("Open");
		open.addActionListener(this);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		save = new JMenuItem("Save");
		save.addActionListener(this);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		this.add(open);
		this.add(save);
		this.fontPanel = fontPanel;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(open))
			fontPanel.getFilePanel().load();
		else if(e.getSource().equals(save)){
			fontPanel.getFilePanel().save();
		}
	}
	
}
