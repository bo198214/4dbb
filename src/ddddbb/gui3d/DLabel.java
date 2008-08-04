package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class DLabel extends JLabel {
	public static final Font font = new Font(Font.DIALOG,Font.PLAIN,12); 
	public DLabel() {
		setHorizontalAlignment(CENTER);
		float brightness = (float)Settings.brightness.getDouble();
		setForeground(new Color(brightness,brightness,brightness));
		setFont(font);
		setOpaque(false);
	}
	public DLabel(int width, int height) {
		this();
		setPreferredSize(new Dimension(width,height));
	}
	
	public DLabel(String label) {
		this();
		setText(label);
	}
	public DLabel(int width, int height, String label) {
		this();
		setPreferredSize(new Dimension(width,height));
		setText(label);
	}
	public void setText(String label) {
		super.setText(label);
		setPreferredSize(new Dimension(label.length()*8+2,font.getSize()+2));		
	}

//	@Override
//	public void paint(Graphics gc) {
//		super.paint(gc);
//		Dimension d = getSize();
//		int w = d.width-1;
//		int h = d.height-1;
//		gc.drawLine(0, 0, 4, 0);
//		gc.drawLine(0, 0, 0, 4);
//		gc.drawLine(0, h, 0, h-4);
//		gc.drawLine(0, h, 4, h);
//		gc.drawLine(w, h, w-4, h);
//		gc.drawLine(w, h, w, h-4);
//		gc.drawLine(w, 0, w, 4);
//		gc.drawLine(w, 0, w-4, 0);
//	}

}
