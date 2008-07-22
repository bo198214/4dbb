/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;
import ddddbb.gen.MyChangeListener;

public class LinearPerspective extends Camera4d.ACentralProjection {
		public String toString() { return "perspective"; }
		
		protected boolean parallelProjection = true;

		public LinearPerspective() {
			super();
			Param.parallelProjection.addChangeListener(new MyChangeListener() {
				public void stateChanged() {
					setParallelProjection(Param.parallelProjection.isSelected());
				}
			});
		}
		
		public void setToDefault() {
			scale = 10;
			initAxes();
			rotateAxes(new Point4d(0,0,0,1),(Point4d)new Point4d(1,1,1,1).normalize());
			eye = viewingDirection().clone();
			eye.multiply(-scale);
			/* choose distance so that 1 unit in 4d in direction v[0], v[1] or v[2]
			 * at zoom distance becomes 1 unit in projection space
			 */
			eye.add(new Point4d(3,2,0,0));
			changed();
		}
		
		public void setDirec(DSignedAxis a) {
			//TODO
		}

		public Point4d viewingDirection() {
			assert v[3].isNormal();
			return v[3];
		}
		
//		public boolean nproj3d(Point4d p4,Point3d res3) {
//			Point pd = p4.clone();
//			pd.subtract(eye);
//			res3.x[0] = v[0].sc(pd);
//			res3.x[1] = v[1].sc(pd);
//			res3.x[2] = v[2].sc(pd);
//
//			if (!parallelProjection) {
//				double d = v[3].sc(pd); //distance to eye
//				if ( d > 0 ) {
//					res3.multiply(scale/d);
//					return true;
//				}
//				return false;
//			}
//			return true;
//		}

		public void rotate(double ph, Point4d a4d, Point4d b4d,Point4d p4) {
			for (int i=0;i<4;i++) {
				v[i].rotate(ph,a4d,b4d);
			}
			orthoNormalize();
			eye.rotate(ph,a4d,b4d,p4);
			changed();
		}
		
		public void rotate(Point4d a4d,Point4d b4d) {
			assert a4d.isNormal() && b4d.isNormal();
			rotateAxes(a4d,b4d);
			eye.rotate(a4d,b4d);
			changed();
		}
		
		protected void setParallelProjection(boolean _parallel) {
			parallelProjection = _parallel;
			changed();
		}

		public boolean isParallelProjection() {
			return parallelProjection;
		}

		//sets cameras viewing direction given by the poloar coordinate arcs
		//aligns other directions with local tetrahedrals of the sphere
		public void setDirec(double ph1,double ph2,double ph3) {
			for (int i=0;i<v.length;i++) v[i] = Gop.UNITVECTOR4[i].clone();
			double eyeDistance = eye.len();
			eye=v[3].clone();
			eye.multiply(-eyeDistance);
			eye.rotate(ph1,v[3],v[2]);
			Gop.rotate(ph1,v[3],v[2]); 
			eye.rotate(ph2,v[3],v[1]);
			Gop.rotate(ph2,v[3],v[1]);
			eye.rotate(ph3,v[3],v[0]);
			Gop.rotate(ph3,v[3],v[0]);
		
//			eye=new Point4d(v[3]);
//			eye.multiply(-zoom);
			changed();
		}

		public boolean isParallelProjectionEnabled() {
			return true;
		}
		
	}