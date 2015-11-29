package ca.nicho.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import ca.nicho.action.HandlerAction;

public class CompilePanel extends JPanel implements ActionListener{
	
	private JButton compile;
	private HandlerAction handler;
	private FileManagePanel fmp;
	
	public CompilePanel(HandlerAction handler, FileManagePanel fmp){
		this.handler = handler;
		this.fmp = fmp;
		this.setLayout(new GridLayout(1, 1));
		compile = new JButton("C");
		compile.addActionListener(this);
		add(compile);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(compile)){
			compile(false);
		}
	}
	
	public void compile(boolean run){
		String path = fmp.save();
		if(path != null){
				try {
					Process p1 = Runtime.getRuntime().exec("javac " + path);
					p1.waitFor();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
