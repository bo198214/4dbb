package ddddbb.comb;

public class DOp {
	public static int length(int[] a) {
		int res = 0;
		for (int i=0;i<a.length;i++) {
			res += Math.abs(a[i]);
		}
		return res;
	}
	
	public static int dist(int[] a,int[] b) {
		int res = 0;
		assert a.length == b.length;
		for (int i=0;i<a.length;i++) {
			res += Math.abs(b[i]-a[i]);
		}
		assert res == length(minus(b,a));
		return res;
	}
	
	private static void copyAdjunctL(int l0, int dim, int[][] adjunct, int[][] matrix) {
		for (int l=0;l<dim;l++) { matrix[l][0] = 0; }
		for (int k=1;k<dim;k++) {
			for (int l=0;l<l0;l++) {
				matrix[l][k] =   adjunct[l][k-1];
			}
			for (int l=l0+1;l<dim;l++) {
				matrix[l][k] = adjunct[l-1][k-1];
			}
		}
	}
	/**
	 * computes all matrices M with det M = -1
	 * the indices are [number][line][column]
	 */
	public static int[][][] mirrorRotations(int dim) {
		int[][][] nr; //negative rotations, result
		if (dim==1) {
			nr = new int[1][1][1];
			nr[0][0][0]=1;
		}
		else {
			int[][][] snr = mirrorRotations(dim-1); // sub negative rotations
			int[][][] spr = rotations(dim-1);       // sub positive rotations
			int N = 2*dim*snr.length; //number of negative/positive rotations 
			nr = new int[N][dim][dim];
			int n=0;
			
			//positive subdeterminants
			for (int i=0;i<dim;i++) {
				for (int ispr=0;ispr<spr.length;ispr++) {
					copyAdjunctL(i,dim,spr[ispr],nr[n]);
					nr[n][i][0] = (i % 2) * 2 - 1; // -1 at [0][0], alternating
					//for the determinant being -1, the subdeterminant must be 1 
					n++;
				}
			}
			
			//negative subdeterminants
			for (int i=0;i<dim;i++) {
				for (int isnr=0;isnr<snr.length;isnr++) {
					copyAdjunctL(i,dim,snr[isnr],nr[n]);
					nr[n][i][0] = 1 - (i % 2) * 2; // 1 at [0][0], alternating
					//for the determinant being -1, the subdeterminant must be -1
					n++;
				}
			}
		}
		return nr;
	}
	
	/**
	 * computes all matrices M with det M = 1
	 * the indices are [number][line][column]
	 */
	public static int[][][] rotations(int dim) {
		int[][][] pr; //positive rotations, result
		if (dim==1) {
			pr = new int[1][1][1];
			pr[0][0][0]=1;
		}
		else {
			int[][][] snr = mirrorRotations(dim-1); // sub negative rotations
			int[][][] spr = rotations(dim-1);       // sub positive rotations
			int N = 2*dim*snr.length; //number of negative/positive rotations 
			pr = new int[N][dim][dim];
			int n=0;
			
			//positive subdeterminants
			for (int i=0;i<dim;i++) {
				for (int ispr=0;ispr<spr.length;ispr++) {
					copyAdjunctL(i,dim,spr[ispr],pr[n]);
					pr[n][i][0] = 1 - (i % 2) * 2; // 1 at [0][0], alternating
					//for the determinant being 1, the subdeterminant must be 1
					n++;
				}
			}
			
			//negative subdeterminants
			for (int i=0;i<dim;i++) {
				for (int isnr=0;isnr<snr.length;isnr++) {
					copyAdjunctL(i,dim,snr[isnr],pr[n]);
					pr[n][i][0] = (i % 2) * 2 - 1; //-1 at [0][0], alternating
					//for the determinant being 1, the subdeterminant must be -1
					n++;
				}
			}
		}
		return pr;
	}
		
	/**
	 * computes the vector of smalles coordinates of compound a
	 * for dimension information a must not be empty
	 */
	public static int[] extentMin(int[][] a) {
		int dim = a[0].length;
		int[] v = new int[dim];
		for (int ix=0;ix<dim;ix++) {
			v[ix]=Integer.MAX_VALUE;
		}
		for (int i=0;i<a.length;i++) {
			int[] p = a[i];
			for (int ix=0;ix<dim;ix++) {
				if (p[ix]<v[ix]) {v[ix]=p[ix];}
			}
		}
		return v;
	}

	/**
	 * computes the vector of largest coordinates of compound a
	 * for dimension information a must not be empty
	 */
	public static int[] extentMax(int[][] a) {
		int dim = a[0].length;
		int[] v = new int[dim];
		for (int ix=0;ix<dim;ix++) {
			v[ix] = Integer.MIN_VALUE;
		}
		for (int i=0;i<a.length;i++) {
			int[] p = a[i];
			for (int ix=0;ix<dim;ix++) {
				if (p[ix]>v[ix]) {v[ix]=p[ix];}				
			}
		}
		return v;
	}
	
	public static boolean vecEqual(int[] a,int[] b) {
		if (a.length!=b.length) { return false; }
		for (int i=0; i<a.length; i++) {
			if (a[i]!=b[i]) { return false; }
		}
		return true;
	}

	/**
	 * verifies if compound a as a set of coordinates is contained in b. 
	 * @return true if a is contained in b
	 */
	public static boolean setContained(int[][] a, int[][] b) {
		if (a.length>b.length) { return false; } //heuristic optimization
		for (int i=0;i<a.length;i++) {
			boolean contained = false;
			for (int j=0;j<b.length;j++) {
				if (vecEqual(a[i],b[j])) { contained = true; break; }
			}
			if (!contained) { return false; }
		}
		return true;
	}

	/**
	 * verifies if compound a takes the same space points as compound b
	 * @return true if equal
	 */
	public static boolean setEqual(int[][] a,int[][] b) {
		if (a.length != b.length) { return false; }
		return setContained(a,b);
		//this suffice because there are no two equal cubes in a compound
	}
	
	public static boolean setContained(int[] a, int[] b) {
		if (a.length > b.length) { return false; } //heuristic optimization
		for (int i=0;i<a.length;i++) {
			boolean contained = false;
			for (int j=0;j<b.length;j++) {
				if (a[i]==b[j]) { contained = true; break; }
			}
			if (!contained) { return false; }
		}
		return true;		
	}
	
	/**
	 * precondition: a must have no 2 equal elements and b too
	 */
	public static boolean setEqual(int[] a,int[] b) {
		if (a.length!=b.length) { return false; }
		return setContained(a,b);
	}
	
	public static int[] minus(int[] a,int[] b) {
		int[] r = new int[a.length];
		for (int i=0;i<a.length;i++) {
			r[i] = a[i]-b[i];
		}
		return r;
	}
	
	public static int[] minus(int[] a) {
//		int[] r = new int[a.length];
//		for (int i=0;i<a.length;i++) {
//			r[i] = -a[i];
//		}
		int[] r = clone(a);
		negate(r);
		return r;		
	}
	
	public static void negate(int[] a) {
		for (int i=0;i<a.length;i++) {
			a[i]=-a[i];
		}
	}
	
	public static int[] plus(int[] a,int[] b) {
//		int[] r = new int[a.length];
//		for (int i=0;i<a.length;i++) {
//			r[i] = a[i]+b[i];
//		}
		int[] r = clone(a);
		translate(r,b);
		return r;
	}
	
	public static void translate(int[] a,int[] d) {
		for (int i=0;i<a.length;i++) {
			a[i]+=d[i];
		}
	}
	
	public static int[][] trans(int[][] a,int[] d) {
//		int[][] r = new int[a.length][];
//		for (int i=0;i<a.length;i++) {
//			r[i] = plus(a[i],d);
//		}
		int[][] r = clone(a);
		for (int i=0;i<a.length;i++) {
			translate(r[i],d);
		}
		return r;
	}
	
	public static void translate(int[][] a,int[] d) {
		for (int i=0;i<a.length;i++) {
			translate(a[i],d);
		}
	}
	
	public static int[] map(int[] a,int[][] matrix) {
		int[] r = new int[a.length];
		for (int ix=0;ix<matrix.length;ix++) {
			r[ix]=0;
			for (int iv=0;iv<matrix.length;iv++) {
				r[ix]+=a[iv]*matrix[iv][ix];
			}
		}
		return r;
	}
	
	public static int[][] map(int[][] a,int[][] matrix) {
		int[][] r = new int[a.length][];
		for (int i=0;i<a.length;i++) {
			r[i] = map(a[i],matrix);
		}
		return r;
	}
	
	/**
	 * verifies whether compound a can be shifted to be b
	 * @return true if equal
	 */
	public static boolean transEqual(int[][] a,int b[][]) {
		int[][] b2 = trans(b,minus(extentMin(a),extentMin(b)));
		return setEqual(a,b2);
	}

	/**
	 * verifies whether compound a can be shifted to be contained in b
	 * @return true if a is contained in b
	 */
	public static boolean transContained(int[][] a, int[][] b) {
		int[] diff = minus(minus(extentMax(b),extentMin(b)),minus(extentMax(a),extentMin(a)));
		int prod = 1;
		for (int i=0;i<diff.length;i++) {
			prod *= diff[i]+1;
		}
		int[][] a2 = trans(a,minus(extentMin(b),extentMin(a)));
		for (int i=0;i<prod;i++) {
			int n = i;
			int[] d = new int[diff.length];
			for (int j=0;j<diff.length;j++) {
				int base = diff[j] + 1;
				d[j] = n % base;
				n /= base;
			}
			int[][] a3 = trans(a2,d);
			if (setContained(a3,b)) { return true; }
		}
		return false;
	}
	
	/**
	 * verifies whether a can be rotated and shifted to be b
	 * dimension is retrieved from first element of a, so a must not be empty
	 * @return true if equal
	 */
	public static boolean motionEqual(int[][] a,int[][] b) {
		//try all rotations of b and shiftCompare
		int[][][] rot = DOp.rotations(a[0].length); 
		for (int i=0;i<rot.length;i++) {
			if (transEqual(a,map(b,rot[i]))) { 
				return true;
			} 
		}
		return false;
	}
	
	/** 
	 * verifies whether a can be rotated and shifted 
	 * to be contained or equal to be b
	 * @return true if a is contained in b
	 */
	public static boolean motionContained(int[][] a, int[][] b) {
		int[][][] rot = DOp.rotations(a[0].length);
		for (int i=0;i<rot.length;i++) {
			if (transContained(a,map(b,rot[i]))) {
				return true;
			}
		}
		return false;
	}
	
	
	/** returns the number of different rotations of <b>a</b> that 
	 * are transEqual to the original <b>a</b>. Minimum is 1 because
	 * of the identity rotation.
	 * @param a the compound
	 * @return number of rotations
	 */
	public static int rotationSymmetry(int[][] a) {
		int[][][] rot = DOp.rotations(a[0].length);
		int symmetry = 0;
		for (int i=0;i<rot.length;i++) {
			if (transEqual(a,map(a,rot[i]))) {
				symmetry++;
			}
		}
		return symmetry;
	}
	
//	/**
//	 * 
//	 * verifies whether there is a motion to embed a into b
//	 */
//	public static boolean motionContained(int[][] a, int[][] b) {
//		//TODO
//		return true;
//	}
	
	/** returns the n-th 4d unit vector
	 * i.e. 1 -> (1,0,0,0), 2-> (0,1,0,0)
	 * for negative n returns the corresponding negated unit vector
	 */
	public static int[] unitVector(int n) {
		int[] r = { 0, 0, 0, 0};
		if (n>0) { r[n-1] = 1; return r; }
		if (n<0) { r[-n-1] = -1; return r; }
		return r;
	}
	
	/** returns the n-th d-dimensional unit vector
	 * 
	 * @see unitVector
	 */
	public static int[] unitVector(int n,int d) {
		int[] r = new int[d];
		for (int i=0;i<d;i++) { r[i]=0;	}
		if (n>0) { r[n-1] = 1; return r; }
		if (n<0) { r[n-1] = -1; return r; }
		return r;
	}
	
	/**
	 * Compound created by going from o first into direction d[0]
	 * then d[1], d[2], etc. Each d[i] can be the index of a unit vector
	 * i.e. 1,2,3 or 4. Or it can be one of -1,-2,-3,-4 denoting the
	 * inverse respective unit vector.
	 */
	public static int[][] compoundTail(int[] o,int[] d) {
		int[][] r = new int[1+d.length][];
		r[0]=o;
		for (int i=0;i<d.length;i++) {
			r[i+1]=plus(r[i],unitVector(d[i]));
		}
		return r;
	}
	
	public static int[][] create4dTail(DSignedAxis[] d) {
		int[][] res = new int[d.length+1][];
		res[0] = new int[] { 0, 0, 0, 0 };
		for (int i=0;i<d.length;i++) {
			res[i+1] = plus(res[i],d[i].unitVector(4));
		}
		return res;
	}
	
	public static int[][] create4dStar() {
		return new int[][] {
				new int[] { 0,0,0,0 },
				new int[] { 1,0,0,0 },
				new int[] { 0,1,0,0 },
				new int[] { 0,0,1,0 },
				new int[] { 0,0,0,1 },
				new int[] { -1,0,0,0 },
				new int[] { 0,-1,0,0 },
				new int[] { 0,0,-1,0 },
				new int[] { 0,0,0,-1 }
		};
	}
	
	public static int[][] create4dCube(int d) {
		int[][] res = new int[d*d*d*d][];
		for (int i0=0;i0<d;i0++) {
			for (int i1=0;i1<d;i1++) {
				for (int i2=0;i2<d;i2++) {
					for (int i3=0;i3<d;i3++) {
						res[d*d*d*i0+d*d*i1+d*i2+i3]=new int[] { i0,i1,i2,i3 };
					}
				}
			}
		}
		return res;
	}
	
	public static void rotate(int[] a,int v,int w) {
		assert v!=w;
//		int sign = v.pmSign() * w.pmSign();
//		int h = a[w.axis];
//		a[w.axis]=sign*a[v.axis];
//		a[v.axis]=-sign*h;
		int h = a[w];
		a[w]=a[v];
		a[v]=-h;
		
	}
	
	public static void rotate(int[][] a,int v,int w) {
		assert v!=w;
		for (int i=0;i<a.length;i++) {
			rotate(a[i],v,w);
		}
	}

	public static int[] rot(int[] a,int v,int w) {
		assert v!=w;
		int[] r = clone(a);
//		int sign, sign1=1, sign2=1;
//		if (v<0) { sign1=-1; }
//		if (w<0) { sign2=-1; }
//		sign = sign1 * sign2;
//		r[w-1]=sign*a[v-1];
//		r[v-1]=-sign*a[w-1];
		rotate(r,v,w);
		return r;
	}
	
//	public static int[] rot(int[] a,int[] o,int v, int w) {
//		return plus(rot(minus(a,o),v,w),o);
//	}
	
	public static void rotate(int[] a,int[] o,int v,int w) {
		assert v!=w;
		translate(a,minus(o));
		rotate(a,v,w);
		translate(a,o);
	}

	public static void rotate(int[] a,DCenter o,int v, int w) {
		assert v!=w;
		for (int i=0;i<a.length;i++) {
			a[i] *= 2;
		}
		rotate(a,o.twice(),v,w);
		for (int i=0;i<a.length;i++) {
			a[i] /= 2;
		}
	}
	
	public static void rotateAsCenters(int[] a,DCenter o,int v, int w) {
		assert v!=w;
		for (int i=0;i<a.length;i++) {
			a[i] = a[i]*2 + 1;
		}
		rotate(a,o.twice(),v,w);
		for (int i=0;i<a.length;i++) {
			a[i] = (a[i]-1)/2;
		}
	}
	
	
	public static void rotate(int[][] a,DCenter o,int v, int w) {
		assert v!=w;
		for (int i=0;i<a.length;i++) {
			rotate(a[i],o,v,w);
		}
	}
	
	public static int[][] rot(int[][] a,int v,int w) {
		assert v!=w;
		int[][] b = clone(a);
		rotate(b,v,w);
		return b;
	}
	
	public static int[][] rot(int[][] a,DCenter o,int v, int w) {
		assert v!=w;
		int[][] b = clone(a);
		rotate(b,o,v,w);
		return b;
	}
//	public static void rotate(int[][] a,SignedAxis v, SignedAxis w) {
//		rotate(a,a[0],v,w);
//	}
	
	public static int[] trans(int[] a,int v) {
		return plus(a,unitVector(v));
	}
	
	public static void translate(int[] a,DSignedAxis v) {
		a[v.axis] += v.pmSign();
	}
	
	public static void translate(int[][] a,DSignedAxis v) {
		for (int i=0;i<a.length;i++) {
			translate(a[i],v);
		}
	}
	
	public static boolean intersecting(int[][][] compounds) {
		for (int i1=0;i1<compounds.length;i1++) {
		   int[][] c1 = compounds[i1];
			for (int i2=i1+1;i2<compounds.length;i2++) {
				int[][] c2 = compounds[i2];
				for (int j1=0;j1<c1.length;j1++) {
					int[] t1 = c1[j1];
					for (int j2=0;j2<c2.length;j2++) {
						int[] t2 = c2[j2];
						if (vecEqual(t1,t2)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public static int[] clone(int[] a) {
		int[] r = new int[a.length];
		for (int i=0;i<a.length;i++) {
			r[i]=a[i];
		}
		return r;
	}
	
	public static int[][] clone(int[][] a) {
		int[][] r = new int[a.length][];
		for (int i=0;i<a.length;i++) {
			r[i] = clone(a[i]);
		}
		return r;
	}
	
	public static boolean d3adjacent(int[] a, int[] b) {
		int diff = 0;
		for (int ix=0;ix<a.length;ix++) {
			diff+=Math.abs(a[ix]-b[ix]);
		}
		return diff == 1;
	}
	
	public static boolean d3adjacent(int[][] a,int[][] b) {
		for (int i=0;i<a.length;i++) {
			for (int j=0;j<b.length;j++) {
				if (d3adjacent(a[i],b[j])) { return true; }
			}
		}
		return false;
	}

	public static String toString(int[] v) {
		String res = "[";
		for (int i=0;i<v.length;i++) {
			res += v[i];
			if (i<v.length-1) {
				res += ",";
			}
		}
		res += "]";
		return res;
	}
	
	public static void main(String[] args) {
//		int[][][] m = rotations(3);
//		for (int i=0;i<m.length;i++) {
//			for (int ix=0;ix<m[i].length;ix++) {
//				for (int iv=0;iv<m[i].length;iv++) {
//					System.out.print(m[i][iv][ix]+"\t");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		int[][] c1 = {
			new int[] { 0, 0, 0, 0 }	
		};
		System.out.println(rotationSymmetry(c1));
		System.out.println(rotationSymmetry(create4dTail(new DSignedAxis[] {
				new DSignedAxis(1),
				new DSignedAxis(2),
				new DSignedAxis(3),
				new DSignedAxis(4)
		})));
//		System.out.println(rotationSymmetry(Objectives.LEVEL0.goal));
//		System.out.println(rotationSymmetry(Objectives.LEVEL1.goal));
//		System.out.println(rotationSymmetry(Objectives.LEVEL2.goal));
//		System.out.println(rotationSymmetry(Objectives.LEVEL4.goal));
	}

}
