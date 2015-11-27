package ca.nicho.gui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

import ca.nicho.action.HandlerAction;
import ca.nicho.keyboard.Keystrokes;

public class Document extends JTextPane implements KeyListener, MouseListener{

	private HandlerAction handler;
	private ExtremeEditorGUI mainframe;
	
	public Document(ExtremeEditorGUI mainframe){		
		KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK);
		this.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
		this.setCaretPosition(this.getDocument().getLength());
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.mainframe = mainframe;
	}
	
	public void printText(){
		System.out.println(this.getText());
	}
	
	public void remove(int pos, int length){
		try {
			this.getDocument().remove(pos, length);
		} catch (BadLocationException e) {
			System.out.println(e.getMessage() + ": " + pos + " " + length);
		}
	}
	
	public void insertString(String text){
		insertString(this.getCaretPosition(), text);
	}
	
	public void insertString(int pos, String text){
		try {
			this.getDocument().insertString(pos, text, null);
		} catch (BadLocationException e) {
			System.out.println(e.getMessage() + ": " + pos + " " + text);
		}
	}
	
	public void setHandler(HandlerAction handler){
		this.handler = handler;
		this.addKeyListener(new Keystrokes(handler));
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	private int lastCaretPos;

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
			handler.caretMove = true;
		}
		lastCaretPos = this.getCaretPosition();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(lastCaretPos != this.getCaretPosition())
			handler.caretMove = true;
		lastCaretPos = this.getCaretPosition();
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
	
	public ExtremeEditorGUI getTopFrame(){
		return mainframe;
	}
	
}
