package ddddbb.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
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
	private DDisplay xPos;
	private DDisplay yPos;
	private DDisplay zPos;
	private DDisplay wPos;

	public ObjectControlPanel(
			final Level scene,
			final Settings ss
	) {
		GridBagLayout thisLayout = new GridBagLayout();
		//this.setPreferredSize(new java.awt.Dimension(186, 154));
		//setMinimumSize(new Dimension(169,154));
		this.setBorder(BorderFactory.createTitledBorder("object control"));
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
			xLeft.addActionListener(scene.transSelectedAction(-1));
		}
		{
			JButton yLeft;
			yLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yLeft, gbc(1,1));
			yLeft.addActionListener(scene.transSelectedAction(-2));
		}
		{
			JButton zLeft;
			zLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zLeft, gbc(1,2));
			zLeft.addActionListener(scene.transSelectedAction(-3));
		}
		{
			JButton wLeft;
			wLeft = new DArrowButton(SwingConstants.WEST);
			this.add(wLeft, gbc(1,3));
			wLeft.addActionListener(scene.transSelectedAction(-4));
		}
		{
			JButton xRight;
			xRight = new DArrowButton(SwingConstants.EAST);
			this.add(xRight, gbc(3,0));
			xRight.addActionListener(scene.transSelectedAction(1));
		}
		{
			JButton yRight;
			yRight = new DArrowButton(SwingConstants.EAST);
			this.add(yRight, gbc(3,1));
			yRight.addActionListener(scene.transSelectedAction(2));
		}
		{
			JButton zRight;
			zRight = new DArrowButton(SwingConstants.EAST);
			this.add(zRight, gbc(3,2));
			zRight.addActionListener(scene.transSelectedAction(3));
		}
		{
			JButton wRight;
			wRight = new DArrowButton(SwingConstants.EAST);
			this.add(wRight, gbc(3,3));
			wRight.addActionListener(scene.transSelectedAction(4));
		}
		{
			xPos = new DDisplay(3,false);
			this.add(xPos, gbc(2,0));
		}
		{
			yPos = new DDisplay(3,false);
			this.add(yPos, gbc(2,1));
		}
		{
			zPos = new DDisplay(3,false);
			this.add(zPos, gbc(2,2));
		}
		{
			wPos = new DDisplay(3,false);
			this.add(wPos, gbc(2,3));
		}
		{
			JButton combine;
			combine = new DButton(60,16,"combine");
			this.add(combine, gbc(1, 4, 3));
			combine.addActionListener(scene.combineAction);
		}
		{
			JLabel nLabel;
			nLabel = new DLabel();
			this.add(nLabel, gbc(0,5));
			nLabel.setText("n");
		}
		{
			JButton nLeft;
			nLeft = new DArrowButton(SwingConstants.WEST);
			this.add(nLeft, gbc(1,5));
			nLeft.addActionListener(scene.compounds.prevAction);
		}
		{
			nPos = new DDisplay(3,false);
			this.add(nPos, gbc(2,5));
		}
		{
			JButton nRight;
			nRight = new DArrowButton(SwingConstants.EAST);
			this.add(nRight, gbc(3,5));
			nRight.addActionListener(scene.compounds.nextAction);
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
			xyLeftRot.addActionListener(scene.rotSelectedAction(2, 1));
		}
		{
			JButton xzLeftRot;
			xzLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(xzLeftRot, gbc(5,1));
			xzLeftRot.addActionListener(scene.rotSelectedAction(3, 1));
		}
		{
			JButton xwLeftRot;
			xwLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(xwLeftRot, gbc(5,2));
			xwLeftRot.addActionListener(scene.rotSelectedAction(4, 1));
		}
		{
			JButton yzLeftRot;
			yzLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(yzLeftRot, gbc(5,3));
			yzLeftRot.addActionListener(scene.rotSelectedAction(3, 2));
		}
		{
			JButton ywLeftRot;
			ywLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(ywLeftRot, gbc(5,4));
			ywLeftRot.addActionListener(scene.rotSelectedAction(4, 2));
		}
		{
			JButton zwLeftRot;
			zwLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(zwLeftRot, gbc(5,5));
			zwLeftRot.addActionListener(scene.rotSelectedAction(4, 3));
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
			xyRightRot.addActionListener(scene.rotSelectedAction(1, 2));
		}
		{
			JButton xzRightRot;
			xzRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(xzRightRot, gbc(7,1));
			xzRightRot.addActionListener(scene.rotSelectedAction(1, 3));
		}
		{
			JButton xwRightRot;
			xwRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(xwRightRot, gbc(7,2));
			xwRightRot.addActionListener(scene.rotSelectedAction(1, 4));
		}
		{
			JButton yzRightRot;
			yzRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(yzRightRot, gbc(7,3));
			yzRightRot.addActionListener(scene.rotSelectedAction(2, 3));
		}
		{
			JButton ywRightRot;
			ywRightRot = new DArrowButton(SwingConstants.EAST);
			this.add(ywRightRot, gbc(7,4));
			ywRightRot.addActionListener(scene.rotSelectedAction(2, 4));
		}
		{
			JButton zwRightRot;
			zwRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(zwRightRot, gbc(7,5));
			zwRightRot.addActionListener(scene.rotSelectedAction(3, 4));
		}


		new AChangeListener() {
			@Override
			public void stateChanged() {
				nPos.setText((scene.compounds.getSelected()+1)+"");
			}}.addTo(scene.compounds);
		new AChangeListener() {
			@Override
			public void stateChanged() {
				Compound co = scene.compounds.getSelectedItem();
				if (co==null) {
					xPos.setText("n/a");
					yPos.setText("n/a");
					zPos.setText("n/a");
					wPos.setText("n/a");	
					return;
				}

				int[] c = co.center.origin();
				xPos.setText(ViewPane.nf.format(c[0]));
				yPos.setText(ViewPane.nf.format(c[1]));
				zPos.setText(ViewPane.nf.format(c[2]));
				wPos.setText(ViewPane.nf.format(c[3]));
			}}.addTo(scene.compounds);
		
		
	}
}
