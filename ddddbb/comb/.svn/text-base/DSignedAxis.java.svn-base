package ddddbb.comb;

import ddddbb.math.Direc;

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
	
	public DSignedAxis(int i) {
		if (i==0) { pos = true; axis = -1; }
		if (i>0) { pos = true; axis = i-1; }
		if (i<0) { pos = false; axis = -i-1; }
	}
	
	public DSignedAxis(int zoSign,int _axis) {
		assert zoSign==0 || zoSign==1 ;
		assert _axis >= -1;
		if (zoSign==0) { pos = false; }
		if (zoSign==1) { pos = true; }
		axis = _axis;
	}
	
	public DSignedAxis(boolean _pos,int _axis) {
		pos = _pos;
		axis = _axis;
	}
	
	public void invert() {
		pos = ! pos;
	}
	public int axis() {
		return axis;
	}
	public int pmSign() {
		if (pos) { return +1; }
		return -1;
	}
	public int zoSign() {
		if (pos) { return 1; }
		return 0;
	}
	public int human() {
		if (pos) { return axis+1; }
		return -(axis+1);
	}
	public static boolean equal(DSignedAxis v,DSignedAxis w) {
		return (v.pos == w.pos) && (v.axis == w.axis);
	}
	public int[] unitVector(int dim) {
		int[] res = new int[dim];
		for (int i=0;i<dim;i++) { res[i] = 0; }
		if (pos) { res[axis] = 1; }
		else     { res[axis] = -1; }
		return res;
	}
	
	public Direc direc(int dim) {
		double x[] = new double[dim];
		for (int i=0;i<dim;i++) { x[i] = 0; }
		if (pos) { x[axis] = 1; }
		else     { x[axis] = -1; }
		return new Direc(x);
	}

}
