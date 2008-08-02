package ddddbb.math;




public class ParallelEyedGraphics extends BarEyeGraphics {
	public ParallelEyedGraphics(D2GraphicsIF g, Camera3d c) { 
		super(g,c);
	}
	
	private static final long serialVersionUID = 1L;

//	private static double sway(double x,double e,double s) {
//		return e+s*x/Math.sqrt(s*s+(e-x)*(e-x));
//	}
	
	public double sway(double x,double e,double s, double d) {
		double sb = xsin(x,e,s,d);
		return e + s*sb/Math.sqrt(1-sb*sb);
	}
	
}
