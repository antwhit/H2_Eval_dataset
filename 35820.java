public class main {

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
        Circle c = new Circle(10);
        System.out.println("    Created circle " + c);
        Square s = new Square(10);
        System.out.println("    Created square " + s);
        System.out.println("\nA total of " + Shape.getNshapes() + " shapes were created");
        c.setX(20);
        c.setY(30);
        Shape shape = s;
        shape.setX(-10);
        shape.setY(5);
        System.out.println("\nHere is their current position:");
        System.out.println("    Circle = (" + c.getX() + " " + c.getY() + ")");
        System.out.println("    Square = (" + s.getX() + " " + s.getY() + ")");
        System.out.println("\nHere are some properties of the shapes:");
        Shape[] shapes = { c, s };
        for (int i = 0; i < shapes.length; i++) {
            System.out.println("   " + shapes[i].toString());
            System.out.println("        area      = " + shapes[i].area());
            System.out.println("        perimeter = " + shapes[i].perimeter());
        }
        System.out.println("\nGuess I'll clean up now");
        c.delete();
        s.delete();
        System.out.println(Shape.getNshapes() + " shapes remain");
        System.out.println("Goodbye");
    }
}
