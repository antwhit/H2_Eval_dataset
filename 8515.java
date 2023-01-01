import java.util.*;
import java.io.Serializable;

/**
 *
 * @author me
 */
public class Profile implements Serializable {

    public String name = "";

    public String degree = "";

    public String major = "";

    private Hashtable areaToClassList = new Hashtable();

    private static final String CORE = "CORE";

    private static final String SUPPORT = "SUPPORT";

    private static final String ELECTIVE = "ELECTIVE";

    private static final String COMPLETED = "COMPLETED";

    /**
     *  Remove dupplicated classes across areas.
     *  (However, allow dupplicated classes in the same area.)
     * @param classId
     * @param area
     */
    public void removeClass(String classId, String area) {
        Enumeration e = areaToClassList.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            if (key.equals(area)) continue;
            ArrayList classList = (ArrayList) areaToClassList.get(key);
            if (classList.contains(classId)) {
                classList.remove(classId);
                return;
            }
        }
    }

    public void setClass(String classId, String area) {
        removeClass(classId, area);
        ArrayList classList;
        if (areaToClassList.containsKey(area)) {
            classList = (ArrayList) areaToClassList.get(area);
        } else {
            classList = new ArrayList();
            areaToClassList.put(area, classList);
        }
        classList.add(classId);
    }

    public void setTime(String time, String area) {
        ArrayList classList;
        if (areaToClassList.containsKey(area)) {
            classList = (ArrayList) areaToClassList.get(area);
            removeTime(area);
        } else {
            classList = new ArrayList();
        }
        areaToClassList.put(area, classList);
        classList.add(time);
    }

    public void removeTime(String area) {
        ArrayList classList;
        classList = (ArrayList) areaToClassList.get(area);
        classList.clear();
        return;
    }

    public String[] getClassByArea(String area) {
        if (!areaToClassList.containsKey(area)) {
            return new String[0];
        }
        ArrayList classList = (ArrayList) areaToClassList.get(area);
        Object[] os = classList.toArray();
        String[] retVal = new String[os.length];
        for (int i = 0; i < os.length; i++) retVal[i] = (String) os[i];
        return retVal;
    }

    public String[] getCompleted() {
        return getClassByArea(COMPLETED);
    }

    public String[] getElectives() {
        return getClassByArea(ELECTIVE);
    }

    public String[] getGEs() {
        ArrayList list = new ArrayList();
        Enumeration e = areaToClassList.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            if (key.startsWith("GE ")) {
                ArrayList hs = (ArrayList) areaToClassList.get(key);
                Object[] objs = hs.toArray();
                if (objs.length > 0) list.add(objs[0]);
            }
        }
        int s = list.size();
        String[] retVal = new String[s];
        for (int i = 0; i < s; i++) {
            retVal[i] = (String) list.get(i);
        }
        return retVal;
    }

    public String[] getSupports() {
        return getClassByArea(SUPPORT);
    }

    public String[] getCores() {
        return getClassByArea(CORE);
    }

    public void removeAllClasses() {
        areaToClassList = new Hashtable();
    }
}
