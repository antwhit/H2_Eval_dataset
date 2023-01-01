import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LinkPanel extends JPanel {

    private CanvasWindow parent = null;

    LinkPanel(CanvasWindow tempParent) {
        parent = tempParent;
        setBounds(0, 0, 300, 300);
    }

    public void main(String args[]) {
        LinkPanel app = new LinkPanel(null);
        show();
    }

    public Dimension getPreferredSize() {
        return new Dimension(300, 300);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int com = 0;
        int pt = 0;
        int adjPt = 0;
        int x1, y1, x2, y2;
        for (com = 0; com < parent.getNumShapes(); com++) {
            for (pt = 0; pt < parent.myShape[com].getNumPoints(); pt++) {
                x1 = parent.myShape[com].getPoint(pt).getX() + parent.myShape[com].getX() + 4;
                y1 = parent.myShape[com].getPoint(pt).getY() + parent.myShape[com].getY() + 23;
                for (adjPt = 0; (adjPt < pt) && (adjPt < parent.myShape[com].getPoint(pt).getNumAdjPt()); adjPt++) {
                    x2 = parent.myShape[com].getPoint(pt).getAdjPt(adjPt).getX() + parent.myShape[com].getPoint(pt).getAdjPt(adjPt).getParentObj().getX() + 4;
                    y2 = parent.myShape[com].getPoint(pt).getAdjPt(adjPt).getY() + parent.myShape[com].getPoint(pt).getAdjPt(adjPt).getParentObj().getY() + 23;
                    g.drawLine(x1 - 4, y1 - 23, x2 - 4, y2 - 23);
                }
            }
        }
    }
}
