import java.awt.*;
import java.awt.event.*;

public class ImageButton extends Canvas implements Runnable {

    Image[] buttonUp;

    Image[] buttonDown;

    Image buttonDisabled;

    boolean up;

    boolean enabled;

    int currentButton;

    int firstFrame;

    public ImageButton(Image[] buttonUp, Image[] buttonDown, Image buttonDisabled) {
        super();
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        this.buttonDisabled = buttonDisabled;
        enabled = false;
        currentButton = 0;
        repaint();
        firstFrame = -1;
        animator = null;
        animate = false;
        gotFocus = false;
        pressed = false;
    }

    public Dimension getMinimumSize() {
        return (new Dimension(buttonDisabled.getWidth(this), buttonDisabled.getHeight(this)));
    }

    public Dimension getPreferredSize() {
        return (getMinimumSize());
    }

    public void setEnabled(boolean enabled) {
        if (enabled != this.enabled) {
            stopAnimation();
            this.enabled = enabled;
            if (enabled) {
                enableEvents(AWTEvent.MOUSE_EVENT_MASK);
                buttonUp();
            } else {
                disableEvents(AWTEvent.MOUSE_EVENT_MASK);
                repaint();
            }
        }
    }

    public void setButton(int currentButton) {
        if (currentButton != this.currentButton) {
            stopAnimation();
            this.currentButton = currentButton;
            if (enabled) buttonUp();
        }
    }

    public void setAnimation(int firstFrame, int delay) {
        this.firstFrame = firstFrame;
        this.delay = delay;
    }

    int delay;

    boolean animate;

    Thread animator;

    private boolean startAnimation() {
        if (animate) return (animate); else if (currentButton == firstFrame) {
            animator = new Thread(this);
            animate = true;
            animator.start();
        }
        return (animate);
    }

    private boolean stopAnimation() {
        if (animate) {
            animate = false;
            try {
                animator.interrupt();
                animator.join();
            } catch (InterruptedException ie) {
            }
            animator = null;
            return (true);
        } else return (false);
    }

    public void run() {
        while (animate) {
            paint(this.getGraphics());
            currentButton++;
            currentButton = (currentButton < buttonUp.length) ? currentButton : firstFrame;
            try {
                animator.sleep(delay);
            } catch (InterruptedException ie) {
            }
        }
        currentButton = firstFrame;
    }

    private void buttonUp() {
        up = true;
        if (!startAnimation()) repaint();
    }

    private void buttonDown() {
        stopAnimation();
        up = false;
        paint(this.getGraphics());
        try {
            Thread.sleep(30);
        } catch (InterruptedException ie) {
        }
    }

    public void paint(Graphics g) {
        if (enabled) {
            if (up) g.drawImage(buttonUp[currentButton], 0, 0, this); else g.drawImage(buttonDown[currentButton], 0, 0, this);
        } else g.drawImage(buttonDisabled, 0, 0, this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    boolean gotFocus, pressed;

    public void processMouseEvent(MouseEvent me) {
        if (me.getID() == MouseEvent.MOUSE_ENTERED) {
            gotFocus = true;
            if (pressed) buttonDown();
        } else if (me.getID() == MouseEvent.MOUSE_EXITED) {
            gotFocus = false;
            if (pressed) buttonUp();
        } else if (me.getID() == MouseEvent.MOUSE_PRESSED) {
            pressed = true;
            buttonDown();
        } else if (me.getID() == MouseEvent.MOUSE_RELEASED) {
            pressed = false;
            if (gotFocus) {
                buttonUp();
                processActionEvent(new ActionEvent(this, ActionEvent.ACTION_LAST, Integer.toString(currentButton)));
            }
        }
    }

    ActionListener al;

    public void addActionListener(ActionListener al) {
        this.al = al;
    }

    public void processActionEvent(ActionEvent ae) {
        al.actionPerformed(ae);
    }

    public void finalize() {
        stopAnimation();
    }
}
