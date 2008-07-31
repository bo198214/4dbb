package ddddbb.gui3d;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DPanel extends JPanel {
	public void paint(Graphics g) {
		//opaque
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintBorder(g);
		super.paintChildren(g);
	}
}
