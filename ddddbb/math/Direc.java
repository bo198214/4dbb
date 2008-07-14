package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class Direc extends Point {
	
	public Direc(int dim) {
		super(dim);
	}

	public Direc(double _x1,double _x2,double _x3) { 
		super(new double[] {_x1,_x2,_x3});
		normalize();
	}

	public Direc(double x0,double x1,double x2, double x3) {
		super(x0,x1,x2,x3);
		normalize();
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
}
