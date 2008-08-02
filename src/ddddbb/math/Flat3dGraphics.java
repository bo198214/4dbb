package ddddbb.math;



public class Flat3dGraphics extends AnaglyphGraphics {
	private static final long serialVersionUID = 1867231794452687535L;

	public Flat3dGraphics(D2GraphicsIF g, Camera3d c) {
		super(g,c);
		
//		setToolTipText(
//"Flat mode is the most unattractive mode viewing\n\r" +
//"3 dimensional data.");
	}

	@Override
	public boolean screenProj(Point3d p, Point2d pl, Point2d pr) {
		return screenProj(p,0,c3.screenEyeDist,pl,pr);
	}

}
