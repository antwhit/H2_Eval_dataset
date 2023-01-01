import java.util.Vector;

public class DiscreetRange {

    public static boolean isMatch(Vector<Object> disObj, Object obj) {
        if (obj == null || disObj == null || disObj.size() == 0) return false;
        return disObj.contains(obj);
    }

    public static Vector<Object> matchedSet(Vector<Object> disObj, Vector<Object> inputObj, boolean match) {
        Vector<Object> subSet = new Vector<Object>();
        int i = 0;
        while (i < inputObj.size()) {
            if (DiscreetRange.isMatch(disObj, inputObj.elementAt(i)) == true && match == true) {
                subSet.add(inputObj.elementAt(i));
            }
            if (DiscreetRange.isMatch(disObj, inputObj.elementAt(i)) == false && match == false) {
                subSet.add(inputObj.elementAt(i));
            }
        }
        return subSet;
    }

    public static Vector<Object[]> matchedSetArray(Vector<Object> disObj, Vector<Object[]> inputObj, boolean match, int index) {
        Vector<Object[]> subSet = new Vector<Object[]>();
        int i = 0;
        while (i < inputObj.size()) {
            Object[] objArray = inputObj.elementAt(i);
            if (DiscreetRange.isMatch(disObj, objArray[index]) == true && match == true) {
                subSet.add(objArray);
            }
            if (DiscreetRange.isMatch(disObj, objArray[index]) == false && match == false) {
                subSet.add(objArray);
            }
        }
        return subSet;
    }

    public static Vector<String> tokenizeText(String text, String token) {
        if (token == null) return null;
        String[] tokenA = text.trim().split(token);
        int i = 0;
        Vector<String> vec = new Vector<String>();
        while (i < tokenA.length) vec.add(tokenA[i++]);
        return vec;
    }
}
