/**
 * Diese Klasse stellt einen Ball dar.
 * 
 * @author Andrei
 */
public class Ball extends Gift<FullCircleShape> {

    /**
	 * Erzeugungskonstruktor
	 * @param name Name des Geschenks
	 * @param shape Form des Geschenks
	 */
    public Ball(String name, FullCircleShape shape) {
        super("Ball", shape);
    }
}
