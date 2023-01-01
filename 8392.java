/**
 * Test code for the method breakpoints.
 *
 * @author Nathan Fiedler
 */
public class Overloaded {

    public void amethod() {
        System.out.println("in amethod");
    }

    public void amethod(int i) {
        System.out.println("in amethod(int)");
    }

    public void amethod(String s) {
        System.out.println("in amethod(String)");
    }

    public void amethod(boolean b) {
        System.out.println("in amethod(boolean)");
    }

    public void themethod() {
        System.out.println("in themethod()");
    }

    public static void main(String[] args) {
        Overloaded o = new Overloaded();
        o.amethod();
        o.amethod(10);
        o.amethod("abc");
        o.amethod(true);
        o.themethod();
    }
}
