package ddddbb.gen;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JToggleButton;


public class BoolModel extends Model {
	private static final long serialVersionUID = -5154608494749418813L;
	
	public String name;
	public boolean value;
	public boolean defaultValue;
	public boolean enabled = true;
	
	public Vector<AbstractButton> buttons = new Vector<AbstractButton>();

	public BoolModel(boolean _defaultValue,String _name) {
		defaultValue = _defaultValue;
		value = defaultValue;
		name = _name;
	}
	
	public boolean isSelected() {
		return value;
	}
	
	public void setSelected(boolean _value) {
		value = _value;
		changed();
	}
	
	public void addButton(AbstractButton b) {
		buttons.add(b);
		if (b.getText().equals("")) { b.setText(name); }
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value = ((AbstractButton)e.getSource()).isSelected();
				changed();
			}});
		changed();
	}
	
	public void addAsCheckBoxMenuItem(Container c) {
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(name);
		addButton(item);
		c.add(item);
	}
	
	public void addAsJToggleButton(Container c) {
		JToggleButton item = new JToggleButton(name);
		addButton(item);
		c.add(item);
	}

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean _enabled) {
		enabled = _enabled;
		changed();
	}
	
	public void setToDefault() {
		setSelected(defaultValue);
	}
	
	public void changed() {
		super.changed();
		for (AbstractButton b:buttons) {
			b.setSelected(value);
			b.setEnabled(enabled);
		}
	}
}
