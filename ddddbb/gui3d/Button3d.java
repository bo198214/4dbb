package ddddbb.gui3d;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import ddddbb.game.Opt;

public class Button3d extends Component {
	int w;
	int h;
	final static double wd = 1;
	final static double hd = 1;
	
	public void updateSize() {
		w = (int)(wd/Opt.xdpcm.getDouble());
		h = (int)(hd/Opt.ydpcm.getDouble());		
	}
	
	public Dimension getPreferredSize() {
		updateSize();
		return new Dimension(w,h);
	}

	public void addActionListener(ActionListener l) {
		
	}
	
	
	private static final long serialVersionUID = -7623676134203906851L;
}
