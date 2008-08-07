package ddddbb.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;

import ddddbb.game.PersistentPreferences;
import ddddbb.game.Settings;
import ddddbb.gen.DoubleSpinner;
import ddddbb.gen.DoubleUnitModel;

@SuppressWarnings("serial")
public class AdjustmentPanel extends JPanel {
	private JLabel eyesDistLabel = null;
	private JSpinner eyesDistSpinner = null;
	private JComboBox unitComboBox = null;
	private JLabel screenResolutionXLabel = null;
	private JComboBox resolutionUnitComboBox = null;
	private JSpinner screenResolutionXSpinner = null;
	private JSpinner screenResolutionYSpinner = null;
	private JLabel screenEyeDistLabel = null;
	private JSpinner screenEyeDistSpinner = null;
	private JLabel screenResolutionYLabel = null;
	private JLabel mouseTransSensLabel = null;

	private JLabel mouseRotSensLabel = null;

	private DoubleSpinner mouseTransSensSpinner = null;

	private DoubleSpinner mouseRotSensSpinner = null;

	private JLabel barEyeFocusDeltaLabel = null;

	private DoubleSpinner barEyeFocusDeltaSpinner = null;

	private JSlider brightnessSlider = null;

	private JLabel brightnessLabel = null;

	private JLabel mouseTransUnitLabel = null;

	private JLabel mouseRotationUnitLabel = null;
	
	private JButton defaultValuesButton = null;

	/**
	 * This is the default constructor
	 */
	private Settings sv;
	private Container window;
	public AdjustmentPanel(Settings _sv, Container _window) {
		sv = _sv;
		window = _window;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		mouseRotationUnitLabel = new JLabel();
		mouseRotationUnitLabel.setText("rad/cm");
		mouseTransUnitLabel = new JLabel();
		mouseTransUnitLabel.setText("cm/cm");
		brightnessLabel = new JLabel();
		brightnessLabel.setText("brightness");
		barEyeFocusDeltaLabel = new JLabel();
		barEyeFocusDeltaLabel.setText("barEyeFocusDelta");
		mouseRotSensLabel = new JLabel();
		mouseRotSensLabel.setText("mouse r sensitivity");
		mouseRotSensLabel.setToolTipText("rotation sensitivity in rad/cm");
		mouseTransSensLabel = new JLabel();
		mouseTransSensLabel.setText("mouse t sensitivity");
		mouseTransSensLabel.setToolTipText("translation sensitivity  = distance in space / distance of mouse");
		screenEyeDistLabel = new JLabel();
		screenEyeDistLabel.setText("Screen Eyes Distance");
		screenResolutionYLabel = new JLabel();
		screenResolutionYLabel.setText("Vertical Resolution");
		screenResolutionXLabel = new JLabel();
		screenResolutionXLabel.setText("Horizontal Resolution");
		eyesDistLabel = new JLabel();
		eyesDistLabel.setText("Half eyes distance:");
		setLayout(new GridBagLayout());
		add(screenEyeDistLabel, new GBC(0,1));
		add(getScreenEyeDistSpinner(), new GBC(1,1));
		
		add(eyesDistLabel, new GBC(0,2));
		add(getEyesDistSpinner(), new GBC(1,2));
		
		add(getUnitComboBox(), new GBC(2,1,1,2));
		
		add(screenResolutionXLabel, new GBC(0,3));
		add(getScreenResolutionXSpinner(), new GBC(1,3));
		
		add(screenResolutionYLabel, new GBC(0,4));
		add(getScreenResolutionYSpinner(), new GBC(1,4));
		
		add(getResolutionUnitComboBox(), new GBC(2,3,1,2));
		
		add(mouseTransSensLabel, new GBC(0,7));
		add(getMouseTransSensSpinner(), new GBC(1,7));
		this.add(mouseTransUnitLabel, new GBC(2,7));
	
		add(mouseRotSensLabel, new GBC(0,8));
		add(getMouseRotSensSpinner(), new GBC(1,8));
		this.add(mouseRotationUnitLabel, new GBC(2,8));
	
		add(barEyeFocusDeltaLabel, new GBC(0,9));
		add(getBarEyeFocusDeltaSpinner(), new GBC(1,9));
		//add(getUnitComboBox(),new GBC(2,9));
	
		add(getBrightnessSlider(), new GBC(1,10,2,1));
		add(brightnessLabel, new GBC(0,10));

		add(getDefaultValuesButton(), new GBC(1,11));
	}
	
	private JButton getDefaultValuesButton() {
		if ( defaultValuesButton == null ) {
			defaultValuesButton = new JButton();
			defaultValuesButton.setAction(new AbstractAction() {
				public void actionPerformed(ActionEvent e) {
					sv.screenDefaults(window);
				}
				private static final long serialVersionUID = 5092274037007117460L;	
			});
			defaultValuesButton.setText("reset");
		}
		return defaultValuesButton;
	}
	
	private JSpinner getEyesDistSpinner() {
		if ( eyesDistSpinner == null ) {
			eyesDistSpinner = new DoubleSpinner(new DoubleUnitModel(sv.eyesDistHalf,sv.lengthUnit));
			eyesDistSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		return eyesDistSpinner;
	}

	private JSpinner getScreenEyeDistSpinner() {
		if (screenEyeDistSpinner == null) {
			screenEyeDistSpinner = new DoubleSpinner(new DoubleUnitModel(sv.screenEyeDist,sv.lengthUnit));
			screenEyeDistSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		return screenEyeDistSpinner;
	}

	private JComboBox getUnitComboBox() {
		if (unitComboBox == null) {
			unitComboBox = new JComboBox(sv.lengthUnit);
		}
		return unitComboBox;
	}

	private JSpinner getScreenResolutionXSpinner() {
		if ( screenResolutionXSpinner == null ) {
			screenResolutionXSpinner = new DoubleSpinner(new DoubleUnitModel(sv.xdpcm,sv.resolutionUnit));
			screenResolutionXSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		return screenResolutionXSpinner;
	}

	private JSpinner getScreenResolutionYSpinner() {
		if ( screenResolutionYSpinner == null ) {
			screenResolutionYSpinner = new DoubleSpinner(new DoubleUnitModel(sv.ydpcm,sv.resolutionUnit));
		}
		return screenResolutionYSpinner;
	}

	/**
	 * This method initializes screenResolutionXComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getResolutionUnitComboBox() {
		if (resolutionUnitComboBox == null) {
			resolutionUnitComboBox = new JComboBox(sv.resolutionUnit);
		}
		return resolutionUnitComboBox;
	}

	/**
	 * This method initializes mouseTransSensSpinner	
	 * 	
	 * @return ddddbb.DoubleSpinner	
	 */
	private DoubleSpinner getMouseTransSensSpinner() {
		if (mouseTransSensSpinner == null) {
			mouseTransSensSpinner = new DoubleSpinner(sv.mouseTransSens);
		}
		return mouseTransSensSpinner;
	}

	/**
	 * This method initializes mouseRotSensSpinner	
	 * 	
	 * @return ddddbb.DoubleSpinner	
	 */
	private DoubleSpinner getMouseRotSensSpinner() {
		if (mouseRotSensSpinner == null) {
			mouseRotSensSpinner = new DoubleSpinner(sv.mouseRotSens);
		}
		return mouseRotSensSpinner;
	}

	/**
	 * This method initializes barEyeFocusDeltaSpinner	
	 * 	
	 * @return ddddbb.DoubleSpinner	
	 */
	private DoubleSpinner getBarEyeFocusDeltaSpinner() {
		if (barEyeFocusDeltaSpinner == null) {
			barEyeFocusDeltaSpinner = new DoubleSpinner(new DoubleUnitModel(sv.barEyeFocusDelta,sv.lengthUnit));
		}
		return barEyeFocusDeltaSpinner;
	}

	/**
	 * This method initializes brightnessSlider	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getBrightnessSlider() {
		if (brightnessSlider == null) {
			brightnessSlider = new JSlider();
			brightnessSlider.setModel(sv.brightness.boundedRange);
		}
		return brightnessSlider;
	}

}
