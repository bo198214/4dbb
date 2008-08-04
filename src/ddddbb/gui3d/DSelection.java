package ddddbb.gui3d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JToggleButton;

import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class DSelection extends JToggleButton {
	float brightness = (float) Settings.brightness.getDouble();
//	public DSelection(String text) {
//		setText(text);
//		setPreferredSize(new Dimension(20,16));
//	}
	
	private DSelection() {
		setFont(new Font(Font.DIALOG,Font.PLAIN,12));
		setOpaque(false);
	}
	public DSelection(int width, int height) {
		this();
		setPreferredSize(new Dimension(width, height));
	}
	public DSelection(int width, int height, String text) {
		this();
		setPreferredSize(new Dimension(width, height));
		setText(text);
	}
	@Override
	public void setText(String text) {
		super.setText(text);
	}
	
	public void paint(Graphics g) {
		Graphics2D gc = (Graphics2D) g;
		gc.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		gc.setRenderingHint( RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
//		gc.setColor(Color.black);
//		gc.fillRect(0, 0, getWidth(), getHeight());
		int w = getWidth()-1;
		int h = getHeight()-1;
		gc.setColor(new Color(brightness,brightness,brightness));
		FontMetrics fm = gc.getFontMetrics();
		
		
		Rectangle2D sb = fm.getStringBounds(getText(), gc);
		double x = (getWidth() - sb.getWidth())/2;
		//System.out.println(getText() + ":" + fm + ":" + sb.getWidth());
		double y = (getHeight()+ sb.getHeight())/2 - fm.getDescent();
		gc.drawString(getText(), (int)Math.round(x), (int)Math.round(y));
		if (isSelected()) {
			gc.drawLine((int)Math.round(x), h, (int)Math.round(x+sb.getWidth()), h);
		}
		else {
			gc.setColor(Color.black);
			gc.drawLine((int)Math.round(x), h, (int)Math.round(x+sb.getWidth()), h);
		}
	}
	
}
