package test.d3;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MouseControl implements MouseListener, MouseMotionListener, ChangeListener {
	private int mouseX;
	private int mouseY;
	private double frx;
	private double fry;
	private double ftx;
	private double fty;
	
	public MouseControl() {
		Opt.xcm.addChangeListener(this);
		Opt.ycm.addChangeListener(this);
		Opt.mouseTransSens.addChangeListener(this);
		Opt.mouseRotSens.addChangeListener(this);
		stateChanged(null);
	}
	
	public void mouseClicked(MouseEvent e) {
//		((ViewPanel)(e.getSource())).requestFocus();		
	}
	public void mouseEntered(MouseEvent e) {
		((JPanel)(e.getSource())).requestFocus();
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
//		System.out.println(m);
//		System.out.println(e.getButton());
		if ((m&InputEvent.BUTTON1_DOWN_MASK)==InputEvent.BUTTON1_DOWN_MASK) {transrot=1;}
		if ((m&InputEvent.BUTTON3_DOWN_MASK)==InputEvent.BUTTON3_DOWN_MASK) {transrot=0;}
		switch (Test3d.opt.dim34.getInt()) {
		case 0: { //3d
			switch (transrot) {
			case 0: // translate
				Test3d.opt.scene.camera3d.translate(
						(e.getX()-mouseX)*ftx,
						Test3d.opt.viewAbsRel.getSelectedObject().selectDirec3d(0)
				);
				Test3d.opt.scene.camera3d.translate(
						-(e.getY()-mouseY)*fty,
						Test3d.opt.viewAbsRel.getSelectedObject().selectDirec3d(1)
				);
				break;
			case 1: // rotate
				Test3d.opt.scene.camera3d.rotate(
						(e.getX()-mouseX)*frx,
						Test3d.opt.viewAbsRel.getSelectedObject().selectDirec3d(1),
						Test3d.opt.viewAbsRel.getSelectedObject().selectCenter3d());
				Test3d.opt.scene.camera3d.rotate(
						(e.getY()-mouseY)*fry,
						Test3d.opt.viewAbsRel.getSelectedObject().selectDirec3d(0),
						Test3d.opt.viewAbsRel.getSelectedObject().selectCenter3d());
//				Test3d.opt.scene.camera3d.rotate(
//				(e.getY()-mouseY)*fry,
//				Test3d.opt.viewAbsRel.getSelectedObject().selectDirec3d(Test3d.opt.d3ViewRotAxis.getInt()),
//				Test3d.opt.viewAbsRel.getSelectedObject().selectCenter3d());
				break;
			}
		}
		break;
		case 1:  //4d 
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
//						Test3d.model.camera.rotate((e.getY()-mouseY)/360.0,absDirec[first],absDirec[second]);
////						System.out.println(e.getX());
//						leave(e);
//						return;
//					}
//				}
//				//exactly 1 of 4 keys pressed
//				Test3d.model.camera.move((e.getY()-mouseY)/100.0,absDirec[first]);
//				leave(e);
//				return;
//			}						
//		}
//		for (i=0;i<4;i++) {
//			if (viewRot[i] == true ) { 
//				Direc[] viewDirec = {
//						new Direc(Test3d.model.camera.getV1()),
//						new Direc(Test3d.model.camera.getV2()),
//						new Direc(Test3d.model.camera.getV3()),
//						new Direc(Test3d.model.camera.getV4())
//				};
//				first = i;
//				for (i=first+1;i<4;i++) {
//					if (viewRot[i] == true) {
//						//pressed 2 of 4 keys
//						second = i;
//						Test3d.model.camera.rotate((e.getY()-mouseY)/90.0,viewDirec[first],viewDirec[second]);
//						leave(e);
//						return;
//					}
//				}
//				//pressed exactly 1 of 4 keys 
//				Test3d.model.camera.move((e.getY()-mouseY)/100.0,viewDirec[first]);
//				viewPanel.repaint();
//				leave(e);
//				return;
//			}
//		}
	}

	public void mouseMoved(MouseEvent arg0) {}


	public void stateChanged(ChangeEvent e) {
		frx = Opt.mouseRotSens.getDouble()/Opt.xcm.getDouble();
		fry = Opt.mouseRotSens.getDouble()/Opt.ycm.getDouble();
		ftx = Opt.mouseTransSens.getDouble()/Opt.xcm.getDouble();
		fty = Opt.mouseTransSens.getDouble()/Opt.ycm.getDouble();
	}
}
