/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class CavalierPerspective extends Camera4d {
	public String toString() { return "cavalier"; }
	
	private Direc4d viewingDirection;
	private static final double v4Factor = 0.5;

	public CavalierPerspective() {
		super();
	}

	public void setToDefault() {
		initAxes();
		viewingDirection = new Direc4d(1,1,1,1);
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
		viewingDirection = new Direc4d(p);
		eye = new Point4d(0,0,0,0);
		eye.translate(v[0],3);
		eye.translate(v[1],2);
		changed();
	}

	public boolean isParallelProjection() {
		return true;
	}

	public Direc4d viewingDirection() {
		return viewingDirection;
	}
	
	public boolean nproj3d(Point4d p,Point res) {
		Point4d pd = new Point4d(p);
		pd.translate(eye,-1);
		res.x[0] = v[0].sc(pd);
		res.x[1] = v[1].sc(pd);
		res.x[2] = v[2].sc(pd);

		Point3d v4proj = new Point3d(viewingDirection.sc(v[0]),viewingDirection.sc(v[1]),viewingDirection.sc(v[2]));
		double d = v[3].sc(pd)*v4Factor;
		res.translate(v4proj,-d);
		return true;
	}
	
	/** no rotation for this perspective */ 
	public void rotate(double ph, Direc4d a, Direc4d b,Point4d p) {}
	public void setDirec(double ph1,double ph2,double ph3) {}

	public boolean isParallelProjectionEnabled() {
		return false;
	}
}