package ca.nicho.gui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu{

	private JMenuItem open, save;
	
	public FileMenu(){
		this.setText("File");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		this.add(open);
		this.add(save);
	}
	
}
