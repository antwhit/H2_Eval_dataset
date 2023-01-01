/**
 * AST node for a parameter list.
 */
class Tparlist implements AST {

    Tparlist parlist;

    Tident ident;

    public Tparlist(Tparlist p, Tident i) {
        parlist = p;
        ident = i;
    }

    public Tparlist(Tident i) {
        parlist = null;
        ident = i;
    }

    public String toString() {
        if (parlist != null) return parlist + "," + ident; else return ident.toString();
    }

    public void setSymtab(SymTab st, boolean isInput, int index) {
        boolean isNew = st.enter(ident.toString(), new STEvar(ident.toString(), isInput, index));
        if (!isNew) Main.error("Variable " + ident + " defined twice!");
        if (parlist != null) parlist.setSymtab(st, isInput, index + 1);
    }
}
