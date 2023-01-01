import java.util.HashSet;
import java.util.Set;

public class SetExample {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        Set<Point> pointSet = new HashSet<Point>();
        pointSet.add(new Point(10, 0));
        pointSet.add(new Point(10, 0));
        System.out.println(pointSet.size());
        pointSet.remove(new Point(10, 12));
        System.out.println(pointSet.size());
    }
}
