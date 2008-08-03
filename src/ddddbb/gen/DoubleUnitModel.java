package ddddbb.gen;

import javax.swing.SpinnerModel;
import javax.swing.event.ChangeListener;

public class DoubleUnitModel implements SpinnerModel {
	IntModel<? extends Unit> unitModel;
	DoubleModel valueModel;
	
	public DoubleUnitModel(DoubleModel v,IntModel<? extends Unit> u) {
		valueModel = v;
		unitModel = u;
	}
	
	public void setValue(Object _value) {
		valueModel.setValue(_value,unitModel.sel());
	}
	
	public Object getValue() {
		return valueModel.getValue(unitModel.sel());
	}

	public void addChangeListener(ChangeListener l) {
		valueModel.addChangeListener(l);
		unitModel.addChangeListener(l);
	}

	public Object getNextValue() {
		double v = ((Number)valueModel.getNextValue()).doubleValue();
		return v/unitModel.sel().unitFactor();
	}

	public Object getPreviousValue() {
		double v = ((Number)valueModel.getPreviousValue()).doubleValue();
		return v/unitModel.sel().unitFactor();
	}

	public void removeChangeListener(ChangeListener l) {
		valueModel.removeChangeListener(l);
		unitModel.removeChangeListener(l);
	}
}
