/**
 * AST node for function application.
 * 
 * Also contains pointer to declaration location of the function.
 */
class Tfun extends Texp implements AST {

    Tident ident;

    Texplist explist;

    public Tfun(Tident i, Texplist e) {
        ident = i;
        explist = e;
    }

    public String toString() {
        return ident + "(" + explist + ")";
    }

    public void checkcontext(SymTab st) {
        explist.checkcontext(st);
        SymtabEntry ste = st.lookup(ident.toString());
        if (ste == null) Main.error("function not defined: " + ident); else if (ste.kind() != SymtabEntry.FUN) Main.error("variable used as funktion: " + ident); else if (((STEfun) ste).arity() != explist.length()) Main.error("wrong arity at function call: " + ident);
    }

    Tdekl fundekl;

    public void prepInterp(SymTab st) {
        fundekl = ((STEfun) st.lookup(ident.toString())).getDekl();
        explist.prepInterp(st);
    }

    public int interpret(int[] in, int[] par) {
        int[] newparams = new int[fundekl.arity()];
        explist.interpret(in, par, newparams, 0);
        return fundekl.interpret(in, newparams);
    }
}
