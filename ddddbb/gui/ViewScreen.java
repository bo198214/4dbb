package ddddbb.gui;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ddddbb.game.Scene;
import ddddbb.game.Scene4d;
import ddddbb.game.ScreenValues;
import ddddbb.game.SimpleSwitches;
import ddddbb.game.Main.ViewAbsRelEnum;
import ddddbb.gen.BoolModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.IntStringModel;
import ddddbb.gen.MyChangeListener;
import ddddbb.math.Camera4d;
import ddddbb.math.D2Graphics;
import ddddbb.math.D3Graphics;
import ddddbb.math.D4Graphics;

public class ViewScreen extends JPanel implements MyChangeListener, ItemListener {
	private static final long serialVersionUID = -8373563348401770940L;

	protected int x0,y0,width=0,height=0;
	protected BufferedImage buffImg;
	protected Color bColor = Color.BLACK;
	protected Composite composite;
	Graphics2D g;
	D2Graphics g2;
	D3Graphics g3;
	D4Graphics g4;
	
	private final Scene scene;
	private final ScreenValues sv;
	private final IntModel<Camera4d> perspective;
	private final IntModel<SimpleSwitches.ViewType> viewType;
	private final BoolModel antiAliased;
	private final BoolModel drawTetrahedral;
	private final BoolModel showGoal;
	private final Scene4d goalScene;
	
	public ViewScreen(
			final Scene _scene,
			final ScreenValues _sv,
			final IntModel<Camera4d> _perspective,
			final BoolModel _drawTetrahedral,
			final BoolModel showInternalFaces,
			final DoubleModel zoom,
			final BoolModel _antiAliased,
			final IntModel<SimpleSwitches.ViewType> _viewType,
			final IntStringModel dim34,
			final ViewAbsRelEnum viewAbsRel,
			final IntStringModel viewTransAxis,
			final IntStringModel d3ViewRotAxis,
			final BoolModel _showGoal,
			final Scene4d _goalScene
	) {
		scene = _scene;
		sv = _sv;
		perspective = _perspective;
		viewType = _viewType;
		antiAliased = _antiAliased;
		drawTetrahedral = _drawTetrahedral;
		showGoal = _showGoal;
		goalScene = _goalScene;
		
		scene.addChangeListener(this);
		sv.eyesDistHalf.addChangeListener(this);
		sv.screenEyeDist.addChangeListener(this);
		sv.xdpcm.addChangeListener(this);
		sv.ydpcm.addChangeListener(this);
		sv.barEyeFocusDelta.addChangeListener(this);
		drawTetrahedral.addChangeListener(this);
		//Opt.drawTrihedral.addChangeListener(this);
		showInternalFaces.addChangeListener(this);
		zoom.addChangeListener(this);

		antiAliased.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				updateAlias();
				repaint();
			}});
		sv.brightness.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				if (g3==null) { return; }
				g3.setBrightness(sv.brightness.getDouble());
				repaint();
			}});
		viewType.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				if (g3==null) { return; }
				g3 = viewType.getSelectedObject().getD3Graphics(g2,scene.camera3d);
				g4.setGraphics(g3);
				repaint();
			}});

//		setPreferredSize(new Dimension(
//				(int)(10.0*Opt.xcm.getDouble()),
//				(int)(10.0*Opt.ycm.getDouble()))); // 10x10cm

		MouseControl mouseControl = new MouseControl(zoom,dim34,viewAbsRel,scene.camera3d,scene.camera4d,viewTransAxis,d3ViewRotAxis,sv.mouseTransSens,sv.mouseRotSens,sv.xdpcm,sv.ydpcm);
		addMouseMotionListener(mouseControl);
		addMouseListener(mouseControl);
		addHierarchyBoundsListener(new HierarchyBoundsListener() {
			public void ancestorMoved(HierarchyEvent e) {
			}

			public void ancestorResized(HierarchyEvent e) {
				updateGraphics();
			}
			
		});
		
		addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				System.out.println("ViewScreen gained focus");
			}

			public void focusLost(FocusEvent e) {
				System.out.println("ViewScreen lost focus");
			}});
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
				g2 = new D2Graphics(g,sv.xdpcm.getDouble(),sv.ydpcm.getDouble());
				g3 = viewType.getSelectedObject().getD3Graphics(g2,scene.camera3d);
				g4 = new D4Graphics(g3,scene.camera4d, perspective);
			}
			else {
				g2.setGraphics(g);
				g3.setGraphics(g2); 
				g4.setGraphics(g3);
			}
			updateAlias();
			g3.setBrightness(sv.brightness.getDouble());
		}		
	}

	protected void updateAlias() {
		if (g==null) { return; }
		if (antiAliased.isSelected()) {
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
	
	
	/** double buffered paint */
	public void paint(Graphics g0) {
		if ( g == null ) { updateGraphics(); }
		g.clearRect(-width/2,-height/2,width,height);
		
		if (drawTetrahedral.isSelected()) { 
			g4.drawTetrahedral();
		}
//		if (Opt.drawTrihedral.isSelected()) { 
//			g3.drawTrihedral();
//		}
		
		if (showGoal.isSelected()) {
			goalScene.paint(g3);
		}
		else {
			scene.paint(g3);
		}
		g4.drawBlob(scene.compounds.getSelectedItem().center);

		((Graphics2D)g0).drawImage(buffImg, null, 0, 0);
	}
	
//	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
//		if (pageIndex > 0) { return Printable.NO_SUCH_PAGE; }
//		Color backGround = g.getBackground();
//		Color lColor = g3.lColor;
//		Color rColor = g3.rColor;
//		g.setBackground(Color.WHITE);
//		if ( g3 instanceof BarEyeGraphics ) {
//			g3.lColor = Color.BLACK;
//			g3.rColor = Color.BLACK;
//		}
//		paint(graphics);
//		g3.lColor = lColor;
//		g3.rColor = rColor;
//		g.setBackground(backGround);
//		return Printable.PAGE_EXISTS;
//	}

	public void stateChanged() {
		repaint();
	}
	public void itemStateChanged(ItemEvent e) {
		repaint();
	}
	
}