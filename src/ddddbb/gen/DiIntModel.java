package ddddbb.gen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractButton;

public class DiIntModel<T> extends IntModel<T> {
	protected int defaultValue2;
	protected int value2;
	

	public DiIntModel(T val, T val2, T[] items) {
		init(obj2int(val,items),obj2int(val2,items),null,items);
	}
	/**
	 * value < value2
	 */
	protected void init(int value, int value2, String[] names, T[] items) {
		assert 0 <= value2;
		assert value2 < items.length;
		assert value < value2;
		defaultValue2 = value2;
		this.value2 = value2;
		init(value,names,items);
	}
	
	public int getInt1() {
		return value;
	}
	
	public int getInt2() {
		return value2;
	}
	
	public T sel1() {
		return sel();
	}
	
	public T sel2() {
		if (value2 == -1) { return null; }
		return items.get(value2);
	}

	public void setInt1(int i) {
		//dont do anything if someone wants to set value to value2
		if (i==value2) return; 
		setInt(i);
	}
	public void setInt2(int i) {
		//dont do anything if someone wants to set value2 to value
		if (i==value) return; 
		int n=items.size();
		if (i<n) { value2 = i; }
		else { value2 = n-1; }
		if (i<0) { value2 = 0; }
		changed();
	}

	public void addButton(final int index,final AbstractButton b) {
		buttons.get(index).add(b);
		if (b.getText() == null | b.getText().length() == 0) {
			b.setText(names.elementAt(index));
		}
		if (index==value||index==value2) b.setSelected(true);
		else b.setSelected(false);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index<value) {
					setInt1(index);
				}
				else if (index==value) {
					if (value+1==value2) {
						if (value==0) {
							setInt2(value2+1);
							setInt1(value+1);
						}
						else setInt1(value-1);
						//b.setSelected(true);//do nothing, undo the toggle
					}
					else setInt1(value+1);
				}
				else if (index > value && index < value2) {
					if (index-value <= value2-index) {
						setInt1(index);
					}
					else {
						setInt2(index);
					}
				}
				else if (index==value2) {
					if (value2-1==value) {
						if (value2==items.size()-1) {
							setInt1(value-1);
							setInt2(value2-1);
						}
						else setInt2(value2+1);
						//b.setSelected(true);//do nothing, undo the toggle
					}
					else setInt2(value2-1);
				}
				else if (index>value2) {
					setInt2(index);
				}
			}					
		});
	}

	public void updateButtonStates() {
		for (Vector<AbstractButton> bs:buttons) for (AbstractButton b:bs) {
			b.setSelected(false); //triggers no action event
		}
		if (-1 < value && value < buttons.size()) { 
			for (AbstractButton b:buttons.get(value)) {
				b.setSelected(true); //triggers no action event
			}
			for (AbstractButton b:buttons.get(value2)) {
				b.setSelected(true);
			}
		}
		propagateEnabled();		
	}
	

	public void setToDefault() {
		value = defaultValue;
		value2 = defaultValue2;
	}

}
