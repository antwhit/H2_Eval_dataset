/**
 * Stellt einen Hohlkoerper mit quadratischer Grundflaeche dar
 * @author Christian
 *
 */
public class HollowSquareShape extends HollowShape implements SquareShape {

    private double side;

    /**
	 * Konstruktor
	 * @param height Hoehe muss positiv sein
	 * @param side Seitenlaenge muss positiv sein
	 */
    public HollowSquareShape(double height, double side) {
        this.height = height;
        this.side = side;
    }

    /**
	 * Liefert die grosse der Grundflaeche zurueck
	 * @return
	 */
    protected double getBaseArea() {
        return this.getSide() * this.getSide();
    }

    /**
	 * Liefert die Aussenseitenlaenge zurueck
	 * @return
	 */
    public double getSide() {
        return this.side + (this.bordersize * 2);
    }

    /**
	 * Berechnet ob dieser Hohlkoerper rechteckige Koerper in sich aufnehmen kann
	 * @return 
	 */
    public boolean isPackable(RectangleShape s) {
        return false;
    }

    /**
	 * Berechnet ob dieser Hohlkoerper rechteckige Koerper in sich aufnehmen kann
	 * @return 
	 */
    public boolean isPackable(SquareShape s) {
        if (this.height == s.getHeight() && this.side == s.getSide()) {
            return true;
        } else {
            return false;
        }
    }

    /**
	 * Berechnet ob dieser Hohlkoerper kreisfoermige Koerper in sich aufnehmen kann
	 * @return 
	 */
    public boolean isPackable(CircleShape s) {
        if (this.height == s.getHeight() && this.side == s.getDiameter()) {
            return true;
        } else {
            return false;
        }
    }
}
