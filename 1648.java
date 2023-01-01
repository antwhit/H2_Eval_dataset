import java.util.Vector;
import java.util.Hashtable;

class stmtStop extends stmtStrl {

    static final int HALT = 1;

    int stmtType;

    stmtStop(int t) {
        super();
        stmtType = t;
    }

    void setStmtType(int s) {
        stmtType = s;
    }

    int getStmtType() {
        return stmtType;
    }

    int splitEC() {
        if (ecl.argEcl.debug) {
            System.err.println("Starting split of Stop");
        }
        int returnCodeType = CODETYPEE;
        setCodeType(returnCodeType);
        if (ecl.argEcl.debug) {
            System.err.print("Stop ");
            System.err.println("Statement " + this + " choosing codetype " + returnCodeType);
        }
        return returnCodeType;
    }

    void printE(structuredStream out) throws java.io.IOException {
        switch(stmtType) {
            case HALT:
                out.indent();
                out.restore();
                out.print("halt");
                break;
            default:
                System.err.println("Severe internal error: type not in this class");
                System.exit(-1);
        }
    }

    public String toString() {
        StringBuffer str = new StringBuffer(super.toString());
        switch(stmtType) {
            case HALT:
                str.append("halt;\n");
                break;
            default:
                System.err.println("Severe internal error: type not in this class");
                System.exit(-1);
        }
        return str.toString();
    }

    void extractPrototypesRecurse(Vector list) {
    }
}
