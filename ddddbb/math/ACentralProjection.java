/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.ACell;

public abstract class ACentralProjection extends Camera4d {
	protected double scale; //distance of 3d projection space from eye

	protected ACentralProjection(Point4d initialEye, Point4d[] initialV) {
		super(initialEye,initialV);
	}

	public boolean facedBy(ACell oc) {
		Point4d o = (Point4d)oc.o().clone().subtract(eye);
		//			if (o.sc(viewingDirection()) < Param.ERR) return false;
		return oc.normal().sc(o,eye) > Param.ERR;			
	}

	public boolean nproj3d(Point4d p4,Point3d res3) {
		Point pd = p4.clone();
		pd.subtract(eye);
		res3.x[0] = v[0].sc(pd);
		res3.x[1] = v[1].sc(pd);
		res3.x[2] = v[2].sc(pd);

		double d = v[3].sc(pd); //distance to eye
		if ( d > 0 ) {
			res3.multiply(scale/d);
			return true;
		}
		return false;
	}

	public Point4d viewingDirection() {
		assert v[3].isNormal();
		return v[3];
	}
}