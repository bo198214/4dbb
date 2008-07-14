package ddddbb.game;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JPanel;

import ddddbb.gen.BoolModel;
import ddddbb.gen.DiAxisModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.IntStringModel;
import ddddbb.gen.MyChangeListener;
import ddddbb.gen.Unit;
import ddddbb.gui.AboutPane;
import ddddbb.gui.AdjustmentPane;
import ddddbb.gui.DocumentationPane;
import ddddbb.gui.KeyControl;
import ddddbb.gui.KeyControlPanel;
import ddddbb.gui.MyContentPane;
import ddddbb.gui.ViewPane;
import ddddbb.gui.ViewScreen;
import ddddbb.math.AnaglyphGraphics;
import ddddbb.math.Camera3d;
import ddddbb.math.CrossEyedGraphics;
import ddddbb.math.D2GraphicsIF;
import ddddbb.math.D3Graphics;
import ddddbb.math.Direc3d;
import ddddbb.math.Direc4d;
import ddddbb.math.Flat3dGraphics;
import ddddbb.math.ParallelEyedGraphics;
import ddddbb.math.Point3d;
import ddddbb.math.Point4d;

public final class Opt {
	public final static Direc4d spaceCoord4d[] = {
			new Direc4d(1,0,0,0), new Direc4d(0,1,0,0), new Direc4d(0,0,1,0), new Direc4d(0,0,0,1) }; 
	public final static Direc3d spaceCoord3d[] = {
			new Direc3d(1,0,0), new Direc3d(0,1,0), new Direc3d(0,0,1) };
	
	
	public static int applicationHeight;
	public static int applicationWidth;
	
	//TODO: adapt zoom to screen resolution/window size
	public static final DoubleModel zoom = new DoubleModel(1,0.2);
	
	public static final IntStringModel dim34 = new IntStringModel(1,new String[] {"D3","D4"});
	public static final String[] axisNames = { "x", "y", "z", "w" };
	public static final IntStringModel viewTransAxis = new IntStringModel(1, axisNames);
	
	//public IntStringModel viewRotXAxis2 = new IntStringModel(1,axisNames);
	//public DiAxisModel viewRotYAxis12 = new DiAxisModel(2,3);
	//public IntStringModel viewRotYAxis2 = new IntStringModel(3,axisNames);
	
	public static final IntStringModel viewTransRot = new IntStringModel(1, new String[] { "trans", "rot"});
	public enum ViewAbsRel {
		SYSTEM("space") {
			Point3d p3 = new Point3d();
			public Direc3d selectDirec3d(int i) {
				return new Direc3d(spaceCoord3d[i]);
			}
			public Direc4d selectDirec4d(int i) {
				return new Direc4d(spaceCoord4d[i]);
			}
			public Point3d selectCenter3d() {
				scene.camera4d.proj3d(selectCenter4d(),p3);
				return p3;
				//return new Point3d(0,0,0);			
			}
			public Point4d selectCenter4d() {
				return new Point4d(scene.compounds.getSelectedItem().center.loc()); 
				//return new Point4d(0,0,0,0);
			}			
		},
		CAMERA("camera") {
			Point3d p3 = new Point3d();
			public Direc3d selectDirec3d(int i) {
				return new Direc3d(scene.camera3d.v[i]);
			}
			public Direc4d selectDirec4d(int i) {			
				return new Direc4d(scene.camera4d.v[i]);
			}
			public Point3d selectCenter3d() {
				scene.camera4d.proj3d(selectCenter4d(),p3);
				return p3;
//				Point3d res = new Point3d(Main.opt.scene.camera3d.eye);
//				Point3d d = new Point3d(Main.opt.scene.camera3d.v[2]);
//				d.multiply(Main.opt.screenEyeDist.getDouble());
//				res.translate(d);
//				return res;				
			}
			public Point4d selectCenter4d() {
				return new Point4d(scene.compounds.getSelectedItem().center.loc()); 
//				return new Point4d(0,0,0,0);
			}
		};
//		OBJECT("object") {
//			
//		};
		String name;
		ViewAbsRel(String _name) {	name = _name;	}
		public String toString() {	return name;	}
		public abstract Direc3d selectDirec3d(int i);
		public abstract Direc4d selectDirec4d(int i);
		public abstract Point3d selectCenter3d();
		public abstract Point4d selectCenter4d();
	}
	public static final IntModel<ViewAbsRel> viewAbsRel = new IntModel<ViewAbsRel>(ViewAbsRel.SYSTEM, ViewAbsRel.values());
	public static final String[] d3axisNames = {"x","y","z"}; 
	public static final IntStringModel d3ViewTransAxis = new IntStringModel(1,d3axisNames);
	public static final IntStringModel d3ViewRotAxis = new IntStringModel(0,new String[] {"yz", "xz", "xy"});
	public static final IntStringModel compTransAxis = new IntStringModel(0,axisNames);
	public static final IntStringModel compRotAxis1 = new IntStringModel(0,axisNames);
	public static final IntStringModel compRotAxis2 = new IntStringModel(1,axisNames);
	public static final DoubleModel	brightness = 
		new DoubleModel(0.75,0,1);

	public static enum ResolutionUnit implements Unit {
		DotsPerCM("dots/cm",1.0),
		DotsPerInch("dpi",1/2.54);
		private String name;
		private double value;
		ResolutionUnit(String _name, double _value) {
			name=_name;
			value=_value;
		}
		public String toString() { return name; }
		public double unitFactor() { return value; }		
	}
	
	public static final IntModel<ResolutionUnit> 
	resolutionUnit = new IntModel<ResolutionUnit>(ResolutionUnit.DotsPerCM,ResolutionUnit.values());

	public static final DoubleModel xcm = new DoubleModel(35.96,LengthUnit.CM);
	public static final DoubleModel ycm = new DoubleModel(35.68,LengthUnit.CM);


	public static final IntModel<LengthUnit> 
	lengthUnit = new IntModel<LengthUnit>(LengthUnit.CM,LengthUnit.values());
	
	public static enum LengthUnit implements Unit {
		CM("cm",1.0),
		INCH("inch",2.54);
		private String name;
		private double value;
		LengthUnit(String _name, double _value) {
			name=_name;
			value=_value;
		}
		public String toString() { return name; }
		public double unitFactor() { return value; }
	}
	
	public static final DoubleModel 
	eyesDistHalf = new DoubleModel(3,0.2,LengthUnit.CM);
	public static final DoubleModel 
	screenEyeDist =	new DoubleModel(30,1,LengthUnit.CM);
	public static final DoubleModel 
	mouseTransSens = new DoubleModel(3,1); // cm/cm
	public static final DoubleModel 
	mouseRotSens   = new DoubleModel(Math.PI/6,Math.PI/36); // rad/cm
	public static final DoubleModel 
	barEyeFocusDelta = new	DoubleModel(0,1,LengthUnit.CM);

	public static final IntModel<Objectives> objectives = new IntModel<Objectives>(Objectives.values());
	
	public static final BoolModel drawTetrahedral = new BoolModel(true,"Tetrahedral");;
	public static final BoolModel drawTrihedral = new BoolModel(false,"Trihedral");
	
	public static final BoolModel antiAliased = new BoolModel(true,"antialiased");

	public static final BoolModel soundOn = new BoolModel(true,"sound on");
	
	public static final BoolModel showGoal = new BoolModel(false,"Goal");
	
	public static enum ViewType {
		FLAT("flat") {
			public D3Graphics getD3Graphics(D2GraphicsIF g,Camera3d c) {
				return new Flat3dGraphics(g,c);
			}
		},
		CROSSED("crossed eyes") {
			public D3Graphics getD3Graphics(D2GraphicsIF g,Camera3d c) {
				return new CrossEyedGraphics(g,c);
			}
		},
		PARALLEL("parallel eyes") {
			public D3Graphics getD3Graphics(D2GraphicsIF g,Camera3d c) {
				return new ParallelEyedGraphics(g,c);
			}
		},
		ANAGLYPH("red/cyan") {
			public D3Graphics getD3Graphics(D2GraphicsIF g,Camera3d c) {
				return new AnaglyphGraphics(g,c);
			}
		};
		private String name;
		private ViewType(String _name) { name = _name; }
		public abstract D3Graphics getD3Graphics(D2GraphicsIF g,Camera3d c);
		public String toString() { return name; }
	}
	
	public static final IntModel<ViewType> viewType = new IntModel<ViewType>(
			ViewType.ANAGLYPH,
			ViewType.values()
	);

	public static enum ShowedScreen {
		ADJUSTMENT("adjustment") {
			private AdjustmentPane panel;
			public AdjustmentPane panel() {
				if (panel==null) {
					panel = new AdjustmentPane();
				}
				return panel;
			}
		},
		KEYCONTROL("key control") {
			private KeyControlPanel panel;
			public KeyControlPanel panel() {
				if (panel==null) {
					panel = new KeyControlPanel();
				}
				return panel;
				
			}
		},
		MAIN("main") {
			private ViewPane panel;
			public ViewPane panel() {
				if (panel==null) {
					panel = new ViewPane();
					panel.addKeyListener(new KeyControl());
					panel.setFocusable(true);
				}
				return panel;
				
			}
		},
		//TODO: place doc in help menue
		DOCUMENTATION("doc") {
			private DocumentationPane panel;
			public DocumentationPane panel() {
				if (panel==null) {
					panel = new DocumentationPane();
				}
				return panel;
				
			}
		},
		ABOUT("about") {
			private AboutPane panel;
			public AboutPane panel() {
				if (panel==null) {
					panel = new AboutPane();
				}
				return panel;
				
			}
		};

		private String name;
		public abstract JPanel panel();
		ShowedScreen(String _name) {
			name = _name;
		}
		public String toString() {
			return name;
		}
	}
	public static IntModel<ShowedScreen> showedScreen;

	public static enum GameStatus {
		NONE, PENDING, REACHED, MISSED;
	}
	public static final IntModel<GameStatus> gameStatus = new IntModel<GameStatus>(GameStatus.PENDING, GameStatus.values());
	public static Scene scene;
	public static GameFlow gameFlow;
	
	public static ViewScreen viewScreen;
	
	public static JPanel contentPane;
	
	public static Frame main;
	public final static DiAxisModel viewRotXAxis12 = new DiAxisModel(0,1);
	
	private Opt() {}
	
	public static void initialize(Container window) {
		ddddbb.math.Param.perspective.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				Opt.viewRotXAxis12.setEnabled(ddddbb.math.Param.perspective.getSelectedObject().isParallelProjectionEnabled());				
			}
		});
		
		scene = new Scene(gameStatus,showGoal);
		gameFlow = new GameFlow(scene,gameStatus,objectives);
		
		showedScreen = new IntModel<ShowedScreen>(
				ShowedScreen.MAIN,ShowedScreen.values()
		);
		
		Properties props = new Properties();
		try {
			props.load(Opt.class.getResourceAsStream("application.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		applicationWidth = Integer.parseInt(props.getProperty("width"));
		applicationHeight = Integer.parseInt(props.getProperty("height"));
		
//		setSize(Main.opt.applicationWidth, Main.opt.applicationHeight);
		window.setPreferredSize(new Dimension(applicationWidth, applicationHeight));
		
		contentPane = new MyContentPane();

		GraphicsConfiguration gconf = window.getGraphicsConfiguration();
		assert gconf != null;
		GraphicsDevice gdev = gconf.getDevice();
		DisplayMode dm = gdev.getDisplayMode();
		dm.getHeight();
		dm.getWidth();
		//Toolkit.getDefaultToolkit().getScreenInsets(gc);
		Toolkit.getDefaultToolkit().getScreenSize();//pixels

		screenDefaults();

	
	}
	
	public static void screenDefaults() {
		int dotsPerInch = Toolkit.getDefaultToolkit().getScreenResolution();//dots per inch
		if (dotsPerInch == 0) { dotsPerInch = 96; }
		/*
		 4:3 y=3/5*d x=4/3*y=4/5*d
		    1600x1200 d=19" : ydpi=5/3*1200/19=xdpi=5/4*1600=105.26
			1024x768  d=19" : ydpi=5/3*768/19=xdpi=5/4*1024/19=67.4
			800x600   d=19" : ydpi=5/3*600/19=xdpi=5/4*800/19=52.63
			800x600   d=14" : ydpi=5/3*600/14=xdpi=5/4*800/14=71.43
			
			Most computer LCD screens are build such that xdpi=ydpi
			though x:y dont have to be always 4:3, for example my monitor:
			1280:1024=5:4 but also x:y=14":11.2"=5:4 dpi=1280/14=91.43
			
			The above getScreenResolution() is very unprecise (enforced by returning int!)
		*/
		
		
		xcm.setDouble(dotsPerInch,ResolutionUnit.DotsPerInch);
		ycm.setDouble(dotsPerInch,ResolutionUnit.DotsPerInch);
		eyesDistHalf.setDouble(3);
		screenEyeDist.setDouble(30);
		barEyeFocusDelta.setDouble(0);
		mouseRotSens.setDouble(Math.PI/6);
		mouseTransSens.setDouble(3);
		//System.out.println(xcm.getDouble(ResolutionUnit.DotsPerCM));
	}
}
