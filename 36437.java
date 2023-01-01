public class TMatrix2x2 {

    protected double[][] v;

    public TMatrix2x2(double[][] vu) {
        v = vu;
    }

    public TMatrix2x2(TKoo2d koo1, TKoo2d koo2) {
        v = new double[2][2];
        v[0][0] = koo1.getX();
        v[0][1] = koo1.getY();
        v[1][0] = koo2.getX();
        v[1][1] = koo2.getY();
    }

    public double getDeterm() {
        return v[0][0] * v[1][1] - v[0][1] * v[1][0];
    }
}
