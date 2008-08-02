package ddddbb.comb;

import ddddbb.math.Point;

public class DCenter {
	private int[] twice;
	public DCenter(int[] origin) {
		origin(origin);
	}
	
	/** Creates center from given origins
	 *  a must have at least one element 
	 *  the given origins can either interpreted
	 *  as origines from cubes
	 *  or as points
	 *  to compute the center of
	 */
	public DCenter(int[][] a,boolean asCubes) {
		assert a.length > 0;
		twice = new int[a[0].length];
		for (int ix=0;ix<twice.length;ix++) {
			int sum = 0;
			for (int i=0;i<a.length;i++) {
				if (asCubes) {
					sum += 2*a[i][ix]+1;
				}
				else {
					sum += 2*a[i][ix];
				}
			}
			twice[ix]=sum/a.length;
		}
		round();
	}
	
	/** for a rotation center either each twice[i] is even,
	 * or each twice[i] is odd. Sets to nearest rotation center
	 */
	private void round() {
		int[] minU = new int[twice.length];
		int toU = 0; //distance to next uneven point
		int[] minE = new int[twice.length];
		int toE = 0; //distance to next even point
		for (int i=0;i<twice.length;i++) {
			int v = twice[i];
			int r = Math.abs(v%2);
			minU[i] = v - (1 - r);
			toU += 1 - r;
			minE[i] = v - r;
			toE += r;
		}
		if (toU<toE) { twice = minU; return; }
		if (toE<toU) { twice = minE; return; }
		twice = minE;
	}
	
	/** returns computed real location */
	public Point loc() {
		double[] res = new double[twice.length];
		for (int i=0;i<res.length;i++) {
			res[i] = twice[i]*0.5;
		}
		return Point.create(res);
	}
	
	/** returns computed origin */
	public int[] origin() {
		int[] res = new int[twice.length];
		for (int i=0;i<res.length;i++) {
			res[i] = (twice[i]-1)/2;
		}
		return res;
	}
	
	/** sets origin by value */
	public void origin(int[] origin) {
		twice = new int[origin.length];
		for (int i=0;i<origin.length;i++) {
			twice[i] = origin[i]*2+1;
		}			
	}
	
	/** returns computed `twice' */
	public int[] twice() {
		return DOp.clone(twice);
	}
	
	/** sets `twice' by value */
	public void twice(int[] _twice) {
		twice = DOp.clone(_twice);
		check();
	}
	
	public void translate(DSignedAxis v) {
		int[] o = origin();
		DOp.translate(o,v);
		origin(o);
	}
	
	public void rotate(DCenter o,int v,int w) {
		assert v!=w;
		DOp.rotate(twice,o.twice,v,w);
	}

	private void check() {
		if (twice.length==0) { return; }
		for (int i=1;i<twice.length;i++) { 
			assert twice[i] % 2 == twice[0] % 2 ;
		}			
	}
}


