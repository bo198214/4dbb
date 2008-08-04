package ddddbb.gui;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ddddbb.game.Level;
import ddddbb.game.Settings;
import ddddbb.gen.MyChangeListener;

public class MouseControl implements MouseListener, MouseMotionListener, MyChangeListener {
	private int mouseX;
	private int mouseY;
	private double frx;
	private double fry;
	private double ftx;
	private double fty;
	
	private final Settings ss;
	private final Level scene;
	
	public MouseControl(
			Settings _ss,
			Level _scene
		) {
		ss = _ss;
		scene = _scene;

		ss.xdpcm.addChangeListener(this);
		ss.ydpcm.addChangeListener(this);
		ss.mouseTransSens.addChangeListener(this);
		ss.mouseRotSens.addChangeListener(this);
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
			ss.zoom.setDouble(ss.zoom.getDouble()+(e.getY()-mouseY)*fry);
		}
		else switch (transrot) {
			case 0: // translate
				if (shiftPressed) {
					scene.camera3d.trans(3,-(e.getY()-mouseY)*fty, scene.viewAbsRel.isSelected());
				}
				else {
					scene.transCamByHoriz((e.getX()-mouseX)*ftx);
					scene.transCamByVertic(-(e.getY()-mouseY)*fty);
				}
				break;
			case 1: // rotate
				scene.rotCamByHoriz((e.getX()-mouseX)*frx);
				scene.rotCamByVertic(-(e.getY()-mouseY)*fry);
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
		frx = ss.mouseRotSens.getDouble()/ss.xdpcm.getDouble();
		fry = ss.mouseRotSens.getDouble()/ss.ydpcm.getDouble();
		ftx = ss.mouseTransSens.getDouble()/ss.xdpcm.getDouble();
		fty = ss.mouseTransSens.getDouble()/ss.ydpcm.getDouble();
	}
}
