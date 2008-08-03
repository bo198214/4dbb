package ddddbb.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import ddddbb.game.Scene4d;
import ddddbb.game.Settings;
import ddddbb.gen.AChangeListener;
import ddddbb.gui3d.DArrowButton;
import ddddbb.gui3d.DButton;
import ddddbb.gui3d.DDisplay;
import ddddbb.gui3d.DLabel;
import ddddbb.gui3d.DPanel;
import ddddbb.gui3d.DRadioButton;
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
public class Cam3dControlPanel extends DPanel {
	private JLabel xLabel;
	private JLabel yLabel;
	private JSeparator vSeparator;
	private DDisplay yzVal;
	private DDisplay xzVal;
	private DDisplay yPos;
	private DDisplay zPos;
	private DDisplay xPos;
	private JButton reset;
	private JButton xyLeft;
	private JButton xyRight;
	private JLabel xyLabel;
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

	private static GridBagConstraints gbc(int x, int y) {
		GridBagConstraints res = new GridBagConstraints();
		res.gridx = x;
		res.gridy = y;
		return res;
	}
	private static GridBagConstraints gbc(int x, int y, int width) {
		GridBagConstraints res = new GridBagConstraints();
		res.gridx = x;
		res.gridy = y;
		res.gridwidth = width;
		return res;
	}
	private static GridBagConstraints gbc(int x, int y, int width, int height) {
		GridBagConstraints res = new GridBagConstraints();
		res.gridx = x;
		res.gridy = y;
		res.gridwidth = width;
		res.gridheight = height;
		return res;
	}

	public Cam3dControlPanel(
			final Scene4d scene
			) {
		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0};
		thisLayout.rowHeights = new int[] {18, 18, 18, 18};
		thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		thisLayout.columnWidths = new int[] {16, 16, 40, 16, 7, 20, 16, 40, 16, 16};
		this.setLayout(thisLayout);
		this.setBorder(BorderFactory.createTitledBorder("3d camera control"));
		this.setPreferredSize(new java.awt.Dimension(216, 100));
		{
			xLabel = new DLabel();
			this.add(xLabel, gbc(0,0));
			xLabel.setText("x");
		}
		{
			yLabel = new DLabel();
			this.add(yLabel, gbc(0,1));
			yLabel.setText("y");
		}
		{
			zLabel = new DLabel();
			this.add(zLabel, gbc(0,2));
			zLabel.setText("z");
		}
		{
			xLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xLeft, gbc(1,0));
		}
		{
			yLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yLeft, gbc(1,1));
		}
		{
			zLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zLeft, gbc(1,2));
		}
		{
			xRight = new DArrowButton(SwingConstants.EAST);
			this.add(xRight, gbc(3,0));
		}
		{
			yRight = new DArrowButton(SwingConstants.EAST);
			this.add(yRight, gbc(3,1));
		}
		{
			zRight = new DArrowButton(SwingConstants.EAST);
			this.add(zRight, gbc(3,2));
		}
		{
			xzLabel = new DLabel();
			this.add(xzLabel, gbc(5,0));
			xzLabel.setText("xz");
		}
		{
			yzLabel = new DLabel();
			this.add(yzLabel, gbc(5,1));
			yzLabel.setText("yz");
		}
		{
			xyLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xyLeft, gbc(6,0));
		}
		{
			xzLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xzLeft, gbc(6,1));
		}
		{
			yzLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yzLeft, gbc(6,2));
		}
		{
			xyRight = new DArrowButton(SwingConstants.EAST);
			this.add(xyRight, gbc(8,0));
		}
		{
			xzRight = new DArrowButton(SwingConstants.EAST);
			this.add(xzRight, gbc(8,1));
		}
		{
			yzRight = new DArrowButton(SwingConstants.EAST);
			this.add(yzRight, gbc(8,2));
		}
		{
			reset = new DButton("reset");
			this.add(reset, gbc(0, 3, 4));
		}
		{
			DRadioButton xy = new DRadioButton();
			Settings.mouseRotDiAxes3d.addButton(Settings.DiAxis3d.XY, xy);
			this.add(xy,gbc(9,0));
		}
		{
			DRadioButton xz = new DRadioButton();
			Settings.mouseRotDiAxes3d.addButton(Settings.DiAxis3d.XZ, xz);
			this.add(xz,gbc(9,1));
		}
		{
			DRadioButton yz = new DRadioButton();
			Settings.mouseRotDiAxes3d.addButton(Settings.DiAxis3d.YZ, yz);
			this.add(yz,gbc(9,2));
		}
		{
			xPos = new DDisplay(30,16);
			this.add(xPos, gbc(2,0));
			xPos.setText("nan");
		}
		{
			yPos = new DDisplay(30,16);
			this.add(yPos, gbc(2,1));
			yPos.setText("nan");
		}
		{
			zPos = new DDisplay(30,16);
			this.add(zPos, gbc(2,2));
			zPos.setText("nan");
		}
		{
			xzVal = new DDisplay(30,16);
			this.add(xzVal, gbc(7,0));
			xzVal.setText("nan");
		}
		{
			yzVal = new DDisplay(30,16);
			this.add(yzVal, gbc(7,1));
			yzVal.setText("nan");
		}
		{
			vSeparator = new JSeparator(SwingConstants.VERTICAL);
			this.add(vSeparator, new GridBagConstraints(4, 0, 1, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		
		xRight.addActionListener(scene.transCam3dAction(1));
		yRight.addActionListener(scene.transCam3dAction(2));
		zRight.addActionListener(scene.transCam3dAction(3));
		xLeft.addActionListener(scene.transCam3dAction(-1));
		yLeft.addActionListener(scene.transCam3dAction(-2));
		zLeft.addActionListener(scene.transCam3dAction(-3));
		
		xyRight.addActionListener(scene.rotCam3dAction(1, 2));
		xzRight.addActionListener(scene.rotCam3dAction(1, 3));
		yzRight.addActionListener(scene.rotCam3dAction(2, 3));
		xyLeft.addActionListener(scene.rotCam3dAction(2, 1));
		xzLeft.addActionListener(scene.rotCam3dAction(3, 1));
		yzLeft.addActionListener(scene.rotCam3dAction(3, 2));
		
		
		reset.addActionListener(scene.camera3d.resetAction);
		
		new AChangeListener() {
			public void stateChanged() {
				double[] o = scene.camera3d.eye.x;
				xPos.setText(ViewPane.fnf.format(o[0]));
				yPos.setText(ViewPane.fnf.format(o[1]));
				zPos.setText(ViewPane.fnf.format(o[2]));
				
				Point3d v = scene.camera3d.viewingDirection();
				Point3d x = AOP.unitVector3(0);
				Point3d y = AOP.unitVector3(1);
				Point3d z = AOP.unitVector3(2);
				double vx=x.sc(v);
				double vy=y.sc(v);
				double vz=z.sc(v);
				double axz = AOP.angle02pi(Math.atan2(vz, vx));
				double ayz = AOP.angle02pi(Math.atan2(vz, vy));
				int axzd = (int)Math.round(axz/Math.PI/2*360);
				int ayzd = (int)Math.round(ayz/Math.PI/2*360);
				xzVal.setText(ViewPane.nf.format(axzd));
				yzVal.setText(ViewPane.nf.format(ayzd));
			}}.addTo(scene.camera3d);
	}
}
