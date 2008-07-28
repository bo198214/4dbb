package ddddbb.sound;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ddddbb.game.Opt;

public enum Sound {
	MOVINGCOMPOUND("movingCompound.wav"),
	ROTATINGCOMPOUND("rotatingCompound.wav"),
	BARRINGCOMPOUND("barringCompound.wav"),
	SNAPPINGCOMPOUNDS("snappingCompounds.wav"),
	GOALREACHED("goalReached.wav"),
	GOALMISSED("goalMissed.wav"),
	SHOWGOAL("showGoal.wav"),
	;
	public static Line line;
	public Clip clip;
	Sound(String filename) { try {
		InputStream istream = getClass().getResourceAsStream(filename);
		if (istream == null) { clip = null; }
		else {
		  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(istream);
		  clip = AudioSystem.getClip();
		  clip.open(audioInputStream);
		}
	} 
	catch (UnsupportedAudioFileException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();			
	} 
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void play() {
		if (clip != null && Opt.soundOn.isSelected()) {
//			clip.stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}
	public void play2end() {
		if (clip != null && Opt.soundOn.isSelected()) {
			clip.setFramePosition(0);
			clip.start();
			clip.drain();
		}
		
	}
}
