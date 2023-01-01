import com.stevesoft.pat.*;
import java.util.HashMap;

public class Variables extends ReplaceRule {

    private HashMap varStorage;

    public Variables(HashMap h) {
        varStorage = new HashMap(h);
    }

    public void apply(StringBufferLike sb, RegRes rr) {
        Object o = varStorage.get(rr.stringMatched());
        if (o == null) sb.append(rr.stringMatched()); else sb.append(o.toString());
    }

    public Object clone1() {
        return new Variables(varStorage);
    }
}
