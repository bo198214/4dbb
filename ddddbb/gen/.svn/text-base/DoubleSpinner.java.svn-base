package ddddbb.gen;

import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class DoubleSpinner extends JSpinner {
	private static final long serialVersionUID = -3019586499647562767L;

	public DoubleSpinner(SpinnerModel sm) {
		super(sm);
		setEditor(new MyNumberEditor(this));
	}
	public DoubleSpinner(SpinnerModel sm, DecimalFormat f) {
		super(sm);
		setEditor(new MyNumberEditor(this,f));
	}
	
	public static class MyNumberEditor extends JSpinner.DefaultEditor {
		private static final long serialVersionUID = 7075682671710400052L;

		public MyNumberEditor(JSpinner spinner) {
			super(spinner);
			initialize(spinner,new DecimalFormat());
		}

		public MyNumberEditor(JSpinner spinner, DecimalFormat format) {
			super(spinner);
			initialize(spinner, format);
		}
		
		private void initialize(JSpinner spinner, DecimalFormat format) {
			SpinnerModel model = spinner.getModel();
			NumberFormatter formatter = new NumberFormatter(format);
			DefaultFormatterFactory factory = new DefaultFormatterFactory(
					formatter);
			JFormattedTextField ftf = getTextField();
			ftf.setEditable(true);
			ftf.setFormatterFactory(factory);
			ftf.setHorizontalAlignment(SwingConstants.RIGHT);
			
			/* TBD - initializing the column width of the text field
			 * is imprecise and doing it here is tricky because 
			 * the developer may configure the formatter later.
			 */
			try {
				String valString = formatter.valueToString(model.getNextValue());
				ftf.setColumns(valString.length());
			}
			catch (ParseException e) {
				// TBD should throw a chained error here
			}
		}


	}

}
