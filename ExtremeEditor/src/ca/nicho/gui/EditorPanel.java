package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import ca.nicho.gui.components.TextLineNumber;
import ca.nicho.gui.font.FontBuilder;
 

public class EditorPanel extends JPanel{
		
	public EditorPanel(JTextPane area){
		
		this.setLayout(new BorderLayout());
		Color editorPanel = new Color(239,253,255);
		area.setBackground(editorPanel);
		this.add(area);
			
	}
	
}
