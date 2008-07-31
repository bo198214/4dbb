package ddddbb.gui;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ddddbb.game.Main.ViewAbsRel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;
import ddddbb.gen.IntStringModel;
import ddddbb.gen.MyChangeListener;
import ddddbb.math.Camera3d;
import ddddbb.math.Camera4d;

public class MouseControl implements MouseListener, MouseMotionListener, MyChangeListener {
	private int mouseX;
	private int mouseY;
	private double frx;
	private double fry;
	private double ftx;
	private double fty;
	
	private final DoubleModel zoom;
	private final IntStringModel dim34;
	private final IntModel<ViewAbsRel> viewAbsRel;
	private final Camera3d camera3d;
	private final Camera4d camera4d;
	private final DoubleModel mouseTransSens;
	private final DoubleModel mouseRotSens;
	private final DoubleModel xdpcm;
	private final DoubleModel ydpcm;
	
	public MouseControl(
			DoubleModel _zoom,
			IntStringModel _dim34,
			IntModel<ViewAbsRel> _viewAbsRel,
			Camera3d _camera3d,
			Camera4d _camera4d,
			DoubleModel _mouseTransSens,
			DoubleModel _mouseRotSens,
			DoubleModel _xdpcm,
			DoubleModel _ydpcm
		) {
		zoom = _zoom;
		dim34 = _dim34;
		viewAbsRel = _viewAbsRel;
		camera3d = _camera3d;
		camera4d = _camera4d;
		mouseTransSens = _mouseTransSens;
		mouseRotSens = _mouseRotSens;
		xdpcm = _xdpcm;
		ydpcm = _ydpcm;

		xdpcm.addChangeListener(this);
		ydpcm.addChangeListener(this);
		mouseTransSens.addChangeListener(this);
		mouseRotSens.addChangeListener(this);
		stateChanged();
	}
	
	public void mouseClicked(MouseEvent e) {
//		System.out.println(Main.opt.viewScreen.isFocusOwner() + " " + Main.opt.viewScreen );
//		ShowedScreen.MAIN.panel().requestFocusInWindow();
//		System.out.println(Main.opt.showedScreen.getSelectedObject().panel().isFocusOwner() + " " + Main.opt.showedScreen.getSelectedObject().panel());
//		Container r = ShowedScreen.MAIN.panel().getFocusCycleRootAncestor();
//		FocusTraversalPolicy f = r.getFocusTraversalPolicy(); 
//		System.out.println(r.isFocusOwner() + " cycle root " + r);
//		System.out.println(f.getFirstComponent(r).isFocusOwner() + " first component " + f.getFirstComponent(r));
//		System.out.println(f.getLastComponent(r).isFocusOwner() + " last component " + f.getLastComponent(r));
	}
	public void mouseEntered(MouseEvent e) {
//		((JPanel)(e.getSource())).requestFocus();
	}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	public void mouseReleased(MouseEvent arg0) {}

	public void mouseDragged(MouseEvent e) {
		int transrot = 0;
		int m = e.getModifiersEx();
		boolean shiftPressed = (e.getModifiers() & InputEvent.SHIFT_MASK) == InputEvent.SHIFT_MASK; 
		//System.out.println(m + "," + shiftPressed);
//		System.out.println(e.getButton());
		if ((m&InputEvent.BUTTON1_DOWN_MASK)==InputEvent.BUTTON1_DOWN_MASK) transrot=1;
		if ((m&InputEvent.BUTTON3_DOWN_MASK)==InputEvent.BUTTON3_DOWN_MASK) transrot=0;
		if (shiftPressed && transrot==1) {
			zoom.setDouble(zoom.getDouble()+(e.getY()-mouseY)*fry);
		}
		else switch (dim34.getInt()) {
		case 0: { //3d
			switch (transrot) {
			case 0: // translate
				if (shiftPressed) {
					camera3d.translate(
							viewAbsRel.getSelectedObject().selectDirec3d(2),
							-(e.getY()-mouseY)*fty
					);
				}
				else {
					camera3d.translate(
							viewAbsRel.getSelectedObject().selectDirec3d(0),
							(e.getX()-mouseX)*ftx
					);
					camera3d.translate(
							viewAbsRel.getSelectedObject().selectDirec3d(1),
							-(e.getY()-mouseY)*fty
					);
				}
				break;
			case 1: // rotate
				camera3d.rotate(
						(e.getX()-mouseX)*frx,
						viewAbsRel.getSelectedObject().selectDirec3d(1),
						viewAbsRel.getSelectedObject().selectCenter3d()		
				);
				camera3d.rotate(
						(e.getY()-mouseY)*fry,
						viewAbsRel.getSelectedObject().selectDirec3d(0),
						viewAbsRel.getSelectedObject().selectCenter3d()
				);
				break;
			}
		}
		break;
		case 1: { //4d 
			switch (transrot) {
			case 0:
				if (shiftPressed) {
					camera4d.translate(
							viewAbsRel.getSelectedObject().selectDirec4d(2),
							(e.getX()-mouseX)*ftx
							);
					camera4d.translate(
							viewAbsRel.getSelectedObject().selectDirec4d(3),
							-(e.getY()-mouseY)*fty
							);					
				}
				else {
					camera4d.translate(
							viewAbsRel.getSelectedObject().selectDirec4d(0),
							(e.getX()-mouseX)*ftx
							);
					camera4d.translate(
							viewAbsRel.getSelectedObject().selectDirec4d(1),
							-(e.getY()-mouseY)*fty
							);
				}
				break;
			case 1: 
				camera4d.rotate(
						(e.getX()-mouseX)*fry,
						viewAbsRel.getSelectedObject().selectDirec4d(0),
						viewAbsRel.getSelectedObject().selectDirec4d(2),
						viewAbsRel.getSelectedObject().selectCenter4d());
				camera4d.rotate(
						(e.getY()-mouseY)*fry,
						viewAbsRel.getSelectedObject().selectDirec4d(1),
						viewAbsRel.getSelectedObject().selectDirec4d(3),
						viewAbsRel.getSelectedObject().selectCenter4d());
				break;
			}
		}
		break;
		}

		mouseX = e.getX();
		mouseY = e.getY();

		//				int first=4,second=4,i;
//		for (i=0;i<4;i++) {
//			if (absRot[i] == true) { first = i; 
//				for (i=first+1;i<4;i++) {
//					if (absRot[i] == true) {
//						//2 of 4 keys pressed
//						second = i; 
////						System.out.println(first + "+" + second);
//						Main.model.camera.rotate((e.getY()-mouseY)/360.0,absDirec[first],absDirec[second]);
////						System.out.println(e.getX());
//						leave(e);
//						return;
//					}
//				}
//				//exactly 1 of 4 keys pressed
//				Main.model.camera.move((e.getY()-mouseY)/100.0,absDirec[first]);
//				leave(e);
//				return;
//			}						
//		}
//		for (i=0;i<4;i++) {
//			if (viewRot[i] == true ) { 
//				Direc[] viewDirec = {
//						new Direc(Main.model.camera.getV1()),
//						new Direc(Main.model.camera.getV2()),
//						new Direc(Main.model.camera.getV3()),
//						new Direc(Main.model.camera.getV4())
//				};
//				first = i;
//				for (i=first+1;i<4;i++) {
//					if (viewRot[i] == true) {
//						//pressed 2 of 4 keys
//						second = i;
//						Main.model.camera.rotate((e.getY()-mouseY)/90.0,viewDirec[first],viewDirec[second]);
//						leave(e);
//						return;
//					}
//				}
//				//pressed exactly 1 of 4 keys 
//				Main.model.camera.move((e.getY()-mouseY)/100.0,viewDirec[first]);
//				viewPanel.repaint();
//				leave(e);
//				return;
//			}
//		}
	}

	public void mouseMoved(MouseEvent arg0) {}


	public void stateChanged() {
		frx = mouseRotSens.getDouble()/xdpcm.getDouble();
		fry = mouseRotSens.getDouble()/ydpcm.getDouble();
		ftx = mouseTransSens.getDouble()/xdpcm.getDouble();
		fty = mouseTransSens.getDouble()/ydpcm.getDouble();
	}
}
