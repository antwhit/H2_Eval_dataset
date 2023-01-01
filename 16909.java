/**
 * AST node for declaration lists of functions
 */
class Tdekllist implements AST {

    Tdekllist dekllist;

    Tdekl dekl;

    public Tdekllist(Tdekllist p, Tdekl e) {
        dekllist = p;
        dekl = e;
    }

    public Tdekllist(Tdekl e) {
        dekllist = null;
        dekl = e;
    }

    public String toString() {
        if (dekllist != null) return (dekllist + ",\n" + dekl); else return (dekl.toString());
    }

    public void setSymtab(SymTab st) {
        if (dekllist != null) dekllist.setSymtab(st);
        dekl.setSymtab(st);
    }

    public void printSymtabs() {
        if (dekllist != null) dekllist.printSymtabs();
        dekl.printSymtabs();
    }

    public void checkcontext() {
        if (dekllist != null) dekllist.checkcontext();
        dekl.checkcontext();
    }

    public void prepInterp(SymTab st) {
        dekl.prepInterp(st);
        if (dekllist != null) dekllist.prepInterp(st);
    }
}
