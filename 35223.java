import java.awt.Frame;

/**
 *
 * @author  peter
 */
public class TestFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("creating new Frame object");
        Frame myFrame = new Frame("Hello AWT World");
        System.out.println("setting bounds to 500x400");
        myFrame.setBounds(10, 10, 500, 400);
        System.out.println("showing window");
        try {
            myFrame.show();
        } catch (Throwable e) {
        }
        System.out.println("wait for 10 seconds");
        Thread.sleep(10000);
        System.out.println("Setting new window title");
        int counter = 10;
        while (counter > 0) {
            myFrame.setTitle("Please wait .... " + counter);
            Thread.sleep(1000);
            counter--;
        }
        System.out.println("close window");
        myFrame.dispose();
        System.out.println("done");
        System.exit(0);
    }
}
