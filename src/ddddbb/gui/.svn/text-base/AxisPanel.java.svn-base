package ddddbb.gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import ddddbb.gen.IntStringModel;

public class AxisPanel extends JPanel {
	private static final long serialVersionUID = -4624597087825673201L;
	private IntStringModel axis;
	private TitledBorder border = BorderFactory.createTitledBorder("trans");

	public JRadioButton[] xRadioButton = {
			new JRadioButton(),
			new JRadioButton(),
			new JRadioButton(),
			new JRadioButton()
	};

//	public AxisPanel() {
//		super();
//		axis = new IntModel(0);
//		for (int i=0;i<4;i++) {
//			xRadioButton[i].setText("v"+(i+1));
//		}
//		initialize();
//	}
//
	public AxisPanel(IntStringModel _axis) {
		super();
		axis = _axis;
		initialize();
	}

	private void initialize() {
		this.setSize(300, 200);
		this.setBorder(border);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for (int i=0;i<4;i++) {
			add(xRadioButton[i],null);
			axis.addButton(i,xRadioButton[i]);
		}
	}
	
	public void setTitle(String text) {
		border.setTitle(text);
	}

}
