package ddddbb.gen;

public class LookupTable2int<T extends Object> {
	public int c1,c2;
	T[][] data;
	public LookupTable2int(int _c1,int _c2) {
		c1=_c1;c2=_c2;
	}

	@SuppressWarnings("unchecked")
	public void put(int i1,int i2, T val) {
		assert i1 < c1 && i2 < c2;
		if (data==null) {
			data=(T[][])new Object[c1][];
		}
		if (data[i1] == null) {
			data[i1] = (T[])new Object[c2]; 
		}
		data[i1][i2] = val;
	}
	public T get(int i1,int i2) {
		if (i1 >= c1) { return null; }
		if (i2 >= c2) { return null; }
		if (data==null) {
			return null;
		}
		if (data[i1]==null) {
			return null;
		}
		return data[i1][i2];
	}
}
