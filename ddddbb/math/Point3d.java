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
	

	/** 3d left rotation around axis d by ph */
	public void rotate(double ph,Point3d d3d) {
		assert d3d.isNormal();
		double r1, r2, r3; 
		double c = Math.cos(ph);
		double s = Math.sin(ph);
		r1=
			((1 - c)*d3d.x[0]*d3d.x[0] + c)     *x[0] +
			((1 - c)*d3d.x[0]*d3d.x[1] + s*d3d.x[2])*x[1] + 
			((1 - c)*d3d.x[0]*d3d.x[2] - s*d3d.x[1])*x[2];  
		r2=
			((1 - c)*d3d.x[1]*d3d.x[0] - s*d3d.x[2])*x[0] + 
			((1 - c)*d3d.x[1]*d3d.x[1] + c)     *x[1] +
			((1 - c)*d3d.x[1]*d3d.x[2] + s*d3d.x[0])*x[2];
		r3 =
			((1 - c)*d3d.x[2]*d3d.x[0] + s*d3d.x[1])*x[0] +
			((1 - c)*d3d.x[2]*d3d.x[1] - s*d3d.x[0])*x[1] +
			((1 - c)*d3d.x[2]*d3d.x[2] + c)     *x[2];
		x[0]=r1;x[1]=r2;x[2]=r3;
	}

	public void rotate(double ph,Point3d d3d,Point3d o3d) {
		assert d3d.isNormal();
		subtract(o3d);
		rotate(ph,d3d);
		add(o3d);
	}
	

}
