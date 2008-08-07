package ddddbb.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ddddbb.game.Compound;
import ddddbb.game.Level;
import ddddbb.game.Settings;
import ddddbb.gen.AChangeListener;
import ddddbb.gui3d.DArrowButton;
import ddddbb.gui3d.DButton;
import ddddbb.gui3d.DDisplay;
import ddddbb.gui3d.DLabel;
import ddddbb.gui3d.DPanel;

@SuppressWarnings("serial")
public class ObjectControlPanel extends DPanel {

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

	private DDisplay nPos;
	private DDisplay xLoc;
	private DDisplay yLoc;
	private DDisplay zLoc;
	private DDisplay wLoc;
	private DDisplay nCount;

	public ObjectControlPanel(
			final Level scene,
			final Settings ss
	) {
		GridBagLayout thisLayout = new GridBagLayout();
		//this.setPreferredSize(new java.awt.Dimension(186, 154));
		//setMinimumSize(new Dimension(169,154));
		this.setBorder(Cam3dControlPanel.border("object control"));
		thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		thisLayout.rowHeights = new int[] {18, 18, 18, 18, 18, 18};
		thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		thisLayout.columnWidths = new int[] {16, 16, 0, 16, 7, 16, 24, 16};
		this.setLayout(thisLayout);
		{
			JLabel xLabel;
			xLabel = new DLabel();
			this.add(xLabel, gbc(0,0));
			xLabel.setText("x");
		}
		{
			JLabel yLabel;
			yLabel = new DLabel(14,16);
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
			JLabel wLabel;
			wLabel = new DLabel();
			this.add(wLabel, gbc(0,3));
			wLabel.setText("w");
		}
		{
			JButton xLeft;
			xLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xLeft, gbc(1,0));
			xLeft.addActionListener(scene.transSelectedA(-1));
		}
		{
			JButton yLeft;
			yLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yLeft, gbc(1,1));
			yLeft.addActionListener(scene.transSelectedA(-2));
		}
		{
			JButton zLeft;
			zLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zLeft, gbc(1,2));
			zLeft.addActionListener(scene.transSelectedA(-3));
		}
		{
			JButton wLeft;
			wLeft = new DArrowButton(SwingConstants.WEST);
			this.add(wLeft, gbc(1,3));
			wLeft.addActionListener(scene.transSelectedA(-4));
		}
		{
			JButton xRight;
			xRight = new DArrowButton(SwingConstants.EAST);
			this.add(xRight, gbc(3,0));
			xRight.addActionListener(scene.transSelectedA(1));
		}
		{
			JButton yRight;
			yRight = new DArrowButton(SwingConstants.EAST);
			this.add(yRight, gbc(3,1));
			yRight.addActionListener(scene.transSelectedA(2));
		}
		{
			JButton zRight;
			zRight = new DArrowButton(SwingConstants.EAST);
			this.add(zRight, gbc(3,2));
			zRight.addActionListener(scene.transSelectedA(3));
		}
		{
			JButton wRight;
			wRight = new DArrowButton(SwingConstants.EAST);
			this.add(wRight, gbc(3,3));
			wRight.addActionListener(scene.transSelectedA(4));
		}
		{
			xLoc = new DDisplay(3,false);
			this.add(xLoc, gbc(2,0));
			xLoc.setToolTipText("X-location of the currently selected object.");
		}
		{
			yLoc = new DDisplay(3,false);
			this.add(yLoc, gbc(2,1));
			yLoc.setToolTipText("Y-location of the currently selected object.");
		}
		{
			zLoc = new DDisplay(3,false);
			this.add(zLoc, gbc(2,2));
			zLoc.setToolTipText("Z-location of the currently selected object.");
		}
		{
			wLoc = new DDisplay(3,false);
			this.add(wLoc, gbc(2,3));
			wLoc.setToolTipText("W-location of the currently selected object.");
		}
		{
			JButton combine;
			combine = new DButton(60,16,"combine");
			this.add(combine, gbc(1, 4, 3));
			combine.addActionListener(scene.combineAction);
			combine.setToolTipText("Combines the currently selected object with all orthogonally touching objects.");
		}
		{
			nCount = new DDisplay(1,false);
			this.add(nCount, gbc(0,5));
			nCount.setToolTipText("The number of objects in the space.");
		}
		{
			JButton nLeft;
			nLeft = new DArrowButton(SwingConstants.WEST);
			this.add(nLeft, gbc(1,5));
			nLeft.addActionListener(scene.compounds.prevAction);
			nLeft.setToolTipText("Cyclically select the predecessor object.");
		}
		{
			nPos = new DDisplay(3,false);
			this.add(nPos, gbc(2,5));
			nPos.setToolTipText("The number of the currently selected object. It is marked with a solid dot.");
		}
		{
			JButton nRight;
			nRight = new DArrowButton(SwingConstants.EAST);
			this.add(nRight, gbc(3,5));
			nRight.addActionListener(scene.compounds.nextAction);
			nRight.setToolTipText("Cyclically select the successor object in order.");
		}
//		{
//			JToggleButton goal;
//			goal = new JToggleButton();
//			this.add(goal, gbc(5, 4, 3, 2));
//			goal.setText("Goal");
//			goal.setOpaque(false);
//			goal.addActionListener(showGoal.toggleAction);
//		}
		{
			JButton xyLeftRot;
			xyLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(xyLeftRot, gbc(5,0));
			xyLeftRot.addActionListener(scene.rotSelectedA(2, 1));
			xyLeftRot.setToolTipText("Rotate the currently selected object by y-Axis passing over to x-Axis.");
		}
		{
			JButton xzLeftRot;
			xzLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(xzLeftRot, gbc(5,1));
			xzLeftRot.addActionListener(scene.rotSelectedA(3, 1));
			xzLeftRot.setToolTipText("Rotate the currently selected object by z-Axis passing over to x-Axis.");
		}
		{
			JButton xwLeftRot;
			xwLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(xwLeftRot, gbc(5,2));
			xwLeftRot.addActionListener(scene.rotSelectedA(4, 1));
			xwLeftRot.setToolTipText("Rotate the currently selected object by w-Axis passing over to x-Axis.");
		}
		{
			JButton yzLeftRot;
			yzLeftRot = new DArrowButton(SwingConstants.WEST);
			this.add(yzLeftRot, gbc(5,3));
			yzLeftRot.addActionListener(scene.rotSelectedA(3, 2));
			yzLeftRot.setToolTipText("Rotate the currently selected object by z-Axis passing over to y-Axis.");
		}
		{
			JButton ywLeftRot;
			ywLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(ywLeftRot, gbc(5,4));
			ywLeftRot.addActionListener(scene.rotSelectedA(4, 2));
			ywLeftRot.setToolTipText("Rotate the currently selected object by w-Axis passing over to y-Axis.");
		}
		{
			JButton zwLeftRot;
			zwLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(zwLeftRot, gbc(5,5));
			zwLeftRot.addActionListener(scene.rotSelectedA(4, 3));
			zwLeftRot.setToolTipText("Rotate the currently selected object by w-Axis passing over to z-Axis.");
		}

		{
			DLabel xyLabel = new DLabel();
			this.add(xyLabel, gbc(6,0));
			xyLabel.setText("xy");
		}
		{
			JLabel xzLabel;
			xzLabel = new DLabel();
			this.add(xzLabel, gbc(6,1));
			xzLabel.setText("xz");
		}
		{
			DLabel xwLabel = new DLabel();
			this.add(xwLabel, gbc(6,2));
			xwLabel.setText("xw");			
		}
		{
			DLabel yzLabel = new DLabel();
			this.add(yzLabel, gbc(6,3));
			yzLabel.setText("yz");						
		}
		{
			JLabel ywLabel;
			ywLabel = new DLabel();
			this.add(ywLabel, gbc(6,4));
			ywLabel.setText("yw");
		}
		{
			DLabel zwLabel = new DLabel();
			this.add(zwLabel, gbc(6,5));
			zwLabel.setText("zw");						
		}

		{
			JButton xyRightRot;
			xyRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(xyRightRot, gbc(7,0));
			xyRightRot.addActionListener(scene.rotSelectedA(1, 2));
			xyRightRot.setToolTipText("Rotate the currently selected object by x-Axis passing over to y-Axis.");
		}
		{
			JButton xzRightRot;
			xzRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(xzRightRot, gbc(7,1));
			xzRightRot.addActionListener(scene.rotSelectedA(1, 3));
			xzRightRot.setToolTipText("Rotate the currently selected object by x-Axis passing over to z-Axis.");
		}
		{
			JButton xwRightRot;
			xwRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(xwRightRot, gbc(7,2));
			xwRightRot.addActionListener(scene.rotSelectedA(1, 4));
			xwRightRot.setToolTipText("Rotate the currently selected object by x-Axis passing over to w-Axis.");
		}
		{
			JButton yzRightRot;
			yzRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(yzRightRot, gbc(7,3));
			yzRightRot.addActionListener(scene.rotSelectedA(2, 3));
			yzRightRot.setToolTipText("Rotate the currently selected object by y-Axis passing over to z-Axis.");
		}
		{
			JButton ywRightRot;
			ywRightRot = new DArrowButton(SwingConstants.EAST);
			this.add(ywRightRot, gbc(7,4));
			ywRightRot.addActionListener(scene.rotSelectedA(2, 4));
			ywRightRot.setToolTipText("Rotate the currently selected object by y-Axis passing over to w-Axis.");
		}
		{
			JButton zwRightRot;
			zwRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(zwRightRot, gbc(7,5));
			zwRightRot.addActionListener(scene.rotSelectedA(3, 4));
			zwRightRot.setToolTipText("Rotate the currently selected object by z-Axis passing over to w-Axis.");
		}


		new AChangeListener() {
			@Override
			public void stateChanged() {
				nPos.setText((scene.compounds.selInt()+1)+"");
				nCount.setText(scene.compounds.size()+"");
				Compound co = scene.compounds.sel();
				if (co==null) {
					xLoc.setText("n/a");
					yLoc.setText("n/a");
					zLoc.setText("n/a");
					wLoc.setText("n/a");	
					return;
				}

				int[] c = co.centerOrigin();
				xLoc.setText(ViewPane.nf.format(c[0]));
				yLoc.setText(ViewPane.nf.format(c[1]));
				zLoc.setText(ViewPane.nf.format(c[2]));
				wLoc.setText(ViewPane.nf.format(c[3]));
			}}.addTo(scene.compounds);
		
		
	}
}
