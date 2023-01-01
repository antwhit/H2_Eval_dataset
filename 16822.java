public class Spot {

    public int x, y;

    private double radius;

    private double area;

    public Spot() {
    }

    public Spot(int x, int y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.setArea();
    }

    public void setRadius(double newRadius) {
        if (newRadius >= 0.0) {
            radius = newRadius;
            this.setArea();
        }
    }

    public int getRadius() {
        return radius;
    }

    private void setArea() {
        this.area = Math.PI * Math.pow(radius, 2.0);
    }

    public double getArea() {
        return area;
    }

    public static void main(String[] argv) {
        Spot outter = new Spot();
        outter.setRadius(3.0);
        Spot inner = new Spot(0, 0, 2.0);
        double intersection = outter.getArea() - inner.getArea();
        System.out.println(intersection);
    }
}
