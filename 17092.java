import java.util.*;

public class LessThan extends MathOperator {

    public LessThan() {
        opName = "lessThan";
    }

    public boolean compare(ArrayList<Object> list1, ArrayList<Object> list2) throws KSInternalErrorException {
        if ((list1 == null) && (list2 == null)) return true;
        Object obj1, obj2;
        for (int i = 0; i < list1.size(); i++) {
            obj1 = list1.get(i);
            for (int j = 0; j < list2.size(); j++) {
                obj2 = list2.get(j);
                if (obj1.getClass() != obj2.getClass()) throw new KSInternalErrorException("MathOperator given mixed-class objects.");
                if ((obj2 instanceof Integer) && ((((Integer) obj1).compareTo((Integer) obj2)) >= 0)) return false;
                if ((obj2 instanceof Float) && ((((Float) obj1).compareTo((Float) obj2)) >= 0)) return false;
                if ((obj2 instanceof String) && ((((String) obj1).compareTo((String) obj2)) >= 0)) return false;
            }
        }
        return true;
    }
}
