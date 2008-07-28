package ddddbb.gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;

import ddddbb.game.Compound;
import ddddbb.game.Opt;

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
public class ObjectControlPanel extends JPanel {
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
	private JTextField wPos;
	private JTextField zPos;
	private JTextField yPos;
	private JTextField nPos;
	private JLabel nLabel;
	private JButton nRight;
	private JButton nLeft;
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
	private JLabel xLabel;

	public ObjectControlPanel() {
		initGUI();
		xRight.addActionListener(UIAction.transSelected(1));
		yRight.addActionListener(UIAction.transSelected(2));
		zRight.addActionListener(UIAction.transSelected(3));
		wRight.addActionListener(UIAction.transSelected(4));
		xLeft.addActionListener(UIAction.transSelected(-1));
		yLeft.addActionListener(UIAction.transSelected(-2));
		zLeft.addActionListener(UIAction.transSelected(-3));
		wLeft.addActionListener(UIAction.transSelected(-4));
		xzRightRot.addActionListener(UIAction.rotSelected(1, 3));
		ywRightRot.addActionListener(UIAction.rotSelected(2, 4));
		xzLeftRot.addActionListener(UIAction.rotSelected(3, 1));
		ywLeftRot.addActionListener(UIAction.rotSelected(4, 2));
		
		nLeft.addActionListener(UIShownAction.prevSelected);
		nRight.addActionListener(UIShownAction.nextSelected);
		
		goal.addActionListener(UIShownAction.showGoal);
		combine.addActionListener(UIShownAction.combineTouchingSelected);
		
		Opt.scene.compounds.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				nPos.setText((Opt.scene.compounds.getSelected()+1)+"");
			}});
		Opt.scene.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Compound co = Opt.scene.compounds.getSelectedItem();
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
			}});
		
		
	}
	private void initGUI() {
		try {
			{
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
					xLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(xLeft, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					yLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(yLeft, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					zLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(zLeft, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					wLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(wLeft, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(xRight, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					yRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(yRight, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					zRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(zRight, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					wRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(wRight, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xPos = new JTextField(3);
					xPos.setEditable(false);
					this.add(xPos, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					xPos.setText(" -0");
				}
				{
					nLeft = new BasicArrowButton(SwingConstants.WEST);
					this.add(nLeft, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					nRight = new BasicArrowButton(SwingConstants.EAST);
					this.add(nRight, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					nLabel = new JLabel();
					this.add(nLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					nLabel.setText("n");
				}
				{
					nPos = new JTextField(3);
					nPos.setEditable(false);
					this.add(nPos, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					yPos = new JTextField(3);
					yPos.setEditable(false);
					this.add(yPos, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					zPos = new JTextField(3);
					zPos.setEditable(false);
					this.add(zPos, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					wPos = new JTextField(3);
					wPos.setEditable(false);
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
					xzLeftRot = new BasicArrowButton(SwingConstants.WEST);;
					this.add(xzLeftRot, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					xzRightRot = new BasicArrowButton(SwingConstants.EAST);;
					this.add(xzRightRot, new GridBagConstraints(7, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					ywLeftRot = new BasicArrowButton(SwingConstants.WEST);;
					this.add(ywLeftRot, new GridBagConstraints(6, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					ywRightRot = new BasicArrowButton(SwingConstants.EAST);
					this.add(ywRightRot, new GridBagConstraints(7, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
