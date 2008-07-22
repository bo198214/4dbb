package ddddbb.gui;

import ddddbb.comb.DSignedAxis;
import ddddbb.game.Opt;

public class UIAction {
	public static Performer transSelected(final int direc) {
		return new Performer() {
			public void perform() {
				Opt.scene.transSelected(new DSignedAxis(direc));
			}
		};
	}
	public static Performer rotSelected(final int a1, final int a2) {
		return new Performer() {
			public void perform() {
				Opt.scene.rotateSelected(a1-1, a2-1);
			}
		};
	}
	
	public static Performer set4dRotAxes(final int a1, final int a2) {
		return new Performer() {
			public void perform() {
				Opt.viewRotXAxis12.setAxis12(a1-1,a2-1);
			}
		};
		
	}
	public static Performer setSelected(final int i) {
		return new Performer() {
			public void perform() {
				Opt.scene.compounds.setSelected(i);
			}		
		};
	}
	public static Performer set3dRotAxis(final int a) {
		return new Performer() {
			public void perform() {
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
				public void perform() {}
			};
		}
		if (dim==3) {
			return new Performer() {
				public void perform() {
					Opt.scene.camera3d.rotate(0.1, 
							Opt.ViewAbsRel.SYSTEM.selectDirec3d(a2-1),
							Opt.ViewAbsRel.SYSTEM.selectDirec3d(a1-1), 
							Opt.ViewAbsRel.SYSTEM.selectCenter3d());
				}
			};			
		}
		if (dim==4) {
			return new Performer() {
				public void perform() {
					Opt.scene.camera4d.rotate(0.1,
							Opt.ViewAbsRel.SYSTEM.selectDirec4d(a2-1),
							Opt.ViewAbsRel.SYSTEM.selectDirec4d(a1-1),
							Opt.ViewAbsRel.SYSTEM.selectCenter4d());
				}
			};			
		}
		assert false;
		return null;
	}
	
	public static Performer trans3dCam(final int a) {
		assert false : "axis must be non-zero but is" + a;
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
				public void perform() {}
			};
		}
		
		if (dim==3) {
			if (axis>0) return new Performer() {
				public void perform() {
					Opt.scene.camera3d.translate(Opt.ViewAbsRel.SYSTEM.selectDirec3d(axis-1),-0.1);
				}
			};
			if (axis < 0) return new Performer() {
				public void perform() {
					Opt.scene.camera3d.translate(Opt.ViewAbsRel.SYSTEM.selectDirec3d(-axis-1),+0.1);
				}
			};
		}
		
		if (dim==4) {
			if (axis>0) return new Performer() {
				public void perform() {
					Opt.scene.camera4d.translate(Opt.ViewAbsRel.SYSTEM.selectDirec4d(axis-1),-0.1);
				}
			};
			if (axis < 0) return new Performer() {
				public void perform() {
					Opt.scene.camera4d.translate(Opt.ViewAbsRel.SYSTEM.selectDirec4d(-axis-1),+0.1);
				}
			};
		}
		assert false;
		return null;		
	}
}
