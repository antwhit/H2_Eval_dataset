/**
 * Symbol table entry for names, there are subclasses for
 * variables and functions.
 * 
 * Defines constants UNKNOWN, VAR und FUN as kinds of
 * symbol table entries.
 */
class SymtabEntry {

    String name;

    public SymtabEntry(String v) {
        name = v;
    }

    public int kind() {
        return UNKNOWN;
    }

    public String toString() {
        return ("unknown " + name);
    }

    static final int UNKNOWN = 12;

    static final int VAR = 13;

    static final int FUN = 14;
}
