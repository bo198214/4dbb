package ddddbb.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ddddbb.game.Scene4d;
import ddddbb.gen.DiAxisModel;
import ddddbb.gen.IntStringModel;
import ddddbb.math.Camera3d;
import ddddbb.math.Camera4d;

public class UIAction {
	
	private final DiAxisModel viewRotXAxis12;
	private final IntStringModel d3ViewRotAxis;
	private final IntStringModel dim34;
	private final Scene4d scene;
	
	UIAction(
			DiAxisModel _viewRotXAxis12,
			IntStringModel _d3ViewRotAxs,
			IntStringModel _dim34,
			Scene4d _scene
	) {
		viewRotXAxis12 = _viewRotXAxis12;
		d3ViewRotAxis = _d3ViewRotAxs;
		dim34 = _dim34;
		scene = _scene;
	}
	
	public ActionListener set4dRotAxes(final int a1, final int a2) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewRotXAxis12.setAxis12(a1-1,a2-1);
			}
		};
		
	}
	public ActionListener set3dRotAxis(final int a) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d3ViewRotAxis.setInt(a-1);
			}
		};
	}

	/**
	 * Camera is rotated opposite to the given direction. 
	 * So that the system seems to rotate in the given direction.
	 */
	public ActionListener rotCam(final int a1, final int a2) {
		assert a1!=0 && a2!=0;
		int dim = 3+dim34.getInt(); 
		if (a1<-dim || a1>dim || a2<-dim || a2>dim) {
			//null action if outside range
			return new ActionListener() {
				public void actionPerformed(ActionEvent e) {}
			};
		}
		if (dim==3) return scene.rotCam3dAction(a1,a2);
		if (dim==4) return scene.rotCam4dAction(a1,a2);
		assert false;
		return null;
	}
	/**
	 * Camera is translated inverted to the given direction,
	 * so that the scene seems to be moved in the given direction
	 */
	public ActionListener transCam(final int axis) {
		assert axis!=0;
		int dim = 3+dim34.getInt(); 
		if (axis<-dim || axis>dim) {
			//null action if outside range
			return new ActionListener() {
				public void actionPerformed(ActionEvent e) {}
			};
		}
		
		if (dim==3) return scene.transCam3dAction(axis);
		if (dim==4) return scene.transCam4dAction(axis);
		assert false;
		return null;		
	}

}
