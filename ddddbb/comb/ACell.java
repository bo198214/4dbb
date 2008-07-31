package ddddbb.comb;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import ddddbb.game.Main;
import ddddbb.math.Camera4d;
import ddddbb.math.HalfSpace;
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
		if ( ! Main.debug.isSelected() ) { return true; }
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

	public static class CompareByOcclusion implements Comparator<ACell> {
		/** Returns >0 if d1 is in front of (or greater than) d2,
		 * returns 0 if neither is in front of the other
		 * returns <0 if d2 is in front of (or greater than) d1
		 */
		public int compare(ACell d1, ACell d2) {
			
			assert d1.facing;
			assert d2.facing;
			//TODO creating a new HalfSpace for every compare is not performant.
			HalfSpace h2 = new HalfSpace(d2.o(),d2.normal());
			HalfSpace h1 = new HalfSpace(d1.o(),d1.normal());
			int sideof1 = h2.side(d1);
			int sideof2 = h1.side(d2);

			if (sideof1 == Cell.CONTAINED) {
				assert sideof2 == Cell.CONTAINED;
				//System.out.println((DLocation)d1.location() + "?" + (DLocation)d2.location() + ":CONTAINED,CONTAINED:0");
				return 0;
			}
			//OUTER is 1, SPLITTED is 0, INNER is -1
			//returns in the range of -2 ... 2
			//System.out.println(d1 + "?" + d2 + ":" + sideof1 + "," + sideof2 + ":" + (-sideof1+sideof2));
			return sideof1 - sideof2;
		}
	}
	
	public static <S,T extends S> void posort(Vector<T> l,Comparator<S> c) {
		if (l.size()==0 || l.size() ==1) { return; }
		int last = l.size()-1;
		T em = l.get(last);
		l.remove(last);
		//use l instead of Vector<T> left = new Vector<T>();
		Vector<T> right = new Vector<T>();
		Vector<T> mid = new Vector<T>();
		int i=0;
		for (T e : l) {
			int cmp = c.compare(e,em);
			if (cmp==0) mid.add(e);
			else if (cmp>0) right.add(e);
			else if (cmp<0) {
				l.set(i,e);
				i++;
			}
			else assert false;
		}
		l.setSize(i);
		//System.out.println(left.size() + "," + mid.size() + "," + right.size());
		posort(l,c);
		posort(right,c);
		posort(mid,c);
		
		for (T e: mid) l.add(e);
		l.add(em);
		for (T e: right) l.add(e);
	}
	
	public static <S,T extends S> void tsort(Vector<T> l,Comparator<S> c) {
		HashMap<T,HashSet<T>> predecessors = new HashMap<T,HashSet<T>>();
		for (T t: l) predecessors.put(t, new HashSet<T>());
		int n = l.size();
		for (int i=0;i<n;i++) {
			T t1 = l.get(i);
			for (int j=i+1;j<n;j++) {
				T t2 = l.get(j);
				int cmp = c.compare(t1, t2);
				if (cmp<0) {//t1<t2
					predecessors.get(t2).add(t1);
				}
				if (cmp>0) {//t1>t2
					predecessors.get(t1).add(t2);
				}
			}
		}
		for (T t1: predecessors.keySet()) {
			for (T t2: predecessors.get(t1)) {
				assert c.compare(t2, t1) < 0; 
			}
		}
		
		l.clear();
		while (l.size()<n) {
			boolean isCyclic = true;
			for (T t: predecessors.keySet()) {
				if (predecessors.get(t).size() == 0) {
					isCyclic = false;
					l.add(t);
				}
			}
			if (isCyclic) {
				System.out.println("Cycle detected:");
				T t = predecessors.keySet().iterator().next();
				Vector<T> prev = new Vector<T>();
				while (!prev.contains(t)) {
					prev.add(t);
					t = predecessors.get(t).iterator().next();
				}
				int i=prev.indexOf(t);
				prev.add(t);
				for (;i<prev.size()-1;i++) {
					t = prev.get(i);
					T tn = prev.get(i+1);
					System.out.println(t + ", " + t.hashCode() + ", this > next: " + (c.compare(t, tn)>0) + "(" + c.compare(t,tn) + ")");
					ACell cell = (ACell) t;
					ACell celln = (ACell) tn;
					System.out.println(cell.o() + ":" + cell.normal() + " > " + celln.o() + ":" + celln.normal());
				}
				assert false;
			}
			
				
			for (T t: predecessors.keySet()) {
				for (T s:l) predecessors.get(t).remove(s);
			}
			for (T s: l) predecessors.remove(s);
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
	
	public static void sortByOcclusion(Vector<? extends ACell> v) {
		tsort(v,new CompareByOcclusion());
//		for (ACell dc: v) {
//			System.out.println(dc);
//		}
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
		return c4.facedBy(this);
	}
	
	public void setFacing(Camera4d c4) {
		facing = c4.facedBy(this);
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
	
//	public String toString() {
//		if (dim()==0) {
//			return location().o().toString();
//		}
//		return facets().toString();
//	}

}
