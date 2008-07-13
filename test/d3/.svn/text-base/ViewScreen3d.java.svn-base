package test.d3;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ddddbb.gen.MyChangeListener;
import ddddbb.gui.KeyControl;
import ddddbb.math.D2Graphics;
import ddddbb.math.D3Graphics;

public class ViewScreen3d extends JPanel implements MyChangeListener {
	private static final long serialVersionUID = 8469923076897478649L;
	protected int x0,y0,width=0,height=0;
	protected BufferedImage buffImg;
	protected Color bColor = Color.BLACK;
	protected Composite composite;
	Graphics2D g;
	D2Graphics g2;
	D3Graphics g3;

	public ViewScreen3d() {
		super();
		initialize();
	}

	private void initialize() {
		Test3d.opt.scene.addChangeListener(this);
		Opt.eyesDistHalf.addChangeListener(this);
		Opt.screenEyeDist.addChangeListener(this);
		Opt.xcm.addChangeListener(this);
		Opt.ycm.addChangeListener(this);
		Opt.barEyeFocusDelta.addChangeListener(this);
		Test3d.opt.drawTrihedral.addChangeListener(this);
		Test3d.opt.showCompoundGrid.addChangeListener(this);
		Test3d.opt.zoom.addChangeListener(this);

		Test3d.opt.antiAliased.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				updateAlias();
				repaint();
			}});
		Test3d.opt.brightness.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (g3==null) { return; }
				g3.setBrightness(Test3d.opt.brightness.getDouble());
				repaint();
			}});
		Test3d.opt.viewType.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				if (g3==null) { return; }
				g3 = Test3d.opt.viewType.getSelectedObject().getD3Graphics(g2,Test3d.opt.scene.camera3d);
				repaint();
			}});

//		setPreferredSize(new Dimension(
//				(int)(10.0*Test3d.opt.xcm.getDouble()),
//				(int)(10.0*Test3d.opt.ycm.getDouble()))); // 10x10cm

		setFocusable(true);
		addKeyListener(new KeyControl() );
		MouseControl mouseControl = new MouseControl();
		addMouseMotionListener(mouseControl);
		addMouseListener(mouseControl);
		addHierarchyBoundsListener(new HierarchyBoundsListener() {
			public void ancestorMoved(HierarchyEvent e) {
			}

			public void ancestorResized(HierarchyEvent e) {
				updateGraphics();
			}
			
		});
	}
	
	/** double buffered paint */
	public void paint(Graphics g0) {
		if ( g == null ) { updateGraphics(); }
		g.clearRect(-width/2,-height/2,width,height);
		
		if (Test3d.opt.drawTrihedral.isSelected()) {
			g3.drawTrihedral();
		}
		
		Test3d.opt.scene.paint(g3);

		((Graphics2D)g0).drawImage(buffImg, null, 0, 0);
	}
	
	public synchronized void updateGraphics() {
		Dimension size = getSize();  
		width = size.width;
		height = size.height;
		if ( width > 0 & height > 0 ) {
			x0 = width/2;
			y0 = height/2;
//			buffImg = (BufferedImage)createImage(width,height);
			buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			g = buffImg.createGraphics();
			g.setBackground(bColor);
			g.setTransform(new AffineTransform());
			g.translate(x0,y0);
			if ( g2 == null ) {
				g2 = new D2Graphics(g,test.d3.Opt.xcm.getDouble(),test.d3.Opt.ycm.getDouble());
				g3 = Test3d.opt.viewType.getSelectedObject().getD3Graphics(g2,Test3d.opt.scene.camera3d);
			}
			else {
				g2.setGraphics(g);
				g3.setGraphics(g2); 
			}
			updateAlias();
			g3.setBrightness(Test3d.opt.brightness.getDouble());
		}		
	}

	protected void updateAlias() {
		if (g==null) { return; }
		if (Test3d.opt.antiAliased.isSelected()) {
			g.setRenderingHint(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON
			);
		}
		else {
			g.setRenderingHint(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_OFF
			);
		}
	}

	public void stateChanged() {
		repaint();
	}


}
