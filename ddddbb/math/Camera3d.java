package ddddbb.math;

import ddddbb.game.Opt;
import ddddbb.gen.Model;

public class Camera3d extends Model {
	public Point eye; //dim 3
	public Direc v[] = { null, null, null}; //v[0], v[1] are Projection plane, v[2] viewing direction
	
	public Camera3d() {
		setToDefault();
	}
	
	public Camera3d(Camera3d c) {
		eye = new Point(c.eye);
		for (int i=0;i<3;i++) {
			v[i]=new Direc(c.v[i]);
		}
	}
	
	public void setToDefault() {
		v[0] = new Direc(1,0,0);
		v[1] = new Direc(0,1,0);
		v[2] = new Direc(0,0,1);
		eye = new Point(0,0,-Opt.screenEyeDist.getDouble());
		//takes coordinate origin in the center of the screen
		
		changed();
	}
	
	public void rotate(double ph, Direc a, Direc b) {
		for (int i=0;i<3;i++) {
			v[i].rotate(ph,a,b);
		}
		eye.rotate(ph,a,b);
		changed();
	}
	
	public void rotate(double ph, Direc d,Point o3d) {
		assert o3d.dim() == 3;
		v[0].rotate(ph,d); 
		v[1].rotate(ph,d);
		v[2].rotate(ph,d);
		eye.rotate(ph,d,o3d);
		changed();
	}

	public void rotate(double ph, Direc a, Direc b, Point o3d) {
		assert o3d.dim() == 3;
		v[0].rotate(ph, a,b);
		v[1].rotate(ph, a,b);
		v[2].rotate(ph, a,b);
		eye.rotate(ph, a, b, o3d);
		changed();
	}
	
	public void translate(double dist,Direc a) {
		eye.translate(a,dist);
		changed();
	}

}
