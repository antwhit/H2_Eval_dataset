import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Launcher extends JComponent {

    private Target target;

    private JFrame frame;

    public volatile ArrayList<Missile> missile;

    private int autoDelay = 10;

    private double missileVel = 2.0;

    private Color color;

    private int colorCounter = 0;

    public Launcher(int x, int y, JFrame f) {
        missile = new ArrayList<Missile>();
        this.setBounds(x, y, 10, 10);
        frame = f;
        color = Color.green;
    }

    int count = 0;

    public void autoFire() {
        count++;
        if (count >= autoDelay) {
            if (target != null) {
                if (fire()) count = 0;
                ;
            }
        }
    }

    public void setTarget(Target t) {
        target = t;
    }

    public boolean fire() {
        if (target != null) {
            double tx = target.getX() - this.getX();
            double tvx = target.getXV();
            double ty = target.getY() - this.getY();
            double tvy = target.getYV();
            double Rv = missileVel;
            double a = tvx * tvx + tvy * tvy - Rv * Rv;
            double b = 2 * (tx * tvx + ty * tvy);
            double c = tx * tx + ty * ty;
            double Ta = (double) (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
            double Tb = (double) (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
            double T = Double.POSITIVE_INFINITY;
            if (Ta > 0 && !Double.isNaN(Ta)) T = Ta;
            if (Tb > 0 && !Double.isNaN(Tb)) T = Math.min(Tb, T);
            if (Double.isInfinite(T)) {
                return false;
            }
            double Cx = tx + (tvx * T);
            double Cy = -(ty + (tvy * T));
            double theta = Math.acos(Cx / Math.sqrt(Cx * Cx + Cy * Cy)) * (Cy < 0 ? 1 : -1);
            double Rvx = Rv * Math.cos(theta);
            double Rvy = Rv * Math.sin(theta);
            missile.add(new Missile(this.getX() + 5, this.getY() + 5, Rvx, Rvy, target, this, (int) Math.floor(T) - 1));
            frame.add(missile.get(missile.size() - 1));
            color = Color.red;
            colorCounter = autoDelay;
        }
        return true;
    }

    public void removeMissile(Missile m) {
        frame.remove(m);
        missile.remove(m);
        this.repaint();
        frame.repaint();
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (colorCounter > 0) {
            colorCounter--;
        } else {
            color = Color.green;
        }
    }
}
