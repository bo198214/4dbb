package ddddbb.gui;

import java.awt.event.ActionEvent;

import ddddbb.game.Opt;

public enum UIShownAction implements Performer {
	cam4dReset {
		public void actionPerformed(ActionEvent e) {
			Opt.scene.camera4d.setToDefault();
		}
	},
	cam3dReset {
		public void actionPerformed(ActionEvent e) {
			Opt.scene.camera3d.setToDefault();
		}
	},
	zoomIn {
		public void actionPerformed(ActionEvent e) {
			Opt.zoom.setDouble(Opt.zoom.getDouble()+0.1);
		}
	},
	zoomOut {
		public void actionPerformed(ActionEvent e) {
			Opt.zoom.setDouble(Opt.zoom.getDouble()-0.1);
		}		
	},
	nextSelected  {
		public void actionPerformed(ActionEvent e) {
			Opt.scene.compounds.nextSelected();
		}		
	},
	combineTouchingSelected {
		public void actionPerformed(ActionEvent e) {
			Opt.scene.combine();
		}		
	},
	prevSelected {
		public void actionPerformed(ActionEvent e) {
			Opt.scene.compounds.prevSelected();
		}		
	},
	showGoal  {
		public void actionPerformed(ActionEvent e) {
			Opt.showGoal.setSelected(!Opt.showGoal.isSelected());
		}
	},
	toggle3d4d {
		public void actionPerformed(ActionEvent e) {
			Opt.dim34.setInt((Opt.dim34.getInt()+1)%2);
		}
	}
	;

}
