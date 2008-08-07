package ddddbb.game;

import java.util.Vector;

import ddddbb.comb.DCenter;
import ddddbb.comb.DLocation;
import ddddbb.comb.DCell;
import ddddbb.comb.DOp;
import ddddbb.comb.DSignedAxis;
import ddddbb.math.Point;

public class Compound {
	//read-only
	public int[][] locations; //origin of cubes
	private DCenter center; //can be on halfGrid center[i]=2*origin[i]+1
	private DCell.Cube[] topLevelFacets;
	@SuppressWarnings("unchecked")
	private Vector<DLocation>[] allFaces = new Vector[] {
			new Vector<DLocation>(),
			new Vector<DLocation>(),
			new Vector<DLocation>(),
			new Vector<DLocation>(),
			new Vector<DLocation>(),
	};
	
	Compound(int[][] locations) {
		this.locations = locations;
		setBaryCenter();
		createTopLevelFacets();
	}
	
	public void translate(DSignedAxis v) {
		DOp.translate(locations,v);

		center.translate(v);

		for (int d=0;d<=4;d++) for (DLocation f : allFaces[d]) {
			f.translate(v);
		}
	}
	
	public void rotate(int v, int w) {
		for (int i=0;i<locations.length;i++) {
			DOp.rotateAsCenters(locations[i],center,v,w);
		}
		
		for (int d=0;d<=4;d++) for (DLocation f : allFaces[d]) {
			f.rotate(center,v,w);
		}

		for (int i=0;i<topLevelFacets.length;i++) {
			topLevelFacets[i].rotate(center,v,w);
		}
	}
	
	protected void setBaryCenter() {
		center = new DCenter(locations,true);
	}
	
	public void combine(Vector<Compound> cs) {
		int l = locations.length;
		for (Compound c:cs) {
			l+=c.locations.length;
		}
		int[][] res = new int[l][];
		int i;
		for (i=0;i<locations.length;i++) {
			res[i] = locations[i];
		}
		for (Compound c:cs) {
			for (int j=0;j<c.locations.length;j++) {
				res[i] = c.locations[j];
				i++;
			}
		}
		locations = res;
		createTopLevelFacets();
		setBaryCenter();
	}

	private void createTopLevelFacets() {
		topLevelFacets = new DCell.Cube[locations.length];
		allFaces[0].clear();
		allFaces[1].clear();
		allFaces[2].clear();
		allFaces[3].clear();
		allFaces[4].clear();
		for (int i=0;i<locations.length;i++) {
			topLevelFacets[i] = new DCell.Cube(locations[i],allFaces);
		}
		setShowGrid(false);
	}

	protected void setShowGrid(boolean showGrid) {
		DCell.setAllExternal(topLevelFacets);
		if (!showGrid) {
			DCell.markInternalFacets(
//					4,
					topLevelFacets);
		}
	}
	
	public DCell.Cube[] getTopLevelFacets() {
		return topLevelFacets;
	}
	
	public Vector<DLocation>[] getAllFaces() {
		return allFaces;
	}
	
	public Point center() {
//		Point res = Point.create(cubes[0]);
//		for (int i=1;i<cubes.length;i++) {
//			res.add(new DCenter(cubes[i]).loc());
//		}
//		res.multiply(1.0/cubes.length);
//		return res;
		return center.loc();
	}
	public int[] centerOrigin() {
		return center.origin();
	}
	
	public int[][] locations() {
		return locations;
	}
}
