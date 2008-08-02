package ddddbb.comb;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import ddddbb.math.Camera4d;
import ddddbb.math.D3Graphics;
import ddddbb.math.Point;
import ddddbb.math.Camera4d.ProjectionException;



/**
 * A class that represents an oriented integer facet, i.e. a facet of a (hyper)cube
 * with integer coordinates and each side length 1.
 * A toplevel facet is defined as having the same number of dimensions
 * as the surrounding space and having no parents.
 * @author bo198214
 *
 */
public class DCell extends BCell {
	
	public static class Cube extends DCell {
		
		public Cube(int[] coords, Vector<DLocation>[] _locations) {
			super(4);
			normal = new DSignedAxis(1,-1);
			parent = null;
			location = new DLocation(DOp.clone(coords),new int[] {0,1,2,3});
			initialize(_locations);
		}
	}

	public DLocation location;
	private int dim;
	DSignedAxis normal; //normal vector for this facet, points outside
	public DCell[][] facets = new DCell[2][];
	DCell parent = null;
	
	/** 
	 * The neighbor facet of this facet in a compound.
	 * That is an adjacent facet 
	 * of same orientation located in the same subspace
	 */
	public DCell neighbor = null;
	public boolean isInternal0() {
		return neighbor != null;
	}

	public boolean isInternal() {
		if ( neighbor != null ) {
			if ( neighbor.parent != null ) {
				return ! neighbor.parent.isInternal(); 
			}
			return true; // snappedTo().parent() == null
		}
		return false; // snappedTo == null
//		return snappedTo() != null;
	}	

	protected DCell(int _dim) {
		dim = _dim;
		if (dim==0) { facets = null; }
		else {
			for (int s=0;s<2;s++) {
				facets[s] = new DCell[dim];
				for (int ai=0;ai<dim;ai++) {
					facets[s][ai] = new DCell(dim-1);
					facets[s][ai].parent = this;
				}
			}
		}
	}
	
	public DCell(DCell f) {
		dim = f.dim;
		normal = f.normal;
		parent = f.parent;
		for (int s=0;s<2;s++) {
			facets[s] = new DCell[dim];
			for (int a=0;a<dim;a++) {
				facets[s][a] = f.facets[s][a];
			}
		}
		location = f.location;
	}

	protected void initialize(List<DLocation>[] locations) {
		if (locations[dim].contains(location)) { 
			location = locations[dim].get(locations[dim].indexOf(location));
		}
		else { 
			locations[dim].add(location); 
		}
		if (dim==0) { return; }
		assert dim == location.spat.length;
		assert dim == facets[0].length; 
		for (int s=0;s<2;s++) {
			for (int ai=0;ai<dim;ai++) {
				
				int[] faceSpat = new int[dim-1];
				for (int j=0;j<dim-1;j++) {
					if (j<ai) { faceSpat[j] = location.spat[j]; }
					else     { faceSpat[j] = location.spat[j+1]; }
				}
				int[] faceOrigin = DOp.clone(location.origin);
				faceOrigin[location.spat[ai]] += s;
				DCell f = facets[s][ai]; 
				f.normal =new DSignedAxis(s,location.spat[ai]); 
				f.location = new DLocation(faceOrigin,faceSpat);
				f.initialize(locations);
			}
		}
	}

	protected boolean centrallyFacing(Point eye) {
		return (location.origin[normal.axis]-eye.x[normal.axis])*normal.pmSign()<0;
	}
	
	protected boolean ortographicallyFacing(Point v) {
		assert v.isNormal();
		return v.x[normal.axis]*normal.pmSign() < 0;
	}
	
	@Override
	public Point normal() {
		if (dim()+1==spaceDim()) {
			int[] normals = location.coSpace();
			assert normals.length==1;
			int n = normals[0];
			assert normal.axis == n;
			if (neighbor!=null) {
				assert normal.pos == ! neighbor.normal.pos;
			}
		}
		return normal.direc(spaceDim());
	}

	//didnt get this to work, together with rotate2
	private void originUpdate() {
//		dim = location.spat.length;

//		boolean locationExisting = false;
//		if (locations.contains(location)) { 
//			location = locations.get(locations.indexOf(location));
//			locationExisting = true;
//		}
//		else { 
//			location.faces = new Facet[2*dim];
//			locations.add(location); 
//		}
//		assert(locationExisting);

		if (dim==0) { return; }
		for (int s=0;s<2;s++) {
			for (int ai=0;ai<dim;ai++) {
				
				int[] faceSpat = new int[dim-1];
				for (int j=0;j<dim-1;j++) {
					if (j<ai) { faceSpat[j] = location.spat[j]; }
					else     { faceSpat[j] = location.spat[j+1]; }
				}
				int[] faceOrigin = DOp.clone(location.origin);
				faceOrigin[location.spat[ai]] += s;
				DCell f = facets[s][ai]; 
				f.normal =new DSignedAxis(s,location.spat[ai]); 
//					f.locations = locations;
				f.location.origin = faceOrigin;
				f.location.spat = faceSpat;
				f.originUpdate();

//					if (! locationExisting) {
//						location.faces[2*ai+s].origin = faces[s][ai].location.origin;
//						location.faces[2*ai+s].spat = faces[s][ai].location.spat;
//					}
			}
		}
	}

	public void rotate2(DCenter o,int v,int w) {
		DCenter c = new DCenter(location.origin);
		c.rotate(o,v,w);
		location.origin = c.origin();
		originUpdate();
	}
	
	public void rotate(DCenter o,int v,int w) {
		assert v!=w;
		
		if (normal.axis==v) { normal.axis=w; }
		else if (normal.axis==w) { normal=new DSignedAxis(!normal.pos,v); }

		if (dim==0) { return; }
		for (int s=0;s<2;s++) {
			for (int ai=0;ai<dim;ai++) {
				facets[s][ai].rotate(o,v,w);
				//Dont rotate locations, its separately done on locations
			}
		}
	}
	
//	/** computes the arithmetic center of the facet */
//	public double[] center() {
//		int[] coords = origin();
//		double[] res = new double[dim];
//		for (int i=0;i<coords.length;i++) {
//			res[i] = coords[i];
//		}
//		int[] spat = spat();
//		for (int a=0;a<spat.length;a++) {
//			res[a]+=0.5;
//		}
//		return res;
//	}
//	
	/** computes the vertex v of a facet
	 *  such that v+0.5*spat() is the center of that facet
	 */ 
	public int[] origin() {
		return location.origin;
////		Vector v = new Vector();
//		int[] res = new int[dim];
//		
//		if (parent==null) {
//			for (int a=0;a<dim;a++) {
//				res[a]=faces[0][a].coord;
//			}
//		}
//		else {
//			res = parent.origin();
//			if (sign==1) {
//				res[axis()]+=coord;
//			}
//		}
//		return res;
	}
	
	/** returns the axes which span the facet */
	public int[] spat() {
		return location.spat;
//		int[] res = new int[dim];
//		if (parent==null) {
//			for (int a=0;a<dim;a++) {
//				res[a]=a;
//			}
//			return res;
//		}
//		int[] ps = parent.spat();
//		for (int a=0;a<res.length;a++) {
//			if (a<axisIndex) { res[a] = ps[a]; }
//			else        { res[a] = ps[a+1]; }
//		}
//		return res;
	}
	
	/** returns the axes which are orthogonal to the facet */
	public int[] orth() {
		int[] res = new int[spaceDim()-dim];
		int[] spat = spat();
		for (int a=0,ind=0;a<spaceDim();a++) {
			boolean inSpat = false;
			for (int i=0;i<spat.length;i++) {
				if (spat[i]==a) { inSpat = true; break; }
			}
			if (!inSpat) {
				res[ind]=a;
				ind++;
			}
		}
		return res;
	}
	
	public int[] path() {
		if (parent==null) { return new int[0]; }
		int[] pp = parent.path();
		int[] res = new int[pp.length+1];
		for (int i=0;i<pp.length;i++) {
			res[i] = pp[i];
		}
		res[pp.length]=normal.axis;
		return res;
	}
	
	public int coord() {
		if (normal.axis==-1||parent==null) { return 0; }
		return origin()[normal.axis];
	}
	
	
	public List<Point> vertices() {
		Vector<Point> v = new Vector<Point>();
		if (dim==0) { v.add(Point.create(vertex())); return v; }
		for (int s=0;s<2;s++) {
			v.addAll(facets[s][0].vertices());
		}
		return v;
	}
	
//	public List<Point> vertices(Projection t) {
//		Vector<Point> v = new Vector<Point>();
//		if (dim==0) { v.add(t.proj(vertex())); return v; }
//		for (int s=0;s<2;s++) {
//			v.addAll(faces[s][0].vertices(t));
//		}
//		return v;
//	}
	
//	private Point origin(Projection t) {
//		Point res = Point.create(t.toDim());  
//		t.proj(origin(),res);
//		return res;
//	}
	
	public int spaceDim() {
		if (parent==null) { return dim; }
		return parent.spaceDim();
	}
	
//	private Vector<Point> spat(Projection t) {
//		int[] o = origin();
//		int d = o.length;
//		Vector<Point> v = new Vector<Point>();
//		int[] spat = spat();
//		for (int i=0;i<spat.length;i++) {
//			
//			Point p2 = Point.create(t.toDim());
//			t.proj(o,p2);
//			Point p1 = Point.create(t.toDim());
//			t.proj(DOp.plus(o,DOp.unitVector(i,d)),p1);
//			Point p = Point.create(t.toDim());
//			p = p1.clone().subtract(p2); 
//			v.add(p.clone().multiply(1/p.len()));
//		}
//		return v;
//	}
	
//	public Vector<Point> orth(Projection t) {
//		int[] o = origin();
//		int d = o.length;
//		Vector<Point> v = new Vector<Point>();
//		int[] orth = orth();
//		for (int i=0;i<orth.length;i++) {
//			Point s =
//				t.proj(DOp.plus(o,DOp.unitVector(i,d))).minus(t.proj(o));
////			Iterator spatIt = spat(t).iterator();
////			while (spatIt.hasNext()) {
////				Point dir = (Point) spatIt.next();
////				s = s.minus(dir.proj(Point.create(o)));
////			}
//			v.add(s.times(1/s.len()));
//		}
//		return v;
//	}
	
//	/** 
//	 * computes the axis in toplevel space
//	 * toplevel facet (i.e. parent==null) has no axis
//	 */
//	public int axis() {
////		int[] orth = orth(); 
////		return orth[orth.length-1];
//		if (parent==null) { return axisIndex; }
//		return parent.location.spat[axisIndex];
//	}
	
	/** computes the vertex of an 0 dimensional Facet */
	public int[] vertex() {
		return location.origin;
//		Vector v = new Vector();
//		if ( dim > 0 ) { return faces[0][0].vertex(); } 
//		
//		OFacet f = this;
//		while (f.parent!=null) {
//			v.add(f.axisIndex,new Integer(f.coord));
//			f = f.parent;
//		}
//		
//		int[] res = new int[v.size()];
//		for (int i=0;i<res.length;i++) {
//			res[i] = ((Integer)v.get(i)).intValue();
//		}
//		return res;
	}
	
	public static boolean positionEqual(DCell f1,DCell f2) {
		return f1.location == f2.location;
//		return f1.location.equals(f2.location);

//		if (f1.dim!=f2.dim) { return false; }
//		if (f1.coord!=f2.coord) { return false; }
//		if (f1.dim==0) {
//			return true;
//		}
//		//it should suffice to test this for s=0 only
//		for (int s=0;s<2;s++) {
//			for (int axis=0;axis<f1.dim;axis++) {
//				if (!positionEqual(f1.faces[s][axis],f2.faces[s][axis])) {
//					return false;
//				}
//			}
//		}
//		return true;
	}
		
	public static int signum(int direction ) {
		return (direction % 2)*2 - 1;
	}

	public double faceDistance(double[] eye) {
//		int axis = axis();
		return location.origin[normal.axis]-eye[normal.axis];
	}
	
	public Collection<DCell> getFaces(boolean all) {
		return getDOFaces(dim-1,all);
//		Collection res = new Vector();
//		
//		if (hidden) { return res; }
//		for (int s=0;s<2;s++) for (int a=0;a<dim;a++) {
//			Facet f = faces[s][a]; 
//			if ( f!=null && !f.hidden) { res.add(f); }
//		}
//		return res;
	}
	
	public static Collection<DCell> getFaces(Collection<DCell> v,boolean withInternalFaces) {
		if (v.isEmpty()) { return v; }
		DCell f = v.iterator().next();
		return getFacets(f.dim-1,v,withInternalFaces);
	}
	
	/**
	 * returns all unhidden faces of dimension d
	 * @param d dimension
	 * @return Collection of facets
	 */
	public Collection<DCell> getDOFaces(int d,boolean withInternalFaces) {
		Collection<DCell> res = new Vector<DCell>();
		if (!withInternalFaces && isInternal()) { return res; }
		if (d>dim) { return res; }
		if (d==dim) { 
			res.add(this);
			return res;
		}
		for (int s=0;s<2;s++) for (int a=0;a<dim;a++) {
			DCell f = facets[s][a];
			res.addAll( f.getDOFaces(d,withInternalFaces));
		}
		return res;
	}
	
	
	public static Collection<DCell> getFacets(int d,Collection<DCell> v,boolean all) {
		Collection<DCell> res = new Vector<DCell>();
		for (DCell f : v) {
			res.addAll(f.getDOFaces(d,all));
		}
		return res;
	}

	/* 
	 * returns the facets of all dimensions
	 * untested and unused
	 */
	public Collection<DCell> getAllFacets() {
		Collection<DCell> res = new Vector<DCell>();
		if (dim==0) {
			return res;
		}
		for (int s=0;s<2;s++) for (int a=0;a<dim;a++) {
			DCell f = facets[s][a];
			res.add(f);
			res.addAll(f.getAllFacets());
		}
		return res;
	}
	
//	private class CounterMap extends HashMap<Integer,Integer> {
//		private static final long serialVersionUID = 1177064665843327829L;
//
//		public void inc(int i) {
//			put(i,get(i)+1);
//		}
//		public void dec(int i) {
//			put(i,get(i)-1);
//		}
//		
//		public int get(int i) {
//			Integer index = new Integer(i);
//			Integer val = get(index);
//			if (val == null ) { return 0; }
//			return val.intValue();
//		}
//		
//		public void put(int i,int v) {
//			Integer index = new Integer(i);
//			Integer val = new Integer(v);
//			put(index,val);
//		}
//	}

	public static class FacetVectorByInt extends HashMap<Integer,Vector<DCell>> {
		private static final long serialVersionUID = 3005137881365448845L;

		public void add(int i,DCell f) {
			Vector<DCell> v = get(i);
			if (v == null ) {  
				v = new Vector<DCell>();
				put(i,v);
			}
			v.add(f);
		}
		
		public Vector<DCell> get(int i) {
			Integer index = new Integer(i);
			Vector<DCell> v = get(index);
			return v;
		}
		
		public void put(int i,Vector<DCell> v) {
			Integer index = new Integer(i);
			put(index,v);
		}
	}

	public static boolean pathEqual(DCell a,DCell b) {
		// a facet without parent has no sign and no axis
		//but it has an origin and orthogonals/spat
		if (a.parent==null||b.parent==null) {
			int[] orth = a.orth();
			if (!DOp.setEqual(orth,b.orth())) { return false; }
			int[] a0 = a.origin();
			int[] b0 = b.origin();
			for (int i=0;i<orth.length;i++) {
				if (a0[orth[i]]!=b0[orth[i]]) { return false; }
			}
			return true;
		}
		return 
			a.dim == b.dim &&
			DSignedAxis.equal(a.normal,b.normal) && 
			a.coord()==b.coord() &&
			pathEqual(a.parent,b.parent)
		;
	}
	
	public static boolean opposite(DCell a,DCell b) {
		if (a.normal.axis!=b.normal.axis) { return false; }
		if (!positionEqual(a,b)) { return false; }
		if (a.parent==null) { return false; }
		if (b.parent==null) { return false; }
		boolean oppSign = a.normal.pmSign()*b.normal.pmSign()==-1; 
		if (oppSign) { 
			return pathEqual(a.parent,b.parent); 
		}
		if (!oppSign) {
			return opposite(a.parent,b.parent);
		}
		assert false;
		return true;
	}

//	public static void hideOposites2(OFacet[] facets) {
//		Vector<OFacet> allFacets = new Vector<OFacet>();
//		for (int i=0;i<facets.length;i++) {
//			allFacets.addAll(facets[i].getAllFacets());
//		}
//		for (int i=0;i<allFacets.size();i++) {
//			int testMulti = 0;
//			OFacet last = null;
//			for (int j=i+1;j<allFacets.size();j++) {
//				OFacet a = allFacets.get(i);
//				OFacet b = allFacets.get(j);
//				if (opposite(a,b)) {
//					a.setHidden();
//					b.setHidden();
//					testMulti++;
//					assert testMulti==1 :
//						"d: "+last.dim+"-"+b.dim+"\n"+
//						positionEqual(last,b)+"\n"+
//						(last.sign==b.sign)+"\n"+
//						(last.axis==b.axis)+"\n"+
//						(last.parent==b.parent);
//					last = b;
//					break;
//				}
//			}
//		}
//	}
	/** top-down hides opposite facets
	 * all subfacets of a hidden made facet remain untouched 
	 */
	public static void markInternalFacets(
//			int dim,
			DCell[] facets) {
		int dim = facets[0].dim;
		for (int a=0;a<dim;a++) {
			FacetVectorByInt[] facetsByCoord = { new FacetVectorByInt(), new FacetVectorByInt() };
			for (int s=0;s<2;s++) {
				for (int i=0;i<facets.length;i++) {
					DCell f0 = facets[i];
					DCell fa = f0.facets[s][a];
					
					int coord = f0.coord();
					facetsByCoord[s].add(coord,fa);
					for (int j=i+1;j<facets.length;j++) {
						DCell f1 = facets[j];
						DCell fb = f1.facets[1-s][a];
						if (coord!=f1.coord()) {
							assert !opposite(fa,fb);
							continue;
						}

//						System.out.println("i:"+i+" j:"+j+" s:"+s+" a:"+a);
						if (positionEqual(fa,fb)) {
							assert(opposite(fa,fb));
							fa.neighbor = fb;
//							System.out.println("h:"+fa);
							fb.neighbor = fa;
//							f0.faces[s][a] = null;
//							f1.faces[1-s][a] = null;
							continue;
						}
					}
				}
			}
			for (int s=0;s<2;s++) {
				for (Vector<DCell> v : facetsByCoord[s].values()) {
					int removed = 0;
					for (int i=0;i<v.size();i++) {
						DCell f = v.get(i);
						if (f.isInternal()) { removed++; }
					}
					int n = v.size()-removed;
					if (n<2) { continue; }
					DCell[] subFacets = new DCell[n];
					for (int i=0,k=0;i<v.size();i++) {
						DCell f = v.get(i);
						if (!f.isInternal()) {
							subFacets[k]=f;
							k++;
						}
					}
					markInternalFacets(subFacets);
				}
			}
			
//			for (int s=0;s<2;s++) {
//				OFacet[] subfacets = new OFacet[facets.length-removed];
//				for (int i=0,n=0;i<facets.length;i++) {
//					int sameCoordCount=0;
//					for (int j=i+1;j<facets.length;j++)
//						if (facets[i].coord==facets[j].coord) {
//							sameCoordCount++;
//					}
//					
//					OFacet f = facets[i].faces[s][a];
//					if (
//							f!=null &&
//							!f.hidden
//					) {
//						if (n>=facets.length-removed) {
//							System.out.println("aaahhh!");
//						}
//						subfacets[n]=f;
//						n++;
//					}
//				}
//				hideOposites(
////						dim-1,
//						subfacets);
//			}
		}
	}
	
	public Collection<DLocation> getLocations() {
		Vector<DLocation> v = new Vector<DLocation>();
		for (int s=0;s<2;s++) for (int a=0;a<dim;a++) {
			v.addAll(facets[s][a].getLocations());
		}
		return v;
	}
	
	private void setAllExternal() {
		neighbor = null;
		for (int s=0;s<2;s++) for (int a=0;a<dim;a++) {
			facets[s][a].setAllExternal();
		}
	}
	
	public static void setAllExternal(DCell[] facets) {
		for (int i=0;i<facets.length;i++) {
			facets[i].setAllExternal();
		}
	}
	
	public void translate(DSignedAxis v) {
		location.translate(v);
		for (int s=0;s<2;s++) for (int a=0;a<dim;a++) {
			facets[s][a].translate(v);
		}
	}

	public List<DCell> facets() {
		assert facets != null || dim == 0;
		Vector<DCell> v = new Vector<DCell>();
		if (dim == 0) { return v; } 
		
		for (int i=0;i<2;i++) {
			for (DCell f: facets[i]) { v.add(f);	}
		}
		return v;
	}

	public BCell snappedTo() {
		return neighbor;
	}

	public int dim() {
		return dim;
	}

	@Override
	public Point o() {
		return Point.create(location.origin);
	}

	@Override
	public ALocation location() {
		return location;
	}

	@Override
	public ACell parent() {
		return parent;
	}

	//	public boolean equals(Object o) {
//		Facet f = (Facet) o;
//		return positionEqual(this,f);
//	}
	
//	public GFacet proj(Camera c) {
//		Facet res = new Facet(3,1,0,0,new int[3],null);
//		for (int s=0;s<2;s++) for (int a=0;a<dim;a++) {
//			c.proj(Gop.UNITVECTOR[faces[s][a].axis]);
//		}
//		c.proj(vertex);
//	}
	
//	public Vector<HalfSpace> halfSpaces() {
//		return location.halfSpaces;
//	}
	
	public String toString() {
//		if (dim()==0) {
//			String res = "(";
//			for (int i=0;i<location.origin.length;i++) {
//				res += location.origin[i] + ",";
//			}
//			res += ")";
//			return res;
//		}
//		return facets.toString();
		return DOp.toString(parent.location.origin) + ":" + DOp.toString(location.spat);
	}

	DSpace space() {
		return new DSpace(location);
	}
	
	public void proj3d2dIN(D3Graphics g3,Camera4d camera4d) throws ProjectionException {
		if (dim()==0) {
			location.proj3d2dIN(g3, camera4d);
			return;
		}
		for (int s=0;s<facets.length;s++) for (int i=0;i<facets[s].length;i++) {
			facets[s][i].proj3d2dIN(g3,camera4d);
		}
	}
	
}

