package ddddbb.game;

import java.awt.Container;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JPanel;

import ddddbb.gen.BoolModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.MyChangeListener;
import ddddbb.gui.AboutPane;
import ddddbb.gui.AdjustmentPane;
import ddddbb.gui.ContentPane;
import ddddbb.gui.DocumentationPane;
import ddddbb.gui.KeyControl;
import ddddbb.gui.KeyControlPanel;
import ddddbb.gui.ViewPane;
import ddddbb.gui.ViewScreen;
import ddddbb.math.AOP;
import ddddbb.math.Camera4d;
import ddddbb.math.Camera4dOrthographic;
import ddddbb.math.CavalierPerspective;
import ddddbb.math.Isometric30Perspective;
import ddddbb.math.OrthographicPerspective;
import ddddbb.math.PhotoPerspective;
import ddddbb.math.Point3d;
import ddddbb.math.Point4d;
import ddddbb.sound.Sound;

public final class Main {
	
	public static final String title = "4D Building Blocks"; 
	public static final String[] axisNames = { "x", "y", "z", "w" };
	public static final BoolModel debug = new BoolModel(true,"Debug");
	
	public static class PerspectiveEnum extends IntModel<Camera4d>{
		public final Camera4d LINEAR;
		public final Camera4d ISOMETRIC30;
		public final Camera4d CAVALIER;
		public final Camera4d ORTHOGRAPHIC;
		public final Camera4d DIMETRIC;
		
		public Camera4d[] values;
		
		public PerspectiveEnum() {
			LINEAR = new PhotoPerspective();
			ISOMETRIC30 = new Isometric30Perspective();
			CAVALIER = new CavalierPerspective();
			ORTHOGRAPHIC = new OrthographicPerspective();
			DIMETRIC = new Camera4dOrthographic.Dimetric();
			values = new Camera4d[] {
					LINEAR,
					ISOMETRIC30,
					CAVALIER,
					ORTHOGRAPHIC,
					DIMETRIC
			};
			super.init(CAVALIER, values);
		}
	}
	
	public final IntModel<Camera4d> perspective = new PerspectiveEnum();

	
	
	public int applicationHeight;
	public int applicationWidth;
	
	public static abstract class ViewAbsRel {
		final Scene scene;
		String name;
		ViewAbsRel(Scene _scene) {	scene = _scene;	}
		public abstract Point3d selectDirec3d(int i);
		public abstract Point4d selectDirec4d(int i);
		public abstract Point3d selectCenter3d();
		public abstract Point4d selectCenter4d();
	};
	public static class ViewAbsRelEnum extends IntModel<ViewAbsRel> {
		public final ViewAbsRel SYSTEM;
		public final ViewAbsRel CAMERA;
		public final ViewAbsRel[] values; 
		ViewAbsRelEnum(Scene scene) {
			SYSTEM  = new ViewAbsRel(scene) {
				public String toString() {	return "system"; }
				Point3d p3 = new Point3d();
				public Point3d selectDirec3d(int i) {
					return AOP.unitVector3(i);
				}
				public Point4d selectDirec4d(int i) {
					return AOP.unitVector4(i);
				}
				public Point3d selectCenter3d() {
					scene.camera4d.proj3d(selectCenter4d(),p3);
					return p3;
					//return new Point3d(0,0,0);			
				}
				public Point4d selectCenter4d() {
					return (Point4d)scene.compounds.getSelectedItem().center.loc(); 
					//return new Point4d(0,0,0,0);
				}		
			};
			CAMERA = new ViewAbsRel(scene) {
				public String toString() {	return "camera";	}
				Point3d p3 = new Point3d();
				public Point3d selectDirec3d(int i) {
					return scene.camera3d.v[i].clone();
				}
				public Point4d selectDirec4d(int i) {			
					return scene.camera4d.v[i].clone();
				}
				public Point3d selectCenter3d() {
					scene.camera4d.proj3d(selectCenter4d(),p3);
					return p3;
//					Point3d res = new Point3d(Main.opt.scene.camera3d.eye);
//					Point3d d = new Point3d(Main.opt.scene.camera3d.v[2]);
//					d.multiply(Main.opt.screenEyeDist.getDouble());
//					res.translate(d);
//					return res;				
				}
				public Point4d selectCenter4d() {
					return (Point4d)scene.compounds.getSelectedItem().center.loc(); 
//					return new Point4d(0,0,0,0);
				}				
			};
			values = new ViewAbsRel[] { SYSTEM, CAMERA };
			super.init(CAMERA, values);
		}
	} 
	public final ViewAbsRelEnum viewAbsRel; 
	public final IntModel<Objectives> objectives = new IntModel<Objectives>(Objectives.values());
	
	public static class ShowedScreenEnum extends IntModel<JPanel> {
		public final AdjustmentPane ADJUSTMENT;
		public final KeyControlPanel KEYCONTROL = null;
		public final ViewPane MAIN;
		public final DocumentationPane DOCUMENTATION;
		public final AboutPane ABOUT;
		ShowedScreenEnum(ScreenValues sv, SimpleSwitches ss, Scene scene, ViewAbsRelEnum viewAbsRel, IntModel<Objectives> objectives,
				IntModel<Camera4d> perspective, Scene4d goalScene) {
			ADJUSTMENT = new AdjustmentPane(sv,ss.viewType);
			//KEYCONTROL = new KeyControlPanel();
			MAIN = new ViewPane(scene,ss.showGoal,ss.zoom,viewAbsRel,ss.dim34,objectives,sv,perspective,ss.drawTetrahedral,ss.showInternalFaces,ss.antiAliased,ss.viewType,ss.viewTransAxis,ss.d3ViewRotAxis,goalScene);
			MAIN.addKeyListener(new KeyControl(scene,ss.showGoal,ss.viewRotXAxis12,ss.d3ViewRotAxis,ss.dim34,viewAbsRel));
			DOCUMENTATION = new DocumentationPane();
			ABOUT = new AboutPane();
			super.init(MAIN,new String[] {"adjustment", "main", "doc", "about"}, 
					new JPanel[] { ADJUSTMENT, MAIN, DOCUMENTATION, ABOUT });
		}
    }
	public final ShowedScreenEnum showedScreen;

	public static enum GameStatus {
		NONE, PENDING, REACHED, MISSED;
	}
	public final IntModel<GameStatus> gameStatus = new IntModel<GameStatus>(GameStatus.PENDING, GameStatus.values());
	public final Scene scene;
	public final Scene4d goalScene;
	
//	public static final ViewScreen viewScreen;
	
	public final JPanel contentPane;
	
	public final ScreenValues sv; 
	public final ViewScreen viewScreen;
	public final SimpleSwitches ss =new SimpleSwitches();
	public final Sound sound = new Sound();
	
	public Main(Container window) {
		sv = new ScreenValues(window);
		scene = new Scene(ss.zoom,ss.orientation4d,ss.orientation3d,ss.showGoal,perspective,ss.occlusion4dAllowance,ss.showInternalFaces,
				sv.screenEyeDist,sv.eyesDistHalf,
				sv.barEyeFocusDelta,sound,gameStatus);
		viewAbsRel = new ViewAbsRelEnum(scene);		
		goalScene = new Scene4d(ss.zoom,ss.orientation4d,ss.orientation3d,ss.showGoal, perspective,ss.occlusion4dAllowance,ss.showGoal,
				sv.screenEyeDist,sv.eyesDistHalf,sv.barEyeFocusDelta,sound);
		objectives.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				scene.changeObjective(objectives.getSelectedObject());
				goalScene.setCompounds(new int[][][] {objectives.getSelectedObject().goal});				
			}});

		
		showedScreen = new ShowedScreenEnum(sv,ss,scene, viewAbsRel,objectives, perspective,goalScene);
		viewScreen = ((ViewPane)showedScreen.MAIN).viewScreen;
		contentPane = new ContentPane(showedScreen);

		gameStatus.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				
				if (gameStatus.getSelectedObject() == GameStatus.REACHED) {
					viewScreen.repaint(); //why does this not work?
					sound.GOALREACHED.play2end();
					if (objectives.getInt() < objectives.getSize()-1) { //before last level
						objectives.setInt(objectives.getInt()+1);
										
					}
					if (objectives.getInt() == objectives.getSize()-1) {
						//TODO: last level completed congratulations
					}
					
				}
				if (gameStatus.getSelectedObject() == GameStatus.MISSED) {
					sound.GOALMISSED.play2end();
					objectives.setInt(objectives.getInt());
				}
			}
		
		});
		
		Properties props = new Properties();
		try {
			props.load(Main.class.getResourceAsStream("application.properties"));
		} catch (FileNotFoundException e) {
			assert false;
			e.printStackTrace();
		} catch (IOException e) {
			assert false;
			e.printStackTrace();
		}
		applicationWidth = Integer.parseInt(props.getProperty("width"));
		applicationHeight = Integer.parseInt(props.getProperty("height"));
		
//		setSize(Main.opt.applicationWidth, Main.opt.applicationHeight);
		window.setPreferredSize(new Dimension(applicationWidth, applicationHeight));
		
	}
	
}
