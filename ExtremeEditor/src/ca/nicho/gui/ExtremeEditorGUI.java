package ca.nicho.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ca.nicho.action.HandlerAction;
import ca.nicho.gui.components.TextLineNumber;

public class ExtremeEditorGUI extends JFrame{

	private EditorPanel editorPanel;
	
	private JPanel headerPanel;
	private MenuPanel menuPanel;
	private FontPanel fontPanel;
	private InfoPanel infoPanel;
	
	private JScrollPane scroll;
	
	public Document textArea = new Document();
	
	public ExtremeEditorGUI(){
		
		this.setBounds(10, 10, 900, 600);
		this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Extreme Editor Plus");

		this.headerPanel = new JPanel();
		this.headerPanel.setLayout(new BorderLayout());
		this.menuPanel = new MenuPanel(textArea.handler);
		this.fontPanel = new FontPanel(textArea.handler);
		
		this.headerPanel.add(menuPanel, BorderLayout.PAGE_START);
		this.headerPanel.add(fontPanel, BorderLayout.PAGE_END);

		this.editorPanel = new EditorPanel(textArea);
		
		this.infoPanel = new InfoPanel();
		
		this.add(headerPanel, BorderLayout.PAGE_START);
		this.add(scroll = new JScrollPane(editorPanel), BorderLayout.CENTER);
		this.scroll.setRowHeaderView(new TextLineNumber(textArea));
		this.add(infoPanel, BorderLayout.PAGE_END);
	}
	
}
