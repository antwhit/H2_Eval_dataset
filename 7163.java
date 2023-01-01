import java.awt.*;
import java.awt.event.*;

interface TimeLineListener {

    public void markerChanged();
}

class TimeLine extends Canvas implements MouseListener, MouseMotionListener {

    protected double period;

    protected double selStart;

    protected double selEnd;

    protected int marker;

    protected TimeLineListener listener;

    protected FontMetrics fm;

    static final int MARKER_SIZE = 3;

    static final int FONT_SIZE = 9;

    static final Font FONT = new Font(null, 0, FONT_SIZE);

    String round(double x) {
        int n = (int) (x * 1000);
        String s = String.valueOf(n % 1000);
        while (s.length() < 3) {
            s = "0" + s;
        }
        return (n / 1000) + "." + s;
    }

    public TimeLine(double period) {
        this.period = period;
        selStart = selEnd = 0.0;
        marker = 0;
        fm = getFontMetrics(FONT);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public Dimension getMinimumSize() {
        return new Dimension(4, 20);
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 20);
    }

    public void setTimeLineListener(TimeLineListener listener) {
        this.listener = listener;
    }

    public void select(double timeStart, double timeEnd) {
        selStart = timeStart;
        selEnd = timeEnd;
        repaint();
    }

    public double getMarkedTime() {
        return period * (marker - 1) / (getWidth() - 2);
    }

    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int h = height - MARKER_SIZE - FONT_SIZE;
        g.setColor(Color.LIGHT_GRAY);
        g.draw3DRect(0, MARKER_SIZE, width - 1, h, false);
        if (selEnd > 0.0) {
            double scale = (width - 2) / period;
            int x = (int) (selStart * scale);
            int w = (int) ((selEnd - selStart) * scale);
            if (w == 0) w = 1;
            g.setColor(Color.DARK_GRAY);
            g.fillRect(x + 1, MARKER_SIZE + 1, w, h - 1);
        }
        if (marker != 0) {
            g.setColor(Color.LIGHT_GRAY);
            g.draw3DRect(marker - 1, 0, 2, h + 2 * MARKER_SIZE, true);
            String value = round(period * (marker - 1) / (width - 2));
            int x = marker + 3;
            int stringWidth = fm.stringWidth(value);
            if (x + stringWidth >= width) {
                x = marker - stringWidth;
            }
            g.setColor(Color.BLACK);
            g.setFont(FONT);
            g.drawString(value, x, height);
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        int newMarker = e.getX();
        if (newMarker < 0 || newMarker >= (getWidth() - 1)) {
            newMarker = 0;
        }
        if (newMarker != marker) {
            marker = newMarker;
            repaint();
            if (listener != null) {
                listener.markerChanged();
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        mousePressed(e);
    }
}
