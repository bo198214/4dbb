package ddddbb.math;

import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;



public class AnaglyphComposite implements Composite {

	public CompositeContext createContext(ColorModel srcColorModel,
			ColorModel dstColorModel, RenderingHints hints) {
		return new AnaglyphCompositeContext(srcColorModel,dstColorModel);
	}

}
