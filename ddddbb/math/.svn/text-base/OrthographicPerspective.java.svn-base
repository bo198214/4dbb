/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class OrthographicPerspective extends Camera4d {
	public String toString() { return "orthographic"; }

	public OrthographicPerspective() {
		super();
	}
	public void setToDefault() {
		initAxes();
		eye = new Point4d(3,2,0,0);
		changed();		
	}

	public void rotate(double ph, Direc4d a, Direc4d b, Point4d p) {
	}

	public void setDirec(DSignedAxis a) {
		initAxes(a);
	}

	public void setDirec(double ph1, double ph2, double ph3) {
	}

	public boolean nproj3d(Point4d p, Point res) {
		Point4d pd = new Point4d(p);
		pd.translate(eye,-1);
		res.x[0] = v[0].sc(pd);
		res.x[1] = v[1].sc(pd);
		res.x[2] = v[2].sc(pd);
		return true;
	}

	public Direc4d viewingDirection() {
		return v[3];
	}

	public boolean isParallelProjection() {
		return true;
	}

	public boolean isParallelProjectionEnabled() {
		return false;
	}
	
}