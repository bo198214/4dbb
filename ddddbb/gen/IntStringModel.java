package ddddbb.gen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ddddbb.gui.Performer;


public class IntStringModel extends IntModel<String> {

	public IntStringModel(int _value,String[] _names) {
		super(_value,_names,_names);
	}

//	public IntStringModel(int _value) {
//		super(_value);
//	}
	
//	public IntStringModel(int _value,String[] _names,Object obj) {
//		this(_value,_names);
//		assert obj == null;
//	}

}
