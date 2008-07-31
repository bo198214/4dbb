package ddddbb;

import javax.swing.JFrame;

import ddddbb.game.Main;
import ddddbb.gui.MyMenuBar;

public class Application extends JFrame {
	
	private static final long serialVersionUID = 627867565200601309L;

	/**
	 * This is the default constructor
	 */
	public Application() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		Main main = new Main(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(new MyMenuBar(main));
		setContentPane(main.contentPane);
		setTitle("4d building blocks");
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
