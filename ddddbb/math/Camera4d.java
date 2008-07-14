package ddddbb.math;

import ddddbb.comb.DSignedAxis;
import ddddbb.game.Opt;
import ddddbb.gen.Model;
import ddddbb.gen.MyChangeListener;

public abstract class Camera4d extends Model implements Projection {
	public Point eye;
	public Direc[] v = new Direc[4];  //v[0],v[1],v[2] projection plane; v[3] viewing direction
	public double zoom;
	
	public abstract void setToDefault();
	public abstract void rotate(double ph, Direc a4d, Direc b4d, Point p4d);
	public abstract void setDirec(DSignedAxis a);
	public abstract void setDirec(double ph1,double ph2,double ph3);
	public abstract boolean nproj3d(Point p4d,Point res);
	public abstract Direc viewingDirection();
	public abstract boolean isParallelProjection();
	public abstract boolean isParallelProjectionEnabled();
	
	public Camera4d() {
		setToDefault();
		zoom = 1;
		Opt.zoom.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				zoom = ((Double)Opt.zoom.getValue()).doubleValue();
			}
		});
	}
	
	public void initAxes() {
		v[0] = new Direc(1,0,0,0);
		v[1] = new Direc(0,1,0,0);
		v[2] = new Direc(0,0,1,0);
		v[3] = new Direc(0,0,0,1);
	}
	
	public void initAxes(DSignedAxis a) {
		initAxes();
		if (a.human()==-4) {
			rotateAxes(new Direc(0,0,0,1),new Direc(1,0,0,0));
			rotateAxes(new Direc(1,0,0,0),new Direc(a,4));
		}
		else {
			rotateAxes(new Direc(0,0,0,1),new Direc(a,4));
		}
		eye = new Point(3,2,0,0);
		changed();

	}
	
//	private void setViewArb0() {
//		eye=new Point4d(0,0,0,-zoom);
////		setDirec(Math.PI/4,0,-Math.PI/6);
//		setDirec(0,0,0);
//		eye.translate(new Point4d(3.5,2.7,1,-1));		
//	}
	
	public void rotate(Direc a,Direc b) {
		rotate(a.arc(b),a,b,new Point(0,0,0,0));
	}

	public void translate(double dist,Direc a) {
		eye.translate(a,dist);
		changed();
	}
	
	protected void rotateAxes(Direc a,Direc b) {
		for (int i=0;i<4;i++) {
			v[i].rotate(a,b);
		}
		orthoNormalize();
	}

	public void orthoNormalize() {
		v[3].normalize();
		v[2].translate(v[3].proj(v[2]),-1);
		v[2].normalize();
		v[1].translate(v[3].proj(v[1]),-1);
		v[1].translate(v[2].proj(v[1]),-1);
		v[1].normalize();
		v[0].translate(v[3].proj(v[0]),-1);
		v[0].translate(v[2].proj(v[0]),-1);
		v[0].translate(v[1].proj(v[0]),-1);
		v[0].normalize();
	}
	
	
	
	public Point getDirec() {
		return viewingDirection().getPolarArcs();
	}
	
	public Point getEye() {
		return eye;
	}
	
	public void setEye(double x1, double x2, double x3, double x4) {
		eye = new Point(x1,x2,x3,x4);
		changed();
	}
	
	public boolean proj3d(Point p4,Point p3) {
		boolean inFront = nproj3d(p4,p3);
		p3.multiply(zoom);
		return inFront;
	}

	public boolean proj(int[] p, Point res) {
		return proj3d(new Point(p),res);
	}
	public int fromDim() { return 4; }
	public int toDim() { return 3; }

}
