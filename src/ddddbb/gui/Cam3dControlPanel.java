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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import ddddbb.game.Scene4d;
import ddddbb.game.Settings;
import ddddbb.gen.AChangeListener;
import ddddbb.gui3d.DArrowButton;
import ddddbb.gui3d.DButton;
import ddddbb.gui3d.DDisplay;
import ddddbb.gui3d.DLabel;
import ddddbb.gui3d.DPanel;
import ddddbb.gui3d.DRadioButton;
import ddddbb.gui3d.DSelection;
import ddddbb.gui3d.DToggleButton;
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
	private DDisplay yzVal;
	private DDisplay xzVal;
	private DDisplay xyVal;
	private DDisplay yLoc;
	private DDisplay zLoc;
	private DDisplay xLoc;
	private DLabel objectiveDisplay;
	public Cam3dControlPanel(
			final Scene4d scene,
			final Settings ss
			) {
		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0};
		thisLayout.rowHeights = new int[] {18, 18, 18, 18, 18, 18};
		thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		thisLayout.columnWidths = new int[] {16, 16, 0, 16, 3, 20, 16, 0, 16};
		this.setLayout(thisLayout);
		this.setBorder(border("3d camera control & misc"));
		{
			JLabel xLabel;
			xLabel = new DLabel();
			this.add(xLabel, gbc(0,0));
			xLabel.setText("x");
		}
		{
			JLabel yLabel;
			yLabel = new DLabel();
			this.add(yLabel, gbc(0,1));
			yLabel.setText("y");
		}
		{
			JLabel zLabel;
			zLabel = new DLabel();
			this.add(zLabel, gbc(0,2));
			zLabel.setText("z");
		}
		{
			JButton xLeft;
			xLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xLeft, gbc(1,0));
			xLeft.addActionListener(scene.transCam3dAction(-1));
		}
		{
			JButton yLeft;
			yLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yLeft, gbc(1,1));
			yLeft.addActionListener(scene.transCam3dAction(-2));
		}
		{
			JButton zLeft;
			zLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zLeft, gbc(1,2));
			zLeft.addActionListener(scene.transCam3dAction(-3));
		}
		{
			JButton xRight;
			xRight = new DArrowButton(SwingConstants.EAST);
			this.add(xRight, gbc(3,0));
			xRight.addActionListener(scene.transCam3dAction(1));
		}
		{
			JButton yRight;
			yRight = new DArrowButton(SwingConstants.EAST);
			this.add(yRight, gbc(3,1));
			yRight.addActionListener(scene.transCam3dAction(2));
		}
		{
			JButton zRight;
			zRight = new DArrowButton(SwingConstants.EAST);
			this.add(zRight, gbc(3,2));
			zRight.addActionListener(scene.transCam3dAction(3));
		}
		String selToolTip = "<html><body>" +
				"There are always 2 items from xy, xz and yz selected.<br/>" +
				"The first one indicates the 3d camera rotation by the mouse moving left-right.<br/>" +
				"The second indicates the 3d rotation by the mouse moving down-up." +
				"</body></html>";
		{
			DSelection xySel = new DSelection(20,16,Settings.DiAxis3d.XY+"");
			Settings.mouseRotDiAxes3d.addButton(Settings.DiAxis3d.XY, xySel);
			this.add(xySel, gbc(5,0));
			xySel.setToolTipText(selToolTip);
		}
		{
			DSelection xzSel = new DSelection(20,16,Settings.DiAxis3d.XZ+"");
			Settings.mouseRotDiAxes3d.addButton(Settings.DiAxis3d.XZ, xzSel);
			this.add(xzSel, gbc(5,1));
			xzSel.setToolTipText(selToolTip);
		}
		{
			DSelection yzSel = new DSelection(20,16,Settings.DiAxis3d.YZ+"");
			Settings.mouseRotDiAxes3d.addButton(Settings.DiAxis3d.YZ, yzSel);
			this.add(yzSel, gbc(5,2));
			yzSel.setToolTipText(selToolTip);
		}
		{
			JButton xyLeft;
			xyLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xyLeft, gbc(6,0));
			xyLeft.addActionListener(scene.rotCam3dAction(2, 1));
			xyLeft.setToolTipText("<html><body>" +
					"Rotates the camera y-Axis towards x-axis.<br/>" +
					"Depending on sys/rot these are the space axes or the camera axes.<br/>" +
					"This is a rotation around the z-Axis." +
					"</body></html>");
		}
		{
			JButton xzLeft;
			xzLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xzLeft, gbc(6,1));
			xzLeft.addActionListener(scene.rotCam3dAction(3, 1));
		}
		{
			JButton yzLeft;
			yzLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yzLeft, gbc(6,2));
			yzLeft.addActionListener(scene.rotCam3dAction(3, 2));
		}
		{
			JButton xyRight;
			xyRight = new DArrowButton(SwingConstants.EAST);
			this.add(xyRight, gbc(8,0));
			
			xyRight.addActionListener(scene.rotCam3dAction(1, 2));
		}
		{
			JButton xzRight;
			xzRight = new DArrowButton(SwingConstants.EAST);
			this.add(xzRight, gbc(8,1));
			xzRight.addActionListener(scene.rotCam3dAction(1, 3));
		}
		{
			JButton yzRight;
			yzRight = new DArrowButton(SwingConstants.EAST);
			this.add(yzRight, gbc(8,2));
			yzRight.addActionListener(scene.rotCam3dAction(2, 3));
			
		}
		{
			JButton reset;
			reset = new DButton(60,16,"reset");
			this.add(reset, gbc(1, 3, 4));
			
			reset.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					scene.camera3d.notify = false;
					scene.camera3d.setToDefault();
					scene.camera3d.notify = true;
					scene.camera3d.setOrientation(ss.orientation3d.sel().value());
					
				}});
			reset.setToolTipText("Resets the 3d camera's location and position.");
		}
		{
			xLoc = new DDisplay(4,true);
			this.add(xLoc, gbc(2,0));
			xLoc.setText("nan");
			xLoc.setToolTipText("X-location of the 3d camera.");
		}
		{
			yLoc = new DDisplay(4,true);
			this.add(yLoc, gbc(2,1));
			yLoc.setText("nan");
			yLoc.setToolTipText("Y-location of the 3d camera.");
		}
		{
			zLoc = new DDisplay(4,true);
			this.add(zLoc, gbc(2,2));
			zLoc.setText("nan");
			zLoc.setToolTipText("Z-location of the 3d camera.");
		}
		{
			xyVal = new DDisplay(3,false);
			this.add(xyVal, gbc(7,0));
			xyVal.setText("nan");
			xyVal.setToolTipText("Angle of the projection of the 3d camera viewing direction to the xy-plane");
		}
		{
			xzVal = new DDisplay(3,false);
			this.add(xzVal, gbc(7,1));
			xzVal.setText("nan");
			xzVal.setToolTipText("Angle of the projection of the 3d camera viewing direction to the xz-plane");
		}
		{
			yzVal = new DDisplay(3,false);
			this.add(yzVal, gbc(7,2));
			yzVal.setText("nan");
			yzVal.setToolTipText("Angle of the projection of the 3d camera viewing direction to the yz-plane");
		}
		{
			DPanel misc = new DPanel();
			this.add(misc, gbc(0,4,4,2));
			GridBagLayout layout = new GridBagLayout();
			misc.setLayout(layout);
			{
				DRadioButton d3 = new DRadioButton("3d");
				misc.add(d3,gbc(0,0));
				ss.dim34.addButton(0, d3);
				d3.setToolTipText("Dragging the mouse & CTRL cursor keys rotates 3d camera.");
			}
			{
				DRadioButton d4 = new DRadioButton("4d");
				misc.add(d4,gbc(0,1));
				ss.dim34.addButton(1, d4);
				d4.setToolTipText("Dragging the mouse & CTRL cursor keys rotates 4d camera.");
			}
			{
				DRadioButton cam = new DRadioButton("cam");
				misc.add(cam,gbc(1,0));
				scene.viewAbsRel.addTrueButton(cam);
				cam.setToolTipText("Rotating and shifting camera is with respect to camera axes.");
			}
			{
				DRadioButton sys = new DRadioButton("sys");
				misc.add(sys,gbc(1,1));
				scene.viewAbsRel.addFalseButton(sys);
				sys.setToolTipText("Rotating and shifting camera is with respect to space axes (shown by the tetrahedral).");
			}
		}
//		{
//			objectiveDisplay = new DLabel(64,16);
//			this.add(objectiveDisplay,gbc(5,3,3,1));
//			new AChangeListener() {
//				public void stateChanged() {
//					objectiveDisplay.setText(ss.objectives.sel().name());
//				}
//			}.addTo(ss.objectives);
//		}
		{
			DToggleButton goal = new DToggleButton(50,24,"goal");
			goal.setAlignmentX(CENTER_ALIGNMENT);
			GridBagConstraints gbc = gbc(5,3,4,4);
			gbc.anchor = gbc.CENTER;
			this.add(goal,gbc);
			ss.showGoal.addButton(goal);
			goal.setToolTipText("Shows the goal to be achievied by combining the objects in the space." );
		}
		{
			JSeparator vSeparator;
			vSeparator = new JSeparator(SwingConstants.VERTICAL);
			this.add(vSeparator, new GridBagConstraints(4, 0, 1, 3, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		
		new AChangeListener() {
			public void stateChanged() {
				double[] o = scene.camera3d.eye.x;
				xLoc.setText(ViewPane.fnf.format(o[0]));
				yLoc.setText(ViewPane.fnf.format(o[1]));
				zLoc.setText(ViewPane.fnf.format(o[2]));
				
				Point3d v = scene.camera3d.viewingDirection();
				Point3d x = AOP.unitVector3(0);
				Point3d y = AOP.unitVector3(1);
				Point3d z = AOP.unitVector3(2);
				double vx=x.sc(v);
				double vy=y.sc(v);
				double vz=z.sc(v);
				double axy = AOP.angle02pi(Math.atan2(vy, vx));
				double axz = AOP.angle02pi(Math.atan2(vz, vx));
				double ayz = AOP.angle02pi(Math.atan2(vz, vy));
				int axyd = (int)Math.round(axy/Math.PI/2*360);
				int axzd = (int)Math.round(axz/Math.PI/2*360);
				int ayzd = (int)Math.round(ayz/Math.PI/2*360);
				xyVal.setText(ViewPane.nf.format(axyd));
				xzVal.setText(ViewPane.nf.format(axzd));
				yzVal.setText(ViewPane.nf.format(ayzd));
			}}.addTo(scene.camera3d);
	}
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
	
	public static TitledBorder border(String title) {
		Border lb = BorderFactory.createLineBorder(Settings.fgColor());
		TitledBorder tb = BorderFactory.createTitledBorder(lb);
		tb.setTitle(title);
		tb.setTitleColor(Settings.fgColor());
		tb.setTitleFont(Settings.font);
		return tb;
	}
}
