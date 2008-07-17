package ddddbb.math;

import ddddbb.comb.DSignedAxis;

/**
 * Class representing a Point or Vector of a given dimension.
 * 
 * In all methods that take another Point p as argument the dimension of p must
 * be less or equal to this dimension. If it is less then the unset coordinates
 * are assumed to be 0.
 */
public class Point {
	
	public double[] x; // this should be read only; made public for sake of simplicity

	/** creates Point of dimension n 
	 * subclassing to Point3d or Point4d 
	 * for n==3 or n==4 respectively.
	 * All create methods perform the proper subclassing.
	 */
	public static Point create(int n) {
		if (n==3) return new Point3d();
		if (n==4) return new Point4d();
		return new Point(n);
	}
	
	protected Point(int n) {
		x = new double[n];
	}
	
	public Point clone() {
		Point res = new Point(x.length);
		for (int d=0;d<x.length;d++) {
			res.x[d] = x[d];
		}
		return res;		
	}
	
	public static Point create(int[] x) {
		Point res = create(x.length);
		for (int d=0;d<x.length;d++) {
			res.x[d] = x[d];
		}
		return res;
	}
	
	/** creates Point, x remains unreferenced by this */
	public static Point create(double[] x) {
		Point res = create(x.length);
		for (int i=0;i<x.length;i++) {
			res.x[i] = x[i];
		}
		return res;
	}
	
	/** creates vector of dimension dim from Axis a */
	public static Point create(DSignedAxis a, int dim) {
		Point res = create(dim);
		for (int i=0;i<dim;i++) res.x[i] = 0;
		res.x[a.axis()]=a.pmSign();
		return res;
	}
	
	/** returns the dimension of this */
	public int dim() {
		return x.length;
	}
	
	/** returns the length of this */
	public double len() {
		double res = 0;
		for (int i=0;i<x.length;i++) {
			res += x[i]*x[i];
		}
		return Math.sqrt(res);
	}

	/** scalar product of this and b, no change of this */
	public double sc(Point b) {
		double res = 0;
		for (int d=0;d<x.length;d++) {
			res += x[d]*b.x[d];
		}
		return res;
	}
	
	/** subtracts b from this */
	public Point subtract(Point b) {
		for (int d=0;d<this.x.length;d++) {
			x[d] -= b.x[d];
		}
		return this;		
	}
	
//	/** the copy version of subtract */ 
//	public Point minus(Point b) {
//		Point res = clone();
//		res.subtract(b);
//		return res;
//	}
	
	/** adds d to this */
	public Point add(Point d) {
		for (int i=0;i<x.length;i++) {
			x[i]+=d.x[i];
		}
		return this;
	}
	
//	/** the copy version of add */
//	public Point plus(Point b) {
//		Point res = clone();
//		res.add(b);
//		return res;
//	}
	
	/** multiplies this by f */
	public Point multiply(double f) {
		for (int i=0;i<x.length;i++) {
			x[i] *= f;
		}
		return this;
	}

//	/** the copy version of multiply */
//	public Point times(double f) {
//		Point res = clone();
//		res.multiply(f);
//		return res;
//}

//	/** translates this by the vector d multiplied by the amount of dist
//	 * d can be of less dimension than this. In this case d is 
//	 * filled with 0 for the missing coordinates.
//	 */
//	public Point translate(Point d,double dist) {
//		for (int i=0;i<d.x.length;i++) {
//			x[i]+=d.x[i]*dist;
//		}
//		return this;
//	}
	
	/* rotates this by the angle ph in the plane spanned from a and b
	 * into the direction from a towards b
	 */
	public Point rotate(double ph,Point a, Point b) {
		double as = a.sc(this);
		double bs = b.sc(this);
		subtract(a.clone().multiply(as));
		subtract(b.clone().multiply(bs));
		// newthis + as*a2 + bs*b2 = oldthis
		Point a2 = a.clone();
		Point b2 = b.clone();
		Gop.rotate(ph,a2,b2);
		add(a2.multiply(as));
		add(b2.multiply(bs));
		return this;
	}

	/** angle from this to p!=0, 0<al<pi */
	public double arc(Point p) {
		return Math.acos(sc(p)/(len()*p.len()));
	}
	

	/** rotates this by the rotation from vector a to vector b */
	public Point rotate(Point a, Point b) {
		rotate(a.arc(b),a,b);
		return this;
	}
	
	/** rotates this with center p from direction a to direction b */
	public Point rotate(double ph,Point a, Point b, Point p) {
		subtract(p);
		rotate(ph,a,b);
		add(p);
		return this;
	}

	
	public Point proj(Point p) {
		return clone().multiply(sc(p));
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
	
	/** scales this Point to having length 1 */
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
		assert Math.abs(len()-1) < Param.ERR || len() < Param.ERR;
		return this;
	}
	
	public boolean isNormal() {
		return Math.abs(len()-1) < Param.ERR;
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
		return clone().subtract(p).len() < Param.ERR;
//		for (int i=0;i<x.length;i++) {
//			if (Math.abs(x[i]-p.x[i])>Main.opt.ERR) { return false; }
//		}
//		return true;
	}

	
	
}
