package ddddbb.math;

import java.util.List;

import ddddbb.comb.Cell;
import ddddbb.comb.OCell;


public class Space2d extends HalfSpace {
//	public Direc3d normal;
//	public double length; // length from (0,0,0) to plane along the normal, can be negative
	// parent is 3d space 
	
	public Space2d(Direc3d _normal, double _length) {
		super(_normal,_length);
	}
	
	public Space2d(Point3d orig, Direc3d _normal) {
		super(orig,_normal);
	}

	public String toString() {
		return "D " + normal.toString() + ";" + length;
	}
//	public Space2d(Direc3d _normal, double _length) {
//		normal = _normal;
//		length = _length;
//	}
//	
	/** s is on the inner side of the plane spanned by a,b,c */
	public Space2d init(Point3d a, Point3d b, Point3d c) {
		normal = Gop.X((Direc3d)b.minus(a).normalize(), (Direc3d)c.minus(a).normalize());
		length = a.sc(normal);
		return this;
	}
	
//	public Space2d(Collection<Facet1d> facets) {
//		assert facets.size() >= 2;
//		int i=0;
//		Direc3d[] d = new Direc3d[2];
//		Point3d a = null;
//		for (Facet1d f1:facets) {
//			if (i>=2) { break; }
//			if (i==0) { a = f1.a; }
//			d[i] = new Direc3d(f1.b.minus(f1.a));
//			i++;
//		}
//		normal = Gop.X(d[0],d[1]);
//		normal.multiply(normal.positivity());
//		length = a.sc(normal);
//	}
	
	public Space2d(List<? extends OCell> facets) {
		assert facets.size() >= 2 : facets.size();
		int i=0;
		Direc3d[] d= new Direc3d[2];
		Point3d a = null;
		for (OCell fc1:facets) {
			Cell f1 = fc1.cell();
			assert f1.location().dim() == 1;
			Point3d f1a,f1b;
			f1a = (Point3d)f1.a().location().o();
			f1b = (Point3d)f1.b().location().o();
			if (i>=2) { break; }
			if (i==0) { a = f1a; }
			d[i] = new Direc3d(f1b.minus(f1a));
			i++;
		}
		normal = Gop.X(d[0],d[1]);
		normal.multiply(normal.positivity());
		length = a.sc(normal);
	}
	
	public void flip() {
		normal.multiply(-1);
		length *= -1;
	}
	
	public Point3d origin() {
		return (new Point3d(normal)).multiply(length);
	}
	public Point3d proj(Point3d p) {
		return (Point3d) (new Point3d(p)).subtract(normal.proj(p.minus(origin())));
	}
	
	public double dist(Point3d p) {
		return  p.minus(proj(p)).len();
	}
	
	public boolean equals(Space2d e) {
		return new Point3d(normal).subtract(e.normal).len() == 0 &&
			     length == e.length;
	}
}
