/**
 * Implements the driver for the Chain of Responsibility example.<p>
 * 
 * @author  Mehdi Bagherzadeh
 * @version $Revision: 1.2 $, $Date: 2010/03/13$
 */
public class AppEntry {

    public static void main(String args[]) {
        Point p = new Point(0, 0);
        Logging l = new Logging();
        p.moveBy(10, 10);
    }
}
