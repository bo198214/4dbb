package ddddbb.game;

import java.util.List;
import java.util.Vector;

import ddddbb.comb.ACell;
import ddddbb.comb.Cell;
import ddddbb.comb.CellComplex;
import ddddbb.comb.DCell;
import ddddbb.comb.DOp;
import ddddbb.gen.AChangeListener;
import ddddbb.gen.BoolModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.Model;
import ddddbb.gen.MyChangeListener;
import ddddbb.gen.SelectedListModel;
import ddddbb.math.Camera3d;
import ddddbb.math.Camera4d;
import ddddbb.math.D3Graphics;
import ddddbb.math.Point3d;
import ddddbb.math.Camera4d.ProjectionException;
import ddddbb.sound.Sound;

public class Scene4d extends Model implements MyChangeListener {

	public SelectedListModel<Compound> compounds = new SelectedListModel<Compound>();
	public Camera4d camera4d;
	public final Camera3d camera3d;
	protected List<DCell> faces3d;

	private boolean showInternalFaces;
	private SimpleSwitches.Occlusion4dAllowance occlusion4dAllowance;
	private int occlusion4dAllowanceInt;
	private int orientation4d;
	private double zoom;
	public Scene4d(
			final DoubleModel _zoom, 
			final IntModel<SimpleSwitches.Orientation4d> _orientation4d,
			final IntModel<SimpleSwitches.Orientation3d> orientation3d,
			final BoolModel showGoal,
			final IntModel<Camera4d> _camera4d,
			final IntModel<SimpleSwitches.Occlusion4dAllowance> _occlusion4dAllowance,
			final BoolModel _showInternalFaces,
			final DoubleModel screenEyeDist,
			final DoubleModel eyesDistHalf,
			final DoubleModel barEyeFocusDelta,	
			final Sound sound
	) {
		super();
		camera3d = new Camera3d(screenEyeDist,eyesDistHalf,barEyeFocusDelta);

		new AChangeListener() {
			public void stateChanged() {
				Camera4d prev = camera4d;
				camera4d = _camera4d.getSelectedObject();
				if (prev!=null) { 
					//prev.removeChangeListener(this);
					prev.removeChangeListener(recomputeFacing);
				}
				//camera4d.addChangeListener(this);
				camera4d.setOrientation(orientation4d);
				camera4d.setZoom(zoom);
				camera4d.addChangeListener(recomputeFacing);
				changed();
			}
		}.addTo(_camera4d);
		
		new AChangeListener() {
			@Override
			public void stateChanged() {
				occlusion4dAllowance = _occlusion4dAllowance.getSelectedObject();
				occlusion4dAllowanceInt = _occlusion4dAllowance.getInt();
			}			
		}.addTo(_occlusion4dAllowance);
		_occlusion4dAllowance.addChangeListener(recomputeFacing);

		new AChangeListener() {
			public void stateChanged() {
				showInternalFaces = _showInternalFaces.isSelected();
				updateCompoundGrid();
			}}.addTo(_showInternalFaces);
			
		new AChangeListener() {
			public void stateChanged() {
				zoom = _zoom.getDouble();
				camera4d.setZoom(zoom);
			}
		}.addTo(_zoom);
		
		new AChangeListener() {
			public void stateChanged() {
				orientation4d = _orientation4d.getSelectedObject().value;
				camera4d.setOrientation(orientation4d);
			}}.addTo(_orientation4d);
			
		new AChangeListener() {
			public void stateChanged() {
				camera3d.setOrientation(
						orientation3d.getSelectedObject().value());
				changed();
			}}.addTo(orientation3d);
			
		new AChangeListener() {
			public void stateChanged() {
				if (showGoal.isSelected()) sound.SHOWGOAL.play();
				changed();
			}}.addTo(showGoal);
	}

	public void setToDefault() {
		//init(0);
	}

	protected void setCompounds(int[][][] cs) {
		compounds.clear();
		for (int i=0;i<cs.length;i++) {
			compounds.add(new Compound(DOp.clone(cs[i])));
		}
		updateFaces3d(compounds);
		updateVisibility();
	}

	public void updateCompoundGrid() {
		for (Compound compound: compounds) {
			compound.setShowGrid(showInternalFaces);
		}		
	}

	protected void updateVisibility() {
		for (DCell of: faces3d) {
			of.setFacing(camera4d);
		}		
	}

	protected final MyChangeListener recomputeFacing = new MyChangeListener() {
			public void stateChanged() {
				if (faces3d==null) return;
				if (occlusion4dAllowanceInt>=SimpleSwitches.Occlusion4dAllowance.BACKFACE.ordinal()) {
					for (DCell of : faces3d) {
						of.setFacing(camera4d);
					}								
				}
				changed();
			}
		};

	protected void updateFaces3d(List<Compound> cs) {
		faces3d = new Vector<DCell>(); 
		for (Compound c : cs) {
			DCell[] facets4d = c.getTopLevelFacets();
			for (int i=0;i<facets4d.length;i++) {
				faces3d.addAll(facets4d[i].getFaces(false));
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
	
			SimpleSwitches.Occlusion4dAllowance oa = occlusion4dAllowance;
			
			if (oa == SimpleSwitches.Occlusion4dAllowance.NONE) {
				for (DCell df3 : ffaces3d) g3.render3dFacet(df3);
			}
			else if (oa==SimpleSwitches.Occlusion4dAllowance.BACKFACE) {
				for (DCell df3 : ffaces3d) {
					if (df3.facing) g3.render3dFacet(df3);
				}
			}
			else if (oa==SimpleSwitches.Occlusion4dAllowance.COMPLETE) {
				Vector<DCell> dvisibles3 = new Vector<DCell>();
				for (DCell df3 : ffaces3d) {
					if (df3.facing) dvisibles3.add(df3);
				}
				ACell.sortByOcclusion(dvisibles3);
				CellComplex visibles3 = new CellComplex(dvisibles3,camera4d);
				assert visibles3.checkSnap();
				assert visibles3.outsideReferrers().size() == 0;
	
				visibles3.occlude();
	
				for (Cell f1 : visibles3.getFacesOfDim(1, Main.debug.isSelected())) {
					if (!f1.isInternal()) {
						g3.drawLine((Point3d)f1.a().o(), (Point3d)f1.b().o());
					}
				}
			}
		}

	public void addChangeListener(MyChangeListener l) {
		super.addChangeListener(l);
		compounds.addChangeListener(l);
		camera3d.addChangeListener(l);
	}
	
}