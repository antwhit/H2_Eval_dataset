import java.awt.geom.Point2D;
import ij.IJ;

class Vector2 extends Point2D.Double {

    Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Vector2(int x, int y) {
        this.x = (double) x;
        this.y = (double) y;
    }

    static double V2SquaredLength(Vector2 v) {
        return ((v.x * v.x) + (v.y * v.y));
    }

    double V2Length() {
        return Math.sqrt(V2SquaredLength(this));
    }

    void V2Negate() {
        x = -x;
        y = -y;
    }

    void V2Normalize() {
        double len = V2Length();
        if (len != 0.0) {
            x /= len;
            y /= len;
        }
    }

    static Vector2 V2Scale(Vector2 v, double newlen) {
        double len = v.V2Length();
        double x = v.x;
        double y = v.y;
        if (len != 0.0) {
            x = x * newlen / len;
            y = y * newlen / len;
        }
        return new Vector2(x, y);
    }

    static Vector2 V2Add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    static Vector2 V2Sub(Vector2 a, Vector2 b) {
        return new Vector2(a.x - b.x, a.y - b.y);
    }

    static double V2Dot(Vector2 a, Vector2 b) {
        return (a.x * b.x) + (a.y * b.y);
    }

    static Vector2 V2Lerp(Vector2 lo, Vector2 hi, double alpha) {
        return new Vector2(LERP(alpha, lo.x, hi.x), LERP(alpha, lo.y, hi.y));
    }

    static double LERP(double alpha, double a, double b) {
        return alpha + ((b - a) * alpha);
    }

    static Vector2 V2Combine(Vector2 a, Vector2 b, double ascl, double bscl) {
        return new Vector2((ascl * a.x) + (bscl * b.x), (ascl * a.y) + (bscl * b.y));
    }

    static Vector2 V2Mul(Vector2 a, Vector2 b) {
        return new Vector2(a.x * b.x, a.y * b.y);
    }

    static double V2DistanceBetween2Points(Vector2 a, Vector2 b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        double dist = Math.sqrt((dx * dx) + (dy * dy));
        if (0 == dist) {
            IJ.write("Distance is 0!");
        }
        return dist;
    }

    void V2MakePerpendicular(Vector2 a) {
        x = -a.y;
        y = -a.x;
    }

    static Vector2 V2Duplicate(Vector2 a) {
        return new Vector2(a.x, a.y);
    }
}
