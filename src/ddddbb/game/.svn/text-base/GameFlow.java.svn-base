package ddddbb.game;

import ddddbb.game.Opt.GameStatus;
import ddddbb.gen.IntModel;
import ddddbb.gen.MyChangeListener;
import ddddbb.sound.Sound;

public class GameFlow {
	protected Scene scene;
	protected IntModel<GameStatus> gameStatus;
	protected IntModel<Objectives> objectives;
	GameFlow(Scene _scene, IntModel<GameStatus> _gameStatus,IntModel<Objectives> _objectives) {
		scene = _scene;
		gameStatus = _gameStatus;
		objectives = _objectives;
		objectives.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				scene.changeObjective(objectives.getSelectedObject());
				
			}});
		gameStatus = _gameStatus;
		gameStatus.addChangeListener(new MyChangeListener() {
			public void stateChanged() {
				
				if (gameStatus.getSelectedObject() == GameStatus.REACHED) {
					Opt.viewScreen.repaint(); //why does this not work?
					ddddbb.sound.Sound.GOALREACHED.play2end();
					if (objectives.getInt() < objectives.getSize()-1) { //before last level
						objectives.setInt(objectives.getInt()+1);
										
					}
					if (objectives.getInt() == objectives.getSize()-1) {
						//TODO: last level completed congratulations
					}
					
				}
				if (gameStatus.getSelectedObject() == GameStatus.MISSED) {
					Sound.GOALMISSED.play2end();
					objectives.setInt(objectives.getInt());
				}
			}
		
		});
		

	}
}
