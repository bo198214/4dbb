package ddddbb.game;

import java.util.List;
import java.util.Vector;

import ddddbb.comb.ACell;
import ddddbb.comb.Cell;
import ddddbb.comb.CellComplex;
import ddddbb.comb.DCell;
import ddddbb.comb.DLocation;
import ddddbb.comb.DOp;
import ddddbb.comb.DSignedAxis;
import ddddbb.game.Opt.GameStatus;
import ddddbb.gen.BoolModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.Model;
import ddddbb.gen.MyChangeListener;
import ddddbb.gen.SelectedListModel;
import ddddbb.math.Camera4dParallel;
import ddddbb.math.Camera3d;
import ddddbb.math.Camera4d;
import ddddbb.math.D3Graphics;
import ddddbb.math.D4Tupel;
import ddddbb.math.Param;
import ddddbb.math.Point;
import ddddbb.math.Point2d;
import ddddbb.math.Point3d;
import ddddbb.math.Point4d;
import ddddbb.math.Camera4d.ProjectionException;
import ddddbb.math.Param.Occlusion4dAllowance;
import ddddbb.sound.Sound;

public class Scene extends Model implements MyChangeListener {
	public final IntModel<GameStatus> gameStatus;
	//	public Vector<Facet> allFacets = new Vector<Facet>();
	public String name;
	public Compound goal;

	public SelectedListModel<Compound> compounds = new SelectedListModel<Compound>();
	public Camera4d camera4d; //default
	public Camera3d camera3d = new Camera3d();
	
	public D4Tupel cursor = new D4Tupel(0,0,0,0);
	public BoolModel showGoal;

	private void setCompounds(int[][][] cs) {
		compounds.clear();
		for (int i=0;i<cs.length;i++) {
			compounds.add(new Compound(DOp.clone(cs[i])));
		}
		updateFaces3d(compounds);
	}
	
	public void setToDefault() {
		//init(0);
	}
	
	public Scene(IntModel<GameStatus> _gameStatus,BoolModel _showGoal) {
		super();
		gameStatus = _gameStatus;
		showGoal = _showGoal;
		updateCamera();
		showGoal.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				if (showGoal.isSelected()) {
					Sound.SHOWGOAL.play();
				}
			}});
		Param.perspective.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				updateCamera();
			}
		});
		Param.occlusion4dAllowance.addChangeListener(recomputeFacing);
//		Param.perspectiveAxis.addChangeListener(new MyChangeListener() {
//			public void stateChanged() {
//				camera4d.setDirec(Param.perspectiveAxis.getSelectedObject());
//				changed();
//			}});
		Param.showInternalFaces.addChangeListener(new MyChangeListener(){
			public void stateChanged() {
				updateCompoundGrid();
			}});
		Opt.zoom.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				camera4d.setZoom(
						((Double)Opt.zoom.getValue()).doubleValue());
			}
		});

	}

	private void propagateGameStatus() {
		if (goal == null) { 
			gameStatus.setSelectedObject(GameStatus.NONE);
			return;
		}
		if (compounds.size() == 1) {
			if (DOp.motionEqual(goal.cubes,compounds.getSelectedItem().cubes)) {
				gameStatus.setSelectedObject(GameStatus.REACHED);
			} 
			else {
				gameStatus.setSelectedObject(GameStatus.MISSED);
			}
			return;
		}
		if (!DOp.motionContained(compounds.getSelectedItem().cubes,goal.cubes)) {
			gameStatus.setSelectedObject(GameStatus.MISSED);
			return;
		}
		if (gameStatus.getSelectedObject() != GameStatus.PENDING ) {
			gameStatus.setSelectedObject(GameStatus.PENDING);
		}
	}
	
	public void updateCompoundGrid() {
		for (Compound compound: compounds) {
			compound.setShowGrid(Param.showInternalFaces.isSelected());
		}		
	}
	protected void updateCamera() {
		if (camera4d!=null) { 
			camera4d.removeChangeListener(this);
			camera4d.removeChangeListener(recomputeFacing);
		}
		camera4d = Param.perspective.getSelectedObject();
		camera4d.addChangeListener(this);
		camera4d.addChangeListener(recomputeFacing);
		changed();		
	}
	
	private void setGoal(int[][] c) {
		goal = new Compound(c);
	}
	
	public void changeObjective(Objectives objective) {
		setGoal(objective.goal);
		setCompounds(objective.compounds);
		updateVisibility();
		propagateGameStatus();
	}
	
//	public Compound getSelected() {
//		return (Compound)compounds.getSelectedObject();
//	}
	
	public void addChangeListener(MyChangeListener l) {
		super.addChangeListener(l);
		compounds.addChangeListener(l);
		camera3d.addChangeListener(l);
		showGoal.addChangeListener(l);
	}
	
//	public boolean move(D4Tupel t) {
//		return move(t.x1,t.x2,t.x3,t.x4);
//	}
	
	public boolean transSelected(DSignedAxis v) {
		if ( compounds.getSelected() == -1 ) { return false; }
		Compound compound = compounds.getSelectedItem(); 
		compound.translate(v);
		if (isOverlapping()) {
			compound.translate(new DSignedAxis(-v.human()));
			Sound.BARRINGCOMPOUND.play();
			return false;
		}
		changed();
		Sound.MOVINGCOMPOUND.play();
		return true;
	}
	
	private void updateVisibility() {
		for (DCell of: faces3d) {
			of.setFacing(camera4d);
		}		
	}
	
	public boolean rotateSelected(int v, int w) {
		if ( compounds.getSelected() == -1 ) { return false; }
		Compound compound = compounds.getSelectedItem();
		compound.rotate(v,w);
		if (isOverlapping()) { 
			compound.rotate(w,v);
			Sound.BARRINGCOMPOUND.play();
			return false;
		}
		if (camera4d instanceof Camera4dParallel) {
			//for other cases visibility is set in paint method
			//for programming lazyness set new visibility of *all* compounds
			updateVisibility();
		}
		changed();
		Sound.ROTATINGCOMPOUND.play();
		return true;
	}
	
	private boolean isOverlapping() {
		int[][][] cs = new int[compounds.size()][][];
		for (int i=0;i<cs.length;i++) {
			cs[i]=compounds.get(i).cubes;
		}
		return DOp.intersecting(cs);
	}
	
	private Vector<Compound> combinables(Compound c0) {
		Vector<Compound> res = new Vector<Compound>();
		for (Compound c : compounds) {
			if (c0!=c && DOp.d3adjacent(c.cubes,c0.cubes)) {
				res.add(c);
			}
		}
		return res;
	}
	
	private void combine(Compound c0,Vector<Compound> cs) {
		c0.combine(cs);
		for (Compound c : cs) {
			compounds.remove(c);
		}
		updateFaces3d(compounds);
		updateVisibility();
	}
	
	public void combine() {
		Compound c0 = compounds.getSelectedItem();
		Vector<Compound> bordering = combinables(c0); 
		combine(c0,bordering);
		changed();
		if (
			compounds.size() != 1 &&
			bordering.size() > 0
		) {
			Sound.SNAPPINGCOMPOUNDS.play2end();
		}
		propagateGameStatus();
	}
	
	public static final Point4d[][] unitVector4d = new Point4d[][] {
		new Point4d[] {
				new Point4d(1,0,0,0),
				new Point4d(0,1,0,0),
				new Point4d(0,0,1,0),
				new Point4d(0,0,0,1)
		},
		new Point4d[] {
				new Point4d( -1, 0, 0, 0 ),
				new Point4d( 0, -1, 0, 0 ),
				new Point4d( 0, 0, -1, 0 ),
				new Point4d( 0, 0, 0, -1 )
		}
	};
	
	protected List<DCell> faces3d;
	
	private void updateFaces3d(List<Compound> cs) {
		faces3d = new Vector<DCell>(); 
		for (Compound c : cs) {
			DCell[] facets4d = c.getTopLevelFacets();
			for (int i=0;i<facets4d.length;i++) {
				faces3d.addAll(facets4d[i].getFaces(false));
			}
		}		
	}

	private final MyChangeListener recomputeFacing = new MyChangeListener() {
		public void stateChanged() {
			if (faces3d==null) return;
			if (Param.occlusion4dAllowance.getInt()>=Param.Occlusion4dAllowance.BACKFACE.ordinal()) {
				for (DCell of : faces3d) {
					of.setFacing(camera4d);
				}								
			}
			changed();
		}
	};
	
	private void paintScene(D3Graphics g3,List<Compound> cs) {
		g3.clear();
		//not in front faces:
		
		List<DCell> ffaces3d;
		try {
			for (DCell dc : faces3d) dc.proj3d2dIN(g3,camera4d);
			ffaces3d = faces3d;
		}
		catch (ProjectionException e) {
			ffaces3d = new Vector<DCell>();
			for (DCell dc : faces3d) {
				try {
					dc.proj3d2dIN(g3,camera4d);
					ffaces3d.add(dc);
				} catch (ProjectionException ee) {}
			}			
		}
//		for (Compound c: cs) for (DLocation v : c.getAllFaces()[0]) {
//			v.proj3d2dIN(g3,camera4d);
//		}

		Occlusion4dAllowance oa = Param.occlusion4dAllowance.getSelectedObject();
		
		if (oa == Param.Occlusion4dAllowance.NONE) {
			for (DCell df3 : ffaces3d) g3.render3dFacet(df3);
		}
		else if (oa==Occlusion4dAllowance.BACKFACE) {
			for (DCell df3 : ffaces3d) {
				if (df3.facing) g3.render3dFacet(df3);
			}
		}
		else if (oa==Occlusion4dAllowance.COMPLETE) {
			Vector<DCell> dvisibles3 = new Vector<DCell>();
			for (DCell df3 : ffaces3d) {
				if (df3.facing) dvisibles3.add(df3);
			}
			ACell.sortByOcclusion(dvisibles3);
			CellComplex visibles3 = new CellComplex(dvisibles3,camera4d);
			assert visibles3.checkSnap();
			assert visibles3.outsideReferrers().size() == 0;

			visibles3.occlude();

			for (Cell f1 : visibles3.getFacesOfDim(1, Opt.debug.isSelected())) {
				if (!f1.isInternal()) {
					g3.drawLine((Point3d)f1.a().o(), (Point3d)f1.b().o());
				}
			}
		}
	}
	
	
	public void BoundaryCuboid3d(Point a,Point b) {
		for (Compound c: compounds) for (DLocation v : c.getAllFaces()[0]) {
			for (int i=0;i<3;i++) {
				if ( v.p3.x[i] < a.x[i]) { a.x[i] = v.p3.x[i]; }
				if ( v.p3.x[i] > b.x[i]) { b.x[i] = v.p3.x[i]; }
			}
		}
	}
	
	public void BoundaryCuboid2d(Point2d a,Point2d b) {
		a.x1 = Double.MAX_VALUE;
		a.x2 = Double.MAX_VALUE;
		b.x1 = Double.MIN_VALUE;
		b.x2 = Double.MIN_VALUE;
		for (Compound c: compounds) for (DLocation v : c.getAllFaces()[0]) {
			if ( v.p2l.x1 < a.x1) { a.x1 = v.p2l.x1; }
			if ( v.p2l.x2 < a.x2) { a.x2 = v.p2l.x2; }
			if ( v.p2r.x1 < a.x1) { a.x1 = v.p2r.x1; }
			if ( v.p2r.x2 < a.x2) { a.x2 = v.p2r.x2; }

			if ( v.p2l.x1 > b.x1) { b.x1 = v.p2l.x1; }				
			if ( v.p2l.x2 > b.x2) { b.x2 = v.p2l.x2; }				
			if ( v.p2r.x1 > b.x1) { b.x1 = v.p2r.x1; }				
			if ( v.p2r.x2 > b.x2) { b.x2 = v.p2r.x2; }				
		}
	}
	
	public void paint(D3Graphics g3) {
		if (showGoal.isSelected()) {
			Vector<Compound> v = new Vector<Compound>();
			v.add(goal);
			updateFaces3d(v);
			paintScene(g3,v);
			updateFaces3d(compounds);
		}
		else { paintScene(g3,compounds); }
	}

	public void stateChanged() {
		changed();
	}

}
