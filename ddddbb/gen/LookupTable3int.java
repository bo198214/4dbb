package ddddbb.gen;

public class LookupTable3int<T> {
	public int c1,c2,c3;
	Object[][][] data;
	public LookupTable3int(int _c1,int _c2, int _c3) {
		c1=_c1;c2=_c2;c3=_c3;
	}

	public void put(int i1,int i2, int i3, T val) {
		assert i1 < c1 && i2 < c2 & i3 < c3;
		if (data==null) {
			data=new Object[c1][][];
		}
		if (data[i1] == null) {
			data[i1] = new Object[c2][]; 
		}
		if (data[i1][i2] == null) {
			data[i1][i2] = new Object[c3];
		}
		data[i1][i2][i3] = val;
	}
	@SuppressWarnings("unchecked")
	public T get(int i1,int i2, int i3) {
		if (i1 >= c1) { return null; }
		if (i2 >= c2) { return null; }
		if (i3 >= c3) { return null; }
		if (data==null) { return null; }
		if (data[i1]==null) { return null; }
		if (data[i1][i2] == null) { return null;}
		return (T)data[i1][i2][i3];
	}
	public boolean has(int i1, int i2) {
		if (i1>=c1) return false; 
		if (i2>=c2) return false; 
		if (data==null) return false;
		if (data[i1]==null) return false;
		if (data[i1][i2]==null) return false;
		return true;
	}
}
