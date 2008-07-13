package ddddbb.game;

import ddddbb.math.BarEyeGraphics;
import ddddbb.math.Camera3d;
import ddddbb.math.D2Graphics;
import ddddbb.math.Point2d;
import ddddbb.math.Point3d;


public class StardustGraphics extends BarEyeGraphics {
	private double bgd = 5; //background distance

	public StardustGraphics(D2Graphics g, Camera3d c) {
		super(g,c);
	}
	
	public double sway(double x, double e, double s, double d) {
		return x;
	}
	
	public void drawPoint(double x, double y) {
		Point2d p = new Point2d(x,y);
		g2.drawLine(p,p);		
	}
	
	public void repeat(double x0,double y0,double w) {
		double e = Opt.eyesDistHalf.getDouble();
		double s = Opt.screenEyeDist.getDouble();
		for (double x=x0;x<w/2;x+=bgd/(bgd+s)*2*e) {
			drawPoint(x,y0);
		}
		for (double x=x0;x>-w/2;x-=bgd/(bgd+s)*2*e) {
			drawPoint(x,y0);
		}
	}
	
	public void drawStarDust() {
		for (int i=-10;i<=10;i++) {
			repeat(0,i,10);
		}
	}
	
	public void drawLine(Point3d a,Point3d b) {
		drawStarDust();
		super.drawLine(a,b);
	}

}
