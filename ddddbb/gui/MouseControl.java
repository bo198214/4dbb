package ddddbb.gui;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ddddbb.game.Opt;

public class MouseControl implements MouseListener, MouseMotionListener, ChangeListener {
	private int mouseX;
	private int mouseY;
	private double frx;
	private double fry;
	private double ftx;
	private double fty;
	
	public MouseControl() {
		Opt.xdpcm.addChangeListener(this);
		Opt.ydpcm.addChangeListener(this);
		Opt.mouseTransSens.addChangeListener(this);
		Opt.mouseRotSens.addChangeListener(this);
		stateChanged(null);
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
			Opt.zoom.setDouble(Opt.zoom.getDouble()+(e.getY()-mouseY)*fry);
		}
		else switch (Opt.dim34.getInt()) {
		case 0: { //3d
			switch (transrot) {
			case 0: // translate
				if (shiftPressed) {
					Opt.scene.camera3d.translate(
							Opt.viewAbsRel.getSelectedObject().selectDirec3d(2),
							-(e.getY()-mouseY)*fty
//							selectDirec3d(Opt.d3ViewTransAxis.getInt()+2)
					);
				}
				else {
//					Main.scene.camera3d.translate(
//							(e.getX()-mouseX)*ftx,
////							selectDirec3d(0)
//							selectDirec3d(Opt.d3ViewTransAxis.getInt()-1)
//					);
					Opt.scene.camera3d.translate(
							Opt.viewAbsRel.getSelectedObject().selectDirec3d(Opt.d3ViewTransAxis.getInt()),
//							selectDirec3d(1)
							-(e.getY()-mouseY)*fty
					);
				}
				break;
			case 1: // rotate
				Opt.scene.camera3d.rotate(
						(e.getX()-mouseX)*frx,
						Opt.viewAbsRel.getSelectedObject().selectDirec3d(1),
						Opt.viewAbsRel.getSelectedObject().selectCenter3d()		
				);
				Opt.scene.camera3d.rotate(
						(e.getY()-mouseY)*fry,
						Opt.viewAbsRel.getSelectedObject().selectDirec3d(0),
						Opt.viewAbsRel.getSelectedObject().selectCenter3d()
				);
				break;
			}
		}
		break;
		case 1: { //4d 
			switch (transrot) {
			case 0:
				if (shiftPressed) {
					Opt.scene.camera4d.translate(
							Opt.viewAbsRel.getSelectedObject().selectDirec4d(2),
//							selectDirec(Opt.viewTransAxis.getInt()-1),
							(e.getX()-mouseX)*ftx
							);
					Opt.scene.camera4d.translate(
							Opt.viewAbsRel.getSelectedObject().selectDirec4d(3),
//							selectDirec(Opt.viewTransAxis.getInt()),
							-(e.getY()-mouseY)*fty
							);					
				}
				else {
//					Main.scene.camera.translate(
//							(e.getX()-mouseX)*ftx,
////							selectDirec(0)
//							selectDirec(Opt.viewTransAxis.getInt()-1)
//							);
					Opt.scene.camera4d.translate(
							//							selectDirec(1)
							Opt.viewAbsRel.getSelectedObject().selectDirec4d(Opt.viewTransAxis.getInt()),
-(e.getY()-mouseY)*fty
							);
				}
				break;
			case 1: 
				Opt.scene.camera4d.rotate(
						(e.getX()-mouseX)*fry,
						Opt.viewAbsRel.getSelectedObject().selectDirec4d(0),
						Opt.viewAbsRel.getSelectedObject().selectDirec4d(2),
						Opt.viewAbsRel.getSelectedObject().selectCenter4d());
				Opt.scene.camera4d.rotate(
						(e.getY()-mouseY)*fry,
						Opt.viewAbsRel.getSelectedObject().selectDirec4d(1),
						Opt.viewAbsRel.getSelectedObject().selectDirec4d(3),
						Opt.viewAbsRel.getSelectedObject().selectCenter4d());
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


	public void stateChanged(ChangeEvent e) {
		frx = Opt.mouseRotSens.getDouble()/Opt.xdpcm.getDouble();
		fry = Opt.mouseRotSens.getDouble()/Opt.ydpcm.getDouble();
		ftx = Opt.mouseTransSens.getDouble()/Opt.xdpcm.getDouble();
		fty = Opt.mouseTransSens.getDouble()/Opt.ydpcm.getDouble();
	}
}
