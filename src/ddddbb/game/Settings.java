package ddddbb.game;

import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;

import ddddbb.comb.DSignedAxis;
import ddddbb.gen.BoolModel;
import ddddbb.gen.DiAxisModel;
import ddddbb.gen.DiIntModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.IntStringModel;
import ddddbb.gen.Unit;
import ddddbb.math.AnaglyphGraphics;
import ddddbb.math.Camera3d;
import ddddbb.math.Camera4d;
import ddddbb.math.Camera4dOrthographic;
import ddddbb.math.CavalierPerspective;
import ddddbb.math.CrossEyedGraphics;
import ddddbb.math.D2GraphicsIF;
import ddddbb.math.D3Graphics;
import ddddbb.math.Flat3dGraphics;
import ddddbb.math.Isometric30Perspective;
import ddddbb.math.OrthographicPerspective;
import ddddbb.math.ParallelEyedGraphics;
import ddddbb.math.PhotoPerspective;

public class Settings {

	public final static DoubleModel	brightness = 
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
	
	public final IntModel<ResolutionUnit> 
	resolutionUnit = new IntModel<ResolutionUnit>(ResolutionUnit.DotsPerCM,ResolutionUnit.values());

	public final DoubleModel xdpcm = new DoubleModel(35.96,LengthUnit.CM);
	public final DoubleModel ydpcm = new DoubleModel(35.68,LengthUnit.CM);


	public final IntModel<LengthUnit> 
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
	
	public final DoubleModel 
	eyesDistHalf = new DoubleModel(3,0.2,LengthUnit.CM);
	public final DoubleModel 
	screenEyeDist =	new DoubleModel(30,1,LengthUnit.CM);
	public final DoubleModel 
	mouseTransSens = new DoubleModel(3,1); // cm/cm
	public final DoubleModel 
	mouseRotSens   = new DoubleModel(Math.PI/6,Math.PI/36); // rad/cm
	public final DoubleModel 
	barEyeFocusDelta = new	DoubleModel(0,1,LengthUnit.CM);
	

	public static enum Axis4d {
		X(1,"x"),
		Y(2,"y"),
		Z(3,"z"),
		W(4,"w");
		String name; int axis;
		Axis4d(int axis, String name) {
			this.axis = axis; this.name = name;
		}
		public int axis() { return axis; }
		public String toString() { return name; }
	}
	public final static DiIntModel<Axis4d> mouseTransAxes4d =
		new DiIntModel<Axis4d>(Axis4d.X,Axis4d.Y,Axis4d.values());
	
	public static enum Axis3d {
		X(1,"x"),
		Y(2,"y"),
		Z(3,"z");
		String name; int axis;
		Axis3d(int axis, String name) {
			this.axis = axis; this.name = name;
		}
		public int axis() { return axis; }
		public String toString() { return name; }
	}
	public final static DiIntModel<Axis3d> mouseTransAxes3d =
		new DiIntModel<Axis3d>(Axis3d.X,Axis3d.Y,Axis3d.values());
	
	public static enum DiAxis4d {
		XY(1,2,"xy"),
		XZ(1,3,"xz"),
		XW(1,4,"xw"),
		YZ(2,3,"yz"),
		YW(2,3,"yw"),
		ZW(3,4,"zw");
		
		String name; int axis1; int axis2;
		DiAxis4d(int axis1, int axis2, String name) {
			this.axis1 = axis1; this.axis2 = axis2; this.name=name;
		}
		public String toString() { return name; }
		public int axis1() { return axis1; }
		public int axis2() { return axis2; }
	};
	public static final DiIntModel<DiAxis4d> mouseRotDiAxes4d = 
		new DiIntModel<DiAxis4d>(DiAxis4d.XZ,DiAxis4d.YW,DiAxis4d.values());
	
	public static enum DiAxis3d {
		XY(1,2,"xy"),
		XZ(1,3,"xz"),
		YZ(2,3,"yz");
		String name; int axis1; int axis2;
		DiAxis3d(int axis1, int axis2, String name) {
			this.axis1 = axis1; this.axis2 = axis2; this.name=name;
		}

		public String toString() { return name; }
		public int axis1() { return axis1; }
		public int axis2() { return axis2; }
	};
	public static final DiIntModel<DiAxis3d> mouseRotDiAxes3d =
		new DiIntModel<DiAxis3d>(DiAxis3d.XZ,DiAxis3d.YZ,DiAxis3d.values());
		
	
	public static enum Occlusion4dAllowance {
		NONE("No 4D occlusion") {},
		BACKFACE("4D Backface culling"),
		COMPLETE("4D Occlusion culling");
		String name;
		Occlusion4dAllowance(String _name) {
			name = _name;
		}
		public String toString() { return name; }
	}
	public final IntModel<Occlusion4dAllowance> occlusion4dAllowance = 
		new IntModel<Occlusion4dAllowance>(Occlusion4dAllowance.COMPLETE,Occlusion4dAllowance.values());

	public static enum Orientation3d {
		LEFTHANDED("3d left handed",+1),
		RIGHTHANDED("3d right handed",-1);
		String name;
		int value;
		Orientation3d(String _name, int _value ) {
			name = _name;
			value = _value;
		}
		public int value() {
			return value;
		}
		public String toString() { return name; }
	}
	public final IntModel<Orientation3d> orientation3d = 
		new IntModel<Orientation3d>(Orientation3d.LEFTHANDED,Orientation3d.values());

	public static enum Orientation4d {
		LEFTHANDED("4d left handed",+1),
		RIGHTHANDED("4d right handed",-1);
		String name;
		int value;
		private Orientation4d(String _name, int _value) { 
			name=_name; value =_value;
		}
		public int value() { return value; 	}
		public String toString() { return name; }
		
	}
	public final IntModel<Orientation4d> orientation4d =
		new IntModel<Orientation4d>(Orientation4d.RIGHTHANDED,Orientation4d.values());

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
	public final IntModel<ViewType> viewType = new IntModel<ViewType>(
			ViewType.ANAGLYPH,
			ViewType.values()
	);

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
	public static enum GameStatus {
		NONE, PENDING, REACHED, MISSED;
	}
	public final IntModel<GameStatus> gameStatus = new IntModel<Settings.GameStatus>(Settings.GameStatus.PENDING, Settings.GameStatus.values());

	public final BoolModel antiAliased = new BoolModel(true,"Antialiased");
	public final IntStringModel compRotAxis1 = new IntStringModel(0,Main.axisNames);
	public final IntStringModel compRotAxis2 = new IntStringModel(1,Main.axisNames);
	public final IntStringModel compTransAxis = new IntStringModel(0,Main.axisNames);
	public final String[] d3axisNames = {"x","y","z"};
	public final IntStringModel d3ViewRotAxis = new IntStringModel(0,new String[] {"yz", "xz", "xy"});
	public final IntStringModel d3ViewTransAxis = new IntStringModel(1,d3axisNames);
	public final IntStringModel dim34 = new IntStringModel(0,new String[] {"3d","4d"});
	//public final BoolModel drawTrihedral = new BoolModel(false,"Trihedral");
	public final BoolModel drawTetrahedral = new BoolModel(true,"Tetrahedral");
	public final BoolModel showGoal = new BoolModel(false,"Goal");
	public final BoolModel showInternalFaces =	new BoolModel(
	false, "Show internal faces");
	//public final Frame main;
	public final DiAxisModel viewRotXAxis12 = new DiAxisModel(0,1);
	public final IntStringModel viewTransAxis = new IntStringModel(1, Main.axisNames);
	//public IntStringModel viewRotXAxis2 = new IntStringModel(1,axisNames);
	//public DiAxisModel viewRotYAxis12 = new DiAxisModel(2,3);
	//public IntStringModel viewRotYAxis2 = new IntStringModel(3,axisNames);
	
	public final IntStringModel viewTransRot = new IntStringModel(1, new String[] { "trans", "rot"});
	public final static DoubleModel zoom = new DoubleModel(1,0.2);

	public final IntModel<DSignedAxis> perspectiveAxis = new IntModel<DSignedAxis>(
			0,
			new String[] {
					"+" + Main.axisNames[3],
					"+" + Main.axisNames[2],
					"+" + Main.axisNames[1],
					"+" + Main.axisNames[0],
					"-" + Main.axisNames[0],
					"-" + Main.axisNames[1],
					"-" + Main.axisNames[2],
					"-" + Main.axisNames[3]
			},
			new DSignedAxis[] {
					new DSignedAxis(4),
					new DSignedAxis(3),
					new DSignedAxis(2),
					new DSignedAxis(1),
					new DSignedAxis(-1),
					new DSignedAxis(-2),
					new DSignedAxis(-3),
					new DSignedAxis(-4),
			}
	);
	public final IntModel<Objectives> objectives = new IntModel<Objectives>(Objectives.values());
	public final IntModel<Camera4d> perspective = new PerspectiveEnum();
	public static final BoolModel soundOn = new BoolModel(true,"Sound on");

	public void screenDefaults(Container window) {
		GraphicsConfiguration gconf = window.getGraphicsConfiguration();
		assert gconf != null;
		GraphicsDevice gdev = gconf.getDevice();
		DisplayMode dm = gdev.getDisplayMode();
		dm.getHeight();
		dm.getWidth();
		//Toolkit.getDefaultToolkit().getScreenInsets(gc);
		Toolkit.getDefaultToolkit().getScreenSize();//pixels

		
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
		
		
		xdpcm.setDouble(dotsPerInch,Settings.ResolutionUnit.DotsPerInch);
		ydpcm.setDouble(dotsPerInch,Settings.ResolutionUnit.DotsPerInch);
		eyesDistHalf.setDouble(3);
		screenEyeDist.setDouble(30);
		barEyeFocusDelta.setDouble(0);
		mouseRotSens.setDouble(Math.PI/6);
		mouseTransSens.setDouble(3);
		//System.out.println(xcm.getDouble(ResolutionUnit.DotsPerCM));
	}

}
