package ddddbb.math;

import ddddbb.game.Opt;
import ddddbb.gen.Model;

public class Camera3d extends Model {
	public Point3d eye;
	public Direc3d v[] = { null, null, null}; //v[0], v[1] are Projection plane, v[2] viewing direction
	
	public Camera3d() {
		setToDefault();
	}
	
	public Camera3d(Camera3d c) {
		eye = new Point3d(c.eye);
		for (int i=0;i<3;i++) {
			v[i]=new Direc3d(c.v[i]);
		}
	}
	
	public void setToDefault() {
		v[0] = new Direc3d(1,0,0);
		v[1] = new Direc3d(0,1,0);
		v[2] = new Direc3d(0,0,1);
		eye = new Point3d(0,0,-Opt.screenEyeDist.getDouble());
		//takes coordinate origin in the center of the screen
		
		changed();
	}
	
	public void rotate(double ph, Direc3d a, Direc3d b) {
		for (int i=0;i<3;i++) {
			v[i].rotate(ph,a,b);
		}
		eye.rotate(ph,a,b);
		changed();
	}
	
	public void rotate(double ph, Direc3d d,Point3d o) {
		v[0].rotate(ph,d); 
		v[1].rotate(ph,d);
		v[2].rotate(ph,d);
		eye.rotate(ph,d,o);
		changed();
	}

	public void rotate(double ph, Direc3d a, Direc3d b, Point3d o) {
		v[0].rotate(ph, a,b);
		v[1].rotate(ph, a,b);
		v[2].rotate(ph, a,b);
		eye.rotate(ph, a, b, o);
		changed();
	}
	
	public void translate(double dist,Direc3d a) {
		eye.translate(a,dist);
		changed();
	}

}
