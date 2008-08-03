package ddddbb.gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import ddddbb.game.Compound;
import ddddbb.game.Level;
import ddddbb.gen.AChangeListener;
import ddddbb.gen.BoolModel;
import ddddbb.gen.DoubleModel;
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
	private JLabel yLabel;
	private JSeparator jSeparator1;
	private JLabel ywLabel;
	private JLabel xzLabel;
	private JButton combine;
	private JButton ywRightRot;
	private JButton ywLeftRot;
	private JButton xzRightRot;
	private JButton xzLeftRot;
	private JToggleButton goal;
	private DDisplay wPos;
	private DDisplay zPos;
	private DDisplay yPos;
	private DDisplay nPos;
	private JLabel nLabel;
	private JButton nRight;
	private JButton nLeft;
	private DDisplay xPos;
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
	private JLabel xLabel;

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

	public ObjectControlPanel(
			final Level scene,
			final BoolModel showGoal, DoubleModel brightness
	) {
		GridBagLayout thisLayout = new GridBagLayout();
		this.setPreferredSize(new java.awt.Dimension(186, 154));
		setMinimumSize(new Dimension(169,154));
		this.setBorder(BorderFactory.createTitledBorder("object control"));
		thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		thisLayout.rowHeights = new int[] {18, 3, 18, 18, 18, 18};
		thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		thisLayout.columnWidths = new int[] {15, 15, 40, 15, 7, 20, 15, 15};
		this.setLayout(thisLayout);
		{
			yLabel = new DLabel();
			this.add(yLabel, gbc(0,3));
			yLabel.setText("y");
		}
		{
			xLabel = new DLabel();
			this.add(xLabel, gbc(0,2));
			xLabel.setText("x");
		}
		{
			zLabel = new DLabel();
			this.add(zLabel, gbc(0,4));
			zLabel.setText("z");
		}
		{
			wLabel = new DLabel();
			this.add(wLabel, gbc(0,5));
			wLabel.setText("w");
		}
		{
			xLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xLeft, gbc(1,2));
		}
		{
			yLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yLeft, gbc(1,3));
		}
		{
			zLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zLeft, gbc(1,4));
		}
		{
			wLeft = new DArrowButton(SwingConstants.WEST);
			this.add(wLeft, gbc(1,5));
		}
		{
			xRight = new DArrowButton(SwingConstants.EAST);
			this.add(xRight, gbc(3,2));
		}
		{
			yRight = new DArrowButton(SwingConstants.EAST);
			this.add(yRight, gbc(3,3));
		}
		{
			zRight = new DArrowButton(SwingConstants.EAST);
			this.add(zRight, gbc(3,4));
		}
		{
			wRight = new DArrowButton(SwingConstants.EAST);
			this.add(wRight, gbc(3,5));
		}
		{
			xPos = new DDisplay(30,16);
			this.add(xPos, gbc(2,2));
			xPos.setText(" -0");
		}
		{
			nLeft = new DArrowButton(SwingConstants.WEST);
			this.add(nLeft, gbc(1,0));
		}
		{
			nRight = new DArrowButton(SwingConstants.EAST);
			this.add(nRight, gbc(3,0));
		}
		{
			nLabel = new DLabel();
			this.add(nLabel, gbc(0,0));
			nLabel.setText("n");
		}
		{
			nPos = new DDisplay(30,16);
			this.add(nPos, gbc(2,0));
		}
		{
			yPos = new DDisplay(30,16);
			this.add(yPos, gbc(2,3));
		}
		{
			zPos = new DDisplay(30,16);
			this.add(zPos, gbc(2,4));
		}
		{
			wPos = new DDisplay(30,16);
			this.add(wPos, gbc(2,5));
		}
		{
			goal = new JToggleButton();
			this.add(goal, gbc(5, 4, 3, 2));
			goal.setText("Goal");
			goal.setOpaque(false);
		}
		{
			combine = new DButton("comb.");
			this.add(combine, gbc(5, 0, 3, 1));
		}
		{
			xzLabel = new DLabel();
			this.add(xzLabel, gbc(5,2));
			xzLabel.setText("xz");
		}
		{
			ywLabel = new DLabel();
			this.add(ywLabel, gbc(5,3));
			ywLabel.setText("yw");
		}
		{
			jSeparator1 = new JSeparator(SwingConstants.HORIZONTAL);
			this.add(jSeparator1, gbc(0, 1, 9, 1));
		}
		{
			xzLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(xzLeftRot, gbc(6,2));
		}
		{
			xzRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(xzRightRot, gbc(7,2));
		}
		{
			ywLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(ywLeftRot, gbc(6,3));
		}
		{
			ywRightRot = new DArrowButton(SwingConstants.EAST);
			this.add(ywRightRot, gbc(7,3));
		}

		xRight.addActionListener(scene.transSelectedAction(1));
		yRight.addActionListener(scene.transSelectedAction(2));
		zRight.addActionListener(scene.transSelectedAction(3));
		wRight.addActionListener(scene.transSelectedAction(4));
		xLeft.addActionListener(scene.transSelectedAction(-1));
		yLeft.addActionListener(scene.transSelectedAction(-2));
		zLeft.addActionListener(scene.transSelectedAction(-3));
		wLeft.addActionListener(scene.transSelectedAction(-4));
		xzRightRot.addActionListener(scene.rotSelectedAction(1, 3));
		ywRightRot.addActionListener(scene.rotSelectedAction(2, 4));
		xzLeftRot.addActionListener(scene.rotSelectedAction(3, 1));
		ywLeftRot.addActionListener(scene.rotSelectedAction(4, 2));
		
		nLeft.addActionListener(scene.compounds.prevAction);
		nRight.addActionListener(scene.compounds.nextAction);
		
		goal.addActionListener(showGoal.toggleAction);
		combine.addActionListener(scene.combineAction);
		
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
