class Example099 {

    public static void main(String[] args) {
        mathtest();
    }

    static void mathtest() {
        print("Illegal arguments, NaN results:");
        print(Math.sqrt(-1));
        print(Math.log(-1));
        print(Math.pow(-1, 2.5));
        print(Math.acos(1.1));
        print("Infinite results:");
        print(Math.log(0));
        print(Math.pow(0, -1));
        print(Math.exp(1000.0));
        print("Infinite arguments:");
        double infinity = Double.POSITIVE_INFINITY;
        print(Math.sqrt(infinity));
        print(Math.log(infinity));
        print(Math.exp(-infinity));
        print("NaN arguments and special cases:");
        double nan = Math.log(-1);
        print(Math.sqrt(nan));
        print(Math.pow(nan, 0));
        print(Math.pow(0, 0));
        print(Math.round(nan));
        print(Math.round(1E50));
        for (double x = -100; x <= 100; x += 0.125) {
            for (double y = -100; y <= 100; y += 0.125) {
                double r = Math.atan2(y, x);
                if (!(sign(Math.cos(r)) == sign(x) && sign(Math.sin(r)) == sign(y))) print("x = " + x + "; y = " + y);
            }
        }
    }

    static int sign(double x) {
        final double tolerance = 1E-14;
        if (x < -tolerance) return -1; else if (x > +tolerance) return +1; else return 0;
    }

    static void print(String d) {
        System.out.println(d);
    }

    static void print(double d) {
        System.out.println(d);
    }

    static void print(long d) {
        System.out.println(d);
    }
}
