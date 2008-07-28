package ddddbb.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;

import ddddbb.game.Opt;
import ddddbb.gui3d.ButtonLeft3d;
import ddddbb.math.AOP;
import ddddbb.math.Point3d;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
public class Cam3dControlPanel extends JPanel {
	private JLabel xLabel;
	private JLabel yLabel;
	private JSeparator vSeparator;
	private JTextField yzVal;
	private JTextField xzVal;
	private JTextField yPos;
	private JTextField zPos;
	private JTextField xPos;
	private JButton reset;
	private JButton yzRight;
	private JButton xzRight;
	private JButton yzLeft;
	private JButton xzLeft;
	private JLabel yzLabel;
	private JLabel xzLabel;
	private JButton zRight;
	private JButton yRight;
	private JButton xRight;
	private JButton zLeft;
	private JButton yLeft;
	private JButton xLeft;
	private JLabel zLabel;

	public Cam3dControlPanel() {
		initGUI();
		
		xRight.addActionListener(UIAction.transCam3d(1));
		yRight.addActionListener(UIAction.transCam3d(2));
		zRight.addActionListener(UIAction.transCam3d(3));
		xLeft.addActionListener(UIAction.transCam3d(-1));
		yLeft.addActionListener(UIAction.transCam3d(-2));
		zLeft.addActionListener(UIAction.transCam3d(-3));
		
		xzRight.addActionListener(UIAction.rotCam3d(1, 3));
		yzRight.addActionListener(UIAction.rotCam3d(2, 3));
		xzLeft.addActionListener(UIAction.rotCam3d(3, 1));
		yzLeft.addActionListener(UIAction.rotCam3d(3, 2));
		
		reset.addActionListener(UIShownAction.cam3dReset);
		
		Opt.scene.camera3d.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double[] o = Opt.scene.camera3d.eye.x;
				xPos.setText(ViewPane.fnf.format(o[0]));
				yPos.setText(ViewPane.fnf.format(o[1]));
				zPos.setText(ViewPane.fnf.format(o[2]));
				
				Point3d v = Opt.scene.camera3d.viewingDirection();
				Point3d x = AOP.unitVector3(0);
				Point3d y = AOP.unitVector3(1);
				Point3d z = AOP.unitVector3(2);
				double vx=x.sc(v);
				double vy=y.sc(v);
				double vz=z.sc(v);
				double axz = Math.atan2(vz, vx);
				double ayz = Math.atan2(vz, vy);
				int axzd = (int)Math.round(axz/Math.PI/2*360);
				int ayzd = (int)Math.round(ayz/Math.PI/2*360);
				xzVal.setText(ViewPane.nf.format(axzd));
				yzVal.setText(ViewPane.nf.format(ayzd));
			}});
	}
	
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.01, 0.01, 0.0};
				thisLayout.rowHeights = new int[] {7, 7, 7};
				thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				thisLayout.columnWidths = new int[] {15, 15, 40, 15, 7, 20, 15, 40, 15};
				this.setLayout(thisLayout);
				this.setBorder(BorderFactory.createTitledBorder("3d camera control"));
				this.setPreferredSize(new java.awt.Dimension(199, 99));
				{
					xLabel = new JLabel();
					this.add(xLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					xLabel.setText("x");
				}
				{
					yLabel = new JLabel();
					this.add(yLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					yLabel.setText("y");
				}
				{
					zLabel = new JLabel();
					this.add(zLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					zLabel.setText("z");
				}
				{
					xLeft = new ButtonLeft3d();
					this.add(xLeft, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					yLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(yLeft, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					zLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(zLeft, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(xRight, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					yRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(yRight, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					zRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(zRight, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xzLabel = new JLabel();
					this.add(xzLabel, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					xzLabel.setText("xz");
				}
				{
					yzLabel = new JLabel();
					this.add(yzLabel, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					yzLabel.setText("yz");
				}
				{
					xzLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(xzLeft, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					yzLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(yzLeft, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xzRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(xzRight, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					yzRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(yzRight, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					reset = new JButton();
					this.add(reset, new GridBagConstraints(5, 2, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					reset.setText("reset");
				}
				{
					xPos = new JTextField(3);
					this.add(xPos, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					xPos.setText("nan");
					xPos.setEditable(false);
				}
				{
					yPos = new JTextField(3);
					this.add(yPos, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					yPos.setText("nan");
					yPos.setEditable(false);
				}
				{
					zPos = new JTextField(3);
					this.add(zPos, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					zPos.setText("nan");
					zPos.setEditable(false);
				}
				{
					xzVal = new JTextField(3);
					this.add(xzVal, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					xzVal.setText("nan");
					xzVal.setEditable(false);
				}
				{
					yzVal = new JTextField(3);
					this.add(yzVal, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					yzVal.setText("nan");
					yzVal.setEditable(false);
				}
				{
					vSeparator = new JSeparator(SwingConstants.VERTICAL);
					this.add(vSeparator, new GridBagConstraints(4, 0, 1, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
