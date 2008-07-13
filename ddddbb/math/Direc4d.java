package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class Direc4d extends Direc {
	public Direc4d(Point4d p) { 
		super(p);
	}
	
	public Direc4d(Direc4d d) {
		super(d);
	}
	
	public Direc4d(double _x1,double _x2,double _x3,double _x4) {
		super(new double[] {_x1,_x2,_x3,_x4 });
	}

	public Direc4d(DSignedAxis a) {
		super(a,4);
	}
	
	public Direc4d(boolean pos,int a) {
		super(new DSignedAxis(pos,a),4);
	}
}
