/**
 * 
 */
package ddddbb.comb;

import ddddbb.math.Camera4d;
import ddddbb.math.D3Graphics;
import ddddbb.math.Point;
import ddddbb.math.Point2d;
import ddddbb.math.Point3d;
import ddddbb.math.Point4d;
import ddddbb.math.Camera4d.ProjectionException;

public abstract class ALocation {
	abstract public Point o();
	abstract public int spaceDim();
	abstract public int dim();

	public boolean p3AheadEye;
	public Point3d p3;
	public boolean p2AheadEye;
	public Point2d p2l;
	public Point2d p2r;
	
	/* computes the temporary p3, p2l and p2r projections if not already done (IN) */
	public void proj3d2dIN(D3Graphics g3,Camera4d c4) throws ProjectionException {
		proj2dIN(g3,c4);
	}

	public void proj3dIN(Camera4d c4) throws ddddbb.math.Camera4d.ProjectionException {
		assert dim() == 0;
		if (spaceDim() == 4) {
			if ( p3 == null ) {
				p3  = new Point3d();
			}
			p3AheadEye = c4.proj3d((Point4d)o(),p3);
			if (!p3AheadEye) { throw new Camera4d.ProjectionException(); }
		}
		if (spaceDim() == 3) {
			p3 = (Point3d)o();
		}
	}

	public void proj2dIN(D3Graphics g3,Camera4d c4) throws ProjectionException {
		assert spaceDim() == 4;
		assert dim() == 0;
		proj3dIN(c4);
		if (p2l == null ) {
			p2l = new Point2d();
			p2r = new Point2d();
		}
		p2AheadEye = g3.screenProj(p3,p2l,p2r);
	}
	
	Cell _dst;
}