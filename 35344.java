import java.util.*;

public abstract class Warez {

    public abstract String getName();

    public abstract String getDescription();

    public abstract void execute();

    protected abstract void registerParams();

    protected List params;

    public Warez() {
        params = new LinkedList();
        registerParams();
    }

    public List getParams() {
        return params;
    }
}
