package ddddbb.sound;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ddddbb.game.Settings;

public class SoundEnum {
	public static final Sound MOVINGCOMPOUND = new Sound("3060_SpeedY_UFOblip.wav",false);
	public static final Sound ROTATINGCOMPOUND = new Sound("rotatingCompound.wav",false);
	public static final Sound BARRINGCOMPOUND = new Sound("barringCompound.wav",false);
	public static final Sound SNAPPINGCOMPOUNDS = new Sound("39762_altemark_cmb5.wav",true);
//	public static final Sound SNAPPINGCOMPOUNDS = new Sound("38500_Corsica_S_blip12.flac",true);
	public static final Sound GOALREACHED = new Sound("goalReached.wav",true);
	public static final Sound GOALMISSED = new Sound("goalMissed.wav",true);
	public static final Sound SHOWGOAL = new Sound("showGoal.wav",false);
	public static final Sound MOUSEOVER = new Sound("39771_altemark_fx17.wav",false);
	
	public static class Sound {
		//public final AudioClip clip;
		public Clip clip;
		private volatile boolean isPlaying = true;
		private final boolean wait2end;

		Sound(String filename,boolean _wait2end) {
			wait2end = _wait2end;
			try {
				
				InputStream istream = getClass().getResourceAsStream(filename);
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(istream);
					clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					if (wait2end) {
						clip.addLineListener(new LineListener() {
							@Override
							public void update(LineEvent event) {
								if (event.getType() == LineEvent.Type.STOP) {
									isPlaying = false;
								}
//								if (event.getType() == LineEvent.Type.START) {
//									isPlaying = true;
//								}
							}});
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
			if (clip != null && Settings.soundOn.isSelected()) {
				//				clip.stop();
				clip.setFramePosition(0);
				clip.start();
			}
			if (wait2end) {
				while (isPlaying) {}
			}
			isPlaying = true; //just to reset the value, otherwise there is a time gap between start and while
		}
//		public void play2end() {
//			if (clip != null && Settings.soundOn.isSelected()) {
//				clip.setFramePosition(0);
//				clip.start();
//				clip.drain();
//			}
//		}
	}
}
