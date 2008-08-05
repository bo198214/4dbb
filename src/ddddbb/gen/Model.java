package ddddbb.gen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.event.ChangeListener;

import ddddbb.game.Main;

/** A class for supporting the Model View paradigm.
 * Basically it has a changed() method that notifies all the registered 
 * ChangeListeners (which are the viewers).
 * The class is itself a ChangeListener, that just propagates changes.
 * For example you can add this to attribute Models, their changes become
 * then also this's changes.
 *  
 * @author bo198214
 *
 */
public abstract class Model implements MyChangeListener {
	public Vector<MyChangeListener> changeListeners = new Vector<MyChangeListener>();
	
	/** Switch on or off notifications of the ChangeListeners.
	 * Useful if you call several methods that contain a changed-call but you
	 * only want to call changed once. Then you put just a 
	 * nofify=false before the calls and a notify=true after the calls.
	 * and call then changed() separately.
	 */
	public boolean notify = true;
	
//	public void changed(ChangeEvent e) {
//		for (ChangeListener l : changeListeners) {
//			l.stateChanged(e);
//		}
//	}

	/** invokes stateChanged of all listeners */
	public void changed() {
		//if (Main.debug.isSelected()) System.out.println(this + " changed");
		if (!notify) return;
		for (MyChangeListener l : changeListeners) {
			//if (Main.debug.isSelected()) System.out.println(this + " delegating to: " + l);
			l.stateChanged();
		}		
	}
	
	public void addChangeListener(MyChangeListener l) {
		changeListeners.add(l);
	}
	public void removeChangeListener(MyChangeListener l) {
		changeListeners.remove(l);
	}
	public abstract void setToDefault();
	
	//the following are for compatibility with javax.swing.event.ChangeListener
	private class CL implements MyChangeListener {
		public ChangeListener l;
		CL(ChangeListener _l) {
			l=_l;
		}
		public void stateChanged() {
			l.stateChanged(new javax.swing.event.ChangeEvent(Model.this));
		}
	}
	public void addChangeListener(ChangeListener l) {
		addChangeListener(new CL(l));
	}
	public void removeChangeListener(ChangeListener listener) {
		Vector<CL> cls = new Vector<CL>();
		for (MyChangeListener l : changeListeners) {
			if ( l instanceof CL) {
				CL cl = (CL)l;
				if (cl.l==listener) {
					cls.add(cl);
				}
			}
		}
		changeListeners.removeAll(cls);
	}
	
	public void stateChanged() {
		//delegate status change
		changed();
	}
}
