import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A simple class that displays an about window with a message.
 * @author Joni Toivanen (jomiolto@gmail.com)
 */
class AboutWindow {

    /**
	 * The title of the about window.
	 */
    private static final String title = "About";

    /**
	 * The string of text displayed in the about window.
	 */
    private static final String message = "Lines and Dots v. " + JLad.version + "\n" + "Copyright © 2007, Joni Toivanen. All rights reserved.";

    /**
	 * Shows the about window.
	 */
    public static void show() {
        JFrame frame = JLad.getMainWindow();
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.PLAIN_MESSAGE);
    }
}
