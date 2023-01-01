import org.ozoneDB.OzoneObject;

/**
 * $Id: DelegateImpl.java,v 1.1 2003/04/17 09:36:08 per_nyfelt Exp $
 */
public class DelegateImpl extends OzoneObject implements Delegate {

    Foo foo;

    Bar bar;

    public DelegateImpl(Foo foo, Bar bar) {
        this.foo = foo;
        this.bar = bar;
    }

    public void updateFooBar(String aVal, String bVal, int cVal) throws Exception {
        foo.setA(aVal);
        foo.setB(bVal);
        bar.setC(cVal);
    }

    public void updateFooBar2(String aVal, String bVal, int cVal) {
        try {
            foo.setA(aVal);
            foo.setB(bVal);
            bar.setC(cVal);
        } catch (Exception e) {
            System.out.println("updateFooBar2(), failed to update");
        }
    }
}
