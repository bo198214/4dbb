package ddddbb.gui;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ddddbb.game.Opt;
import ddddbb.gen.MyChangeListener;

public class ViewPane extends JPanel {
	private static final long serialVersionUID = -6532380335237721850L;

	private Cam4dControlPanel viewControlPanel = null;

//	private ViewScreen viewScreen = null;

	private JPanel modelPanel = null;

	private JPanel southPanel = null;

	protected JLabel objectiveLabel = null;

	JLabel compoundx1Label = null;

	JLabel compoundx2Label = null;

	JLabel compoundx3Label = null;

	JLabel compoundx4Label = null;

	JLabel selectedCompoundLabel = null;

	public static DecimalFormat nf = new DecimalFormat("###");
	public static DecimalFormat fnf = new DecimalFormat("#.#");
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
	private JPanel getCam4dControlPanel() {
		if (viewControlPanel == null) {
			viewControlPanel = new Cam4dControlPanel();
			//Opt.scene.addChangeListener(viewControlPanel);
		}
		return viewControlPanel;
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
	private JPanel getObjectControlPanel() {
		if (modelPanel == null) {
			modelPanel = new ObjectControlPanel();
		}
		return modelPanel;
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
			southPanel = new ControlPanel();
		}
		return southPanel;
	}

	private JLabel getObjectiveLabel() {
		if (objectiveLabel == null) {
			objectiveLabel = new JLabel();
			Opt.objectives.addChangeListener(new MyChangeListener() {
				public void stateChanged() {
					objectiveLabel.setText(Opt.objectives.getSelectedObject().toString());
				}});
		}
		return objectiveLabel;
	}

//	public String toString() {
//		return "View Panel";
//	}
}  //  @jve:decl-index=0:visual-constraint="10,12"
