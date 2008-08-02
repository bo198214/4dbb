package ddddbb.comb;

import java.util.Vector;

import static ddddbb.comb.DOp.*;

@SuppressWarnings("unused")
public class DOpPartition {
	private int[][] compound;
	private int k;
	private int maxSymm;
	
	private Vector<Vector<int[][]>> partitions;
	
	public DOpPartition(int[][] _compound,int _k,int _maxSymm) {
		compound = _compound;
		k = _k;
		maxSymm = _maxSymm;
	}
	
	private int maxDist(int[] origin) {
		int res = 0;
		for (int i=0;i<compound.length;i++) {
			int d = dist(origin,compound[i]); 
			if (d>res) {res = d; }
		}
		return res;
	}
	
	private Vector<int[]> dist1Boundary(int[] origin,Vector<int[]> hull) {
		Vector<int[]> res = new Vector<int[]>();
		for (int i=0;i<compound.length;i++) {
			for (int j=0;j<hull.size();j++) {
				if (dist(compound[i],origin)-dist(hull.get(i),origin)==1) {
					if (hull.indexOf(compound[i]) == -1) {
						res.add(compound[i]);
						break;
					}
				}
			}
		}
		return res;
	}
	
	/** partition into compounds consisting of at least one cube */
	private void partitions() {
		partitions = new Vector<Vector<int[][]>>();
		/* Choose a first cube 
		 * determine all cubes with distance 1
		 * determine all combinations with those cubes
		 * use this as new 1st part if not motionequal to elements in previous partitions 
		 * determine all k-1-partitions of the remaining cubes
		 * add them to the previous partitions
		 */

		for (int iFirstCube=0;iFirstCube<compound.length;iFirstCube++) {
			int[] firstCube = compound[iFirstCube];
			Vector<int[]> firstPart = new Vector<int[]>();
			firstPart.add(firstCube);
			Vector<int[]> firstBoundary = new Vector<int[]>();
			while (true) {
				firstBoundary = dist1Boundary(firstCube,firstPart);
				for (int subset=0;subset<Math.pow(2,firstBoundary.size());subset++) {
					Vector<int[]> firstBoundarySubset = new Vector<int[]>(); 
					int subsetv = subset;
					for (int i=0;i<firstBoundary.size();i++) {
						if ( (subsetv & 1) == 1 ) { firstBoundarySubset.add(firstBoundary.get(i));	}
						subsetv = subsetv >> 1;
					}
					
					Vector<int[]> firstPart2 = new Vector<int[]>();
					firstPart2.addAll(firstPart);
					firstPart2.addAll(firstBoundarySubset);
					
					/* check that firstpart2 isnt contained in previous partitions already */
				}
			}
		}
		int[] firstPart = new int[k];
		for (int i=0;i<k;i++) {
			firstPart[i]=i;
		}
	}

}
