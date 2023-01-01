import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UFrame {

    public static void maximize(Frame frame) {
        Point frameLocation = frame.getLocationOnScreen();
        Dimension frameSize = frame.getSize();
        int maximizeX = frameLocation.x + frameSize.width - 30;
        int maximizeY = frameLocation.y + 10;
        try {
            Robot robot = new Robot();
            robot.mouseMove(maximizeX, maximizeY);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (AWTException e) {
        }
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Text Maximize");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(25, 25, 300, 200);
        frame.setVisible(true);
        UFrame.maximize(frame);
    }
}
