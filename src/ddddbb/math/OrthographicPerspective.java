/**
 * 
 */
package ddddbb.math;

public class OrthographicPerspective extends Camera4dOrthographic {
	public OrthographicPerspective() {
		super(defaultInitialEye(),defaultInitialV());
	}

	public String toString() { return "Front (orthographic)"; }
}