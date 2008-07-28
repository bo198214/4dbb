package ddddbb.comb;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import ddddbb.math.Camera4d;
import ddddbb.math.D3Graphics;
import ddddbb.math.HalfSpace;
import ddddbb.math.Param;
import ddddbb.math.Point;
import ddddbb.math.Point3d;

public class OCell extends BCell implements Iterable<OCell> {
	protected Cell cell;
	protected Cell parent;
	protected int orientation = 0;  // +1 or -1

	private boolean outsnapped = false;

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
		cell.referrers.add(this);
		assert cell.referrers.contains(this);
	}
	
	public void nullifyParent() {
		parent = null;
	}
	
	public Cell cell() {
		return cell;
	}
	
	public void nullifyCell() {
		cell = null;
	}
	
	public void connectParent(Cell _parent) {
		parent = _parent;
		parent.facets.add(this);
	}

	/** Copies only the O relevant details into the current OCell from c */
	private void fillO(OCell c) {
		orientation = c.orientation;
		outsnapped = c.outsnapped;
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
		if ( outsnapped ) {
			snapMark = "x";
		}
		return super.toString() + snapMark;
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
	
	public static boolean adjacent(OCell a,OCell b) {
		return a.cell == b.cell;
	}
	
	/**
	 * Two OCells are opposite if they are not equal,
	 * reference the same Cell
	 * (i.e. occupy the same location)
	 * and their parent Cells lie in the same space (same SpaceId), 
	 * but dont occupy the same location.
	 * In this case they are not visible per default.
	 */
	public static boolean opposite(OCell a, OCell b) {
		if (a==b) return false;
		if (!adjacent(a,b)) return false;
		if (a.parent==b.parent) return false;
		return  a.parent.spaceId == b.parent.spaceId;
	}
		
	public OCell opposite() {
		if (dim()>=spaceDim()) { return null; }
		HashSet<OCell> candidates = cell.referrers;
		for (OCell c:candidates) {
			if (c==this) continue;
			assert parent != null;
			assert c.parent != null;
			if (OCell.opposite(this,c)) 
				return c;
		}
		return null;
	}

	public OCell snappedTo() {
		if (outsnapped) {
			return null;
		}
		return opposite();
	}
	
	@Override
	public List<? extends BCell> facets() {
		return cell.facets;
	}

	@Override
	public Point normal() {
		assert cell.normal().isNormal();
		return cell.normal();
	}

	@Override
	public ALocation location() {
		return cell.location();
	}

	OCell(DCell dc, boolean proj3d) {
		if (dc.location()._dst!=null) {
			connectCell(dc.location()._dst);
		}
		else {
			connectCell(new Cell());
			dc.location._dst = cell;

			cell.location = new Location(dc.location, proj3d);
			for (DCell f : dc.facets()) {
				OCell fcell = new OCell(f,proj3d);
				fcell.connectParent(cell);
			}
		}
		cell.spaceId = SpaceId.from(dc.space());
		
		src = dc;
		dc.dst = this;		
	}

	void adjustSnapAfterDCopy() {
		if (src.neighbor!=null) {
			assert src.neighbor != null;
			if (src.neighbor.dst==null) {}
			else {
				assert src.neighbor.dst != null;
				assert cell.equals(src.neighbor.dst.cell) : 
					cell + "," + src.neighbor.dst.cell;
				assert cell == src.neighbor.dst.cell ;
				assert src.location.equals(src.neighbor.location);
				assert src.location == src.neighbor.location;
				assert src.parent.space().equals(src.neighbor.parent.space());
				assert parent.spaceId == src.neighbor.dst.parent.spaceId :  
					"\n" + (new DSpace(parent.spaceId.dspace)) + "\n" + (new DSpace(src.neighbor.dst.parent.spaceId.dspace));
				assert OCell.opposite(src.snappedTo().dst,this);
				assert snappedTo() == src.snappedTo().dst : src.snappedTo() + "\n" + src.snappedTo().dst + "\n" + this;
			}
		}
		else {
			//src.snappedTo() == null
			if ( snappedTo() != null ) {
				//System.out.println("outsnapped adjusted after copy");
				outsnapped = true;
				opposite().outsnapped = true;
			}
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
		OCell snappedTo = snappedTo();
		if (snappedTo != null) {
			if (snappedTo.cell!=cell) {
//				snappedTo.fixSnapAfterSplit();
			}
			assert snappedTo.cell == cell || snappedTo.snappedTo().isSplitted():
				snappedTo.cell == cell || snappedTo.snappedTo().isSplitted();
			assert snappedTo.cell == cell || snappedTo.isSplitted();
			assert snappedTo.cell() == cell() || snappedTo.inner.cell == cell || snappedTo.outer.cell == cell;
			if (snappedTo.snappedTo()!=this) {
				snappedTo.outsnapped = false;
				assert snappedTo.snappedTo() == this : 
					snappedTo.snappedTo();
				snappedTo.outsnapped = true;
			}
			assert opposite().opposite() == this :
				opposite().opposite();
			assert snappedTo.snappedTo() == this : 
				snappedTo.snappedTo();
			assert !snappedTo.isSplitted() :
				snappedTo;
		}
		for ( OCell f : cell.facets ) {
			assert f.checkSnap();
		}
		return true;
	}
	
//	private void snapTo(OCell _dst) {
//		snappedTo = _dst;
//		_dst.snappedTo = this;
//		snappedToSet = true;
//		snappedTo.snappedToSet = true;
//	}
	
	public void unsnap() {
		outsnapped = true;
		OCell snappedTo = opposite(); 
		if (snappedTo!=null) {
			snappedTo.outsnapped = true;
		}
	}
	
	public boolean isOutSnapped() {
		return outsnapped;
	}
	
	public void snapOut() {
		unsnap();
		for (OCell f:cell.facets) {
			f.snapOut();
		}
	}
	
	public boolean checkSnapCell() {
		if (isSplitted()) {
			assert inner.checkSnapCell();
			assert outer.checkSnapCell();
			return true;
		}
		if (!cell.isSplitted()) {
			OCell snappedTo = snappedTo();
			if (snappedTo!=null) {
				assert snappedTo.cell() == cell();
				assert snappedTo.snappedTo() == this : 
					snappedTo.snappedTo();
				assert !snappedTo.isSplitted() :
					snappedTo;
			}
		}
		for ( OCell f : cell.facets ) {
			assert f.checkSnapCell();
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
	
	/**
	 * An OCell is internal if
	 * a) it's parent is internal
	 * b) it's parent is non-internal and its snappedTo something with non-internal parent 
	 */
	boolean isInternal() {
		if (parent()!=null && parent().isInternal()) return true;
		//(has no parent or) is visible
		OCell snappedTo = snappedTo();
		if (snappedTo==null) return false;
		if (snappedTo.parent().isInternal()) return false; 
		if (snappedTo.snappedTo()!=this) {
			snappedTo.outsnapped = false;
			assert snappedTo.snappedTo() == this : 
				snappedTo.snappedTo();
			snappedTo.outsnapped = true;
		}
		assert snappedTo.snappedTo() == this : 
			snappedTo.snappedTo();
		return true;
	}
	
	public void paint(D3Graphics g3, boolean woInternals) {
		cell.paint(g3,woInternals);
		if (!Param.debug.isSelected()) return;
		if (dim()==2 && spaceDim() == 3) {
			Point normal = normal();
			normal.multiply(orientation);
			Point3d a = cell.center3d();
			Point3d b = cell.center3d();
			b.add(normal.multiply(0.25));
			g3.drawLine(a, b);
			g3.drawBlob(b);
			return;
		}
	}

}
