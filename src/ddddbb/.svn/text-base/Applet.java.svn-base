package ddddbb;

import javax.swing.JApplet;

import ddddbb.game.Opt;
import ddddbb.gui.MyMenuBar;

public class Applet extends JApplet {
	private static final long serialVersionUID = 6142102208419131502L;

	/**
	 * This is the default constructor
	 */
	public Applet() {
		super();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void init() {
		Opt.initialize(this);
		setJMenuBar(new MyMenuBar());
		setContentPane(Opt.contentPane);
		invalidate();
	}
}  //  @jve:decl-index=0:visual-constraint="4,5"
