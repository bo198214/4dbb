/**
 * 
 */
package ddddbb.math;

import ddddbb.comb.DSignedAxis;
import ddddbb.gen.MyChangeListener;

public class LinearPerspective extends Camera4d {
		public String toString() { return "perspective"; }
		
		protected boolean parallelProjection = true;
		protected double scale; //distance of 3d projection space from eye

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
			rotateAxes(new Direc4d(0,0,0,1),new Direc4d(1,1,1,1));
			eye = new Point4d(viewingDirection());
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

		public Direc4d viewingDirection() {
			return v[3];
		}
		
		public boolean nproj3d(Point4d p,Point res) {
			Point4d pd = new Point4d(p);
			pd.translate(eye,-1);
			res.x[0] = v[0].sc(pd);
			res.x[1] = v[1].sc(pd);
			res.x[2] = v[2].sc(pd);

			if (!parallelProjection) {
				double d = v[3].sc(pd); //distance to eye
				if ( d > 0 ) {
					res.multiply(scale/d);
					return true;
				}
				return false;
			}
			return true;
		}

		public void rotate(double ph, Direc4d a, Direc4d b,Point4d p) {
			for (int i=0;i<4;i++) {
				v[i].rotate(ph,a,b);
			}
			orthoNormalize();
			eye.rotate(ph,a,b,p);
			changed();
		}
		
		public void rotate(Direc4d a,Direc4d b) {
			rotateAxes(a,b);
			eye.rotate(a,b);
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
			v[0]=new Direc4d(1,0,0,0);
			v[1]=new Direc4d(0,1,0,0);
			v[2]=new Direc4d(0,0,1,0);
			v[3]=new Direc4d(0,0,0,1);
			double eyeDistance = eye.len();
			eye=new Point4d(v[3]);
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