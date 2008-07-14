package ddddbb.gui;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;


public class KeyControl implements KeyListener {
	public static class KeyAction {
		public int modifiers;
		public int keyCode;
		public Performer action;

		public KeyAction(int _keyCode, UIActions _action) {
			keyCode = _keyCode;
			modifiers = 0;
			action = _action;
		}
		public KeyAction(int _keyCode,int _modifiers,UIActions _action) {
			keyCode = _keyCode;
			modifiers = _modifiers;
			action = _action;
		}
		
		public void perform() {

			action.perform();
		}
		
	}
	public static class DoubleKeyAction {
		public int keyCode1;
		public int keyCode2;
		public boolean key1Pressed = false;
		public boolean key2Pressed = false;
		public Performer action;

		public DoubleKeyAction(int _keyCode1, int _keyCode2,UIActions _action) {
			keyCode1 = _keyCode1;
			keyCode2 = _keyCode2;
			action = _action;
		}
		
		public void perform() {
			action.perform();
		}
		
	}
	
	public static final KeyAction[] defaultSingleKeyAssignment = {
		new KeyAction(37,UIActions.transSelectedMD1), // <-
		new KeyAction(37,InputEvent.SHIFT_MASK,UIActions.transSelectedPD4), 
		new KeyAction(37,InputEvent.CTRL_MASK,UIActions.rot3dCamZX),
		new KeyAction(39,UIActions.transSelectedPD1), // ->
		new KeyAction(39,InputEvent.SHIFT_MASK,UIActions.transSelectedMD4), 
		new KeyAction(39,InputEvent.CTRL_MASK,UIActions.rot3dCamXZ),
		new KeyAction(38,UIActions.transSelectedPD2), // ^
		new KeyAction(38,InputEvent.SHIFT_MASK,UIActions.transSelectedPD3),
		new KeyAction(38,InputEvent.CTRL_MASK,UIActions.rot3dCamYZ),
		new KeyAction(40,UIActions.transSelectedMD2), // v
		new KeyAction(40,InputEvent.SHIFT_MASK,UIActions.transSelectedMD3),
		new KeyAction(40,InputEvent.CTRL_MASK,UIActions.rot3dCamZY),
		new KeyAction(74,UIActions.transSelectedMD1), //j
		new KeyAction(74,InputEvent.SHIFT_MASK,UIActions.transSelectedMD3),
		new KeyAction(76,UIActions.transSelectedPD1), //l
		new KeyAction(76,InputEvent.SHIFT_MASK,UIActions.transSelectedPD4),
		new KeyAction(75,UIActions.transSelectedMD2), //k
		new KeyAction(75,InputEvent.SHIFT_MASK,UIActions.transSelectedMD3),
		new KeyAction(73,UIActions.transSelectedPD2), //i
		new KeyAction(73,InputEvent.SHIFT_MASK,UIActions.transSelectedPD3),
		new KeyAction(49,UIActions.setSelected0), //1
		new KeyAction(50,UIActions.setSelected1), //2
		new KeyAction(51,UIActions.setSelected2), //3
		new KeyAction(52,UIActions.setSelected3), //4
		new KeyAction(53,UIActions.setSelected4), //5
		new KeyAction(54,UIActions.setSelected5), //6
		new KeyAction(55,UIActions.setSelected6), //7
		new KeyAction(56,UIActions.setSelected7), //8
		new KeyAction(57,UIActions.setSelected8), //9
		new KeyAction(78,UIActions.nextSelected), //n
		new KeyAction(86,UIActions.nextSelected), //v
		new KeyAction(32,UIActions.showGoal), //SPACE
		new KeyAction(88,UIActions.prevSelected), //x
		new KeyAction(67,UIActions.combineTouchingSelected), //c
		new KeyAction(84,UIActions.set3dRotAxis1), //t
		new KeyAction(71,UIActions.set3dRotAxis2), //g
		new KeyAction(66,UIActions.set3dRotAxis3), //b
	};
	
	public static final DoubleKeyAction[] defaultDoubleKeyAssignment = {
		new DoubleKeyAction(70,68,UIActions.rotSelected12), //f,d
		new DoubleKeyAction(70,83,UIActions.rotSelected13), //f,s
		new DoubleKeyAction(70,65,UIActions.rotSelected14), //f,a
		new DoubleKeyAction(68,83,UIActions.rotSelected23), //d,s
		new DoubleKeyAction(68,65,UIActions.rotSelected24), //d,a
		new DoubleKeyAction(83,65,UIActions.rotSelected34), //s,a
		new DoubleKeyAction(68,70,UIActions.rotSelected21), //d,f
		new DoubleKeyAction(83,70,UIActions.rotSelected31), //s,f
		new DoubleKeyAction(65,70,UIActions.rotSelected41), //a,f
		new DoubleKeyAction(83,68,UIActions.rotSelected32), //s,d
		new DoubleKeyAction(65,68,UIActions.rotSelected42), //a,d
		new DoubleKeyAction(65,83,UIActions.rotSelected43), //a,s
		new DoubleKeyAction(82,69,UIActions.set4dRotAxes12), //r,e
		new DoubleKeyAction(69,82,UIActions.set4dRotAxes12), //r,e
		new DoubleKeyAction(82,87,UIActions.set4dRotAxes13), //r,w
		new DoubleKeyAction(87,82,UIActions.set4dRotAxes13), //r,w
		new DoubleKeyAction(82,81,UIActions.set4dRotAxes14), //r,q
		new DoubleKeyAction(81,82,UIActions.set4dRotAxes14), //r,q
		new DoubleKeyAction(69,87,UIActions.set4dRotAxes23), //e,w
		new DoubleKeyAction(87,69,UIActions.set4dRotAxes23), //e,w
		new DoubleKeyAction(69,81,UIActions.set4dRotAxes24), //e,q
		new DoubleKeyAction(81,69,UIActions.set4dRotAxes24), //e,q
		new DoubleKeyAction(87,81,UIActions.set4dRotAxes34), //w,q
		new DoubleKeyAction(81,87,UIActions.set4dRotAxes34), //w,q
	};
	
	public static List<KeyAction> singleKeyAssignment = 
		Arrays.asList(defaultSingleKeyAssignment);
	public static List<DoubleKeyAction> doubleKeyAssignment = 
		Arrays.asList(defaultDoubleKeyAssignment);
	
	
	private void setState(KeyEvent e, boolean val) {
		for (DoubleKeyAction doubleKeyAction:doubleKeyAssignment) {
			if ( doubleKeyAction.keyCode1 == e.getKeyCode() ) {
				doubleKeyAction.key1Pressed = val;
			}
			if ( doubleKeyAction.keyCode2 == e.getKeyCode() ) {
				doubleKeyAction.key2Pressed = val;
			}
		}
	}
	
	public synchronized void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		setState(e,true);
		for (KeyAction keyAction:singleKeyAssignment) {
			if (
					keyAction.keyCode == e.getKeyCode() && 
					e.getModifiers()  == keyAction.modifiers
			) {
				keyAction.perform();
			}
		}
		for (DoubleKeyAction doubleKeyAction: doubleKeyAssignment) {
			if ( doubleKeyAction.keyCode2 == e.getKeyCode() && doubleKeyAction.key1Pressed ) {
				doubleKeyAction.perform();
			}
		}
	}
	
	public synchronized void keyReleased(KeyEvent e) {
		setState(e,false);
	}
	
	public void keyTyped(KeyEvent e) {
		//adapter
	}
}
