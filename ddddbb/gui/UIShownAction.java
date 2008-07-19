package ddddbb.gui;

import ddddbb.game.Opt;

public enum UIShownAction implements Performer {
	nextSelected  {
		public void perform() {
			Opt.scene.compounds.nextSelected();
		}		
	},
	combineTouchingSelected {
		public void perform() {
			Opt.scene.combine();
		}		
	},
	prevSelected {
		public void perform() {
			Opt.scene.compounds.prevSelected();
		}		
	},
	showGoal  {
		public void perform() {
			Opt.showGoal.setSelected(!Opt.showGoal.isSelected());
		}
	},
	toggle3d4d {
		public void perform() {
			Opt.dim34.setInt((Opt.dim34.getInt()+1)%2);
		}
	}
	;

}
