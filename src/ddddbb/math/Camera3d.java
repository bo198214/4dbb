package ddddbb.math;

import ddddbb.gen.AChangeListener;
import ddddbb.gen.DoubleModel;

public class Camera3d extends Camera {
	public Point3d eye; //dim 3
	protected int orientation = 1;
	
	protected double screenEyeDist;
	protected double eyesDistHalf;
	protected double barEyeFocusDelta;
	
	public Camera3d(
			final DoubleModel _screenEyeDist,
			final DoubleModel _eyesDistHalf,
			final DoubleModel _barEyeFocusDelta 
	) {
		//v[0], v[1] are Projection plane, v[2] viewing direction
		v = new Point3d[] { null, null, null};
		new AChangeListener() {
			public void stateChanged() {
				screenEyeDist = _screenEyeDist.getDouble();
				changed();
			}
		}.addTo(_screenEyeDist);
		new AChangeListener() {
			public void stateChanged() {
				eyesDistHalf = _eyesDistHalf.getDouble();
				changed();
			}
		}.addTo(_eyesDistHalf);
		new AChangeListener() {
			public void stateChanged() {
				barEyeFocusDelta = _barEyeFocusDelta.getDouble();
				changed();
			}
		}.addTo(_barEyeFocusDelta);
		
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
		notify=false; setOrientation(1); notify=true;
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
	
	public void rotate3d(double ph, Point3d axis,Point3d o) {
		assert axis.isNormal() : axis.len();
		((Point3d)v[0]).rotate3d(ph,axis); 
		((Point3d)v[1]).rotate3d(ph,axis);
		((Point3d)v[2]).rotate3d(ph,axis);
		eye.rotate3d(ph,axis,o);
		AOP.orthoNormalize(v); //avoid those tiny drifts
		changed();
	}

	public void rotate(double ph, Point a, Point b, Point o) {
		assert a.dim() == 3 && b.dim() == 3 && o.dim() == 3;
		assert a.isNormal() && b.isNormal() : a.len() + "!=1," + b.len() + "!=1";
		v[0].rotate(ph, a,b);
		v[1].rotate(ph, a,b);
		v[2].rotate(ph, a,b);
		eye.rotate(ph, a, b, o);
		AOP.orthoNormalize(v); //avoid those tiny drifts
		changed();
	}
	
	public void translate(Point a,double dist) {
		assert a.dim() == 3;
		assert a.isNormal();
		eye.addby(a,dist);
		changed();
	}
	
	public Point3d viewingDirection() {
		if (orientation>0) {
			return (Point3d)v[2];
		}
		Point3d vd = (Point3d)v[0].clone();
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

//	/** a1>0, a2>0 */
//	public void rot(double ph,final int a1, final int a2) {
//		assert a1>0 && a2>0;
//		rotate(ph, 
//				viewAbsRel.selectDirec3d(a1-1),
//				viewAbsRel.selectDirec3d(a2-1), 
//				viewAbsRel.selectCenter3d());		
//	}
//	
//	/** a1>0, a2>0 */
//	public ActionListener rotAction(final int a1, final int a2) {
//		assert a1>0 && a2>0;
//		return new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				rot(AOP.deg,a1,a2);
//			}
//		};					
//	}
//	
//	/** axis != 0 */
//	public void trans(int axis, double by) {
//		assert axis!=0;
//		if (axis>0) translate(viewAbsRel.selectDirec3d(axis-1),+by);	
//		if (axis<0) translate(viewAbsRel.selectDirec3d(-axis-1),-by);
//	}
//	
//	
//	/** axis != 0 */
//	public ActionListener transAction(final int axis) {
//		assert axis != 0;
//		return new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				trans(axis,0.1);
//			}
//		};
//	}
}
