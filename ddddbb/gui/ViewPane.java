package ddddbb.gui;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.net.ssl.SSLContext;

import ddddbb.game.Objectives;
import ddddbb.game.Scene;
import ddddbb.game.Scene4d;
import ddddbb.game.ScreenValues;
import ddddbb.game.SimpleSwitches;
import ddddbb.game.Main.ViewAbsRelEnum;
import ddddbb.gen.BoolModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.IntStringModel;
import ddddbb.gen.MyChangeListener;
import ddddbb.math.Camera4d;

@SuppressWarnings("serial")
public class ViewPane extends JPanel {
	public final ViewScreen viewScreen;

	private final JPanel controlPanel;
	protected final JLabel objectiveLabel;

	public static DecimalFormat nf = new DecimalFormat("###");
	public static DecimalFormat fnf = new DecimalFormat("#.#");

	public ViewPane(
			final Scene scene,
			final BoolModel showGoal,
			final DoubleModel zoom,
			final ViewAbsRelEnum viewAbsRel,
			final IntStringModel dim34,
			final IntModel<Objectives> objectives,
			final ScreenValues sv,
			final IntModel<Camera4d> perspective,
			final BoolModel drawTetrahedral,
			final BoolModel showInternalFaces,
			final BoolModel antiAliased,
			final IntModel<SimpleSwitches.ViewType> viewType,
			final IntStringModel viewTransAxis,
			final IntStringModel d3ViewRotAxis,
			final Scene4d goalScene) {
		viewScreen = new ViewScreen(scene,sv,perspective,drawTetrahedral,showInternalFaces,zoom,antiAliased,viewType,dim34,
				viewAbsRel,viewTransAxis,d3ViewRotAxis,showGoal,goalScene);
		controlPanel = new ControlPanel(scene,showGoal,zoom,viewAbsRel,dim34, sv.brightness);
		objectiveLabel = new JLabel();
		objectives.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				objectiveLabel.setText(objectives.getSelectedObject().toString());
			}});
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
