import java.awt.*;

class T {

    public static void main(String argv[]) {
        double kn = 5.4;
        double kmh;
        CAux.perr("sp=" + 3.6d * kn / 1.852d, 2);
        CAux.perr(CAux.frds0(3.6d * kn / 1.852d, 2), 2);
    }
}
