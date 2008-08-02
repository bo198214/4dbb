package ddddbb.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;


public class AdjustmentPane extends JPanel {
	private static final long serialVersionUID = 8206023000270677933L;
	private TestScreenCanvas testScreenCanvas = null;
	private AdjustmentPanel adjustmentPanel = null;
	private JPanel jPanel = null;
	/**
	 * This is the default constructor
	 */
	public AdjustmentPane() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getTestScreenCanvas(), java.awt.BorderLayout.CENTER);
		this.add(getJPanel(), java.awt.BorderLayout.EAST);
	}

	/**
	 * This method initializes testScreenCanvas	
	 * 	
	 * @return ddddbb.TestScreenCanvas	
	 */
	private TestScreenCanvas getTestScreenCanvas() {
		if (testScreenCanvas == null) {
			testScreenCanvas = new TestScreenCanvas();
		}
		return testScreenCanvas;
	}

	/**
	 * This method initializes adjustmentPanel	
	 * 	
	 * @return ddddbb.AdjustmentPanel	
	 */
	private AdjustmentPanel getAdjustmentPanel() {
		if (adjustmentPanel == null) {
			adjustmentPanel = new AdjustmentPanel();
		}
		return adjustmentPanel;
	}
	
	public String toString() {
		return "testScreen";
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
//			jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
			jPanel.add(getAdjustmentPanel(), null);
		}
		return jPanel;
	}

}
