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
	public Vector<AbstractButton> trueButtons = new Vector<AbstractButton>();
	public Vector<AbstractButton> falseButtons = new Vector<AbstractButton>();

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
		b.setSelected(value);
	}
	
	public void addTrueButton(AbstractButton b) {
		trueButtons.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value = true;
				changed();
			}
		});
		b.setSelected(value);		
	}
	
	public void addFalseButton(AbstractButton b) {
		falseButtons.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value = false;
				changed();
			}
		});
		b.setSelected(!value);
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
	
	protected void updateButtons() {
		for (AbstractButton b:buttons) {
			b.setSelected(value);
			b.setEnabled(enabled);
		}
		for (AbstractButton b:trueButtons) {
			b.setSelected(value);
			b.setEnabled(enabled);
		}
		for (AbstractButton b:falseButtons) {
			b.setSelected(!value);
			b.setEnabled(enabled);
		}		
	}
	
	public void changed() {
		super.changed();
		updateButtons();
	}

	public ActionListener toggleAction  = new ActionListener()  {
		public void actionPerformed(ActionEvent e) {
			setSelected(!isSelected());
		}
	};

}
