package ddddbb.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ddddbb.game.Scene;
import ddddbb.game.Main.ViewAbsRel;
import ddddbb.game.Main.ViewAbsRelEnum;
import ddddbb.gen.BoolModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.IntStringModel;
import ddddbb.gui3d.DPanel;
import ddddbb.math.Camera3d;
import ddddbb.math.Camera4d;


@SuppressWarnings("serial")
public class ControlPanel extends DPanel {
	private final JPanel mouseSelector;
	private final JPanel viewAbsRelSelector;
	private final JLabel viewAbsRelLabel;
	private final JPanel mouseNCam3d;
	private final JLabel mouseLabel;
	
	
	public ControlPanel(
			Scene scene,
			BoolModel showGoal,
			DoubleModel zoom, 
			ViewAbsRelEnum viewAbsRel, 
			IntStringModel dim34) {
		super();
		{
			viewAbsRelSelector = new DPanel();
			{
				viewAbsRelLabel = new JLabel();
				viewAbsRelLabel.setText("coords");
			}
			viewAbsRelSelector.add(viewAbsRelLabel);
			viewAbsRel.addAsRadioButtons(viewAbsRelSelector);
			
		}
		{
			mouseSelector = new DPanel();
			{
				mouseLabel = new JLabel();
				mouseLabel.setText("Mouse");
			}
			mouseSelector.add(mouseLabel);
			dim34.addAsRadioButtons(mouseSelector);
		}
		{
			mouseNCam3d = new DPanel();
			mouseNCam3d.setLayout(new BoxLayout(mouseNCam3d,BoxLayout.Y_AXIS));
			mouseNCam3d.add(viewAbsRelSelector);
			mouseNCam3d.add(mouseSelector);
			mouseNCam3d.add(new Cam3dControlPanel(scene.camera3d,viewAbsRel));
		}
		
		add(new ObjectControlPanel(scene,showGoal), null);
		add(new Cam4dControlPanel(scene.camera4d, zoom,viewAbsRel), null);
		add(mouseNCam3d, null );
	}
}
