package ddddbb.math;

import ddddbb.comb.DCell;
import ddddbb.gen.AChangeListener;
import ddddbb.gen.IntModel;

public class D4Graphics {
	protected D3Graphics g3;
//	private double blobRadius = 0.2;
	protected Camera4d c4;
	
	public D4Graphics(D3Graphics _g,final IntModel<Camera4d> perspective) {
		g3 = _g;
		new AChangeListener() {
			public void stateChanged() {
				c4 = perspective.sel();
			}}.addTo(perspective);
	}
	
	/** dot is a center (i.e c=2*o+1) */
	public void drawBlob(Point4d p) {
		Point3d dot3 = new Point3d();
		c4.proj3d(p,dot3);
		g3.drawBlob(dot3);

//		D2Tupel l = new D2Tupel();
//		D2Tupel r = new D2Tupel();
//		proj(dot[0]+0.5,dot[1]+0.5,dot[2]+0.5,dot[3]+0.5,l,r);
//		g.setColor(lColor);
//		g.draw
//		g.setColor(rColor);
		
	}
	
	public void drawMark(Point4d p,double r) {
//		for (int i=0;i<4;i++) {
//			double[] a = new double[4];
//			for (int ix=0;ix<4;ix++) {
//				a[ix]=0;
////				if (((i >> ix) & 1) == 1) {
////					a[ix] -= blobRadius;
////					b[ix] += blobRadius; 
////				}
////				else {
////					a[ix] += blobRadius;
////					b[ix] -= blobRadius;
////				}
//				if (ix==i) {
//					a[ix]=r;
//				}
//			}
//			drawLine(
//					new Point4d(p.x[0]-a[0],p.x[1]-a[1],p.x[2]-a[2],p.x[3]-a[3]),
//					new Point4d(p.x[0]+a[0],p.x[1]+a[1],p.x[2]+a[2],p.x[3]+a[3]));
//		}
		drawBlob(p);
	}
	
	public void drawLine(int[] a,int[] b) {
		drawLine(new Point4d(a),new Point4d(b));
	}

	public void drawLine(DCell l) {
		drawLine(new Point4d(l.facets[0][0].location.origin),new Point4d(l.facets[1][0].location.origin));
	}

	public void drawString(String s,Point4d p4d) {
		assert p4d.dim() == 4;
		Point3d pd3 = new Point3d();
		c4.proj3d(p4d,pd3);
		g3.drawString(s,pd3);
//		D2Tupel l=new D2Tupel(),r=new D2Tupel();
//		proj(x[0],x[1],x[2],x[3],l,r);
//		g.setColor(lColor);
//		g.drawString(s,l.x[0],l.x[1]);
//		g.setColor(rColor);
//		g.drawString(s,r.x[0],r.x[1]);
	}
	
//	public boolean screenProj(Point p, Point2d pl, Point2d pr) {
//		Point3d pd3=Main.scene.camera.proj(p);
//		if (pd3==null) { return false; }
//		return screenProj(pd3,pl,pr);
//	}
//
//	public boolean proj(double x[0],double x2,double x[2],double x[3],D2Tupel l,D2Tupel r) {
//		Point p = new Point(x[0],x[1],x[2],x[3]);
//		Point2d pl=new Point2d(),pr=new Point2d();
//		if (!screenProj(p,pl,pr)) { return false; }
//		l.x[0] = x0+(int)Math.round(pl.x[0]*xcm);
//		l.x[1] = y0-(int)Math.round(pl.x[1]*ycm);
//		r.x[0] = x0+(int)Math.round(pr.x[0]*xcm);
//		r.x[1] = y0-(int)Math.round(pr.x[1]*ycm);
//		return true;
//	}

	public void drawLine(Point4d a,Point4d b) {
//		Point a = new Point(a1,a2,a3,a4);
//		Point b = new Point(b1,b2,b3,b4);
		Point3d a3d = new Point3d();
		c4.proj3d(a,a3d);
		Point3d b3d = new Point3d();
		c4.proj3d(b,b3d);
		g3.drawLine(a3d,b3d);
//		D2Tupel p1l,p1r,p2l,p2r;
//		p1l = new D2Tupel();
//		p1r = new D2Tupel();
//		p2l = new D2Tupel();
//		p2r = new D2Tupel();
//		if (proj(a1,a2,a3,a4,p1l,p1r) & proj(b1,b2,b3,b4,p2l,p2r)) {
//			g.setColor(lColor);
//			g.drawLine(p1l.x[0],p1l.x[1],p2l.x[0],p2l.x[1]);
//			g.setColor(rColor);
//			g.drawLine(p1r.x[0],p1r.x[1],p2r.x[0],p2r.x[1]);		
//		}
	}
	
	public void drawArrow(Point4d a,Point4d b) {
		Point3d a3d = new Point3d();
		c4.proj3d(a,a3d);
		Point3d b3d = new Point3d();
		c4.proj3d(b,b3d);
		g3.drawArrow(a3d,b3d);		
	}
	
	public void drawArrow(Point4d a, Point4d b, String label) {
		Point3d a3d = new Point3d();
		c4.proj3d(a,a3d);
		Point3d b3d = new Point3d();
		c4.proj3d(b,b3d);
		g3.drawArrow(a3d,b3d,label);				
	}
	
	public void drawTetrahedral(Point4d o,double s) {
		Point4d o1,o2,o3,o4;
		o1 = new Point4d(s,0,0,0); o1.add(o);
		o2 = new Point4d(0,s,0,0); o1.add(o);
		o3 = new Point4d(0,0,s,0); o1.add(o);
		o4 = new Point4d(0,0,0,s); o1.add(o);
		drawArrow(o,o1,"x");
		drawArrow(o,o2,"y");
		drawArrow(o,o3,"z");
		drawArrow(o,o4,"w");
	}
	
	public void drawTetrahedral(double s) {
		drawTetrahedral(new Point4d(0,0,0,0),s);
	}
	
	public void drawTetrahedral() {
		drawTetrahedral(4);
	}

	public D3Graphics getGraphics() {
		return g3;
	}

	public void setGraphics(D3Graphics _g3) {
		g3 = _g3;
	}
}
