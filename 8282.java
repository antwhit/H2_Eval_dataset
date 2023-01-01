import edu.iastate.cs.ptolemy.runtime.PtEventType;
import edu.iastate.cs.ptolemy.runtime.diagnostics.Contract;

class Changed extends PtEventType<Object> {

    Point changedPoint;

    public void contract1() {
        Contract.requires(1 == 1);
        Contract.ensures(true);
    }
}
