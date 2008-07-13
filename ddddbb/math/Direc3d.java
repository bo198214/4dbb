package ddddbb.math;


public class Direc3d extends Direc {

	public Direc3d(double _x1,double _x2,double _x3) { 
		super(new double[] {_x1,_x2,_x3});
		normalize();
	}

	public Direc3d(Point3d p) {
		super(p);
		normalize();
	}
	
	public Direc3d(Direc3d d) {
		super(d);
	}
	
	public Direc3d(double[] _x) {
		super(_x);
		normalize();
	}
	
	public double arc(Point3d p) {
		return Math.acos(sc(p)/p.len());
	}
}
