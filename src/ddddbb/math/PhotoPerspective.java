/**
 * 
 */
package ddddbb.math;

public class PhotoPerspective extends Camera4dCentral {
		//for the left-handed case
		public PhotoPerspective() {
//			super(new Point4d(3,3,2,10),defaultInitialV(),9);
			super(new Point4d(3,3,2,-10),defaultInitialV(),9);
			//super(new Point4d(3,3,2,0),defaultInitialV(),1);
		}
		
		public String toString() { return "Photo/Perspective"; }
		
	
	}