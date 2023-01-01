import java.util.Vector;
import java.util.Hashtable;

public class SelectCompleter extends AbstractCompleter {

    public String complete(String text, int state, String line, int pos) {
        String tmp = null;
        if (countMatches(line.substring(0, pos), "from") == 0) {
            return selectFields(text, state, line, pos);
        }
        if (countMatches(line.substring(0, pos), "where") == 0) {
            return targetTables(text, state, line, pos);
        }
        return conditions(text, state, line, pos);
    }

    protected String selectFields(String text, int state, String line, int pos) {
        if (doneVariables(line.substring(0, pos))) {
            if (state == 0) return "from";
            return null;
        }
        return fieldlookup(text, state);
    }

    protected String targetTables(String text, int state, String line, int pos) {
        if (doneVariables(line.substring(0, pos))) {
            if (state == 0) return "where";
            return null;
        }
        return tablelookup(text, state);
    }
}
