package ddddbb.game;

import java.util.Vector;

import ddddbb.comb.DCenter;
import ddddbb.comb.DLocation;
import ddddbb.comb.DCell;
import ddddbb.comb.DOp;
import ddddbb.comb.DSignedAxis;

public class Compound {
	//read-only
	public int[][] cubes; //origin of cubes
	public DCenter center; //can be on halfGrid center[i]=2*origin[i]+1
	private DCell.Cube[] topLevelFacets;
	@SuppressWarnings("unchecked")
	private Vector<DLocation>[] allFaces = new Vector[] {
			new Vector<DLocation>(),
			new Vector<DLocation>(),
			new Vector<DLocation>(),
			new Vector<DLocation>(),
			new Vector<DLocation>(),
	};
	
	Compound(int[][] _cubes) {
		cubes = _cubes;
		setBaryCenter();
		createTopLevelFacets();
	}
	
	public void translate(DSignedAxis v) {
		DOp.translate(cubes,v);

		center.translate(v);

		for (int d=0;d<4;d++) for (DLocation f : allFaces[d]) {
			f.translate(v);
		}
	}
	
	public void rotate(int v, int w) {
		for (int i=0;i<cubes.length;i++) {
			DOp.rotateAsCenters(cubes[i],center,v,w);
		}
		
		for (int d=0;d<4;d++) for (DLocation f : allFaces[d]) {
			f.rotate(center,v,w);
		}

		for (int i=0;i<topLevelFacets.length;i++) {
			topLevelFacets[i].rotate(center,v,w);
		}
	}
	
	public void setBaryCenter() {
		center = new DCenter(cubes,true);
		//state changed
	}
	
	public void combine(Vector<Compound> cs) {
		int l = cubes.length;
		for (Compound c:cs) {
			l+=c.cubes.length;
		}
		int[][] res = new int[l][];
		int i;
		for (i=0;i<cubes.length;i++) {
			res[i] = cubes[i];
		}
		for (Compound c:cs) {
			for (int j=0;j<c.cubes.length;j++) {
				res[i] = c.cubes[j];
				i++;
			}
		}
		cubes = res;
		createTopLevelFacets();
		setBaryCenter();
	}

	private void createTopLevelFacets() {
		topLevelFacets = new DCell.Cube[cubes.length];
		allFaces[0].clear();
		allFaces[1].clear();
		allFaces[2].clear();
		allFaces[3].clear();
		allFaces[4].clear();
		for (int i=0;i<cubes.length;i++) {
			topLevelFacets[i] = new DCell.Cube(cubes[i],allFaces);
		}
		setShowGrid(false);
	}

	protected void setShowGrid(boolean showGrid) {
		if (!showGrid) {
			DCell.setAllExternal(topLevelFacets);
			DCell.markInternalFacets(
//					4,
					topLevelFacets);
		}
		else {
			DCell.setAllExternal(topLevelFacets);
		}
	}
	
	public DCell.Cube[] getTopLevelFacets() {
		return topLevelFacets;
	}
	
	public Vector<DLocation>[] getAllFaces() {
		return allFaces;
	}
}
