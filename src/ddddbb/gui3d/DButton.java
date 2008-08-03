package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class DButton extends JButton {
	public final String text;
	public final Font font;
		
	public DButton(String _text) {
		text = _text;
		font = new Font(Font.DIALOG,Font.PLAIN,12);
		setPreferredSize(new Dimension(50,16));
	}
	public void paint(Graphics gc) {
		Dimension d = getSize();
		//System.out.println(d.width + "x" + d.height);
		//System.out.println(d.width + "," + d.height);
		int w  = d.width - 1;
		int h  = d.height - 1;
		float brightness = (float) Settings.brightness.getDouble();
		gc.setColor(new Color(brightness,brightness,brightness));
		gc.drawLine(0, 0, 0, h);
		gc.drawLine(0, h, w, h);
		gc.drawLine(w, h, w, 0);
		gc.drawLine(w, 0, 0, 0);
		gc.setFont(font);
		FontMetrics fm = gc.getFontMetrics();
		Rectangle2D sb = fm.getStringBounds(text, gc);
		//System.out.println(d.height);
		//System.out.println(sb.getHeight());
		double x = (d.width - sb.getWidth())/2;
		double y = (d.height+ sb.getHeight())/2 - fm.getDescent();
		gc.drawString(text, (int)Math.round(x), (int)Math.round(y));
	}
}
