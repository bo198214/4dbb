package ddddbb.math;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ddddbb.gen.DoubleModel;
import ddddbb.gen.Model;

public class Camera3d extends Model {
	public Point3d eye; //dim 3
	public Point3d v[] = { null, null, null}; //v[0], v[1] are Projection plane, v[2] viewing direction
	protected int orientation = 1;
	
	protected double screenEyeDist;
	protected double eyesDistHalf;
	protected double barEyeFocusDelta;
	
	public Camera3d(
			final DoubleModel _screenEyeDist,
			final DoubleModel _eyesDistHalf,
			final DoubleModel _barEyeFocusDelta
) {
		screenEyeDist = _screenEyeDist.getDouble();
		_screenEyeDist.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				screenEyeDist = _screenEyeDist.getDouble();
				changed();
			}
		});
		eyesDistHalf = _eyesDistHalf.getDouble();
		_eyesDistHalf.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ev) {
				eyesDistHalf = _eyesDistHalf.getDouble();
			}
		});
		barEyeFocusDelta = _barEyeFocusDelta.getDouble();
		_barEyeFocusDelta.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				barEyeFocusDelta = _barEyeFocusDelta.getDouble();
			}
		});

		setToDefault();
	}

	private Camera3d(boolean dummy) {}
	
	public Camera3d clone() {
		Camera3d res = new Camera3d(true);
		res.eye = eye.clone();
		for (int i=0;i<3;i++) {
			res.v[i] = v[i].clone();
		}
		return res;
	}
	
	public void setToDefault() {
		for (int i=0;i<v.length;i++) v[i] = AOP.unitVector3(i);
		eye = new Point3d(0,0,-screenEyeDist);
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
		assert axis.isNormal() : axis.len();
		v[0].rotate3d(ph,axis); 
		v[1].rotate3d(ph,axis);
		v[2].rotate3d(ph,axis);
		eye.rotate3d(ph,axis,o);
		AOP.orthoNormalize(v); //avoid those tiny drifts
		changed();
	}

	public void rotate(double ph, Point3d a, Point3d b, Point3d o) {
		assert a.isNormal() && b.isNormal() : a.len() + "!=1," + b.len() + "!=1";
		v[0].rotate(ph, a,b);
		v[1].rotate(ph, a,b);
		v[2].rotate(ph, a,b);
		eye.rotate(ph, a, b, o);
		AOP.orthoNormalize(v); //avoid those tiny drifts
		changed();
	}
	
	public void translate(Point3d a,double dist) {
		assert a.isNormal();
		eye.addby(a,dist);
		changed();
	}
	
	public Point3d viewingDirection() {
		if (orientation>0) {
			return v[2];
		}
		Point3d vd = v[0].clone();
		vd.multiply(orientation);
		return vd;
	}
	
	public void swapOrientation() {
		orientation *= -1;
		for (int i=0;i<3; i++ ) {
			v[i].x[2] *= -1;
		}
		eye.x[2] *= -1;
		changed();
	}
	
	/** +1 is left-handed, -1 is right-handed */
	public void setOrientation(int _orientation) {
		if (orientation!=_orientation) {
			swapOrientation();
		}
	}
}
