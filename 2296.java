import example;

public class main {

    static {
        try {
            System.loadLibrary("example");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Cannot load the example native code.\nMake sure your LD_LIBRARY_PATH contains \'.\'\n" + e);
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
        Vector c = new Vector(example.addv(a.getCPtr(), b.getCPtr()), false);
        System.out.println("    a+b = " + c.print());
        c._delete();
        System.out.println("Creating an array of vectors");
        VectorArray va = new VectorArray(10);
        System.out.println("    va = " + va.toString());
        va.set(0, a);
        va.set(1, b);
        example.VectorArray_set(va.getCPtr(), 2, example.addv(a.getCPtr(), b.getCPtr()));
        c = new Vector(example.addv(a.getCPtr(), b.getCPtr()), true);
        va.set(3, c);
        System.out.println("Getting some array values");
        for (int i = 0; i < 5; i++) System.out.println("    va(" + i + ") = " + va.get(i).print());
        System.out.println("Making sure we don't leak memory.");
        for (int i = 0; i < 1000000; i++) c = va.get(i % 10);
        System.out.println("Cleaning up");
        va._delete();
        a._delete();
        b._delete();
    }
}
