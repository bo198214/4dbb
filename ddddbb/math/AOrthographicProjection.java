/**
 * 
 */
package ddddbb.math;


public abstract class AOrthographicProjection extends AParallelProjection {
	protected AOrthographicProjection(Point4d initialEye, Point4d[] initialV) {
		//though we dont need it - as we override the using methods - 
		//this would be the value as particular case of the AParallelProjection 
		super(initialEye, initialV, new Point4d(0,0,0,1));
	}

	@Override
	public boolean nproj3d(Point4d p4,Point3d res3) {
		Point4d pd = p4.clone();
		pd.subtract(eye);
		res3.x[0] = v[0].sc(pd);
		res3.x[1] = v[1].sc(pd);
		res3.x[2] = v[2].sc(pd);
		//if (pd.sc(viewingDirection()) < Param.ERR) return false;
		return true;
	}		
	
	@Override
	public Point4d viewingDirection() {
		assert v[3].isNormal();
		return v[3];
	}
	
	@Override
	public void rotate(double ph,Point4d a, Point4d b, Point4d c) {
		for (int i=0;i<4;i++) {
			v[i].rotate(ph, a,b);
		}
		eye.rotate(ph,a,b,c);
		changed();
	}
	
}