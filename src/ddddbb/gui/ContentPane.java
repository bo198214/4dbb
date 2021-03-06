package ddddbb.gui;

import java.awt.CardLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPanel;

import ddddbb.game.Main.ShowedScreenEnum;
import ddddbb.gen.AChangeListener;

@SuppressWarnings("serial")
public class ContentPane extends JPanel {
	
	//by some reason with this focus listener buttons cant be pressed anymore
	//TODO remove debugging code
	protected final FocusListener mainFocusListener; 

	private final CardLayout mainPanelCardLayout;
	/**
	 * This is the default constructor
	 */
	public ContentPane(final ShowedScreenEnum showedScreen) {
		mainFocusListener = new FocusListener() {
			public void focusGained(FocusEvent e1) {
				System.out.println("Main screen got input focus.");
			}
			public void focusLost(FocusEvent e1) {
				System.out.println("Main screen lost input focus.");
				showedScreen.MAIN.requestFocusInWindow();
			}
		};
		showedScreen.MAIN.requestFocusInWindow();
		//this.setSize(300, 200);
		mainPanelCardLayout = new CardLayout(); 
		setLayout(mainPanelCardLayout);
		showedScreen.addAsCards(this,mainPanelCardLayout);
		new AChangeListener() {
			public void stateChanged() {
				if (showedScreen.sel() == showedScreen.MAIN) {
					showedScreen.MAIN.setInputVerifier(new InputVerifier() {
						@Override
						public boolean verify(JComponent input) {
							// this assures that focus never leaves this window
							return false;
						}
					});
//					showedScreen.MAIN.addFocusListener(mainFocusListener);
				}
				else {
					showedScreen.MAIN.setInputVerifier(null);
//					showedScreen.MAIN.removeFocusListener(mainFocusListener);
				}
				showedScreen.sel().requestFocusInWindow();
			}
		}.addTo(showedScreen);
	}	
}
