package ddddbb.gui;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ddddbb.game.Level;
import ddddbb.game.Scene4d;
import ddddbb.game.Settings;
import ddddbb.gen.AChangeListener;
import ddddbb.gen.MyChangeListener;

@SuppressWarnings("serial")
public class ViewPane extends JPanel {
	public final ViewScreen viewScreen;

	private final JPanel controlPanel;
	protected final JLabel objectiveLabel;

	public static DecimalFormat nf = new DecimalFormat("###");
	public static DecimalFormat fnf = new DecimalFormat("#.#");

	public ViewPane(
			final Settings ss, final Level scene, final Scene4d goalScene) {
		viewScreen = new ViewScreen(ss,scene,goalScene);
		controlPanel = new ControlPanel(ss,scene);
		objectiveLabel = new JLabel();
		new AChangeListener() {
			public void stateChanged() {
				objectiveLabel.setText(ss.objectives.getSelectedObject().toString());
			}}.addTo(ss.objectives);
		this.setSize(1002, 655);
		setLayout(new BorderLayout());
		//add(getViewPanel(), BorderLayout.CENTER);
		this.add(viewScreen, java.awt.BorderLayout.CENTER);

		//this.add(southPanel, java.awt.BorderLayout.SOUTH);
		viewScreen.add(controlPanel);
		
		
//		add(getCanvasPanel(), java.awt.BorderLayout.CENTER);
//		this.add(getSouthPanel(), java.awt.BorderLayout.SOUTH);
//		Opt.viewType.addChangeListener(new ChangeListener(){
//			public void stateChanged(ChangeEvent e) {
//				showSelectedViewType();
//			}
//		});
	}
}  //  @jve:decl-index=0:visual-constraint="10,12"
