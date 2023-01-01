import java.awt.*;
import java.applet.*;

public class Prism_Demo extends Applet {

    Env_Box env;

    public void init() {
        env = new Env_Box(100, 100);
    }

    public void paint(Graphics g) {
        env.Draw(g);
    }
}
