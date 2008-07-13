package ddddbb.gui;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class AboutPane extends JPanel {
	private static final long serialVersionUID = 270882618502046682L;
	private JTextPane aboutTextPane = null;

	/**
	 * This is the default constructor
	 */
	public AboutPane() {
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
		this.add(getAboutTextPane(), null);
	}

	/**
	 * This method initializes aboutTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getAboutTextPane() {
		if (aboutTextPane == null) {
			aboutTextPane = new JTextPane();
			aboutTextPane.setEditable(false);
			try {
				aboutTextPane.setPage(
						AboutPane.class.getResource("about.html")
						);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return aboutTextPane;
	}

}
