package ddddbb.comb;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import ddddbb.math.AOP;
import ddddbb.math.HalfSpace;
import ddddbb.math.Point;
import ddddbb.math.Point3d;

/** We want to have the same SpaceId for equal spaces */
public class SpaceId extends HalfSpace {
	static Hashtable<DSpace, SpaceId> dspaceMemo = new Hashtable<DSpace, SpaceId>(); 
	
	HashMap<HalfSpace,SpaceId> memo = new HashMap<HalfSpace,SpaceId>();
	
	//for debuggin purpose only
	Vector<HalfSpace> halfSpaces;
	SpaceId base;
	DSpace dspace;
	
	static SpaceId from(DSpace dspace) {
		SpaceId res;
		if (!dspaceMemo.containsKey(dspace)) {
			res = new SpaceId(dspace);
			dspaceMemo.put(dspace, res);
		}
		else {
			res = dspaceMemo.get(dspace);
		}
		res.spaceComputed = false;
		return res;
	}
	
	private SpaceId(DSpace _dspace) {
		halfSpaces = new Vector<HalfSpace>();
		dspace = _dspace.clone(); 
	}
	
	protected SpaceId() {
		System.out.println("n:" + dspace);
		halfSpaces = new Vector<HalfSpace>();
		base = null;
	}
	private SpaceId(SpaceId b, Vector<HalfSpace> hs) {
		base = b;
		halfSpaces = new Vector<HalfSpace>(hs);
	}
	
	public SpaceId cut(HalfSpace e) {
		if (memo.containsKey(e)) {
			return memo.get(e);
		}
		SpaceId res = new SpaceId(this,halfSpaces);
		res.halfSpaces.add(e);
		
		memo.put(e, res);
		return res;
	}
	
	boolean spaceComputed = false;
	public void setHalfSpace(List<? extends OCell> facets) {
		assert facets.size() >= 2 : facets.size();
		Point3d[] d= new Point3d[2];
		Point a = null;
		int i=0;
		for (OCell fc1:facets) {
			Cell f1 = fc1.cell();
			assert f1.location().dim() == 1;
			Point3d f1a,f1b;
			f1a = (Point3d)f1.a().location().o().clone();
			f1b = (Point3d)f1.b().location().o().clone();
			if (i>=2) { break; }
			if (i==0) { a = f1a; }
			d[i] = (Point3d)f1b.subtract(f1a).normalize();
			i++;
		}
		normal = AOP.X(d[0],d[1]).normalize();
		normal.multiply(normal.positivity());
		length = a.sc(normal);
		spaceComputed = true;
	}
	
	public static void main(String[] args) {
		DLocation a,b,c;
		a = new DLocation(new int[] {0,0,0,0}, new int[] {1,2,3});
		b = new DLocation(new int[] {0,0,0,0}, new int[] {3,1,2});
		c = new DLocation(new int[] {0,2,1,1}, new int[] {1,2,2});
		assert a.equals(b);
		assert ! a.equals(c);
		assert a.hashCode() == b.hashCode() : a.hashCode() + "," + b.hashCode();
		assert a.hashCode() == c.hashCode() : a.hashCode() + "," + c.hashCode();
		Hashtable<DLocation,SpaceId> ht = new Hashtable<DLocation, SpaceId>();
		ht.put(a, new SpaceId());
		assert ht.containsKey(b);
		assert ! ht.containsKey(c);
		
		HashMap<DLocation,SpaceId> hm = new HashMap<DLocation, SpaceId>();
		hm.put(a,new SpaceId());
		assert hm.containsKey(b);
		assert ! hm.containsKey(c);
	}
}
