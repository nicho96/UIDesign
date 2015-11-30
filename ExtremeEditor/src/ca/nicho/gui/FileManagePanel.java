package ca.nicho.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import ca.nicho.action.HandlerAction;

public class FileManagePanel extends JPanel implements ActionListener{
		
	private HandlerAction handler;
	
	private ActionHistoryFrame actions;
	
	private JButton open, save, delete;
	
	public Color idle = Color.BLUE;
	public Color pressed = Color.GREEN;
	
	public FileManagePanel(HandlerAction handler, ActionHistoryFrame actions){
		this.setLayout(new GridLayout());
		this.handler = handler;
		this.actions = actions;
		ImageIcon openIcon = new ImageIcon("icons/ic_folder_open_black_48dp.png");
		ImageIcon saveIcon = new ImageIcon("icons/ic_save_48px-128.png");
		ImageIcon deleteIcon = new ImageIcon("icons/ic_delete_black_48dp.png");
		
		Image img = openIcon.getImage();
		Image openImg = img.getScaledInstance( 28, 28,  java.awt.Image.SCALE_SMOOTH );
		openIcon.setImage(openImg);
		open = new JButton(openIcon);
		open.setIcon(openIcon);
		open.addActionListener(this);
		open.setToolTipText("Open Ctrl-O");
		open.setBorder(BorderFactory.createEtchedBorder());
		open.setContentAreaFilled(false);
		
		open.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        open.setBackground(idle);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	open.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	open.setBackground(idle);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        open.setBackground(UIManager.getColor("control"));
		    }
		});
		
		
		img = saveIcon.getImage();
		Image saveImg = img.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		saveIcon = new ImageIcon(saveImg);
		save = new JButton(saveIcon);
		save.setContentAreaFilled(false);
		save.addActionListener(this);
		save.setBorder(BorderFactory.createEtchedBorder());
		save.setToolTipText("Save Ctrl-S") ;
		
		save.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        save.setBackground(idle);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	save.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	save.setBackground(idle);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        save.setBackground(UIManager.getColor("control"));
		    }
		});

		Image deleteImg = deleteIcon.getImage();
		deleteImg = deleteImg.getScaledInstance(28, 28, java.awt.Image.SCALE_SMOOTH);
		deleteIcon.setImage(deleteImg);
		delete = new JButton(deleteIcon);
		delete.setBorder(BorderFactory.createEtchedBorder());
		delete.setContentAreaFilled(false);
		delete.setToolTipText("Delete");
		delete.addActionListener(this);
		
		delete.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        delete.setBackground(idle);
		    }
		    public void mousePressed(java.awt.event.MouseEvent evt) {
		    	delete.setBackground(pressed);
		    }
		    public void mouseReleased(java.awt.event.MouseEvent evt) {
		    	delete.setBackground(idle);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		        delete.setBackground(UIManager.getColor("control"));
		    }
		});
		
		this.setBackground(new Color(203,203,203));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.add(open);
		this.add(save);
		this.add(delete);
		this.setPreferredSize(new Dimension(105,40));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	if(e.getSource().equals(open)){
		load();
	}else if(e.getSource().equals(save)){
		save();
	}else if(e.getSource().equals(delete)){
		//handler.getParent().delete();	
	}
		
	}
	
	public String save(){
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.showSaveDialog(this);
			File f = chooser.getSelectedFile();
			
			if(f != null){
				PrintWriter writer = new PrintWriter(f);
				writer.print(handler.getParent().getText());
				int i = JOptionPane.showConfirmDialog(this, "Would you like to save your undo/redo history?");
				
				if(i == JOptionPane.YES_OPTION){
					handler.saveHistoryToFile(new File(f.getAbsolutePath() + ".hist"));
				}
				writer.close();
				return f.getAbsolutePath();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void load(){
		try {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(this);
			File f = chooser.getSelectedFile();
			
			if(f != null){
				Scanner sc = new Scanner(f);
				sc.useDelimiter("/z");
				//If file is empty, prevents an EOF error
				if(sc.hasNext())
					handler.getParent().setText(sc.next());
				sc.close();
				
				File hist = new File(f.getAbsolutePath() + ".hist");
				
				if(f.exists()){
					handler.loadHistoryFile(hist);
				}
				
			}
		} catch (FileNotFoundException e) {
			//Do nothing
		}
	}
	
}
