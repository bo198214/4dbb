package ddddbb.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ddddbb.game.Main.ViewAbsRelEnum;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.MyChangeListener;
import ddddbb.gui3d.DArrowButton;
import ddddbb.gui3d.DLabel;
import ddddbb.gui3d.DPanel;
import ddddbb.math.AOP;
import ddddbb.math.Camera4d;
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
public class Cam4dControlPanel extends DPanel {
	private JLabel xLabel;
	private JLabel yLabel;
	private JSeparator jSeparator1;
	private JLabel ywVal;
	private JLabel xzVal;
	private JLabel zoomVal;
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
	private JLabel wPos;
	private JLabel zPos;
	private JLabel yPos;
	private JLabel xPos;
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

	public Cam4dControlPanel(final Camera4d camera4d, 
			final DoubleModel zoom,
			final ViewAbsRelEnum viewAbsRel, DoubleModel brightness) {
		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] {0.00, 0.00, 0.00, 0.0};
		thisLayout.rowHeights = new int[] {16, 16, 16, 16};
		thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		thisLayout.columnWidths = new int[] {16, 16, 40, 16, 8, 30, 16, 40, 15};
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
			xLeft = new DArrowButton(SwingConstants.WEST, brightness);
			this.add(xLeft, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			yLeft = new DArrowButton(SwingConstants.WEST, brightness);
			this.add(yLeft, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			zLeft = new DArrowButton(SwingConstants.WEST, brightness);
			this.add(zLeft, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			wLeft = new DArrowButton(SwingConstants.WEST, brightness);
			this.add(wLeft, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			xRight = new DArrowButton(SwingConstants.EAST, brightness);
			this.add(xRight, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			yRight = new DArrowButton(SwingConstants.EAST, brightness);
			this.add(yRight, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			zRight = new DArrowButton(SwingConstants.EAST, brightness);
			this.add(zRight, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			wRight = new DArrowButton(SwingConstants.EAST, brightness);
			this.add(wRight, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			xPos = new DLabel(30,16, brightness);
			this.add(xPos, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			yPos = new DLabel(30,16, brightness);
			this.add(yPos, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			zPos = new DLabel(30,16, brightness);
			this.add(zPos, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			wPos = new DLabel(30,16, brightness);
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
			reset.setSize(50,16);
			this.add(reset, new GridBagConstraints(5, 3, 5, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			reset.setText("reset");
		}
		{
			zoomLeft = new DArrowButton(SwingConstants.WEST, brightness);
			this.add(zoomLeft, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			zoomRight = new DArrowButton(SwingConstants.EAST, brightness);
			this.add(zoomRight, new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			xzLeft = new DArrowButton(SwingConstants.WEST, brightness);
			this.add(xzLeft, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			xzRight = new DArrowButton(SwingConstants.EAST, brightness);
			this.add(xzRight, new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			ywLeft = new DArrowButton(SwingConstants.WEST, brightness);
			this.add(ywLeft, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			ywRight = new DArrowButton(SwingConstants.EAST, brightness);
			this.add(ywRight, new GridBagConstraints(8, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			jSeparator1 = new JSeparator(SwingConstants.VERTICAL);
			this.add(jSeparator1, new GridBagConstraints(4, 0, 1, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			zoomVal = new DLabel(30,16, brightness);
			this.add(zoomVal, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			xzVal = new DLabel(30,16, brightness);
			this.add(xzVal, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			ywVal = new DLabel(30,16, brightness);
			this.add(ywVal, new GridBagConstraints(7, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		
		Camera4dAction c4a = new Camera4dAction(camera4d, viewAbsRel);
		
		xRight.addActionListener(c4a.transCam4d(1));
		yRight.addActionListener(c4a.transCam4d(2));
		zRight.addActionListener(c4a.transCam4d(3));
		wRight.addActionListener(c4a.transCam4d(4));
		xLeft.addActionListener(c4a.transCam4d(-1));
		yLeft.addActionListener(c4a.transCam4d(-2));
		zLeft.addActionListener(c4a.transCam4d(-3));
		wLeft.addActionListener(c4a.transCam4d(-4));
		
		xzRight.addActionListener(c4a.rotCam4d(1, 3));
		xzLeft.addActionListener(c4a.rotCam4d(3, 1));
		ywRight.addActionListener(c4a.rotCam4d(2, 4));
		ywLeft.addActionListener(c4a.rotCam4d(4, 2));
		
		reset.addActionListener(camera4d.reset);
		zoomRight.addActionListener(zoom.increase);
		zoomLeft.addActionListener(zoom.decrease);
		
		zoom.addChangeListener(new MyChangeListener() {
			@Override
			public void stateChanged() {
				zoomVal.setText(ViewPane.fnf.format(zoom.getDouble()));
			}			
		});
		camera4d.addChangeListener(new MyChangeListener() {
			@Override
			public void stateChanged() {
				double[] o = camera4d.eye.x;
				xPos.setText(ViewPane.fnf.format(o[0]));
				yPos.setText(ViewPane.fnf.format(o[1]));
				zPos.setText(ViewPane.fnf.format(o[2]));
				wPos.setText(ViewPane.fnf.format(o[3]));
				
				Point4d v = camera4d.viewingDirection();
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
}
