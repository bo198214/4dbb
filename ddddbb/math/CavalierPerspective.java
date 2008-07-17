/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class CavalierPerspective extends Camera4d {
	public String toString() { return "cavalier"; }
	
	private Point4d viewingDirection;
	private static final double v4Factor = 0.5;

	public CavalierPerspective() {
		super();
	}

	public void setToDefault() {
		initAxes();
		viewingDirection = new Point4d(1,1,1,1);
		eye = new Point4d(3,2,0,0);
		changed();
	}

	public void setDirec(DSignedAxis a) {
		initAxes(a);
		Point4d p = new Point4d(0,0,0,0);
		p.add(v[0]);
		p.add(v[1]);
		p.add(v[2]);
		p.add(v[3]);
		viewingDirection = (Point4d)p.normalize();
		eye = new Point4d(0,0,0,0);
		eye.add(v[0].clone().multiply(3));
		eye.add(v[1].clone().multiply(2));
		changed();
	}

	public boolean isParallelProjection() {
		return true;
	}

	public Point4d viewingDirection() {
		assert viewingDirection.isNormal();
		return viewingDirection;
	}
	
	public boolean nproj3d(Point4d p4d,Point3d res3d) {
		Point4d pd = p4d.clone();
		pd.subtract(eye);
		res3d.x[0] = v[0].sc(pd);
		res3d.x[1] = v[1].sc(pd);
		res3d.x[2] = v[2].sc(pd);

		Point3d v4proj = new Point3d(viewingDirection.sc(v[0]),viewingDirection.sc(v[1]),viewingDirection.sc(v[2]));
		double d = v[3].sc(pd)*v4Factor;
		res3d.subtract(v4proj.multiply(d));
		return true;
	}
	
	/** no rotation for this perspective */ 
	public void rotate(double ph, Point4d a, Point4d b,Point4d p) {}
	public void setDirec(double ph1,double ph2,double ph3) {}

	public boolean isParallelProjectionEnabled() {
		return false;
	}
}