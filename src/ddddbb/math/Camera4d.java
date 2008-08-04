package ddddbb.math;

import ddddbb.comb.ACell;
import ddddbb.comb.DSignedAxis;

public abstract class Camera4d extends Camera {
	
	public static Point4d[] initialV(Point4d viewDirIn, Point4d eyeInOut) {
		Point4d w = new Point4d(0,0,0,1);
		Point4d[] v = new Point4d[] { new Point4d(1,0,0,0), new Point4d(0,1,0,0), new Point4d(0,0,1,0), new Point4d(0,0,0,1) };
		eyeInOut.rotate(w, viewDirIn);
		for (int i=0;i<4;i++) {
			v[i].rotate(w, viewDirIn);
		}
		assert v[3].equals(viewDirIn.clone().normalize()) : v[3] + "!=" + viewDirIn;
		return v;
	}

	protected static Point4d defaultInitialEye() {
		return new Point4d(3,2,0,0);
	}
	
	protected static Point4d[] defaultInitialV() {
		return new Point4d[] {
				new Point4d(1,0,0,0),
				new Point4d(0,1,0,0),
				new Point4d(0,0,1,0),
				new Point4d(0,0,0,1)
		};
	}
	
//	public abstract void rotate(double ph, Point4d a4d, Point4d b4d, Point4d p4d);
	//public abstract void initAxes(double ph1,double ph2,double ph3);
//	public abstract void initAxes(DSignedAxis a);
	/** projects the point p to res via this camera 
	 * and returns whether the point p was in front of the camera */
	protected abstract boolean nproj3d(Point4d p,Point3d res);
	public abstract Point4d viewingDirection();
	public abstract boolean facedBy(ACell d);

	public Point4d eye;
	private double zoom = 1;
	
	protected final Point4d initialEye;
	protected final Point4d[] initialV;
	private int orientation = +1; //+1 for left-handed  or -1 for right-handed
	
	/** Specify initial camera v coordinate system with origin at eye. 
	 * v[3] is supposed to be the viewing direction in the left-handed case.
	 * Coordinate system must be orthonormal.
	 * */
	protected Camera4d(Point4d _initialEye,Point4d[] _initialV) {
		initialEye = _initialEye;
		initialV = _initialV;
		v = new Point4d[4];  //v[0],v[1],v[2] projection plane; v[3] viewing direction
		setToDefault(); //no changed() comes outside as long as initialization
	}
	
	/** Camera coordinate system is derived by rotating 
	 * the standard R4 basis and initial eye by (0,0,0,1) -> initialViewDir 
	 */
	protected Camera4d(Point4d _initialEye, Point4d initialViewDir) {
		initialEye = _initialEye;
		initialV = initialV(initialViewDir,eye);
		assert v[0].equals(initialViewDir);
		setToDefault();
	}
	
	private void initAxes() {
		for (int i=0;i<4;i++) {
			v[i] = initialV[i].clone();
		}
		eye = initialEye.clone();
	}
	
	private void initAxes(DSignedAxis axis) {
		initAxes();
		Point4d w = AOP.unitVector4(3);
		Point4d x = AOP.unitVector4(0);
		Point4d a = new Point4d(axis);
		if (axis.human()==-4) {
			rotateAxes(w,x);
			rotateAxes(x,a);
			eye.rotate(w,x);
			eye.rotate(x,a );
		}
		else {
			rotateAxes(w,a);
			eye.rotate(w,a);
		}
	}

	public void setZoom(double _zoom) {
		if (zoom!=_zoom) {
			zoom = _zoom;
			changed();
		}
	}
	
	//sets cameras viewing direction given by the poloar coordinate arcs
	//aligns other directions with local tetrahedrals of the sphere
	public void initAxes(double ph1,double ph2,double ph3) {
		eye = initialEye.clone();
		v = eye.polarRotate(ph1, ph2, ph3);
		changed();
	}

//	private void setViewArb0() {
//		eye=new Point4d(0,0,0,-zoom);
////		setDirec(Math.PI/4,0,-Math.PI/6);
//		setDirec(0,0,0);
//		eye.translate(new Point4d(3.5,2.7,1,-1));		
//	}
	
	public void rotate(Point4d a,Point4d b) {
		assert a.isNormal() && b.isNormal();
		rotate(a.arc(b),a,b,new Point4d(0,0,0,0));
		changed();
	}

	public void translate(Point a,double dist) {
		assert a.dim() == 4;
		assert a.isNormal();
		eye.addby(a,dist);
		changed();
	}
	
	private void rotateAxes(Point4d a,Point4d b) {
		assert a.isNormal() && b.isNormal();
		for (int i=0;i<4;i++) {
			v[i].rotate(a,b);
		}
		AOP.orthoNormalize(v); //avoid those tiny drifts
	}

	public void rotate(double ph,Point a, Point b, Point c) {
		assert a.dim() == 4 : a.dim();
		assert b.dim() == 4 : b.dim();
		assert c.dim() == 4 : c.dim();
		for (int i=0;i<4;i++) {
			v[i].rotate(ph, a,b);
		}
		eye.rotate(ph,a,b,c);
		AOP.orthoNormalize(v); //avoid those tiny drifts
		changed();
	}

	public void setToDefault() {
		notify=false;
		setOrientation(1);
		initAxes();
		zoom = 1;
		notify=true;
		changed();
	}

	public Point getDirec() {
		return viewingDirection().getPolarArcs();
	}
	
	public Point getEye() {
		return eye;
	}
	
	public void setEye(double x1, double x2, double x3, double x4) {
		eye = new Point4d(x1,x2,x3,x4);
		changed();
	}
	
	@SuppressWarnings("serial")
	public static class ProjectionException extends Exception {
	}
	
	/** Returns true if p4 is in front of this camera */
	public boolean proj3d(Point4d p4,Point3d p3) {
		boolean inFront = nproj3d(p4,p3);
		p3.multiply(zoom);
		return inFront;
	}

	public void setDirec(DSignedAxis a) {
		initAxes(a);		
		changed();
	}
	
	private void swapOrientation() {
		orientation *= -1;
		for (int i=0;i<4;i++) {
			v[i].x[3] *= -1;
		}
		eye.x[3] *= -1;
	}
	
	/** +1 for left handed, -1 for right handed */
	public void setOrientation(int _orientation) {
		if (orientation != _orientation) {
			swapOrientation();
		}
		changed();
	}
	
	public int getOrientation() {
		return orientation;
	}

	
}
