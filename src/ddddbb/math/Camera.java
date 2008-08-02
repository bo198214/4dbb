package ddddbb.math;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ddddbb.gen.BoolModel;
import ddddbb.gen.Model;

public abstract class Camera extends Model {
	protected abstract void rotate(double ph, Point a, Point b, Point c);
	protected abstract void translate(Point axis,double d);
	
	public Point[] v;
	
	/** k>=0 */
	private Point selectDirec(int k, boolean cameraCoordinates) {
		if (cameraCoordinates) {
			return v[k].clone();
		}
		else {
			double[] x = new double[v.length];
			for (int i=0;i<v.length;i++) {
				if (i==k) x[i] = 1;
				else x[i] = 0;
			}
			return Point.create(x);
		}
	}

	/** a1>0, a2>0 */
	public void rot(double ph, int a1, int a2, Point center, boolean cameraCoordinates) {
		assert a1>0 && a2>0;
		//System.out.println("called rotate on " + this);
		rotate(ph,selectDirec(a1-1,cameraCoordinates),selectDirec(a2-1,cameraCoordinates),center);		
	}
	
	public ActionListener rotAction(final int a1, final int a2, final Point center, final BoolModel cameraCoordinates) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rot(AOP.deg,a1,a2,center,cameraCoordinates.isSelected());
			}
		};					
	}

	/** axis != 0 */
	public void trans(int axis, double d, boolean cameraCoordinates) {
		assert axis != 0;
		if (axis>0) translate(selectDirec(axis-1,cameraCoordinates),d);
		if (axis<0) translate(selectDirec(-axis-1,cameraCoordinates),-d);
	}
	
	public ActionListener transAction(final int axis, final BoolModel cameraCoordinates) {
		 return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				trans(axis,0.1,cameraCoordinates.isSelected());
			}
		};
	}

}
