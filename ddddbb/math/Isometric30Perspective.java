/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;


public class Isometric30Perspective extends Camera4d.AParallelProjection {
	/**
	 * 
	 */
	public String toString() { return "isometric 30°"; }
	
	public Isometric30Perspective() {
		super();
	}

	public void setToDefault() {
		initAxes();
		rotateAxes(new Point4d(0,0,0,1),(Point4d)new Point4d(1,1,1,1).normalize());
		eye = new Point4d(3,2,0,0);
		changed();		
	}

	public void setDirec(DSignedAxis a) {
		initAxes(a);
		rotateAxes(new Point4d(0,0,0,1),(Point4d)new Point4d(1,1,1,1).normalize());
		eye = new Point4d(3,2,0,0);
		changed();		
	}
	
	public Point4d viewingDirection() {
		assert v[3].isNormal();
		return v[3];
	}
	
	public boolean nproj3d(Point4d p4,Point3d res3) {
		assert p4.dim() == 4;
		assert res3.dim() == 3;
		Point pd = p4.clone();
		pd.subtract(eye);
		res3.x[0] = v[0].sc(pd);
		res3.x[1] = v[1].sc(pd);
		res3.x[2] = v[2].sc(pd);
		//TODO: zoom to 1cm edge length
		return true;
	}

	/** no rotation for this perspective */ 
	public void rotate(double ph, Point4d a4d, Point4d b4d,Point4d p4d) {}
	public void setDirec(double ph1,double ph2,double ph3) {}

	public boolean isParallelProjectionEnabled() {
		return false;
	}
}