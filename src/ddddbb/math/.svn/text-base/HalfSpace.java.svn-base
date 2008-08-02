package ddddbb.math;

import java.util.Collection;
import java.util.List;

import ddddbb.comb.ACell;
import ddddbb.comb.ALocation;
import ddddbb.comb.Cell;

public class HalfSpace {
	public Direc normal;
	public double length; // length from (0,0,0) to plane along the normal, can be negative
	// parent is 3d space 
	
	protected HalfSpace() {}
	public HalfSpace(Direc _normal, double _length) {
		normal = _normal;
		length = _length;
	}
	
	public HalfSpace(Point orig, Direc _normal) {
		normal = _normal;
		length = normal.sc(orig);
	}
	
//	/** s is on the inner side of the plane spanned by a,b,c */
//	public Space2d init(Point3d a, Point3d b, Point3d c) {
//		normal = Gop.X((Direc3d)b.minus(a).normalize(), (Direc3d)c.minus(a).normalize());
//		length = a.sc(normal);
//		return this;
//	}
	
	
	public HalfSpace(List<Cell> facets) {
		assert facets.size() >= 2;
		int i=0;
		Direc[] d= new Direc[2];
		Point a = null;
		for (Cell f1:facets) {
			assert f1.location().dim() == 1;
			Point f1a,f1b;
			f1a = f1.a().location().o();
			f1b = f1.b().location().o();
			if (i>=2) { break; }
			if (i==0) { a = f1a; }
			d[i] = new Direc(f1b.minus(f1a));
			i++;
		}
		//TODO normal = Gop.X(d[0],d[1]); //for spacedim 3d
		normal.multiply(normal.positivity());
		length = a.sc(normal);
	}
	
	public void flip() {
		normal.multiply(-1);
		length *= -1;
	}
	
	public Point origin() {
		return (new Point(normal)).multiply(length);
	}
	public Point proj(Point p) {
		return (new Point(p)).subtract(normal.proj(p.minus(origin())));
	}
	
	public double dist(Point p) {
		return  p.minus(proj(p)).len();
	}
	
	/*
	 *  1 outer
	 *  0 on
	 * -1 inner
	 */
	public int side(Point p) {
		assert normal.dim() == origin().dim() : normal.dim() + "!=" + origin().dim();
		assert normal.dim() == p.dim() : normal.dim() + "!=" + p.dim();
		double proj = normal.sc(p.minus(origin())); 
		if ( proj >=  Param.ERR ) { return  1; }
		if ( proj <= -Param.ERR ) { return -1; }
		return 0;
	}
		
	public boolean equals(HalfSpace e) {
		return new Point(normal).subtract(e.normal).len() == 0 &&
			     length == e.length;
	}
	
	/** returns one of Cell.CONTAINED, Cell.INNER, Cell.OUTER, Cell.SPLITTED */
	public int side(ACell c) {
		return side(c,1);
	}
	
	/** returns one of Cell.CONTAINED, Cell.INNER, Cell.OUTER, Cell.SPLITTED */
	public int side(ACell c, int positivity) {
		Collection<ALocation> locations = c.getPointLocations(false);
		int side = Cell.PRISTINE;
		
		for ( ALocation l : locations ) {
			int pointSide = side(l.o())*positivity; //-1 inner, 0 contained, +1 outer  
			if ( pointSide == -1 ) {
				if ( side == Cell.PRISTINE ) { side = Cell.INNER; continue; }
				if ( side == Cell.CONTAINED ) { side = Cell.INNER; continue; }
				if ( side == Cell.OUTER ) { return Cell.SPLITTED; }
				// unchanged for side==INNER
				continue;
			}
			if ( pointSide == +1 ) {
				if ( side == Cell.PRISTINE ) { side = Cell.OUTER; continue; }
				if ( side == Cell.CONTAINED ) { side = Cell.OUTER; continue; }
				if ( side == Cell.INNER) { return Cell.SPLITTED; }
				// unchanged for side==OUTER
				continue;
			}
			if ( pointSide == 0 ) {
				if ( side == Cell.PRISTINE ) { side = Cell.CONTAINED; continue; }
				//unchanged for side == INNER, OUTER, CONTAINED
				continue;
			}
		}
		return side;
		
	}
	
}
