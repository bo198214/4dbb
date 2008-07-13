package ddddbb.comb;

import java.util.Collection;

public abstract class Equality<T> {
	public abstract boolean equal(T a, T b);
	
	public boolean isSet(Collection<? extends T> collection) {
		for ( T el : collection ) {
			boolean search = false;
			for ( T el2 : collection ) {
				if ( search == true ) {
					if (equal(el,el2)) { return false; }
				}
				else if ( el == el2 ) {
					search = true;
				}
			}
		}
		return true;
	}
	
	/** under the assumption that there are no multiple elements in a and b */
	public boolean setContained(Collection<? extends T> a, Collection<? extends T> b) {
		assert isSet(a);
		assert isSet(b);
		if (a.size() > b.size()) { return false; }
		for (T ael : a) {
			boolean contained = false;
			for ( T bel : b ) {
				if (equal(ael,bel)) {
					contained = true;
				}
			}
			if ( ! contained ) { return false; }
		}
		return true;
	}
	
	/** under the assumption that there are no multiple elements in a and b */
	public boolean setEqual(Collection<? extends T> a, Collection<? extends T> b) {
		assert isSet(a);
		assert isSet(b);
		if (a.size()!=b.size()) { return false; }
		return setContained(a,b);
	}

	public boolean pointerEqual(T a, T b) {
		return a == b;
	}

	
}
