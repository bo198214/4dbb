package ddddbb.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import ddddbb.game.Scene4d;
import ddddbb.gen.AChangeListener;
import ddddbb.gen.DoubleModel;
import ddddbb.gui3d.DArrowButton;
import ddddbb.gui3d.DButton;
import ddddbb.gui3d.DDisplay;
import ddddbb.gui3d.DLabel;
import ddddbb.gui3d.DPanel;
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
	private DButton reset;
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
	public Cam4dControlPanel(
			final Scene4d scene, 
			final DoubleModel zoom,
			DoubleModel brightness) {
		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] {0.00, 0.00, 0.00, 0.0};
		thisLayout.rowHeights = new int[] {18, 18, 18, 18};
		thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0};
		thisLayout.columnWidths = new int[] {16, 16, 40, 16, 8, 30, 16, 40, 16, 16};
		this.setLayout(thisLayout);
		this.setBorder(BorderFactory.createTitledBorder("4d camera control"));

		{
			int[] ws = thisLayout.columnWidths;
			int width = 0; 
			for (int i=0;i<ws.length;i++) { width+= ws[i]; }
			int[] hs = thisLayout.rowHeights;
			int height = 0;
			for (int i=0;i<hs.length;i++) { height+= hs[i]; }
			this.setPreferredSize(new java.awt.Dimension(width+25, height+25));
		}
		{
			xLabel = new DLabel(14,16,"x");
			this.add(xLabel, gbc(0,0));
			//xLabel.setText("x");
		}
		{
			yLabel = new DLabel(14,16);
			this.add(yLabel, gbc(0,1));
			yLabel.setText("y");
		}
		{
			zLabel = new DLabel(14,16);
			this.add(zLabel, gbc(0,2));
			zLabel.setText("z");
		}
		{
			wLabel = new DLabel(14,16);
			this.add(wLabel, gbc(0,3));
			wLabel.setText("w");
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
			wLeft = new DArrowButton(SwingConstants.WEST);
			this.add(wLeft, gbc(1,3));
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
			wRight = new DArrowButton(SwingConstants.EAST);
			this.add(wRight, gbc(3,3));
		}
		{
			xPos = new DDisplay(30,16);
			this.add(xPos, gbc(2,0));
		}
		{
			yPos = new DDisplay(30,16);
			this.add(yPos, gbc(2,1));
		}
		{
			zPos = new DDisplay(30,16);
			this.add(zPos, gbc(2,2));
		}
		{
			wPos = new DDisplay(30,16);
			this.add(wPos, gbc(2,3));
		}
		{
			zoomLabel = new DLabel();
			this.add(zoomLabel, gbc(5,0));
			zoomLabel.setText("zoom");
		}
		{
			xzLabel = new DLabel(30,16);
			this.add(xzLabel, gbc(5,1));
			xzLabel.setText("xz");
		}
		{
			ywLabel = new DLabel(30,16);
			this.add(ywLabel, gbc(5,2));
			ywLabel.setText("yw");
		}
		{
			reset = new DButton("reset");
			reset.setSize(50,16);
			this.add(reset, gbc(5,3,3));
		}
		{
			zoomLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zoomLeft, gbc(6,0));
		}
		{
			zoomRight = new DArrowButton(SwingConstants.EAST);
			this.add(zoomRight, gbc(8,0));
		}
		{
			xzLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xzLeft, gbc(6,1));
		}
		{
			xzRight = new DArrowButton(SwingConstants.EAST);
			this.add(xzRight, gbc(8,1));
		}
		{
			ywLeft = new DArrowButton(SwingConstants.WEST);
			this.add(ywLeft, gbc(6,2));
		}
		{
			ywRight = new DArrowButton(SwingConstants.EAST);
			this.add(ywRight, gbc(8,2));
		}
		{
			jSeparator1 = new JSeparator(SwingConstants.VERTICAL);
			this.add(jSeparator1, new GridBagConstraints(4, 0, 1, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			zoomVal = new DDisplay(30,16);
			this.add(zoomVal, gbc(7,0));
		}
		{
			xzVal = new DDisplay(30,16);
			this.add(xzVal, gbc(7,1));
		}
		{
			ywVal = new DDisplay(30,16);
			this.add(ywVal, gbc(7,2));
		}
		
		xRight.addActionListener(scene.transCam4dAction(1));
		yRight.addActionListener(scene.transCam4dAction(2));
		zRight.addActionListener(scene.transCam4dAction(3));
		wRight.addActionListener(scene.transCam4dAction(4));
		xLeft.addActionListener(scene.transCam4dAction(-1));
		yLeft.addActionListener(scene.transCam4dAction(-2));
		zLeft.addActionListener(scene.transCam4dAction(-3));
		wLeft.addActionListener(scene.transCam4dAction(-4));
		
		xzRight.addActionListener(scene.rotCam4dAction(1, 3));
		xzLeft.addActionListener(scene.rotCam4dAction(3, 1));
		ywRight.addActionListener(scene.rotCam4dAction(2, 4));
		ywLeft.addActionListener(scene.rotCam4dAction(4, 2));
		
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scene.camera4d.setToDefault();
				//Settings.zoom.setToDefault();
			}
		});
		zoomRight.addActionListener(zoom.increaseAction);
		zoomLeft.addActionListener(zoom.decreaseAction);
		
		new AChangeListener() {
			@Override
			public void stateChanged() {
				zoomVal.setText(ViewPane.fnf.format(zoom.getDouble()));
			}			
		}.addTo(zoom);
		new AChangeListener() {
			@Override
			public void stateChanged() {
				double[] o = scene.camera4d.eye.x;
				xPos.setText(ViewPane.fnf.format(o[0]));
				yPos.setText(ViewPane.fnf.format(o[1]));
				zPos.setText(ViewPane.fnf.format(o[2]));
				wPos.setText(ViewPane.fnf.format(o[3]));
				
				Point4d v = scene.camera4d.viewingDirection();
				double vx = v.sc(AOP.unitVector4(0));
				double vy = v.sc(AOP.unitVector4(1));
				double vz = v.sc(AOP.unitVector4(2));
				double vw = v.sc(AOP.unitVector4(3));
				double axz = AOP.angle02pi(Math.atan2(vz,vx));
				double ayw = AOP.angle02pi(Math.atan2(vw,vy));
				int axzd = (int) Math.round(axz/Math.PI/2*360);
				int aywd = (int) Math.round(ayw/Math.PI/2*360);
				xzVal.setText(""+axzd);
				ywVal.setText(""+aywd);
			}}.addTo(scene.camera4d);
	}
}
