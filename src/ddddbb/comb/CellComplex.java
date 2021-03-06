package ddddbb.comb;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Vector;

import ddddbb.math.Camera4d;
import ddddbb.math.OHalfSpace;

public class CellComplex {
	public Vector<Cell> cells;
	
	public CellComplex() {
		cells = new Vector<Cell>();
	}
	
	public CellComplex(Vector<Cell> _cells) {
		cells = _cells;
	}
	
	public CellComplex(Iterable<DCell> dcells, Camera4d camera4d) {
		cells = new Vector<Cell>();
		for (DCell dc:dcells) {
			initDCopy(dc);
		}
		for (DCell df: dcells) {
			//get 3d projection of df
			OCell oc = new OCell(df, true);
			
			cells.add(oc.cell());
			oc.cell().computeSpacesIN();
		}
		for (Cell c : cells) {
			c.referrers.clear();
			for (OCell oc: c.facets)
				oc.adjustSnapAfterDCopy();
		}
	}

	private static void initDCopy(DCell dc) {
		dc.location._dst = null;
		dc.dst = null;
		for (DCell dc2:dc.facets()) {
			initDCopy(dc2);
		}		
	}
	
	public boolean checkSnap() {
		for ( Cell c : cells ) {
			if ( ! c.checkSnap() ) { return false; }
		}
		return true;
	}
	

	public void cutOut(Iterable<OHalfSpace> planes) {
		Vector<Cell> res = new Vector<Cell>();
		Vector<Cell> affected = new Vector<Cell>();
		
		for (Cell c:cells) {
			//if c is outside one of the planes put it into res
			boolean aff = true;
			for (OHalfSpace e:planes) {
				if (e.side(c) == Cell.OUTER) {
					res.add(c);
					aff = false;
					break;
				}
			}
			if (aff) { affected.add(c); }
		}

//		Vector<Cell> dc = new Vector<Cell>();
//		dc.clear();dc.addAll(res);dc.addAll(affected);
//		System.out.println(outsideReferrers(dc).size() + " before splits.");
		Vector<Cell> innerCells = new Vector<Cell>(affected);
		Vector<Cell> innerCells2;
		for (OHalfSpace e:planes) {
			innerCells2 = new Vector<Cell>();
			for (Cell innerCell:innerCells) {
				innerCell.split(e,null);
				if ( innerCell.isSplitted() ) {
					res.add(innerCell.outer);
					innerCells2.add(innerCell.inner);
				}
				if ( innerCell.isInner()) {
					innerCells2.add(innerCell);
				}
				if ( innerCell.isOuter()) {
					res.add(innerCell);
				}
			}
			innerCells = innerCells2;
		}
//		dc.clear();dc.addAll(res);dc.addAll(innerCells);
//		System.out.println(outsideReferrers(dc).size() + " before rm.");
		for (Cell innerCell:innerCells) {
			innerCell.remove();
		}
//		dc.clear();dc.addAll(res);
//		System.out.println(outsideReferrers(dc).size() + " after rm.");
		
		cells = res;
	}
	
	

	public void cutOut(Cell c) {
		assert c.spaceDim() == 3;
		assert c.dim() == 3;
		Vector<OHalfSpace> hyperPlanes = new Vector<OHalfSpace>();
		for (OCell f:c.facets ) {
			OHalfSpace s = new OHalfSpace(f.cell().spaceId,f.orientation);
			hyperPlanes.add(s);
		}
		cutOut(hyperPlanes);
	}

	
	public static CellComplex occlusionCreate(Vector<DCell> dcells, Camera4d camera4d) {
		Comparator<ACell> comp = new ACell.CompareByOcclusion();
		Vector<Cell> cells1 = new CellComplex(dcells,camera4d).cells;
		Vector<Cell> cells2 = new CellComplex(dcells,camera4d).cells;
		Vector<CellComplex> singleComplexes = new Vector<CellComplex>();
		for (Cell c: cells1) {
			CellComplex singleton = new CellComplex();
			singleton.cells.add(c);
			singleComplexes.add(singleton);
		}
		for (int i=0;i<dcells.size();i++) {
			for (int j=0;j<dcells.size();j++) {
				if (i==j) continue; 
				if (comp.compare(dcells.get(i),dcells.get(j)) < 0) {
					singleComplexes.get(i).cutOut(cells2.get(j));
				}
			}
		}
		CellComplex res = new CellComplex();
		for (CellComplex sc : singleComplexes) {
			res.cells.addAll(sc.cells);
		}
		return res;
	}
	/**
	 * Suppose the list of cells is d-dimensional.  
	 * The result is again a list of cells. 
	 */
	public void occlude() {
		CellComplex res = new CellComplex(new Vector<Cell>());
		
		
		for (Cell c : cells) {
			assert c.checkCellrefs();
			assert c.checkPointRefs();
			res.cutOut(c);
			res.cells.add(c);
		}
		assert res.checkSnap();
		cells = res.cells;
	}

	/**
	 * Gets all faces of dimension d from this Complex
	 */
	public HashSet<Cell> getFacesOfDim(int d, boolean withInternalFaces) {
		HashSet<Cell> res = new HashSet<Cell>();
		for (Cell c:cells) {
			for (Cell f:c.getFaces(d, withInternalFaces))
				res.add(f);
		}
		return res;
	}
	
	private static HashSet<OCell> outsideReferrers(Vector<Cell> cells) {
		HashSet<OCell> ocells = new HashSet<OCell>();
		HashSet<Cell> faces = new HashSet<Cell>();
		HashSet<OCell> res = new HashSet<OCell>();
		for (Cell c: cells) {
			faces.addAll(c.getFacesDownTo(0, true));
		}
		for (Cell c: faces) {
			for (OCell o: c.facets) {
				ocells.add(o);
			}
		}
		for (Cell c: faces) {
			for (OCell r: c.referrers) {
				if (! ocells.contains(r)) {
					//System.out.print(r.dim());
					res.add(r);
				}
			}
		}
		return res;
	}
	public HashSet<OCell> outsideReferrers() {
		return outsideReferrers(cells);
	}
}
