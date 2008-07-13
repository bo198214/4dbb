package ddddbb.game;

import java.awt.Color;
import java.awt.Composite;

import ddddbb.math.D2GraphicsIF;
import ddddbb.math.Point2d;

public class PSGraphics implements D2GraphicsIF {
	private String text;
	
	public PSGraphics() {
		text = new String();
	}
	
	public String getText() {
		return text;
	}
	
	public void start() {
		/* 
		 PS coordinate system: 
		 (0,0) is left lower corner
		 the unit is 1/72 inch
		 */
		text += "%!\n";
		text += 28.3465 + " " + 28.3465 + " scale\n";
		text += 10.51121 + " " + 14.86509 + " translate\n";
		text += "0 setlinewidth\n";
	}
	
	public void finish() {
		text += "showpage\n";
	}

	public void drawString(String s, Point2d p) {
		// TODO Auto-generated method stub
	}

	public void drawLine(Point2d a, Point2d b) {
		text += "newpath\n";
		text += a.x1 + " " + a.x2 + " moveto\n";
		text += b.x1 + " " + b.x2 + " lineto\n";
		text += " stroke\n";
	}

	public void drawBlob(Point2d a) {
		// TODO Auto-generated method stub
		
	}

	public void setColor(Color c) {
		text += c.getRed() + " " + c.getGreen() + " " + c.getBlue() + " setrgbcolor\n";
	}

	public void setComposite(Composite c) {
		// TODO Auto-generated method stub
		
	}

}
