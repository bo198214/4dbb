package ddddbb.game;

import java.awt.Container;
import java.awt.Dimension;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import ddddbb.gen.BoolModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;

public class PersistentPreferences {
	public static class User extends PersistentPreferences {
		public User(Settings ss,Container window) {
			super(ss, window,
					"4dbb",
					new String[] {
					"barEyeFocusDelta",
					"eyesDistHalf",
					"screenEyeDist",
					"brightness",
					"xdpcm",
					"ydpcm",
					"resolutionUnit",
					"lengthUnit",
					"mouseRotSens",
					"mouseTransSens",
					"viewType",
					"orientation3d",
					"orientation4d",
					"perspective",
					"drawTetrahedral",
					"soundOn",
					
			});
		}
	}
	
	private static String widthKey = "applicationWidth";
	private static String heightKey = "applicationHeight";
	
	public final String node;
	public final String[] keys;
		
	private final Settings ss;
	private final Container window;

	protected PersistentPreferences(Settings ss, Container window, String node, String[] keys) {
		this.ss = ss;
		this.window = window;
		this.node = node;
		this.keys = keys;
		checkPrefKeys(ss, keys);
	}

	public static void checkPrefKeys(Settings ss, String[] keys) {
		for (int i=0;i<keys.length;i++) {
			try {
				@SuppressWarnings("unused")
				Object setting = ss.getClass().getField(keys[i]).get(ss);
				return;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}			
		}
		assert false;
	}
	
	private void put(Preferences prefs, String field) {
		Object setting;
		try {
			setting = ss.getClass().getField(field).get(ss);
			if (setting instanceof DoubleModel) {
				DoubleModel dm = (DoubleModel) setting;
				prefs.put(field, Double.toString(dm.getDouble()));
				//if (Main.debug.isSelected()) System.out.println(field +":" +prefs.get(field, "undefined"));
			}
			if (setting instanceof BoolModel) {
				BoolModel bm = (BoolModel) setting;
				prefs.put(field, Boolean.toString(bm.isSelected()));
			}
			if (setting instanceof IntModel) {
				IntModel im = (IntModel) setting;
				prefs.put(field, Integer.toString(im.selInt()));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	private void get(Preferences prefs, String field) {
		Object setting;
		try {
			setting = ss.getClass().getField(field).get(ss);
			if (setting instanceof DoubleModel) {
				DoubleModel dm = (DoubleModel) setting;
				double d = Double.parseDouble(prefs.get(field,Double.toString(dm.getDouble())));
				dm.setDouble(d);
			}
			if (setting instanceof BoolModel) {
				BoolModel bm = (BoolModel) setting;
				boolean b = Boolean.parseBoolean(prefs.get(field, Boolean.toString(bm.isSelected())));
				bm.setSelected(b);
			}
			if (setting instanceof IntModel) {
				IntModel im = (IntModel) setting;
				int i = Integer.parseInt(prefs.get(field, Integer.toString(im.selInt())));
				im.setSelInt(i);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	public void save() throws BackingStoreException {
		Preferences prefs = Preferences.userRoot().node(node);
		for (int i=0;i<keys.length;i++) {
			put(prefs, keys[i]);
		}
		prefs.put(widthKey,Integer.toString(window.getWidth()));
		prefs.put(heightKey,Integer.toString(window.getHeight()));
		prefs.flush();
	}
	
	public void load() {
		Preferences prefs = Preferences.userRoot().node(node);
		for (int i=0;i<keys.length;i++) {
			get(prefs, keys[i]);
		}
		int w = Integer.parseInt(prefs.get(widthKey, Integer.toString(Settings.defaultWidth)));
		int h = Integer.parseInt(prefs.get(heightKey, Integer.toString(Settings.defaultHeight)));
		window.setPreferredSize(new Dimension(w,h));
	}
	
//	public static void main(String[] args) throws BackingStoreException {
//		Settings ss = new Settings();
//		assert ss != null;
//		PersistentPreferences prefs = new PersistentPreferences.Screen(ss);
//		
//		prefs.load();
//	}
}
