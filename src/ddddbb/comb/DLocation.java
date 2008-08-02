package ddddbb.comb;

import ddddbb.math.Point;


public class DLocation extends ALocation {
	public int[] origin;
	public int[] spat;
	
	public DLocation(int[] _origin,int[] _spat) {
		origin = DOp.clone(_origin);
		spat = DOp.clone(_spat);
	}
	
	protected DLocation(DLocation dloc) {
		this(dloc.origin,dloc.spat);
	}
	
	public DLocation clone() {
		return new DLocation(origin,spat);
	}
	
	public boolean equals(Object o) {
		DLocation f = (DLocation) o;
		return 
		DOp.vecEqual(origin,f.origin) &&
		DOp.setEqual(spat,f.spat);
	}

	public int hashCode() {
		int d = origin.length;
		
		int spatHash = 1;
		for (int i=0;i<spat.length;i++) {
			spatHash *= spat[i]+1; 
		}
		int oa = 0;
		for (int i=0;i<d;i++) {
			oa += origin[i]*origin[i];
		}
		
		return spatHash + oa;
	}
	
	public String toString() {
		return DOp.toString(origin) + ":" + DOp.toString(spat); 
	}
	
	public int spaceDim() {
		return origin.length;
	}

	public int dim() {
		return spat.length;
	}
	
	public void translate(DSignedAxis v) {
		origin[v.axis]+=v.pmSign();
	}

	/** precondition: v,w positive */
	public void rotate(DCenter o,int v,int w) {
		assert v!=w;
		
//		Center c = new Center(origin);
//		c.rotate(o,v,w);
//		origin = c.origin();
		DOp.rotate(origin,o,v,w);
		for (int i=0;i<spat.length;i++) {
			if (spat[i]==v) { spat[i]=w; }
			else if (spat[i]==w) { origin[v]-=1; spat[i]=v; }
		}
	}

	@Override
	public Point o() {
		return Point.create(origin);
	}
	
	int[] coSpace() {
		int[] res = new int[spaceDim()-dim()];
		int k=0;
		for (int i=0;i<spaceDim();i++) {
			boolean contained = false;
			for (int j=0;j<dim();j++) {
				if (spat[j]==i) contained = true;
			}
			if (!contained) {
				res[k]=i;
				k++;
			}
		}
		assert k==spaceDim()-dim();
		return res;
	}
	
	DSpace space() {
		return new DSpace(this);
	}
	
}
