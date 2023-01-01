import java.util.Stack;
import java.util.Vector;

public class FragmentParser {

    public FragmentParser() {
        elems = new Stack();
        rootElem = new ParseElem("**root**", 0, 4, true);
    }

    public FragmentParser(String s) {
        elems = new Stack();
        rootElem = new ParseElem("**root**", 0, 4, true);
        result = parseFragment(s);
    }

    public boolean parseFragment(String s) {
        source = s;
        elems = new Stack();
        rootElem.down = new ListVector();
        int i = s.length();
        int j = 0;
        boolean flag = false;
        elems = new Stack();
        elems.push(rootElem);
        do {
            int k = s.indexOf('<', j);
            if (k == -1) {
                if (s.indexOf('>', j) == -1) {
                    ((ParseElem) elems.peek()).addDown(new ParseElem(s, j, i, true));
                    return elems.peek() == rootElem;
                } else {
                    return false;
                }
            }
            if (k > s.indexOf('>', j)) {
                return false;
            }
            if (k > j) {
                ((ParseElem) elems.peek()).addDown(new ParseElem(s, j, k, true));
            }
            j = k + 1;
            if (xxxChar(j) == '/') {
                flag = true;
                j++;
            }
            int l = s.indexOf('>', j);
            int i1 = s.indexOf(' ', j);
            int j1 = s.indexOf('\t', j);
            if (j1 == -1) {
                j1 = 0x3b9aca00;
            }
            int k1 = s.indexOf('\r', j);
            if (k1 == -1) {
                k1 = 0x3b9aca00;
            }
            int l1 = s.indexOf('\n', j);
            if (l1 == -1) {
                l1 = 0x3b9aca00;
            }
            if (k1 < j1) {
                j1 = k1;
            }
            if (k1 < l1) {
                l1 = k1;
            }
            if (l1 < j1) {
                j1 = l1;
            }
            if (i1 == -1) {
                i1 = j1;
            } else if (j1 < i1) {
                i1 = j1;
            }
            if (l == -1 && i1 == -1) {
                return false;
            }
            k = l != -1 ? i1 != -1 ? l >= i1 ? i1 : l : l : i1;
            if (k == -1) {
                return false;
            }
            ParseElem parseelem = new ParseElem(s, j, k, false);
            lastTag = parseelem;
            if (flag) {
                flag = false;
                if (((ParseElem) elems.peek()).getString().equals(parseelem.getString())) {
                    elems.pop();
                } else {
                    return false;
                }
            } else {
                ((ParseElem) elems.peek()).addDown(parseelem);
                elems.push(parseelem);
            }
            if (xxxChar(k) != '>') {
                j = l;
            } else {
                j = l;
            }
        } while (++j < i);
        return elems.peek() == rootElem;
    }

    public char xxxChar(int i) {
        char c = source.charAt(i);
        return c;
    }

    public String toString() {
        String s = rootElem.descendentsToString();
        return s;
    }

    public String dump() {
        String s = "\nParse results:\n" + toString() + "\n" + elems.toString();
        return s;
    }

    public ParseElem stackTop() {
        return (ParseElem) elems.peek();
    }

    public void setResult(boolean flag) {
        result = flag;
    }

    public boolean isResult() {
        return result;
    }

    Stack elems;

    ParseElem rootElem;

    ParseElem lastTag;

    String source;

    static final int QSTRING = 1;

    static final int NDATA = 2;

    static final int TDATA = 3;

    static final int CONTENT = 4;

    static final int ITDATA = 5;

    static final int NULLTOKEN = 0;

    protected boolean result;
}
