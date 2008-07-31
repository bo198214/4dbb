package ddddbb.game;

import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;

import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.Unit;

public class ScreenValues {

	public final DoubleModel	brightness = 
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
	
	private final Container window;
	public ScreenValues(Container _window) {
		window = _window;
		screenDefaults();
	}
	public void screenDefaults() {
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
		
		
		xdpcm.setDouble(dotsPerInch,ResolutionUnit.DotsPerInch);
		ydpcm.setDouble(dotsPerInch,ResolutionUnit.DotsPerInch);
		eyesDistHalf.setDouble(3);
		screenEyeDist.setDouble(30);
		barEyeFocusDelta.setDouble(0);
		mouseRotSens.setDouble(Math.PI/6);
		mouseTransSens.setDouble(3);
		//System.out.println(xcm.getDouble(ResolutionUnit.DotsPerCM));
	}

}
