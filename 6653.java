import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;
import java.util.Vector;

public class Config {

    private static PersistentObject store = PersistentStore.getPersistentObject(0xbd25849731babbd9L);

    private static Vector settings = new Vector();

    private static int MaxSettings = 8;

    public static boolean hasrun = false;

    public static boolean imperial = false;

    public static boolean backlight = false;

    public static boolean showfound = false;

    public static boolean showarchived = false;

    public static boolean showowned = false;

    public static String UserName = "";

    public static int lastloc = -1;

    public Config() {
    }

    public static void load() {
        if (store.getContents() != null) {
            try {
                Object obj = store.getContents();
                if (obj != null) {
                    settings = (Vector) obj;
                    loadSettings();
                }
            } catch (Exception e) {
                commit();
            }
        } else commit();
    }

    private static void updateBool(boolean val, int i) {
        if (val) settings.setElementAt("true", i); else settings.setElementAt(null, i);
    }

    private static void loadSettings() {
        if (settings.elementAt(0) != null) hasrun = true;
        if (settings.elementAt(1) != null) imperial = true;
        if (settings.elementAt(2) != null) backlight = true;
        if (settings.elementAt(3) != null) showfound = true;
        if (settings.elementAt(4) != null) showarchived = true;
        if (settings.elementAt(5) != null) showowned = true;
        UserName = (String) settings.elementAt(6);
        lastloc = Integer.parseInt((String) settings.elementAt(7));
    }

    public static void commit() {
        while (settings.size() < MaxSettings) settings.addElement(null);
        updateBool(hasrun, 0);
        updateBool(imperial, 1);
        updateBool(backlight, 2);
        updateBool(showfound, 3);
        updateBool(showarchived, 4);
        updateBool(showowned, 5);
        settings.setElementAt(UserName, 6);
        settings.setElementAt("" + lastloc, 7);
        store.setContents(settings);
        store.commit();
    }

    public static String getString(int i, String defStr) {
        String tmp = null;
        try {
            Object o = settings.elementAt(i);
            if (o != null) tmp = (String) o;
        } catch (Exception e) {
        }
        if (tmp == null) {
            tmp = defStr;
            settings.setElementAt(tmp, i);
            commit();
        }
        return tmp;
    }

    public static boolean getBool(int i, boolean defVal) {
        String tmp = getString(i, null);
        if (tmp == null) return defVal;
        if (tmp.equals("true")) return true;
        return false;
    }
}
