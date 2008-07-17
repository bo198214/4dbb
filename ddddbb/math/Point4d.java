package ddddbb.math;

public class Point4d extends Point {
	public Point4d() {
		super(4);
	}
	
	public Point4d clone() {
		Point4d res = new Point4d();
		for (int i=0;i<4;i++) {
			res.x[i] = x[i];
		}
		return res;
	}

	public Point4d(double _x1,double _x2,double _x3,double _x4) {
		this();
		x[0]=_x1;x[1]=_x2;x[2]=_x3;x[3]=_x4;
	}
	
	public Point4d(int[] _x) {
		this();
		assert x.length == 4;
		for (int i=0;i<_x.length;i++) {
			x[i] = _x[i];
		}
	}

	/** 
	 * TODO: this is not generic
	 * returns ph1,ph2,ph3 0<ph1<2pi, 0<ph2,ph3<pi */
	public Point3d getPolarArcs() {
		double ph3=Math.PI/2,ph2=Math.PI/2,ph1=Math.PI/2;
		ph3 -= Gop.D1000.arc(this);
		if ( sc(this) > Param.ERR ) {
			ph2 -= Gop.D100.arc(this);
		}
		if (Math.abs(x[2]) > Param.ERR || Math.abs(x[3]) > Param.ERR ) {
			//atan2 angle from (0,1) clockwise, 
			ph1 = Math.atan2(x[2],x[3]);
		}
		return new Point3d(ph1,ph2,ph3);
	}
	
}
