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

import ddddbb.game.Level;
import ddddbb.game.ScreenValues;
import ddddbb.game.Settings;
import ddddbb.gen.AChangeListener;
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

	public ViewScreen3d(final Settings ss, final ScreenValues sv, final Scene3d scene) {
		scene.addChangeListener(this);
		sv.eyesDistHalf.addChangeListener(this);
		sv.screenEyeDist.addChangeListener(this);
		sv.xdpcm.addChangeListener(this);
		sv.ydpcm.addChangeListener(this);
		sv.barEyeFocusDelta.addChangeListener(this);
		Test3d.opt.drawTrihedral.addChangeListener(this);
		ss.showInternalFaces.addChangeListener(this);
		ss.zoom.addChangeListener(this);

		new AChangeListener() {
			public void stateChanged() {
				updateAlias();
				repaint();
			}}.addTo(ss.antiAliased);
		new AChangeListener() {
			public void stateChanged() {
				if (g3==null) { return; }
				g3.setBrightness(sv.brightness.getDouble());
				repaint();
			}}.addTo(ss.brightness);
		new AChangeListener() {
			public void stateChanged() {
				if (g3==null) { return; }
				g3 = ss.viewType.getSelectedObject().getD3Graphics(g2,scene.camera3d);
				repaint();
			}}.addTo(ss.viewType);

//		setPreferredSize(new Dimension(
//				(int)(10.0*Test3d.opt.xcm.getDouble()),
//				(int)(10.0*Test3d.opt.ycm.getDouble()))); // 10x10cm

		setFocusable(true);
		addKeyListener(new KeyControl(scene,ss.showGoal,ss.viewRotXAxis12,ss.d3ViewRotAxis,ss.dim34,viewAbsRel) );
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
		
		scene.paint(g3);

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
				g3 = ss.viewType.getSelectedObject().getD3Graphics(g2,scene.camera3d);
			}
			else {
				g2.setGraphics(g);
				g3.setGraphics(g2); 
			}
			updateAlias();
			g3.setBrightness(ss.brightness.getDouble());
		}		
	}

	protected void updateAlias() {
		if (g==null) { return; }
		if (ss.antiAliased.isSelected()) {
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
