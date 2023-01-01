/**
 * AST node for unary minus expressions.
 */
class Tuminus extends Texp implements AST {

    Texp exp;

    public Tuminus(Texp e) {
        exp = e;
    }

    public String toString() {
        return "-" + exp;
    }

    public void checkcontext(SymTab st) {
        exp.checkcontext(st);
    }

    public void prepInterp(SymTab st) {
        exp.prepInterp(st);
    }

    public int interpret(int[] in, int[] par) {
        return -(exp.interpret(in, par));
    }
}
