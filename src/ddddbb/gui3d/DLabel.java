package ddddbb.gui3d;

import java.awt.Color;

import javax.swing.JLabel;

import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class DLabel extends JLabel {
	public DLabel(int width, int height) {
		setSize(width,height);
		
		float brightness = (float)Settings.brightness.getDouble();
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
