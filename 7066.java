import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import net.jeeseminar.gplus.domain.Circle;
import net.jeeseminar.gplus.domain.CircleEditService;
import net.jeeseminar.gplus.domain.CircleNotSelectedException;
import net.jeeseminar.gplus.domain.CircleServiceRemote;

public class MainStateful {

    /**
	 * @param args
	 * @throws NamingException 
	 */
    public static void main(String[] args) throws NamingException {
        InitialContext context = new InitialContext();
        CircleEditService circleService = (CircleEditService) context.lookup("java:global/GPlus/CircleEditStatefulBean!net.jeeseminar.gplus.domain.CircleEditService");
        List<Circle> allCircles = circleService.getAllCircles();
        Circle toEdit = null;
        for (Circle circle : allCircles) {
            if (circle.getName().equals("Freunde")) toEdit = circle;
        }
        try {
            circleService.commitChanges();
        } catch (CircleNotSelectedException e1) {
            e1.printStackTrace();
        }
        System.out.println("N�chster aufruf");
        try {
            circleService.setCircleToEdit("Freunde", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
