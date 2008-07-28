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
import ddddbb.math.AOP;
import ddddbb.math.Point4d;

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
public class Cam4dControlPanel extends JPanel {
	public static class Val3Digits extends JTextField {
		public Val3Digits() {
			super(3);
			setEditable(false);
			setText("0");
		}
	}
	
	private JLabel xLabel;
	private JLabel yLabel;
	private JSeparator jSeparator1;
	private JTextField ywVal;
	private JTextField xzVal;
	private JTextField zoomVal;
	private JButton ywRight;
	private JButton ywLeft;
	private JButton xzRight;
	private JButton xzLeft;
	private JButton zoomRight;
	private JButton zoomLeft;
	private JButton reset;
	private JLabel ywLabel;
	private JLabel xzLabel;
	private JLabel zoomLabel;
	private JTextField wPos;
	private JTextField zPos;
	private JTextField yPos;
	private JTextField xPos;
	private JButton wRight;
	private JButton zRight;
	private JButton yRight;
	private JButton xRight;
	private JButton wLeft;
	private JButton zLeft;
	private JButton yLeft;
	private JButton xLeft;
	private JLabel wLabel;
	private JLabel zLabel;

	public Cam4dControlPanel() {
		initGUI();
		
		xRight.addActionListener(UIAction.transCam4d(1));
		yRight.addActionListener(UIAction.transCam4d(2));
		zRight.addActionListener(UIAction.transCam4d(3));
		wRight.addActionListener(UIAction.transCam4d(4));
		xLeft.addActionListener(UIAction.transCam4d(-1));
		yLeft.addActionListener(UIAction.transCam4d(-2));
		zLeft.addActionListener(UIAction.transCam4d(-3));
		wLeft.addActionListener(UIAction.transCam4d(-4));
		
		xzRight.addActionListener(UIAction.rotCam4d(1, 3));
		xzLeft.addActionListener(UIAction.rotCam4d(3, 1));
		ywRight.addActionListener(UIAction.rotCam4d(2, 4));
		ywLeft.addActionListener(UIAction.rotCam4d(4, 2));
		
		reset.addActionListener(UIShownAction.cam4dReset);
		zoomRight.addActionListener(UIShownAction.zoomIn);
		zoomLeft.addActionListener(UIShownAction.zoomOut);
		
		Opt.zoom.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				zoomVal.setText(ViewPane.fnf.format(Opt.zoom.getDouble()));
			}			
		});
		Opt.scene.camera4d.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				double[] o = Opt.scene.camera4d.eye.x;
				xPos.setText(ViewPane.fnf.format(o[0]));
				yPos.setText(ViewPane.fnf.format(o[1]));
				zPos.setText(ViewPane.fnf.format(o[2]));
				wPos.setText(ViewPane.fnf.format(o[3]));
				
				Point4d v = Opt.scene.camera4d.viewingDirection();
				double vx = v.sc(AOP.unitVector4(0));
				double vy = v.sc(AOP.unitVector4(1));
				double vz = v.sc(AOP.unitVector4(2));
				double vw = v.sc(AOP.unitVector4(3));
				double axz = Math.atan2(vz,vx);
				double ayw = Math.atan2(vw,vy);
				int axzd = (int) Math.round(axz/Math.PI/2*360);
				int aywd = (int) Math.round(ayw/Math.PI/2*360);
				xzVal.setText(""+axzd);
				ywVal.setText(""+aywd);
			}});
	}
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.01, 0.01, 0.01, 0.0};
				thisLayout.rowHeights = new int[] {7, 7, 7, 7};
				thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
				thisLayout.columnWidths = new int[] {15, 15, 40, 15, 7, 30, 15, 40, 15};
				this.setLayout(thisLayout);
				this.setBorder(BorderFactory.createTitledBorder("4d camera control"));
				this.setPreferredSize(new java.awt.Dimension(222, 130));
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
					wLabel = new JLabel();
					this.add(wLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					wLabel.setText("w");
				}
				{	
					xLeft = new BasicArrowButton(SwingConstants.WEST);
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
					wLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(wLeft, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
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
					wRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(wRight, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xPos = new Val3Digits();
					this.add(xPos, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					yPos = new Val3Digits();
					this.add(yPos, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					zPos = new Val3Digits();
					this.add(zPos, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					wPos = new Val3Digits();
					this.add(wPos, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					zoomLabel = new JLabel();
					this.add(zoomLabel, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					zoomLabel.setText("zoom");
				}
				{
					xzLabel = new JLabel();
					this.add(xzLabel, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					xzLabel.setText("xz");
				}
				{
					ywLabel = new JLabel();
					this.add(ywLabel, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					ywLabel.setText("yw");
				}
				{
					reset = new JButton();
					this.add(reset, new GridBagConstraints(5, 3, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					reset.setText("reset");
				}
				{
					zoomLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(zoomLeft, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					zoomRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(zoomRight, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xzLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(xzLeft, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xzRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(xzRight, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					ywLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(ywLeft, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					ywRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(ywRight, new GridBagConstraints(8, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jSeparator1 = new JSeparator(SwingConstants.VERTICAL);
					this.add(jSeparator1, new GridBagConstraints(4, 0, 1, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					zoomVal = new Val3Digits();
					this.add(zoomVal, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xzVal = new Val3Digits();
					this.add(xzVal, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					ywVal = new Val3Digits();
					this.add(ywVal, new GridBagConstraints(7, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
