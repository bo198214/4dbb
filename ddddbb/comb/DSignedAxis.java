package ddddbb.comb;

import ddddbb.math.Point;

public class DSignedAxis {
	public static final DSignedAxis PD1 = new DSignedAxis(1);
	public static final DSignedAxis PD2 = new DSignedAxis(2);
	public static final DSignedAxis PD3 = new DSignedAxis(3);
	public static final DSignedAxis PD4 = new DSignedAxis(4);
	public static final DSignedAxis MD1 = new DSignedAxis(-1);
	public static final DSignedAxis MD2 = new DSignedAxis(-2);
	public static final DSignedAxis MD3 = new DSignedAxis(-3);
	public static final DSignedAxis MD4 = new DSignedAxis(-4);
	
	boolean pos;
	int axis; // -1 means diffuse direction
	
	/** 1,2,3,4,... are the positive axes
	 * -1,-2,-3,-4,... are the negative axes
	 * 0 is not allowed as value
	 */
	public DSignedAxis(int i) {
		if (i==0) { pos = true; axis = -1; }
		if (i>0) { pos = true; axis = i-1; }
		if (i<0) { pos = false; axis = -i-1; }
	}
	
	/**
	 * zoSign==1 indicates a positive axis, zoSign==0 negative
	 * _axis is one of 0,1,2,3,4,...
	 */
	public DSignedAxis(int zoSign,int _axis) {
		assert zoSign==0 || zoSign==1 ;
		assert _axis >= -1;
		if (zoSign==0) { pos = false; }
		if (zoSign==1) { pos = true; }
		axis = _axis;
	}
	
	/**
	 * _pos indicates whether the axis is positive
	 * _axis is one of 0,1,2,3,4, ...
	 * @param _axis
	 */
	public DSignedAxis(boolean _pos,int _axis) {
		pos = _pos;
		axis = _axis;
	}
	
	/**
	 * Makes a negative axis from a positive one and vice versa.
	 */
	public void invert() {
		pos = ! pos;
	}
	
	/**
	 * Returns the axis as a number 0,1,2,...
	 */
	public int axis() {
		return axis;
	}

	/** returns 1 for a positive axis and -1 for a negative axis */
	public int pmSign() {
		if (pos) { return +1; }
		return -1;
	}
	/* returns 1 for a positive axis and 0 for a negative axis */ 
	public int zoSign() {
		if (pos) { return 1; }
		return 0;
	}
	/** returns the axis as values 1,2,3,... or -1,-2,-3, ... */
	public int human() {
		if (pos) { return axis+1; }
		return -(axis+1);
	}
	
	public static boolean equal(DSignedAxis v,DSignedAxis w) {
		return (v.pos == w.pos) && (v.axis == w.axis);
	}
	
	public boolean equals(Object a) {
		return equal(this,(DSignedAxis)a);
	}
	
	/** returns the unit vector of this axis in dim dimensions */
	public int[] unitVector(int dim) {
		int[] res = new int[dim];
		for (int i=0;i<dim;i++) { res[i] = 0; }
		if (pos) { res[axis] = 1; }
		else     { res[axis] = -1; }
		return res;
	}
	
	/** returns this axis as Point */
	public Point direc(int dim) {
		double x[] = new double[dim];
		for (int i=0;i<dim;i++) { x[i] = 0; }
		if (pos) { x[axis] = 1; }
		else     { x[axis] = -1; }
		return Point.create(x);
	}

}
