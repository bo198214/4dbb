package ddddbb.math;

public class Point3d extends Point {
	public Point3d() {
		super(3);
	}
	
	public Point3d clone() {
		Point3d res = new Point3d();
		for (int i=0;i<3;i++) {
			res.x[i] = x[i];
		}
		return res;
	}

	public Point3d(double _x1,double _x2,double _x3) {
		this();
		x[0]=_x1;x[1]=_x2;x[2]=_x3;
	}
	
	/** chops of coordinates, requires p to have at least 3 coordinates */
	public Point3d(Point p) {
		this();
		for (int i=0;i<3;i++) {
			x[i] = p.x[i];
		}
	}

	/** 3d left rotation around axis d by ph */
	public void rotate3d(double ph,Point3d axis) {
		assert axis.isNormal();
		double r1, r2, r3; 
		double c = Math.cos(ph);
		double s = Math.sin(ph);
		r1=
			((1 - c)*axis.x[0]*axis.x[0] + c)     *x[0] +
			((1 - c)*axis.x[0]*axis.x[1] + s*axis.x[2])*x[1] + 
			((1 - c)*axis.x[0]*axis.x[2] - s*axis.x[1])*x[2];  
		r2=
			((1 - c)*axis.x[1]*axis.x[0] - s*axis.x[2])*x[0] + 
			((1 - c)*axis.x[1]*axis.x[1] + c)     *x[1] +
			((1 - c)*axis.x[1]*axis.x[2] + s*axis.x[0])*x[2];
		r3 =
			((1 - c)*axis.x[2]*axis.x[0] + s*axis.x[1])*x[0] +
			((1 - c)*axis.x[2]*axis.x[1] - s*axis.x[0])*x[1] +
			((1 - c)*axis.x[2]*axis.x[2] + c)     *x[2];
		x[0]=r1;x[1]=r2;x[2]=r3;
	}

	public Point3d rotate3d(double ph,Point3d axis,Point3d o3d) {
		assert axis.isNormal();
		subtract(o3d);
		rotate3d(ph,axis);
		add(o3d);
		return this;
	}
	

}
