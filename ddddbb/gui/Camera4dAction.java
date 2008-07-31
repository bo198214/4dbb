package ddddbb.gui;

import java.awt.event.ActionEvent;

import ddddbb.game.Main.ViewAbsRelEnum;
import ddddbb.math.AOP;
import ddddbb.math.Camera4d;

public class Camera4dAction {

	private final Camera4d camera4d;
	private ViewAbsRelEnum viewAbsRel;
	public Camera4dAction(Camera4d _camera4d, 
			final ViewAbsRelEnum _viewAbsRel) {
		camera4d = _camera4d;
		viewAbsRel = _viewAbsRel;
	}

	public Performer rotCam4d(final int a1, final int a2) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				camera4d.rotate(AOP.deg,
						viewAbsRel.getSelectedObject().selectDirec4d(a1-1),
						viewAbsRel.getSelectedObject().selectDirec4d(a2-1),
						viewAbsRel.getSelectedObject().selectCenter4d());
			}
		};					
	}

	public Performer transCam4d(final int axis) {
		if (axis>0) return new Performer() {
			public void actionPerformed(ActionEvent e) {
				camera4d.translate(viewAbsRel.CAMERA.selectDirec4d(axis-1),+0.1);
			}
		};
		if (axis < 0) return new Performer() {
			public void actionPerformed(ActionEvent e) {
				camera4d.translate(viewAbsRel.CAMERA.selectDirec4d(-axis-1),-0.1);
			}
		};
		assert false;
		return null;
	}


}
