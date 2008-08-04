package ddddbb.gen;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ListDataListener;

/** This class represents a fixed length list (array) of type T, 
 * in which one element is changeably selected.
 * Also an array of associated names (String) is maintained.  
 *  
 * @author bo198214
 */
public class IntModel<T> extends Model implements ComboBoxModel, SelArray<T> {
	protected int selDefault;
	protected int sel = -1;
	
	protected T[] items;
	protected String[] names;
	protected Vector<Vector<AbstractButton>> buttons = new Vector<Vector<AbstractButton>>();
	protected boolean enabled = true;
	
//	public IntModel() {}
//	protected IntModel(int _value) {
//		value = _value;
//		defaultValue = value;
//	}
	
	protected IntModel() {}
	protected void init(int _value,String[] _names,T[] _items) {
		assert 0 <= _value && _value < _items.length;
		assert _names == null || _names.length == _items.length;
		selDefault = _value;
		sel = _value;
//		items = arr2Vec(_items);
		names = _names;
		items = _items;
		if (names==null) names = new String[items.length];
		for (int i=0;i<_items.length;i++) {
			if ( _names == null ) {
				names[i]=items[i].toString();
			}
			buttons.add(i,new Vector<AbstractButton>());
		}
//		if (_names == null) { names = namesOf(items); } 
//		else { names = arr2Vec(_names); }
	}
	
	public IntModel(T _selectedItem,T[] _values) {
		init(_selectedItem,_values);
	}
	protected void init(T _selectedItem,T[] _values) {
		init(indexOf(_selectedItem,_values),null,_values);
	}

	public IntModel(int i,T[] _values) {
		this(i,null,_values);
	}

	public IntModel(T[] _values) {
		this(0,null,_values);
	}
	
	public IntModel(int _value,String[] _names,T[] _items) {
		init(_value,_names,_items);
	}
	
	public IntModel(T initial, String[] _names, T[] _items) {
		init(initial,_names,_items);
	}
	protected void init(T initial, String[] _names, T[] _items) {
		int i=0;
		for (;i<_items.length;i++) {
			if (_items[i]==initial) {
				break;
			}
		}
		init(i,_names,_items);
	}
	
	public void setToDefault() {
		setSelInt(selDefault);
	}

//	public static <X> Vector<X> arr2Vec(X[] a) {
//		if (a==null) { return null; }
//		Vector<X> res = new Vector<X>();
//		for (int i=0;i<a.length;i++) {
//			res.add(i,a[i]);
//		}
//		return res;
//	}
			
//	public void setNames(String[] _names) {
//		names = arr2Vec(_names);
//	}
//	
//	public void setObjects(T[] _objects) {
//		items = arr2Vec(_objects);
//	}

	/* (non-Javadoc)
	 * @see ddddbb.gen.SelArray#selInt()
	 */
	public int selInt() {
		return sel;
	}

	/* (non-Javadoc)
	 * @see ddddbb.gen.SelArray#setSelInt(int)
	 */
	public void setSelInt(int i) {
		//if (i==value) { return; }
		int n=items.length;
		if (i<n) { sel = i; }
		else { sel = n-1; }
		if (i<0) { sel = 0; }
		changed();
	}
	
	public ActionListener nextAction  = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setSelInt(selInt()+1);
		}
	};
	
	public ActionListener prevAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setSelInt(selInt()-1);
		}
		
	};
	
	public ActionListener cyclicNextAction  = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setSelInt((selInt()+1)%items.length);
		}
	};
	
	public ActionListener cyclicPrevAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			setSelInt((selInt()-1)%items.length);
		}
		
	};

	/* (non-Javadoc)
	 * @see ddddbb.gen.SelArray#addButton(int, javax.swing.AbstractButton)
	 */
	public void addButton(final int index,AbstractButton b) {
		buttons.get(index).add(b);
		if (b.getText() == null | b.getText().length() == 0) {
			b.setText(names[index]);
		}
		if (index==sel) b.setSelected(true);
		else b.setSelected(false);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelInt(index);
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see ddddbb.gen.SelArray#addButton(T, javax.swing.AbstractButton)
	 */
	public void addButton(final T item,AbstractButton b) {
		int i;
		for (i=0;i<items.length;i++) if (items[i]==item) break;
		addButton(i,b);
	}
	
	public static <S> int indexOf(S item,S[] items) {
		int i;
		for (i=0;i<items.length;i++) if (item==items[i]) break;
		return i;
	}
	public static <S> int indexEqualOf(S item, S[] items) {
		int i;
		for (i=0;i<items.length;i++) if (item.equals(items[i])) break;
		return i;
	}
	
	
	/**
	 * This selects the first item with its name equal to object.toString().
	 */
	public void setSelectedItem(Object object) {
		setSelInt(indexEqualOf(object.toString(),names));
	}

	/* (non-Javadoc)
	 * @see ddddbb.gen.SelArray#setSel(T)
	 */
	public void setSel(T anObject) {
		setSelInt(indexOf(anObject,items));
	}
	
	/**
	 * Returns the name of the selected item.
	 */
	public String getSelectedItem() {
		if ( sel == -1 ) { return null; }
		return names[sel];
	}
	public int getSize() {
		return items.length;
	}
	public String getElementAt(int index) {
		if (sel == -1) { return null; }
		return names[index];
	}
	public void addListDataListener(ListDataListener l) {
		// items is fixed
	}
	public void removeListDataListener(ListDataListener l) {
		// items is fixed
	}
	
	/* (non-Javadoc)
	 * @see ddddbb.gen.SelArray#sel()
	 */
	public T sel() {
		if (sel == -1) { return null; }
		return items[sel];
	}
	
	public T[] getItems() {
		return items;
	}
	
	public String[] getNames() {
		return names;
	}
	
	
	public void addAsRadioButtonMenuItems(Container c) {
		JRadioButtonMenuItem[] menuItems = new JRadioButtonMenuItem[getSize()];
		for (int i=0;i<getSize();i++) {
			menuItems[i] = new JRadioButtonMenuItem(names[i]);
			addButton(i,menuItems[i]);
			c.add(menuItems[i]);
		}
	}

	public void addAsMenuItems(Container c) {
		JMenuItem[] menuItems = new JMenuItem[getSize()];
		for (int i=0;i<getSize();i++) {
			menuItems[i] = new JMenuItem(names[i]);
			addButton(i,menuItems[i]);
			c.add(menuItems[i]);
		}
	}

	public void addAsRadioButtons(Container c) {
		JRadioButton[] menuItems = new JRadioButton[getSize()];
		for (int i=0;i<getSize();i++) {
			menuItems[i] = new JRadioButton(names[i]);
			addButton(i,menuItems[i]);
			c.add(menuItems[i]);
		}
	}
	
	public void addAsCheckBoxMenuItems(Container c) {
		JCheckBoxMenuItem[] menuItems = new JCheckBoxMenuItem[getSize()];
		for (int i=0;i<getSize();i++) {
			menuItems[i] = new JCheckBoxMenuItem(names[i]);
			addButton(i,menuItems[i]);
			c.add(menuItems[i]);
		}
	}
	
	public JComboBox addAsComboBox(Container c) {
		JComboBox cb = new JComboBox(this); 
		c.add(cb);
		return cb;
	}
	
	public void addAsCards(final Container c, final CardLayout l) {
		for (int i=0;i<getSize();i++) {
			c.add((JPanel)items[i],names[i]);
		}
		new AChangeListener() {
			public void stateChanged() {
				l.show(c,names[sel]);			
			}			
		}.addTo(this);
	}
	
	public void setEnabled(boolean _enabled) {
		enabled = _enabled;
		propagateEnabled();
	}

	public void propagateEnabled() {
		for (Vector<AbstractButton> bs:buttons) for (AbstractButton b:bs) {
			b.setEnabled(enabled);
		}		
	}

	public void updateButtonStates() {
		for (Vector<AbstractButton> bs:buttons) for (AbstractButton b:bs) {
			b.setSelected(false); //triggers no action event
		}
		if (-1 < sel && sel < buttons.size()) { 
			//does not fire actionEvent
			for (AbstractButton b:buttons.get(sel)) {
				b.setSelected(true); //triggers no action event
			}
		}
		propagateEnabled();		
	}
	
	public void changed() {
		super.changed();
		updateButtonStates();
	}


//	protected String nameOf(T o) { 
//		return o.toString();
//	}
//	protected Vector<String> namesOf(Collection<T> c) {
//		Vector<String> res = new Vector<String>();
//		for (T t:c) {
//			res.add(nameOf(t));
//		}
//		return res;
//	}
	
}
