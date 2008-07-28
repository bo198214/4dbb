package ddddbb.math;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;

import ddddbb.game.Opt;

public abstract class BarEyeGraphics extends D3Graphics {

	public static final Composite COMPOSITE = 
		AlphaComposite.getInstance(AlphaComposite.SRC_OVER); 

	/** d is the difference between the real focus and the screen center focus */
	public abstract double sway(double x,double e,double s, double d);
	

	public BarEyeGraphics(D2GraphicsIF _g2,Camera3d c) {
		super(_g2,c,Color.WHITE,Color.WHITE);
		g2.setComposite(COMPOSITE);
	}
	
	/** 
	 * Computes the sinus of the angle in the eye from the focus point (0,d) to point (x,0) 
	 * The (right) eye is assumed at coordinates (e,-s)
	 * In this calculation the focus point may be different from (0,0)
	 * where it would be usually assumed.
	 * Because one can not determine absolute depth in bar-eye representation
	 * this angle information only is needed to compute the views
	 */
	public double xsin2(double x, double e, double s, double d) {
		double ta = (d+s)/e;
		double x2 = x - d/ta;
		double h = x2 * ta / Math.sqrt(1+ta*ta);
		return h/Math.sqrt((e-x)*(e-x)+s*s);
	}
		
	public double xsin(double x,double e,double s,double d) {
		return Math.sin(Math.atan2(s,e-x)-Math.atan2(s+d,e));
	}
//	private double arc(double x0,double x1, double e, double s) {
//		return Math.atan2(s,e-x1)-Math.atan2(s,e-x0);
//	}
	
	public boolean screenProj(Point3d _p, Point2d pl, Point2d pr) {
		assert _p.dim() == 3;
		double e = Opt.eyesDistHalf.getDouble();
		double s = Opt.screenEyeDist.getDouble();
		double d = Opt.barEyeFocusDelta.getDouble();

//		Compound sel = (Compound)Main.scene.compounds.getSelectedItem();
//		Point c = new Point(
//				sel.center[0]+0.5,
//				sel.center[1]+0.5,
//				sel.center[2]+0.5,
//				sel.center[3]+0.5
//		);
//		Point3d c3d = Main.scene.camera.proj(c); 
//		
//		double d = c3d.minus(Main.scene.camera3d.eye).sc(Main.scene.camera3d.v[2]) - s;
//		System.out.println(d);
		boolean res = screenProj(_p,e,s,pl,pr); 
		double xr = pr.x1;
		double xl = pl.x1;

//		pl.x1 = -sway(-xl,e,s);
//		pr.x1 = sway(xr,e,s);

		pl.x1 = -sway(-xl,e,s,d);
		pr.x1 =  sway( xr,e,s,d);
		return res;
	}
	
//	public void drawEyeCross() {
//		double d = 1;
//		double e = Main.opt.eyesDistHalf.getDouble();
//		double s = Main.opt.screenEyeDist.getDouble();
//		Direc3d ld = new Direc3d(e,-d,s);
//		Direc3d rd = new Direc3d(-e,d,s);
//		Point3d p0l = new Point3d(0,-d,0);
//		Point3d p0r = new Point3d(0,d,0);
//		drawLine(p0l.plus(ld.times3d(-d)),p0l.plus(ld.times3d(d)));
//		drawLine(p0r.plus(rd.times3d(-d)),p0r.plus(rd.times3d(d)));
//		drawLine(new Point3d(0,0-d,0),new Point3d(0,0+d,0));
//	}
}
