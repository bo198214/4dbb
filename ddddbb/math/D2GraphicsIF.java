package ddddbb.math;

import java.awt.Color;
import java.awt.Composite;
import java.awt.FontMetrics;


public interface D2GraphicsIF {
	public void drawString(String s,Point2d p);
	public void drawStringWest(String s, Point2d p);
	public void drawStringEast(String s, Point2d p);
	public void drawStringNorth(String s, Point2d p);
	public void drawStringSouth(String s, Point2d p);
	public void drawLine(Point2d a,Point2d b);
	public void drawBlob(Point2d a);
	public void setColor(Color c);
	public void setComposite(Composite c);
}
