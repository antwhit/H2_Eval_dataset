import java.util.prefs.Preferences;

/**
 * Not part of the system.. I needed to run this to enable sound in the simulator
 * @author mdamiano
 *
 */
public class SetPreferences {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        String node = "com/tivo/hme/sdk/simulator";
        Preferences pref = Preferences.userRoot().node(node);
        pref.putBoolean("SOUND", true);
        pref.putBoolean("DEBUG", false);
        try {
            pref.sync();
            pref.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private static void showPrefs(String node) {
        Preferences pref = Preferences.userRoot().node(node);
        try {
            for (String child : pref.childrenNames()) {
                System.out.println(child);
            }
            for (String key : pref.keys()) {
                System.out.println(key + ":" + pref.get(key, ""));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
