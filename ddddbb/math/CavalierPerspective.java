/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class CavalierPerspective extends Camera4d.AParallelProjection {
	public String toString() { return "cavalier"; }
	
	private Point4d viewingDirection;
	private static final double v4Factor = 0.5;
	boolean facingDepth = false;

	public CavalierPerspective() {
		super();
	}

	public void setToDefault() {
		initAxes();
		if (facingDepth) viewingDirection = (Point4d)new Point4d(-1,-1,-1,-1).normalize();
		else viewingDirection = (Point4d) new Point4d(-1,-1,-1,1).normalize();
		eye = new Point4d(3,2,0,0);
		changed();
	}

	public void setDirec(DSignedAxis a) {
		initAxes(a);
		Point4d p = new Point4d(0,0,0,0);
		p.subtract(v[0]);
		p.subtract(v[1]);
		p.subtract(v[2]);
		if (facingDepth) p.subtract(v[3]);
		else p.add(v[3]);
		p.normalize();
		
		viewingDirection = p; 
		eye = new Point4d(0,0,0,0);
		eye.addby(v[0],3);
		eye.addby(v[1],2);
		changed();
	}

	public Point4d viewingDirection() {
		assert viewingDirection.isNormal();
		return viewingDirection;
	}
	
	private static final Point3d depthDirec =(Point3d) new Point3d(1,1,1).normalize(); 
	public boolean nproj3d(Point4d p4d,Point3d res3d) {
		Point4d pd = p4d.clone();
		pd.subtract(eye);
		res3d.x[0] = v[0].sc(pd);
		res3d.x[1] = v[1].sc(pd);
		res3d.x[2] = v[2].sc(pd);

		double d = v[3].sc(pd)*v4Factor;
		if (facingDepth) res3d.addby(depthDirec,-d);
		else res3d.addby(depthDirec,d);
		return true;
	}
	
	/** no rotation for this perspective */ 
	public void rotate(double ph, Point4d a, Point4d b,Point4d p) {}
	public void setDirec(double ph1,double ph2,double ph3) {}

	public boolean isParallelProjectionEnabled() {
		return false;
	}
}