package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JButton;

import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class DButton extends JButton {
	class RolloverIcon implements Icon {
		@Override
		public int getIconHeight() {
			return getHeight()-1;
		}
		@Override
		public int getIconWidth() {
			return getWidth()-1;
		}
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			g.setColor(Color.white);
			g.drawLine(x+0, y+5, x+5, y+0);
			g.drawLine(0, 5, 5, 0);
			System.out.println(x + "," + y);
		}
	}
	
	
	public final String text;
	public final Font font;
	private RolloverIcon rolloverIcon = new RolloverIcon();
		
	public DButton(int width, int height, String _text) {
		text = _text;
		font = new Font(Font.DIALOG,Font.PLAIN,12);
		setFont(font);
		setPreferredSize(new Dimension(width,height));
		//setRolloverEnabled(false);
		setRolloverEnabled(true);
		setRolloverIcon(rolloverIcon);
		//setOpaque(false);
	}
	public void paint(Graphics g) {
		Graphics2D gc = (Graphics2D) g;
		// for antialiasing text
		gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gc.setRenderingHint( RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

		Dimension d = getSize();
		//System.out.println(d.width + "x" + d.height);
		//System.out.println(d.width + "," + d.height);
		int w  = d.width - 1;
		int h  = d.height - 1;
		float brightness = (float) Settings.brightness.getDouble();
		gc.setColor(new Color(brightness,brightness,brightness));
//		if (this.isSelected()) {
			gc.drawRect(0, 0, w, h);
//		}
//		else {
//			gc.drawPolygon(new int[] {0,0,3}, new int[] {3,0,0},3);
//			gc.drawPolygon(new int[] {0,0,3}, new int[] {h-3,h,h},3);
//			gc.drawPolygon(new int[] {w-3,w,w}, new int[] {h,h,h-3},3);
//			gc.drawPolygon(new int[] {w,w,w-3}, new int[] {3,0,0},3);
//			
//		}
		//gc.setFont(font);
		FontMetrics fm = gc.getFontMetrics();
		Rectangle2D sb = fm.getStringBounds(text, gc);
		//System.out.println(d.height);
		//System.out.println(sb.getHeight());
		double x = (d.width - sb.getWidth())/2;
		double y = (d.height+ sb.getHeight())/2 - fm.getDescent();
		gc.drawString(text, (int)Math.round(x), (int)Math.round(y));
	}
}
