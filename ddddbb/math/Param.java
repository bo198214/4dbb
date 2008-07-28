package ddddbb.math;

import ddddbb.comb.DSignedAxis;
import ddddbb.game.Opt;
import ddddbb.gen.BoolModel;
import ddddbb.gen.IntModel;

public class Param {
	public final static BoolModel debug = new BoolModel(
			false, "debug");
	
	public enum Occlusion4dAllowance {
		NONE("No 4D occlusion") {},
		BACKFACE("4D Backface culling"),
		COMPLETE("4D Occlusion culling");
		String name;
		Occlusion4dAllowance(String _name) {
			name = _name;
		}
		public String toString() { return name; }
	}
	public final static IntModel<Occlusion4dAllowance> occlusion4dAllowance = 
		new IntModel<Occlusion4dAllowance>(Occlusion4dAllowance.COMPLETE,Occlusion4dAllowance.values());

	public final static BoolModel showInternalFaces =	new BoolModel(
			false, "Show internal faces");
	
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
		public final Camera4d DIMETRIC;
		
		public Camera4d[] perspectives;
		
		public PerspectiveEnum() {
			LINEAR = new PhotoPerspective();
			ISOMETRIC30 = new Isometric30Perspective();
			CAVALIER = new CavalierPerspective();
			ORTHOGRAPHIC = new OrthographicPerspective();
			DIMETRIC = new Camera4dOrthographic.Dimetric();
			perspectives = new Camera4d[] {
					LINEAR,
					ISOMETRIC30,
					CAVALIER,
					ORTHOGRAPHIC,
					DIMETRIC
			};
		}
	}
	
	public final static PerspectiveEnum perspectiveEnum = new PerspectiveEnum();
	public final static IntModel<Camera4d> perspective = new IntModel<Camera4d>(perspectiveEnum.CAVALIER,perspectiveEnum.perspectives);
}
