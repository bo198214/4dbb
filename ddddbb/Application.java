package ddddbb;

import javax.swing.JFrame;

import ddddbb.game.Main;
import ddddbb.gui.TheMenuBar;

@SuppressWarnings("serial")
public class Application extends JFrame {

	public Application() {
		super();
		Main main = new Main(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(new TheMenuBar(main));
		setContentPane(main.contentPane);
		setTitle(Main.title);
		pack();
	}

	/**
	 * Launches this application
	 */
	public static void main(String[] args) {
		Application application = new Application();
		//application.show();
		application.setVisible(true);
	}

}  //  @jve:decl-index=0:visual-constraint="9,7"
