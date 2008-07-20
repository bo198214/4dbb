package ddddbb.comb;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import ddddbb.math.Camera4d;
import ddddbb.math.HalfSpace;
import ddddbb.math.Param;
import ddddbb.math.Point;

public abstract class ACell {
	abstract public List<? extends BCell> facets();
	abstract public Point normal();
//	abstract public Vector<HalfSpace> halfSpaces();
	abstract public ALocation location();
	


	public int dim() { return location().dim(); }
	public int spaceDim() { return location().spaceDim(); }
	public Point o() { return location().o(); }
	

	public final Equality<ACell> relaxed = new Equality<ACell>() {
		@Override
		public boolean equal(ACell a, ACell b) {
			if ( a.dim() != b.dim() ) { return false; }
			if (a instanceof OCell) {
				OCell oa = (OCell) a;
				OCell ob = (OCell) b;
				return oa.cell() == ob.cell();
			};
			return a.location() == b.location();
			//if ( a.location().equals(b.location())) { return true; }
//			if ( a.dim() == 0 ) {
//				return a.location().o().minus(b.location().o()).len() < Param.ERR;
//			}
//			return relaxed.setEqual(a.facets(),b.facets());
		}
	};
	
	public final Equality<ACell> strict = new Equality<ACell>() {
		@Override
		public boolean equal(ACell a, ACell b) {
			if ( a.dim() != b.dim() ) { return false; }
			if ( a.location() == b.location() ) { return true; }
			return strict.setEqual(a.facets(),b.facets());
		}
	};
	
	public final Equality<ACell> locationStrict = new Equality<ACell>() {
		@Override
		public boolean equal(ACell a, ACell b) {
			return a.location() == b.location();
		}
	};
	
	/* Checks whether this cell is closed */
	public boolean checkClosed() {
		if ( ! Param.debug.isSelected() ) { return true; }
		if ( dim() == 0 ) { return true; }
		if ( dim() == 1 ) { return facets().size() == 2; }
		for (ACell fm1: facets()) {
			if (!fm1.checkClosed()) { assert false; }
			for (ACell fm2: fm1.facets()) {
				//For each facet of a facet there must be exactly
				//one other facet of a facet with the same location
				int refcount = 1;
				for (ACell gm1: facets()) {
					if (gm1 == fm1) { continue; }
					for (ACell gm2: gm1.facets()) {
						if (relaxed.equal(gm2,fm2)) { refcount++; }
					}
				}
				if (refcount != 2) { 
					assert false :
						"Did not find adjacent facet for " + fm1 + 
						" at " + fm2 +
						" in " + this; 
				}
			}
		}
		return true;
	}
	
	/* checks that each facets is contained in at most one parent 
	 */
	public boolean checkFaces() {
		for ( BCell f : facets() ) {
			assert f!=null;
			assert f.parent() != null;
			//if (f.parent() == null) { f.parent = this; }
			assert f.parent() == this;
		}
		return true;
	}

	public static boolean checkFaces(Collection<? extends ACell> tops) {
		for ( ACell t : tops ) {
			assert t.checkFaces();
		}
		return true;
	}

	/**	
	 * Is a decoration to temporarily store visibility information
	 * Indicates whether the outer direction of the facet is directed to the camera
	 */
	public boolean facing = true;  

	/** returns whether the front of this Cell would be seen by an observer at eye
	 *  in central projection.
	 */
	protected boolean centrallyFacing(Point eye) {
		return normal().sc(eye.clone().subtract(o())) > 0;
	}
	
	/** returns whether the front side of this cell would be seen by an 
	 * observer given by the direction v in parallel projection. 
	 */
	protected boolean ortographicallyFacing(Point v) {
		assert v.isNormal();
		return normal().sc(v) < 0;
	}

	private static class CompareByOcclusion implements Comparator<ACell> {
		public CompareByOcclusion() { super(); }
		public int compare(ACell d1, ACell d2) {
			assert d1.facing;
			assert d2.facing;
//			System.out.println((DLocation)d1.location() + "?" + (DLocation)d2.location());
			int sideof2 =(new HalfSpace(d1.o(),d1.normal())).side(d2);
			if (sideof2 == Cell.CONTAINED ) return 0; // does not matter 
			if (sideof2 == Cell.OUTER) {
//				System.out.println((DLocation)d1.location() + "<" + (DLocation)d2.location());
				return -1;} // 2 is in front of(>) 1
			if (sideof2 == Cell.INNER) {
//				System.out.println((DLocation)d1.location() + ">" + (DLocation)d2.location());
				return +1;} // 2 is behind(<) 1
			assert sideof2 == Cell.SPLITTED;
			int sideof1 = (new HalfSpace(d2.o(),d2.normal())).side(d1);
			assert sideof1 != Cell.CONTAINED;
			if (sideof1 == Cell.OUTER) return +1; // 1 is in front of (>) 2
			if (sideof1 == Cell.INNER) return -1; // 1 is behind (<) 2
			assert false;
			return 0;
		}
	}
	
//	private static class CompareByOcclusion implements Comparator<OCell> {
//		public int compare(OCell o1, OCell o2) {
//			assert o1.visible;
//			assert o2.visible;
//			assert o1.cell().location.space != null;
//			assert o2.cell().location.space != null;
//			assert o1.orientation() != 0;
//			assert o2.orientation() != 0;
//			if ( o1.cell().location.space.side(o2.cell(),o1.orientation) == Cell.OUTER ) {
//					return -1;
//			}
//			if ( o2.cell().location.space.side(o1.cell(),o2.orientation) == Cell.OUTER ) {
//				return 1;
//			}
//			return 0;
//		}		
//	}
	
	public static void sortByOcclusion(List<? extends ACell> v) {
		Collections.sort(v,new CompareByOcclusion());
	}
	
	
	public static void removeInvisibleParallel(Collection<DCell> v,Point d) {
		assert d.isNormal();
		Vector<DCell> toRemove = new Vector<DCell>();
		for (DCell f : v) {
			assert f != null;
			if ( f.isInternal() || ! f.ortographicallyFacing(d) ) {
				toRemove.add(f);
			}
		}
		v.removeAll(toRemove);
	}
	
	public static void removeInvisibleCentral(Collection<DCell> v,Point eye) {
		Vector<DCell> toRemove = new Vector<DCell>();
		for (DCell f : v) {
			assert f != null;
			if ( f.isInternal() || ! f.centrallyFacing(eye) ) {
				toRemove.add(f);
			}
		}
		v.removeAll(toRemove);
	}
	
	public boolean isFacing(Camera4d c4) {
		if (c4.isParallelProjection()) {
			return ortographicallyFacing(c4.viewingDirection());
		}
		return centrallyFacing(c4.getEye());
	}
	
	public void setFacing(Camera4d c4) {
		facing = isFacing(c4);
	}
	
	public Vector<ALocation> getPointLocations() {
		return getPointLocations(true);
	}
	public Vector<ALocation> getPointLocations(boolean inclOfInternal) {
		Vector<ALocation> locations = new Vector<ALocation>();
		addPointLocationsToList(locations,inclOfInternal);
		return locations;
	}
	
	public void addPointLocationsToList(Collection<ALocation> locations, boolean inclOfInternal) {
		if ( dim() == 0 ) {
			for ( ALocation l : locations ) {
				if ( l == location() ) { return; }
			}
			locations.add(location());
		}
		else {
			for ( ACell f : facets() ) {
				f.addPointLocationsToList(locations,inclOfInternal);
			}
		}
	}
	
	public String toString() {
		if (dim()==0) {
			return location().o().toString();
		}
		return facets().toString();
	}

}
