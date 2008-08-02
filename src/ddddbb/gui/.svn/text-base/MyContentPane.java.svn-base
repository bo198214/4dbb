package ddddbb.gui;

import java.awt.CardLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPanel;

import ddddbb.game.Opt;
import ddddbb.game.Opt.ShowedScreen;
import ddddbb.gen.MyChangeListener;

public class MyContentPane extends JPanel {
	private static final long serialVersionUID = -3031673861156377204L;
	
	//by some reason with this focus listener buttons cant be pressed anymore
	//TODO remove debugging code
	protected final FocusListener mainFocusListener = new FocusListener() {
		public void focusGained(FocusEvent e1) {
			System.out.println("Main screen got input focus.");
		}
		public void focusLost(FocusEvent e1) {
			System.out.println("Main screen lost input focus.");
			ShowedScreen.MAIN.panel().requestFocusInWindow();
		}
	};

	private CardLayout mainPanelCardLayout = null;
	/**
	 * This is the default constructor
	 */
	public MyContentPane() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		//this.setSize(300, 200);
		mainPanelCardLayout = new CardLayout(); 
		setLayout(mainPanelCardLayout);
		Opt.showedScreen.addAsCards(this,mainPanelCardLayout);
		Opt.showedScreen.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				if (Opt.showedScreen.getSelectedObject() == ShowedScreen.MAIN) {
					ShowedScreen.MAIN.panel().setInputVerifier(new InputVerifier() {
						@Override
						public boolean verify(JComponent input) {
							// this assures that focus never leaves this window
							return false;
						}
					});
//					ShowedScreen.MAIN.panel().addFocusListener(mainFocusListener);
				}
				else {
					ShowedScreen.MAIN.panel().setInputVerifier(null);
//					ShowedScreen.MAIN.panel().removeFocusListener(mainFocusListener);
				}
				Opt.showedScreen.getSelectedObject().panel().requestFocusInWindow();
			}
		});
	}
	
}
