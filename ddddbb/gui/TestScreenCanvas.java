package ddddbb.gui;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ddddbb.game.ScreenValues;
import ddddbb.game.SimpleSwitches;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.MyChangeListener;
import ddddbb.math.Camera3d;
import ddddbb.math.D2Graphics;
import ddddbb.math.D3Graphics;
import ddddbb.math.Point3d;

public class TestScreenCanvas extends JPanel implements MyChangeListener {
	private static final long serialVersionUID = -6777833202679708271L;

	int width;
	int height;
	private Graphics2D g = null;
	private BufferedImage buffImg;
	private D2Graphics g2;
	private D3Graphics g3;
	private Composite ocomposite;
	

	private final DoubleModel xdpcm;
	private final DoubleModel ydpcm;
	private final IntModel<SimpleSwitches.ViewType> viewType;
	private final DoubleModel brightness;
	private final DoubleModel eyesDistHalf;
	private final DoubleModel screenEyeDist;
	private final DoubleModel barEyeFocusDelta;
	
	public TestScreenCanvas(
			final DoubleModel _xdpcm,
			final DoubleModel _ydpcm,
			final DoubleModel _brightness,
			final IntModel<SimpleSwitches.ViewType> _viewType,
			final DoubleModel _eyesDistHalf,
			final DoubleModel _screenEyeDist,
			final DoubleModel _barEyeFocusDelta
	) {
		super();
		xdpcm = _xdpcm;
		ydpcm = _ydpcm;
		viewType = _viewType;
		brightness = _brightness;
		eyesDistHalf = _eyesDistHalf;
		screenEyeDist = _screenEyeDist;
		barEyeFocusDelta = _barEyeFocusDelta;
		setPreferredSize();
		eyesDistHalf.addChangeListener(this);
		screenEyeDist.addChangeListener(this);
		xdpcm.addChangeListener(this);
		ydpcm.addChangeListener(this);
		brightness.addChangeListener(this);
		barEyeFocusDelta.addChangeListener(this);
		viewType.addChangeListener(this);
	}
	
	public void setPreferredSize() {
		setPreferredSize(new Dimension(
				10+(int)(xdpcm.getDouble(ScreenValues.ResolutionUnit.DotsPerInch)),
				10+(int)(ydpcm.getDouble(ScreenValues.ResolutionUnit.DotsPerInch))
		));
	}
	
	private void initializeGraphics() {
		Dimension size = getSize();  
		width = size.width;
		height = size.height;
		buffImg  = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
	}
	
	private void initGCs() {
		g = buffImg.createGraphics();
		g.setBackground(Color.BLACK);
		g.setTransform(new AffineTransform());
		g.translate(width/2,height/2);
		ocomposite = g.getComposite();
		g2 = new D2Graphics(g,xdpcm.getDouble(),ydpcm.getDouble());
		g3 = viewType.getSelectedObject().getD3Graphics(g2, new Camera3d(screenEyeDist,eyesDistHalf,barEyeFocusDelta));
	}
	
	private void drawTestCube(double x0,double y0) {
		double f = 1;
		g3.drawLine(new Point3d(x0-0.5*f,y0-0.5*f,f),new Point3d(x0+0.5*f,y0-0.5*f,f));
		g3.drawLine(new Point3d(x0-0.5*f,y0-0.5*f,f),new Point3d(x0-0.5*f,y0+0.5*f,f));
		g3.drawLine(new Point3d(x0+0.5*f,y0+0.5*f,f),new Point3d(x0-0.5*f,y0+0.5*f,f));
		g3.drawLine(new Point3d(x0+0.5*f,y0+0.5*f,f),new Point3d(x0+0.5*f,y0-0.5*f,f));
		g3.drawString("Back",new Point3d(x0+0.5*f,y0+0.5*f,f));
		
		g3.drawLine(new Point3d(x0-0.5*f,y0-0.5*f,0),new Point3d(x0+0.5*f,y0-0.5*f,0));
		g3.drawLine(new Point3d(x0-0.5*f,y0-0.5*f,0),new Point3d(x0-0.5*f,y0+0.5*f,0));
		g3.drawLine(new Point3d(x0+0.5*f,y0+0.5*f,0),new Point3d(x0-0.5*f,y0+0.5*f,0));
		g3.drawLine(new Point3d(x0+0.5*f,y0+0.5*f,0),new Point3d(x0+0.5*f,y0-0.5*f,0));		
		g3.drawString("Middle",new Point3d(x0+0.5*f,y0,0));

		g3.drawLine(new Point3d(x0-0.5*f,y0-0.5*f,-f),new Point3d(x0+0.5*f,y0-0.5*f,-f));
		g3.drawLine(new Point3d(x0-0.5*f,y0-0.5*f,-f),new Point3d(x0-0.5*f,y0+0.5*f,-f));
		g3.drawLine(new Point3d(x0+0.5*f,y0+0.5*f,-f),new Point3d(x0-0.5*f,y0+0.5*f,-f));
		g3.drawLine(new Point3d(x0+0.5*f,y0+0.5*f,-f),new Point3d(x0+0.5*f,y0-0.5*f,-f));
		g3.drawString("Front",new Point3d(x0+0.5*f,y0-0.5*f,-f));
		
//		g3.drawLine(new Point3d(x0-1,y0,0), new Point3d(x0-1,y0,1));
//		g3.drawLine(new Point3d(x0-1,y0,-1),new Point3d(x0-1,y0,0));
//		g3.drawBlob(new double[] {x0-1,y0,-1});
//		g3.drawBlob(new double[] {x0-1,y0, 0});
//		g3.drawBlob(new double[] {x0-1,y0,+1});
		
//		g3.drawLine(new Point3d(x0-0.5*f,y0-0.5*f,0),new Point3d(x0+0.5*f,y0-0.5*f,0));
//		g3.drawLine(new Point3d(x0-0.5*f,y0-0.5*f,0),new Point3d(x0-0.5*f,y0+0.5*f,0));
//		g3.drawLine(new Point3d(x0+0.5*f,y0+0.5*f,0),new Point3d(x0-0.5*f,y0+0.5*f,0));
//		g3.drawLine(new Point3d(x0+0.5*f,y0+0.5*f,0),new Point3d(x0+0.5*f,y0-0.5*f,0));
		g3.drawTrihedral(new Point3d(x0-1,y0-1,0),2.54);
	}

	private void test3D(double y0) {
		g3.setBrightness(brightness.getDouble());
		drawTestCube(0, y0);
	}
	
	private void test2D(double y0) {
		g2.setColor(Color.WHITE);
		g2.drawRect(-2.54/2,y0-2.54/2,2.54,2.54);
		g2.drawString("1\"",-0.1,y0-2.54/2+0.1);
		g2.drawRect(-1.0/2,y0-1.0/2,1.0,1.0);
		g2.drawString("1cm",-1.0/4-0.1,y0-1.0/2+0.1);
	}
	
	public void paint(Graphics g0) {
		if (buffImg==null) initializeGraphics();
		initGCs();
		g.clearRect(-width/2,-height/2,width,height);
		
		test3D(-3);
		g.setComposite(ocomposite);
		test2D(3);

		((Graphics2D)g0).drawImage(buffImg, null, 0, 0);
	}
	
	public void stateChanged() {
		repaint();
	}
}
