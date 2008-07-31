package ddddbb.gui;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

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

public class ViewPane extends JPanel {
	private static final long serialVersionUID = -6532380335237721850L;
	private JPanel southPanel = null;
	protected JLabel objectiveLabel = null;
	JLabel compoundx1Label = null;
	JLabel compoundx2Label = null;
	JLabel compoundx3Label = null;
	JLabel compoundx4Label = null;
	JLabel selectedCompoundLabel = null;
	public ViewScreen viewScreen;

	public static DecimalFormat nf = new DecimalFormat("###");
	public static DecimalFormat fnf = new DecimalFormat("#.#");
	/**
	 * This is the default constructor
	 */
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
			final Scene4d goalScene
		) {
		super();
		viewScreen = new ViewScreen(scene,sv,perspective,drawTetrahedral,showInternalFaces,zoom,antiAliased,viewType,dim34,
				viewAbsRel,viewTransAxis,d3ViewRotAxis,showGoal,goalScene);
		southPanel = new ControlPanel(scene,showGoal,zoom,viewAbsRel,dim34);
		objectiveLabel = new JLabel();
		objectives.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				objectiveLabel.setText(objectives.getSelectedObject().toString());
			}});
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1002, 655);
		setLayout(new BorderLayout());
		//add(getViewPanel(), BorderLayout.CENTER);
		this.add(viewScreen, java.awt.BorderLayout.CENTER);
		this.add(southPanel, java.awt.BorderLayout.SOUTH);
//		add(getCanvasPanel(), java.awt.BorderLayout.CENTER);
//		this.add(getSouthPanel(), java.awt.BorderLayout.SOUTH);
//		Opt.viewType.addChangeListener(new ChangeListener(){
//			public void stateChanged(ChangeEvent e) {
//				showSelectedViewType();
//			}
//		});
	}

//	public String toString() {
//		return "View Panel";
//	}
}  //  @jve:decl-index=0:visual-constraint="10,12"
