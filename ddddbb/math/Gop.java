package ddddbb.math;



public class Gop {

	public static final Point4d[] UNITVECTOR4 = new Point4d[] {
		new Point4d(1,0,0,0),
		new Point4d(0,1,0,0),
		new Point4d(0,0,1,0),
		new Point4d(0,0,0,1)
	};
	public static final Point3d[] UNITVECTOR3 = new Point3d[] {
		new Point3d(1,0,0),
		new Point3d(0,1,0),
		new Point3d(0,0,1)
	};
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
