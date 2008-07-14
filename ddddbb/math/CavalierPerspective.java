/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class CavalierPerspective extends Camera4d {
	public String toString() { return "cavalier"; }
	
	private Direc viewingDirection;
	private static final double v4Factor = 0.5;

	public CavalierPerspective() {
		super();
	}

	public void setToDefault() {
		initAxes();
		viewingDirection = new Direc(1,1,1,1);
		eye = new Point(3,2,0,0);
		changed();
	}

	public void setDirec(DSignedAxis a) {
		initAxes(a);
		Point p = new Point(0,0,0,0);
		p.add(v[0]);
		p.add(v[1]);
		p.add(v[2]);
		p.add(v[3]);
		viewingDirection = new Direc(p);
		eye = new Point(0,0,0,0);
		eye.translate(v[0],3);
		eye.translate(v[1],2);
		changed();
	}

	public boolean isParallelProjection() {
		return true;
	}

	public Direc viewingDirection() {
		return viewingDirection;
	}
	
	public boolean nproj3d(Point p4d,Point res3d) {
		Point pd = new Point(p4d);
		pd.translate(eye,-1);
		res3d.x[0] = v[0].sc(pd);
		res3d.x[1] = v[1].sc(pd);
		res3d.x[2] = v[2].sc(pd);

		Point v4proj = new Point(viewingDirection.sc(v[0]),viewingDirection.sc(v[1]),viewingDirection.sc(v[2]));
		double d = v[3].sc(pd)*v4Factor;
		res3d.translate(v4proj,-d);
		return true;
	}
	
	/** no rotation for this perspective */ 
	public void rotate(double ph, Direc a, Direc b,Point p) {}
	public void setDirec(double ph1,double ph2,double ph3) {}

	public boolean isParallelProjectionEnabled() {
		return false;
	}
}