package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import ddddbb.game.Main;
import ddddbb.gen.DoubleModel;

@SuppressWarnings("serial")
public class DLabel extends JLabel {
	public DLabel(int width, int height, DoubleModel _brightness) {
		setSize(width,height);
		
		float brightness = (float)_brightness.getDouble();
		setForeground(new Color(brightness,brightness,brightness));
	}

//	@Override
//	public void paint(Graphics g) {
//		int x = super.getHorizontalTextPosition();
//		int y = super.getVerticalTextPosition();
//		setForeground(Color.WHITE);
//		System.out.println(getText() + "," + x + "," + y);
//		g.drawString(getText(), x, y);
//	}
}
