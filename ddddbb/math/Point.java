package ddddbb.math;



/**
 * Class representing a Point or Vector of a given dimension.
 * 
 * In all methods that take another Point p as argument the dimension of p must
 * be less or equal to this dimension. If it is less then the unset coordinates
 * are assumed to be 0.
 */
public class Point implements Comparable<Point>{
	public final double[] x; // this should be read only; made public for sake of simplicity

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
	
	protected Point(double[] x) {
		this.x = x;
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
	
	/** creates a Point referencing x */
	public static Point wrap(double[] x) {
		return new Point(x);
	}
	
//	/** creates vector of dimension dim from Axis a */
//	public static Point create(DSignedAxis a, int dim) {
//		Point res = create(dim);
//		for (int i=0;i<dim;i++) res.x[i] = 0;
//		res.x[a.axis()]=a.pmSign();
//		return res;
//	}
	
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
	
	/** faster sc(b.clone().subtract(a)) */
	public double sc(Point a, Point b) {
		double res = 0;
		for (int d=0;d<x.length;d++) {
			res += x[d]*(b.x[d]-a.x[d]);
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

	/** Faster add(d.clone().multiply(dist))
	 */
	public Point addby(Point d,double dist) {
		for (int i=0;i<d.x.length;i++) {
			x[i]+=d.x[i]*dist;
		}
		return this;
	}
	
	/* Rotates this by the angle ph in the plane spanned from a and b
	 * into the direction from a towards b
	 * a and b must be orthogonal and each must be normal
	 */
	public Point rotate(double ph,Point a, Point b) {
		assert AOP.isZero(a.sc(b)) : a.sc(b);
		assert AOP.eq(a.len(), 1) : a;
		assert AOP.eq(b.len(), 1) : b;
		double as = a.sc(this);
		double bs = b.sc(this);
		addby(a,-as);
		addby(b,-bs);
		// newthis + as*a2 + bs*b2 = oldthis
		Point a2 = a.clone();
		Point b2 = b.clone();
		AOP.rotate(ph,a2,b2);
		add(a2.multiply(as));
		add(b2.multiply(bs));
		return this;
	}

	/** angle from this to p!=0, 0&lt;al&lt;pi */
	public double arc(Point p) {
		return Math.acos(sc(p)/(len()*p.len()));
	}
	

	/** rotates this by the rotation from vector a to vector b */
	public Point rotate(Point a, Point b) {
		double assertLength = len();
		double ph = a.arc(b); 
		if (AOP.eq(ph, 0)) return this;
		Point a0 = a.clone();
		Point b0 = b.clone();
		AOP.orthoNormalize(a0,b0);
		rotate(a.arc(b),a0,b0);
		assert AOP.eq(assertLength, len()) : assertLength + "!=" + len();
		return this;
	}
	
	/** rotates this with center p from direction a to direction b */
	public Point rotate(double ph,Point a, Point b, Point p) {
		subtract(p);
		rotate(ph,a,b);
		add(p);
		return this;
	}

	/** returns the projection of Point p to this Point, this Point remains unmodified */ 
	public Point proj(Point p) {
		return clone().multiply(sc(p)/sc(this));
	}

	/** returns 1 if the first non-zero coordinate is positive
	 *  returns 0 if the this is 0
	 *  return -1 otherwise (if the first non-zero coordinate is negative) 
	 * */
	public int positivity() {
		for (int i=0;i<x.length;i++) {
			if (-AOP.ERR < x[i] && x[i]<AOP.ERR) { continue; }
			if (x[i]>0) { return 1; }
			if (x[i]<0) { return -1; }
		}		
		return 0;
	}
	
	/** scales this Point to having length 1 */
	protected Point normalize() {
		double l = len();
		if (l >= AOP.ERR) {
			for (int i=0;i<x.length;i++) {
				x[i] /= l;
			}
		}
		else {
			for (int i=0;i< x.length;i++) {
				x[i]=0;
			}
		}
		assert Math.abs(len()-1) < AOP.ERR || len() < AOP.ERR : this;
		return this;
	}
	
	public boolean isNormal() {
		return Math.abs(len()-1) < AOP.ERR;
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
		return clone().subtract(p).len() < AOP.ERR;
//		for (int i=0;i<x.length;i++) {
//			if (Math.abs(x[i]-p.x[i])>Main.opt.ERR) { return false; }
//		}
//		return true;
	}

	public int compareTo(Point o) {
		for (int i=0;i<x.length;i++) {
			if (x[i]<o.x[i]) return -1;
			if (x[i]>o.x[i]) return 1;
		}
		return 0;
	}

	
	
}
