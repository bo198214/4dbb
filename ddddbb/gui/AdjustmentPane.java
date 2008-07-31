package ddddbb.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import ddddbb.game.ScreenValues;
import ddddbb.game.SimpleSwitches;
import ddddbb.gen.IntModel;


@SuppressWarnings("serial")
public class AdjustmentPane extends JPanel {
	private final TestScreenCanvas testScreenCanvas;
	private final AdjustmentPanel adjustmentPanel;
	private final JPanel jPanel;

	public AdjustmentPane(ScreenValues sv,IntModel<SimpleSwitches.ViewType> viewType) {
		testScreenCanvas = new TestScreenCanvas(sv.xdpcm,sv.ydpcm,sv.brightness,viewType,sv.eyesDistHalf,sv.screenEyeDist,sv.barEyeFocusDelta);
		adjustmentPanel = new AdjustmentPanel(sv);
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
