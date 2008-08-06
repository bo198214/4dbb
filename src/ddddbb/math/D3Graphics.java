package ddddbb.math;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import ddddbb.comb.ACell;
import ddddbb.comb.DLocation;
import ddddbb.comb.DCell;
import ddddbb.game.Compound;

public abstract class D3Graphics {
	protected D2GraphicsIF g2;
	protected Color lColor = Color.WHITE;
	protected Color rColor = Color.WHITE;
	protected Color LCOLOR;
	protected Color RCOLOR;
	private double brightness; //between 0 and 1
	private List<DLocation> lines = new Vector<DLocation>();
	private Camera3d c3;
	

	public abstract boolean screenProj(Point3d p,Point2d pl, Point2d pr);
	
	public D3Graphics(D2GraphicsIF _g,Camera3d c,Color _LCOLOR,Color _RCOLOR) {
		g2 = _g;
		c3 = c;
		LCOLOR = _LCOLOR;
		RCOLOR = _RCOLOR;
		setBrightness(0.75);
	}

	public Color setBrightness(Color c) {
		return new Color(
				(int)Math.round(c.getRed()*brightness),
				(int)Math.round(c.getGreen()*brightness),
				(int)Math.round(c.getBlue()*brightness)
		);
	}

	public void setBrightness(double _brightness) {
		brightness = _brightness;
		lColor = setBrightness(LCOLOR);
		rColor = setBrightness(RCOLOR);
	}
	public double getBrightness() {
		return brightness;
	}
	
	public void clear() {
		lines.clear();
	}
	
//	public boolean proj(Point3d p, D2Tupel l, D2Tupel r) {
//		Point2d pl=new Point2d(),pr=new Point2d();
//		if (!screenProj(p,pl,pr)) { return false; }
//		g2.proj(pl,l);
//		g2.proj(pr,r);
//		return true;
//	}
	
	public void drawString(String s, Point3d p) {
		Point2d l=new Point2d(),r=new Point2d();
		screenProj(p,l,r);
		g2.setColor(lColor);
		g2.drawString(s,l);
		g2.setColor(rColor);
		g2.drawString(s,r);		
	}

	public void drawEndString(String s, Point3d p, Point3d from) {
		Point2d l=new Point2d(),r=new Point2d();
		screenProj(p,l,r);
		Point2d lfrom=new Point2d(), rfrom=new Point2d();
		screenProj(from,lfrom,rfrom);
		double x = (l.x1+r.x1-lfrom.x1-rfrom.x1)/2;
		double y = (l.x2+r.x2-lfrom.x2-rfrom.x2)/2;
		if (y>0 && y>Math.abs(x)) {
			g2.setColor(lColor);
			g2.drawStringNorth(s,l);
			g2.setColor(rColor);
			g2.drawStringNorth(s,r);		
		}
		if (y<0 && -y>Math.abs(x)) {
			g2.setColor(lColor);
			g2.drawStringSouth(s,l);
			g2.setColor(rColor);
			g2.drawStringSouth(s,r);		
		}
		if (x>0 && x>Math.abs(y)) {
			g2.setColor(lColor);
			g2.drawStringEast(s,l);
			g2.setColor(rColor);
			g2.drawStringEast(s,r);		
		}
		if (x<0 && -x>Math.abs(y)) {
			g2.setColor(lColor);
			g2.drawStringWest(s,l);
			g2.setColor(rColor);
			g2.drawStringWest(s,r);		
		}
	}
	


	public boolean screenProj(Point3d _p, double e, double s, Point2d pl, Point2d pr) {

		Point3d p = _p.clone();
		p.subtract(c3.eye);
		double x = c3.v[0].sc(p);
		double y = c3.v[1].sc(p);
		double z = c3.v[2].sc(p);
//		pl.x1 = (x*s-z*e)/(z+s); //-e
//		pl.x2 = y * s/(z+s);//0
//		pr.x1 = (x*s+z*e)/(z+s); //+e
//		pr.x2 = pl.x2;
//		return z+s > 0;
		pl.x1 = (x*s-(z-s)*e)/z; //-e
		pl.x2 = y * s/z;//0
		pr.x1 = (x*s+(z-s)*e)/z; //+e
		pr.x2 = pl.x2;
		return z > 0;
	}

	public void drawLine(Point3d a,Point3d b) {
		Point2d pal=new Point2d(),par=new Point2d(),pbl=new Point2d(),pbr=new Point2d();
		if ( screenProj(a,pal,par) && screenProj(b,pbl,pbr) ) {
			g2.setColor(lColor);
			g2.drawLine(pal,pbl);
			g2.setColor(rColor);
			g2.drawLine(par,pbr);
		}
	}
	
	public static final double dx=0.2;
	public static final double dd=0.05;
	public static final Point3d[] xArrowTip = {
		new Point3d(-dx,dd,dd),
		new Point3d(-dx,-dd,dd),
		new Point3d(-dx,-dd,-dd),
		new Point3d(-dx,dd,-dd)
	};
	
	public void drawArrow(Point3d src, Point3d dst) {
		drawLine(src,dst);
		Point3d[] arrowTip = new Point3d[xArrowTip.length];
		Point3d dir = dst.clone();
		dir.subtract(src).normalize();
		for (int i=0;i<xArrowTip.length;i++) {
			arrowTip[i] = xArrowTip[i].clone();
			arrowTip[i].rotate(AOP.D100,dir);
			arrowTip[i].add(dst);
			drawLine(arrowTip[i],dst);
		}
		drawLine(arrowTip[0],arrowTip[1]);
		drawLine(arrowTip[1],arrowTip[2]);
		drawLine(arrowTip[2],arrowTip[3]);
		drawLine(arrowTip[3],arrowTip[0]);
	}
	
	public void drawArrow(Point3d src, Point3d dst, String label) {
		drawArrow(src,dst);
		drawEndString(label, dst, src);
	}
	
	public void drawBlob(Point3d dot3d) {
		Point2d dot2l = new Point2d();
		Point2d dot2r = new Point2d();
		if ( screenProj(dot3d,dot2l,dot2r) ) {
			g2.setColor(lColor);
			g2.drawBlob(dot2l);
			g2.setColor(rColor);
			g2.drawBlob(dot2r);
		}
//		for (int i=0;i<4;i++) {
//			double[] a = new double[4], b= new double[4];
//			for (int ix=0;ix<3;ix++) {
//				a[ix]=dot[ix];
//				b[ix]=a[ix];
//				if (ix==i) {
//					a[ix]-=blobRadius;
//					b[ix]+=blobRadius;
//				}
//			}
//			drawLine(new Point3d(a),new Point3d(b));
//		}
	}
	
	public void drawPoint(Point3d dot3d) {
		double blobRadius = 0.1;
		for (int i=0;i<4;i++) {
			double[] a = new double[3], b= new double[3];
			for (int ix=0;ix<3;ix++) {
				a[ix]=dot3d.x[ix];
				b[ix]=a[ix];
				if (ix==i) {
					a[ix]-=blobRadius;
					b[ix]+=blobRadius;
				}
			}
			drawLine((Point3d) Point.wrap(a),(Point3d) Point.wrap(b));
		}
	}
		
	public void drawLine(ACell a,ACell b) {
		if ( a.location().p2AheadEye && b.location().p2AheadEye ) {
			g2.setColor(lColor);
			g2.drawLine(a.location().p2l,b.location().p2l);
			g2.setColor(rColor);
			g2.drawLine(a.location().p2r,b.location().p2r);			
		}
	}

	public void draw1dFacet(DCell line) {
		if (lines.contains(line.location)) { 
			return;
		}
		drawLine(line.facets[0][0],line.facets[1][0]);
		if (!lines.contains(line.location)) {
			lines.add(line.location);		
		}
	}
	
	/** draws a 3 dimensional facet */
	public void render3dFacet(DCell f3) {
		draw3dFacet(f3);
	}
	
//	public void intersect(Facet line,OFacet f3,Camera4d c) {
//		Point3d[] v = new Point3d[3];
//		Point3d l0 = new Point3d();
//		c.proj(line.faces[0].origin,l0);
//		Point3d l1 = new Point3d();
//		c.proj(line.faces[1].origin,l1);
//		v[0] = new Point3d(l0); v[0].translate(l1,-1);
//		Point3d[] cut = new Point3d[2];
//		int cuti=0;
//		for (int s=0;s<2;s++) for (int a=0;a<3;a++) {
//			OFacet f2 = f3.faces[s][a];
//			Point f2o = f2.origin(c);
//			List spat = f2.spat(c);
//			v[1] = new Point3d(((Point)spat.get(0)).x);
//			v[2] = new Point3d(((Point)spat.get(1)).x);
//			Matrix m = new Matrix(3,3);
//			for (int i=0;i<3;i++) for (int j=0;j<3;j++) {
//				m.set(j,i,v[i].x[j]);
//			}
//			Matrix b = new Matrix(3,1);
//			for (int i=0;i<3;i++) {
//				m.set(i,0,l0.minus(f2o).x[i]);
//			}
//			if (Math.abs(m.det())>Main.opt.ERR) {
//				Matrix r = m.solve(b);
//				Point3d is = new Point3d(l0);
//				is.translate(v[0],r.get(0,0));
//				Point3d f2or = new Point3d(f2o);
//				f2or.translate(v[1],r.get(1,0));
//				f2or.translate(v[2],r.get(2,0));
//				Point3d l0r = new Point3d(l0);
//				l0r.translate(v[0],-r.get(0,0));
//				if(!f2or.equals(l0r)) {
//					System.out.println("wrong computation");
//				}
//				System.out.println(r.get(0,0));
//				m.print(2,3);
//				boolean inF2 = true;
//				for (int s2=0;s2<2;s2++) for (int a2=0;a2<2;a2++) {
//					Point d = f2.faces[s2][a2].orth(c).get(0);
//					Point o = f2.faces[s2][a2].origin(c); 
//					if (is.minus(o).sc(d)< -Main.opt.ERR) { inF2 = false; break; }
//				}
//				
//				boolean inLine = Main.opt.ERR < r.get(0,0) && r.get(0,0) < 1-Main.opt.ERR;
//				if (inLine) { 
//					System.out.println("inline");
//				}
//				if (inF2 && inLine)	{
//					cut[cuti] = is;
//					System.out.println(is.x[0]+" "+is.x[1]+" "+is.x[2]+" "+line+" "+f2);
//					cuti++;
//				}
//			}
//		}
//		if (cuti==1) {
//			//dont know what to do yet
//		}
//		if (cuti>=2) {System.out.println(cuti);}
//	}
	
	public void draw3dFacet(DCell f3) {
		for (DCell f:f3.getFaces(1,false)) {
			if (f.isInternal()) { continue; }
			draw1dFacet(f);
//			drawLine(new PE3d1d(f));
		}
		
	}
	
	public void renderCompound(Compound c) {
		// all visible facets of a compound (non-grid,non-transparent)
		// dont intersect each other
		//
		DCell[] facets4d = c.getTopLevelFacets();
		for (int i=0;i<facets4d.length;i++) {
			DCell facet4d = facets4d[i];
			for (int s=0;s<2;s++) for (int a=0;a<4;a++) {
				DCell face3d = facet4d.facets[s][i];
				if (!face3d.isInternal()) { draw3dFacet(face3d); }
			}
		}
	}
	
	public void drawTrihedral(Point3d o,double s) {
		Point3d o1,o2,o3;
		o1 = new Point3d(s,0,0); o1.add(o);
		o2 = new Point3d(0,s,0); o2.add(o);
		o3 = new Point3d(0,0,s); o3.add(o);
		drawArrow(o,o1,"x");
		drawArrow(o,o2,"y");
		drawArrow(o,o3,"z");
	}
	
	public void drawTrihedral(double s) {
		drawTrihedral(new Point3d(0,0,0),s);
	}
	
	public void drawTrihedral() {
		drawTrihedral(1);
	}

	public D2GraphicsIF getGraphics() {
		return g2;
	}

	public void setGraphics(D2GraphicsIF _g2) {
		g2 = _g2;
	}

}
