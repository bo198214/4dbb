package ddddbb.gui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ddddbb.gen.DiAxisModel;

public class DiAxisPanel extends JPanel {
	private static final long serialVersionUID = 5606315918977294790L;

	DiAxisModel sel;

	private TitledBorder border = null;
	
	public DiAxisPanel(DiAxisModel _sel) {
		sel = _sel;
		initialize();
	}
	
	public void setTitle(String title) {
		border.setTitle(title);
	}
	
	private void initialize() {
        this.setSize(new java.awt.Dimension(90,186));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        border = BorderFactory.createTitledBorder("rot");
        this.setBorder(border);
        sel.addAsRadioButtons(this);
 	}
	
	public void setEnabled(boolean enabled) {
		
	}
}  //  @jve:decl-index=0:visual-constraint="10,2"
