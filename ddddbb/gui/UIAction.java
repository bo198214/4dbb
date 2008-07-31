package ddddbb.gui;

import java.awt.event.ActionEvent;

import ddddbb.gen.DiAxisModel;
import ddddbb.gen.IntStringModel;

public class UIAction {
	
	private final DiAxisModel viewRotXAxis12;
	private final IntStringModel d3ViewRotAxis;
	private final IntStringModel dim34;
	private final Cam3dAction c3a;
	private final Camera4dAction c4a;
	
	UIAction(
			DiAxisModel _viewRotXAxis12,
			IntStringModel _d3ViewRotAxs,
			IntStringModel _dim34,
			Cam3dAction _c3a,
			Camera4dAction _c4a
	) {
		viewRotXAxis12 = _viewRotXAxis12;
		d3ViewRotAxis = _d3ViewRotAxs;
		dim34 = _dim34;
		c3a = _c3a;
		c4a = _c4a;
	}
	
	public Performer set4dRotAxes(final int a1, final int a2) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				viewRotXAxis12.setAxis12(a1-1,a2-1);
			}
		};
		
	}
	public Performer set3dRotAxis(final int a) {
		return new Performer() {
			public void actionPerformed(ActionEvent e) {
				d3ViewRotAxis.setInt(a-1);
			}
		};
	}

	/**
	 * Camera is rotated opposite to the given direction. 
	 * So that the system seems to rotate in the given direction.
	 */
	public Performer rotCam(final int a1, final int a2) {
		assert a1!=0 && a2!=0;
		int dim = 3+dim34.getInt(); 
		if (a1<-dim || a1>dim || a2<-dim || a2>dim) {
			//null action if outside range
			return new Performer() {
				public void actionPerformed(ActionEvent e) {}
			};
		}
		if (dim==3) return c3a.rotCam3d(a1,a2);
		if (dim==4) return c4a.rotCam4d(a1,a2);
		assert false;
		return null;
	}
	/**
	 * Camera is translated inverted to the given direction,
	 * so that the scene seems to be moved in the given direction
	 */
	public Performer transCam(final int axis) {
		assert axis!=0;
		int dim = 3+dim34.getInt(); 
		if (axis<-dim || axis>dim) {
			//null action if outside range
			return new Performer() {
				public void actionPerformed(ActionEvent e) {}
			};
		}
		
		if (dim==3) return c3a.transCam3d(axis);
		if (dim==4) return c4a.transCam4d(axis);
		assert false;
		return null;		
	}

}
