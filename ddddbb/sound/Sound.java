package ddddbb.sound;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ddddbb.gen.BoolModel;

public class Sound {
	public final SoundFnum MOVINGCOMPOUND = new SoundFnum("movingCompound.wav");
	public final SoundFnum ROTATINGCOMPOUND = new SoundFnum("rotatingCompound.wav");
	public final SoundFnum BARRINGCOMPOUND = new SoundFnum("barringCompound.wav");
	public final SoundFnum SNAPPINGCOMPOUNDS = new SoundFnum("snappingCompounds.wav");
	public final SoundFnum GOALREACHED = new SoundFnum("goalReached.wav");
	public final SoundFnum GOALMISSED = new SoundFnum("goalMissed.wav");
	public final SoundFnum SHOWGOAL = new SoundFnum("showGoal.wav");
	
	public final BoolModel soundOn = new BoolModel(true,"Sound on");
	
	public class SoundFnum {
		public Clip clip;

		SoundFnum(String filename) {
			try {
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
			if (clip != null && soundOn.isSelected()) {
				//				clip.stop();
				clip.setFramePosition(0);
				clip.start();
			}
		}
		public void play2end() {
			if (clip != null && soundOn.isSelected()) {
				clip.setFramePosition(0);
				clip.start();
				clip.drain();
			}

		}
	}
}
