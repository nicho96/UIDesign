package ca.nicho.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ca.nicho.action.HandlerAction;

public class CompilePanel extends JPanel implements ActionListener{
	
	private JButton compile;
	private HandlerAction handler;
	private FileManagePanel fmp;
	
	public Color idle = Color.RED;
	public Color pressed = Color.GREEN;
	
	public CompilePanel(HandlerAction handler, FileManagePanel fmp){
		this.handler = handler;
		this.fmp = fmp;
		this.setLayout(new GridLayout(1, 1));
		this.setBackground(new Color(203,203,203));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.setPreferredSize(new Dimension(37,40));

		ImageIcon compileIcon = new ImageIcon("res/compile.png");
		Image cutImg = compileIcon.getImage();
		cutImg = cutImg.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		compileIcon.setImage(cutImg);
		compile = new JButton(compileIcon);
		compile.setBorder(BorderFactory.createEtchedBorder());
		compile.setContentAreaFilled(false);
		compile.setToolTipText("Compile");
		compile.addActionListener(this);
		
		compile.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        compile.setBackground(idle);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	compile.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	compile.setBackground(idle);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        compile.setBackground(UIManager.getColor("control"));
		    }
		});

		
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
