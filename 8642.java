public class runme {

    static {
        try {
            System.loadLibrary("example");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        System.out.println("Creating some objects:");
        Vector a = new Vector(3, 4, 5);
        Vector b = new Vector(10, 11, 12);
        System.out.println("    Created " + a.print());
        System.out.println("    Created " + b.print());
        System.out.println("Adding a+b");
        Vector c = example.addv(a, b);
        System.out.println("    a+b = " + c.print());
        c.delete();
        c = null;
        System.out.println("Creating an array of vectors");
        VectorArray va = new VectorArray(10);
        System.out.println("    va = " + va.toString());
        va.set(0, a);
        va.set(1, b);
        va.set(2, example.addv(a, b));
        System.out.println("Getting some array values");
        for (int i = 0; i < 5; i++) System.out.println("    va(" + i + ") = " + va.get(i).print());
        System.out.println("Making sure we don't leak memory.");
        for (int i = 0; i < 1000000; i++) c = va.get(i % 10);
        System.out.println("Cleaning up");
        va.delete();
        a.delete();
        b.delete();
    }
}
