package sandbox;

public class Final {
	public static class Final2 extends Final {
		final int attr = 2;
		final double x[] = new double[] {1,2,3};
		public int getAttr() {
			return super.attr;
		}
	}
	
	int attr = 0;
	double x[];
	void m(final Final arg) {
		arg.change();
	}
	void change() {
		attr = 1;
	}
	public static void main(String[] args) {
		Final a = new Final();
		Final b = new Final();
		a.m(b);
		System.out.println(b.attr);
		Final2 c = new Final2();
		System.out.println(c.attr);
		System.out.println(c.getAttr());
		c.change();
		System.out.println(c.attr);
		System.out.println(c.getAttr());
		//System.out.println()
	}
}

