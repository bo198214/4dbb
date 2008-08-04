package ddddbb.gen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractButton;

public class DiIntModel<T> extends IntModel<T> {
	protected int sel2Default;
	protected int sel2;
	

	public DiIntModel(T item, T item2, T[] items) {
		init(indexOf(item,items),indexOf(item2,items),null,items);
	}
	/**
	 * value < value2
	 */
	protected void init(int value, int value2, String[] names, T[] items) {
		assert 0 <= value2;
		assert value2 < items.length;
		assert value < value2;
		sel2Default = value2;
		this.sel2 = value2;
		init(value,names,items);
	}
	
	public int getInt1() {
		return sel;
	}
	
	public int getInt2() {
		return sel2;
	}
	
	public T sel1() {
		return sel();
	}
	
	public T sel2() {
		if (sel2 == -1) { return null; }
		return items[sel2];
	}

	public void setInt1(int i) {
		//dont do anything if someone wants to set value to value2
		if (i==sel2) return; 
		setSelInt(i);
	}
	public void setInt2(int i) {
		//dont do anything if someone wants to set value2 to value
		if (i==sel) return; 
		int n=items.length;
		if (i<n) { sel2 = i; }
		else { sel2 = n-1; }
		if (i<0) { sel2 = 0; }
		changed();
	}

	public void addButton(final int index,final AbstractButton b) {
		buttons.get(index).add(b);
		if (b.getText() == null | b.getText().length() == 0) {
			b.setText(names[index]);
		}
		if (index==sel||index==sel2) b.setSelected(true);
		else b.setSelected(false);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index<sel) {
					setInt1(index);
				}
				else if (index==sel) {
					if (sel+1==sel2) {
						if (sel==0) {
							setInt2(sel2+1);
							setInt1(sel+1);
						}
						else setInt1(sel-1);
						//b.setSelected(true);//do nothing, undo the toggle
					}
					else setInt1(sel+1);
				}
				else if (index > sel && index < sel2) {
					if (index-sel <= sel2-index) {
						setInt1(index);
					}
					else {
						setInt2(index);
					}
				}
				else if (index==sel2) {
					if (sel2-1==sel) {
						if (sel2==items.length-1) {
							setInt1(sel-1);
							setInt2(sel2-1);
						}
						else setInt2(sel2+1);
						//b.setSelected(true);//do nothing, undo the toggle
					}
					else setInt2(sel2-1);
				}
				else if (index>sel2) {
					setInt2(index);
				}
			}					
		});
	}

	public void updateButtonStates() {
		for (Vector<AbstractButton> bs:buttons) for (AbstractButton b:bs) {
			b.setSelected(false); //triggers no action event
		}
		if (-1 < sel && sel < buttons.size()) { 
			for (AbstractButton b:buttons.get(sel)) {
				b.setSelected(true); //triggers no action event
			}
			for (AbstractButton b:buttons.get(sel2)) {
				b.setSelected(true);
			}
		}
		propagateEnabled();		
	}
	

	public void setToDefault() {
		sel = selDefault;
		sel2 = sel2Default;
	}

}
