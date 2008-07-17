package test.d3;

import java.util.Vector;

import ddddbb.comb.Cell;
import ddddbb.comb.CellComplex;
import ddddbb.comb.OCell;
import ddddbb.gen.BoolModel;
import ddddbb.gen.DiAxisModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.IntStringModel;
import ddddbb.gen.Unit;
import ddddbb.math.AnaglyphGraphics;
import ddddbb.math.Camera3d;
import ddddbb.math.CrossEyedGraphics;
import ddddbb.math.D2GraphicsIF;
import ddddbb.math.D3Graphics;
import ddddbb.math.Flat3dGraphics;
import ddddbb.math.OHalfSpace;
import ddddbb.math.ParallelEyedGraphics;
import ddddbb.math.Point3d;
import ddddbb.math.Space2d;

public final class Opt {
	public  final double ERR = 0.00001;

	protected final static Point3d spaceCoord3d[] = {
			new Point3d(1,0,0), new Point3d(0,1,0), new Point3d(0,0,1) };
	
	
	int applicationHeight = 600;
	int applicationWidth = 800;
	
	public DoubleModel zoom = new DoubleModel(1,0.2);
	
	public  IntStringModel dim34 = new IntStringModel(0,new String[] {"D3","D4"});
	private  String[] axisNames = { "v1", "v2", "v3", "v4" };
	public  IntStringModel viewTransAxis = new IntStringModel(1, axisNames);
	
	public DiAxisModel viewRotXAxis12 = new DiAxisModel(0,1);
	//public IntStringModel viewRotXAxis2 = new IntStringModel(1,axisNames);
	//public DiAxisModel viewRotYAxis12 = new DiAxisModel(2,3);
	//public IntStringModel viewRotYAxis2 = new IntStringModel(3,axisNames);
	
	public IntStringModel viewTransRot = new IntStringModel(1, new String[] { "trans", "rot"});
	public enum ViewAbsRel {
		SYSTEM("space") {
			public Point3d selectDirec3d(int i) {
				return spaceCoord3d[i].clone();
			}
			public Point3d selectCenter3d() {
//				Point3d p3 = new Point3d();
//				Test3d.opt.scene.camera4d.proj3d(selectCenter4d(),p3);
//				return p3;
				return new Point3d(0,0,0);			
			}
		},
		CAMERA("camera") {
			public Point3d selectDirec3d(int i) {
				return Test3d.opt.scene.camera3d.v[i].clone();
			}
			public Point3d selectCenter3d() {
				return new Point3d(0,0,0);
//				Point3d res = new Point3d(Test3d.opt.scene.camera3d.eye);
//				Point3d d = new Point3d(Test3d.opt.scene.camera3d.v[2]);
//				d.multiply(Test3d.opt.screenEyeDist.getDouble());
//				res.translate(d);
//				return res;				
			}
		};
//		OBJECT("object") {
//			
//		};
		String name;
		ViewAbsRel(String _name) {	name = _name;	}
		public String toString() {	return name;	}
		public abstract Point3d selectDirec3d(int i);
		public abstract Point3d selectCenter3d();
	}
	public IntModel<ViewAbsRel> viewAbsRel = new IntModel<ViewAbsRel>(ViewAbsRel.CAMERA, ViewAbsRel.values());
	public String[] d3axisNames = {"v1","v2","v3"}; 
	public IntStringModel d3ViewTransAxis = new IntStringModel(1,d3axisNames);
	public IntStringModel d3ViewRotAxis = new IntStringModel(0,d3axisNames);
	public IntStringModel compTransAxis = new IntStringModel(0,axisNames);
	public IntStringModel compRotAxis1 = new IntStringModel(0,axisNames);
	public IntStringModel compRotAxis2 = new IntStringModel(1,axisNames);
	public DoubleModel brightness = new DoubleModel(0.75,0,1);

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

	public static DoubleModel xcm = new DoubleModel(35.96,LengthUnit.CM);
	public static DoubleModel ycm = new DoubleModel(35.68,LengthUnit.CM);


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
	eyesDistHalf = new DoubleModel(3,0.2); //CM
	public static final DoubleModel 
	screenEyeDist =	new DoubleModel(30,LengthUnit.CM);
	public static final DoubleModel 
	mouseTransSens = new DoubleModel(3,1); // cm/cm
	public static final DoubleModel 
	mouseRotSens   = new DoubleModel(Math.PI/6,Math.PI/36); // rad/cm
	public static final DoubleModel 
	barEyeFocusDelta = new	DoubleModel(0,LengthUnit.CM);

	
	public BoolModel drawTrihedral = new BoolModel(false,"Draw Trihedral");
	
	public BoolModel showCompoundGrid =
		new BoolModel(false, "Compound Grid");
		
	public BoolModel rendering = new BoolModel(false,"render");
	public BoolModel antiAliased = new BoolModel(true,"antialiased");
	
	public BoolModel parallelProjection = new BoolModel(false,"orthogonal projection");
		
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
	
	public IntModel<ViewType> viewType = new IntModel<ViewType>(
			ViewType.ANAGLYPH,
			ViewType.values()
	);


	public Scene3d scene;
		
	public Opt() {
		//cannot do the initialization of dependent
		//attributes here because when constructing
		//Test3d.opt doesnt yet exists
	}
	
	public void initialize() {
		xcm = new DoubleModel(35.96,ResolutionUnit.DotsPerCM);
		ycm = new DoubleModel(35.68,ResolutionUnit.DotsPerCM);
		
//		Cell square = new Cell(new double[][] {
//			new double[] {0,0,1, 1,0,1},
//			new double[] {1,0,1, 1,1,1},
//			new double[] {1,1,1, 0,1,1},
//			new double[] {0,1,1, 0,0,1}
//		});
		
		double[][][] _tet1 = new double[][][] {
			new double[][] { // xy
					new double[] {0,0,0, 1,0,0},
					new double[] {1,0,0, 0,1,0},
					new double[] {0,1,0, 0,0,0},
			},
			new double[][] { //xz
					new double[] {0,0,0, 1,0,0},
					new double[] {1,0,0, 0,0,1},
					new double[] {0,0,1, 0,0,0}
			},
			new double[][] { //yz
					new double[] {0,0,0, 0,1,0},
					new double[] {0,1,0, 0,0,1},
					new double[] {0,0,1, 0,0,0}
			},
			new double[][] {
					new double[] {1,0,0, 0,1,0},
					new double[] {0,1,0, 0,0,1},
					new double[] {0,0,1, 1,0,0}
			}
		};
		double[][][] _cube1 = new double[][][] {
				new double[][] {
						new double[] {0,0,0, 1,0,0},
						new double[] {1,0,0, 1,1,0},
						new double[] {1,1,0, 0,1,0},
						new double[] {0,1,0, 0,0,0},
				},
				new double[][] {
						new double[] {0,0,1, 1,0,1},
						new double[] {1,0,1, 1,1,1},
						new double[] {1,1,1, 0,1,1},
						new double[] {0,1,1, 0,0,1},
				},
				new double[][] {
						new double[] {0,0,0, 1,0,0},
						new double[] {1,0,0, 1,0,1},
						new double[] {1,0,1, 0,0,1},
						new double[] {0,0,1, 0,0,0},
				},                                            
				new double[][] {                
						new double[] {0,1,0, 1,1,0},
						new double[] {1,1,0, 1,1,1},
						new double[] {1,1,1, 0,1,1},
						new double[] {0,1,1, 0,1,0},
				},
				new double[][] {
						new double[] {0,0,0, 0,1,0},
						new double[] {0,1,0, 0,1,1},
						new double[] {0,1,1, 0,0,1},
						new double[] {0,0,1, 0,0,0},
				},                                            
				new double[][] {                
						new double[] {1,0,0, 1,1,0},
						new double[] {1,1,0, 1,1,1},
						new double[] {1,1,1, 1,0,1},
						new double[] {1,0,1, 1,0,0},
				},
		};

		double[][][] _cube2 = new double[][][] {
				new double[][] {
						new double[] {1,0,0, 2,0,0},
						new double[] {2,0,0, 2,1,0},
						new double[] {2,1,0, 1,1,0},
						new double[] {1,1,0, 1,0,0},
				},
				new double[][] {
						new double[] {1,0,1, 2,0,1},
						new double[] {2,0,1, 2,1,1},
						new double[] {2,1,1, 1,1,1},
						new double[] {1,1,1, 1,0,1},
				},
				new double[][] {
						new double[] {1,0,0, 2,0,0},
						new double[] {2,0,0, 2,0,1},
						new double[] {2,0,1, 1,0,1},
						new double[] {1,0,1, 1,0,0},
				},                                            
				new double[][] {                
						new double[] {1,1,0, 2,1,0},
						new double[] {2,1,0, 2,1,1},
						new double[] {2,1,1, 1,1,1},
						new double[] {1,1,1, 1,1,0},
				},
				new double[][] {
						new double[] {1,0,0, 1,1,0},
						new double[] {1,1,0, 1,1,1},
						new double[] {1,1,1, 1,0,1},
						new double[] {1,0,1, 1,0,0},
				},                                            
				new double[][] {                
						new double[] {2,0,0, 2,1,0},
						new double[] {2,1,0, 2,1,1},
						new double[] {2,1,1, 2,0,1},
						new double[] {2,0,1, 2,0,0},
				},
		};
		
//		Facet1d diag = new Facet1d().init(new Point3d(0,0,1), new Point3d(1,1,1));
		Space2d xyDiag = new Space2d(new Point3d(-1,1,0),0);
		Space2d zPlane = new Space2d(new Point3d(0,0,1),0.5);
		Space2d yPlane = new Space2d(new Point3d(0,1,0),0.5);
//		Space2d xPlane = new Space2d(new Point3d(1,0,0),0.5);
		
//		Space2d s = new Space2d(new Direc(0.5939074310171529, -0.8035411409028785, 0.03994494034425462),1.7592413464922083);
//		Cell c = new Cell(new double[][] {
//				new double[] {-0.6333160367908606,-1.902347119019372,0.5199549470672978,-0.7681162264742307,-1.996139926012234,1.286186244542663,},
//				new double[] {-1.336133961483991,-1.99425562296554,1.3915454357945687,-0.7681162264742307,-1.996139926012234,1.286186244542663,},
//				new double[] {-1.2242480356830696,-1.8974620297672329,0.6065601350815312,-1.336133961483991,-1.99425562296554,1.3915454357945687,},
//				new double[] {-0.6333160367908606,-1.902347119019372,0.5199549470672978,-1.2242480356830696,-1.8974620297672329,0.6065601350815312,}
//		});		
//		c.checkFaces();
//		c.split(s);
//		c = new Cell(new double[][] {
//			new double[] { 0,0,0, 0,0,1 },
//			new double[] { 0,0,1, 0,1,1 },
//			new double[] { 0,1,1, 0,1,0 },
//			new double[] { 0,1,0, 0,0,0 }
//		});
//		s = new Space2d(new Direc(0,0,1),0);
//		c.split(s);
//		c = new Cell(new double[][] {
//				new double[]{-2.0,-2.0,0.0,-2.25,-2.25,-0.25,},
//				new double[]{-2.0,-2.0,0.0,-2.0,-2.0,1.0,},
//				new double[]{-2.0,-2.0,1.0,-2.25,-2.25,0.75,},
//				new double[]{-2.25,-2.25,-0.25,-2.25,-2.25,0.75,}
//		});
//		s = new Space2d(new Direc(0.7071067811865476, 0.0, -0.7071067811865476),-1.4142135623730951);
//		c.split(s);
//		Cell c = new Cell(_cube1);
//		c.split(xPlane);
//		c = new Cell(_cube1);
//		c.split(xPlane); c.inner().checkFaces(); c.outer().checkFaces();
//		c.inner().split(yPlane);
//		c.outer().split(yPlane);
//		c.inner().inner().split(zPlane);
//		c.inner().outer().split(zPlane);
//		c.outer().inner().split(zPlane);
//		c.outer().outer().split(zPlane);
//		
		Cell c = new Cell(_cube1);
		c.checkPointRefs();
		
//		c.split(yPlane);
//		c.inner.split(zPlane);
//		c.inner.adjustLocalSnap();
//		c.inner.inner.snapOut();

		//scene.top3d.add(new Cell(_cube2));
		
		//zPlane.flip();
		//scene.top3d = Cell.cutOut(scene.top3d,new Space2d[] {xyDiag,xPlane,zPlane});
		//scene.top3d = Cell.cutOut(scene.top3d,new Space2d[] {xPlane,yPlane,zPlane } );

		double[][][] _cube3 = new double[_cube1.length][][];
		for (int i=0;i<_cube1.length;i++) {
			_cube3[i] = new double[_cube1[i].length][];
			for (int j=0;j<_cube1[i].length;j++) {
				_cube3[i][j] = new double[_cube1[i][j].length];
				for (int k=0;k<_cube1[i][j].length;k++) {
					_cube3[i][j][k] = _cube1[i][j][k] + 0.5;
				}
			}
		}
		double[][][] _tet3 = new double[_tet1.length][][];
		for (int i=0;i<_tet1.length;i++) {
			_tet3[i] = new double[_tet1[i].length][];
			for (int j=0;j<_tet1[i].length;j++) {
				_tet3[i][j] = new double[_tet1[i][j].length];
				for (int k=0;k<_tet1[i][j].length;k++) {
					_tet3[i][j][k] = _tet1[i][j][k] + 0.5;
				}
			}
		}
		
		Vector<Space2d> planes = new Vector<Space2d>();
		planes.add(xyDiag);
		planes.add(zPlane);
		planes.add(yPlane);
		CellComplex cubes = new CellComplex();
		Cell cube2 = new Cell(_cube2);
		cube2.computeSpacesIN();
		Cell cube1 = new Cell(_cube1);
		cube1.computeSpacesIN();
		//cubes.cells.add(cube2);
		cubes.cells.add(cube1);
		
		Cell cube3 = new Cell(_cube3);
		
		cube3.computeSpacesIN();
		Cell tet3 = new Cell(_tet3);
		tet3.computeSpacesIN();
		scene = new Scene3d();
//		cubes.cutOut(tet3);
//		cubes.cells.add(tet3);
//		System.out.println(cubes.cells.size());
//		
//		for (Cell i:cubes.cells) {
//			scene.top3d.add(i); 
//		}
		//scene.top3d.add(tet3);
		//scene.top3d.add(cube3);

		cube1.split(new OHalfSpace(((OCell)tet3.facets().get(3)).cell().halfSpace(),1), null);
		cube1.outer().snapOut();
//		cube1.inner().split(new OHalfSpace(((OCell)tet3.facets().get(2)).cell().halfSpace(),-1), null);
//		cube1.inner().outer().snapOut();
//		cube1.inner().inner().split(new OHalfSpace(((OCell)tet3.facets().get(1)).cell().halfSpace(),-1), null);
//		cube1.inner().inner().outer().snapOut();
//		scene.top3d.add(cube1.inner().inner().inner());
		scene.top3d.add(cube1.inner());
		scene.top3d.add(tet3);
//		scene.top3d.add(cube1.outer());
	}
}
