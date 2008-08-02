package ddddbb.gui;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ddddbb.game.Opt;
import ddddbb.math.Camera3d;
import ddddbb.math.D2Graphics;
import ddddbb.math.D3Graphics;
import ddddbb.math.Point3d;

public class TestScreenCanvas extends JPanel implements ChangeListener {
	private static final long serialVersionUID = -6777833202679708271L;

	int width;
	int height;
	private Graphics2D g = null;
	private BufferedImage buffImg;
	private D2Graphics g2;
	private D3Graphics g3;
	private Composite ocomposite;
	
	
	public void setPreferredSize() {
		setPreferredSize(new Dimension(
				10+(int)(Opt.xcm.getDouble(Opt.ResolutionUnit.DotsPerInch)),
				10+(int)(Opt.ycm.getDouble(Opt.ResolutionUnit.DotsPerInch))
		));
	}
	
	public TestScreenCanvas() {
		super();
		setPreferredSize();
		Opt.eyesDistHalf.addChangeListener(this);
		Opt.screenEyeDist.addChangeListener(this);
		Opt.xcm.addChangeListener(this);
		Opt.ycm.addChangeListener(this);
		Opt.brightness.addChangeListener(this);
		Opt.barEyeFocusDelta.addChangeListener(this);
		Opt.viewType.addChangeListener(this);
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
		g2 = new D2Graphics(g,Opt.xcm.getDouble(),Opt.ycm.getDouble());
		g3 = Opt.viewType.getSelectedObject().getD3Graphics(g2, new Camera3d());
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
		g3.setBrightness(Opt.brightness.getDouble());
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
	
	public void stateChanged(ChangeEvent e) {
		repaint();
	}
}
