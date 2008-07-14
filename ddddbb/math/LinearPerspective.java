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
			rotateAxes(new Direc(0,0,0,1),new Direc(1,1,1,1));
			eye = new Point(viewingDirection());
			eye.multiply(-scale);
			/* choose distance so that 1 unit in 4d in direction v[0], v[1] or v[2]
			 * at zoom distance becomes 1 unit in projection space
			 */
			eye.add(new Point(3,2,0,0));
			changed();
		}
		
		public void setDirec(DSignedAxis a) {
			//TODO
		}

		public Direc viewingDirection() {
			return v[3];
		}
		
		public boolean nproj3d(Point p4,Point res4) {
			assert p4.dim() == 4;
			assert res4.dim() == 3;
			
			Point pd = new Point(p4);
			pd.translate(eye,-1);
			res4.x[0] = v[0].sc(pd);
			res4.x[1] = v[1].sc(pd);
			res4.x[2] = v[2].sc(pd);

			if (!parallelProjection) {
				double d = v[3].sc(pd); //distance to eye
				if ( d > 0 ) {
					res4.multiply(scale/d);
					return true;
				}
				return false;
			}
			return true;
		}

		public void rotate(double ph, Direc a4d, Direc b4d,Point p4) {
			assert p4.dim() == 4;
			assert a4d.dim() == 4;
			assert b4d.dim() == 4;
			for (int i=0;i<4;i++) {
				v[i].rotate(ph,a4d,b4d);
			}
			orthoNormalize();
			eye.rotate(ph,a4d,b4d,p4);
			changed();
		}
		
		public void rotate(Direc a4d,Direc b4d) {
			assert a4d.dim() == 4;
			assert b4d.dim() == 4;
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
			v[0]=new Direc(1,0,0,0);
			v[1]=new Direc(0,1,0,0);
			v[2]=new Direc(0,0,1,0);
			v[3]=new Direc(0,0,0,1);
			double eyeDistance = eye.len();
			eye=new Point(v[3]);
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