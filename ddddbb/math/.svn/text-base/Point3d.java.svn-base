package ddddbb.math;


public class Point3d extends Point {
	//public double x1,x2,x3; // this should be read only; made public for sake of simplicity
	
	public Point3d() {
		super(3);
	}

	public Point3d(Point3d src) {
		super(src);
	}
	
	public Point3d init(Point3d src) {
		super.init(src);
		return this;
	}
	
	public Point3d(double _x1,double _x2,double _x3) {
		super(3);
		x[0]=_x1;x[1]=_x2;x[2]=_x3;
	}
	
	public Point3d(double[] _x) {
		super(_x); //check needed
	}
	
	public Point3d(int[] _x) {
		super(_x); //check needed
	}
	
	public Point3d(Point p) {
		super(p);
	}
	
	public Point3d add(Point3d p) {
		super.add(p);
		return this;
	}
	public Point3d plus(Point3d p) {
		Point3d res = new Point3d(this);
		res.add(p);
		return res;
	}
	
	public Point3d subtract(Point3d p) {
		super.subtract(p);
		return this;
	}
	public Point3d minus(Point3d p) {
		Point3d res = new Point3d(this);
		res.subtract(p);
		return res;
	}
	
	public Point3d multiply(double f) {
		super.multiply(f);
		return this;
	}
	public Point3d times(double f) {
		Point3d res = new Point3d(this);
		res.multiply(f);
		return res;
	}
	
//	public double len() {
//		return Math.sqrt(x[0]*x[0]+x[1]*x[1]+x[2]*x[2]);
//	}
//
//	/** sclar product */
//	public double sc(Point3d p) {
//		return x[0]*p.x[0]+x[1]*p.x[1]+x[2]*p.x[2];
//	}
//	
//	/** multiply scalar */
//	public Point3d times3d(double r) {
//		return new Point3d(r*x[0],r*x[1],r*x[2]);
//	}
//	
//	public Point3d plus(Point3d p) {
//		return new Point3d(x[0]+p.x[0], x[1]+p.x[1], x[2]+p.x[2]);
//	}
//
//	public Point3d minus(Point3d p) {
//		return new Point3d(x[0]-p.x[0], x[1]-p.x[1], x[2]-p.x[2]);
//	}
	
//	public void rotate(double ph,Direc3d a, Direc3d b) {
//		Direc3d a2 = new Direc3d(a);
//		Direc3d b2 = new Direc3d(b);
//		double as = a2.sc(this);
//		double bs = b2.sc(this);
//		Point3d abo = minus(a.times3d(as).plus(b.times3d(bs)));
//		// abo + as*a2 + bs*b2 = this
//		Gop.rotate(ph,a2,b2);
//		Point3d p = abo.plus(a2.times3d(as).plus(b2.times3d(bs)));
//		x[0] = p.x[0];x[1] = p.x[1]; x[2] = p.x[2];
//	}
	
//	public void translate(Point3d d) {
//		x[0]+=d.x[0];
//		x[1]+=d.x[1];
//		x[2]+=d.x[2];
//	}
//	

	public void rotate(double ph,Direc3d d,Point3d o) {
		translate(o,-1);
		rotate(ph,d);
		add(o);
	}
	
//	public Point3d copy() {
//		return new Point3d(x[0],x[1],x[2]);
//	}

}
