package test.d3;

import java.util.List;
import java.util.Vector;

import ddddbb.comb.Cell;
import ddddbb.gen.Model;
import ddddbb.gen.MyChangeListener;
import ddddbb.math.Camera3d;
import ddddbb.math.D3Graphics;
import ddddbb.math.Point;

public class Scene3d extends Model implements MyChangeListener {
	//lists are sorted by distance of the 4d parent cubes from 4d camera
	public List<Cell> top3d = new Vector<Cell>(); //3d
	public List<Cell> top2d = new Vector<Cell>();
	public List<Cell> top1d = new Vector<Cell>();
	public List<Point> top0d;
	
//	private Collection<Facet2d> ofacets2d = new Vector<OFacet2d>();
//	private Collection<Facet1d> ofacets1d = new Vector<OFacet1d>();
//	private Collection<Facet0d> ofacets0d = new Vector<OFacet0d>();

	public Camera3d camera3d = new Camera3d();

	public Scene3d() {
		camera3d.addChangeListener(this);
	}

	protected void reset() {
		top3d = new Vector<Cell>();
		top2d = new Vector<Cell>();
		top1d = new Vector<Cell>();
//		ofacets2d = new Vector<OFacet2d>();
//		ofacets1d = new Vector<OFacet1d>();
//		ofacets0d = new Vector<OFacet0d>();
	}
	
	public void addTop3d(Cell f) {
		top3d.add(f);
//		for (OFacet2d sf2: f.faces) {
//			ofacets2d.add(sf2);
//			for (OFacet1d sf1: sf2.facet.faces) {
//				ofacets1d.add(sf1);
//				/** 0d */
//			}
//		}
	}
	public void addTop2d(Cell f) {
		top2d.add(f);
//		for (Facet1d sf1: f.faces) {
//			ofacets1d.add(sf1);
//		}
	}
	public void addTop1d(Cell f) {
		top1d.add(f);
	}
	public void addTop0d(Point f3d) {
		top0d.add(f3d);
	}
	
//	/* overpaint basicly cutOut with adding the facet
//	 * but it has no duplicate 1d facets
//	 */
//	public void overPaint(Facet3d f) {
//		Collection<Facet3d> affectedSceneTops = new Vector<Facet3d>();
//		Collection<Facet2d> core = new Vector<Facet2d>();
//		
//		/* choose affected 2d facets and 3d facets */
//		for (Facet3d f3: top3d) {
//			boolean affected3d = false;
//			for (Facet2d f2: f3.facets) {
//				boolean affected = true;
//				for (Facet2d ef2: f.facets) {
//					Space2d oPlane = ef2.space;
//					f2.cut(oPlane,false);
//					if (f2.inner == null) {
//						affected = false; break;
//					}
//				}
//				if (affected) {
//					affected3d = true;
//					core.add(f2);
//				}
//			}
//			if (affected3d) {
//				affectedSceneTops.add(f3);
//			}
//		}
//		
//		/* slice affected 2dfacets */
//		for (Facet2d ef2: f.facets) {
//			Space2d oPlane = ef2.space;
//			Collection<Facet2d> core2 = new Vector<Facet2d>();
//			for (Facet2d f2: core) {
//				f2.cut(oPlane,true);
//				if (f2.inner != null) {
//					core2.add(f2.inner);
//				}
//			}
//			core = core2;
//		}
//		
//		/* slice it on the parents */
//		for (Facet3d f3: affectedSceneTops) {
//			List<Facet2d> f2s = new Vector<Facet2d>();
//			f2s.addAll(f3.facets);
//			for (Facet2d f2: f2s) {
//				adoptSlices(f2,f3);
//			}
//		}
//		
//		/* make opposite core visible */
//		for (Facet2d f2: core) {
//			for (Facet1d f1: f2.facets) {
//				f1.innerParent = null; //only interesting for those with outerParent
//				assert f1.outerParent != f2;
//			}
//		}
//		
//		/* remove core */
//		for (Facet3d f3: affectedSceneTops) {
//			List<Facet2d> f2s = new Vector<Facet2d>();
//			f2s.addAll(f3.facets);
//			for (Facet2d f2: f2s) {
//				for (Facet2d cf2: core) {
//					if (cf2 == f2) { f3.facets.remove(f2); }
//				}
//			}
//		}
//		top3d.add(f);
//	}
	
//	private void cutOut(ACell f) {
//		assert f.dim() == 3;
//		/* slice affectedSceneTops */
//		List<ACell> affectedSceneTopsOuter = new Vector<ACell>(); //3d
//		List<ACell> affectedSceneTopsInner = top3d; //3d
//		for (ACell ef2: f.facets()) {
//			List<ACell> astOuter2 = new Vector<ACell>(); //3d
//			List<ACell> astInner2 = new Vector<ACell>(); //3d
//			Space2d oPlane = ef2.space;
//			for (ACell f3: affectedSceneTopsOuter) {
//				f3.split(oPlane);
//				if (f3.isSplitted()) {
//					astOuter2.add(f3.inner);
//					astOuter2.add(f3.outer);
//				}
//				else {
//					astOuter2.add(f3);
//				}
//			}
//			for (ACell f3: affectedSceneTopsInner) {
//				f3.split(oPlane);
//				if (f3.isSplitted()) {
//					astInner2.add(f3.inner);
//					astOuter2.add(f3.outer);
//				}
//				else {
//					if (f3.isInner()) {
//						astInner2.add(f3);
//					}
//					if (f3.isOuter()) {
//						astOuter2.add(f3);
//					}
//				}
//			}
//			for (ACell f3: affectedSceneTopsInner) {
//				f3.adjustSnap();
//			}
//			for (ACell f3: affectedSceneTopsOuter) {
//				f3.adjustSnap();
//			}
//			affectedSceneTopsInner = astInner2;
//			affectedSceneTopsOuter = astOuter2;
//		}
//		
//		/* make opposite core visible */
//		for (ACell f3: affectedSceneTopsInner) {
//			f3.snapOut();
//		}
//		
//		top3d = affectedSceneTopsOuter;
//	}

//	private void paint(D3Graphics g3,Cell f2) {
//		assert f2.dim == 2;
//		final double reduce = 0.2;
//		Point c = f2.center();
//		if (f2.isInternal()) { 
//			return;
//		}
//		for (Cell f1: f2.facets) {
//			if (f1.isInternal() ) {
//				continue; 
//			}
//			Point3d a = new Point3d(f1.facets.get(0).location.origin);
//			Point3d b = new Point3d(f1.facets.get(1).location.origin);
////			Point3d ad = (Point3d)new Point3d(d).subtract(a);
////			Point3d bd = (Point3d)new Point3d(d).subtract(a);
////			a.add(ad.multiply(reduce));
////			b.add(bd.multiply(reduce));
//			Point ac = c.minus(a);
//			Point bc = c.minus(b);
//			a.add(ac.multiply(reduce));
//			b.add(bc.multiply(reduce));
//			
//			
//			g3.drawLine(a,b);
//			a.add(new Point3d(b).subtract(a).times(reduce));
//			b.add(new Point3d(a).subtract(b).times(reduce));
////			g3.drawLine(a,a.plus(c.minus(a).multiply(reduce)));
////			g3.drawLine(b,b.plus(c.minus(b).multiply(reduce)));
//		}
//	}
	
	public static final boolean facetPaint = false;
	public void paint(D3Graphics g3) {
//		for (Facet1d f1:top1d) {
//			g3.drawLine(f1.a,f1.b);			
//		}
//		for (Facet2d f2:top2d) {
//			for (OFacet1d f1:f2.faces) {
//				g3.drawLine(f1.a,f1.b);
//			}
//		}
		if (facetPaint) {
//			for (Cell f3:top3d) {
//				for (Cell f2:f3.facets) {
//					paint(g3,f2);
//				}
//			}
		} else {
			for (Cell f3:top3d) { 
				f3.paint(g3,true);
			}
		}
//		for (OFacet1d f1:ofacets1d) {
//			g3.drawLine(f1.facet.a,f1.facet.b);
//		}
	}
	public void stateChanged() {
		changed();
	}
	public void setToDefault() {
	}


}
