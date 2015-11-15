package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ca.nicho.gui.components.TextLineNumber;

public class ExtremeEditorGUI extends JFrame{

	private EditorPanel editorPanel;
	
	private JPanel headerPanel;
	private MenuPanel menuPanel;
	private FontPanel fontPanel;
	private InfoPanel infoPanel;
	private ActionHistoryFrame historyDialog;
	private HistoryPanel historyPanel;
	
	private JScrollPane scroll;
	
	public Document textArea;
	
	public ExtremeEditorGUI(){
		
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Extreme Editor Plus");

		this.textArea = new Document(historyDialog);
		this.headerPanel = new JPanel();
		this.headerPanel.setLayout(new BorderLayout());
		this.menuPanel = new MenuPanel(textArea.handler);
		this.historyPanel = new HistoryPanel(textArea.handler);
		this.historyDialog = new ActionHistoryFrame(this, historyPanel);
		this.fontPanel = new FontPanel(textArea.handler, historyDialog);

		
		this.headerPanel.add(menuPanel, BorderLayout.PAGE_START);
		this.headerPanel.add(fontPanel, BorderLayout.PAGE_END);

		this.editorPanel = new EditorPanel(textArea);
		this.setPreferredSize(new Dimension(300, 600));
		
		this.infoPanel = new InfoPanel();

						
		this.add(headerPanel, BorderLayout.PAGE_START);
		this.add(scroll = new JScrollPane(editorPanel), BorderLayout.CENTER);
		this.scroll.setRowHeaderView(new TextLineNumber(textArea));
		this.add(infoPanel, BorderLayout.PAGE_END);
		
		
	}
	
}
