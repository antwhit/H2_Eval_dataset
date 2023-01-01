/**
 * Test insertion of an abstract method in a superclass.
 */
public class Main {

    public static void main(String[] args) {
        ConcreteSub.main();
        try {
            ConcreteSub2 blah = new ConcreteSub2();
            blah.doStuff();
            System.err.println("Succeeded unexpectedly");
        } catch (VerifyError ve) {
            System.out.println("Got expected failure");
        } catch (AbstractMethodError ame) {
            System.out.println("Got expected failure");
        }
    }
}
