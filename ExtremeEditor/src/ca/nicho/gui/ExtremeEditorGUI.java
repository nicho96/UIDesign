package ca.nicho.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
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
	private ActionHistoryFrame historyDialog;
	private HistoryPanel historyPanel;
	private HandlerAction handler;
	
	private JScrollPane scroll;
	
	public Document textArea;
	
	public ExtremeEditorGUI(){
		
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Extreme Editor Plus");

		this.textArea = new Document();
		this.historyPanel = new HistoryPanel();
		this.historyDialog = new ActionHistoryFrame(this, historyPanel);
		handler = new HandlerAction(textArea, historyDialog);
		textArea.setHandler(handler);
		historyPanel.setHandler(handler);
		System.out.println(historyDialog);
		this.headerPanel = new JPanel();
		this.headerPanel.setLayout(new BorderLayout());
		this.menuPanel = new MenuPanel(handler);
		this.fontPanel = new FontPanel(handler, historyDialog);

		
		this.headerPanel.add(menuPanel, BorderLayout.PAGE_START);
		this.headerPanel.add(fontPanel, BorderLayout.PAGE_END);

		this.editorPanel = new EditorPanel(textArea);
		this.setPreferredSize(new Dimension(300, 600));
		
		this.infoPanel = new InfoPanel();

						
		this.add(headerPanel, BorderLayout.PAGE_START);
		this.add(scroll = new JScrollPane(editorPanel), BorderLayout.CENTER);
		TextLineNumber lineNum = new TextLineNumber(textArea);
		this.scroll.setRowHeaderView(lineNum);
		
		this.add(infoPanel, BorderLayout.PAGE_END);
		
		
	}
	
}
