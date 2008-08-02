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
	private DLabel wPos;
	private DLabel zPos;
	private DLabel yPos;
	private DLabel nPos;
	private JLabel nLabel;
	private JButton nRight;
	private JButton nLeft;
	private DLabel xPos;
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

	public ObjectControlPanel(
			final Level scene,
			final BoolModel showGoal, DoubleModel brightness
	) {
		GridBagLayout thisLayout = new GridBagLayout();
		this.setPreferredSize(new java.awt.Dimension(186, 154));
		setMinimumSize(new Dimension(169,154));
		this.setBorder(BorderFactory.createTitledBorder("object control"));
		thisLayout.rowWeights = new double[] {0.1, 0.0, 0.1, 0.1, 0.1, 0.1};
		thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 20};
		thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		thisLayout.columnWidths = new int[] {15, 15, 40, 15, 7, 20, 15, 15};
		this.setLayout(thisLayout);
		{
			yLabel = new JLabel();
			this.add(yLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			yLabel.setText("y");
		}
		{
			xLabel = new JLabel();
			this.add(xLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			xLabel.setText("x");
		}
		{
			zLabel = new JLabel();
			this.add(zLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			zLabel.setText("z");
		}
		{
			wLabel = new JLabel();
			this.add(wLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			wLabel.setText("w");
		}
		{
			xLeft = new DArrowButton(SwingConstants.WEST);
			this.add(xLeft, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			yLeft = new DArrowButton(SwingConstants.WEST);
			this.add(yLeft, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			zLeft = new DArrowButton(SwingConstants.WEST);
			this.add(zLeft, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			wLeft = new DArrowButton(SwingConstants.WEST);
			this.add(wLeft, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			xRight = new DArrowButton(SwingConstants.EAST);
			this.add(xRight, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			yRight = new DArrowButton(SwingConstants.EAST);
			this.add(yRight, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			zRight = new DArrowButton(SwingConstants.EAST);
			this.add(zRight, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			wRight = new DArrowButton(SwingConstants.EAST);
			this.add(wRight, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			xPos = new DLabel(30,16);
			this.add(xPos, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			xPos.setText(" -0");
		}
		{
			nLeft = new DArrowButton(SwingConstants.WEST);
			this.add(nLeft, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			nRight = new DArrowButton(SwingConstants.EAST);
			this.add(nRight, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			nLabel = new JLabel();
			this.add(nLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			nLabel.setText("n");
		}
		{
			nPos = new DLabel(30,16);
			this.add(nPos, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			yPos = new DLabel(30,16);
			this.add(yPos, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			zPos = new DLabel(30,16);
			this.add(zPos, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			wPos = new DLabel(30,16);
			this.add(wPos, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			goal = new JToggleButton();
			this.add(goal, new GridBagConstraints(5, 4, 3, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			goal.setText("Goal");
		}
		{
			combine = new JButton();
			this.add(combine, new GridBagConstraints(5, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			combine.setText("comb.");
		}
		{
			xzLabel = new JLabel();
			this.add(xzLabel, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			xzLabel.setText("xz");
		}
		{
			ywLabel = new JLabel();
			this.add(ywLabel, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			ywLabel.setText("yw");
		}
		{
			jSeparator1 = new JSeparator(SwingConstants.HORIZONTAL);
			this.add(jSeparator1, new GridBagConstraints(0, 1, 9, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			xzLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(xzLeftRot, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			xzRightRot = new DArrowButton(SwingConstants.EAST);;
			this.add(xzRightRot, new GridBagConstraints(7, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			ywLeftRot = new DArrowButton(SwingConstants.WEST);;
			this.add(ywLeftRot, new GridBagConstraints(6, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		}
		{
			ywRightRot = new DArrowButton(SwingConstants.EAST);
			this.add(ywRightRot, new GridBagConstraints(7, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
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
