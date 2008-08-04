package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;

import javax.swing.AbstractButton;
import javax.swing.JToggleButton;

import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class DRadioButton extends JToggleButton {

	public DRadioButton() {
		setPreferredSize(new Dimension(16,16));
		setSize(new Dimension(16,16));
	}
	public void paint(Graphics g) {
		Graphics2D gc = (Graphics2D) g;
		gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		gc.setRenderingHint( RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
		Dimension d = getSize();
		//System.out.println(d);
		d.width -= 1;
		d.height -= 1;
		float brightness = (float) Settings.brightness.getDouble();
		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, d.width, d.height);
		gc.setColor(new Color(brightness,brightness,brightness));
		gc.drawArc(2, 2, 11, 11, 0, 360);
		if (isSelected()) {
			gc.fillArc(5, 5, 5, 5, 0, 360);
		}
	}
}
