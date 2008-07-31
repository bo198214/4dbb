package ddddbb.game;

import ddddbb.comb.DSignedAxis;
import ddddbb.gen.BoolModel;
import ddddbb.gen.DiAxisModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.IntStringModel;
import ddddbb.math.AnaglyphGraphics;
import ddddbb.math.Camera3d;
import ddddbb.math.CrossEyedGraphics;
import ddddbb.math.D2GraphicsIF;
import ddddbb.math.D3Graphics;
import ddddbb.math.Flat3dGraphics;
import ddddbb.math.ParallelEyedGraphics;

public class SimpleSwitches {

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
	public final IntModel<Occlusion4dAllowance> occlusion4dAllowance = 
	new IntModel<Occlusion4dAllowance>(Occlusion4dAllowance.COMPLETE,Occlusion4dAllowance.values());
	public final IntModel<Orientation3d> orientation3d = 
	new IntModel<Orientation3d>(Orientation3d.LEFTHANDED,Orientation3d.values());
	public final IntModel<Orientation4d> orientation4d =
	new IntModel<Orientation4d>(Orientation4d.RIGHTHANDED,Orientation4d.values());
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
	public final IntModel<ViewType> viewType = new IntModel<ViewType>(
			ViewType.ANAGLYPH,
			ViewType.values()
	);
	//TODO: adapt zoom to screen resolution/window size
	public final DoubleModel zoom = new DoubleModel(1,0.2);
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
	

}
