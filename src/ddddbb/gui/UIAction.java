package ddddbb.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ddddbb.game.Scene4d;
import ddddbb.game.Settings;
import ddddbb.game.Settings.Axis3d;
import ddddbb.game.Settings.Axis4d;
import ddddbb.game.Settings.DiAxis3d;
import ddddbb.game.Settings.DiAxis4d;
import ddddbb.gen.DiAxisModel;
import ddddbb.gen.IntStringModel;
import ddddbb.math.AOP;
import ddddbb.math.Camera3d;
import ddddbb.math.Camera4d;

public class UIAction {
	
	private final Settings ss;
	private final Scene4d scene;
	
	UIAction(
			Settings ss,
			Scene4d scene
	) {
		this.ss = ss;
		this.scene = scene;
	}
	
	public ActionListener set4dRotAxes(final int a1, final int a2) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ss.viewRotXAxis12.setAxis12(a1-1,a2-1);
			}
		};
		
	}
	public ActionListener set3dRotAxis(final int a) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ss.d3ViewRotAxis.setSelInt(a-1);
			}
		};
	}


	public void rotCam(double ph, int a1, int a2) {
		assert a1!=0 && a2!=0;
		int dim = ss.dim34.sel().dim();
		if (a1<-dim || a1>dim || a2<-dim || a2>dim) return;
		if (dim==3) scene.rotCam3d(ph, a1, a2);
		else if (dim==4) scene.rotCam4d(ph, a1, a2);
		else assert false;
	}
	/**
	 * Camera is rotated to the given direction. 
	 */
	public ActionListener rotCamA(final int a1, final int a2) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rotCam(AOP.deg,a1,a2);
			}
		};
	}
	/**
	 * Camera is translated inverted to the given direction,
	 * so that the scene seems to be moved in the given direction
	 */
	public ActionListener transCam(final int axis) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transCam(axis,0.1);
			}
		};
	}
	
	public void transCam(final int axis, double d) {
		assert axis!=0;
		int dim = ss.dim34.sel().dim(); 
		if (axis<-dim || axis>dim) return;
		if (dim==3) scene.camera3d.trans(axis,d,scene.viewAbsRel.isSelected());
		else if (dim==4) scene.camera4d.trans(axis,d, scene.viewAbsRel.isSelected());
		else assert false;
	}

	public ActionListener transCam3dAction(final int axis) {
		return scene.camera3d.transAction(axis, scene.viewAbsRel);
	}
	public ActionListener transCam4dAction(final int axis) {
		return scene.camera4d.transAction(axis, scene.viewAbsRel);
	}

	public ActionListener rotCam3dAction(final int a1, final int a2) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scene.rotCam3d(AOP.deg,a1,a2);
			}
		};
	}
	
	public ActionListener rotCam4dAction(final int a1, final int a2) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scene.rotCam4d(AOP.deg,a1,a2);
			}
		};
	}

	public void transCamByHoriz(double d) {
		int dim =ss.dim34.sel().dim(); 
		if (dim==3) {
			Axis3d a = ss.mouseTransAxes3d.sel1();
			scene.camera3d.trans(a.axis(),d,scene.viewAbsRel.isSelected());
		}
		if (dim==4) {
			Axis4d a = ss.mouseTransAxes4d.sel1();
			scene.camera4d.trans(a.axis(),d,scene.viewAbsRel.isSelected());
		}
	}
	
	public void transCamByVertic(double d) {
		int dim =ss.dim34.sel().dim(); 
		if (dim==3) {
			Axis3d a = ss.mouseTransAxes3d.sel2();
			scene.camera3d.trans(a.axis(),d,scene.viewAbsRel.isSelected());
		}
		if (dim==4) {
			Axis4d a = ss.mouseTransAxes4d.sel2();
			scene.camera4d.trans(a.axis(),d,scene.viewAbsRel.isSelected());
		}		
	}

	public void rotCamByHoriz(double ph) {
		int dim =ss.dim34.sel().dim();
		if (dim==3) {
			DiAxis3d da = ss.mouseRotDiAxes3d.sel1();
			scene.rotCam3d(ph, da.axis1(), da.axis2());
		}
		if (dim==4) {
			DiAxis4d da = ss.mouseRotDiAxes4d.sel1();
			scene.rotCam4d(ph, da.axis1(), da.axis2());
		}
	}
	
	public void rotCamByVertic(double ph) {
		int dim =ss.dim34.sel().dim(); 
		if (dim==3) {
			DiAxis3d da = ss.mouseRotDiAxes3d.sel2();
			scene.rotCam3d(ph,da.axis1(),da.axis2());
		}
		if (dim==4) {
			DiAxis4d da = ss.mouseRotDiAxes4d.sel2();
			scene.rotCam4d(ph,da.axis1(),da.axis2());
		}
	}
	
	public ActionListener rotCamByHorizA(final double ph) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rotCamByHoriz(ph);
			}
			
		};
	}

	public ActionListener rotCamByVerticA(final double ph) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rotCamByVertic(ph);
			}
			
		};
	}

	public ActionListener transCamByHorizA(final double d) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transCamByHoriz(d);
			}
			
		};
	}

	public ActionListener transCamByVerticA(final double d) {
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				transCamByVertic(d);
			}
			
		};
	}

	public ActionListener setTo3d=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ss.dim34.setSel(Settings.Dim.D3);
			}
	};
	
	public ActionListener setTo4d =
		new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ss.dim34.setSel(Settings.Dim.D4);
			}
	};
		
	public ActionListener resetCam3d = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scene.camera3d.notify = false;
				scene.camera3d.setToDefault();
				scene.camera3d.notify = true;
				scene.camera3d.setOrientation(ss.orientation3d.sel().value());
			}
	};

	public ActionListener resetCam4d = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			scene.camera4d.setToDefault();
			scene.camera4d.setOrientation(ss.orientation4d.sel().value());
			scene.camera4d.setZoom(ss.zoom.getDouble());
			//Settings.zoom.setToDefault();
		}
	};
	
	public ActionListener resetCam = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (ss.dim34.sel()==Settings.Dim.D3) {
				resetCam3d.actionPerformed(null);
			}
			else if (ss.dim34.sel()==Settings.Dim.D4) {
				resetCam4d.actionPerformed(null);
			}
		}
	};
}
