package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JToggleButton;

import ddddbb.game.Settings;
import ddddbb.sound.SoundEnum;
import ddddbb.sound.SoundEnum.Sound;

public class DToggleButton extends JToggleButton {
	private boolean mouseInside = false;
	public DToggleButton(int width, int height, String text) {
		setPreferredSize(new Dimension(width,height));
		setText(text);
		setFont(Settings.font);
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
		//setRolloverIcon(rolloverIcon);
	}
	
	public void paint(Graphics g) {
		Graphics2D gc = (Graphics2D) g;
		// for antialiasing text
		gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gc.setRenderingHint( RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

		gc.setColor(Color.black);
		gc.fillRect(0, 0, getWidth(), getHeight());
		//System.out.println(d.width + "x" + d.height);
		//System.out.println(d.width + "," + d.height);
		int w  = getWidth() - 1;
		int h  = getHeight() - 1;
		float brightness = (float) Settings.brightness.getDouble();
		gc.setColor(new Color(brightness,brightness,brightness));
		if (this.isSelected()) {
			if (mouseInside) gc.drawRoundRect(0, 0, w, h, 8, 8);
			else gc.drawRect(0, 0, w, h); 
			
		}
		else {
			if (mouseInside) {
				gc.drawArc(0,0,8,8,90,90);
				gc.drawArc(0,h-8,8,8,180,90);
				gc.drawArc(w-8,h-8,8,8,270,90);
				gc.drawArc(w-8,0,8,8,0,90);
			}
			else {
				gc.drawPolyline(new int[] {0,0,3}, new int[] {3,0,0},3);
				gc.drawPolyline(new int[] {0,0,3}, new int[] {h-3,h,h},3);
				gc.drawPolyline(new int[] {w-3,w,w}, new int[] {h,h,h-3},3);
				gc.drawPolyline(new int[] {w,w,w-3}, new int[] {3,0,0},3);
			}			
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
