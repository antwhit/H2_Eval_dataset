import java.awt.*;
import java.awt.event.*;

public class OvalTool implements Tool {

    protected DrawingCanvas canvas;

    protected Point startingMousePosition;

    protected Point currentMousePosition;

    protected Color saveColor;

    public OvalTool(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    protected void drawOval(int x, int y, int width, int height) {
        int ovalX = x;
        int ovalY = y;
        int ovalWidth = width;
        int ovalHeight = height;
        if (width < 0) {
            ovalX = x + width;
            ovalWidth = -width;
        }
        if (height < 0) {
            ovalY = y + height;
            ovalHeight = -height;
        }
        Graphics iBGraphics = canvas.getImageBufferGraphics();
        iBGraphics.drawOval(ovalX, ovalY, ovalWidth, ovalHeight);
        canvas.repaint();
    }

    public void mousePressed(MouseEvent e) {
        startingMousePosition = e.getPoint();
        currentMousePosition = startingMousePosition;
        Graphics iBGraphics = canvas.getImageBufferGraphics();
        saveColor = iBGraphics.getColor();
        iBGraphics.setXORMode(Color.lightGray);
        iBGraphics.setColor(Color.white);
        drawOval(startingMousePosition.x, startingMousePosition.y, 0, 0);
    }

    public void mouseDragged(MouseEvent e) {
        Point newMousePosition = e.getPoint();
        drawOval(startingMousePosition.x, startingMousePosition.y, currentMousePosition.x - startingMousePosition.x, currentMousePosition.y - startingMousePosition.y);
        drawOval(startingMousePosition.x, startingMousePosition.y, newMousePosition.x - startingMousePosition.x, newMousePosition.y - startingMousePosition.y);
        currentMousePosition = newMousePosition;
    }

    public void mouseReleased(MouseEvent e) {
        Graphics iBGraphics = canvas.getImageBufferGraphics();
        drawOval(startingMousePosition.x, startingMousePosition.y, currentMousePosition.x - startingMousePosition.x, currentMousePosition.y - startingMousePosition.y);
        iBGraphics.setPaintMode();
        iBGraphics.setColor(saveColor);
        drawOval(startingMousePosition.x, startingMousePosition.y, e.getPoint().x - startingMousePosition.x, e.getPoint().y - startingMousePosition.y);
        OvalObject myoval = new OvalObject(startingMousePosition, e.getPoint(), canvas.getImageBufferGraphics().getColor());
        IDrawingObjectLink myovalLink = new IDrawingObjectLink(myoval);
        myovalLink.next = canvas.firstObject;
        canvas.firstObject = myovalLink;
    }
}
