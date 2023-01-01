import processing.core.PApplet;

public class SecondApplet extends PApplet {

    int xPos = 0;

    int yPos = 0;

    public Drawer drawer;

    public boolean opengl;

    public boolean onetime = true;

    public void setup() {
        if (onetime) {
            if (opengl) {
            } else size(200, 200);
            if (drawer != null) {
                drawer.setup(this);
            }
            onetime = false;
        }
    }

    public void draw() {
        if (drawer != null) drawer.draw(this);
    }

    @Override
    public void keyPressed() {
        drawer.keyPressed(key);
    }
}
