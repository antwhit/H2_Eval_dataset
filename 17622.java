/**
 * Bezier-g�rbe trajekt�ri�t megval�s�t� oszt�ly
 */
public class Bezier extends Trajectory {

    private Shape attachedShape;

    /**
	 * Konstruktor
	 */
    public Bezier() {
        IO.startprint("Bezier");
        IO.exitprint("Bezier");
    }

    /**
	 * Trajekt�ri�hoz alakzatot rendel hozz�
	 * @param shape A hozz�rendelend� alakzat
	 */
    public void setShape(Shape shape) {
        IO.startprint("Bezier.setShape");
        attachedShape = shape;
        IO.exitprint("Bezier.setShape");
    }

    /**
	 * Anim�ci� k�zben kisz�molja a hozz� tartoz� alakzat helyzet�t
	 * @param szam Az anim�ci� kezdet�t�l eltelt id�
	 */
    public void calculateFrame(double szam) {
        IO.startprint("Bezier.calculateFrame");
        attachedShape.getAnimState(new Point(), 1.0);
        IO.exitprint("Bezier.calculateFrame");
    }

    /**
	 * Az elment�st v�gz� f�ggv�ny
	 */
    public void save() {
        IO.startprint("Bezier.save");
        IO.exitprint("Bezier.save");
    }
}
