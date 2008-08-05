package ddddbb.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ddddbb.game.Scene4d;
import ddddbb.game.Settings;
import ddddbb.gen.AChangeListener;
import ddddbb.gen.DoubleModel;
import ddddbb.gui3d.DArrowButton;
import ddddbb.gui3d.DButton;
import ddddbb.gui3d.DDisplay;
import ddddbb.gui3d.DLabel;
import ddddbb.gui3d.DPanel;
import ddddbb.gui3d.DSelection;
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
	private JLabel xyVal;
	private JLabel xzVal;
	private JLabel xwVal;
	private JLabel yzVal;
	private JLabel ywVal;
	private JLabel zwVal;
	private JLabel zoomVal;
	private JLabel wPos;
	private JLabel zPos;
	private JLabel yPos;
	private JLabel xPos;

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
	public Cam4dControlPanel(
			final Scene4d scene, 
			final Settings ss
	) {
		UIAction ua = new UIAction(ss,scene);
		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] {0.00, 0.00, 0.00, 0.0, 0, 0};
		thisLayout.rowHeights = new int[] {18, 18, 18, 18, 18, 18};
		thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		thisLayout.columnWidths = new int[] {0, 16, 0, 16, 3, 30, 16, 0, 16};
		this.setLayout(thisLayout);
		this.setBorder(Cam3dControlPanel.border("4d camera control"));

		{
			DSelection xSel = new DSelection(14,16,"x");
			this.add(xSel, gbc(0,0));
			ss.mouseTransAxes4d.addButton(Settings.Axis4d.X, xSel);
		}
		{
			DSelection ySel = new DSelection(14,16);
			this.add(ySel, gbc(0,1));
			ySel.setText("y");
			ss.mouseTransAxes4d.addButton(Settings.Axis4d.Y, ySel);
		}
		{
			DSelection zSel = new DSelection(14,16);
			this.add(zSel, gbc(0,2));
			zSel.setText("z");
			ss.mouseTransAxes4d.addButton(Settings.Axis4d.Z, zSel);
		}
		{
			DSelection wSel = new DSelection(14,16);
			this.add(wSel, gbc(0,3));
			wSel.setText("w");
			ss.mouseTransAxes4d.addButton(Settings.Axis4d.W, wSel);
		}
		{	
			JButton xLeft;
			xLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xLeft, gbc(1,0));
			xLeft.addActionListener(ua.transCam4dAction(-1));
		}
		{
			JButton yLeft;
			yLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yLeft, gbc(1,1));
			yLeft.addActionListener(ua.transCam4dAction(-2));
		}
		{
			JButton zLeft;
			zLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zLeft, gbc(1,2));
			zLeft.addActionListener(ua.transCam4dAction(-3));
		}
		{
			JButton wLeft;
			wLeft = new DArrowButton(SwingConstants.WEST);
			this.add(wLeft, gbc(1,3));
			wLeft.addActionListener(ua.transCam4dAction(-4));
		}
		{
			xPos = new DDisplay(3,true);
			this.add(xPos, gbc(2,0));
			xPos.setToolTipText("X-position of the 4d camera.");
		}
		{
			yPos = new DDisplay(3,true);
			this.add(yPos, gbc(2,1));
			yPos.setToolTipText("Y-position of the 4d camera.");
		}
		{
			zPos = new DDisplay(3,true);
			this.add(zPos, gbc(2,2));
			zPos.setToolTipText("Z-position of the 4d camera.");
		}
		{
			wPos = new DDisplay(3,true);
			this.add(wPos, gbc(2,3));
			wPos.setToolTipText("W-position of the 4d camera.");
		}
		{
			JButton xRight;
			xRight = new DArrowButton(SwingConstants.EAST);
			this.add(xRight, gbc(3,0));
			xRight.addActionListener(ua.transCam4dAction(1));
		}
		{
			JButton yRight;
			yRight = new DArrowButton(SwingConstants.EAST);
			this.add(yRight, gbc(3,1));
			yRight.addActionListener(ua.transCam4dAction(2));
		}
		{
			JButton zRight;
			zRight = new DArrowButton(SwingConstants.EAST);
			this.add(zRight, gbc(3,2));
			zRight.addActionListener(ua.transCam4dAction(3));
		}
		{
			JButton wRight;
			wRight = new DArrowButton(SwingConstants.EAST);
			this.add(wRight, gbc(3,3));
			wRight.addActionListener(ua.transCam4dAction(4));
		}
		{
			DButton reset;
			reset = new DButton(60,16,"reset");
			reset.setSize(50,16);
			this.add(reset, gbc(1,4,4));
			reset.addActionListener(ua.resetCam4d);
			reset.setToolTipText("Reset camera location and position (zoom remains).");
		}
		{
			JLabel zoomLabel;
			zoomLabel = new DLabel();
			this.add(zoomLabel, gbc(0,5));
			zoomLabel.setText("zm");
			zoomLabel.setToolTipText("4d zoom.");
		}
		{
			JButton zoomLeft;
			zoomLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zoomLeft, gbc(1,5));
			zoomLeft.addActionListener(ss.zoom.decreaseAction);
		}
		{
			zoomVal = new DDisplay(3,true);
			this.add(zoomVal, gbc(2,5));
		}
		{
			JButton zoomRight;
			zoomRight = new DArrowButton(SwingConstants.EAST);
			this.add(zoomRight, gbc(3,5));
			zoomRight.addActionListener(ss.zoom.increaseAction);
		}
		String diAxisSelector = 
			"<html><body>" +
			"There are always 2 items of xy,xz,xw,yz,yw and zw underlined.<br/>" +
			"The first one indicates the 4d rotation performed by the mouse moving left-right.<br/>" +
			"The second underlined item indicates the rotation by the mouse moving down-up.<br/>" +
			"Find out yourself how to operate the double selection." +
			"</body></html>";
		{
			DSelection xySel = new DSelection(30,16);
			this.add(xySel, gbc(5,0));
			xySel.setText("xy");
			Settings.mouseRotDiAxes4d.addButton(0, xySel);
			xySel.setToolTipText(diAxisSelector);
		}
		{
			DSelection xzSel = new DSelection(30,16);
			this.add(xzSel, gbc(5,1));
			xzSel.setText("xz");
			Settings.mouseRotDiAxes4d.addButton(1, xzSel);
			xzSel.setToolTipText(diAxisSelector);
		}
		{
			DSelection xwSel = new DSelection(30,16);
			this.add(xwSel, gbc(5,2));
			xwSel.setText("xw");
			Settings.mouseRotDiAxes4d.addButton(2, xwSel);
			xwSel.setToolTipText(diAxisSelector);
		}
		{
			DSelection yzSel = new DSelection(30,16);
			this.add(yzSel, gbc(5,3));
			yzSel.setText("yz");
			Settings.mouseRotDiAxes4d.addButton(3, yzSel);
			yzSel.setToolTipText(diAxisSelector);
		}
		{
			DSelection ywSel = new DSelection(30,16);
			this.add(ywSel, gbc(5,4));
			ywSel.setText("yw");
			Settings.mouseRotDiAxes4d.addButton(4, ywSel);
			ywSel.setToolTipText(diAxisSelector);
		}
		{
			DSelection zwSel = new DSelection(30,16);
			this.add(zwSel, gbc(5,5));
			zwSel.setText("zw");
			Settings.mouseRotDiAxes4d.addButton(5, zwSel);
			zwSel.setToolTipText(diAxisSelector);
		}

		{
			JButton xyLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xyLeft, gbc(6,0));
			xyLeft.addActionListener(ua.rotCam4dAction(2, 1));
			xyLeft.setToolTipText(
					"<html><body>" +
					"Rotating the y-Axis towards the x-Axis.<br/>" +
					"Depending on sys/cam either the space axes or the camera axes are used.<br/>" +
					"In a left handed 4d system the w-axis of the camera is your viewing direction.<br/>" +
					"In a right handed 4d system the negative w-axis is the viewing direction.<br/>" +
					"The camera's x-axis is projected towards your right.<br/>" +
					"The camera's y-axis is projected upwards.<br/>" +
					"The camera's z-axis is projected towards your front in a 3d left-handed system." +
					"</body></html>"
					);
		}
		{
			JButton xzLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xzLeft, gbc(6,1));
			xzLeft.addActionListener(ua.rotCam4dAction(2, 1));
		}
		{
			JButton xwLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xwLeft, gbc(6,2));
			xwLeft.addActionListener(ua.rotCam4dAction(2, 1));
		}
		{
			JButton yzLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yzLeft, gbc(6,3));
			yzLeft.addActionListener(ua.rotCam4dAction(2, 1));
		}
		{
			JButton ywLeft = new DArrowButton(SwingConstants.WEST);
			this.add(ywLeft, gbc(6,4));
			ywLeft.addActionListener(ua.rotCam4dAction(2, 1));
		}
		{
			JButton zwLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zwLeft, gbc(6,5));
			zwLeft.addActionListener(ua.rotCam4dAction(2, 1));
		}

		{
			xyVal = new DDisplay(3,false);
			this.add(xyVal, gbc(7,0));
			xyVal.setToolTipText("The angle of the camera direction in the projection to the xy-plane.");
		}
		{
			xzVal = new DDisplay(3,false);
			this.add(xzVal, gbc(7,1));
			xzVal.setToolTipText("The angle of the camera direction in the projection to the xz-plane.");
		}
		{
			xwVal = new DDisplay(3,false);
			this.add(xwVal, gbc(7,2));
			xwVal.setToolTipText("The angle of the camera direction in the projection to the xw-plane.");
		}
		{
			yzVal = new DDisplay(3,false);
			this.add(yzVal, gbc(7,3));
			yzVal.setToolTipText("The angle of the camera direction in the projection to the yz-plane.");
		}
		{
			ywVal = new DDisplay(3,false);
			this.add(ywVal, gbc(7,4));
			ywVal.setToolTipText("The angle of the camera direction in the projection to the yw-plane.");
		}
		{
			zwVal = new DDisplay(3,false);
			this.add(zwVal, gbc(7,5));
			zwVal.setToolTipText("The angle of the camera direction in the projection to the zw-plane.");
		}

		{
			JButton xyRight = new DArrowButton(SwingConstants.EAST);
			this.add(xyRight, gbc(8,0));
			xyRight.addActionListener(ua.rotCam4dAction(1, 2));
		}
		{
			JButton xzRight;
			xzRight = new DArrowButton(SwingConstants.EAST);
			this.add(xzRight, gbc(8,1));
			xzRight.addActionListener(ua.rotCam4dAction(1, 3));
		}
		{
			JButton xwRight = new DArrowButton(SwingConstants.EAST);
			this.add(xwRight, gbc(8,2));
			xwRight.addActionListener(ua.rotCam4dAction(1, 4));
		}
		{
			JButton yzRight = new DArrowButton(SwingConstants.EAST);
			this.add(yzRight, gbc(8,3));
			yzRight.addActionListener(ua.rotCam4dAction(2, 3));
		}
		{
			JButton ywRight;
			ywRight = new DArrowButton(SwingConstants.EAST);
			this.add(ywRight, gbc(8,4));
			ywRight.addActionListener(ua.rotCam4dAction(2, 4));
		}
		{
			JButton zwRight = new DArrowButton(SwingConstants.EAST);
			this.add(zwRight, gbc(8,5));
			zwRight.addActionListener(ua.rotCam4dAction(3, 4));
		}

//		{
//			JSeparator jSeparator1;
//			jSeparator1 = new JSeparator(SwingConstants.VERTICAL);
//			this.add(jSeparator1, new GridBagConstraints(4, 0, 1, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
//		}
		
		new AChangeListener() {
			@Override
			public void stateChanged() {
				zoomVal.setText(ViewPane.fnf.format(ss.zoom.getDouble()));
			}			
		}.addTo(ss.zoom);
		new AChangeListener() {
			@Override
			public void stateChanged() {
				double[] o = scene.camera4d.eye.x;
				xPos.setText(ViewPane.fnf.format(o[0]));
				yPos.setText(ViewPane.fnf.format(o[1]));
				zPos.setText(ViewPane.fnf.format(o[2]));
				wPos.setText(ViewPane.fnf.format(o[3]));

				Point4d v;
//				if (scene.viewAbsRel.isSelected()) {
					v = scene.camera4d.viewingDirection();
//				}
//				else {
//					
//				}
				xyVal.setText(""+angle(v,1,2));
				xzVal.setText(""+angle(v,1,3));
				xwVal.setText(""+angle(v,1,4));
				yzVal.setText(""+angle(v,2,3));
				ywVal.setText(""+angle(v,2,4));
				zwVal.setText(""+angle(v,3,4));
			}}.addTo(scene.camera4d);
	}
	
	private int angle(Point4d v, int a1, int a2) {
		double ph = AOP.angle02pi(Math.atan2(
				v.sc(AOP.unitVector4(a2-1)),
				v.sc(AOP.unitVector4(a1-1))));
		return (int) Math.round(ph/Math.PI/2*360);
	}
}
