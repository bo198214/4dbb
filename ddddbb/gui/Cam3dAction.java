package ddddbb.gui;

import java.awt.event.ActionEvent;

import ddddbb.game.Main.ViewAbsRelEnum;
import ddddbb.math.AOP;
import ddddbb.math.Camera3d;

public class Cam3dAction {
	
	final ViewAbsRelEnum viewAbsRel;
	final Camera3d camera3d;
	Cam3dAction(
			final Camera3d _camera3d,
			final ViewAbsRelEnum _viewAbsRel) {
		viewAbsRel = _viewAbsRel;
		camera3d = _camera3d;
	}
	
	public Performer rotCam3d(final int a1, final int a2) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				camera3d.rotate(AOP.deg, 
						viewAbsRel.getSelectedObject().selectDirec3d(a1-1),
						viewAbsRel.getSelectedObject().selectDirec3d(a2-1), 
						viewAbsRel.getSelectedObject().selectCenter3d());
			}
		};					
	}
	

	public Performer transCam3d(final int axis) {
		if (axis>0) return new Performer() {
			public void actionPerformed(ActionEvent e) {
				camera3d.translate(viewAbsRel.CAMERA.selectDirec3d(axis-1),+0.1);
			}
		};
		if (axis < 0) return new Performer() {
			public void actionPerformed(ActionEvent e) {
				camera3d.translate(viewAbsRel.CAMERA.selectDirec3d(-axis-1),-0.1);
			}
		};
		assert false;
		return null;
	}

	
}
