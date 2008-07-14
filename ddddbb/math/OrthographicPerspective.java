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
		eye = new Point(3,2,0,0);
		changed();		
	}

	public void rotate(double ph, Direc a4d, Direc b4d, Point p4d) {
	}

	public void setDirec(DSignedAxis a) {
		initAxes(a);
	}

	public void setDirec(double ph1, double ph2, double ph3) {
	}

	public boolean nproj3d(Point p4, Point res3) {
		assert p4.dim() == 4;
		Point pd = new Point(p4);
		pd.translate(eye,-1);
		res3.x[0] = v[0].sc(pd);
		res3.x[1] = v[1].sc(pd);
		res3.x[2] = v[2].sc(pd);
		return true;
	}

	public Direc viewingDirection() {
		return v[3];
	}

	public boolean isParallelProjection() {
		return true;
	}

	public boolean isParallelProjectionEnabled() {
		return false;
	}
	
}