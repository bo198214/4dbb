package ddddbb.math;

public class Point4d extends Point {
	
	public Point4d() {
		super(4);
	}
	
	public Point4d(double _x1,double _x2,double _x3,double _x4) {
		super(4);
		x[0]=_x1;x[1]=_x2;x[2]=_x3;x[3]=_x4;
	}
	
	public Point4d(Point p) {
		super(p);
	}
	
	public Point4d(Point4d p) {
		super(p);
	}
	
	public Point4d(int[] _x) {
		super(_x);
	}
	public Point4d(double[] _x) {
		super(_x);
	}
	
//	public double len() {
//		return Math.sqrt(x[0]*x[0]+x[1]*x[1]+x[2]*x[2]+x[3]*x[3]);
//	}
//	
//	/** scalar product */
//	public double sc(Point4d p) {
//		return x[0]*p.x[0]+x[1]*p.x[1]+x[2]*p.x[2]+x[3]*p.x[3];
//	}
//
//	/**  multiplication with scalar */
//	public Point4d times4d(double r) {
//		return new Point4d(r*x[0],r*x[1],r*x[2],r*x[3]);
//	}
//
//	public Point4d plus(Point4d p) {
//		return new Point4d(x[0]+p.x[0], x[1]+p.x[1], x[2]+p.x[2], x[3]+p.x[3]);
//	}
//	
//	public Point4d minus(Point4d p) {
//		return new Point4d(x[0]-p.x[0], x[1]-p.x[1], x[2]-p.x[2], x[3]-p.x[3]);
//	}
	
//	public void translate(Point4d p) {
//		x[0]+=p.x[0]; x[1]+=p.x[1]; x[2]+=p.x[2]; x[3]+=p.x[3];
//	}
//	
//	public void rotate(double ph,Direc4d a, Direc4d b, Point4d p) {
//		translate(p.times4d(-1));
//		rotate(ph,a,b);
//		translate(p);
//	}
//	
}
