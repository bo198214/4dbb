package test;

import java.util.Random;

import static ddddbb.comb.DOp.*;
import ddddbb.comb.DCenter;
import ddddbb.comb.DOp;

import junit.framework.TestCase;

public class DOpTest extends TestCase {

	private Random rand = new Random();
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(DOpTest.class);
	}

	/*
	 * Test method for 'ddddbb.DOp.rotate' 
	 */
	
	public void testRotate() {
		int[][] a = new int[][] { 
				new int[] { -0, -0},
				new int[] { -0,  1},
				new int[] {  1, -0},
				new int[] {  1,  1}
		};
		int[][] b = copy(a);
		DCenter c = new DCenter(a,false);
		rotate(a,c,0,1);
		assertTrue(setEqual(a,b));
	}
	
	/*
	 * Test method for 'ddddbb.DOp.rotations(int)'
	 */
	public void testRotations() {

	}

	/*
	 * Test method for 'ddddbb.DOp.transEqual(int[][], int[][])'
	 */
	public void testTransEqual() {
		assertTrue(ddddbb.comb.DOp.transEqual(
				new int[][] { random4Vec() },
				new int[][] { random4Vec() }
		));
		
		assertFalse(ddddbb.comb.DOp.transEqual(
				new int[][] { new int[] { 1,1,1,1 } },
				new int[][] { new int[] { 1,1,1,1 }, new int[] { 1,1,1,2 } }
		));
	}

	/*
	 * Test method for 'ddddbb.DOp.map(int[], int[][])'
	 */
	public void testMapIntArrayIntArrayArray() {

	}

	public int[] random4Vec() {
		int[] v = new int[4];
		for (int ix=0;ix<4;ix++) {
			v[ix]=rand.nextInt()/4;
		}			
		return v;
	}
	public int[][] random4Compound() {
		int[][] a;
		int n = rand.nextInt(50);
		a = new int[n][];
		for (int i=0;i<n;i++) {
			a[i] = random4Vec();
		}
		return a;
	}
	
	/*
	 * Test method for 'ddddbb.DOp.motionEqual(int[][], int[][])'
	 */
	public void testMotionEqual() {
		assertTrue(ddddbb.comb.DOp.motionEqual(
				new int[][] { random4Vec() },
				new int[][] { random4Vec() }
		));
		
		assertFalse(ddddbb.comb.DOp.motionEqual(
				new int[][] { new int[] { 1,1,1,1 } },
				new int[][] { new int[] { 1,1,1,1 }, new int[] { 1,1,1,2 } }
		));
		
		assertTrue(ddddbb.comb.DOp.motionEqual(
				new int[][] { new int[] { 0,0,0,0 }, new int[] {0,0,0,1} },
				new int[][] { new int[] { 1,1,1,1 }, new int[] {2,1,1,1} }
		));
		// in even numbered dimensions every reflection is a rotation
		assertTrue(motionEqual(
				compoundTail(random4Vec(),new int[] { 1,2,3,4  }),
				compoundTail(random4Vec(),new int[] { -1,-2,-3,-4})
		));
		// in uneven numbered dimensions no reflection is a rotation
		assertFalse(motionEqual(
				new int[][] {
						new int[] { 0,0,0 },
						new int[] { 1,0,0 },
						new int[] { 1,1,0 },
						new int[] { 2,1,1 }
				},
				new int[][] {
						new int[] { 0,0,0 },
						new int[] { -1,0,0 },
						new int[] { -1, -1, 0 },
						new int[] { -2, -1, -1 }
				}
		));
	}
	
	public void testMotionContained() {
		assertTrue(transContained(
				new int[][] { new int[] {1,0,0,0}},
				new int[][] { 
						new int[] {2,0,0,0},
						new int[] {4,0,0,0}
				}
		));
		assertTrue(motionContained(
				new int[][] { new int[] {0,0,0,0}, new int[] {0,1,0,0} },
				new int[][] { new int[] {0,0,0,1}, new int[] {0,0,0,2} }
				));
		assertTrue(transContained(
				new int[][] { new int[] {1,0,0,0}, new int[] { 2,0,0,0 }, new int[] { 3,0,0,0 }},
				DOp.create4dStar()
				));
	}
	
	public void testBla() {
		int[][][] rot = rotations(2);
		assertTrue(rot != null);
	}

}
