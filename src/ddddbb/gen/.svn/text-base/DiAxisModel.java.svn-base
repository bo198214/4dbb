package ddddbb.gen;

import ddddbb.game.Opt;



public class DiAxisModel extends IntStringModel {
	
	public DiAxisModel(int axis1,int axis2) {
		super(axis12(axis1,axis2), new String[] {
			Opt.axisNames[0]+ Opt.axisNames[1],
			Opt.axisNames[0]+ Opt.axisNames[2],
			Opt.axisNames[0]+ Opt.axisNames[3],
			Opt.axisNames[1]+ Opt.axisNames[2],
			Opt.axisNames[1]+ Opt.axisNames[3],
			Opt.axisNames[2]+ Opt.axisNames[3],
			});
	}

	public int getAxis1() {
		switch (getInt()) {
		case 0: return 0;
		case 1: return 0;
		case 2: return 0;
		case 3: return 1;
		case 4: return 1;
		case 5: return 2;
		}		
		return 0;
	}
	
	public int getAxis2() {
		switch (getInt()) {
		case 0: return 1;
		case 1: return 2;
		case 2: return 3;
		case 3: return 2;
		case 4: return 3;
		case 5: return 3;
		}
		return 0;
	}
	
	public static int axis12(int axis1,int axis2) {
		switch (axis1) {
		case 0:
			switch (axis2) {
			case 1: return(0); 
			case 2: return(1);
			case 3: return(2);
			default: throw new RuntimeException();
			}
		case 1:
			switch (axis2) {
			case 2: return(3);
			case 3: return(4);
			default: throw new RuntimeException();
			}
		case 2:
			if (axis2==3) { return(5); }
			throw new RuntimeException();
		default:
			throw new RuntimeException();
		}
	}
	
	public void setAxis1(int axis1) {
		setInt(axis12(axis1,getAxis2()));
	}
	
	public void setAxis2(int axis2) {
		setInt(axis12(getAxis1(),axis2));
	}
	
	public void setAxis12(int axis1, int axis2) {
		setInt(axis12(axis1,axis2));
	}

}
