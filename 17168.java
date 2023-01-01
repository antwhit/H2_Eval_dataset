import java.awt.*;
import java.awt.event.*;

public class RectangleTool implements Tool {

    protected DrawingCanvas canvas;

    protected Point startingMousePosition;

    protected Point currentMousePosition;

    protected Color saveColor;

    public RectangleTool(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    protected void drawRectangle(int x, int y, int width, int height) {
        int rectX = x;
        int rectY = y;
        int rectWidth = width;
        int rectHeight = height;
        if (width < 0) {
            rectX = x + width;
            rectWidth = -width;
        }
        if (height < 0) {
            rectY = y + height;
            rectHeight = -height;
        }
        Graphics iBGraphics = canvas.getImageBufferGraphics();
        iBGraphics.drawRect(rectX, rectY, rectWidth, rectHeight);
        canvas.repaint();
    }

    public void mousePressed(MouseEvent e) {
        startingMousePosition = e.getPoint();
        currentMousePosition = startingMousePosition;
        Graphics iBGraphics = canvas.getImageBufferGraphics();
        saveColor = iBGraphics.getColor();
        iBGraphics.setXORMode(Color.lightGray);
        iBGraphics.setColor(Color.white);
        drawRectangle(startingMousePosition.x, startingMousePosition.y, 0, 0);
    }

    public void mouseDragged(MouseEvent e) {
        Point newMousePosition = e.getPoint();
        drawRectangle(startingMousePosition.x, startingMousePosition.y, currentMousePosition.x - startingMousePosition.x, currentMousePosition.y - startingMousePosition.y);
        drawRectangle(startingMousePosition.x, startingMousePosition.y, newMousePosition.x - startingMousePosition.x, newMousePosition.y - startingMousePosition.y);
        currentMousePosition = newMousePosition;
    }

    public void mouseReleased(MouseEvent e) {
        Graphics iBGraphics = canvas.getImageBufferGraphics();
        drawRectangle(startingMousePosition.x, startingMousePosition.y, currentMousePosition.x - startingMousePosition.x, currentMousePosition.y - startingMousePosition.y);
        iBGraphics.setPaintMode();
        iBGraphics.setColor(saveColor);
        drawRectangle(startingMousePosition.x, startingMousePosition.y, e.getPoint().x - startingMousePosition.x, e.getPoint().y - startingMousePosition.y);
        RectangleObject myrect = new RectangleObject(startingMousePosition, e.getPoint(), canvas.getImageBufferGraphics().getColor());
        IDrawingObjectLink myrectLink = new IDrawingObjectLink(myrect);
        myrectLink.next = canvas.firstObject;
        canvas.firstObject = myrectLink;
    }
}
