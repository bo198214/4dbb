package ddddbb.math;

import ddddbb.comb.DSignedAxis;
import ddddbb.game.Opt;
import ddddbb.gen.BoolModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.MyChangeListener;

public class Param {
	public final static BoolModel debug = new BoolModel(
			false, "debug");
	public final static BoolModel opaque4d =	new BoolModel(
			true, "opaque 4d");
	
	public final static BoolModel occlusionCulling = new BoolModel(
			true,"4d occlusion culling"); //TODO default should maybe false

	public final static BoolModel parallelProjection = new BoolModel(
			false,"orthogonal projection");

	public final static BoolModel showCompoundGrid =	new BoolModel(
			false, "Compound Grid");
	
	public final static IntModel<DSignedAxis> perspectiveAxis = new IntModel<DSignedAxis>(
			0,
			new String[] {
					"+" + Opt.axisNames[3],
					"+" + Opt.axisNames[2],
					"+" + Opt.axisNames[1],
					"+" + Opt.axisNames[0],
					"-" + Opt.axisNames[0],
					"-" + Opt.axisNames[1],
					"-" + Opt.axisNames[2],
					"-" + Opt.axisNames[3]
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
	
	public static class PerspectiveEnum {
		public final Camera4d LINEAR;
		public final Camera4d ISOMETRIC30;
		public final Camera4d CAVALIER;
		public final Camera4d ORTHOGRAPHIC;
		
		public Camera4d[] perspectives;
		
		public PerspectiveEnum() {
			LINEAR = new LinearPerspective();
			ISOMETRIC30 = new Isometric30Perspective();
			CAVALIER = new CavalierPerspective();
			ORTHOGRAPHIC = new OrthographicPerspective();
			perspectives = new Camera4d[] {
					LINEAR,
					ISOMETRIC30,
					CAVALIER,
					ORTHOGRAPHIC,
			};
		}
	}
	public final static PerspectiveEnum perspectiveEnum = new PerspectiveEnum();
	public final static IntModel<Camera4d> perspective = new IntModel<Camera4d>(perspectiveEnum.CAVALIER,perspectiveEnum.perspectives);

	static {
		perspective.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				parallelProjection.setEnabled(perspective.getSelectedObject().isParallelProjectionEnabled());
			}});
	}

	public  final static double ERR = 0.00001;
}
