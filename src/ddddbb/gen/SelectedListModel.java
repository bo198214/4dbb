package ddddbb.gen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/** This class is more flexible than {@link IntModel} as it allows
 * to add and remove items. It implements List<T>. 
 * List changes are additionally propagated to the ChangeListeners.  
 * 
 * @author bo198214
 */
public class SelectedListModel<T> extends Model implements SelArray<T>, List<T> {
	private int sel;
	private List<T> items;
	
	public SelectedListModel() {
		setToDefault();
	}
	
	public void setToDefault() {
		sel = -1;
		items = new Vector<T>();
	}
	
	public int selInt() {
		return sel;
	}
	public void setSelInt(int i) {
		sel = i;
		changed();
	}
	public ActionListener setSelectedAction(final int i) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelInt(i);
			}
			
		};
	}

	@Override
	public T sel() {
		if (sel == -1) { return null; }
		return items.get(sel);
	}

	@Override
	public void setSel(T item) {
		setSelInt(items.indexOf(item));
	}

	public void nextSelected() {
		if (sel == items.size()-1 ) { setSelInt(0); }
		else { setSelInt(sel+1); }
		changed();
	}

	public ActionListener nextAction  = new ActionListener()  {
		public void actionPerformed(ActionEvent e) {
			nextSelected();
		}		
	};

	public void prevSelected() {
		if (sel == 0) { setSelInt(items.size()-1); }
		else { setSelInt(sel-1); }
		changed();
	}
	
	public ActionListener prevAction = new ActionListener()  {
		public void actionPerformed(ActionEvent e) {
			prevSelected();
		}		
	};

	
	
	public void clear() {
		items.clear();
		sel = -1;
		changed();
	}
	public boolean retainAll(Collection<?> c) {
		Object origSel = sel();
		boolean res = items.retainAll(c);
		if (res) { //changed
			sel = items.indexOf(origSel);
			changed();
		}
		return res;
	}
	public boolean remove(Object o) {
		Object origSel = sel();
		boolean res = items.remove(o);
		if (res) { //changed
			sel = items.indexOf(origSel);
			changed();
		}
		return res;
	}
	public boolean removeAll(Collection<?> c) {
		Object origSel = sel();
		boolean res = items.removeAll(c);
		if (res) { //changed
			sel = items.indexOf(origSel);
			changed();
		}
		return res;
	}
	public T remove(int index) {
		T res = items.remove(index);
		if (items.size()==0) { index = -1; }
		else if (index == sel) {
			if (index>0) { sel = index-1; }
			else         { sel = index+1; }
		}
		changed();
		return res;
	}
	public T set(int index, T element) {
		T res = items.set(index, element);
		changed();
		return res;
	}
	public void add(int index, T element) {
		Object origSel = sel();
		items.add(index, element);
		sel = items.indexOf(origSel);
		if (sel == -1) { sel = 0; }
		changed();
	}
	public boolean add(T o) {
		boolean res = items.add(o);
		if (sel == -1) { sel = 0; }
		changed();
		return res;
	}
	public boolean addAll(Collection<? extends T> c) {
		boolean res = items.addAll(c);
		if (res) { //changed
			if (sel == -1) { sel = 0; }
			changed();
		}
		return res;
	}
	public boolean addAll(int index, Collection<? extends T> c) {
		Object origSel = sel();
		boolean res = items.addAll(index, c);
		if (res) { //changed
			sel = items.indexOf(origSel);
			if (sel == -1) { sel = 0; }			
			changed();
		}
		return res;
	}

	/* read only and selected unchanged */
	public boolean contains(Object o) {
		return items.contains(o);
	}
	public boolean containsAll(Collection<?> c) {
		return items.containsAll(c);
	}
	public T get(int index) {
		return items.get(index);
	}
	public int indexOf(Object o) {
		return items.indexOf(o);
	}
	public boolean isEmpty() {
		return items.isEmpty();
	}
	public Iterator<T> iterator() {
		return items.iterator();
	}
	public int lastIndexOf(Object o) {
		return items.lastIndexOf(o);
	}
	public ListIterator<T> listIterator() {
		return items.listIterator();
	}
	public ListIterator<T> listIterator(int index) {
		return items.listIterator(index);
	}
	public int size() {
		return items.size();
	}
	public List<T> subList(int fromIndex, int toIndex) {
		return items.subList(fromIndex, toIndex);
	}
	public Object[] toArray() {
		return items.toArray();
	}
	public <S> S[] toArray(S[] a) {
		return items.toArray(a);
	}

}
