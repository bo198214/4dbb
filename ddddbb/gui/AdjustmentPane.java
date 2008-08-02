package ddddbb.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JPanel;

import ddddbb.game.Settings;


@SuppressWarnings("serial")
public class AdjustmentPane extends JPanel {
	private final TestScreenCanvas testScreenCanvas;
	private final AdjustmentPanel adjustmentPanel;
	private final JPanel jPanel;

	public AdjustmentPane(Settings sv, Container window) {
		testScreenCanvas = new TestScreenCanvas(sv.xdpcm,sv.ydpcm,sv.brightness,sv.viewType,sv.eyesDistHalf,sv.screenEyeDist,sv.barEyeFocusDelta);
		adjustmentPanel = new AdjustmentPanel(sv, window);
		jPanel = new JPanel();
//		jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
		jPanel.add(adjustmentPanel, null);
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(testScreenCanvas, java.awt.BorderLayout.CENTER);
		this.add(jPanel, java.awt.BorderLayout.EAST);
	}


	public String toString() {
		return "testScreen";
	}

}
