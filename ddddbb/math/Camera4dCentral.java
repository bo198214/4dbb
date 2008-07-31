/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.ACell;

public abstract class Camera4dCentral extends Camera4d {
	protected final double scale; //distance of 3d projection space from eye

	protected Camera4dCentral(Point4d initialEye, Point4d[] initialV, double _scale) {
		super(initialEye,initialV);
		scale = _scale;
	}

	public boolean facedBy(ACell oc) {
		Point4d o = (Point4d)oc.o().clone().subtract(eye);
		//			if (o.sc(viewingDirection()) < Param.ERR) return false;
		return oc.normal().sc(o,eye) >= AOP.ERR;			
	}

	public boolean nproj3d(Point4d p4,Point3d res3) {
		Point pd = p4.clone();
		pd.subtract(eye);
		res3.x[0] = v[0].sc(pd);
		res3.x[1] = v[1].sc(pd);
		res3.x[2] = v[2].sc(pd);

		double d = v[3].sc(pd); //distance to eye
		if ( d >= AOP.ERR ) {
			res3.multiply(scale/d);
			return true;
		}
		return false;
	}

	public Point4d viewingDirection() {
//		assert v[3].isNormal();
//		if (orientation > 0) {
			return v[3];
//		}
//		Point4d vd = v[3].clone();
//		vd.multiply(orientation);
//		return vd;
	}
}