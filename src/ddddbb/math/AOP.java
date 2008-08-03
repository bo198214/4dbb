package ddddbb.math;



public class AOP {

	public static final double deg = Math.PI*2/360;

	private static final Point4d[] UNITVECTOR4 = new Point4d[] {
		new Point4d(1,0,0,0),
		new Point4d(0,1,0,0),
		new Point4d(0,0,1,0),
		new Point4d(0,0,0,1)
	};
	private static final Point3d[] UNITVECTOR3 = new Point3d[] {
		new Point3d(1,0,0),
		new Point3d(0,1,0),
		new Point3d(0,0,1)
	};
	
	/* Computes the angle (given in degree) in the range 0<= .. <360.
	 *  Not fast.
	 */
	public static double angle0360(double ph) {
		if (ph<0) { return angle0360(ph+360); }
		if (ph>=360) { return angle0360(ph-360); }
		return ph;
	}
	/* Computes the angle (given in rad) into the range 0<= .. < 2*pi.
	 * Not fast.
	 */
	public static double angle02pi(double ph) {
		double pi2 = Math.PI*2;
		if (ph<0) { return angle02pi(ph+pi2); }
		if (ph>=pi2) { return angle02pi(ph-pi2); }
		return ph;
	}
	
	public static Point4d X(Point4d a,Point4d b,Point4d c) {
		return new Point4d(
				a.x[1]*b.x[2] *c.x[3]+b.x[1]*c.x[2] *a.x[3]+c.x[1]*a.x[2] *b.x[3]-
				c.x[1]*b.x[2] *a.x[3]-b.x[1]*a.x[2] *c.x[3]-a.x[1]*c.x[2] *b.x[3],
			    c.x[0]*b.x[2] *a.x[3]+b.x[0]*a.x[2] *c.x[3]+a.x[0]*c.x[2] *b.x[3]-
			    a.x[0]*b.x[2] *c.x[3]-b.x[0]*c.x[2] *a.x[3]-c.x[0]*a.x[2] *b.x[3] ,
			    a.x[0]*b.x[1] *c.x[3]+b.x[0]*c.x[1] *a.x[3]+c.x[0]*a.x[1] *b.x[3]-
			    c.x[0]*b.x[1] *a.x[3]-b.x[0]*a.x[1] *c.x[3]-a.x[0]*c.x[1] *b.x[3] ,
			    c.x[0]*b.x[1] *a.x[2]+b.x[0]*a.x[1] *c.x[2]+a.x[0]*c.x[1] *b.x[2]-
			    a.x[0]*b.x[1] *c.x[2]-b.x[0]*c.x[1] *a.x[2]-c.x[0]*a.x[1] *b.x[2] 
		);
	}
	
	public static Point4d unitVector4(int i) {
		assert 0<=i && i<4;
		return UNITVECTOR4[i].clone();
	}
	
	public static Point3d unitVector3(int i) {
		assert 0<=i && i<3;
		return UNITVECTOR3[i].clone();
	}

//	/**  
//	 * Given 2 orthogonal directions a and b,
//	 * rotates a towards b by the angle ph
//	 * rotates b by the same rotation
//	 *
//	 * @param ph rotation angle.
//	 * @param a and b determine rotation plane and direction
//	 * @return rotated a and b
//	*/
//	public static void rotate(double ph,Point4d a,Point4d b) {
//		double ax1,ax2,ax3,ax4,bx1,bx2,bx3,bx4;
//		ax1=a.x[0]; ax2=a.x[1]; ax3=a.x[2]; ax4=a.x[3];
//		bx1=b.x[0]; bx2=b.x[1]; bx3=b.x[2]; bx4=b.x[3];
//		double co = Math.cos(ph);
//		double si = Math.sin(ph);
//		a.x[0] = co*ax1+si*bx1;
//		a.x[1] = co*ax2+si*bx2;
//		a.x[2] = co*ax3+si*bx3;
//		a.x[3] = co*ax4+si*bx4;
//		b.x[0] = co*bx1-si*ax1;
//		b.x[1] = co*bx2-si*ax2;
//		b.x[2] = co*bx3-si*ax3;
//		b.x[3] = co*bx4-si*ax4;
//	}
//	
	/**  
	 * Given 2 orthogonal directions a and b,
	 * rotates a towards b by the angle ph
	 * rotates b by the same rotation
	 *
	 * @param ph rotation angle.
	 * @param a and b determine rotation plane and direction
	 * @return rotated a and b
	*/
	public static void rotate(double ph,Point a,Point b) {
		assert Math.abs(a.sc(b)) < AOP.ERR;
		double co = Math.cos(ph);
		double si = Math.sin(ph);
		Point a0, b0;
		a0 = a.clone();
		b0 = b.clone();
		for (int i=0;i<a.x.length;i++) {
 			a.x[i] = co*a0.x[i]+si*b0.x[i];
			b.x[i] = co*b0.x[i]-si*a0.x[i];
		}
	}
	
	public static boolean isOrthoNormal(Point[] v) {
		int n = v.length;
		for (int i=0;i<n;i++) {
			for (int j=i+1;j<n;j++) {
				if (!isZero(v[i].sc(v[j]))) return false;
			}
		}
		for (int i=0;i<n;i++) {
			if (!eq(v[i].len(),1)) return false;
		}
		return true;
	}
	public static void orthogonalize(Point v, Point w) {
		w.subtract(v.proj(w));
	}
	
	public static void orthoNormalize(Point v, Point w) {
		orthogonalize(v, w);
		v.normalize();
		w.normalize();
	}
	
	public static void orthogonalize(Point[] v) {
		for (int n=0;n<v.length;n++) {
			Point p = v[n].clone();
			for (int i=0;i<n;i++) {
				v[n].subtract(v[i].proj(p));
			}
			for (int i=0;i<n;i++) {
				assert Math.abs(v[i].sc(v[n])) < AOP.ERR :
					n + "," + v[i].sc(v[n]) +"\n" + v[i] + "\n" + v[n];
			}
		}
	}
	
	public static void orthoNormalize(Point[] v) {
		orthogonalize(v);
		for (int i=0;i<v.length;i++) {
			v[i].normalize();
		}
		assert isOrthoNormal(v);
	}
	
//	/** @see #rotate(double, Point4d, Point4d) */
//	public static void rotate(double ph,Point3d a,Point3d b) {
//		double ax1,ax2,ax3,bx1,bx2,bx3;
//		ax1=a.x[0]; ax2=a.x[1]; ax3=a.x[2];
//		bx1=b.x[0]; bx2=b.x[1]; bx3=b.x[2];
//		double co = Math.cos(ph);
//		double si = Math.sin(ph);
//		a.x[0] = co*ax1+si*bx1;
//		a.x[1] = co*ax2+si*bx2;
//		a.x[2] = co*ax3+si*bx3;
//		b.x[0] = co*bx1-si*ax1;
//		b.x[1] = co*bx2-si*ax2;
//		b.x[2] = co*bx3-si*ax3;
//	}
		
	public static Point3d X(Point3d a3d,Point3d b3d) {
		return new Point3d(
				a3d.x[1]*b3d.x[2]-a3d.x[2]*b3d.x[1],
				a3d.x[2]*b3d.x[0]-a3d.x[0]*b3d.x[2],
				a3d.x[0]*b3d.x[1]-a3d.x[1]*b3d.x[0]);
	}

	static final Point4d D1000 = UNITVECTOR4[0];
	static final Point3d D100 = UNITVECTOR3[0];

	public  final static double ERR = 0.00001;
	public static boolean isZero(double x) {
		return Math.abs(x) < ERR;
	}
	public static boolean eq(double x, double y) {
		return isZero(x-y);
	}

	public static boolean lt(double x, double y) {
		return x <= y - ERR;
	}

	public static boolean le(double x, double y) {
		return x < y + ERR;
	}
	
//	public static Point X(Point[] vecs) {
//		
//	}

//	public static boolean inSubSpace(Point p,Collection vertices) {
//		Iterator i = vertices.iterator();
//		double sum = 0;
//		while (i.hasNext()) {
//			sum += ((Point)i.next()).sc(p);
//		}
//		return Math.abs(sum-1)<Opt.ERR; 
//	}
//	public static boolean inConvex(Point p,Collection vertices) {
//		Iterator i = vertices.iterator();
//		double sum = 0;
//		while (i.hasNext()) {
//			double param = ((Point)i.next()).sc(p);
//			if (param < 0 || param > 1) { return false; }
//			sum += param;
//		}
//		return Math.abs(sum-1)<Opt.ERR;
//	}
//	public boolean inConvex(Point3d p,Point3d[] vertices) {
//		
//	}
	
}
