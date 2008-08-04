package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JButton;

import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class DButton extends JButton {
	static class RolloverIcon implements Icon {
		Component c;
		RolloverIcon(Component c) {
			this.c = c;
		}
		@Override
		public int getIconHeight() {
			return c.getHeight()-1;
		}
		@Override
		public int getIconWidth() {
			return c.getWidth()-1;
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
	private final RolloverIcon rolloverIcon;
	private boolean mouseInside = false;
		
	public DButton(int width, int height, String _text) {
		text = _text;
		font = new Font(Font.DIALOG,Font.PLAIN,12);
		setFont(font);
		setPreferredSize(new Dimension(width,height));
		//setRolloverEnabled(false);
		setRolloverEnabled(true);
		rolloverIcon = new RolloverIcon(this);
		//setRolloverIcon(rolloverIcon);
		//setOpaque(false);
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				mouseInside = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mouseInside = false;
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}
		});
	}
	public void paint(Graphics g) {
		Graphics2D gc = (Graphics2D) g;
		// for antialiasing text
		gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		gc.setRenderingHint( RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

		Dimension d = getSize();
		//System.out.println(d.width + "x" + d.height);
		//System.out.println(d.width + "," + d.height);
		int w  = d.width - 1;
		int h  = d.height - 1;
		float brightness = (float) Settings.brightness.getDouble();
		gc.setColor(Color.black);
		gc.fillRect(0,0,getWidth(),getHeight());
		
		gc.setColor(new Color(brightness,brightness,brightness));
		
		if (mouseInside) gc.drawRoundRect(0, 0, w, h, 8, 8);
		else gc.drawRect(0, 0, w, h);
		FontMetrics fm = gc.getFontMetrics();
		Rectangle2D sb = fm.getStringBounds(text, gc);
		double x = (d.width - sb.getWidth())/2;
		double y = (d.height+ sb.getHeight())/2 - fm.getDescent();
		gc.drawString(text, (int)Math.round(x), (int)Math.round(y));
	}
}
