package ca.nicho.gui.menu;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

import ca.nicho.action.HandlerAction;
import ca.nicho.gui.ExtremeEditorGUI;
import ca.nicho.gui.font.FontBuilder;

public class EditMenu extends JMenu implements ActionListener{
	
	private HandlerAction handler;
	
	private JMenuItem copy, paste, undo, redo;
	
	public EditMenu(HandlerAction handler){
		this.handler = handler;
		this.setText("Edit");		
		this.copy = new JMenuItem("Copy");
		this.copy.addActionListener(this);
		this.copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		
		this.paste = new JMenuItem("Paste");
		this.paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		this.paste.addActionListener(this);
		
		this.undo = new JMenuItem("Undo");
		this.undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		this.undo.addActionListener(this);
		
		this.redo = new JMenuItem("Redo");
		this.redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		this.redo.addActionListener(this);
		
		this.add(copy);
		this.add(paste);
		this.add(undo);
		this.add(redo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src.equals(copy)){
			StringSelection selection = new StringSelection(handler.getParent().getSelectedText());
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
		    
		}else if(src.equals(paste)){
				try {
					String s = (String) Toolkit.getDefaultToolkit().getSystemSelection().getData(DataFlavor.stringFlavor);
					handler.getParent().insertString(s);
					System.out.println(s);
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedFlavorException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}else if(src.equals(undo)){
			handler.undo();
		}else if(src.equals(redo)){
			handler.redo();
		}
	}

}
