package test;

public class PA {

	double[] x;
	
	public PA(int dim) {
		x= new double[dim];
	}
	
	public PA clone() {
		PA res = new PA(x.length);
		for (int i=0;i<x.length;i++) {
			res.x[i] = x[i];
		}
		return res;
	}
	
	public PA plus(PA b) {
		PA res = clone();
		res.add(b);
		return res;
	}
	
	
	public void add(PA b) {
		for (int i=0;i<x.length;i++) {
			x[i] += b.x[i];
		}
		
	}
}
