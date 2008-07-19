package ddddbb.math;

import java.awt.Composite;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ddddbb.game.Opt;

public class AnaglyphGraphics extends D3Graphics {
	private static final long serialVersionUID = 6933013057972473075L;
	
	public static final Composite COMPOSITE = new AnaglyphComposite();
	
	protected double e;

	public AnaglyphGraphics(D2GraphicsIF g,Camera3d c) {
		super(g,c,AnaglyphCompositeContext.LCOLOR,AnaglyphCompositeContext.RCOLOR);
		e = Opt.eyesDistHalf.getDouble();
		Opt.eyesDistHalf.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent ce) {
				e = Opt.eyesDistHalf.getDouble();
			}
			
		});
		initialize();
	}
	
	protected AnaglyphGraphics(D2GraphicsIF g,Camera3d c,double _e) {
		super(g,c,AnaglyphCompositeContext.LCOLOR,AnaglyphCompositeContext.RCOLOR);
		e = _e;
		initialize();
	}

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

	public boolean screenProj(Point p3d, Point2d pl, Point2d pr) {
		double s = Opt.screenEyeDist.getDouble();
		return screenProj(p3d,e,s,pl,pr);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
