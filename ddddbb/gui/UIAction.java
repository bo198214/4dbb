package ddddbb.gui;

import java.awt.event.ActionEvent;

import ddddbb.comb.DSignedAxis;
import ddddbb.game.Opt;

public class UIAction {
	public static final double deg = Math.PI*2/360; 
	public static Performer transSelected(final int direc) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.scene.transSelected(new DSignedAxis(direc));
			}
		};
	}
	public static Performer rotSelected(final int a1, final int a2) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.scene.rotateSelected(a1-1, a2-1);
			}
		};
	}
	
	public static Performer set4dRotAxes(final int a1, final int a2) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.viewRotXAxis12.setAxis12(a1-1,a2-1);
			}
		};
		
	}
	public static Performer setSelected(final int i) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.scene.compounds.setSelected(i);
			}		
		};
	}
	public static Performer set3dRotAxis(final int a) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.d3ViewRotAxis.setInt(a-1);
			}
		};
	}

	/**
	 * Camera is rotated opposite to the given direction. 
	 * So that the system seems to rotate in the given direction.
	 */
	public static Performer rotCam(final int a1, final int a2) {
		assert a1!=0 && a2!=0;
		int dim = 3+Opt.dim34.getInt(); 
		if (a1<-dim || a1>dim || a2<-dim || a2>dim) {
			//null action if outside range
			return new Performer() {
				public void actionPerformed(ActionEvent e) {}
			};
		}
		if (dim==3) return rotCam3d(a1,a2);
		if (dim==4) return rotCam4d(a1,a2);
		assert false;
		return null;
	}
	
	public static Performer rotCam3d(final int a1, final int a2) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.scene.camera3d.rotate(deg, 
						Opt.viewAbsRel.getSelectedObject().selectDirec3d(a1-1),
						Opt.viewAbsRel.getSelectedObject().selectDirec3d(a2-1), 
						Opt.viewAbsRel.getSelectedObject().selectCenter3d());
			}
		};					
	}
	
	public static Performer rotCam4d(final int a1, final int a2) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.scene.camera4d.rotate(deg,
						Opt.viewAbsRel.getSelectedObject().selectDirec4d(a1-1),
						Opt.viewAbsRel.getSelectedObject().selectDirec4d(a2-1),
						Opt.viewAbsRel.getSelectedObject().selectCenter4d());
			}
		};					
	}
	
	public static Performer transCam3d(final int axis) {
		if (axis>0) return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.scene.camera3d.translate(Opt.ViewAbsRel.CAMERA.selectDirec3d(axis-1),+0.1);
			}
		};
		if (axis < 0) return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.scene.camera3d.translate(Opt.ViewAbsRel.CAMERA.selectDirec3d(-axis-1),-0.1);
			}
		};
		assert false;
		return null;
	}

	public static Performer transCam4d(final int axis) {
		if (axis>0) return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.scene.camera4d.translate(Opt.ViewAbsRel.CAMERA.selectDirec4d(axis-1),+0.1);
			}
		};
		if (axis < 0) return new Performer() {
			public void actionPerformed(ActionEvent e) {
				Opt.scene.camera4d.translate(Opt.ViewAbsRel.CAMERA.selectDirec4d(-axis-1),-0.1);
			}
		};
		assert false;
		return null;
	}
	/**
	 * Camera is translated inverted to the given direction,
	 * so that the scene seems to be moved in the given direction
	 */
	public static Performer transCam(final int axis) {
		assert axis!=0;
		int dim = 3+Opt.dim34.getInt(); 
		if (axis<-dim || axis>dim) {
			//null action if outside range
			return new Performer() {
				public void actionPerformed(ActionEvent e) {}
			};
		}
		
		if (dim==3) return transCam3d(axis);
		if (dim==4) return transCam4d(axis);
		assert false;
		return null;		
	}
}
