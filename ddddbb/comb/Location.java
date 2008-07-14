/**
 * 
 */
package ddddbb.comb;

import ddddbb.math.HalfSpace;
import ddddbb.math.Point;

public class Location extends ALocation {
		protected int dim;
		protected int spaceDim;
		protected Point origin = null;
		private HalfSpace halfSpace;

		public boolean equals(Object o) {
			Location l = (Location) o;
			if (dim != l.dim) return false;
			if (spaceDim!=l.spaceDim) return false;
			if (origin==null && l.origin==null) return true;
			return 	origin.equals(l.origin); 
		}
		Location(int _spaceDim,int _dim) {
			spaceDim = _spaceDim;
			dim = _dim;
		}
		Location(int _dim, Point _o) {
			spaceDim = _o.dim();
			dim = _dim;
			origin = _o;
		}
		Location(Point _o, boolean proj3d) {
			spaceDim = _o.dim();
			dim = 0;
			origin = _o;
			if (proj3d) {
				p3 = origin; 
			}
				
		}
		
		Location(DLocation loc, boolean proj3d) {
			if (proj3d) { assert loc.spaceDim() == 4; }
			dim = loc.dim();
//			assert (! proj3d) || (dim != 0 || p3 != null); 
			if (proj3d) {
				spaceDim  = 3;
				if (dim==0) { origin = loc.p3; }
			}
			else {
				spaceDim = loc.spaceDim();		
				if (dim==0) { origin = loc.o(); }
			}
			
			p3 = loc.p3;
			p2l = loc.p2l;
			p2r = loc.p2r;
			p2AheadEye = loc.p2AheadEye;
			p3AheadEye = loc.p3AheadEye;
			
		}
		
		public void setO(Point _origin) {
			assert _origin != null;
			origin = _origin;
		}

		@Override
		public int spaceDim() {
			return spaceDim;
		}

		@Override
		public int dim() {
			return dim;
		}

		@Override
		public Point o() {
			return origin;
		}

		public String toString() {
			return o().toString();
		}

		public HalfSpace halfSpace() {
			return halfSpace;
		}
		
		public void setHalfSpace(HalfSpace _halfSpace) {
			assert dim + 1 == spaceDim;
			assert origin==null || halfSpace.side(origin) == 0;
			halfSpace = _halfSpace;
		}
		
		DLocation _src;
	}