package test.d3;

import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;

public class Test3d extends JApplet {
	private static final long serialVersionUID = 8870460436654326338L;

	public static Opt opt = new Opt();  //  @jve:decl-index=0:
	
	private JPanel jContentPane = null;

	private ViewScreen3d viewScreen3d = null;

	/**
	 * This is the xxx default constructor
	 */
	public Test3d() {
		super();
		opt.initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void init() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getViewScreen3d(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes viewScreen3d	
	 * 	
	 * @return test.d3.ViewScreen3d	
	 */
	private ViewScreen3d getViewScreen3d() {
		if (viewScreen3d == null) {
			viewScreen3d = new ViewScreen3d();
		}
		return viewScreen3d;
	}

}
