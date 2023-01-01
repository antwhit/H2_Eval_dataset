public class AppEntry {

    public static void main(String args[]) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 100);
        Point p3 = new Point(100, 0);
        Line yAxis = new Line(p1, p2);
        Line xAxis = new Line(p1, p3);
        yAxis.draw();
        xAxis.draw();
        new DisplayUpdate();
        p1.moveBy(50, 50);
        yAxis.moveBy(50, 50);
    }
}
