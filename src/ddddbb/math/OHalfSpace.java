package ddddbb.math;

import ddddbb.comb.Cell;

public class OHalfSpace {
	private HalfSpace space;
	private int orientation;
	
	public OHalfSpace(HalfSpace _space,int _orientation) {
		assert _space != null;
		assert _orientation != 0;
		space = _space;
		orientation = _orientation;
	}
	
	/*
	 *  1 outer
	 *  0 on
	 * -1 inner
	 */
	public int outer(Point p) {
		return space.side(p)*orientation;
	}
	
	public int side(Cell c) {
		return space.side(c,orientation);
	}
	
	public double dist(Point p) {
		return space.dist(p);
	}
	
	public Point proj(Point p) {
		return space.proj(p);
	}
	
	public HalfSpace space() {
		return space;
	}
	public int orientation() {
		return orientation;
	}
}
