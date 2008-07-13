package ddddbb.gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ddddbb.game.Opt;

public class D3AxisPanel extends JPanel {
	private static final long serialVersionUID = -6267893679596626474L;
	public JRadioButton[] xRadioButton = {
			new JRadioButton(Opt.d3axisNames[0]),
			new JRadioButton(Opt.d3axisNames[1]),
			new JRadioButton(Opt.d3axisNames[2])
	};

	/**
	 * This is the default constructor
	 */
	public D3AxisPanel() {
		super();
		initialize("axis");
	}
	
	public D3AxisPanel(String title) {
		super();
		initialize(title);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(String title) {
		this.setSize(300, 200);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(title));
		for (int i=0;i<3;i++) {
			add(xRadioButton[i]);
		}
	}
}
