package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class DDisplay extends JLabel {
	public static final Font font = new Font(Font.DIALOG,Font.PLAIN,12);
	public DDisplay(int digits, boolean isFloat) {
		//setPreferredSize(new Dimension(width,height));
		//FontMetrics fm = getGraphics().getFontMetrics(font);
		//double numberWidth = fm.stringWidth("1");
		double numberWidth = 8;
		double commaWidth;
		if (isFloat) commaWidth=2;
		else commaWidth=0;
		int height = font.getSize();
		setPreferredSize(new Dimension(
				(int)Math.round(digits*numberWidth+commaWidth)+2,height+2));
		this.setHorizontalAlignment(CENTER);
		float brightness = (float)Settings.brightness.getDouble();
		setForeground(new Color(brightness,brightness,brightness));
		setFont(font);
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
