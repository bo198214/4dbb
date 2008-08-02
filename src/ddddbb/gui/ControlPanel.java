package ddddbb.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ddddbb.game.Level;
import ddddbb.game.Settings;
import ddddbb.gui3d.DPanel;


@SuppressWarnings("serial")
public class ControlPanel extends DPanel {
	private final JPanel mouseSelector;
	private final JPanel viewAbsRelSelector;
	private final JLabel viewAbsRelLabel;
	private final JPanel mouseNCam3d;
	private final JLabel mouseLabel;
	
	
	public ControlPanel(
			Settings ss,
			Level scene) {
		{
			viewAbsRelSelector = new DPanel();
			{
				viewAbsRelLabel = new JLabel();
				viewAbsRelLabel.setText("coords");
			}
			viewAbsRelSelector.add(viewAbsRelLabel);
			scene.viewAbsRel.addAsCheckBoxMenuItem(viewAbsRelSelector);
			
		}
		{
			mouseSelector = new DPanel();
			{
				mouseLabel = new JLabel();
				mouseLabel.setText("Mouse");
			}
			mouseSelector.add(mouseLabel);
			ss.dim34.addAsRadioButtons(mouseSelector);
		}
		{
			mouseNCam3d = new DPanel();
			mouseNCam3d.setLayout(new BoxLayout(mouseNCam3d,BoxLayout.Y_AXIS));
			mouseNCam3d.add(viewAbsRelSelector);
			mouseNCam3d.add(mouseSelector);
			mouseNCam3d.add(new Cam3dControlPanel(scene));
		}
		
		add(new ObjectControlPanel(scene,ss.showGoal, ss.brightness), null);
		add(new Cam4dControlPanel(scene, ss.zoom,ss.brightness), null);
		add(mouseNCam3d, null );
	}
}
