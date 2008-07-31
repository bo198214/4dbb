package ddddbb;

import javax.swing.JApplet;

import ddddbb.game.Main;
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
		Main main = new Main(this);
		setJMenuBar(new MyMenuBar(main));
		setContentPane(main.contentPane);
		invalidate();
	}
}  //  @jve:decl-index=0:visual-constraint="4,5"
