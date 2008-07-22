/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class OrthographicPerspective extends Camera4d.AParallelProjection {
	public String toString() { return "orthographic"; }

	public OrthographicPerspective() {
		super();
	}
	public void setToDefault() {
		initAxes();
		eye = new Point4d(3,2,0,0);
		changed();		
	}

	public void rotate(double ph, Point4d a4d, Point4d b4d, Point4d p4d) {
	}

	public void setDirec(DSignedAxis a) {
		initAxes(a);
	}

	public void setDirec(double ph1, double ph2, double ph3) {
	}

	public boolean nproj3d(Point4d p4, Point3d res3) {
		Point pd = p4.clone();
		pd.subtract(eye);
		res3.x[0] = v[0].sc(pd);
		res3.x[1] = v[1].sc(pd);
		res3.x[2] = v[2].sc(pd);
		return true;
	}

	public Point4d viewingDirection() {
		assert v[3].isNormal();
		return v[3];
	}

	public boolean isParallelProjectionEnabled() {
		return false;
	}
	
}