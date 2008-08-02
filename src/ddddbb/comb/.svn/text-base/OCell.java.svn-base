package ddddbb.comb;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import ddddbb.math.Camera4d;
import ddddbb.math.D3Graphics;
import ddddbb.math.Direc;
import ddddbb.math.HalfSpace;
import ddddbb.math.Param;
import ddddbb.math.Point3d;

public class OCell extends BCell implements Iterable<OCell> {
	protected Cell cell;
	private Cell parent;
	protected int orientation = 0;  // +1 or -1
	private OCell snappedTo = null;
	private boolean snappedToSet = false;
	
	public OCell(Cell _cell, Cell _parent) {
		connectCell(_cell);
		connectParent(_parent);
	}

	public OCell(Cell _cell) {
		connectCell(_cell);
	}

	public OCell(Cell _cell,OCell _template) {
		fillO(_template);
		connectCell(_cell);
	}
	
	protected void connectCell(Cell _cell) {
		cell = _cell;
		cell.ocells.add(this);
		assert cell.ocells.contains(this);
	}
	
	public Cell cell() {
		return cell;
	}
	
	public void connectParent(Cell _parent) {
		parent = _parent;
		parent.facets.add(this);
	}

	protected void disconnect() {
		cell.ocells.remove(this);
		parent.facets.remove(this);
	}
	
	/** Copies only the O relevant details into the current OCell from c */
	private void fillO(OCell c) {
		orientation = c.orientation;
//		snappedTo = c.snappedTo;
//		snappedToSet = c.snappedToSet;
		parent = c.parent;
	}
	
	protected OCell inner;
	protected OCell outer;
	protected int splitCellIs = Cell.PRISTINE;
	
	public boolean isSplitted() {
		return splitCellIs == Cell.SPLITTED;
	}
	public boolean isInnerCut() {
		return splitCellIs == Cell.INNER;
	}
	public boolean isOuter() {
		return splitCellIs == Cell.OUTER;
	}
	public boolean isContained() {
		return splitCellIs == Cell.CONTAINED;
	}
	
	@Override
	public Cell parent() {
		return parent;
	}
	
	public String toString() {
		String snapMark = "";
		if ( snappedTo != null ) {
			snapMark = "*";
		}
		return cell.toString() + snapMark;
	}

	public static <T> boolean setEqual(Collection<T> alist,Collection<T> blist) {
		if (alist.size()!=blist.size()) { return false; }
		for (T a:alist) {
			boolean contained = false;
			for (T b:blist) {
				if (a==b) {
					contained = true;
					break;
				}
			}
			if (!contained) { return false; }
		}
		return true;
	}
	
	public static boolean opposite(OCell a,OCell b) {
		if (a.cell!=b.cell) { return false; }
		return setEqual(a.parent.halfSpaces(),b.parent.halfSpaces());
	}
	
	public OCell snappedTo() {
		if (snappedToSet) { return snappedTo; }
		if (dim()+1>=spaceDim()) { return null; }
		Vector<OCell> candidates = cell.ocells;
		for (OCell c:candidates) {
			if (c!=this) {
				assert parent != null;
				if (parent.halfSpaces()!=null && c.parent.halfSpaces()!=null) 
					if	(setEqual(parent.halfSpaces(),c.parent.halfSpaces())) {
						snapTo(c);
						return c;
					}
			}
		}
		return null;
	}

	@Override
	public List<? extends BCell> facets() {
		return cell.facets;
	}

	@Override
	public Direc normal() {
		return cell.normal();
	}

	@Override
	public ALocation location() {
		return cell.location();
	}

	public static Vector<OCell> d2o(Iterable<DCell> dcells, Camera4d camera4d) {
		Vector<OCell> ocells = new Vector<OCell>();
		for (DCell dc:dcells) {
			initDCopy(dc);
		}
		for (DCell df: dcells) {
			OCell oc = new OCell(df, camera4d);
			ocells.add(oc);
			oc.cell().computeSpacesIN();
		}
		for (OCell oc : ocells) {
			oc.adjustSnapAfterDCopy();
		}
		return ocells;
	}
	
	private OCell(DCell dc, Camera4d c4) {
		boolean proj3d = false;
		if (c4!=null) { proj3d = true; }
		
		if (dc.location()._dst!=null) {
			connectCell(dc.location()._dst);
		}
		else {
			connectCell(new Cell());
			dc.location._dst = cell;

			cell.location = new Location(dc.location, proj3d);
			for (DCell f : dc.facets()) {
				OCell fcell = new OCell(f,c4);
				fcell.connectParent(cell);
			}
		}
		
		src = dc;
		dc.dst = this;		
	}

	private static void initDCopy(DCell dc) {
		dc.location._dst = null;
		dc.dst = null;
		for (DCell dc2:dc.facets()) {
			initDCopy(dc2);
		}		
	}
	
	private void adjustSnapAfterDCopy() {
		if (src.snappedTo()!=null) {
			snappedTo = src.snappedTo().dst;
			snappedToSet = true;
		}
		else {
			snappedTo = null;
			snappedToSet = true;
		}
		for (OCell f : cell.facets) {
			f.adjustSnapAfterDCopy();
		}
	}

	public void breakOpen() {
		if (cell.isSplitted()) {
			inner = new OCell(cell.inner,this);
			outer = new OCell(cell.outer,this);
		}
		if (cell.isInner()) {
			inner = this;
			outer = null;
		}
		if (cell.isOuter()) {
			inner = null;
			outer = this;
		}
		splitCellIs = cell.splitCellIs;
	}
	
	public boolean checkSnap() {
		if (isSplitted()) {
			assert inner.checkSnap();
			assert outer.checkSnap();
			return true;
		}
		//not splitted
		assert !cell().isSplitted() :
			cell;
		if (snappedTo() != null) {
			if (snappedTo().cell!=cell) {
//				snappedTo.fixSnapAfterSplit();
			}
			assert snappedTo().cell == cell || snappedTo().snappedTo().isSplitted():
				snappedTo().cell == cell || snappedTo().snappedTo().isSplitted();
			assert snappedTo().cell == cell || snappedTo().isSplitted();
			assert snappedTo().cell() == cell() || snappedTo().inner.cell == cell || snappedTo().outer.cell == cell;
			assert snappedTo().snappedTo() == this : 
				snappedTo().snappedTo();
			assert !snappedTo().isSplitted() :
				snappedTo();
		}
		for ( OCell f : cell.facets ) {
			assert f.checkSnap();
		}
		return true;
	}
	
	public void snapTo(OCell _dst) {
		snappedTo = _dst;
		_dst.snappedTo = this;
		snappedToSet = true;
		snappedTo.snappedToSet = true;
	}
	
	public void unsnap() {
		snappedToSet = true;
		if (snappedTo!=null) {
			snappedTo.snappedToSet = true;
			snappedTo.snappedTo = null;
			snappedTo = null;
		}
		
	}
	
	public void snapOut() {
		unsnap();
		for (OCell f:cell.facets) {
			f.snapOut();
		}
	}
	
	public void isolate() {
		snapOut();
		if (parent!=null) {
			parent.facets.remove(this);
			parent = null;
		}
		assert cell!=null;
		cell.ocells.remove(this);
		cell = null;
	}
	
	public boolean checkSnapCell() {
		if (isSplitted()) {
			assert inner.checkSnapCell();
			assert outer.checkSnapCell();
			return true;
		}
		if (!cell.isSplitted()) {
			if (snappedTo!=null) {
				assert snappedTo.cell() == cell();
				assert snappedTo.snappedTo == this : 
					snappedTo.snappedTo;
				assert !snappedTo.isSplitted() :
					snappedTo;
			}
		}
		for ( OCell f : cell.facets ) {
			assert f.checkSnapCell();
		}
		return true;
	}

	public static boolean checkSnap(List<OCell> cells) {
		for ( OCell c : cells ) {
			if ( ! c.checkSnap() ) { return false; }
		}
		return true;
	}
	
	/* checks that each facets is contained in at most one parent 
	 */
	@Override
	public boolean checkFaces() {
		for ( BCell f : facets() ) {
			assert f.parent() != null;
			//if (f.parent() == null) { f.parent = this; }
			if ( f.parent() != cell ) { 
				assert false;
			}
		}
		return true;
	}

	public int orientation() {
		return orientation;
	}

	public HashSet<HalfSpace> halfSpaces() {
		return cell.halfSpaces();
	}

	public Iterator<OCell> iterator() {
		return new Iterator<OCell>() {
			public Vector<OCell> facets = cell.facets;
			int pos = 0;
			Iterator<OCell> fIt;
			
			public boolean hasNext() {
				while (pos<facets.size()) {
					fIt = facets.get(pos).iterator();
					if (fIt.hasNext()) { return true; }
					pos = pos+1;
				}
				return false;
			}

			public OCell next() {
				while (true) {
					if(fIt.hasNext()) { return fIt.next(); }
					pos = pos+1;
					if (pos>=facets.size()) { break; }
					fIt = facets.get(pos).iterator();
				}
				return null;
			}

			public void remove() {}
			
		};
	}
	
	public void paint(D3Graphics g3, boolean woInternals) {
		cell.paint(g3,woInternals);
		if (!Param.debug.isSelected()) return;
		if (dim()==2 && spaceDim() == 3) {
			Direc normal = normal();
			normal.multiply(orientation);
			Point3d a = cell.center3d();
			Point3d b = cell.center3d().plus(new Point3d(normal).times(0.25));
			g3.drawLine(a, b);
			g3.drawBlob(b);
			return;
		}
	}
	
}
