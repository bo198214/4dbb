package ddddbb.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ddddbb.comb.DSignedAxis;
import ddddbb.game.Opt;

public class CompoundControlPanel extends JPanel {
	
	private static final long serialVersionUID = 9020302403384102091L;
	private JPanel transPanel = null;
	private JPanel rotPanel = null;
	private JButton v2nButton = null;
	private JButton v2pButton = null;
	private JButton v3pButton = null;
	private JButton v4nButton = null;
	private JButton v4pButton = null;
	private JButton v3nButton = null;
	private JButton v1pButton = null;
	private JButton v1nButton = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JButton jButton5 = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JButton jButton6 = null;
	private JButton jButton7 = null;
	private JButton jButton8 = null;
	private JButton jButton9 = null;
	private JButton jButton10 = null;
	private JButton jButton11 = null;
	private JPanel jPanel = null;
	private JButton prevButton = null;
	private JButton nextButton = null;
	private JPanel jPanel1 = null;
	private JButton jButton23 = null;
	private JPanel jPanel2 = null;
	private JButton baryCenterButton = null;
	/**
	 * This is the default constructor
	 */
	public CompoundControlPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.add(getJPanel(), null);
		this.add(getJPanel2(), null);
	}

	/**
	 * This method initializes transPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTransPanel() {
		if (transPanel == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.gridy = 3;
			jLabel13 = new JLabel();
			jLabel13.setText(Opt.axisNames[3]);
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 1;
			gridBagConstraints9.gridy = 2;
			jLabel12 = new JLabel();
			jLabel12.setText(Opt.axisNames[2]);
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 1;
			gridBagConstraints8.gridy = 1;
			jLabel11 = new JLabel();
			jLabel11.setText(Opt.axisNames[1]);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			jLabel10 = new JLabel();
			jLabel10.setText(Opt.axisNames[0]);
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.gridy = 3;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 3;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 2;
			gridBagConstraints5.gridy = 2;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 2;
			gridBagConstraints1.gridy = 0;
			transPanel = new JPanel();
			transPanel.setBorder(BorderFactory.createTitledBorder("translation"));
//			transPanel.setLayout(new BoxLayout(transPanel,BoxLayout.Y_AXIS));
//			transPanel.setLayout(new GridLayout(4,3));
			transPanel.setLayout(new GridBagLayout());

			transPanel.add(getV1nButton(), new GridBagConstraints());
			transPanel.add(getV1pButton(), gridBagConstraints1);
			transPanel.add(getV2nButton(), gridBagConstraints2);
			transPanel.add(getV2pButton(), gridBagConstraints3);
			transPanel.add(getV3nButton(), gridBagConstraints4);
			transPanel.add(getV3pButton(), gridBagConstraints5);
			transPanel.add(getV4nButton(), gridBagConstraints6);
			transPanel.add(getV4pButton(), gridBagConstraints7);
			transPanel.add(jLabel10, gridBagConstraints);
			transPanel.add(jLabel11, gridBagConstraints8);
			transPanel.add(jLabel12, gridBagConstraints9);
			transPanel.add(jLabel13, gridBagConstraints10);
		}
		return transPanel;
	}

	/**
	 * This method initializes rotPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRotPanel() {
		if (rotPanel == null) {
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.gridx = 2;
			gridBagConstraints28.gridy = 6;
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.gridx = 2;
			gridBagConstraints27.gridy = 5;
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.gridx = 2;
			gridBagConstraints26.gridy = 4;
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			gridBagConstraints25.gridx = 2;
			gridBagConstraints25.gridy = 3;
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.gridx = 2;
			gridBagConstraints24.gridy = 2;
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.gridx = 2;
			gridBagConstraints23.gridy = 1;
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridx = 1;
			gridBagConstraints22.gridy = 6;
			jLabel5 = new JLabel();
			jLabel5.setText("zw");
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.gridy = 5;
			jLabel4 = new JLabel();
			jLabel4.setText("yw");
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 1;
			gridBagConstraints20.gridy = 4;
			jLabel3 = new JLabel();
			jLabel3.setText("yz");
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.gridx = 1;
			gridBagConstraints19.gridy = 3;
			jLabel2 = new JLabel();
			jLabel2.setText("xw");
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 1;
			gridBagConstraints18.gridy = 2;
			jLabel1 = new JLabel();
			jLabel1.setText("xz");
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridy = 1;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.gridx = 1;
			gridBagConstraints16.gridy = 1;
			jLabel = new JLabel();
			jLabel.setText("xy");
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 0;
			gridBagConstraints15.gridy = 6;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.gridy = 5;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 0;
			gridBagConstraints13.gridy = 4;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.gridy = 3;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 2;
			rotPanel = new JPanel();
			//rotPanel.setLayout(new BoxLayout(rotPanel,BoxLayout.Y_AXIS));
			//rotPanel.setLayout(new GridLayout(6,3));
			rotPanel.setBorder(BorderFactory.createTitledBorder("rotation"));
			rotPanel.setLayout(new GridBagLayout());
			rotPanel.add(getJButton(), gridBagConstraints17);
			rotPanel.add(getJButton1(), gridBagConstraints11);
			rotPanel.add(getJButton2(), gridBagConstraints12);
			rotPanel.add(getJButton3(), gridBagConstraints13);
			rotPanel.add(getJButton4(), gridBagConstraints14);
			rotPanel.add(getJButton5(), gridBagConstraints15);
			rotPanel.add(jLabel, gridBagConstraints16);
			rotPanel.add(jLabel1, gridBagConstraints18);
			rotPanel.add(jLabel2, gridBagConstraints19);
			rotPanel.add(jLabel3, gridBagConstraints20);
			rotPanel.add(jLabel4, gridBagConstraints21);
			rotPanel.add(jLabel5, gridBagConstraints22);
			rotPanel.add(getJButton6(), gridBagConstraints23);
			rotPanel.add(getJButton7(), gridBagConstraints24);
			rotPanel.add(getJButton8(), gridBagConstraints25);
			rotPanel.add(getJButton9(), gridBagConstraints26);
			rotPanel.add(getJButton10(), gridBagConstraints27);
			rotPanel.add(getJButton11(), gridBagConstraints28);
			
		}
		return rotPanel;
	}

	/**
	 * This method initializes jButton12	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getV2nButton() {
		if (v2nButton == null) {
			v2nButton = new JButton();
			v2nButton.setText("-");
			v2nButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.transSelected(new DSignedAxis(-2));				}
			});
		}
		return v2nButton;
	}

	/**
	 * This method initializes jButton13	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getV2pButton() {
		if (v2pButton == null) {
			v2pButton = new JButton();
			v2pButton.setText("+");
			v2pButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.transSelected(new DSignedAxis(+2));				}
			});
		}
		return v2pButton;
	}

	/**
	 * This method initializes jButton15	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getV3pButton() {
		if (v3pButton == null) {
			v3pButton = new JButton();
			v3pButton.setText("+");
			v3pButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.transSelected(new DSignedAxis(+3));
				}
			});
		}
		return v3pButton;
	}

	/**
	 * This method initializes jButton16	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getV4nButton() {
		if (v4nButton == null) {
			v4nButton = new JButton();
			v4nButton.setText("-");
			v4nButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.transSelected(new DSignedAxis(-4));
				}
			});
		}
		return v4nButton;
	}

	/**
	 * This method initializes jButton17	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getV4pButton() {
		if (v4pButton == null) {
			v4pButton = new JButton();
			v4pButton.setText("+");
			v4pButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.transSelected(new DSignedAxis(+4));
				}
			});
		}
		return v4pButton;
	}

	/**
	 * This method initializes jButton18	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getV3nButton() {
		if (v3nButton == null) {
			v3nButton = new JButton();
			v3nButton.setText("-");
			v3nButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.transSelected(new DSignedAxis(-3));				}
			});
		}
		return v3nButton;
	}

	/**
	 * This method initializes jButton19	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getV1pButton() {
		if (v1pButton == null) {
			v1pButton = new JButton();
			v1pButton.setText("+");
			v1pButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.transSelected(new DSignedAxis(+1));				}
			});
		}
		return v1pButton;
	}

	/**
	 * This method initializes jButton20	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getV1nButton() {
		if (v1nButton == null) {
			v1nButton = new JButton();
			v1nButton.setText("-");
			v1nButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.transSelected(new DSignedAxis(-1));
				}
			});
		}
		return v1nButton;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("-");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(1,0);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("-");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(2,0);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("-");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(3,0);
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("-");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(2,1);
				}
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("-");
			jButton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(3,1);
				}
			});
		}
		return jButton4;
	}

	/**
	 * This method initializes jButton5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("-");
			jButton5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(3,2);
				}
			});
		}
		return jButton5;
	}

	/**
	 * This method initializes jButton6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("+");
			jButton6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(0,1);
				}
			});
		}
		return jButton6;
	}

	/**
	 * This method initializes jButton7	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setText("+");
			jButton7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(0,2);
				}
			});
		}
		return jButton7;
	}

	/**
	 * This method initializes jButton8	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton8() {
		if (jButton8 == null) {
			jButton8 = new JButton();
			jButton8.setText("+");
			jButton8.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(0,3);
				}
			});
		}
		return jButton8;
	}

	/**
	 * This method initializes jButton9	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton9() {
		if (jButton9 == null) {
			jButton9 = new JButton();
			jButton9.setText("+");
			jButton9.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(1,2);
				}
			});
		}
		return jButton9;
	}

	/**
	 * This method initializes jButton10	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton10() {
		if (jButton10 == null) {
			jButton10 = new JButton();
			jButton10.setText("+");
			jButton10.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(1,3);
				}
			});
		}
		return jButton10;
	}

	/**
	 * This method initializes jButton11	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton11() {
		if (jButton11 == null) {
			jButton11 = new JButton();
			jButton11.setText("+");
			jButton11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.rotateSelected(2,3);
				}
			});
		}
		return jButton11;
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
			jPanel.add(getTransPanel(), null);
			jPanel.add(getJPanel1(), null);
			jPanel.add(getJButton23(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton21	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPrevButton() {
		if (prevButton == null) {
			prevButton = new JButton();
			prevButton.setText("prev");
			prevButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.compounds.prevSelected();
				}
			});
		}
		return prevButton;
	}

	/**
	 * This method initializes jButton22	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton();
			nextButton.setText("next");
			nextButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.compounds.nextSelected();
				}
			});
		}
		return nextButton;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.add(getPrevButton(), null);
			jPanel1.add(getNextButton(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jButton23	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton23() {
		if (jButton23 == null) {
			jButton23 = new JButton();
			jButton23.setText("combine");
			jButton23.setAlignmentX(Component.CENTER_ALIGNMENT);
			jButton23.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.combine();
				}
			});
		}
		return jButton23;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new BoxLayout(jPanel2,BoxLayout.Y_AXIS));
			jPanel2.add(getRotPanel(), null);
			jPanel2.add(getBaryCenterButton(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes baryCenterButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBaryCenterButton() {
		if (baryCenterButton == null) {
			baryCenterButton = new JButton();
			baryCenterButton.setText("barycenter");
			baryCenterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			baryCenterButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Opt.scene.compounds.getSelectedItem().setBaryCenter();
				}
			});
		}
		return baryCenterButton;
	}

}  //  @jve:decl-index=0:visual-constraint="8,9"
