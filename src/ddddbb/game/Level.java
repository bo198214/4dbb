package ddddbb.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import ddddbb.comb.DLocation;
import ddddbb.comb.DOp;
import ddddbb.comb.DSignedAxis;
import ddddbb.gen.IntModel;
import ddddbb.math.Camera4dParallel;
import ddddbb.math.D4Tupel;
import ddddbb.math.Point;
import ddddbb.math.Point2d;
import ddddbb.math.Point4d;
import ddddbb.sound.SoundEnum;

public class Level extends Scene4d {
	public final IntModel<Settings.GameStatus> gameStatus;
	//	public Vector<Facet> allFacets = new Vector<Facet>();
	public String name;
	public Compound goal;

	public D4Tupel cursor = new D4Tupel(0,0,0,0);
	//public BoolModel showGoal;
	
	public Level(final Settings ss) {
		super(ss);
		gameStatus = ss.gameStatus;
	}

	private void propagateGameStatus() {
		if (goal == null) { 
			gameStatus.setSelectedObject(Settings.GameStatus.NONE);
			return;
		}
		if (compounds.size() == 1) {
			if (DOp.motionEqual(goal.cubes,compounds.getSelectedItem().cubes)) {
				gameStatus.setSelectedObject(Settings.GameStatus.REACHED);
			} 
			else {
				gameStatus.setSelectedObject(Settings.GameStatus.MISSED);
			}
			return;
		}
		if (!DOp.motionContained(compounds.getSelectedItem().cubes,goal.cubes)) {
			gameStatus.setSelectedObject(Settings.GameStatus.MISSED);
			return;
		}
		if (gameStatus.getSelectedObject() != Settings.GameStatus.PENDING ) {
			gameStatus.setSelectedObject(Settings.GameStatus.PENDING);
		}
	}
	
	private void setGoal(int[][] c) {
		goal = new Compound(c);
	}
	
	public void changeObjective(Objectives objective) {
		setGoal(objective.goal);
		setCompounds(objective.compounds);
		propagateGameStatus();
	}
	
//	public Compound getSelected() {
//		return (Compound)compounds.getSelectedObject();
//	}
	
//	public boolean move(D4Tupel t) {
//		return move(t.x1,t.x2,t.x3,t.x4);
//	}
	
	public boolean transSelected(DSignedAxis v) {
		if ( compounds.getSelected() == -1 ) { return false; }
		Compound compound = compounds.getSelectedItem(); 
		compound.translate(v);
		if (isOverlapping()) {
			compound.translate(new DSignedAxis(-v.human()));
			SoundEnum.BARRINGCOMPOUND.play();
			return false;
		}
		compounds.changed();
		SoundEnum.MOVINGCOMPOUND.play();
		return true;
	}
	
	public ActionListener transSelectedAction(final int direc) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transSelected(new DSignedAxis(direc));
			}
		};
	}
	
	public boolean rotSelected(int v, int w) {
		if ( compounds.getSelected() == -1 ) { return false; }
		Compound compound = compounds.getSelectedItem();
		compound.rotate(v,w);
		if (isOverlapping()) { 
			compound.rotate(w,v);
			SoundEnum.BARRINGCOMPOUND.play();
			return false;
		}
		if (camera4d instanceof Camera4dParallel) {
			//for other cases visibility is set in paint method
			//for programming lazyness set new visibility of *all* compounds
			updateFacing();
		}
		compounds.changed();
		SoundEnum.ROTATINGCOMPOUND.play();
		return true;
	}
	
	public ActionListener rotSelectedAction(final int a1, final int a2) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotSelected(a1-1, a2-1);
			}
		};
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
		c0.setBaryCenter();
		updateFaces3d(compounds);
		updateFacing();
	}
	
	public void combine() {
		Compound c0 = compounds.getSelectedItem();
		Vector<Compound> bordering = combinables(c0); 
		if (
			compounds.size() != 1 &&
			bordering.size() > 0
		) {
			combine(c0,bordering);
			compounds.changed();			
			SoundEnum.SNAPPINGCOMPOUNDS.play();
			System.out.println("sound played");
		}
		propagateGameStatus();
	}
	
	public ActionListener combineAction  = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			combine();
		}		
	};

	
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
	
//	public void paint(D3Graphics g3) {
//		if (showGoal.isSelected()) {
//			Vector<Compound> v = new Vector<Compound>();
//			v.add(goal);
//			updateFaces3d(v);
//			paintScene(g3,v);
//			updateFaces3d(compounds);
//		}
//		else { paintScene(g3,compounds); }
//	}

	public void stateChanged() {
		changed();
	}

}
