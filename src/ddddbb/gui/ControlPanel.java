package ddddbb.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ddddbb.game.Level;
import ddddbb.game.Settings;
import ddddbb.gui3d.DPanel;
import ddddbb.gui3d.DRadioButton;


@SuppressWarnings("serial")
public class ControlPanel extends DPanel {
	
	
	public ControlPanel(
			Settings ss,
			Level scene) {
		
		add(new ObjectControlPanel(scene,ss), null);			
		add(new Cam4dControlPanel(scene, ss), null);
		add(new Cam3dControlPanel(scene,ss), null );
	}
}
