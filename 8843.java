import javax.swing.JFrame;

/**
 * @author trunghpb
 *
 */
public class Application {

    /**
	 * @param args
	 */
    public static void main(String[] args) {
        EventFrame mainFrame = new EventFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
