package ddddbb.math;

import java.util.List;

import ddddbb.comb.Cell;
import ddddbb.comb.OCell;


public class Space2d extends HalfSpace {
//	public Point3d normal;
//	public double length; // length from (0,0,0) to plane along the normal, can be negative
	// parent is 3d space 
	
	public Space2d(Point _normal3d, double _length) {
		super(_normal3d,_length);
	}
	
	public Space2d(Point orig3d, Point3d _normal3d) {
		super(orig3d,_normal3d);
	}

	public String toString() {
		return "D " + normal.toString() + ";" + length;
	}
//	public Space2d(Point3d _normal, double _length) {
//		normal = _normal;
//		length = _length;
//	}
//	
//	/** s is on the inner side of the plane spanned by a,b,c */
//	public Space2d init(Point a, Point b, Point c) {
//		assert a.dim() == 3 && b.dim() == 3 && c.dim() == 3;
//		normal = Gop.X((Point)b.minus(a).normalize(), (Point)c.minus(a).normalize());
//		length = a.sc(normal);
//		return this;
//	}
	
//	public Space2d(Collection<Facet1d> facets) {
//		assert facets.size() >= 2;
//		int i=0;
//		Point3d[] d = new Point3d[2];
//		Point3d a = null;
//		for (Facet1d f1:facets) {
//			if (i>=2) { break; }
//			if (i==0) { a = f1.a; }
//			d[i] = (Point3d)f1.b.minus(f1.a).normalize();
//			i++;
//		}
//		normal = Gop.X(d[0],d[1]);
//		normal.multiply(normal.positivity());
//		length = a.sc(normal);
//	}
	
	public Space2d(List<? extends OCell> facets) {
		assert facets.size() >= 2 : facets.size();
		Point3d[] d= new Point3d[2];
		Point a = null;
		int i=0;
		for (OCell fc1:facets) {
			Cell f1 = fc1.cell();
			assert f1.location().dim() == 1;
			Point3d f1a,f1b;
			f1a = (Point3d)f1.a().location().o().clone();
			f1b = (Point3d)f1.b().location().o().clone();
			if (i>=2) { break; }
			if (i==0) { a = f1a; }
			d[i] = (Point3d)f1b.subtract(f1a).normalize();
			i++;
		}
		normal = AOP.X(d[0],d[1]).normalize();
		normal.multiply(normal.positivity());
		length = a.sc(normal);
	}
	
	public boolean equals(Space2d e) {
		return normal.clone().subtract(e.normal).len() == 0 &&
			     length == e.length;
	}
}
