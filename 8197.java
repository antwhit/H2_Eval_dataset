public class Triangle {

    private Line2D faceA;

    private Line2D faceB;

    private Line2D faceC;

    private Triangle triangleA;

    private Triangle triangleB;

    private Triangle triangleC;

    public Triangle(Line2D faceA, Line2D faceB, Line2D faceC) {
        this.faceA = faceA;
        this.faceB = faceB;
        this.faceC = faceC;
        this.triangleA = null;
        this.triangleB = null;
        this.triangleC = null;
    }

    public Triangle(Point2D A, Point2D B, Point2D C) {
        this.faceA = new Line2D(A, B);
        this.faceB = new Line2D(B, C);
        this.faceC = new Line2D(C, A);
        this.triangleA = null;
        this.triangleB = null;
        this.triangleC = null;
    }

    public boolean estDedans(Point2D p) {
        return this.faceA.estADroite(p) && this.faceB.estADroite(p) && this.faceC.estADroite(p);
    }

    public String toString() {
        return faceA + "\n" + faceB + "\n" + faceC;
    }

    public static void main(String[] argv) {
        Point2D A = new Point2D(1, 2);
        Point2D B = new Point2D(2, 2);
        Point2D C = new Point2D(3, 3);
        Triangle tri = new Triangle(A, B, C);
        System.out.println(tri);
    }
}
