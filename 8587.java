import edu.iastate.cs.ptolemy.runtime.EventType;
import edu.iastate.cs.ptolemy.runtime.diagnostics.Contract;

class Changed extends EventType<Fig> {

    Fig fe;

    public Object contract() {
        Contract.requires(fe != null);
        Contract.ensures(fe != null);
        if (fe.fixed) return invoke(next); else return null;
    }
}
