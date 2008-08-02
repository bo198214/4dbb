package ddddbb.gui;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ddddbb.game.Compound;
import ddddbb.game.Opt;
import ddddbb.gen.MyChangeListener;

public class ViewPane extends JPanel {
	private static final long serialVersionUID = -6532380335237721850L;

	private ViewControlPanel viewControlPanel = null;

	private CompoundControlPanel compoundControlPanel = null;

	private JPanel miscPanel = null;

//	private ViewScreen viewScreen = null;

	private JPanel modelPanel = null;

	private JPanel compoundSelectPanel = null;

	private JPanel southPanel = null;

	protected JLabel objectiveLabel = null;

	private JPanel compoundCoordinatesPanel = null;

	JLabel compoundx1Label = null;

	JLabel compoundx2Label = null;

	JLabel compoundx3Label = null;

	JLabel compoundx4Label = null;

	JLabel selectedCompoundLabel = null;

	/**
	 * This is the default constructor
	 */
	public ViewPane() {
		super();
		initialize();
	}

	/**
	 * This method initializes unitComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	/**
	 * This method initializes viewControlPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getViewControlPanel() {
		if (viewControlPanel == null) {
			viewControlPanel = new ViewControlPanel();
			Opt.scene.addChangeListener(viewControlPanel);
		}
		return viewControlPanel;
	}

	/**
	 * This method initializes compoundControlPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private CompoundControlPanel getCompoundControlPanel() {
		if (compoundControlPanel == null) {
			compoundControlPanel = new CompoundControlPanel();
		}
		return compoundControlPanel;
	}

	/**
	 * This method initializes miscPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMiscPanel() {
		if (miscPanel == null) {
			miscPanel = new JPanel();
			miscPanel.setLayout(new BoxLayout(miscPanel, BoxLayout.Y_AXIS));
			miscPanel.add(getModelPanel(), null);
		}
		return miscPanel;
	}

	/**
	 * This method initializes viewScreen	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	ViewScreen getViewScreen() {
		if (Opt.viewScreen == null) {
			Opt.viewScreen = new ViewScreen();
//			canvasPanelCardLayout = new CardLayout(); 
//			viewScreen = new JPanel(canvasPanelCardLayout);
////			System.out.println(ViewType.selected);
//
//			Opt.viewType.addAsCards(viewScreen,canvasPanelCardLayout);
////			viewScreen.setFocusable(true);
////			viewScreen.requestFocusInWindow();
////			viewScreen.addKeyListener(new KeyControl());
////			viewScreen.addMouseListener(new MouseListener() {
////				public void mouseClicked(MouseEvent e) {}
////				public void mouseEntered(MouseEvent e) {
////					viewScreen.requestFocus();
////				}
////				public void mouseExited(MouseEvent e) {	}
////				public void mousePressed(MouseEvent e) {}
////				public void mouseReleased(MouseEvent e) {}
////			});
		}
		return Opt.viewScreen;
	}

	/**
	 * This method initializes modelPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getModelPanel() {
		if (modelPanel == null) {
			modelPanel = new JPanel();
			modelPanel.setLayout(new BoxLayout(modelPanel,BoxLayout.Y_AXIS));
			modelPanel.setBorder(BorderFactory.createTitledBorder("object control"));
			modelPanel.add(getCompoundCoordinatesPanel(), null);
			modelPanel.add(getCompoundControlPanel(), null);
			modelPanel.add(getCompoundSelectPanel(), null);
		}
		return modelPanel;
	}

	/**
	 * This method initializes compoundSelectPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCompoundSelectPanel() {
		if (compoundSelectPanel == null) {
			compoundSelectPanel = new JPanel();
			compoundSelectPanel.setLayout(new BoxLayout(compoundSelectPanel,BoxLayout.Y_AXIS));
		}
		return compoundSelectPanel;
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
		this.add(getViewScreen(), java.awt.BorderLayout.CENTER);
		this.add(getSouthPanel(), java.awt.BorderLayout.SOUTH);
//		add(getCanvasPanel(), java.awt.BorderLayout.CENTER);
//		this.add(getSouthPanel(), java.awt.BorderLayout.SOUTH);
//		Opt.viewType.addChangeListener(new ChangeListener(){
//			public void stateChanged(ChangeEvent e) {
//				showSelectedViewType();
//			}
//		});
	}

	/**
	 * This method initializes southPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSouthPanel() {
		if (southPanel == null) {
			southPanel = new JPanel();
//			southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.X_AXIS));
			southPanel.add(getMiscPanel(), null);
			southPanel.add(getViewControlPanel(), null);
		}
		return southPanel;
	}

	public JLabel getObjectiveLabel() {
		if (objectiveLabel == null) {
			objectiveLabel = new JLabel();
			Opt.objectives.addChangeListener(new MyChangeListener() {
				public void stateChanged() {
					objectiveLabel.setText(Opt.objectives.getSelectedObject().toString());
				}});
		}
		return objectiveLabel;
	}

	/**
	 * This method initializes compoundCoordinatesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCompoundCoordinatesPanel() {
		if (compoundCoordinatesPanel == null) {
			selectedCompoundLabel = new JLabel();
			selectedCompoundLabel.setText("comp");
			compoundx4Label = new JLabel();
			compoundx4Label.setText("x4");
			compoundx3Label = new JLabel();
			compoundx3Label.setText("x3");
			compoundx2Label = new JLabel();
			compoundx2Label.setText("x2");
			compoundx1Label = new JLabel();
			compoundx1Label.setText("x1");
			Opt.scene.compounds.addChangeListener(new MyChangeListener(){
				public void stateChanged() {
					int i = Opt.scene.compounds.getSelected();
					if (i==-1) {
						selectedCompoundLabel.setText("none selected");
						return;
					}
					selectedCompoundLabel.setText((i+1)+" of 1.." + Opt.scene.compounds.size()+": ");
				}
			});
			Opt.scene.addChangeListener(new MyChangeListener() {
				private DecimalFormat nf = new DecimalFormat("###");
				public void stateChanged() {
					Compound co = Opt.scene.compounds.getSelectedItem();
					if (co==null) {
						compoundx1Label.setText("n/a");
						compoundx2Label.setText("n/a");
						compoundx3Label.setText("n/a");
						compoundx4Label.setText("n/a");	
						return;
					}
					int[] c = co.center.origin();
					compoundx1Label.setText("("+nf.format(c[0]));
					compoundx2Label.setText(","+nf.format(c[1]));
					compoundx3Label.setText(","+nf.format(c[2]));
					compoundx4Label.setText(","+nf.format(c[3])+")");
				}
			});

			compoundCoordinatesPanel = new JPanel();
//			compoundCoordinatesPanel.setLayout(new BoxLayout(compoundCoordinatesPanel,BoxLayout.X_AXIS));
			compoundCoordinatesPanel.add(selectedCompoundLabel, null);
			compoundCoordinatesPanel.add(compoundx1Label, null);
			compoundCoordinatesPanel.add(compoundx2Label, null);
			compoundCoordinatesPanel.add(compoundx3Label, null);
			compoundCoordinatesPanel.add(compoundx4Label, null);
			Opt.showGoal.addAsJToggleButton(compoundCoordinatesPanel);
			compoundCoordinatesPanel.add(getObjectiveLabel());
		}
		return compoundCoordinatesPanel;
	}

//	public String toString() {
//		return "View Panel";
//	}
}  //  @jve:decl-index=0:visual-constraint="10,12"
