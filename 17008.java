import edu.iastate.cs.ptolemy.runtime.diagnostics.Contract;

public class Changed {

    Object o = new Object();

    public void contract() {
        Contract.requires(true);
        Contract.ensures(o != Contract.oldValue(o));
    }
}
