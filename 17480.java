import java.awt.Color;

public class FlowText {

    String text;

    Color c;

    int x;

    int y;

    /**constructor*/
    FlowText(String text, Color c, int x, int y) {
        this.text = text;
        this.c = c;
        this.x = x;
        this.y = y;
    }

    public void setText(String s) {
        text = s;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return c;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
