package ddddbb;

import javax.swing.JApplet;

import ddddbb.game.Main;
import ddddbb.gui.TheMenuBar;

@SuppressWarnings("serial")
public class Applet extends JApplet {
	/**
	 * Applet initialization
	 */
	@Override
	public void init() {
		Main main = new Main(this);
		setJMenuBar(new TheMenuBar(main));
		setContentPane(main.contentPane);
		invalidate();
		main.afterPackHook();
	}
}  //  @jve:decl-index=0:visual-constraint="4,5"
