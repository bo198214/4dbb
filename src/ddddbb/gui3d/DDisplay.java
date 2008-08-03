package ddddbb.gui3d;

import java.awt.Color;

import javax.swing.JLabel;

import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class DDisplay extends JLabel {
	public DDisplay(int width, int height) {
		//setPreferredSize(new Dimension(width,height));
		
		float brightness = (float)Settings.brightness.getDouble();
		setForeground(new Color(brightness,brightness,brightness));
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
