package ddddbb.gen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import ddddbb.gui.Performer;


public class SelectedListModel<T> extends Model implements List<T> {
	private int selected;
	private List<T> items;
	
	public SelectedListModel() {
		setToDefault();
	}
	
	public void setToDefault() {
		selected = -1;
		items = new Vector<T>();
	}
	
	public int getSelected() {
		return selected;
	}
	public void setSelected(int i) {
		selected = i;
		changed();
	}
	public ActionListener setSelectedAction(final int i) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelected(i);
			}
			
		};
	}

	public T getSelectedItem() {
		if (selected == -1) { return null; }
		return items.get(selected);
	}

	public void nextSelected() {
		if (selected == items.size()-1 ) { setSelected(0); }
		else { setSelected(selected+1); }
		changed();
	}

	public Performer nextSelected  = new Performer()  {
		public void actionPerformed(ActionEvent e) {
			nextSelected();
		}		
	};

	public ActionListener actionSetSelected(final int i) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelected(i);
			}		
		};
	}

	public void prevSelected() {
		if (selected == 0) { setSelected(items.size()-1); }
		else { setSelected(selected-1); }
		changed();
	}
	
	public Performer prevSelected = new Performer()  {
		public void actionPerformed(ActionEvent e) {
			prevSelected();
		}		
	};

	
	
	public void clear() {
		items.clear();
		selected = -1;
		changed();
	}
	public boolean retainAll(Collection<?> c) {
		Object origSel = getSelectedItem();
		boolean res = items.retainAll(c);
		if (res) { //changed
			selected = items.indexOf(origSel);
			changed();
		}
		return res;
	}
	public boolean remove(Object o) {
		Object origSel = getSelectedItem();
		boolean res = items.remove(o);
		if (res) { //changed
			selected = items.indexOf(origSel);
			changed();
		}
		return res;
	}
	public boolean removeAll(Collection<?> c) {
		Object origSel = getSelectedItem();
		boolean res = items.removeAll(c);
		if (res) { //changed
			selected = items.indexOf(origSel);
			changed();
		}
		return res;
	}
	public T remove(int index) {
		T res = items.remove(index);
		if (items.size()==0) { index = -1; }
		else if (index == selected) {
			if (index>0) { selected = index-1; }
			else         { selected = index+1; }
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
		Object origSel = getSelectedItem();
		items.add(index, element);
		selected = items.indexOf(origSel);
		if (selected == -1) { selected = 0; }
		changed();
	}
	public boolean add(T o) {
		boolean res = items.add(o);
		if (selected == -1) { selected = 0; }
		changed();
		return res;
	}
	public boolean addAll(Collection<? extends T> c) {
		boolean res = items.addAll(c);
		if (res) { //changed
			if (selected == -1) { selected = 0; }
			changed();
		}
		return res;
	}
	public boolean addAll(int index, Collection<? extends T> c) {
		Object origSel = getSelectedItem();
		boolean res = items.addAll(index, c);
		if (res) { //changed
			selected = items.indexOf(origSel);
			if (selected == -1) { selected = 0; }			
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
