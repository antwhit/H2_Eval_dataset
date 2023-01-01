import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/** Test to verify that java bug 4187388 is not present in our system
    (<clinit> for implemented interfaces shows up using
    Class.getMethods()). If "<clinit>" is printed, the test has failed. */
interface TestedInterface {

    String s = System.getProperty("Test");
}

public class ReflectionClinitTest {

    public static void main(String args[]) {
        try {
            Class c = Class.forName("TestedInterface");
            Method meth[] = c.getMethods();
            for (int i = 0; i < meth.length; i++) System.out.println(Modifier.toString(meth[i].getModifiers()) + " " + meth[i].getName());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
