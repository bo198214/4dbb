package test.d3;

import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JPanel;

import ddddbb.game.ScreenValues;
import ddddbb.game.Settings;

@SuppressWarnings("serial")
public class Test3d extends JApplet {
	public final Settings ss = new Settings();
	public final ScreenValues sv;
	private JPanel jContentPane;

	private ViewScreen3d viewScreen3d;

	/**
	 * This is the xxx default constructor
	 */
	public Test3d() {
		sv = new ScreenValues(this);
	}

	public void init() {
		setSize(300, 200);
		viewScreen3d = new ViewScreen3d();
		jContentPane = new JPanel();
		jContentPane.setLayout(new BorderLayout());
		jContentPane.add(viewScreen3d, BorderLayout.CENTER);
		setContentPane(jContentPane);
	}
}
