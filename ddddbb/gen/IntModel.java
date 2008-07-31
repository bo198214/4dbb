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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListDataListener;


public class IntModel<T> extends Model implements ComboBoxModel {
	protected int defaultValue;
	protected int value = -1;
	
	protected Vector<T> items = new Vector<T>();
	protected Vector<String> names = new Vector<String>();
	protected Vector<Vector<AbstractButton>> buttons = new Vector<Vector<AbstractButton>>();
	protected boolean enabled = true;
	
//	public IntModel() {}
//	protected IntModel(int _value) {
//		value = _value;
//		defaultValue = value;
//	}
	
	private static int obj2int(Object obj,Object[] objs) {
		int i=0;
		for (;i<objs.length;i++) {
			if ( obj == objs[i] ) { break; }
		}		
		return i;
	}
	
	protected IntModel() {}
	protected void init(int _value,String[] _names,T[] _items) {
		assert 0 <= _value && _value < _items.length;
		assert _names == null || _names.length == _items.length;
		value = _value;
//		items = arr2Vec(_items);
		for (int i=0;i<_items.length;i++) {
			items.add(i,_items[i]);
			if ( _names == null ) {
				names.add(i,_items[i].toString());
			}
			else {
				names.add(i,_names[i]);
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
		init(obj2int(_selectedItem,_values),null,_values);
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
		setInt(defaultValue);
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

	public int getInt() {
		return value;
	}

	public void setInt(int i) {
		//if (i==value) { return; }
		int n=items.size();
		if (i<n) { value = i; }
		else { value = n-1; }
		if (i<0) { value = 0; }
		changed();
	}
	
	public ActionListener actionNextRot  = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setInt((getInt()+1)%items.size());
		}
	};

	private class MyActionListener implements ActionListener {
		private int i;
		public MyActionListener(int _i) {
			i=_i;
		}
		public void actionPerformed(ActionEvent e) {
			setInt(i);
		}		
	}
	
	public void addButton(int index,AbstractButton b) {
		buttons.get(index).add(b);
		if (b.getText() == null | b.getText().length() == 0) {
			b.setText(names.elementAt(index));
		}
		b.addActionListener(new MyActionListener(index));
		updateButtonStates();
	}
	
	public void setSelectedItem(Object anItem) {
		assert (anItem instanceof String);
		setInt(getItems().indexOf(anItem));
	}

	public void setSelectedName(String name) {
		setInt(getNames().indexOf(name));
	}
	
	public void setSelectedObject(T anObject) {
		setInt(getObjects().indexOf(anObject));
	}
	
	public String getSelectedItem() {
		if ( value == -1 ) { return null; }
		return getItems().get(value);
	}
	public int getSize() {
		return getItems().size();
	}
	public String getElementAt(int index) {
		return getItems().get(index);
	}
	public void addListDataListener(ListDataListener l) {
		// items is fixed
	}
	public void removeListDataListener(ListDataListener l) {
		// items is fixed
	}
	
	public String getName(int i) {
		if (value == -1) { return null; }
		return names.get(i);
	}
	
	public T getSelectedObject() {
		if (value == -1) { return null; }
		return items.get(value);
	}
	
	public String getSelectedName() {
		if (value == -1) { return null; }
		return names.get(value);
	}
	
	public Vector<String> getItems() {
		return names;
	}
	
	public Vector<T> getObjects() {
		return items;
	}
	
	public Vector<String> getNames() {
		return names;
	}
	
	
	public void addAsRadioButtonMenuItems(Container c) {
		JRadioButtonMenuItem[] menuItems = new JRadioButtonMenuItem[getSize()];
		for (int i=0;i<getSize();i++) {
			menuItems[i] = new JRadioButtonMenuItem(getName(i));
			addButton(i,menuItems[i]);
			c.add(menuItems[i]);
		}
	}

	public void addAsMenuItems(Container c) {
		JMenuItem[] menuItems = new JMenuItem[getSize()];
		for (int i=0;i<getSize();i++) {
			menuItems[i] = new JMenuItem(getName(i));
			addButton(i,menuItems[i]);
			c.add(menuItems[i]);
		}
	}

	public void addAsRadioButtons(Container c) {
		JRadioButton[] menuItems = new JRadioButton[getSize()];
		for (int i=0;i<getSize();i++) {
			menuItems[i] = new JRadioButton(getName(i));
			addButton(i,menuItems[i]);
			c.add(menuItems[i]);
		}
	}
	
	public void addAsCheckBoxMenuItems(Container c) {
		JCheckBoxMenuItem[] menuItems = new JCheckBoxMenuItem[getSize()];
		for (int i=0;i<getSize();i++) {
			menuItems[i] = new JCheckBoxMenuItem(getName(i));
			addButton(i,menuItems[i]);
			c.add(menuItems[i]);
		}
	}
	
	public JComboBox addAsComboBox(Container c) {
		JComboBox cb = new JComboBox(this); 
		c.add(cb);
		return cb;
	}
	
	public class CardListener implements ChangeListener {
		Container container;
		CardLayout layout;
		CardListener(Container c,CardLayout l) {
			container=c;
			layout=l;
		}

		public void stateChanged(ChangeEvent e) {
			layout.show(container,getSelectedName());			
		}
		
	}
	
	public void addAsCards(Container c, CardLayout l) {
		for (int i=0;i<getSize();i++) {
			c.add((JPanel)getObjects().get(i),names.get(i));
		}
		addChangeListener(new CardListener(c,l));
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
		if (-1 < value && value < buttons.size()) { 
			//does not fire actionEvent
			for (AbstractButton b:buttons.get(value)) {
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
