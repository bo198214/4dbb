package ddddbb.math;

import ddddbb.comb.ACell;
import ddddbb.comb.DSignedAxis;
import ddddbb.game.Opt;
import ddddbb.gen.Model;
import ddddbb.gen.MyChangeListener;

public abstract class Camera4d extends Model implements Projection {
	public Point4d eye;
	public Point4d[] v = new Point4d[4];  //v[0],v[1],v[2] projection plane; v[3] viewing direction
	public double zoom;
	
	public abstract void setToDefault();
	public abstract void rotate(double ph, Point4d a4d, Point4d b4d, Point4d p4d);
	public abstract void setDirec(DSignedAxis a);
	public abstract void setDirec(double ph1,double ph2,double ph3);
	public abstract boolean nproj3d(Point4d p4d,Point3d res);
	public abstract Point4d viewingDirection();
	public abstract boolean isParallelProjection();
	public abstract boolean isParallelProjectionEnabled();
	public abstract boolean facedBy(ACell d);
	
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
		for (int i=0;i<v.length;i++) v[i] = Gop.UNITVECTOR4[i].clone();
	}
	
	public void initAxes(DSignedAxis a) {
		initAxes();
		if (a.human()==-4) {
			rotateAxes(Gop.UNITVECTOR4[3],Gop.UNITVECTOR4[0]);
			rotateAxes(Gop.UNITVECTOR4[0],new Point4d(a));
		}
		else {
			rotateAxes(Gop.UNITVECTOR4[3],new Point4d(a));
		}
		eye = new Point4d(3,2,0,0);
		changed();

	}
	
//	private void setViewArb0() {
//		eye=new Point4d(0,0,0,-zoom);
////		setDirec(Math.PI/4,0,-Math.PI/6);
//		setDirec(0,0,0);
//		eye.translate(new Point4d(3.5,2.7,1,-1));		
//	}
	
	public void rotate(Point4d a,Point4d b) {
		assert a.isNormal() && b.isNormal();
		rotate(a.arc(b),a,b,new Point4d(0,0,0,0));
	}

	public void translate(Point4d a,double dist) {
		assert a.isNormal();
		eye.addby(a,dist);
		changed();
	}
	
	protected void rotateAxes(Point4d a,Point4d b) {
		assert a.isNormal() && b.isNormal();
		for (int i=0;i<4;i++) {
			v[i].rotate(a,b);
		}
		orthoNormalize();
	}

	public void orthoNormalize() {
		Gop.orthogonalize(v);
		for (int i=0;i<v.length;i++) {
			v[i].normalize();
		}
	}
	
	public Point getDirec() {
		return viewingDirection().getPolarArcs();
	}
	
	public Point getEye() {
		return eye;
	}
	
	public void setEye(double x1, double x2, double x3, double x4) {
		eye = new Point4d(x1,x2,x3,x4);
		changed();
	}
	
	public boolean proj3d(Point4d p4,Point3d p3) {
		boolean inFront = nproj3d(p4,p3);
		p3.multiply(zoom);
		return inFront;
	}

	public boolean proj(int[] p, Point res) {
		return proj3d(new Point4d(p),(Point3d)res);
	}
	public int fromDim() { return 4; }
	public int toDim() { return 3; }

	public static abstract class AParallelProjection extends Camera4d {
		public boolean facedBy(ACell oc) {
//			Point4d o = (Point4d)oc.o().clone().subtract(eye); 
//			if (o.sc(viewingDirection()) < Param.ERR) return false;
			return oc.normal().sc(viewingDirection()) < -Param.ERR;
		}
		public boolean isParallelProjection() {
			return true;
		}
		public boolean nproj3d(Point4d p4,Point3d res3) {
			Point pd = p4.clone();
			pd.subtract(eye);
			res3.x[0] = v[0].sc(pd);
			res3.x[1] = v[1].sc(pd);
			res3.x[2] = v[2].sc(pd);
			return true;
		}
	}
	public static abstract class ACentralProjection extends Camera4d {
		protected double scale; //distance of 3d projection space from eye
		public boolean facedBy(ACell oc) {
			Point4d o = (Point4d)oc.o().clone().subtract(eye);
//			if (o.sc(viewingDirection()) < Param.ERR) return false;
			return oc.normal().sc(o,eye) > Param.ERR;			
		}
		public boolean isParallelProjection() {
			return false;
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


	}
}
