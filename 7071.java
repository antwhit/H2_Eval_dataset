import java.io.IOException;
import java.util.*;

public class CollectionTest {

    public static void main(String[] args) {
        int NumIn = 0;
        ArrayList al = new ArrayList();
        int sum = 0;
        System.out.println("Input Numbers:");
        Iterator itr = null;
        while (true) {
            try {
                NumIn = System.in.read();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            if (NumIn == '\r' || NumIn == '\n') break;
            al.add(new Integer(NumIn));
        }
        System.out.println("This is result:");
        itr = al.iterator();
        Integer intg = null;
        int temp = 0;
        while (itr.hasNext()) {
            intg = (Integer) itr.next();
            temp = intg.intValue() - '0';
            System.out.print(temp + " ");
            sum += temp;
        }
        System.out.println(" ----------> SUM: " + sum);
    }
}
