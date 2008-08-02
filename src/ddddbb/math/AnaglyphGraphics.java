package ddddbb.math;

import java.awt.Composite;

public class AnaglyphGraphics extends D3Graphics {
	private static final long serialVersionUID = 6933013057972473075L;
	
	public static final Composite COMPOSITE = new AnaglyphComposite();
	
	protected Camera3d c3;
	public AnaglyphGraphics(D2GraphicsIF g,Camera3d _c3) {
		super(g,_c3,AnaglyphCompositeContext.LCOLOR,AnaglyphCompositeContext.RCOLOR);
		c3 = _c3;
		initialize();
	}
	
//	protected AnaglyphGraphics(D2GraphicsIF g,Camera3d c,double _e) {
//		super(g,c,AnaglyphCompositeContext.LCOLOR,AnaglyphCompositeContext.RCOLOR);
//		e = _e;
//		initialize();
//	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		g2.setComposite(COMPOSITE);
//		lColor = new Color(255,0,0,128);
//		rColor = new Color(0,255,255,128);
//		g2.setComposite(AlphaComposite.SrcAtop);
	}

	public boolean screenProj(Point3d p, Point2d pl, Point2d pr) {
		return screenProj(p,c3.eyesDistHalf,c3.screenEyeDist,pl,pr);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
