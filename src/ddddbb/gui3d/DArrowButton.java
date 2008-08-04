package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import ddddbb.game.Settings;
import ddddbb.gen.AChangeListener;
import ddddbb.sound.SoundEnum;

@SuppressWarnings("serial")
public class DArrowButton extends BasicArrowButton {
	int w;
	int h;
	
	boolean mouseInside = false;
	private float brightness;
	
	public DArrowButton(int direction) {
		super(direction);
		new AChangeListener() {
			public void stateChanged() {
				brightness = (float)Settings.brightness.getDouble();	
				repaint();
			}
		}.addTo(Settings.brightness);
		setBackground(Color.black);
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseInside = true;
				if ((e.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) == InputEvent.BUTTON1_DOWN_MASK) return;
				repaint();
				SoundEnum.MOUSEOVER.play();
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
		setPreferredSize(new Dimension(16,16));
	}
	
	public static final int[] xWest = new int[] {8+2,4+2,8+2};
	public static final int[] yWest = new int[] {4,8,12};
	public static final int[] xEast = new int[] {8-2,12-2,8-2};
	public static final int[] yEast = new int[] {4,8,12};
	
	public void paint(Graphics g) {
		Graphics2D gc = (Graphics2D) g;
		gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		gc.setRenderingHint( RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

		gc.setColor(getBackground());
		gc.fillRect(0, 0, getWidth(), getHeight());

		w = getWidth() - 1;
		h = getHeight() - 1;

		gc.setColor(new Color(brightness,brightness,brightness));
		if (mouseInside) {
			gc.drawOval(0, 0, w, h);
		}
		else {
			gc.drawRect(0, 0, w, h);
		}
		
		
		switch (direction) {
		case SwingConstants.WEST:
			if(mouseInside) {
				gc.fillPolygon(xWest, yWest, xWest.length);
			}
			else {
				gc.drawPolygon(xWest, yWest, xWest.length);
			}
			break;
		case SwingConstants.EAST:
			if (mouseInside) {
				gc.fillPolygon(xEast, yEast, xEast.length);
			}
			else {
				gc.drawPolygon(xEast, yEast, xEast.length);
			}
//			gc.drawLine(8-2, 4, 12-2, 8);
//			gc.drawLine(12-2, 8, 8-2, 12);
//			gc.drawLine(8-2, 12, 8-2, 4);
		}		
	}
}
