package ddddbb.math;

public class Point2d {
	public double x1,x2;

	public Point2d() {}
	
	public Point2d(double _x1,double _x2) { x1=_x1; x2=_x2; }

	public double len() {
		return Math.sqrt(sc(this));
	}
	
	public double sc(Point2d p) {
		return x1*p.x1+x2*p.x2;
	}
	
	public Point2d mul(double r) {
		return new Point2d(r*x1,r*x2);
	}
}
