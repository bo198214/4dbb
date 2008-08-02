/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class Isometric30Perspective extends Camera4d {
	/**
	 * 
	 */
	public String toString() { return "isometric 30°"; }
	
	public Isometric30Perspective() {
		super();
	}

	public void setToDefault() {
		initAxes();
		rotateAxes(new Direc4d(0,0,0,1),new Direc4d(1,1,1,1));
		eye = new Point4d(3,2,0,0);
		changed();		
	}

	public void setDirec(DSignedAxis a) {
		initAxes(a);
		rotateAxes(new Direc4d(0,0,0,1),new Direc4d(1,1,1,1));
		eye = new Point4d(3,2,0,0);
		changed();		
	}

	public boolean isParallelProjection() {
		return true;
	}
	
	
	public Direc4d viewingDirection() {
		return v[3];
	}
	
	public boolean nproj3d(Point4d p,Point res) {
		Point4d pd = new Point4d(p);
		pd.translate(eye,-1);
		res.x[0] = v[0].sc(pd);
		res.x[1] = v[1].sc(pd);
		res.x[2] = v[2].sc(pd);
		//TODO: zoom to 1cm edge length
		return true;
	}

	/** no rotation for this perspective */ 
	public void rotate(double ph, Direc4d a, Direc4d b,Point4d p) {}
	public void setDirec(double ph1,double ph2,double ph3) {}

	public boolean isParallelProjectionEnabled() {
		return false;
	}
}