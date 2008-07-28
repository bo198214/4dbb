package ddddbb.gui;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ddddbb.game.Opt;


@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	private final JPanel mouseSelector;
	private final JPanel viewAbsRelSelector;
	private final JLabel viewAbsRelLabel;
	private final JPanel mouseNCam3d;
	private final JLabel mouseLabel;
	
	
	public ControlPanel() {
		super();
		{
			viewAbsRelSelector = new JPanel();
			{
				viewAbsRelLabel = new JLabel();
				viewAbsRelLabel.setText("coords");
			}
			viewAbsRelSelector.add(viewAbsRelLabel);
			Opt.viewAbsRel.addAsRadioButtons(viewAbsRelSelector);
			
		}
		{
			mouseSelector = new JPanel();
			{
				mouseLabel = new JLabel();
				mouseLabel.setText("Mouse");
			}
			mouseSelector.add(mouseLabel);
			Opt.dim34.addAsRadioButtons(mouseSelector);
		}
		{
			mouseNCam3d = new JPanel();
			mouseNCam3d.setLayout(new BoxLayout(mouseNCam3d,BoxLayout.Y_AXIS));
			mouseNCam3d.add(viewAbsRelSelector);
			mouseNCam3d.add(mouseSelector);
			mouseNCam3d.add(new Cam3dControlPanel());
		}
		
		add(new ObjectControlPanel(), null);
		add(new Cam4dControlPanel(), null);
		add(mouseNCam3d, null );
	}
}
