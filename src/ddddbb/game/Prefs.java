package ddddbb.game;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import ddddbb.gen.BoolModel;
import ddddbb.gen.DoubleModel;
import ddddbb.gen.IntModel;

public class Prefs {
	public static class Screen extends Prefs {
		public Screen(Settings ss) {
			super(ss,
					"4dbb/screen",
					new String[] {
					"barEyeFocusDelta",
					"eyesDistHalf",
					"screenEyeDist",
					"brightness",
					"xdpcm",
					"ydpcm",
					"resolutionUnit",
					"lengthUnit"
			});
		}
	}
	
	
	public final String node;
	public final String[] keys;
		
	private final Settings ss;

	protected Prefs(Settings ss, String node, String[] keys) {
		this.ss = ss;
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
		prefs.flush();
	}
	
	public void load() {
		Preferences prefs = Preferences.userRoot().node(node);
		for (int i=0;i<keys.length;i++) {
			get(prefs, keys[i]);
		}
	}
	
	public static void main(String[] args) throws BackingStoreException {
		Settings ss = new Settings();
		assert ss != null;
		Prefs prefs = new Prefs.Screen(ss);
		
		prefs.load();
	}
}
