package ddddbb.gen;

import java.util.Vector;

import javax.swing.event.ChangeListener;

public abstract class Model implements MyChangeListener {
	public Vector<MyChangeListener> changeListeners = new Vector<MyChangeListener>();
	
//	public void changed(ChangeEvent e) {
//		for (ChangeListener l : changeListeners) {
//			l.stateChanged(e);
//		}
//	}

	public void changed() {
		for (MyChangeListener l : changeListeners) {
			l.stateChanged();
		}		
	}
	
	public void addChangeListener(MyChangeListener l) {
		changeListeners.add(l);
		l.stateChanged();
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
