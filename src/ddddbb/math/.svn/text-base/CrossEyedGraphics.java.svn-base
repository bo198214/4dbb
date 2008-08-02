package ddddbb.math;



public class CrossEyedGraphics extends BarEyeGraphics {
	private static final long serialVersionUID = -1037265581164570519L;

	public CrossEyedGraphics(D2GraphicsIF g,Camera3d c) {
		super(g,c);
	}
	
	public double sway(double x,double e,double s, double d) {
		double sb = xsin(x,e,s,d);
		double cb = Math.sqrt(1-sb*sb);
		double de = 2*e;
		double xx = de-s*(de*cb-s*sb)/( s*cb + de*sb);
		return xx-e;
	}
	
//	public boolean screenProj(Point3d _p3d, Point2d pl, Point2d pr) {
//		double e = Opt.eyesDistHalf.getDouble();
//		double s = Opt.screenEyeDist.getDouble();
//		double d = Opt.barEyeFocusDelta.getDouble();
//		boolean res = screenProj(_p3d,e,s,pl,pr); 
//		double xr = pr.x1;
//		double xl = pl.x1;
//
////		pl.x1 = -sway(-xl,e,s);
////		pr.x1 = sway(xr,e,s);
//
//		pl.x1 = -(-xl - e);// sway(-xl,e,s,d);
//		pr.x1 = sway( xr,e,s,d);
//		return res;
//	}
}
