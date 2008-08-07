package ddddbb.game;

import java.util.List;
import java.util.Vector;

import javax.sound.sampled.Clip;

import ddddbb.comb.Cell;
import ddddbb.comb.CellComplex;
import ddddbb.comb.DCell;
import ddddbb.comb.DOp;
import ddddbb.gen.AChangeListener;
import ddddbb.gen.BoolModel;
import ddddbb.gen.Model;
import ddddbb.gen.MyChangeListener;
import ddddbb.gen.SelectedListModel;
import ddddbb.math.Camera3d;
import ddddbb.math.Camera4d;
import ddddbb.math.D3Graphics;
import ddddbb.math.Point3d;
import ddddbb.math.Point4d;
import ddddbb.math.Camera4d.ProjectionException;
import ddddbb.sound.SoundEnum;

public class Scene4d extends Model implements MyChangeListener {
	public final SelectedListModel<Compound> compounds = new SelectedListModel<Compound>();
	protected List<DCell> faces3d;

	public final Camera3d camera3d;
	public Camera4d camera4d;

	private boolean showInternalFaces;
	private Settings.Occlusion4dAllowance occlusion4dAllowance;
	private int occlusion4dAllowanceInt;

	public final BoolModel viewAbsRel = new BoolModel(true,"Cam coords");;
	protected final Settings ss;
	
	public Scene4d(	final Settings ss) {
		this.ss = ss;
		camera3d = new Camera3d(ss.screenEyeDist,ss.eyesDistHalf,ss.barEyeFocusDelta);
		new AChangeListener() {
			public void stateChanged() {
				Camera4d prev = camera4d;
				camera4d = ss.perspective.sel();
				if (camera4d==prev) return;
				assert camera4d.changeListeners.isEmpty() : camera4d + ":" + camera4d.changeListeners;
				//following lines do not cause propagation of changes
				camera4d.setOrientation(ss.orientation4d.sel().value);
				camera4d.setZoom(ss.zoom.getDouble());
				if (prev!=null) { 
					//move ChangeListeners to new camera4d
					Vector<MyChangeListener> listeners = prev.changeListeners;
					prev.changeListeners = camera4d.changeListeners;
					camera4d.changeListeners = listeners;
				}
				else {
					camera4d.addChangeListener(updateFacing);					
					camera4d.addChangeListener(Scene4d.this);
				}
				changed();
			}
		}.addTo(ss.perspective);

		new AChangeListener() {
			public void stateChanged() {
				camera4d.setZoom(ss.zoom.getDouble());
			}
		}.addTo(ss.zoom);
		
		new AChangeListener() {
			public void stateChanged() {
				camera4d.setOrientation(ss.orientation4d.sel().value);
			}}.addTo(ss.orientation4d);
			
		new AChangeListener() {
			public void stateChanged() {
				camera3d.setOrientation(
						ss.orientation3d.sel().value());
				changed();
			}}.addTo(ss.orientation3d);
			
		new AChangeListener() {
			public void stateChanged() {
				Clip clip = SoundEnum.SHOWGOAL.clip; 
				if (ss.showGoal.isSelected()) {
					clip.setFramePosition(0);
					clip.start();
				}
				else {
//					//SoundEnum.SHOWGOAL.clip.drain();
//					if (clip.isRunning()) 
//						SoundEnum.SHOWGOAL.clip.stop();
				}
				changed();
			}}.addTo(ss.showGoal);
			
		camera3d.addChangeListener(this);
		compounds.addChangeListener(this);
		initCompounds(ss);
	}

	private void initCompounds(final Settings ss) {
		new AChangeListener() {
			@Override
			public void stateChanged() {
				occlusion4dAllowance = ss.occlusion4dAllowance.sel();
				occlusion4dAllowanceInt = ss.occlusion4dAllowance.selInt();
				changed();
			}			
		}.addTo(ss.occlusion4dAllowance);
		ss.occlusion4dAllowance.addChangeListener(updateFacing);

		new AChangeListener() {
			public void stateChanged() {
				showInternalFaces = ss.showInternalFaces.isSelected();
				updateCompoundGrid();
			}}.addTo(ss.showInternalFaces);		
	}
	
	private Scene4d(Camera3d c3d,Settings ss) {
		camera3d = c3d;
		this.ss = ss;
	}
		
	public Scene4d cloneCamRefs() {
		Scene4d res = new Scene4d(camera3d,ss);
		res.camera4d = camera4d;
		res.showInternalFaces = showInternalFaces;
		res.occlusion4dAllowance = occlusion4dAllowance;
		res.occlusion4dAllowanceInt = occlusion4dAllowanceInt;
		res.initCompounds(ss);
		return res;
	}
	
	public void setToDefault() {
		//init(0);
	}

	public void setCompounds(int[][][] cs) {
		compounds.notify = false;
		compounds.clear();
		for (int i=0;i<cs.length;i++) {
			compounds.add(new Compound(DOp.clone(cs[i])));
		}
		updateFaces3d(compounds);
		updateFacing();
		compounds.notify = true;
		compounds.changed();
	}

	public void updateCompoundGrid() {
		for (Compound compound: compounds) {
			compound.setShowGrid(showInternalFaces);
		}		
	}

	protected void updateFacing() {
		if (faces3d==null) return;
		if (occlusion4dAllowanceInt>=Settings.Occlusion4dAllowance.BACKFACE.ordinal()) {
			for (DCell of : faces3d) {
				of.setFacing(camera4d);
			}								
		}
	}

	protected final MyChangeListener updateFacing = new MyChangeListener() {
			public void stateChanged() {
				updateFacing();
				//changed();
			}
		};

	protected void updateFaces3d(List<Compound> cs) {
		faces3d = new Vector<DCell>(); 
		for (Compound c : cs) {
			DCell[] facets4d = c.getTopLevelFacets();
			for (int i=0;i<facets4d.length;i++) {
				faces3d.addAll(facets4d[i].getFacets(false));
			}
		}		
	}
	
	public void paint(D3Graphics g3) {
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

		Settings.Occlusion4dAllowance oa = occlusion4dAllowance;

		if (oa == Settings.Occlusion4dAllowance.NONE) {
			for (DCell df3 : ffaces3d) g3.render3dFacet(df3);
		}
		else if (oa==Settings.Occlusion4dAllowance.BACKFACE) {
			for (DCell df3 : ffaces3d) {
				if (df3.facing) g3.render3dFacet(df3);
			}
		}
		else if (oa==Settings.Occlusion4dAllowance.COMPLETE) {
			Vector<DCell> dvisibles3 = new Vector<DCell>();
			for (DCell df3 : ffaces3d) {
				if (df3.facing) dvisibles3.add(df3);
			}
			CellComplex occlusionComplex = CellComplex.occlusionCreate(dvisibles3,camera4d);
			assert occlusionComplex.checkSnap();
			assert occlusionComplex.outsideReferrers().size() == 0;

			for (Cell f1 : occlusionComplex.getFacesOfDim(1, Main.debug.isSelected())) {
				if (!f1.isInternal()) {
					g3.drawLine((Point3d)f1.a().o(), (Point3d)f1.b().o());
				}
			}
		}
	}

//	public void addChangeListener(MyChangeListener l) {
//		super.addChangeListener(l);
//		compounds.addChangeListener(l);
//		camera3d.addChangeListener(l);
//	}

	private Point4d selectedCenter4d() {
		return (Point4d)compounds.sel().center(); 
	}
	
	public void rotCam3d(double ph, int a1, int a2) {
		Point3d p3 = new Point3d();
		camera4d.proj3d(selectedCenter4d(),p3);
		camera3d.rot(ph, a1, a2, p3, viewAbsRel.isSelected());
	}
	
	public void rotCam4d(double ph, int a1, int a2) {
		camera4d.rot(ph, a1, a2, selectedCenter4d(), viewAbsRel.isSelected());
	}

}