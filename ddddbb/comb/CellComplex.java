package ddddbb.comb;

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
			OCell oc = new OCell(df, camera4d);
			cells.add(oc.cell());
			oc.cell().computeSpacesIN();
		}
		for (Cell c : cells) {
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
		
		for (Cell innerCell:innerCells) {
			innerCell.remove();
		}
		
		cells = res;
	}
	
	

	public void cutOut(Cell c) {
		assert c.spaceDim() == 3;
		assert c.dim() == 3;
		Vector<OHalfSpace> hyperPlanes = new Vector<OHalfSpace>();
		for (OCell f:c.facets ) {
			OHalfSpace s = new OHalfSpace(f.cell().halfSpace(),f.orientation);
			hyperPlanes.add(s);
		}
		cutOut(hyperPlanes);
	}

	/**
	 * Suppose the list of cells is d-dimensional.  
	 * Paint the cells in the given order into the d-dimensional space.
	 * Later cells overpaint previous cells where they intersect.
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
}
