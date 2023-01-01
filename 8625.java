import java.utils.*;
import org.xml.transform.*;

public class Foo {

    public Foo() {
    }

    /**
     * Comment
     */
    public void bar() {
        for (int i = 0; i < 10; i++) System.out.println("Hello " + (int) (i + 1));
    }

    Object test(Object x) {
        bar();
        int y = multiply(2, 3);
        return null;
    }

    public int multiply(int x, int y) {
        int result = x * y;
        if (result < 0) return -result; else return result;
    }

    public double divide(double x, double y) {
        double result = x / y;
        return result;
    }

    public static void main(String[] args) {
        Foo obj = new Foo();
        obj.bar();
        int x;
        if (true) x = obj.multiply(3, 4); else x = obj.multiply(3, 5);
        double y = obj.divide(14, 4);
    }
}
