package ddddbb.math;

public class D4Tupel {
	public int[] x = new int[4];
//	public int x1;
//	public int x2;
//	public int x3;
//	public int x4;
	public D4Tupel(int _x1,int _x2, int _x3, int _x4) {
		x[0] = _x1;
		x[1] = _x2;
		x[2] = _x3;
		x[3] = _x4;
	}
	public D4Tupel(int[] _x) {
		x[0]=_x[0];
		x[1]=_x[1];
		x[2]=_x[2];
		x[3]=_x[3];
	}
	public boolean isEqual(D4Tupel t) {
		if (x[0] == t.x[0] & x[1] == t.x[1] & x[2] == t.x[2] & x[3] == t.x[3]) { return true; }
		return false;
	}
	
	public boolean equals(D4Tupel t) {
		return isEqual(t);
	}
	
	public D4Tupel minus() {
		return new D4Tupel(-x[0],-x[1],-x[2],-x[3]);
	}
	
	public void rotate(int axis1, int axis2) {
		int d1 = 2*x[0]+1, d2=2*x[1]+1, d3=2*x[2]+1, d4=2*x[3]+1;
		int d=0;
		switch (axis1) {
		case 0: 
			switch (axis2) {
			case 1:
				d  = d2;
				d2 = d1;
				break;
			case 2:
				d = d3;
				d3 = d1;
				break;
			case 3:
				d = d4;
				d4 = d1;
				break;
			}
			d1 = -d;
			break;
		case 1:
			switch (axis2) {
			case 0:
				d = d1;
				d1 = d2;
				break;
			case 2:
				d = d3;
				d3 = d2;
				break;
			case 3:
				d = d4;
				d4 = d2;
				break;
			}
			d2 = -d;
			break;
		case 2:
			switch (axis2) {
			case 0:
				d = d1;
				d1 = d3;
				break;
			case 1:
				d = d2;
				d2 = d3;
				break;
			case 3:
				d = d4;
				d4 = d3;
				break;
			}
			d3 = -d;
			break;
		case 3:
			switch (axis2) {
			case 0:
				d = d1;
				d1 = d4;
				break;
			case 1:
				d = d2;
				d2 = d4;
				break;
			case 2:
				d = d3;
				d3 = d4;
				break;
			}
			d4 = -d;
			break;
		}
		x[0] = (d1 - 1)/2;
		x[1] = (d2 - 1)/2;
		x[2] = (d3 - 1)/2;
		x[3] = (d4 - 1)/2;
	}
	
	public void shift(int axis,int dist) {
		switch (axis) {
		case 0: x[0]+=dist; break;
		case 1: x[1]+=dist; break;
		case 2: x[2]+=dist; break;
		case 3: x[3]+=dist; break;
		}
	}
	
	public void trans(D4Tupel t) {
		x[0] += t.x[0];
		x[1] += t.x[1];
		x[2] += t.x[2];
		x[3] += t.x[3];
	}

}
