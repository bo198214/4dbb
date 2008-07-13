package ddddbb.gen;

import javax.swing.BoundedRangeModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeListener;


public class DoubleModel extends Model implements SpinnerModel {

	
	protected double defaultValue;
	protected double value;
	protected double delta = 1;
	protected double min = Double.MIN_VALUE;
	protected double max = Double.MAX_VALUE;
	protected int iMax = 100;
	protected int iMin = 0;
	protected int iValue;

	public DoubleModel(double _value) {
		defaultValue = _value;
		value = _value;
	}
	public DoubleModel(double v,Unit u) {
		this(v*u.unitFactor());
	}
	public DoubleModel(double _value,double _delta) {
		defaultValue = _value;
		value = _value;
		delta = _delta;
	}
	public DoubleModel(double _value,double _delta, Unit u) {
		this(_value*u.unitFactor(),_delta*u.unitFactor());
	}
	public DoubleModel(double _value,double _min,double _max) {
		defaultValue = _value;
		value = _value;
		min = _min;
		max = _max;
	}
	public DoubleModel(double _value,double _min,double _max,Unit u) {
		this(_value*u.unitFactor(),_min*u.unitFactor(),_max*u.unitFactor());
	}
	
	protected void setIValue(int x) {
		double f = (iMax-iMin)/(max-min);
		setDouble((x-iMin)/f+min);		
	}
	protected int getIValue() {
		double f = (iMax-iMin)/(max-min);
		return (int)Math.round((getDouble()-min)*f+iMin);
	}
	
	public BoundedRangeModel boundedRange = new BoundedRangeModel() {

		public int getValue() {
			return getIValue();
		}

		public void setValue(int newValue) {
			setIValue(newValue);
		}


		public int getMaximum() {
			return iMax;
		}
		public int getMinimum() {
			return iMin;
		}

		public void setMinimum(int newMinimum) {
			//fixed value
		}

		public void setMaximum(int newMaximum) {
			//fixed value
		}


		public void setValueIsAdjusting(boolean b) {
			//not used for our purposes
		}

		public boolean getValueIsAdjusting() {
			return false;
		}

		public int getExtent() {
			return 0;
		}

		public void setExtent(int newExtent) {
			//not used for our purposes
		}

		public void setRangeProperties(int _value, int extent, int _min, int _max, boolean adjusting) {
			iValue = _value;
			iMin = _min;
			iMax = _max;
		}

		public void addChangeListener(ChangeListener x) {
			DoubleModel.this.addChangeListener(x);
		}

		public void removeChangeListener(ChangeListener x) {
			DoubleModel.this.removeChangeListener(x);
		}};
	
	public void setToDefault() {
		setDouble(defaultValue);
	}
	
	public double getDouble() {
		return value;
	}
	public void setDouble(double _value) {
		value = _value;
		changed();
	}
	
	public double getDouble(Unit u) {
		return value/u.unitFactor();
	}
	public void setDouble(double v, Unit u) {
		value = v*u.unitFactor();
		changed();
	}

//	public Object getValue() {
//		return new Double(value);
//	}	
	public Object getNextValue() {
		return new Double(value+delta);
	}
	public Object getPreviousValue() {
		return new Double(value-delta);
	}
	
	public void setValue(Object _value) {
		setDouble(((Number)_value).doubleValue());
	}
	public void setValue(Object _value, Unit u) {
		setDouble(((Number)_value).doubleValue(),u);
	}
	public Object getValue() {
		return new Double(getDouble());
	}
	public Object getValue(Unit u) {
		return new Double(getDouble(u));
	}
	
}
