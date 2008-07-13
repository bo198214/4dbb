package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class Direc extends Point {
	
	public Direc(int dim) {
		super(dim);
	}

	public Direc(double[] _x) {
		super(_x);
		normalize();
	}

	public Direc(Direc d) {
		super(d);
	}

	
	public Direc(Point p) {
		this(p.x);
	}
	
	public Direc(DSignedAxis a, int dim) {
		super(dim);
		for (int i=0;i<dim;i++) {
			x[i]=0;
		}
		x[a.axis()]=a.pmSign();
	}
	
//	public Point proj(Point p) {
//		return times(sc(p));
//	}
//
}
