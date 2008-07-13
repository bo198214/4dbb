package ddddbb.gui;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.event.ChangeListener;

import ddddbb.game.Opt;
import ddddbb.gen.DoubleSpinner;
import ddddbb.gen.MyChangeListener;
import ddddbb.math.Camera4d;

public class ViewControlPanel extends JPanel implements MyChangeListener {

	private static final long serialVersionUID = 3168894646504798186L;
	
	private DiAxisPanel rotAxisPanel = null;

	private JPanel polarPanel = null;

	private JPanel d4Panel = null;

	private JPanel d3TransAxisPanel = null;

	public JRadioButton d4RadioButton = null;

	private JPanel d3Panel = null;

	public JRadioButton d3RadioButton = null;

	private JPanel absRelPanel = null;

	public JRadioButton relRadioButton = null;

	public JRadioButton absRadioButton = null;

	private JPanel d3RotAxisPanel = null;

	private JPanel jPanel = null;

	private JPanel d3transNRotPanel = null;

	private JButton reset4dViewButton = null;

	private JButton reset3dViewButton = null;

	private AxisPanel transAxisPanel = null;

	private JPanel jPanel1 = null;

	private JPanel jPanel2 = null;

	private JScrollBar ph1ScrollBar = null;

	private JScrollBar ph2ScrollBar = null;

	private JScrollBar ph3ScrollBar = null;

	private JPanel jPanel3 = null;

	private JLabel jLabel = null;

	private JLabel ph1Label = null;

	private JLabel ph2Label = null;

	private JLabel ph3Label = null;

	private JLabel x1Label = null;

	private JLabel x2Label = null;

	private JLabel x3Label = null;

	private JLabel x4Label = null;

	private JPanel jPanel4 = null;

	private JPanel jPanel5 = null;

	private JPanel jPanel6 = null;

	private JPanel jPanel7 = null;

	private JLabel objectiveLabel = null;

	private class PhScrollBar extends JScrollBar {
		private static final long serialVersionUID = 2270063470833499198L;
		int index;
		PhScrollBar(int n) {
			super();
			index = n;
			setModel(new PhBoundedRangeModel(n));
		}
		
	}
	private class PhBoundedRangeModel implements BoundedRangeModel {
		int index;
		
		int maximum = -90;
		int minimum =  90;
		
		public PhBoundedRangeModel(int n) {
			index = n;
		}
		

		public int getValue() {
			double x = Opt.scene.camera4d.getDirec().x[index];
//			System.out.println(index + ":" + Math.round(x*180/Math.PI));
			return (int)Math.round(x*180/Math.PI);
		}

		public void setValue(int newValue) {
			double ph[] = Opt.scene.camera4d.getDirec().x;
			ph[index] = newValue*Math.PI/180;
			Opt.scene.camera4d.setDirec(ph[0],ph[1],ph[2]);
		}

		public int getExtent() { return 1; }
		public void setExtent(int newExtent) { /* 1 */	}
		public boolean getValueIsAdjusting() { return false; }
		public void setValueIsAdjusting(boolean b) { /* false */ }
		public int getMaximum() { return maximum; }
		public void setMaximum(int newMaximum) { maximum = newMaximum; }
		public int getMinimum() { return minimum; }
		public void setMinimum(int newMinimum) { minimum = newMinimum; }
		public void setRangeProperties(int value, int extent, int min, int max, boolean adjusting) {
			setValue(value);
			setExtent(extent);
			setMinimum(min);
			setMaximum(max);
		}
		public void addChangeListener(MyChangeListener l) {
			Opt.scene.addChangeListener(l);
		}
		public void addChangeListener(ChangeListener l) {
			Opt.scene.addChangeListener(l);
		}
		public void removeChangeListener(MyChangeListener l) {
			Opt.scene.removeChangeListener(l);
		}
		public void removeChangeListener(ChangeListener l) {
			Opt.scene.removeChangeListener(l);
		}
	}
	/**
	 * This is the default constructor
	 */
	public ViewControlPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
//		viewControlPanel.setLayout(new GridLayout(2,4));
		this.setSize(new java.awt.Dimension(517,269));
		this.setBorder(BorderFactory.createTitledBorder("view control"));
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(getJPanel7(), null);
		this.add(getJPanel6(), null);
		Opt.dim34.addButton(0,getD3RadioButton());
		Opt.dim34.addButton(1,getD4RadioButton());
		
		MyChangeListener to4 = new MyChangeListener() {
			public void stateChanged() {
				Opt.dim34.setInt(1);
			}
		};
		MyChangeListener toTrans = new MyChangeListener() {
			public void stateChanged() {
				Opt.viewTransRot.setInt(0);
			}
		};
		MyChangeListener toRot = new MyChangeListener() {
			public void stateChanged() {
				Opt.viewTransRot.setInt(1);
			}
		};
		Opt.viewTransAxis.addChangeListener(to4);
		Opt.viewTransAxis.addChangeListener(toTrans);
		ddddbb.game.Opt.viewRotXAxis12.addChangeListener(to4);
		ddddbb.game.Opt.viewRotXAxis12.addChangeListener(toRot);
		MyChangeListener to3 = new MyChangeListener() {
			public void stateChanged() {
				Opt.dim34.setInt(0);				
			}
		};
		Opt.d3ViewRotAxis.addChangeListener(to3);
		Opt.d3ViewRotAxis.addChangeListener(toRot);
		Opt.d3ViewTransAxis.addChangeListener(to3);
		Opt.d3ViewTransAxis.addChangeListener(toTrans);
		Opt.scene.addChangeListener(this);
	}
	
	public void stateChanged() {
		Camera4d camera4d = Opt.scene.camera4d;

		double ph1,ph2,ph3;
		ph1=camera4d.getDirec().x[0]/Math.PI*180;
		ph2=camera4d.getDirec().x[1]/Math.PI*180;
		ph3=camera4d.getDirec().x[2]/Math.PI*180;

		DecimalFormat nf = new DecimalFormat();
		nf.applyPattern("###.#");
		nf.setMinimumFractionDigits(1);
//		nf.setMaximumFractionDigits(1);
//		nf.setMinimumIntegerDigits(3);
//		nf.setMaximumIntegerDigits(3);

		ph1Label.setText("ph1: "+nf.format(ph1));
		ph2Label.setText("ph2: "+nf.format(ph2));
		ph3Label.setText("ph3: "+nf.format(ph3));
		
		double x1,x2,x3,x4;
		x1 = camera4d.getEye().x[0];
		x2 = camera4d.getEye().x[1];
		x3 = camera4d.getEye().x[2];
		x4 = camera4d.getEye().x[3];
		
		x1Label.setText(Opt.axisNames[0] + ": "+nf.format(x1));
		x2Label.setText(Opt.axisNames[1] + ": "+nf.format(x2));
		x3Label.setText(Opt.axisNames[2] + ": "+nf.format(x3));
		x4Label.setText(Opt.axisNames[3] + ": "+nf.format(x4));
		
		objectiveLabel.setText(Opt.objectives.getSelectedName());
		getPh1ScrollBar().setEnabled(Opt.scene.camera4d.isParallelProjectionEnabled());
		getPh2ScrollBar().setEnabled(Opt.scene.camera4d.isParallelProjectionEnabled());
		getPh3ScrollBar().setEnabled(Opt.scene.camera4d.isParallelProjectionEnabled());
	}

	/**
	 * This method initializes diAxisPanel	
	 * 	
	 * @return test.DiAxisPanel	
	 */
	private DiAxisPanel getDiAxisPanel() {
		if (rotAxisPanel == null) {
			rotAxisPanel = new DiAxisPanel(
					ddddbb.game.Opt.viewRotXAxis12
//					Opt.viewRotYAxis1,
//					Opt.viewRotYAxis2
					);
			rotAxisPanel.setToolTipText("Choose rotation plane.");
			rotAxisPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
//			diAxisPanel.setText("rot axes");
		}
		return rotAxisPanel;
	}

	/**
	 * This method initializes polarPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPolarPanel() {
		if (polarPanel == null) {
//			Box.Filler samplePanel = new Box.Filler(sampleSize,sampleSize,sampleSize);
			
//			samplePanel.setPreferredSize(sampleSize);
//			samplePanel.setMinimumSize(sampleSize);
//			samplePanel.setMaximumSize(sampleSize);
//			samplePanel.setSize(sampleSize);
			polarPanel = new JPanel();
			polarPanel.setBorder(BorderFactory.createTitledBorder("Polar Coordinates"));
//			polarPanel.setLayout(new GridBagLayout());
			polarPanel.setLayout(new BoxLayout(polarPanel,BoxLayout.X_AXIS));
//			for (int i=0;i<3;i++) { 
			polarPanel.add(getJPanel2(), null);
//				phScrollBar[i].setOrientation(JScrollBar.VERTICAL);
			polarPanel.add(getJPanel3(), null);
//				phScrollBar[i].setBlockIncrement(1); 
//			}
//			phScrollBar[0].setMaximum(180);
//			phScrollBar[0].setMinimum(-180);
//			for (int i=1;i<3;i++) {
//				phScrollBar[i].setMaximum(90);
//				phScrollBar[i].setMinimum(-90);
//			}
//			
//			polarPanel.add(phScrollBar[0], new GBC(0,0,1,5));
//			polarPanel.add(phScrollBar[1], new GBC(1,0,1,5));
//			polarPanel.add(phScrollBar[2], new GBC(2,0,1,5));
		}
		return polarPanel;
	}

	/**
	 * This method initializes rotationPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getD4Panel() {
		if (d4Panel == null) {
			d4Panel = new JPanel();
			d4Panel.setLayout(new BoxLayout(d4Panel,BoxLayout.X_AXIS));
			d4Panel.setBorder(BorderFactory.createTitledBorder("4D Camera Motion"));
//			d4Panel.setLayout(new BoxLayout(d4Panel,BoxLayout.Y_AXIS));
			d4Panel.add(getJPanel1(), null);
			d4Panel.add(getDiAxisPanel(), null);
			d4Panel.setToolTipText("Drag mouse on view screen with left or right button.");
		}
		return d4Panel;
	}

	/**
	 * This method initializes transAxisPanel	
	 * 	
	 * @return ddddbb.D3AxisPanel	
	 */
	private JPanel getD3TransAxisPanel() {
		if (d3TransAxisPanel == null) {
			d3TransAxisPanel = new JPanel();
			d3TransAxisPanel.setBorder(BorderFactory.createTitledBorder("trans"));
			d3TransAxisPanel.setLayout(new BoxLayout(d3TransAxisPanel,BoxLayout.Y_AXIS));
			d3TransAxisPanel.setToolTipText("Select translation direction.");
			Opt.d3ViewTransAxis.addAsRadioButtons(d3TransAxisPanel);
//			for (int i=0;i<3;i++) {
//				Opt.d3ViewTransAxis.addButton(i,
//						d3TransAxisPanel.xRadioButton[i]);
//			}
		}
		return d3TransAxisPanel;
	}

	/**
	 * This method initializes d4RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getD4RadioButton() {
		if (d4RadioButton == null) {
			d4RadioButton = new JRadioButton("select");
		}
		return d4RadioButton;
	}

	/**
	 * This method initializes d3Panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getD3Panel() {
		if (d3Panel == null) {
			d3Panel = new JPanel();
			d3Panel.setLayout(new BoxLayout(d3Panel,BoxLayout.Y_AXIS));
			d3Panel.setBorder(BorderFactory.createTitledBorder("3D Camera Motion"));
//			d3Panel.setLayout(new BoxLayout(d3Panel,BoxLayout.X_AXIS));
			d3Panel.add(getJPanel5(), null);
			d3Panel.add(getD3transNRotPanel(), null);
			d3Panel.setToolTipText("Drag mouse on view screen with left or right button.");
		}
		return d3Panel;
	}

	/**
	 * This method initializes d3RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getD3RadioButton() {
		if (d3RadioButton == null) {
			d3RadioButton = new JRadioButton("select");
		}
		return d3RadioButton;
	}

	/**
	 * This method initializes sysElemPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbsRelPanel() {
		if (absRelPanel == null) {
			absRelPanel = new JPanel();
			absRelPanel.setLayout(new BoxLayout(absRelPanel,BoxLayout.X_AXIS));
			absRelPanel.setBorder(BorderFactory.createTitledBorder("Coordinate System"));
			Opt.viewAbsRel.addAsRadioButtons(absRelPanel);
		}
		return absRelPanel;
	}

//	/**
//	 * This method initializes relRadioButton	
//	 * 	
//	 * @return javax.swing.JRadioButton	
//	 */
//	private JRadioButton getRelRadioButton() {
//		if (relRadioButton == null) {
//			relRadioButton = new JRadioButton("camera");
//		}
//		return relRadioButton;
//	}

//	/**
//	 * This method initializes absRadioButton	
//	 * 	
//	 * @return javax.swing.JRadioButton	
//	 */
//	private JRadioButton getAbsRadioButton() {
//		if (absRadioButton == null) {
//			absRadioButton = new JRadioButton("space");
//		}
//		return absRadioButton;
//	}

	/**
	 * This method initializes d3rotAxisPanel	
	 * 	
	 * @return ddddbb.D3AxisPanel	
	 */
	private JPanel getD3RotAxisPanel() {
		if (d3RotAxisPanel == null) {
			d3RotAxisPanel = new JPanel();
			d3RotAxisPanel.setBorder(BorderFactory.createTitledBorder("rot"));
			d3RotAxisPanel.setLayout(new BoxLayout(d3RotAxisPanel,BoxLayout.Y_AXIS));
			d3RotAxisPanel.setToolTipText("Select rotation axis.");
			Opt.d3ViewRotAxis.addAsRadioButtons(d3RotAxisPanel);
		}
		return d3RotAxisPanel;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
			jPanel.add(getD4Panel(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes d3transNRotPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getD3transNRotPanel() {
		if (d3transNRotPanel == null) {
			d3transNRotPanel = new JPanel();
			d3transNRotPanel.setLayout(new BoxLayout(d3transNRotPanel,BoxLayout.X_AXIS));
			d3transNRotPanel.add(getD3TransAxisPanel(), null);
			d3transNRotPanel.add(getD3RotAxisPanel(), null);
		}
		return d3transNRotPanel;
	}

	/**
	 * This method initializes default4dViewButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReset4dViewButton() {
		if (reset4dViewButton == null) {
			reset4dViewButton = new JButton("reset");
			reset4dViewButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.camera4d.setToDefault();
				}
			});
		}
		return reset4dViewButton;
	}

	/**
	 * This method initializes default3dViewButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReset3dViewButton() {
		if (reset3dViewButton == null) {
			reset3dViewButton = new JButton("reset");
			reset3dViewButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.camera3d.setToDefault();
				}
			});
		}
		return reset3dViewButton;
	}

	/**
	 * This method initializes axisPanel	
	 * 	
	 * @return ddddbb.AxisPanel	
	 */
	private AxisPanel getAxisPanel() {
		if (transAxisPanel == null) {
			transAxisPanel = new AxisPanel(Opt.viewTransAxis);
			transAxisPanel.setToolTipText("Choose translation direction");
		}
		return transAxisPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new BoxLayout(jPanel1,BoxLayout.Y_AXIS));
			jPanel1.add(getD4RadioButton(), null);
			jPanel1.add(getReset4dViewButton(), null);
			jPanel1.add(getAxisPanel(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(jPanel2,BoxLayout.X_AXIS));
			jPanel2.add(getPh1ScrollBar(), null);
			jPanel2.add(getPh2ScrollBar(), null);
			jPanel2.add(getPh3ScrollBar(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jScrollBar	
	 * 	
	 * @return javax.swing.JScrollBar	
	 */
	private JScrollBar getPh1ScrollBar() {
		if (ph1ScrollBar == null) {
			ph1ScrollBar = new PhScrollBar(0);
			ph1ScrollBar.setMinimum(-180);
			ph1ScrollBar.setMaximum(180);
			ph1ScrollBar.setUnitIncrement(1);
		}
		return ph1ScrollBar;
	}

	/**
	 * This method initializes jScrollBar1	
	 * 	
	 * @return javax.swing.JScrollBar	
	 */
	private JScrollBar getPh2ScrollBar() {
		if (ph2ScrollBar == null) {
			ph2ScrollBar = new PhScrollBar(1);
			ph2ScrollBar.setMinimum(-90);
			ph2ScrollBar.setMaximum(90);
			ph2ScrollBar.setUnitIncrement(1);
		}
		return ph2ScrollBar;
	}

	/**
	 * This method initializes jScrollBar2	
	 * 	
	 * @return javax.swing.JScrollBar	
	 */
	private JScrollBar getPh3ScrollBar() {
		if (ph3ScrollBar == null) {
			ph3ScrollBar = new PhScrollBar(2);
			ph3ScrollBar.setMinimum(-90);
			ph3ScrollBar.setMaximum(90);
			ph3ScrollBar.setUnitIncrement(1);
		}
		return ph3ScrollBar;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			x4Label = new JLabel();
			x4Label.setText("JLabel");
			x3Label = new JLabel();
			x3Label.setText("JLabel");
			x2Label = new JLabel();
			x2Label.setText("JLabel");
			x1Label = new JLabel();
			x1Label.setText("JLabel");
			ph3Label = new JLabel();
			ph3Label.setText("JLabel");
			ph2Label = new JLabel();
			ph2Label.setText("JLabel");
			ph1Label = new JLabel();
			ph1Label.setText("JLabel");
			jLabel = new JLabel();
			jLabel.setText("----------------");
			jPanel3 = new JPanel();
			jPanel3.setLayout(new BoxLayout(jPanel3,BoxLayout.Y_AXIS));
			jPanel3.add(jLabel, null);
			jPanel3.add(ph1Label, null);
			jPanel3.add(ph2Label, null);
			jPanel3.add(ph3Label, null);
			jPanel3.add(x1Label, null);
			jPanel3.add(x2Label, null);
			jPanel3.add(x3Label, null);
			jPanel3.add(x4Label, null);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jPanel4	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(new BoxLayout(jPanel4,BoxLayout.Y_AXIS));
			jPanel4.add(getAbsRelPanel(), null);
			jPanel4.add(getD3Panel(), null);
		}
		return jPanel4;
	}

	/**
	 * This method initializes jPanel5	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel5() {
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setLayout(new BoxLayout(jPanel5,BoxLayout.X_AXIS));
			jPanel5.add(getD3RadioButton(), null);
			jPanel5.add(getReset3dViewButton(), null);
		}
		return jPanel5;
	}

	/**
	 * This method initializes jPanel6	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			jPanel6 = new JPanel();
			jPanel6.add(getJPanel4(), null);
			jPanel6.add(getJPanel(), null);
			jPanel6.add(getPolarPanel(), null);
		}
		return jPanel6;
	}

	/**
	 * This method initializes jPanel7	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel7() {
		if (jPanel7 == null) {
			objectiveLabel = new JLabel();
//			objectiveLabel.setText("JLabel");
//			objectiveLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);

			jPanel7 = new JPanel();
			//jPanel7.add(objectiveLabel, null);
			
			//Opt.objectives.addAsComboBox(jPanel7);
			jPanel7.add(new JLabel("Zoom:"),null);
			jPanel7.add(new DoubleSpinner(Opt.zoom));
			jPanel7.add(new JLabel("Projection:"),null);
			ddddbb.math.Param.perspective.addAsComboBox(jPanel7).setToolTipText("Perspective");
			jPanel7.add(new JLabel("View Axis:"),null);
			ddddbb.math.Param.perspectiveAxis.addAsComboBox(jPanel7).setToolTipText("View Axis");
		}
		return jPanel7;
	}

}  //  @jve:decl-index=0:visual-constraint="9,-2"
