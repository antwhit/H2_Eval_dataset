class Box13 {

    double width;

    double height;

    double depth;

    Box13(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    public String toString() {
        return "Dimensions are " + width + " by " + depth + " by " + height + ".";
    }
}

class toStringDemo {

    public static void main(String args[]) {
        Box13 b = new Box13(10, 12, 14);
        String s = "Box b: " + b;
        System.out.println(b);
        System.out.println(s);
    }
}
