import template_classes.*;

public class template_classes_runme {

    static {
        try {
            System.loadLibrary("template_classes");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        RectangleInt rectint = new RectangleInt();
        PointInt pi = rectint.getPoint();
        int x = pi.getX();
    }
}
