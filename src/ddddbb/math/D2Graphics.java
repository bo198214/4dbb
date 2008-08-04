package ddddbb.math;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.FontMetrics;
import java.awt.Graphics2D;


public class D2Graphics implements D2GraphicsIF {
	private Graphics2D g;
	private double xcm;
	private double ycm;
	FontMetrics fm;
	
	public D2Graphics(Graphics2D _g,double _xcm, double _ycm) {
		g = _g;
		fm = g.getFontMetrics();
		xcm = _xcm;
		ycm = _ycm;
		
	}

	public void proj(Point2d p,D2Tupel s) {
		s.x1 =  (int)Math.round(p.x1*xcm);
		s.x2 = -(int)Math.round(p.x2*ycm);		
	}
	
	public void drawString(String s,Point2d p) {
		D2Tupel pi = new D2Tupel();
		proj(p,pi);
		g.drawString(s,pi.x1,pi.x2);
	}
	
	public void drawStringEast(String s, Point2d p) {
		D2Tupel pi = new D2Tupel();
		proj(p,pi);
		g.drawString(s,pi.x1+1,pi.x2);
	}
	
	public void drawStringNorth(String s, Point2d p) {
		D2Tupel pi = new D2Tupel();
		proj(p,pi);
		int descent = fm.getDescent();
		int w = fm.stringWidth(s);
		g.drawString(s,pi.x1-w/2,pi.x2-descent);
	}
	
	public void drawStringSouth(String s, Point2d p) {
		D2Tupel pi = new D2Tupel();
		proj(p,pi);
		int ascent = fm.getAscent();
		int w = fm.stringWidth(s);
		g.drawString(s, pi.x1-w/2, pi.x2+ascent);
	}
	
	public void drawStringWest(String s, Point2d p) {
		D2Tupel pi = new D2Tupel();
		proj(p,pi);
		int w = fm.stringWidth(s);
		g.drawString(s, pi.x1-w-1, pi.x2);
	}
	
	public void drawString(String s,double x,double y) {
		drawString(s,new Point2d(x,y));
	}
	
	public void drawLine(Point2d a,Point2d b) {
		D2Tupel ai = new D2Tupel();
		D2Tupel bi = new D2Tupel();
		proj(a,ai);
		proj(b,bi);
		g.drawLine(ai.x1,ai.x2,bi.x1,bi.x2);
	}
	
	public void drawThickLine(Point2d a, Point2d b) {
		D2Tupel ai = new D2Tupel();
		D2Tupel bi = new D2Tupel();
		proj(a,ai);
		proj(b,bi);
		g.setStroke(new BasicStroke(2));
		g.drawLine(ai.x1,ai.x2,bi.x1,bi.x2);
	}
	
	private static int blobRadius = 3;
	public void drawBlob(Point2d a) {
		D2Tupel ai = new D2Tupel();
		proj(a,ai);
		g.fillOval(ai.x1-blobRadius,ai.x2-blobRadius,2*blobRadius,2*blobRadius);
	}
	
	public void drawLine(double ax,double ay,double bx,double by) {
		drawLine(new Point2d(ax,ay),new Point2d(bx,by));
	}
	
	/** draws rectangle where a must be the left lower corner and b must be the right upper corner */
	public void drawRect(Point2d a,Point2d b) {
		D2Tupel ai = new D2Tupel();
		D2Tupel bi = new D2Tupel();
		proj(a,ai);
		proj(b,bi);
		g.drawRect(ai.x1,bi.x2,bi.x1-ai.x1,ai.x2-bi.x2);
	}
	
	public void clearRect(Point2d a,Point2d b) {
		D2Tupel ai = new D2Tupel();
		D2Tupel bi = new D2Tupel();
		proj(a,ai);
		proj(b,bi);
		g.clearRect(ai.x1,bi.x2,bi.x1-ai.x1,ai.x2-bi.x2);		
	}

	public void drawRect(double x0,double y0,double w,double h) {
		drawRect(new Point2d(x0,y0),new Point2d(x0+w,y0+h));
	}
	
	public void setColor(Color c) {
		g.setColor(c);
	}
	
	public Color getBackground() {
		return g.getBackground();
	}
	
	public void setBackground(Color c) {
		g.setBackground(c);
	}

	public void setComposite(Composite c) {
		g.setComposite(c);
	}

	public void translate(double x, double y) {
		Point2d a = new Point2d(x,y);
		D2Tupel ai = new D2Tupel();
		proj(a,ai);
		g.translate(ai.x1,ai.x2);
	}

	public Graphics2D getGraphics() {
		return g;
	}

	public void setGraphics(Graphics2D _g) {
		Composite c = g.getComposite();
		g = _g;
		g.setComposite(c);
	}

}
