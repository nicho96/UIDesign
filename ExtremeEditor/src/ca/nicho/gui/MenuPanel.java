package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import ca.nicho.action.HandlerAction;
import ca.nicho.gui.menu.EditMenu;
import ca.nicho.gui.menu.FileMenu;

public class MenuPanel extends JPanel{

	private JMenuBar topBar;
	private FileMenu fileMenu;
	private EditMenu editMenu;
	private FontPanel fontPanel;
			
	public MenuPanel(HandlerAction handler, FontPanel fontPanel){
		
		this.fontPanel = fontPanel;
		
		this.setLayout(new BorderLayout());
		
		this.topBar = new JMenuBar();
		
		this.fileMenu = new FileMenu(fontPanel);
		this.editMenu = new EditMenu(handler);
		
		this.topBar.add(fileMenu);
		this.topBar.add(editMenu);
		
		this.add(topBar, BorderLayout.CENTER); 
		
	}
	
}
