package test;

public class PA1 extends PA {
	public PA1() {
		super(1);
	}
	public PA1 clone() {
		PA1 res = new PA1();
		res.x[0] = x[0];
		return res;
	}
	public static void main(String[] args) {
		PA1 a = new PA1();
		a.x[0] = 5;
		PA1 b = new PA1();
		b.x[0] = 3;
		@SuppressWarnings("unused")
		PA1 c = (PA1) a.plus(b);
	}
}
