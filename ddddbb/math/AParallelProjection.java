/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.ACell;

public abstract class AParallelProjection extends Camera4d {
	/** The viewing direction of this parallel projection
	 * given in coordinates of the camera
	 */
	protected final Point4d viewDirRel; 
	protected Point4d viewingDirection = new Point4d(0,0,0,0);
	
	/** creates a parallel projection with given viewing direction 
	 * in coordinates of the camera.
	 * This viewing direction doesnt need to be normalized.
	 */
	protected AParallelProjection(Point4d initialEye, Point4d[] initialV, Point4d _viewDirRel) {
		super(initialEye,initialV);
		viewDirRel = _viewDirRel.clone();
		viewDirRel.normalize();
	}
	
	public boolean facedBy(ACell oc) {
		return oc.normal().sc(viewingDirection()) < -Param.ERR;
	}
	
	public boolean nproj3d(Point4d p4, Point3d res3) {
		Point4d pd = p4.clone();
		pd.subtract(eye);

		double d = v[3].sc(pd)/viewDirRel.x[3];
		for (int i=0;i<3;i++) {
			res3.x[i] = v[i].sc(pd) - d*viewDirRel.x[i];
		}
		//if (pd.sc(viewingDirection())<Param.ERR) return false;
		return true;
	}

	public Point4d viewingDirection() {
		for (int i=0;i<4;i++) {
			viewingDirection.x[i]=0;
		}
		for (int i=0;i<4;i++) {
			viewingDirection.addby(v[i], viewDirRel.x[i]);
		}
		return viewingDirection;
	}
}