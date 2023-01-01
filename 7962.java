import java.util.*;

public class AstType {

    public int type;

    public String name;

    public boolean isTuple;

    public boolean isClass;

    public boolean isInterface;

    public boolean isAbstract;

    public boolean isGeneric;

    public boolean isPointer;

    public boolean isArray;

    public AstType() {
        isTuple = false;
        isClass = false;
        isInterface = false;
        isAbstract = false;
        isGeneric = false;
        isPointer = false;
        isArray = false;
    }
}
