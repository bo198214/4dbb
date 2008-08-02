package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import ddddbb.game.Settings;
import ddddbb.gen.AChangeListener;
import ddddbb.gen.MyChangeListener;
import ddddbb.sound.SoundEnum;

public class DArrowButton extends BasicArrowButton {
	int w;
	int h;
	final static double wd = 1;
	final static double hd = 1;
	
	boolean mouseInside = false;
	int direction;
	private float brightness;
	
	public DArrowButton(int _direction) {
		super(_direction);
		new AChangeListener() {
			public void stateChanged() {
				brightness = (float)Settings.brightness.getDouble();	
				repaint();
			}
		}.addTo(Settings.brightness);
		direction = _direction;
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				mouseInside = true;
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
	}
	
	public void updateSize() {
//		w = (int)(wd/Opt.xdpcm.getDouble());
//		h = (int)(hd/Opt.ydpcm.getDouble());		
	}
	
//	public Dimension getPreferredSize() {
////		updateSize();
////		return new Dimension(w,h);
//	}

	public void paint(Graphics gc) {
		Dimension d = getSize();
		//System.out.println(d.width + "," + d.height);
		d.width -= 1;
		d.height -= 1;
		gc.setColor(new Color(brightness,brightness,brightness));
		gc.drawLine(0, 0, 0, d.height);
		gc.drawLine(0, d.height, d.width, d.height);
		gc.drawLine(d.width, d.height, d.width, 0);
		gc.drawLine(d.width, 0, 0, 0);
		switch (direction) {
		case SwingConstants.WEST:
			gc.drawLine(8+2, 4, 4+2, 8);
			gc.drawLine(4+2, 8, 8+2, 12);
			gc.drawLine(8+2, 12, 8+2, 4);
			break;
		case SwingConstants.EAST:
			gc.drawLine(8-2, 4, 12-2, 8);
			gc.drawLine(12-2, 8, 8-2, 12);
			gc.drawLine(8-2, 12, 8-2, 4);
		}		
	}
	
	
	private static final long serialVersionUID = -7623676134203906851L;
}
