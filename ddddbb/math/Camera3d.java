package ddddbb.math;

import ddddbb.game.Opt;
import ddddbb.gen.Model;

public class Camera3d extends Model {
	public Point3d eye; //dim 3
	public Point3d v[] = { null, null, null}; //v[0], v[1] are Projection plane, v[2] viewing direction
	
	public Camera3d() {
		setToDefault();
	}

	private Camera3d(@SuppressWarnings("unused") boolean dummy) {}
	
	public Camera3d clone() {
		Camera3d res = new Camera3d(true);
		res.eye = eye.clone();
		for (int i=0;i<3;i++) {
			res.v[i] = v[i].clone();
		}
		return res;
	}
	
	public void setToDefault() {
		for (int i=0;i<v.length;i++) v[i] = Gop.UNITVECTOR3[i].clone();
		eye = new Point3d(0,0,-Opt.screenEyeDist.getDouble());
		//takes coordinate origin in the center of the screen
		changed();
	}
	
	public void rotate(double ph, Point a, Point b) {
		assert a.isNormal() && b.isNormal();
		for (int i=0;i<3;i++) {
			v[i].rotate(ph,a,b);
		}
		eye.rotate(ph,a,b);
		changed();
	}
	
	public void rotate(double ph, Point3d axis,Point3d o) {
		assert axis.isNormal();
		v[0].rotate3d(ph,axis); 
		v[1].rotate3d(ph,axis);
		v[2].rotate3d(ph,axis);
		eye.rotate3d(ph,axis,o);
		changed();
	}

	public void rotate(double ph, Point3d a, Point3d b, Point3d o) {
		assert a.isNormal() && b.isNormal() : a.len() + "!=1," + b.len() + "!=1";
		v[0].rotate(ph, a,b);
		v[1].rotate(ph, a,b);
		v[2].rotate(ph, a,b);
		eye.rotate(ph, a, b, o);
		changed();
	}
	
	public void translate(double dist,Point3d a) {
		assert a.isNormal();
		eye.add(a.clone().multiply(dist));
		changed();
	}

}
