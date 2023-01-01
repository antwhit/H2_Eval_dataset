import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class SequenceLabels extends JComponent implements IView {

    List<String> names = new ArrayList<String>();

    CoreModel model;

    SequenceLabels(String name) {
        names.add(name);
        updateView();
    }

    SequenceLabels(List<String> newNames) {
        names = newNames;
        updateView();
    }

    SequenceLabels(CoreModel model) {
        this.names = model.getAlignment().getNames();
        updateView();
    }

    public void setMaximumHeight(int ph) {
        setMaximumSize(new Dimension(100, ph));
    }

    public void setMaximumWidth(int pw) {
        setMaximumSize(new Dimension(pw, 30));
    }

    public void setPreferredHeight(int ph) {
        setPreferredSize(new Dimension(100, ph));
    }

    public void setPreferredWidth(int pw) {
        setPreferredSize(new Dimension(pw, 30));
    }

    public void setMinimumHeight(int ph) {
        setMinimumSize(new Dimension(100, ph));
    }

    public void setMinimumWidth(int pw) {
        setMinimumSize(new Dimension(pw, 30));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle drawHere = g2.getClipBounds();
        g.setColor(this.getBackground());
        g.fillRect(drawHere.x, drawHere.y, drawHere.width, drawHere.height);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 9));
        g2.setColor(Color.BLACK);
        for (int i = 0; i < names.size(); i++) {
            g2.drawString(names.get(i), 10, 8 + (i * 10));
        }
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (String name : names) {
            buf.append(name + "\n");
        }
        return buf.toString();
    }

    public void updateView() {
        setPreferredWidth(50);
        setMinimumWidth(50);
        setMaximumWidth(50);
        setPreferredHeight(names.size() * 10);
        setMinimumHeight(names.size() * 10);
        setMaximumHeight(names.size() * 10);
        this.repaint();
    }
}
