/**
 * 
 */
package ddddbb.math;

public class CavalierPerspective extends Camera4dParallel {
	public String toString() { return "Cavalier (parallel 2:1)"; }

	public CavalierPerspective() {
		super(defaultInitialEye(), defaultInitialV(), new Point4d(-1,-1,-1,Math.sqrt(3.0)*2));
		changed();
	}
}