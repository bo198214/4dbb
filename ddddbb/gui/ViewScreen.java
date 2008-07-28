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

import ddddbb.game.Opt;
import ddddbb.gen.MyChangeListener;
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
	
	public ViewScreen() {
		super();
		initialize();
	}
	
	private void initialize() {
		Opt.scene.addChangeListener(this);
		Opt.eyesDistHalf.addChangeListener(this);
		Opt.screenEyeDist.addChangeListener(this);
		Opt.xdpcm.addChangeListener(this);
		Opt.ydpcm.addChangeListener(this);
		Opt.barEyeFocusDelta.addChangeListener(this);
		Opt.drawTetrahedral.addChangeListener(this);
		//Opt.drawTrihedral.addChangeListener(this);
		ddddbb.math.Param.showInternalFaces.addChangeListener(this);
		Opt.zoom.addChangeListener(this);

		Opt.antiAliased.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				updateAlias();
				repaint();
			}});
		Opt.brightness.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				if (g3==null) { return; }
				g3.setBrightness(Opt.brightness.getDouble());
				repaint();
			}});
		Opt.viewType.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				if (g3==null) { return; }
				g3 = Opt.viewType.getSelectedObject().getD3Graphics(g2,Opt.scene.camera3d);
				g4.setGraphics(g3);
				repaint();
			}});

//		setPreferredSize(new Dimension(
//				(int)(10.0*Opt.xcm.getDouble()),
//				(int)(10.0*Opt.ycm.getDouble()))); // 10x10cm

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
				g2 = new D2Graphics(g,Opt.xdpcm.getDouble(),Opt.ydpcm.getDouble());
				g3 = Opt.viewType.getSelectedObject().getD3Graphics(g2,Opt.scene.camera3d);
				g4 = new D4Graphics(g3,Opt.scene.camera4d);
			}
			else {
				g2.setGraphics(g);
				g3.setGraphics(g2); 
				g4.setGraphics(g3);
			}
			updateAlias();
			g3.setBrightness(Opt.brightness.getDouble());
		}		
	}

	protected void updateAlias() {
		if (g==null) { return; }
		if (Opt.antiAliased.isSelected()) {
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
		
		if (Opt.drawTetrahedral.isSelected()) { 
			g4.drawTetrahedral();
		}
//		if (Opt.drawTrihedral.isSelected()) { 
//			g3.drawTrihedral();
//		}
		
		Opt.scene.paint(g3);
		g4.drawBlob(Opt.scene.compounds.getSelectedItem().center);

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