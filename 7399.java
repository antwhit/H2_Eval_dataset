import javax.microedition.lcdui.Graphics;
import java.util.*;

public class MobiBoxes implements MobiGameObjects {

    private int maxCol, maxRow, xSpace, ySpace;

    private Vector createdBoxes;

    public MobiBoxes(int maxCol, int maxRow, int xSpace, int ySpace) {
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        this.xSpace = xSpace;
        this.ySpace = ySpace;
    }

    public void create() {
        int vectIndex = 0;
        createdBoxes = new Vector();
        for (int yCount = 0; yCount < maxCol; yCount++) {
            for (int count = 0; count < maxRow; count++) {
                BoxObject box = new BoxObject(xSpace * count + 17, ySpace * yCount + 17, 100, 100, 100, xSpace - 9, ySpace - 9, vectIndex);
                createdBoxes.addElement(box);
                vectIndex++;
            }
        }
    }

    public void draw(Graphics g, Vector toDraw) {
        for (int count = 0; count < toDraw.size(); count++) {
            BoxObject b = (BoxObject) (toDraw.elementAt(count));
            g.setColor(b.getColorRed(), b.getColorBlue(), b.getColorGreen());
            g.fillRect(b.getXCoOrd(), b.getYCoOrd(), b.getHeight(), b.getWidth());
        }
    }

    public Vector getCreated() {
        return createdBoxes;
    }
}
