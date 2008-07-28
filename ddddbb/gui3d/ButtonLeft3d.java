package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import ddddbb.game.Opt;

public class ButtonLeft3d extends BasicArrowButton {
	int w;
	int h;
	final static double wd = 1;
	final static double hd = 1;
	
	boolean mouseInside = false;
	
	public ButtonLeft3d() {
		super(SwingConstants.WEST);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

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
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}});
		addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}});
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}});
	}
	
	public void updateSize() {
		w = (int)(wd/Opt.xdpcm.getDouble());
		h = (int)(hd/Opt.ydpcm.getDouble());		
	}
	
//	public Dimension getPreferredSize() {
////		updateSize();
////		return new Dimension(w,h);
//	}

	public void paint(Graphics gc) {
		Dimension d = getSize();
		d.width -= 1;
		d.height -= 1;
		if (mouseInside) gc.setColor(Color.RED);
		else gc.setColor(Color.BLUE);
		gc.drawLine(0, 0, 0, d.height);
		gc.drawLine(0, d.height, d.width, d.height);
		gc.drawLine(d.width, d.height, d.width, 0);
		gc.drawLine(d.width, 0, 0, 0);
	}
	
	
	
	private static final long serialVersionUID = -7623676134203906851L;
}
