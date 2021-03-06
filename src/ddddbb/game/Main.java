package ddddbb.game;

import java.applet.Applet;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;

import ddddbb.gen.AChangeListener;
import ddddbb.gen.BoolModel;
import ddddbb.gen.IntModel;
import ddddbb.gui.AdjustmentPane;
import ddddbb.gui.ContentPane;
import ddddbb.gui.KeyControl;
import ddddbb.gui.LinkEnabledTextPane;
import ddddbb.gui.ViewPane;
import ddddbb.gui.ViewScreen;
import ddddbb.sound.SoundEnum;

public final class Main {
	
	public static final String title = "4D Building Blocks"; 
	public static final String[] axisNames = { "x", "y", "z", "w" };
	public static final BoolModel debug = new BoolModel(true,"Debug");
	
	public static class ShowedScreenEnum extends IntModel<JPanel> {
		public final AdjustmentPane ADJUSTMENT;
		public final ViewPane MAIN;
		public final JPanel DOCUMENTATION;
		public final JPanel ABOUT;
		ShowedScreenEnum(Settings ss, Level scene, Scene4d goalScene, Container window) {
			ADJUSTMENT = new AdjustmentPane(ss, window); 
			//KEYCONTROL = new KeyControlPanel();
			MAIN = new ViewPane(ss,scene,goalScene);
			MAIN.addKeyListener(new KeyControl(ss,scene));
			DOCUMENTATION = new LinkEnabledTextPane(LinkEnabledTextPane.class.getResource("documentation.html"),window);
			ABOUT = new LinkEnabledTextPane(LinkEnabledTextPane.class.getResource("about.html"),window);
			super.init(MAIN,new String[] {"Adjustment", "Scene", "Documentation", "About"}, 
					new JPanel[] { ADJUSTMENT, MAIN, DOCUMENTATION, ABOUT });
		}
    }
	public final ShowedScreenEnum showedScreen;

	public final Level scene;
	public final Scene4d goalScene;
	
//	public static final ViewScreen viewScreen;
	
	public final JPanel contentPane;
	
	public final ViewScreen viewScreen;
	public final Settings ss =new Settings();
	public final Container window;
	public final PersistentPreferences prefs;
	
	public Main(Container _window) {
		window = _window;
		if (window instanceof Applet) {
			prefs = null;
			window.setPreferredSize(new Dimension(Settings.defaultWidth, Settings.defaultHeight));
		}
		else {
			prefs = new PersistentPreferences.User(ss,window);
			prefs.load();
		}

		scene = new Level(ss);
		goalScene = scene.cloneCamRefs();
		new AChangeListener() {
			public void stateChanged() {
				scene.changeObjective(ss.objectives.sel());
				goalScene.setCompounds(new int[][][] {ss.objectives.sel().goal});				
			}}.addTo(ss.objectives);

		
		showedScreen = new ShowedScreenEnum(ss,scene, goalScene,window);
		viewScreen = ((ViewPane)showedScreen.MAIN).viewScreen;
		scene.viewScreen = viewScreen;
		contentPane = new ContentPane(showedScreen);

		new AChangeListener() {
			public void stateChanged() {
				
				if (ss.gameStatus.sel() == Settings.GameStatus.REACHED) {
					//viewScreen.repaint(); //why does this not work?
					if (ss.objectives.selInt() < ss.objectives.getSize()-1) { //before last level
						ss.objectives.setSelInt(ss.objectives.selInt()+1);
						SoundEnum.GOALREACHED.play();										
					}
					else  {
						assert ss.objectives.selInt() == ss.objectives.getSize()-1;
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						SoundEnum.GAMECOMPLETED.play();
					}
					
				}
				if (ss.gameStatus.sel() == Settings.GameStatus.MISSED) {
					SoundEnum.GOALMISSED.play();
					ss.objectives.setSelInt(ss.objectives.selInt());
				}
			}
		
		}.addTo(ss.gameStatus);
		
//		Properties props = new Properties();
//		try {
//			props.load(Main.class.getResourceAsStream("application.properties"));
//		} catch (FileNotFoundException e) {
//			assert false;
//			e.printStackTrace();
//		} catch (IOException e) {
//			assert false;
//			e.printStackTrace();
//		}
		
	}
	
	public void afterPackHook() {
		showedScreen.MAIN.requestFocusInWindow();
	}
}
