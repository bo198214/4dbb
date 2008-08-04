package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JToggleButton;

import ddddbb.game.Settings;

public class DToggleButton extends JToggleButton {
	public DToggleButton(int width, int height, String text) {
		setPreferredSize(new Dimension(width,height));
		setText(text);
		//setRolloverIcon(rolloverIcon);
	}
	public void paint(Graphics g) {
		Graphics2D gc = (Graphics2D) g;
		// for antialiasing text
		gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gc.setRenderingHint( RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

		//System.out.println(d.width + "x" + d.height);
		//System.out.println(d.width + "," + d.height);
		int w  = getWidth() - 1;
		int h  = getHeight() - 1;
		float brightness = (float) Settings.brightness.getDouble();
		gc.setColor(new Color(brightness,brightness,brightness));
		if (this.isSelected()) {
			gc.drawRect(0, 0, w, h);
		}
		else {
			gc.drawPolyline(new int[] {0,0,3}, new int[] {3,0,0},3);
			gc.drawPolyline(new int[] {0,0,3}, new int[] {h-3,h,h},3);
			gc.drawPolyline(new int[] {w-3,w,w}, new int[] {h,h,h-3},3);
			gc.drawPolyline(new int[] {w,w,w-3}, new int[] {3,0,0},3);
			
		}
		//gc.setFont(font);
		FontMetrics fm = gc.getFontMetrics();
		Rectangle2D sb = fm.getStringBounds(getText(), gc);
		//System.out.println(d.height);
		//System.out.println(sb.getHeight());
		double x = (getWidth() - sb.getWidth())/2;
		double y = (getHeight()+ sb.getHeight())/2 - fm.getDescent();
		gc.drawString(getText(), (int)Math.round(x), (int)Math.round(y));
	}

}
