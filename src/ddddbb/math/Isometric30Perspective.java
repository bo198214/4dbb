/**
 * 
 */
package ddddbb.math;

public class Isometric30Perspective extends Camera4dOrthographic {
	public static Point4d initialViewDir = 
		(Point4d)new Point4d(-1,-1,-1,1).normalize();
	
	public static Point4d[] initialV() {
		Point4d[] res = defaultInitialV();
		assert res[3].isNormal();
		for (int i=0;i<4;i++) res[i].rotate(new Point4d(0,0,0,1), initialViewDir);
		assert res[3].isNormal();
		return res;
	}
	public static Point4d initialEye() {
		Point4d res = defaultInitialEye();
		res.rotate(new Point4d(0,0,0,1), initialViewDir);
		return res;
	}
	
	public Isometric30Perspective() {
		super(initialEye(),initialV());
		changed();
	}

	public String toString() { return "Isometric (orthographic)"; }
	
//	public void setToDefault() {
//		initAxes();
//		rotateAxes(Gop.unitVector4(3),initialViewDir);
//		eye = initialEye.clone();
//		changed();		
//	}
//
//	public void setDirec(DSignedAxis a) {
//		initAxes(a);
//		rotateAxes(Gop.unitVector4(3),initialViewDir);
//		eye = initialEye.clone();
//		changed();		
//	}
	
//	public Point4d viewingDirection() {
//		assert v[3].isNormal();
//		return v[3];
//	}
//	
//	public void initAxes(double ph1,double ph2,double ph3) {}

}