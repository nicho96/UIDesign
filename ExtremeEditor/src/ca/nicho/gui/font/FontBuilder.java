package ca.nicho.gui.font;

import java.awt.Color;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class FontBuilder {
	
	public static SimpleAttributeSet standard = getFont(Color.BLACK, Color.WHITE, false, false, 12, null);
	public static SimpleAttributeSet bold = getFont(Color.BLACK, Color.WHITE, true, false, 12, null);

	public static SimpleAttributeSet getFont(Color fore, Color back, boolean bold, boolean italics, int size, String family){
		
		
		SimpleAttributeSet font = new SimpleAttributeSet();
		StyleConstants.setForeground(font, fore);
		StyleConstants.setBackground(font, back);
		StyleConstants.setBold(font, bold);
		StyleConstants.setItalic(font, italics);
		StyleConstants.setFontSize(font, size);
		StyleConstants.setFontFamily(font, family);
		return font;
		
	}
	
}
