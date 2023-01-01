import graphs.CycleException;
import java.util.ArrayList;
import javax.swing.JFrame;
import poset.ExplicitRedundancyException;
import poset.Item;
import poset.Ordering;
import poset.PosetSpace;
import poset.User;
import poset.Poset;
import poset.ReflexiveOrderingException;

public class PosetSpaceSettlingTesterMain {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Poset Predictor");
        frame.setBounds(100, 100, 700, 500);
        PosetSpaceViewer psv = createPosetSpaceViewer();
        frame.setContentPane(psv);
        frame.setVisible(true);
        psv.start();
    }

    private static PosetSpaceViewer createPosetSpaceViewer() {
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < 50; i++) {
            items.add(new Item("Item" + i));
        }
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < 3; i++) {
            User user = new User("User" + i);
            Poset poset = new Poset();
            for (int j = 0; j < 200; j++) {
                if (Math.random() > 0) {
                    try {
                        Ordering o = new Ordering(items.get((int) (items.size() * Math.random())), items.get((int) (items.size() * Math.random())));
                        poset.addOrdering(o);
                    } catch (ReflexiveOrderingException e) {
                        System.out.println("Reflexive");
                    } catch (CycleException e) {
                        System.out.println("Loop");
                    } catch (ExplicitRedundancyException e) {
                        System.out.println("Explicit Redundancy");
                    }
                }
            }
            user.addPoset("like", poset);
            System.out.println(user);
            users.add(user);
        }
        PosetSpace ps = new PosetSpace("like", users, 2);
        PosetSpaceViewer psv = new PosetSpaceViewer(ps);
        return psv;
    }
}
