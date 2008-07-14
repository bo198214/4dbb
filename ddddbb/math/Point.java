package ddddbb.math;

public class Point {
	public double[] x; // this should be read only; made public for sake of simplicity
	/** scalar product, a,b of same dimension */

	public Point(int n) {
		x = new double[n];
	}
	
	public Point(Point p) { init(p); }
	
	public Point(int[] _x) {
		this(_x.length);
		for (int d=0;d<x.length;d++) {
			x[d] = _x[d];
		}
	}
	
	public Point(double[] _x) {
		x = _x;
	}
	
	public Point init(Point p) {
		x = new double[p.x.length];
		for (int d=0;d<x.length;d++) {
			x[d]=p.x[d];
		}
		return this;
	}
	
	public int dim() {
		return x.length;
	}
	public double len() {
		double res = 0;
		for (int i=0;i<x.length;i++) {
			res += x[i]*x[i];
		}
		return Math.sqrt(res);
	}
	public double sc(Point b) {
		double res = 0;
		for (int d=0;d<x.length;d++) {
			res += x[d]*b.x[d];
		}
		return res;
	}
	
	public Point subtract(Point b) {
		for (int d=0;d<this.x.length;d++) {
			x[d] -= b.x[d];
		}
		return this;		
	}
	
	/** the copy version of subtract */ 
	public Point minus(Point b) {
		Point res = new Point(this);
		res.subtract(b);
		return res;
	}
	
	public Point add(Point d) {
		for (int i=0;i<x.length;i++) {
			x[i]+=d.x[i];
		}
		return this;
	}
	
	/** the copy version of add */
	public Point plus(Point b) {
		Point res = new Point(this);
		res.add(b);
		return res;
	}
	
	public Point multiply(double f) {
		for (int i=0;i<x.length;i++) {
			x[i] *= f;
		}
		return this;
	}

	/** the copy version of multiply */
	public Point times(double f) {
		Point res = new Point(this);
		res.multiply(f);
		return res;
}

	/** d can be of less dimension than this */
	public void translate(Point d,double dist) {
		for (int i=0;i<d.x.length;i++) {
			x[i]+=d.x[i]*dist;
		}
	}
	
	public void rotate(double ph,Point a, Point b) {
		double as = a.sc(this);
		double bs = b.sc(this);
		translate(a,-as);
		translate(b,-bs);
		// newthis + as*a2 + bs*b2 = oldthis
		Point a2 = new Point(a);
		Point b2 = new Point(b);
		Gop.rotate(ph,a2,b2);
		translate(a2,as);
		translate(b2,bs);
	}

	/** angle from this to p!=0, 0<al<pi */
	public double arc(Point p) {
		return Math.acos(sc(p)/(len()*p.len()));
	}
	

	//this could be better done ;)
	public void rotate(Point a, Point b) {
		rotate(a.arc(b),a,b);
	}
	
	/** rotate with center p */
	public void rotate(double ph,Direc a, Direc b, Point p) {
		translate(p,-1);
		rotate(ph,a,b);
		add(p);
	}

	
	public Point proj(Point p) {
		return times(sc(p));
	}

	
	private static final Direc4d D1000 = new Direc4d(1,0,0,0);
	private static final Direc3d D100 = new Direc3d(1,0,0);
	/** 
	 * TODO: this is not generic
	 * returns ph1,ph2,ph3 0<ph1<2pi, 0<ph2,ph3<pi */
	public Point3d getPolarArcs() {
		double ph3=Math.PI/2,ph2=Math.PI/2,ph1=Math.PI/2;
		ph3 -= D1000.arc(this);
		Point3d p3 = new Point3d(x[1],x[2],x[3]);
		if ( p3.sc(p3) > Param.ERR ) {
			ph2 -= D100.arc(p3);
		}
		if (Math.abs(x[2]) > Param.ERR || Math.abs(x[3]) > Param.ERR ) {
			//atan2 angle from (0,1) clockwise, 
			ph1 = Math.atan2(x[2],x[3]);
		}
		return new Point3d(ph1,ph2,ph3);
	}
	
	/** returns 1 if the first non-zero coordinate is positive
	 *  returns 0 if the this is 0
	 *  return -1 otherwise (if the first non-zero coordinate is negative) 
	 * */
	public int positivity() {
		for (int i=0;i<x.length;i++) {
			if (-Param.ERR < x[i] && x[i]<Param.ERR) { continue; }
			if (x[i]>0) { return 1; }
			if (x[i]<0) { return -1; }
		}		
		return 0;
	}
	
	/** non-copy */
	protected Point normalize() {
		double l = len();
		if (l > 0) {
			for (int i=0;i<x.length;i++) {
				x[i] /= l;
			}
		}
		else {
			for (int i=0;i< x.length;i++) {
				x[i]=0;
			}
		}
		return this;
	}
	
	public String toString() {
		String res = "(";
		for (int i=0;i<x.length;i++) {
			res += x[i] + ",";
		}
		res += ")";
		return res;
	}
	

	public boolean equals(Object o) {
		Point p = (Point) o;
		if (x.length!=p.x.length) { return false; }
		return minus(p).len() < Param.ERR;
//		for (int i=0;i<x.length;i++) {
//			if (Math.abs(x[i]-p.x[i])>Main.opt.ERR) { return false; }
//		}
//		return true;
	}

	/** 3d left rotation around axis d by ph */
	public void rotate(double ph,Direc3d d) {
		assert dim() == 3;
		double r1, r2, r3; 
		double c = Math.cos(ph);
		double s = Math.sin(ph);
		r1=
			((1 - c)*d.x[0]*d.x[0] + c)     *x[0] +
			((1 - c)*d.x[0]*d.x[1] + s*d.x[2])*x[1] + 
			((1 - c)*d.x[0]*d.x[2] - s*d.x[1])*x[2];  
		r2=
			((1 - c)*d.x[1]*d.x[0] - s*d.x[2])*x[0] + 
			((1 - c)*d.x[1]*d.x[1] + c)     *x[1] +
			((1 - c)*d.x[1]*d.x[2] + s*d.x[0])*x[2];
		r3 =
			((1 - c)*d.x[2]*d.x[0] + s*d.x[1])*x[0] +
			((1 - c)*d.x[2]*d.x[1] - s*d.x[0])*x[1] +
			((1 - c)*d.x[2]*d.x[2] + c)     *x[2];
		x[0]=r1;x[1]=r2;x[2]=r3;
	}
	
	
}
