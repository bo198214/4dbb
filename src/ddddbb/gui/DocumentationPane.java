package ddddbb.gui;

import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class DocumentationPane extends JPanel {
	private static final long serialVersionUID = 1991142944038184397L;
	private JScrollPane jScrollPane = null;
	private JTextPane jTextPane = null;

	/**
	 * This is the default constructor
	 */
	public DocumentationPane() {
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
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(getJScrollPane(), null);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();

			jScrollPane.setViewportView(getJTextPane());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJTextPane() {
		if (jTextPane == null) {
			jTextPane = new JTextPane();
			jTextPane.setEditable(true);
			try {
				jTextPane.setPage(
						DocumentationPane.class.getResource("documentation.html")
						);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return jTextPane;
	}

}
