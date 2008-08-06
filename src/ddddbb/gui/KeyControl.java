package ddddbb.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ddddbb.game.Level;
import ddddbb.game.Settings;
import ddddbb.gen.BoolModel;
import ddddbb.gen.DiAxisModel;
import ddddbb.gen.IntStringModel;
import ddddbb.math.AOP;

public class KeyControl implements KeyListener {
	public static class LookupTable3int<T> {
		public int c1,c2,c3;
		Object[][][] data;
		public LookupTable3int(int _c1,int _c2, int _c3) {
			c1=_c1;c2=_c2;c3=_c3;
		}

		public void put(int i1,int i2, int i3, T val) {
			assert i1 < c1 && i2 < c2 & i3 < c3;
			if (data==null) {
				data=new Object[c1][][];
			}
			if (data[i1] == null) {
				data[i1] = new Object[c2][]; 
			}
			if (data[i1][i2] == null) {
				data[i1][i2] = new Object[c3];
			}
			data[i1][i2][i3] = val;
		}
		@SuppressWarnings("unchecked")
		public T get(int i1,int i2, int i3) {
			if (i1 >= c1) { return null; }
			if (i2 >= c2) { return null; }
			if (i3 >= c3) { return null; }
			if (data==null) { return null; }
			if (data[i1]==null) { return null; }
			if (data[i1][i2] == null) { return null;}
			return (T)data[i1][i2][i3];
		}
		public boolean has(int i1, int i2) {
			if (i1>=c1) return false; 
			if (i2>=c2) return false; 
			if (data==null) return false;
			if (data[i1]==null) return false;
			if (data[i1][i2]==null) return false;
			return true;
		}
	}

	public class LookupTable2int<T extends Object> {
		public int c1,c2;
		T[][] data;
		public LookupTable2int(int _c1,int _c2) {
			c1=_c1;c2=_c2;
		}

		@SuppressWarnings("unchecked")
		public void put(int i1,int i2, T val) {
			assert i1 < c1 && i2 < c2;
			if (data==null) {
				data=(T[][])new Object[c1][];
			}
			if (data[i1] == null) {
				data[i1] = (T[])new Object[c2]; 
			}
			data[i1][i2] = val;
		}
		public T get(int i1,int i2) {
			if (i1 >= c1) { return null; }
			if (i2 >= c2) { return null; }
			if (data==null) {
				return null;
			}
			if (data[i1]==null) {
				return null;
			}
			return data[i1][i2];
		}
	}

	public KeyControl(final Settings ss, final Level scene) {
		int maxMod = 16;
		int maxKey = 100;
		singleKeyAssignment = new LookupTable2int<ActionListener>(maxMod,maxKey);
		LookupTable2int<ActionListener> s = singleKeyAssignment;
		int SHIFT = InputEvent.SHIFT_MASK;
		int CTRL = InputEvent.CTRL_MASK;
		int ALT = InputEvent.ALT_MASK;

		UIAction ua = new UIAction(ss,scene);
		
		s.put(0,37,scene.transSelectedA(-1)); // <-
		s.put(0,39,scene.transSelectedA(+1)); // ->
		s.put(0,40,scene.transSelectedA(-2)); // v
		s.put(0,38,scene.transSelectedA(+2)); // ^
		s.put(SHIFT,37,scene.transSelectedA(-4)); // <-
		s.put(SHIFT,39,scene.transSelectedA(+4)); // ->
		s.put(SHIFT,40,scene.transSelectedA(-3)); // v
		s.put(SHIFT,38,scene.transSelectedA(+3)); // ^

		//ALT corresponds to right mouse button
		s.put(ALT,37,ua.transCamByHorizA(-0.1)); 
		s.put(ALT,39,ua.transCamByHorizA(+0.1)); 
		s.put(ALT,40,ua.transCamByVerticA(-0.1));
		s.put(ALT,38,ua.transCamByVerticA(+0.1));
		s.put(SHIFT|ALT,37,ua.transCam(-4));
		s.put(SHIFT|ALT,39,ua.transCam(+4));
		s.put(SHIFT|ALT,40,ua.transCam(-3));
		s.put(SHIFT|ALT,38,ua.transCam(+3));
		
		//CTRL corresponds to left mouse button
		s.put(CTRL,37,ua.rotCamByHorizA(-0.1));
		s.put(CTRL,39,ua.rotCamByHorizA(+0.1));
		s.put(CTRL,40,ua.rotCamByVerticA(-0.1));
		s.put(CTRL,38,ua.rotCamByVerticA(+0.1));
		
		s.put(0,70,scene.transSelectedA(1)); //f
		s.put(0,68,scene.transSelectedA(2)); //d
		s.put(0,83,scene.transSelectedA(3)); //s
		s.put(0,65,scene.transSelectedA(4)); //a
		s.put(SHIFT,70,scene.transSelectedA(-1)); //f
		s.put(SHIFT,68,scene.transSelectedA(-2)); //d
		s.put(SHIFT,83,scene.transSelectedA(-3)); //s
		s.put(SHIFT,65,scene.transSelectedA(-4)); //a

		s.put(0,74,ua.transCam(1)); //j
		s.put(0,75,ua.transCam(2)); //k
		s.put(0,76,ua.transCam(3)); //l
		s.put(0,59,ua.transCam(4)); //;
		s.put(SHIFT,74,ua.transCam(-1)); //j
		s.put(SHIFT,75,ua.transCam(-2)); //k
		s.put(SHIFT,76,ua.transCam(-3)); //l
		s.put(SHIFT,59,ua.transCam(-4)); //;
		
		s.put(0, 89, ss.zoom.increaseAction); //z
		s.put(SHIFT, 89, ss.zoom.decreaseAction); //Z
		
		s.put(0, 72, ua.resetCam); //h
		s.put(0,51,ua.setTo3d); //3
		s.put(0,52,ua.setTo4d); //4
		s.put(0,84,ss.drawTetrahedral.toggleAction); //t
		s.put(0, 66, ss.occlusion4dAllowance.cyclicNextAction); //b
		
		
		s.put(0,86,scene.compounds.nextAction); //v
		s.put(0,88,scene.compounds.prevAction); //x
		s.put(0,67,scene.combineAction); //c
		s.put(0,71,ss.showGoal.toggleAction); //g
		s.put(0,20,ss.dim34.cyclicNextAction);//caps lock

		doubleKeyAssignment = new LookupTable3int<ActionListener>(maxMod,maxKey,maxKey);
		LookupTable3int<ActionListener> d = doubleKeyAssignment;
		
		d.put(0,82,69,scene.rotSelectedA(1,2)); //r,e
		d.put(0,69,82,scene.rotSelectedA(1,3)); //e,r
		d.put(0,82,87,scene.rotSelectedA(1,4)); //r,w
		d.put(0,87,82,scene.rotSelectedA(2,3)); //w,r
		d.put(0,82,81,scene.rotSelectedA(2,4)); //r,q
		d.put(0,81,82,scene.rotSelectedA(3,4)); //q,r
		d.put(0,69,87,scene.rotSelectedA(2,1)); //e,w
		d.put(0,87,69,scene.rotSelectedA(3,1)); //w,e
		d.put(0,69,81,scene.rotSelectedA(4,1)); //e,q
		d.put(0,81,69,scene.rotSelectedA(3,2)); //q,e
		d.put(0,87,81,scene.rotSelectedA(4,2)); //w,q
		d.put(0,81,87,scene.rotSelectedA(4,3)); //q,w

		d.put(0,85,73,ua.rotCamA(1,2)); //u,i
		d.put(0,85,79,ua.rotCamA(1,3)); //u,o
		d.put(0,85,80,ua.rotCamA(1,4)); //u,p
		d.put(0,73,79,ua.rotCamA(2,3)); //i,o
		d.put(0,73,80,ua.rotCamA(2,4)); //i,p
		d.put(0,79,80,ua.rotCamA(3,4)); //o,p
		d.put(0,73,85,ua.rotCamA(2,1)); //i,u
		d.put(0,79,85,ua.rotCamA(3,1)); //o,u
		d.put(0,80,85,ua.rotCamA(4,1)); //p,u
		d.put(0,79,73,ua.rotCamA(3,2)); //o,i
		d.put(0,80,73,ua.rotCamA(4,2)); //p,i
		d.put(0,80,79,ua.rotCamA(4,3)); //p,o

	}
	
	public static LookupTable2int<ActionListener> singleKeyAssignment;
	public static LookupTable3int<ActionListener> doubleKeyAssignment;
	
	public int pressedKey = -1;
	
	public synchronized void keyPressed(KeyEvent e) {
		if (doubleKeyAssignment.has(e.getModifiers(),e.getKeyCode()) &&	pressedKey==-1) {
			pressedKey = e.getKeyCode();
			return;
		}
		
		ActionListener p = singleKeyAssignment.get(e.getModifiers(),e.getKeyCode());
		if (p!=null) {
			p.actionPerformed(null);
			return;
		}
		else if (pressedKey!=-1) {
			p = doubleKeyAssignment.get(e.getModifiers(),pressedKey,e.getKeyCode());
			if (p!=null) {
				p.actionPerformed(null);
				return;
			}
		}
		
		switch (e.getKeyCode()) {
		case 16: //Caps
		case 17: //shift
		case 18:  //CRTL
		case 524: //Win
		case 525: //Context
			return; 	
		}
		System.out.println("Keycode " + e.getKeyCode() + " not assigned.");
		//System.out.println(e.getKeyLocation());
	}
	
	public synchronized void keyReleased(KeyEvent e) {
		if (pressedKey == e.getKeyCode()) {
			pressedKey = -1;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		//adapter
	}
}
