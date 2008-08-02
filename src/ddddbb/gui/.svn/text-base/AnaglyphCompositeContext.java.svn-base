package ddddbb.gui;

import java.awt.CompositeContext;
import java.awt.Rectangle;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.Color;

public class AnaglyphCompositeContext implements CompositeContext {
	
	private ColorModel srcColorModel;
	private ColorModel dstColorModel;
	
	/** background color */
	public static final Color BCOLOR = new Color(0,0,0,255);
	/** color for left eye */
	public static final Color LCOLOR = new Color(255,0,0,255);
	/** color for right eye */
	public static final Color RCOLOR = new Color(0,255,255,255);
	
	public AnaglyphCompositeContext(ColorModel srcCM,ColorModel dstCM) {
		srcColorModel = srcCM;
		dstColorModel = dstCM;
	}

	public void dispose() {
		//TODO dont know what to do with it
	}
	
	public int compColor(int cs,int cd) {
		int rd = (cd >> 16) & 255;
		int gd = (cd >> 8) & 255;
		int bd = cd & 255;
		int rs = (cs >> 16) & 255;
		int gs = (cs >> 8) & 255;
		int bs = cs & 255;
		if (rs==0) {
			return (255 << 24) | (rd << 16) | (gs << 8) | bs;
		}
		return (255 << 24) | (rs << 16) | (gd << 8) | bd;
	}

//	private String cv(int c) {
//		return "r:"+ ((c >> 16) & 255) + " g:" + ((c >> 8) & 255) + " b:"+ (c & 255) + " a: "+ ((c>>24)& 255);		
//	}
	public void compose(Raster src, Raster dstIn, WritableRaster dstOut) {
		Rectangle s = src.getBounds();
		Rectangle d = dstIn.getBounds();
		Rectangle r = s.intersection(d);
		for (int x=r.x;x<r.x+r.width;x++) {
			for (int y=r.y;y<r.y+r.height;y++) {
				
				Object sp = src.getDataElements(x,y,null);
				Object dp = dstIn.getDataElements(x,y,null);

				int sc = srcColorModel.getRGB(sp) ;
				int dc = dstColorModel.getRGB(dp);
				
//				if ( (sc != bcolor && sc != rcolor && sc != lcolor )) {
//					System.out.println("src "+cv(sc));
//				}
	
				int color = compColor(sc,dc);

				dstOut.setDataElements(x,y,dstColorModel.getDataElements(color,null));
			}
			
		}
		
	}

}
