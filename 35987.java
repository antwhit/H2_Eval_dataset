import javax.vecmath.Point3d;

/**
 * This class represents a single triangle defined by three vertices.
 * @author Eugene Smal
 *
 */
public class Triangle {

    public Point3d vertices[];

    /**
	 * Constructor. Receives three vertices that define a triangle.
	 * @category Constructor
	 * @param one Vertex one
	 * @param two Vertex two
	 * @param three Vertex three
	 */
    public Triangle(Point3d one, Point3d two, Point3d three) {
        vertices = new Point3d[3];
        vertices[0] = one;
        vertices[1] = two;
        vertices[2] = three;
    }
}
